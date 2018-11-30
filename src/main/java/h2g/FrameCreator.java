package h2g;

import java.awt.Color;
import java.awt.Font;

class FrameCreator {
    CanvaStyle c;
    HistogramData d;
    SigDraw bg, coord;
    CoordProjecter cp;

    double[] xScale = new double[2]; // MIN, MAX
    double[] yValue; // MIN, MAX
    int[] bgSize;
    int[] coordSize;
    int[] cBorder = new int[4];
    double barWidth, barSpace;
    int barNum;
    double rulerStep;
    int rulerGrade;
    public FrameCreator(CanvaStyle c, HistogramData d) {
        this.c = c;
        this.d = d;
        init();
        draw();
    }

    private void init() {
        yValue = d.yValue;
        bgSize = c.bgSize;
        coordSize = c.coordSize;
        barSpace = c.blankRatio;
        barWidth = 1.0 - barSpace;
        barNum = d.keys.length;
        rulerStep = d.rulerStep;
        rulerGrade = d.rulerGrade;
        xScale[MIN] = -barSpace;
        xScale[MAX] = barNum*1.0;

        cBorder[LEFT] = c.xProject - coordSize[WIDTH]/2;
        cBorder[RIGHT] = c.xProject + coordSize[WIDTH]/2;
        cBorder[UP] = c.yProject + coordSize[HEIGHT]/2;
        cBorder[DOWN] = c.yProject - coordSize[HEIGHT]/2;

        bg = new SigDraw(bgSize[WIDTH], bgSize[HEIGHT], true);
        coord = new SigDraw(coordSize[WIDTH], coordSize[HEIGHT], false);
        coord.setXscale(xScale[MIN], xScale[MAX]);
        coord.setYscale(yValue[MIN], yValue[MAX]);
        bg.clear(c.bgColor);
        cp = new CoordProjecter(bg, coord, c.xProject, c.yProject);
    }

    public void draw() {
        plotBars();
        plotRuler();
        plotKeys();
        if (c.hasBorder)
            plotBorder();
        if (c.hasHeader)
            plotHeader();
        if (c.hasFooter)
            plotFooter();
    }

    private void plotBars() {
        /*
        double[] a = d.values;
        int n = a.length;
        setHistogramScale(n);
        if (f.isBarFilled) {
            StdDraw.setPenColor(f.barFillColor);
            for (int i = 0; i < n; i++) {
                StdDraw.filledRectangle(i, a[i] / 2, 0.25, a[i] / 2);
                // (x, y, halfWidth, halfHeight)
            }
        }
        if (f.hasBarFrame) {
            StdDraw.setPenColor(f.barFrameColor);
            for (int i = 0; i < n; i++) {
                StdDraw.rectangle(i, a[i] / 2, 0.25, a[i] / 2);
                // (x, y, halfWidth, halfHeight)
            }
        }*/
    }
    private void plotRuler() {
        Font font = c.rulerFont; // TO BE Customized
        bg.setFont(font);
        bg.setPenColor(c.rulerColor);
        final double x0 = cBorder[LEFT]-5 , x1 = cBorder[LEFT]+5;
        final double x2 = cBorder[RIGHT]-5 , x3 = cBorder[RIGHT]+5;
        String[] mark = new String[rulerGrade + 1];

        for (int i = 0; i <= rulerGrade; i++) {
            double y = cp.getY( yValue[MIN] + i * rulerStep );
            mark[i] = numberForRuler(y);
            if(c.hasRuler) bg.line(x0, y, x1, y);
            if(c.hasRightRuler) bg.line(x2, y, x3, y);
        }
        int len = maxMarkLength(mark);
        final double xl = cBorder[LEFT]-c.rulerXoffset;
        final double xr = cBorder[RIGHT]+c.rulerXoffset;
        for (int i = 0; i <= rulerGrade; i++) {
            double y = cp.getY( yValue[MIN] + i * rulerStep );
            if(c.hasRuler) bg.text(xl, y, String.format("%" + len + "s", mark[i]));
            if(c.hasRightRuler) bg.text(xr, y, String.format("%" + len + "s", mark[i]));
        }
    }

    private String numberForRuler(double x) { // TO BE Customized
        if (yValue[MAX] >= 5 && rulerStep > 1)
            return "" + (int) x;
        if (rulerStep > 0.1)
            return String.format("%.1f", x);
        if (rulerStep > 0.01)
            return String.format("%.2f", x);
        if (rulerStep > 0.001)
            return String.format("%.3f", x);
        if (rulerStep > 0.0001)
            return String.format("%.4f", x);
        if (rulerStep > 0.00001)
            return String.format("%.5f", x);
        return String.format("%g", x);
    }

    private int maxMarkLength(String[] sa) {
        int n = sa[0].length();
        for (String s : sa)
            if (n < s.length())
                n = s.length();
        return n;
    }

    private void plotKeys() {
        Font font = c.keysFont; // TO BE Customized
        bg.setFont(font);
        bg.setPenColor(c.keyColor);
        final double y = cBorder[DOWN] - c.keysYoffset;
        for (int i = 0; i < barNum; i++) {
            if (d.keys[i].length() >= 1) {
                bg.text(i, y, d.keys[i]);
            }
        }
    }

    private void plotBorder() {
        bg.setPenColor(c.borderColor);
        bg.rectangle(cBorder[LEFT], cBorder[UP], cBorder[RIGHT], cBorder[DOWN]);
    }


    private void plotHeader() {
        Font font = c.headerFont; // TO BE Customized
        bg.setFont( font ); 
        double x = c.headerXoffset + (cBorder[LEFT]+cBorder[RIGHT])/2;
        double y = c.headerYoffset + cBorder[UP];
        bg.setPenColor( c.headerColor );
        bg.text( x, y, d.header );
    }

    private void plotFooter() {
        Font font = c.footerFont; // TO BE Customized
        bg.setFont(font);
        double x = c.headerXoffset + (cBorder[LEFT]+cBorder[RIGHT])/2;
        double y = -c.headerYoffset + cBorder[DOWN];
        bg.setPenColor(c.footerColor);
        bg.text(x, y, d.footer);
    }

    private final static int UP = 0;
    private final static int DOWN = 1;
    private final static int LEFT = 2;
    private final static int RIGHT = 3;
    private final static int MIN = 0;
    private final static int MAX = 1;
    private final static int WIDTH = 0;
    private final static int HEIGHT = 1;

    public static void main(String[] args) {
        CanvaStyle c = new CanvaStyle();
        HistogramData d = new HistogramData();
        d.yValue[0] = 0;
        d.yValue[1] = 1000.0;
        d.rulerStep = 10;
        d.rulerGrade = 8;
        FrameCreator fc = new FrameCreator(c, d);
        fc.bg.save("test.jpg");
    }
}