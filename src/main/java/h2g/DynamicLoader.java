package h2g;

public class DynamicLoader {
    //TODO: MODIFY THE FUNCTION AND DIFFERENT STYLES
    private static BarBasicSkinStyle BarFlatUI = new BarBasicSkinStyle();

    static {
        try {
            BarFlatUI.loadConfig("BarFlatUI.json");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static BarBasicSkinStyle FlatUI0 = new BarBasicSkinStyle();

    static {
        try {
            FlatUI0.loadConfig("FlatUI0.json");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static BarBasicSkinStyle FlatUI1 = new BarBasicSkinStyle();

    static {
        try {
            FlatUI1.loadConfig("FlatUI1.json");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static BarBasicSkinStyle FlatUI = new BarBasicSkinStyle();

    public static void FlatUILoad(String pattern) {
        try {
            FlatUI.loadConfig(pattern + ".json");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static BarGenerator get(String skinName, int[] barSize, double[] scale, boolean rotated) {
        if ("Basic".equals(skinName)) return new BarBasicSkin(barSize, scale, rotated);
        //if ("BarFlatUI".equals(skinName)) return new BarBasicSkin(BarFlatUI, barSize, scale, rotated);
        //if ("FlatUI1".equals(skinName)) return new BarBasicSkin(FlatUI1, barSize, scale, rotated);
        if (skinName.contains("FlatUI")) {
            FlatUILoad(skinName);
            return new BarBasicSkin(FlatUI, barSize, scale, rotated);
        }
        return null;
    }
}
