package org.ucsdcssa.capes.service.impl;

import org.ucsdcssa.capes.pojo.Column;
import org.ucsdcssa.capes.pojo.Table;
import org.ucsdcssa.capes.service.SQLGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component("MySQL")
@Configuration
public class MySQLGenerator implements SQLGenerator {

    @Override
    public String deleteTable(List<Table> tableList) {
        StringBuilder sql = new StringBuilder();
        for (Table table : tableList) {
            String tableName = table.getTableName();
            String primaryKey = "";
            HashMap<String, Column> tableColumn = table.getColumns();
            sql.append("DROP TABLE ");
            sql.append(tableName);
            sql.append(" ;");
        }
        return sql.toString();
    }

    @Override
    public String deleteColumn(List<Column> columnList) {
        StringBuilder sql = new StringBuilder();
        for (Column column : columnList) {
            sql.append("alter table ");
            sql.append(column.getTableName());
            sql.append(" drop column ");
            sql.append(column.getColumnName());
            sql.append(";");
        }
        return sql.toString();
    }

    @Override
    public String createTable(List<Table> tableList) {
        StringBuilder sql = new StringBuilder();
        for (Table table : tableList) {
            String tableName = table.getTableName();
            String primaryKey = "";
            HashMap<String, Column> tableColumn = table.getColumns();
            sql.append("CREATE TABLE ");
            sql.append(tableName);
            sql.append(" (");
            for (String key : tableColumn.keySet()) {
                sql.append(tableColumn.get(key).getColumnName());
                sql.append(" ");
                sql.append(tableColumn.get(key).getColumnType());
                if (!"".equals(tableColumn.get(key).getExtra()))
                    sql.append(" ");
                sql.append(tableColumn.get(key).getExtra());
                if ("NO".equals(tableColumn.get(key).getIsNullable())) {
                    sql.append(" NOT NULL");
                }
                sql.append(",");
                if ("PRI".equals(tableColumn.get(key).getColumnKey()))
                    primaryKey = key;
            }
            if (primaryKey.length() > 0) {
                sql.append("PRIMARY KEY ( ");
                sql.append(primaryKey);
                sql.append(" )");
            } else {
                sql.deleteCharAt(sql.length() - 1);
            }
            sql.append(")ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        }
        return sql.toString();
    }

    @Override
    public String addColumn(List<Column> columnList) {
        StringBuilder sql = new StringBuilder();
        for (Column column : columnList) {
            sql.append("alter table ");
            sql.append(column.getTableName());
            sql.append(" ADD ");
            sql.append(column.getColumnName());
            sql.append(" ");
            sql.append(column.getColumnType());
            sql.append(" ");
            if ("NO".equals(column.getIsNullable())) {
                sql.append("NOT NULL");
            }
            if (column.getExtra().length() > 0) {
                sql.append(" ");
                sql.append(column.getExtra());
            }

            if ("PRI".equals(column.getColumnKey())) {
                sql.append(" ,add primary key (");
                sql.append(column.getColumnName());
                sql.append(")");
            }
            sql.append(";");
        }
        return sql.toString();
    }

    @Override
    public String modifyColumn(List<Column> columnList) {
        StringBuilder sql = new StringBuilder();
        for (Column column : columnList) {
            sql.append("alter table ");
            sql.append(column.getTableName());
            sql.append(" modify column ");
            sql.append(column.getColumnName());
            sql.append(" ");
            sql.append(column.getColumnType());
            sql.append(" ");
            if ("NO".equals(column.getIsNullable())) {
                sql.append("NOT NULL");
            }
            if (column.getExtra().length() > 0) {
                sql.append(" ");
                sql.append(column.getExtra());
            }

            if ("PRI".equals(column.getColumnKey())) {
                sql.append(" ,add primary key (");
                sql.append(column.getColumnName());
                sql.append(")");
            }
            sql.append(";");
        }
        return sql.toString();
    }
}
