package org.ucsdcssa.capes.dbcomparator;

import org.ucsdcssa.capes.pojo.Column;
import org.ucsdcssa.capes.pojo.Table;
import org.ucsdcssa.capes.util.DbCompareResult;

import java.util.*;

public class DatabaseComparator {
    private static StringBuffer[] sb = {new StringBuffer(), new StringBuffer(),
            new StringBuffer(), new StringBuffer(), new StringBuffer(),
            new StringBuffer(), new StringBuffer()};


    private List<Table> newTableList = new ArrayList<>();
    private List<Table> deleteTableList = new ArrayList<>();
    private List<Column> newColumnList = new ArrayList<>();
    private List<Column> deleteColumnList = new ArrayList<>();
    private List<Column> updateColumnList = new ArrayList<>();
    private List<Column> conflictAutoIncreaseColumnList = new ArrayList<>();
    private List<Column> conflictFieldColumnList = new ArrayList<>();

    private boolean hasAutoIncrease = false;
//    /**
//     * 根据标示位，追加到满足条件的StringBuffer
//     */
//    public void append(Table inputTable, Column inputColumn, Table targetTable, Column targetColumn, int flag) {
//        switch (flag) {
//            case 1:
//                System.out.println("1、新文件存在，源文件不存在的表：" + inputTable.getTableName());// 跳过
//                sb[0].append(inputTable.getTableName() + "\n");
//                break;
//            case 2:
//                System.out.println("2、新文件不存在，源文件存在的表：" + inputTable.getTableName());// 需要人工判断脚本
//                sb[1].append(inputTable.getTableName() + "\n");
//
//                break;
//            case 3:
//                System.out.println("3、新文件存在，源文件不存在的字段：" + inputTable.getTableName()
//                        + " | " + inputColumn.getColumnName());// 需人工判断如何处理
//                sb[2].append(inputTable.getTableName() + " | " + inputColumn.getColumnName()
//                        + "\n");
//
//                break;
//            case 4:
//                System.out.println("4、新文件不存在，源文件存在的字段：" + inputTable.getTableName()
//                        + " | " + inputColumn.getColumnName());// 需要人工判断脚本
//                sb[3].append(inputTable.getTableName() + " | " + inputColumn.getColumnName()
//                        + "\n");
//
//                break;
//            case 5:
//                System.out.println("5、表和字段都相同，但字段类型长度不同的内容：" + inputTable.getTableName()
//                        + " | " + inputColumn.getColumnName() + " | "
//                        + inputColumn.getColumnType() + " | " + targetTable.getTableName()
//                        + " | " + targetColumn.getColumnName() + " | "
//                        + targetColumn.getColumnType());// 需要人工判断脚本
//                sb[4].append(inputTable.getTableName()
//                        + " | " + inputColumn.getColumnName() + " | "
//                        + inputColumn.getColumnType() + targetTable.getTableName()
//                        + " | " + targetColumn.getColumnName() + " | "
//                        + targetColumn.getColumnType() + "\n");
//                break;
//            case 6:
//                System.out.println("6、表和字段、字段类型都相同，但字段PRI不同的内容："
//                        + inputTable.getTableName() + " | " + inputColumn.getColumnName()
//                        + " | " + inputColumn.getColumnKey() + " | " + targetTable.getTableName() + " | " + targetColumn.getColumnName()
//                        + " | " + targetColumn.getColumnKey());// 需要人工判断脚本
//                sb[5].append(inputTable.getTableName() + " | " + inputColumn.getColumnName()
//                        + " | " + inputColumn.getColumnKey() + targetTable.getTableName() + " | " + targetColumn.getColumnName()
//                        + " | " + targetColumn.getColumnKey() + "\n");
//                break;
//            case 7:
//                System.out.println("7、表和字段、字段类型都相同，但字段extra不同的内容："
//                        + inputTable.getTableName() + " | " + inputColumn.getColumnName()
//                        + " | " + inputColumn.getColumnKey() + " | " + targetTable.getTableName() + " | " + targetColumn.getColumnName()
//                        + " | " + targetColumn.getColumnKey());// 需要人工判断脚本
//                sb[6].append(inputTable.getTableName() + " | " + inputColumn.getColumnName()
//                        + " | " + inputColumn.getColumnKey() + targetTable.getTableName() + " | " + targetColumn.getColumnName()
//                        + " | " + targetColumn.getColumnKey() + "\n");
//                break;
//        }
//    }

