package h2g;

import java.awt.image.BufferedImage;

public abstract class Barchart {
    public static int width,height;
    public static SigDraw baseIMG;
    private boolean hasInit;
    public double xmin,xmax;
    
    public void setXScale(double xmin, double xmax) {
        this.xmin = xmin;
        this.xmax = xmax;
    }
    public static void init(int width, int height, double xmin, double xmax) {}
    public abstract BufferedImage getBarchart(int frame, double x);
    public abstract BufferedImage getBarchart(int frame, double x1, double x2);
}