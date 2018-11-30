package h2g;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

class CanvaStyle {
    // Background
    int[] bgSize = {1000, 1000};
    Color bgColor = Color.WHITE;

    // Coordinate
    int[] coordSize = {800, 800};
    int xProject = bgSize[0] / 2;
    int yProject = bgSize[1] / 2;
    double blankRatio = 0.5;
    /*boolean rotate = false;*/ // haven't be used now. But I should keep it.

    // Layout
    int rulerXoffset = 20;
    int keysYoffset = 20;
    int headerXoffset = 0;
    int headerYoffset = 20;
    int footerXoffset = 0;
    int footerYoffset = 50;


    // Fonts
    Font rulerFont = new Font("consolas", Font.PLAIN, 12);
    Font keysFont = new Font("consolas", Font.PLAIN, 12);
    Font headerFont = new Font("calibri", Font.PLAIN, 20);
    Font footerFont = new Font("consolas", Font.BOLD, 16);

    // Switch
/*    boolean isBarFilled = true;
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

        Object _bgColors = loader.get("bg.color");
        if (_bgColors instanceof Integer) {
            int[] bgColors = (int[]) _bgColors;
            bgColor = new Color(bgColors[0], bgColors[1], bgColors[2]);
        }

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
        if (_rulerXoffset != 0) {
            rulerXoffset = _rulerXoffset;
        }
        int _keysYoffset = loader.getInt("keysyoffset");
        if (_keysYoffset != 0) {
            keysYoffset = _keysYoffset;
        }
        int _headerXoffset = loader.getInt("headerxoffset");
        if (_headerXoffset != 0) {
            headerXoffset = _headerXoffset;
        }
        int _headerYoffset = loader.getInt("headeryoffset");
        if (_headerYoffset != 0) {
            headerYoffset = _headerYoffset;
        }
        int _footerXoffset = loader.getInt("footerxoffset");
        if (_footerXoffset != 0) {
            footerXoffset = _footerXoffset;
        }
        int _footerYoffset = loader.getInt("footeryoffset");
        if (_footerYoffset != 0) {
            footerYoffset = _footerYoffset;
        }

        // Fonts
        // Default part has been shut down by AI "WhexySTAR" automatically.
        // **ATTENTION**: JSON file must contains Font settings ! Other wise there will be terrible error!
        String rulerFontname = loader.getStr("rulerfont.name");
        int rulerFontform = loader.getInt("rulerfont.form");
        int rulerFontsize = loader.getInt("rulerfont.size");
        rulerFont = new Font(rulerFontname, rulerFontform, rulerFontsize);

        String keysFontname = loader.getStr("keysfont.name");
        int keysFontform = loader.getInt("keysfont.form");
        int keysFontsize = loader.getInt("keysfont.size");
        keysFont = new Font(keysFontname, keysFontform, keysFontsize);

        String headerFontname = loader.getStr("headerfont.name");
        int headerFontform = loader.getInt("headerfont.form");
        int headerFontsize = loader.getInt("headerfont.size");
        headerFont = new Font(headerFontname, headerFontform, headerFontsize);

        String footerFontname = loader.getStr("footerfont.name");
        int footerFontform = loader.getInt("footerfont.form");
        int footerFontsize = loader.getInt("footerfont.size");
        footerFont = new Font(footerFontname, footerFontform, footerFontsize);

        // Switch
        /*isBarFilled = loader.getBool("isbarfilled");
        hasBarFrame = loader.getBool("hasbarframe");*/
        hasBorder = loader.getBool("hasborder");
        hasRuler = loader.getBool("hasruler");
        hasRightRuler = loader.getBool("hasrightruler");
        hasHeader = loader.getBool("hasheader");
        hasFooter = loader.getBool("hasfooter");

        // Color
        Object _Colors = loader.get("borderColor");
        if (_Colors instanceof Integer) {
            int[] Colors = (int[]) _Colors;
            borderColor = new Color(Colors[0], Colors[1], Colors[2]);
        }

        _Colors = loader.get("rulerColor");
        if (_Colors instanceof Integer) {
            int[] Colors = (int[]) _Colors;
            rulerColor = new Color(Colors[0], Colors[1], Colors[2]);
        }

        _Colors = loader.get("headerColor");
        if (_Colors instanceof Integer) {
            int[] Colors = (int[]) _Colors;
            headerColor = new Color(Colors[0], Colors[1], Colors[2]);
        }

        _Colors = loader.get("footerColor");
        if (_Colors instanceof Integer) {
            int[] Colors = (int[]) _Colors;
            footerColor = new Color(Colors[0], Colors[1], Colors[2]);
        }

        _Colors = loader.get("keyColor");
        if (_Colors instanceof Integer) {
            int[] Colors = (int[]) _Colors;
            keyColor = new Color(Colors[0], Colors[1], Colors[2]);
        }
    }
}