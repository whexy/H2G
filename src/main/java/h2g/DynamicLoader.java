package h2g;

import java.util.HashMap;

public class DynamicLoader 
{
    public static HashMap<String,BarGenerator> buffer = new HashMap<>();
    public static BarGenerator getGenerator(String skinName, int[] barSize, double[] scale) {
        String descriptor = skinName + "," + barSize[0] + "," + barSize[1];
        BarGenerator rel = null;
        if(buffer.containsKey(descriptor)) {
            System.out.println("Share:"+descriptor);
            rel = buffer.get(descriptor);
            rel.setScale(scale.clone());
            return rel;
        }
        else {
            System.out.println("Create:"+descriptor);
            if(skinName.equals("Basic")) rel = new BarBasicSkin(barSize, scale);
            buffer.put(descriptor, rel);
            return rel;
        }
    }
}
