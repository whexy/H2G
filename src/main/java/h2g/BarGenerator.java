package h2g;

import java.awt.image.BufferedImage;

public abstract class BarGenerator {
    protected final static int HEIGHT = 1;
    protected final static int WIDTH = 0;
    protected final static int MAX = 1;
    protected final static int MIN = 0;
    public SigDraw baseIMG;
    public int[] barSize;
    public double[] scale;
    public BarGenerator(int[] barSize, double[] scale) {
        this.barSize = barSize;
        this.scale = scale;
    };
    public abstract BufferedImage getBarchart(int frame, double val);
    public abstract BufferedImage getBarchart(int frame, double val1, double val2);
    public abstract void loadConfig(String filename);
    public abstract void loadConfig();
}