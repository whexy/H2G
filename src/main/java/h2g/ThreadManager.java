package h2g;

import java.awt.image.BufferedImage;

public class ThreadManager {
    
    public static void main(String[] args) {
        CanvaStyle c = new CanvaStyle();
        HistogramData d = new HistogramData();
        double[][] rawData = new double[4][];
        rawData[0] = new double[]{4,5,9,16,17,20,100,430,440,440,440,440,440,440};
        rawData[1] = new double[]{3,6,8,15,17,40,200,440,440,440,440,440,440,440};
        rawData[2] = new double[]{2,8,11,14,17,60,300,460,440,440,440,440,440,440};
        rawData[3] = new double[]{1,7,12,13,17,80,400,450,440,440,440,440,440,440};
        d.yValue[0] = 0;
        d.yValue[1] = 1.0;
        d.visiblePattern = 1;
        c.FPD = 90;
        RulerDrawingTutor r = new RulerDrawingTutor(d.yValue, 10);
        BarDrawingTutor initB = new BarDrawingTutor(c,rawData,0.08); // For initialization
        FrameCreator f;
        long startTime = System.currentTimeMillis();
        BufferedImage[] bf = new BufferedImage[initB.getTotalFrame()];
        for(int x=0;x<initB.getTotalFrame();++x) {
            BarDrawingTutor b = new BarDrawingTutor(x);
            d.yValue[1] = b.getMaxValue()*1.01;
            r.setYmaxValue(d.yValue[1]);
            d.rulerGrade = r.getRulerGrade();
            d.rulerStep = r.getRulerStep();
            f = new FrameCreator(b, c, d);
            //bf[x] = f.bg.getBuffImg();
            long endTime = System.currentTimeMillis();
            System.out.printf("Average FPS: %.3f\n",x/((endTime-startTime)/1000.0));
            //System.out.println("Frame"+x+" has been created!");
        }
        
        
        
        /*
        CanvaStyle c = new CanvaStyle();
        c.loadConfig();
        HistogramData d = new HistogramData();
        d.keys = new String[]{"father", "father", "son"};
        d.values = new double[]{0.0, 0.0, 0.0};
        
        
        d.rulerStep = 100;
        d.rulerGrade = 10;
        d.header = "Nothing left";
        d.footer = "Nothing right";
        FrameCreator fc = new FrameCreator(c, d);
        fc.bg.save("test.jpg");
        */
    }
}
class RulerDrawingTutor {
    double[] yValue;
    double rulerStep;
    int maxRulerGrade;
    double nFactor;  // ={1,2,5}
    public void selectNfactor() {
        nFactor = 1;
        if((yValue[1]-yValue[0])/(rulerStep)<=maxRulerGrade) return;
        nFactor = 2;
        if((yValue[1]-yValue[0])/(2*rulerStep)<=maxRulerGrade) return;
        nFactor = 5;
        if((yValue[1]-yValue[0])/(5*rulerStep)<=maxRulerGrade) return;
            
        rulerStep*=10;
        selectNfactor();
    }
    public RulerDrawingTutor(double[] yValue, int maxRulerGrade) {
        this.maxRulerGrade = maxRulerGrade;
        this.yValue = yValue.clone();
        rulerStep = (yValue[1]-yValue[0])/maxRulerGrade;
        rulerStep = Math.pow(10, Math.floor( Math.log10(rulerStep)) );
        selectNfactor();
    }
    public void setYmaxValue(double yMaxValue) {
        yValue[1] = yMaxValue;
        selectNfactor();
    }
    public double getRulerStep() {
        return nFactor*rulerStep;
    }
    public int getRulerGrade() {
        return (int)( (yValue[1]-yValue[0])/(nFactor*rulerStep) );
    }
}