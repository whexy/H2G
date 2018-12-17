package h2g;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LegendDrawer {

    //TODO: JSON FIXING
    private final static int X = 0;
    private final static int Y = 1;
    private String[] keys;
    private String[] skin;
    int elementNum;
    private int[] iconSize = new int[]{50, 50};
    private double[] iconScale = new double[]{0, 50};
    private int maxLegendBarWidth = 0;
    private Font font;
    private int Height = 60;
    private CanvaStyle canvaStyle;
    private boolean rotated;

    public LegendDrawer(CanvaStyle canvaStyle, HistogramData histogramData) throws Exception {
        this.canvaStyle = canvaStyle;
        rotated = canvaStyle.rotated;
        keys = histogramData.keys;
        skin = canvaStyle.barSkin;
        elementNum = keys.length;
        iconSize = canvaStyle.iconSize;
        iconScale[1] = iconSize[1];
        font = canvaStyle.legendFont;
        Height = canvaStyle.legendHeight;
        keys = histogramData.keys;
        for (int i = 0; i < elementNum; i++) {
            maxLegendBarWidth = Math.max(keys[i].length() * font.getSize(), maxLegendBarWidth);
        }
    }

    public BufferedImage getBarLegend(int id) {
        BarGenerator barGenerator = DynamicLoader.get(skin[id], iconSize, iconScale, rotated);
        assert barGenerator != null;
        BufferedImage squareBlock = barGenerator.getBarChart(0, "", Height);
        int textWidth = maxLegendBarWidth;
        int textHeight = font.getSize();
        SigDraw Legend = new SigDraw(iconSize[X] + textWidth, (int) (textHeight * 1.25), true);
        Legend.setPenColor(Color.BLACK);
        Legend.setFont(font);
        Legend.text(iconSize[X] + textWidth / 2.0, textHeight / 2.0, keys[id]);
        Legend.picture(iconSize[X] / 2.0, iconSize[Y] / 2.0, squareBlock);
        return Legend.getBuffImg();
    }

    public BufferedImage getLegend() {
        int Width = iconSize[X]+ maxLegendBarWidth;
        int rowNum = canvaStyle.legendRowNum;
        int columnNum = canvaStyle.legendColumnNum;

        SigDraw Legend = new SigDraw(Width * columnNum, Height * rowNum, true);
        for (int row = 0, i=0 ; row < rowNum; row++) {
            for(int column = 0; column < columnNum && i<elementNum ; column++, i++) {
                BufferedImage element = getBarLegend(i);
                Legend.picture(Width*(column+1) - Width/2.0, Height*(row+1) - Height/2.0, element);
            }
        }
        return Legend.getScaledImage(canvaStyle.legendScaleFactor, Scalr.Method.ULTRA_QUALITY);
    }

    public static void main(String[] args) throws Exception {
        /*LegendDrawer drawer = new LegendDrawer();
        drawer.loadConfig("Data.json");
        BufferedImage Legend = drawer.getLegend();
        new SigDraw(Legend, true).save("Legend.jpg");*/
    }
}
