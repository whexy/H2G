package h2g;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BarBasicSkin extends BarGenerator {
    private Color[] segColor = {Color.BLACK, Color.BLUE, Color.WHITE};
    private Color frameColor = Color.GREEN;
    private boolean isBarFilled = true;

    @Override
    public String toString() {
        return "BarBasicSkin{" +
                "segColor=" + segColor +
                ", frameColor=" + frameColor +
                ", isBarFilled=" + isBarFilled +
                ", hasBarFrame=" + hasBarFrame +
                ", frameSize=" + frameSize +
                '}';
    }

    private boolean hasBarFrame = true;
    private double frameSize = 0.01;
    public BarBasicSkin(int[] barSize, double[] scale) {
        super(barSize, scale);
        baseIMG = new SigDraw(barSize[WIDTH], barSize[HEIGHT], true);
        baseIMG.setYscale(scale[MIN], scale[MAX]);
        baseIMG.setPenRadius(frameSize);
    }
    @Override
    public BufferedImage getBarChart(int frame, double val) {
        return getBarChart(frame, new double[]{val});
    }
    @Override
    public BufferedImage getBarChart(int frame, double[] valList) {
        double halfWidth = barSize[WIDTH]/2;
        double baseVal = 0;
        for(int x=0;x<valList.length && x<segColor.length;++x) {
            baseIMG.setPenColor(segColor[x]);
            if(isBarFilled) baseIMG.filledRectangle(halfWidth, baseVal+valList[x]/2, halfWidth, valList[x]/2);
            else baseIMG.rectangle(halfWidth, baseVal+valList[x]/2, halfWidth, valList[x]/2);
            baseVal += valList[x];
        }       

        baseIMG.setPenColor(frameColor);
        if(hasBarFrame) baseIMG.rectangle(halfWidth, (scale[MAX] + scale[MIN])/2, halfWidth, (scale[MAX] - scale[MIN])/2);
        //return baseIMG.getSubImage(x, y, width, height);
        return baseIMG.getBuffImg();
    }
    @Override
    public void loadConfig(String filename) throws Exception {
        ConfigLoader loader = new ConfigLoader(filename);
        ArrayList<Color> segColor = new ArrayList<>();
        for(int x=1;;++x) {
            Color _segColor = loader.getColor("segColor"+x);
            if (_segColor != null) segColor.add(_segColor);
            else break;
        }
        if(!segColor.isEmpty()) this.segColor = (Color[])(segColor.toArray());

        Color _frameColor = loader.getColor("frameColor");
        if (_frameColor != null) frameColor = _frameColor;
        isBarFilled = loader.getBool("isBarFilled");
        hasBarFrame = loader.getBool("hasBarFrame");
        double _frameSize = loader.getDouble("frameSize");
        if (_frameSize != 0) frameSize = _frameSize;
    }
    @Override
    public void loadConfig() throws Exception {
        loadConfig("BarBasicSkin.json");
    }
    public static void main(String[] args) throws Exception {
        BarBasicSkin b = new BarBasicSkin(new int[]{100,1000} ,new double[]{0,10000});
        b.loadConfig();
        System.out.println(b);
        SigDraw s = new SigDraw( b.getBarChart(0, new double[]{800, 6000}) ,true);
        s.save("test1.jpg");
        new SigDraw( s.getSubImage(50, 500, 20, 200) ).save("test2.jpg");
    }
}