    /**
     * 比较新文件数据库和源文件数据库的数据表，包括表名、字段名、字段类型、字段长度
     */

    public void compareTables(Map<String, Table> targetMap, Map<String, Table> inputMap) {
        // 遍历源文件库Map
        for (Iterator<String> mapIter = inputMap.keySet().iterator(); mapIter.hasNext(); ) {
            String tableKey = mapIter.next();
            Table inputTable = inputMap.get(tableKey);// 获得源文件库中的表
            Table targetTable = targetMap.get(tableKey);// 尝试从新文件库中获得同名表
            if (targetTable == null) { // 如果获得表为空，说明源文件存在，新文件不存在
//                append(inputTable, null, null, null, 2);
                newTableList.add(inputTable);
            } else { // 表相同，判断字段、字段类型、字段长度
                for (Iterator<String> columnIter = inputTable.getColumns().keySet().iterator(); columnIter.hasNext(); ) {
                    String columnKey = columnIter.next();
                    Column inputColumn = inputTable.getColumns().get(columnKey);// 获得源文件库中的列
                    Column targetColumn = targetTable.getColumns().get(columnKey);// 尝试从新文件库中获得同名列
                    if ("auto_increment".equals(inputColumn.getExtra())){
                        hasAutoIncrease = true;
                    }
                    if (targetColumn == null) {// 如果列名为空，说明源文件存在，新文件不存在
//                        append(inputTable, inputColumn, targetTable, null, 4);
                        newColumnList.add(inputColumn);
                    } else {// 说明两者都存在
                        boolean needChange = false;
                        String columnTypeTemp="";
                        String columnKeyTemp="";
                        String extraTemp="";
                        String isNullableTemp="";
                        if (!inputColumn.getColumnType().equals(targetColumn.getColumnType())) {// 字段类型长度不一致
//                            append(inputTable, inputColumn, targetTable, targetColumn, 5);
                            String[] inputColumnType = inputColumn.getColumnType().split("\\(");//分离字段类型和长度
                            String[] targetColumnType = targetColumn.getColumnType().split("\\(");
                            StringBuilder sb = new StringBuilder();
                            if(inputColumnType[0].equals(targetColumnType[0])){//字段类型一致，长度不一致
                                int inputColumnLength =Integer.parseInt(inputColumnType[0].substring(0,inputColumnType[0].length() - 1));//得到字段长度
                                int targetColumnLength =Integer.parseInt(targetColumnType[0].substring(0,targetColumnType[0].length() - 1));
                                if(inputColumnLength>targetColumnLength){//字段长度不一致
                                    sb.append(inputColumnType[0]).append("(").append(inputColumnLength).append(")");
                                }else{
                                    sb.append(inputColumnType[0]).append("(").append(targetColumnLength).append(")");
                                }

                                columnTypeTemp = sb.toString();
                                needChange=true;
                            }
                            else{//字段类型不一致
                                conflictFieldColumnList.add(inputColumn);
                            }
                        }
                        if (!inputColumn.getColumnKey().equals(targetColumn.getColumnKey())) {// 字段PRI不一致
//                            append(inputTable, inputColumn, targetTable, targetColumn, 6);

                            columnKeyTemp = "PRI";
                            needChange=true;
                        }
                        if (!inputColumn.getExtra().equals(targetColumn.getExtra())) {// 字段Extra不一致
//                            append(inputTable, inputColumn, targetTable, targetColumn, 7);
                            if (hasAutoIncrease){
                                conflictAutoIncreaseColumnList.add(inputColumn);
                            }else {
                                extraTemp = "auto_increment";//TODO there can only one auto increase column
                                needChange = true;
                            }
                        }
                        if (!inputColumn.getIsNullable().equals(targetColumn.getIsNullable())) {// 字段isNullable不一致
//                            append(inputTable, inputColumn, targetTable, targetColumn, 7);

                            isNullableTemp = "YES";
                            needChange=true;
                        }
                        if(needChange){
                            Column column = new Column();
                            column.setColumnType(columnTypeTemp);
                            column.setExtra(extraTemp);
                            column.setIsNullable(isNullableTemp);
                            column.setColumnKey(columnKeyTemp);
                            column.setTableName(targetTable.getTableName());
                            column.setColumnName(targetColumn.getColumnName());
                            updateColumnList.add(column);
                        }

                    }
                }
            }
        }

        // 遍历新文件库Map
        for (Iterator<String> tableIter = targetMap.keySet().iterator(); tableIter
                .hasNext(); ) {
            String tableKey = tableIter.next();
            Table targetTable = targetMap.get(tableKey);// 尝试从新文件库中获得同名表
            Table inputTable = inputMap.get(tableKey);// 获得源文件库中的表
            if (inputTable == null) { // 如果获得表为空，说明源文件存在，新文件不存在
                deleteTableList.add(targetTable);
//                append(targetTable, null, null, null, 1);
            } else { // 表相同，判断字段、字段类型、字段长度
                for (Iterator<String> iter_column = targetTable.getColumns()
                        .keySet().iterator(); iter_column.hasNext(); ) {
                    String key_column = iter_column.next();
                    Column targetColumn = targetTable.getColumns().get(key_column);// 获得新文件库中的列
                    Column inputColumn = inputTable.getColumns().get(key_column);// 尝试从源文件库中获得同名列
                    if (inputColumn == null) {// 如果列名为空，说明新文件存在，源文件不存在
//                        append(targetTable, targetColumn, inputTable, inputColumn, 3);
                        deleteColumnList.add(targetColumn);
                    }
                }
            }
        }
    }

