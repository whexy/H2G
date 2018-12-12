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
            if(skinName.equals("Basic")) curGenerator = new BarBasicSkin(barSize, scale);
            buffer.put(descriptor, curGenerator);
        }
    }
    public static BufferedImage get
}
