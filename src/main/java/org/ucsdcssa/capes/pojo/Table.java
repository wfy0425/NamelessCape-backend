package org.ucsdcssa.capes.pojo;

import java.util.HashMap;

public class Table {
    private String tableName="";
    private HashMap<String, Column> columns = new HashMap<>();

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public Table() {

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public HashMap<String,Column> getColumns() {
        return columns;
    }

    public void setColumns(HashMap<String,Column> columns) {
        this.columns = columns;
    }
}
