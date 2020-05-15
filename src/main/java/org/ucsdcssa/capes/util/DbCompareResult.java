package org.ucsdcssa.capes.util;

import java.util.List;
import java.util.Map;

public class DbCompareResult {
    private Map<String, List> result;
    private String SQLPath;

    public Map<String, List> getResult() {
        return result;
    }

    public void setResult(Map<String, List> result) {
        this.result = result;
    }

    public String getSQLPath() {
        return SQLPath;
    }

    public void setSQLPath(String SQLPath) {
        this.SQLPath = SQLPath;
    }
}
