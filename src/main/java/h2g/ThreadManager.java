package h2g;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.lang.Thread;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics2D;

public class ThreadManager {
    public static CanvaStyle canvaStyle;
    public static HistogramData histogramData;
    public static RulerDrawingTutor rulerDrawingTutor;
    public static BarDrawingHelper barDrawingHelper;
    public static FrameCreator frameCreator;
    public static BarDrawingTutor barDrawingTutor;
    public static Timer timer = null;
    public static ConcurrentLinkedQueue<BufferedImage> buffer = new ConcurrentLinkedQueue<>();

    public static BarDrawingTutor bDTbuffer[];
    public static void bufferBarDrawingTutor() {
        bDTbuffer = new BarDrawingTutor[barDrawingHelper.getTotalFrame()];
        for(int x=0;x<barDrawingHelper.getTotalFrame();++x) {
            if(canvaStyle.isStackedBar && barDrawingHelper instanceof StackedBarDrawingHelper) {
                bDTbuffer[x] = ((StackedBarDrawingHelper) barDrawingHelper).getStackedTutor(x);
            }
            else bDTbuffer[x] = barDrawingHelper.getTutor(x);
        }
    }
    public static BarDrawingTutor fetchBarDrawingTutor(int currentFrame) {
        BarDrawingTutor rel = bDTbuffer[currentFrame];
        bDTbuffer[currentFrame] = null;
        return rel;
    }

    public static void refreshRuler(double maxValue) {
        maxValue += Math.abs(maxValue*canvaStyle.expandRatio);
        histogramData.yValue[1] = maxValue;
        rulerDrawingTutor.setYmaxValue(histogramData.yValue[1]);
        histogramData.rulerGrade = rulerDrawingTutor.getRulerGrade();
        histogramData.rulerStep = rulerDrawingTutor.getRulerStep();
    }
    public static void main(String[] args) {
        double[][] rawData = new double[4][];
        rawData[0] = new double[]{4,5,9,21,30,70,110,400,400,800,2000,9000};
        rawData[1] = new double[]{3,6,10,23,50,100,200,300,400,1000,3000,8000};
        rawData[2] = new double[]{2,7,11,22,40,90,300,350,500,1200,4000,7000};
        rawData[3] = new double[]{1,8,12,24,60,80,100,120,600,700,5000,6000};

        /*
        rawData[0] = new double[]{1,5,10};
        rawData[1] = new double[]{2,6,20};
        rawData[2] = new double[]{3,7,30};
        rawData[3] = new double[]{4,8,40};*/

        canvaStyle = new CanvaStyle();
        histogramData = new HistogramData();
        rulerDrawingTutor = new RulerDrawingTutor(canvaStyle, histogramData);

        if(canvaStyle.isStackedBar) barDrawingHelper = new StackedBarDrawingHelper(canvaStyle, histogramData, rawData);
        else barDrawingHelper = new BarDrawingHelper(canvaStyle, rawData);

        long endTime, startTime = System.currentTimeMillis();
        double averageFPS = 0;
        bufferBarDrawingTutor();

        if(!canvaStyle.enableDynamicRuler) {
            refreshRuler( bDTbuffer[bDTbuffer.length-1].getMaxValue() );
        }

        for(int currentFrame=0;currentFrame<barDrawingHelper.getTotalFrame();++currentFrame) {
            if(currentFrame+1>=canvaStyle.FPD && timer==null) {
                timer = new Timer();
                timer.schedule(new ImagePlayer(buffer, canvaStyle.bgSize), 0, 1000/canvaStyle.FPS);
            }
            barDrawingTutor = fetchBarDrawingTutor(currentFrame);
            if(canvaStyle.enableDynamicRuler) refreshRuler(barDrawingTutor.getMaxValue());
            frameCreator = new FrameCreator(barDrawingTutor, canvaStyle, histogramData);
            //f.bg.save(x+".jpg");
            //bf[x] = f.bg.getBuffImg();
            buffer.offer( frameCreator.bg.getBuffImg() );
             
            if(currentFrame%canvaStyle.FPD==0) {
                endTime = System.currentTimeMillis();
                averageFPS = canvaStyle.FPD/((endTime-startTime)/1000.0);
                System.out.printf("Frame:%d Average FPS: %.3f\n",currentFrame,averageFPS);
                startTime = System.currentTimeMillis();
            }
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
class ImagePlayer extends TimerTask {
    private static BufferedImage onscreenImage;
    private static Graphics2D onscreen;
    private static JFrame frame;
    private static ConcurrentLinkedQueue<BufferedImage> buffer;
    public ImagePlayer(ConcurrentLinkedQueue<BufferedImage> buffer, int[] bgSize) {
        ImagePlayer.buffer = buffer;

        frame = new JFrame();
        onscreenImage  = new BufferedImage(bgSize[0], bgSize[1], BufferedImage.TYPE_INT_ARGB);
        onscreen  = onscreenImage.createGraphics();

        ImageIcon icon = new ImageIcon(onscreenImage);
        JLabel draw = new JLabel(icon);

        draw.addMouseListener(null);
        draw.addMouseMotionListener(null);
        frame.addKeyListener(null);    // JLabel cannot get keyboard focus

        frame.setContentPane(draw);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // closes all windows
        // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      // closes only current window
        frame.setTitle("Standard Draw");
        frame.pack();
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }

    @Override
    public void run() {
        if(buffer.isEmpty()) {
            // onscreen.dispose();
            // this.cancel();
            return;
        }
        show(buffer.poll());
    }
    public static void show(BufferedImage offscreenImage) {
        onscreen.drawImage(offscreenImage, 0, 0, null);
        frame.repaint();
    }
}