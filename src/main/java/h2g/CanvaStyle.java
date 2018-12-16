package h2g;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

class CanvaStyle {
    // Background
    int[] bgSize = {1000, 1000};
    Color bgColor = Color.WHITE;
    double bgTransparency = 1;

    // Coordinate
    int[] coordSize = {800, 800};
    int xProject = bgSize[0] / 2;
    int yProject = bgSize[1] / 2;
    boolean rotated = true;
    double expandRatio = 0.01;
    double coordTrantransparency = 0.8;

    // Bar
    String[] barPattern = {"Bar1", ""};
    double[] barWidthRatio = {0.5, -1};
    String[] barSkin = {"Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1", "Basic", "Basic1"};
    int FPD = 420;
    int FPS = 60;
    double maxVelocity = 0.1;
    double maxTrantransparency = 1;
    double minTrantransparency = 0.2;
    boolean isStackedBar = false;
    int maxRulerGrade = 10;
    boolean enableDynamicRuler = true;
    String sortMethod = "BubbleSort"; // Availble method: BubbleSort, SelectionSort

    // Layout
    int rulerXoffset = 20;
    int keysYoffset = 50;
    int headerXoffset = 0;
    int headerYoffset = 50;
    int footerXoffset = 0;
    int footerYoffset = 50;


    // Fonts
    Font rulerFont = new Font("consolas", Font.PLAIN, 12);
    Font keysFont = new Font("consolas", Font.PLAIN, 12);
    Font headerFont = new Font("calibri", Font.PLAIN, 20);
    Font footerFont = new Font("consolas", Font.BOLD, 16);

    // Switch
/*  boolean isBarFilled = true;
    boolean hasBarFrame = true;*/
    boolean hasBorder = true;
    boolean hasRuler = true;
    boolean hasRightRuler = true;
    boolean hasHeader = true;
    boolean hasFooter = true;


    // Color
/*    Color barFillColor = Color.BLACK;
    Color barFrameColor = Color.BLACK;*/
    Color borderColor = Color.BLACK;
    Color rulerColor = Color.BLACK;
    //Color rulerMarkColor = Color.BLACK;
    Color keysColor = Color.BLACK;
    Color headerColor = Color.BLACK;
    Color footerColor = Color.BLACK;
    ConfigLoader loader;

    /**
     * Load Config from json
     *
     * @param path the json file path
     */
    public void loadConfig(String path) throws Exception {
        loader = new ConfigLoader(path);

        // Background,bg
        bgSize = loader.setIntegerArray(bgSize, "bg.size");
        bgColor = loader.setColor(bgColor, "bg.Color");
        bgTransparency = loader.setDouble(bgTransparency, "bg.transparency");

        //Coordinate,coord
        coordSize = loader.setIntegerArray(coordSize, "coord.size");
        rotated = loader.setBool(rotated, "coord.rotated");
        expandRatio = loader.setDouble(expandRatio, "coord.expandRatio");
        coordTrantransparency = loader.setDouble(coordTrantransparency, "coord.transparency");
        borderColor = loader.setColor(borderColor, "coord.borderColor");
        xProject = bgSize[0] / 2;
        yProject = bgSize[1] / 2;

        // Header
        headerXoffset = loader.setInt(headerXoffset, "header.offset.0");
        headerYoffset = loader.setInt(headerYoffset, "header.offset.1");
        headerFont = loader.setFont(headerFont, "header.font");
        headerColor = loader.setColor(headerColor, "header.color");

        // Footer
        footerXoffset = loader.setInt(footerXoffset, "footer.offset.0");
        footerYoffset = loader.setInt(footerYoffset, "footer.offset.1");
        footerFont = loader.setFont(footerFont, "footer.font");
        footerColor = loader.setColor(footerColor, "footer.color");

        // Ruler
        rulerXoffset = loader.setInt(rulerXoffset, "ruler.offset");
        rulerFont = loader.setFont(rulerFont, "ruler.font");
        rulerColor = loader.setColor(rulerColor, "ruler.color");
        maxRulerGrade = loader.setInt(maxRulerGrade, "ruler.maxRulerGrade");
        enableDynamicRuler = loader.setBool(enableDynamicRuler, "ruler.enableDynamicRuler");

        //Key
        keysYoffset = loader.setInt(keysYoffset, "key.offset");
        keysFont = loader.setFont(keysFont, "key.font");
        keysColor = loader.setColor(keysColor, "key.color");
        /*there should be a markColor, but it is not in key nor in the program*/

        // interpolator
        FPS = loader.setInt(FPS, "interpolator.FPS");
        FPD = loader.setInt(FPD, "interpolator.FPD");
        maxVelocity = loader.setDouble(maxVelocity, "interpolator.maxVelocity");
        sortMethod = loader.setStr(sortMethod, "interpolator.sortMethod");

        //bar
        barPattern = loader.setStringArray(barPattern, "bar.barPattern");
        barWidthRatio = loader.setDoubleArray(barWidthRatio, "bar.barWidthRatio");
        barSkin = getBarSkin();
        
        //Basic_Config
        isStackedBar = loader.setBool(isStackedBar, "isStackedBar");
        hasRightRuler = loader.setBool(hasRightRuler, "hasRightRuler");
        hasBorder = loader.setBool(hasBorder, "hasBorder");
        hasHeader = loader.setBool(hasHeader, "hasHeader");
        hasFooter = loader.setBool(hasFooter, "hasFooter");
    }

    public void loadConfig() throws Exception {
        loadConfig("Data.json");
    }

    private String[] getBarSkin() throws Exception {
        ArrayList<String> barSkinList = new ArrayList<>();
        for (int i = 0; ; i++) {
            if (loader.get("bar." + i) == null) break;
            barSkinList.add(loader.getStr("bar." + i + ".skin"));
        }
        String[] _O = new String[barSkinList.size()];
        for (int i = 0; i < barSkinList.size(); i++) {
            _O[i] = barSkinList.get(i);
        }
        return _O;
    }
}