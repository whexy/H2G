package h2g;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataLoader {

    private double[][] rawData;
    String pattern = "HistogramData.json";

    public DataLoader(String pattern) {
        this.pattern = pattern;
    }

    public DataLoader(){

    }

    double[][] loadConfig() throws Exception {
        ConfigLoader loader = new ConfigLoader(pattern);
        ArrayList<double[]> _Data = new ArrayList<>();
        for (int x = 1; ; ++x) {
            double[] _row = loader.getDoubleArray("data" + x);
            if (_row == null) break;
            _Data.add(_row);
        }
        double[][] DATA = new double[_Data.size()][];
        for (int i = 0; i < _Data.size(); i++) {
            DATA[i] = _Data.get(i);
        }
        return DATA;
    }
}
