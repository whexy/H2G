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
    int length;
    private int[] LegendIconSize = new int[]{50, 50};
    private double[] LegendIconScale = new double[]{0, 50};
    private int maxLegendBarWidth = 0;
    private Font font = new Font("Microsoft YaHei Light", Font.PLAIN, 50);
    private int Height = 60;
    private ConfigLoader loader;
    public double LegendX = 0, LegendY = 0;

    public void loadConfig(String pattern) throws Exception {
        loader = new ConfigLoader(pattern);
        keys = getKeys();
        skin = getSkins();
        length = keys.length;
        LegendIconSize = loader.setIntegerArray(LegendIconSize, "legend.icons");
        LegendIconScale[1] = LegendIconSize[1];
        font = loader.setFont(font, "legend.font");
        Height = loader.setInt(Height, "legend.height");
        LegendX = loader.setDouble(LegendX, "legend.x");
        LegendY = loader.setDouble(LegendY, "legend.y");
        for (int i = 0; i < length; i++) {
            maxLegendBarWidth = Math.max(keys[i].length() * font.getSize(), maxLegendBarWidth);
        }
    }

    public BufferedImage getBarLegend(int id) {
        BarBasicSkin barGenerator = (BarBasicSkin) DynamicLoader.get(skin[id], LegendIconSize, LegendIconScale, false);
        assert barGenerator != null;
        BufferedImage squareBlock = barGenerator.getBarChart(0, "", Height);
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

    private String[] getKeys() throws Exception {
        ArrayList<String> keyList = new ArrayList<>();
        for (int i = 0; ; i++) {
            if (loader.get("bar." + i) == null) break;
            keyList.add(loader.getStr("bar." + i + ".key"));
        }
        String[] _O = new String[keyList.size()];
        for (int i = 0; i < keyList.size(); i++) {
            _O[i] = keyList.get(i);
        }
        return _O;
    }

    private String[] getSkins() throws Exception {
        ArrayList<String> skinList = new ArrayList<>();
        for (int i = 0; ; i++) {
            if (loader.get("bar." + i) == null) break;
            skinList.add(loader.getStr("bar." + i + ".skin"));
        }
        String[] _O = new String[skinList.size()];
        for (int i = 0; i < skinList.size(); i++) {
            _O[i] = skinList.get(i);
        }
        return _O;
    }

    public static void main(String[] args) throws Exception {
        LegendDrawer drawer = new LegendDrawer();
        drawer.loadConfig("Data.json");
        BufferedImage Legend = drawer.getLegend();
        new SigDraw(Legend, true).save("Legend.jpg");
    }
}
