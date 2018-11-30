package h2g;

import java.awt.Color;
import java.awt.Font;

class CanvaStyle {
    // Backgroud
    int[] bgSize = {1000, 1000};
    Color bgColor = Color.WHITE;

    // Coordinate
    int[] coordSize = {800, 800};
    int xProject = bgSize[0]/2;
    int yProject = bgSize[1]/2;
    Color coordColor = Color.BLACK;
    double blankRatio = 0.5;
    boolean rotate = false;

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
    Font headerFont = new Font( "calibri", Font.PLAIN, 20);
    Font footerFont = new Font("consolas", Font.BOLD, 16);

    // Switch
    boolean hasBorder = true;
    boolean hasRuler = true;
    boolean hasRightRuler = true;
    boolean hasHeader = true;
    boolean hasFooter = true;
    

    // Color
    Color barFillColor = Color.BLACK;
    Color barFrameColor = Color.BLACK;
    Color borderColor = Color.BLACK;
    Color rulerColor = Color.BLACK;
    Color rulerMarkColor = Color.BLACK;
    Color keyColor = Color.BLACK;
    Color headerColor = Color.BLACK;
    Color footerColor = Color.BLACK;

    public void loadConfig() {

    }
}