package h2g;

import java.io.IOException;

public class HistogramData {
    String header = "2013-2018 FORBES CHINA RICHEST TOP 10";
    String footer = "";
    String[] keys = {"Ma Yun", "Ma Huateng", "Xu Jiayin", "Wang Jianlin", "He Xiangjian", "Yang Huiyan", "Wang Wei", "Li Yanhong", "Li Shufu", "Ding Lei", "Lei Jun", "Huang Zheng", "Wang Wenying", "Zhang Zhidong", "Zong Qinhou", "Li Hejun", "Wei Jianjun", "Liu Yongxing"};
    
    // Blank size will be 1.0-sum(ratio)
    //double[] xScale = { 0, 1.0 }; // MIN, MAX
    int visiblePattern = 17;
    double[] yValue = {-0.00001, 1.0}; // MIN, MAX
    double rulerStep = 0.0;
    int rulerGrade = 0;

    public void loadConfig(String pattern) throws Exception {
        ConfigLoader loader = new ConfigLoader(pattern);
        header = loader.getStr("header");
        footer = loader.getStr("footer");
        minValue = loader.getDouble("minValue");
        keys = loader.getStringArray("keys");
        values = loader.getDoubleArray("values");
        visiblePattern = loader.getInt("visiblePattern");
        yValue = loader.getDoubleArray("yValue");
        rulerStep = loader.getDouble("rulerStep");
        rulerGrade = loader.getInt("rulerGrade");
    }

    public void loadConfig() throws Exception {
        loadConfig("Data.json");
    }
}