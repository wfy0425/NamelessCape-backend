package org.ucsdcssa.capes.pojo;

public class Column {
    private String columnName="";
    private String columnType="";
    private String columnKey="";
    private String extra="";
    private String isNullable="";
    private String tableName="";


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }



    public Column() {

    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Column(String columnName, String columnType, String columnKey, String extra) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnKey = columnKey;
        this.extra=extra;
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getColumnType() {
        return columnType;
    }
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    public String getColumnKey() {
        return columnKey;
    }
    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }
}
