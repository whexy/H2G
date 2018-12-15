package h2g;

import java.io.IOException;

public class HistogramData {
    String header = "";
    String footer = "";
    double minValue = 0.0;
    String[] keys = {"A1","A2","B1","B2"};
    
    // Blank size will be 1.0-sum(ratio)
    double[] values = {};
    //double[] xScale = { 0, 1.0 }; // MIN, MAX
    int visiblePattern = 1;
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