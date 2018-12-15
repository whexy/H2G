package h2g;

import java.awt.Color;
import java.awt.Font;

class CanvaStyle {
    // Background
    int[] bgSize = {1000, 1000};
    Color bgColor = Color.WHITE;
    double bgTrantransparency = 1;

    // Coordinate
    int[] coordSize = {800, 800};
    int xProject = bgSize[0] / 2;
    int yProject = bgSize[1] / 2;
    double blankRatio = 0.5;
    boolean rotated = true;
    double expandRatio = 0.01;
    double coordTrantransparency = 0.8;

    // Bar
    String[] barPattern = {"Bar1", ""};
    double[] barWidthRatio = {0.5,-1};
    String[] barSkin = {"Basic","Basic1","Basic","Basic1","Basic","Basic1","Basic","Basic1","Basic","Basic1","Basic","Basic1","Basic","Basic1","Basic","Basic1","Basic","Basic1","Basic","Basic1"};
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
    /*    Color rulerMarkColor = Color.BLACK;*/
    Color keyColor = Color.BLACK;
    Color headerColor = Color.BLACK;
    Color footerColor = Color.BLACK;
    
    /**
     * Load Config from json
     *
     * @param path the json file path
     */
    public void loadConfig(String path) throws Exception {
        ConfigLoader loader = new ConfigLoader(path);

        // Background
        Object bgSizes = loader.get("bg.size");
        if (bgSizes instanceof Integer) {
            bgSize = (int[]) bgSizes;
        }

        bgColor = loader.getColor("bg.Color");

        //Coordinate

        Object _coordSize = loader.get("coord.size");
        if (_coordSize instanceof Integer) {
            coordSize = (int[]) _coordSize;
        }
        xProject = bgSize[0] / 2;
        yProject = bgSize[1] / 2;
        double _blankRatio = loader.getDouble("coord.blankratio");
        if (_blankRatio != 0) {
            blankRatio = _blankRatio;
        }
        /*rotate = loader.getBool("rotate");*/

        // Layout
        int _rulerXoffset = loader.getInt("rulerxoffset");
        if (_rulerXoffset != 0) rulerXoffset = _rulerXoffset;
        int _keysYoffset = loader.getInt("keysyoffset");
        if (_keysYoffset != 0) keysYoffset = _keysYoffset;
        int _headerXoffset = loader.getInt("headerxoffset");
        if (_headerXoffset != 0) headerXoffset = _headerXoffset;
        int _headerYoffset = loader.getInt("headeryoffset");
        if (_headerYoffset != 0) headerYoffset = _headerYoffset;
        int _footerXoffset = loader.getInt("footerxoffset");
        if (_footerXoffset != 0) footerXoffset = _footerXoffset;
        int _footerYoffset = loader.getInt("footeryoffset");
        if (_footerYoffset != 0) footerYoffset = _footerYoffset;

        // Fonts
        Font _rulerFont = loader.getFont("rulerFont");
        if (_rulerFont != null) rulerFont = _rulerFont;
        Font _keysFont = loader.getFont("keysFont");
        if (_keysFont != null) keysFont = _keysFont;
        Font _headerFont = loader.getFont("headerFont");
        if (_headerFont != null) headerFont = _headerFont;
        Font _footerFont = loader.getFont("footerFont");
        if (_footerFont != null) footerFont = _footerFont;
        // Switch
        /*isBarFilled = loader.getBool("isBarFilled");
        hasBarFrame = loader.getBool("hasBarFrame");*/
        hasBorder = loader.getBool("hasBorder");
        hasRuler = loader.getBool("hasRuler");
        hasRightRuler = loader.getBool("hasRightRuler");
        hasHeader = loader.getBool("hasHeader");
        hasFooter = loader.getBool("hasFooter");

        // Color
        Color _borderColor = loader.getColor("borderColor");
        if (_borderColor != null) borderColor = _borderColor;
        Color _rulerColor = loader.getColor("rulerColor");
        if (_rulerColor != null) rulerColor = _rulerColor;
        Color _headerColor = loader.getColor("headerColor");
        if (_headerColor != null) headerColor = _headerColor;
        Color _footerColor = loader.getColor("footerColor");
        if (_footerColor != null) footerColor = _footerColor;
        Color _keyColor = loader.getColor("keyColor");
        if (_keyColor != null) keyColor = _keyColor;
    }
    public void loadConfig() throws Exception {
        loadConfig("CanvaStyle.json");
    }
}