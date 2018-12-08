package h2g;

import javax.sound.sampled.ReverbType;

public class ThreadManager {
    
    public static void main(String[] args) {
        CanvaStyle c = new CanvaStyle();
        HistogramData d = new HistogramData();
        double[][] rawData = new double[4][];
        rawData[0] = new double[]{4,5,9,16,17,17};
        rawData[1] = new double[]{3,6,8,15,17,17};
        rawData[2] = new double[]{2,8,11,14,17,17};
        rawData[3] = new double[]{1,7,12,13,17,17};
        d.yValue[0] = 0;
        d.yValue[1] = 20.0;
        d.visiblePattern = 1;
        c.FPD = 90;
        BarDrawingTutor initB = new BarDrawingTutor(c,rawData,0.2); // For initialization
        FrameCreator[] f = new FrameCreator[initB.getTotalFrame()];
        for(int x=0;x<initB.getTotalFrame();++x) {
            BarDrawingTutor b = new BarDrawingTutor(x);
            d.yValue[1] = b.getMaxValue()*1.01;
            RulerDrawingTutor r = new RulerDrawingTutor(d.yValue, 10);
            d.rulerGrade = r.getRulerGrade();
            d.rulerStep = r.getRulerStep();
            f[x] = new FrameCreator(b, c, d);
            System.out.println("Frame"+x+" has been created!");
        }
        for(int x=0;x<initB.getTotalFrame();++x) {
            //f[x].bg.save(x+".jpg");
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
        if((yValue[0]-yValue[1])/(rulerStep)<=maxRulerGrade) return;
        nFactor = 2;
        if((yValue[0]-yValue[1])/(2*rulerStep)<=maxRulerGrade) return;
        nFactor = 5;
        if((yValue[0]-yValue[1])/(5*rulerStep)<=maxRulerGrade) return;
            
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