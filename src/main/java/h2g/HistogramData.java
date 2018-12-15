package h2g;

public class HistogramData {
    String header = "2013-2018 FORBES CHINA RICHEST TOP 10";
    String footer = "";
    double minValue = 0.0;
    String[] keys = {"Ma Yun", "Ma Huateng", "Xu Jiayin", "Wang Jianlin", "He Xiangjian", "Yang Huiyan", "Wang Wei", "Li Yanhong", "Li Shufu", "Ding Lei", "Lei Jun", "Huang Zheng", "Wang Wenying", "Zhang Zhidong", "Zong Qinhou", "Li Hejun", "Wei Jianjun", "Liu Yongxing"};
    
    // Blank size will be 1.0-sum(ratio)
    double[] values = {};
    //double[] xScale = { 0, 1.0 }; // MIN, MAX
    int visiblePattern = 17;
    double[] yValue = {-0.00001, 1.0}; // MIN, MAX
    double rulerStep = 0.0;
    int rulerGrade = 0;
}