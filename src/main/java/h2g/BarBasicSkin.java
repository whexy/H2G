package h2g;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BarBasicSkin extends BarGenerator {
    Color segColor1 = Color.BLACK;
    Color segColor2 = Color.BLUE;
    Color segColor3 = Color.WHITE;
    Color frameColor = Color.GREEN;
    boolean isBarFilled = true;
    boolean hasBarFrame = true;
    double frameSize = 0.01;
    public BarBasicSkin(int[] barSize, double[] scale) {
        super(barSize, scale);
        baseIMG = new SigDraw(barSize[WIDTH], barSize[HEIGHT], true);
        baseIMG.setYscale(scale[MIN], scale[MAX]);
        baseIMG.setPenRadius(frameSize);
    }
    @Override
    public BufferedImage getBarchart(int frame, double val) {
        return getBarchart(frame, val, 0);
    }
    @Override
    public BufferedImage getBarchart(int frame, double val1, double val2) {
        double halfWidth = barSize[WIDTH]/2;
        baseIMG.setPenColor(segColor1); 
        if(isBarFilled) baseIMG.filledRectangle(halfWidth, val1/2, halfWidth, val1/2);
        else baseIMG.rectangle(halfWidth, val1/2, halfWidth, val1/2);

        baseIMG.setPenColor(segColor2);
        if(isBarFilled) baseIMG.filledRectangle(halfWidth, val1 + val2/2, halfWidth, val2/2);
        else baseIMG.rectangle(halfWidth, val1 + val2/2, halfWidth, val2/2);

        double val3 = scale[HEIGHT]-val1-val2;
        baseIMG.setPenColor(segColor3);
        if(isBarFilled) baseIMG.filledRectangle(halfWidth, val1 + val2 + val3/2, halfWidth, val3/2);
        else baseIMG.rectangle(halfWidth, val1 + val2 + val3/2, halfWidth, val3/2);
        

        baseIMG.setPenColor(frameColor);
        if(hasBarFrame) baseIMG.rectangle(halfWidth, (scale[MAX] + scale[MIN])/2, halfWidth, (scale[MAX] - scale[MIN])/2);

        //return baseIMG.getSubImage(x, y, width, height);
        return baseIMG.getBuffImg();
    }
    @Override
    public void loadConfig(String filename) {
        
    }
    @Override
    public void loadConfig() {
        loadConfig("BarBasicSkin.json");
    }
    public static void main(String[] args) {
        BarBasicSkin b = new BarBasicSkin(new int[]{100,1000} ,new double[]{0,10000});
        SigDraw s = new SigDraw( b.getBarchart(0, 800, 6000) ,true);
        s.save("test1.jpg");
        new SigDraw( s.getSubImage(50, 500, 20, 200) ).save("test2.jpg");
    }
}