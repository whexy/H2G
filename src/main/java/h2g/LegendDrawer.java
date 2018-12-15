package h2g;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LegendDrawer {

    //TODO: JSON FIXING
    final static int X = 0;
    final static int Y = 1;
    private String[] keys;
    private String[] skin;
    int length;
    private int[] LegendIconSize = new int[]{500, 500};
    private double[] LegendIconScale = new double[]{0, 500};
    private int maxLegendBarWidth = 0;
    private Font font = new Font("Microsoft YaHei Light", Font.PLAIN, 500);
    private int Height = 600;

    public void loadConfig(String pattern) throws Exception {
        ConfigLoader loader = new ConfigLoader(pattern);
        keys = loader.getStringArray("keys");
        skin = loader.getStringArray("barSkin");
        if (keys.length != skin.length) {
            throw new Exception("Keys and Skins have different length!");
        }
        length = keys.length;
        for (int i = 0; i < length; i++) {
            maxLegendBarWidth = Math.max(keys[i].length() * font.getSize(), maxLegendBarWidth);
        }
    }

    public BufferedImage getBarLegend(int id) {
        BarBasicSkin barGenerator = (BarBasicSkin) DynamicLoader.get(skin[id], LegendIconSize, LegendIconScale, false);
        assert barGenerator != null;
        BufferedImage squareBlock = barGenerator.getBarChart(0, "", LegendIconSize[Y]);
        int textWidth = maxLegendBarWidth;
        int textHeight = font.getSize();
        SigDraw Legend = new SigDraw(LegendIconSize[X] + textWidth, (int) (textHeight * 1.25), true);
        Legend.setPenColor(Color.BLACK);
        Legend.setFont(font);
        Legend.text(LegendIconSize[X] + textWidth / 2.0, textHeight / 2.0, keys[id]);
        Legend.picture(LegendIconSize[X] / 2.0, LegendIconSize[Y] / 2.0, squareBlock);
        return Legend.getScaledImage(1, Scalr.Method.ULTRA_QUALITY);
    }

    public BufferedImage getLegend() {
        int Width = LegendIconSize[X] + maxLegendBarWidth;
        SigDraw Legend = new SigDraw(Width * length, Height, true);
        for (int i = 0; i < length; i++) {
            BufferedImage element = getBarLegend(i);
            Legend.picture(Width * (i + 1) - Width / 2.0, element.getHeight() / 2.0, element);
        }
        return Legend.getScaledImage(1, Scalr.Method.ULTRA_QUALITY);
    }

    public static void main(String[] args) throws Exception {
        LegendDrawer drawer = new LegendDrawer();
        drawer.loadConfig("Data.json");
        BufferedImage Legend = drawer.getLegend();
        new SigDraw(Legend, true).save("Legend.jpg");
    }
}
