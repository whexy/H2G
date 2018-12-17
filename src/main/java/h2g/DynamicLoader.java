package h2g;

import java.util.HashMap;

public class DynamicLoader {
    //TODO: MODIFY THE FUNCTION AND DIFFERENT STYLES
    private static HashMap<String,BarBasicSkinStyle> pool = new HashMap<>();
    private static String[] resourceInPool = new String[]{
        "FlatUI0","FlatUI1","FlatUI2","FlatUI3","FlatUI4",
        "FlatUI5","FlatUI6","FlatUI7","FlatUI8","FlatUI9",
        "FlatUI10","FlatUI11","FlatUI12","FlatUI13","FlatUI14",
        "FlatUI15","FlatUI16","FlatUI17","FlatUI18","FlatUI19",
        "BarFlatUI"
    };
    static {
        BarBasicSkinStyle configure;
        for(String name:resourceInPool) {
            try {
                configure = new BarBasicSkinStyle();
                configure.loadConfig(name+".json");
                pool.put(name, configure);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static BarGenerator get(String skinName, int[] barSize, double[] scale, boolean rotated) {
        if (pool.containsKey(skinName)) {
            return new BarBasicSkin(pool.get(skinName), barSize, scale, rotated);
        }
        if ("Basic".equals(skinName)) return new BarBasicSkin(barSize, scale, rotated);
        return null;
    }
}