    /**
     * 将StringBuffer中的值写入文件中
     */
    public String[] getResult() {

        String[] strings = new String[7];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = sb[i].toString();
        }
        return strings;
    }

    public DbCompareResult getCompareResult(String SQLPath){
        if(newTableList.size()>0||deleteTableList.size()>0||newColumnList.size()>0
                ||deleteColumnList.size()>0||updateColumnList.size()>0||conflictAutoIncreaseColumnList.size()>0||
                conflictFieldColumnList.size()>0)
        {

        Map<String,List> result = new HashMap<>();
        DbCompareResult dbCompareResult = new DbCompareResult();
        result.put("newTableList",newTableList);
        result.put("deleteTableList",deleteTableList);
        result.put("newColumnList",newColumnList);
        result.put("deleteColumnList",deleteColumnList);
        result.put("updateColumnList",updateColumnList);
        result.put("conflictAutoIncreaseColumnList",conflictAutoIncreaseColumnList);
        result.put("conflictFieldColumnList",conflictFieldColumnList);
        dbCompareResult.setResult(result);
        dbCompareResult.setSQLPath(SQLPath);
        return dbCompareResult;
        }
        else {
            return null;
        }
    }
    public List<Table> getNewTableList() {
        return newTableList;
    }

    public List<Column> getNewColumnList() {
        return newColumnList;
    }

    public List<Column> getUpdateColumnList() {
        return updateColumnList;
    }

    public List<Table> getDeleteTableList() {
        return deleteTableList;
    }

    public List<Column> getDeleteColumnList() {
        return deleteColumnList;
    }

    public List<Column> getConflictAutoIncreaseColumnList() {
        return conflictAutoIncreaseColumnList;
    }

    public List<Column> getConflictFieldColumnList() {
        return conflictFieldColumnList;
    }
}
