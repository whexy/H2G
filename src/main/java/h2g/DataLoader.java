package h2g;

import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {
    private String pattern = "Data.json";
    private ConfigLoader loader;

    public DataLoader(String pattern) throws IOException {
        this.pattern = pattern;
        loader = new ConfigLoader(pattern);
    }

    public DataLoader() throws IOException {
        loader = new ConfigLoader(pattern);
    }

    double[][] loadConfig() throws Exception {
        ArrayList<double[]> _Data = new ArrayList<>();
        for (int x = 0; ; ++x) {
            double[] _row = loader.getDoubleArray("bar." + x + ".data");
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
