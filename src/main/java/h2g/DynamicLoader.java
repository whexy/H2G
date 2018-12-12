package h2g;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class DynamicLoader 
{
    public static HashMap<String,BarGenerator> buffer = new HashMap<>();
    public static BarGenerator curGenerator;
    public static void set(String skinName, int[] barSize, double[] scale) {
        String descriptor = skinName + "," + barSize[0] + "," + barSize[1];
        if(buffer.containsKey(descriptor)) {
            curGenerator = buffer.get(descriptor);
            curGenerator.setScale(scale);
        }
        else {
<<<<<<< HEAD
            if(skinName.equals("Basic")) curGenerator = new BarBasicSkin(barSize, scale);
            buffer.put(descriptor, curGenerator);
=======
            if(skinName.equals("Basic")) rel = new BarBasicSkin(barSize, scale, false);
            buffer.put(descriptor, rel);
            return rel;
>>>>>>> 83f4e1d2a6b5e7bcb3454f7d733fce23a7e696fb
        }
    }
    public static BufferedImage get
}
