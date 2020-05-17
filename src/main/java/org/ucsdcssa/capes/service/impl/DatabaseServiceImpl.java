package org.ucsdcssa.capes.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.ucsdcssa.capes.dao.DatabaseDao;
import org.ucsdcssa.capes.dbcomparator.DatabaseComparator;
import org.ucsdcssa.capes.pojo.Column;
import org.ucsdcssa.capes.pojo.Course;
import org.ucsdcssa.capes.pojo.Table;
import org.ucsdcssa.capes.pojo.User;
import org.ucsdcssa.capes.service.DatabaseService;
import org.ucsdcssa.capes.service.SQLGenerator;
import org.ucsdcssa.capes.util.DbCompareResult;
import org.ucsdcssa.capes.util.JsonResult;
import org.ucsdcssa.capes.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    private DatabaseDao databaseDao = null;

//    @Value("${database.JSON.path}")
//    String JSONPath = null;
//    @Value("${database.generatedSQL.path}")
//    String SQLPath = null;


//    @Override
//    public User getUser(Long id) {
//        return databaseDao.getUser(id);
//    }
//
//    @Override
//    public List<Map> listTable() {
//        return databaseDao.listTable();
//    }
//
//    @Override
//    public List<Map> listTableColumn(String tableName) {
//        return databaseDao.listTableColumn(tableName);
//    }
//
//    @Override
//    public Map<String, Table> printStructure() {
//        List<Map> tableMapList = listTable();
////        String[] tableArray = new String[tableMapList.size()];
//        Table table;
//        Column column;
//        String tableName;
//        HashMap<String, Column> columnsMap;
//        Map<String, Table> tablesMap = new HashMap<>();
////        List<Table> tableList = new ArrayList<>();
//        for (Map map : tableMapList) {
////            tableArray[i]= (String) tableMapList.get(i).get("TABLE_NAME");
//            tableName = (String) map.get("TABLE_NAME");
//            List<Map> columnMapList = listTableColumn(tableName);
//            columnsMap = new HashMap<>();
//
//            for (Map map1 : columnMapList) {
//                column = new Column();
//                String columnName = (String) map1.get("COLUMN_NAME");
//                column.setColumnName(columnName);
//                column.setColumnKey((String) map1.get("COLUMN_KEY"));
//                column.setColumnType((String) map1.get("COLUMN_TYPE"));
//                column.setExtra((String) map1.get("EXTRA"));
//                column.setIsNullable((String) map1.get("IS_NULLABLE"));
//                column.setTableName((String) map1.get("TABLE_NAME"));
//                columnsMap.put(columnName, column);
//            }
//            table = new Table();
//            table.setColumns(columnsMap);
//            table.setTableName(tableName);
//            tablesMap.put(tableName, table);
////            tableList.add(table);
//
//        }
//        try {
//            JSONObject json = new JSONObject();
//            json.put("db", tablesMap);
//            File file = new File("./src/main/resources/static/data/oldDb.json");
//            // if file doesnt exists, then create it
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            FileWriter fw = new FileWriter(file.getAbsoluteFile());
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(JSON.toJSONString(tablesMap));
//            bw.close();
//
//            System.out.println("Done");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return tablesMap;
//    }
//
//    @Override
//    public boolean createTable(String generatorName, List<Table> tableList) {
//        if (tableList.size()==0){
//            return false;
//        }
//        SQLGenerator sqlGenerator = (SQLGenerator)SpringUtil.getBean(generatorName);
//        return databaseDao.operateBySQL(sqlGenerator.createTable(tableList));
//    }
//
//    @Override
//    public boolean addColumn(String generatorName, List<Column> columnList) {
//        if (columnList.size()==0){
//            return false;
//        }
//        SQLGenerator sqlGenerator = (SQLGenerator)SpringUtil.getBean(generatorName);
//        return databaseDao.operateBySQL(sqlGenerator.addColumn(columnList));
//    }
//
//    @Override
//    public boolean modifyColumn(String generatorName, List<Column> columnList) {
//        if (columnList.size()==0){
//            return false;
//        }
//        SQLGenerator sqlGenerator = (SQLGenerator)SpringUtil.getBean(generatorName);
//        return databaseDao.operateBySQL(sqlGenerator.modifyColumn(columnList));
//    }

//    @Override
//    public DbCompareResult mergeDb(String generatorName) {
//        SQLGenerator sqlGenerator = (SQLGenerator)SpringUtil.getBean(generatorName);
//        BufferedReader read = null;
//        StringBuilder sb = new StringBuilder();
//        try {
//            read = new BufferedReader(new FileReader(JSONPath));
//            String s;
//
//            while((s=read.readLine()) != null){
//                sb.append(s);
//            }
//            read.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String inputDbJson = sb.toString();
//        inputDbJson = inputDbJson.replaceAll("\\r\\n", "\\n").replaceAll("\\s*", "");
//        Map<String,Table> userListInput = JSONArray.parseObject(inputDbJson, new TypeReference<HashMap<String,Table>>() {});
//        JsonResult jr = new JsonResult();
//        Map<String,Table> userListDb = printStructure();
//        DatabaseComparator databaseComparator = new DatabaseComparator();
//        databaseComparator.compareTables(userListDb,userListInput);
//
//
//        List<Table> newTableList= databaseComparator.getNewTableList();
//        List<Column> newColumnList = databaseComparator.getNewColumnList();
//        List<Column> updateColumnList = databaseComparator.getUpdateColumnList();
//        List<Table> deleteTableList = databaseComparator.getDeleteTableList();
//        List<Column> deleteColumnList = databaseComparator.getDeleteColumnList();
//        if(databaseComparator.getCompareResult(SQLPath)!=null) {
//            Scanner scanner = new Scanner(System.in);
//            try {
//                File file = new File(SQLPath);
//                // if file doesnt exists, then create it
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//                System.out.println(sqlGenerator.createTable(newTableList));
//                System.out.println(sqlGenerator.addColumn(newColumnList));
//                System.out.println(sqlGenerator.modifyColumn(updateColumnList));
//
//                FileWriter fw = new FileWriter(file.getAbsoluteFile());
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write(sqlGenerator.createTable(newTableList));
//                bw.write("\r\n");
//                bw.write(sqlGenerator.addColumn(newColumnList));
//                bw.write("\r\n");
//                bw.write(sqlGenerator.modifyColumn(updateColumnList));
//                bw.write("\r\n");
//                bw.write(sqlGenerator.deleteTable(deleteTableList));
//                bw.write("\r\n");
//                bw.write(sqlGenerator.deleteColumn(deleteColumnList));
//                bw.write("\r\n");
//                bw.close();
//
//                System.out.println("sql文件写入完成");
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
////            System.out.println("确认？ y/N");
////            String input = scanner.nextLine();
////            if ("y".equals(input) || "Y".equals(input)) {
////                this.createTable(generatorName, newTableList);
////                this.addColumn(generatorName, newColumnList);
////                this.modifyColumn(generatorName, updateColumnList);
////                System.out.println("修改完成");
////            } else {
////                System.out.println("操作取消");
////            }
//            return databaseComparator.getCompareResult(SQLPath);
//        }else{
//            return null;
//        }
//    }

    @Override
    public boolean insertAll(JSONArray response) {
        String jsonStr = JSONArray.toJSONString(response);

        List<Course> courseList = JSONArray.parseObject(jsonStr, new TypeReference<List<Course>>() {});
        for (Course course:courseList) {
            String[] courseName = course.getCourse().split("-");
            String[] courseCode = courseName[0].split(" ");
            course.setDepartment(courseCode[0]);
            course.setCourseCode(courseCode[1]);
        }
        return databaseDao.insert(courseList);
    }

    @Override
    public List<Course> getByCourse(String department, String courseCode) {
        return databaseDao.getByCourse(department,courseCode);
    }

    @Override
    public List<Course> getByTerm(String term) {
        return databaseDao.getByTerm(term);
    }

    @Override
    public List<Course> getByInstructor(String instructor) {
        return databaseDao.getByInstructor(instructor);
    }

    @Override
    public List<Course> getByExpectedGPA(float max, float min) {
        return databaseDao.getByExpectedGPA(max,min);
    }

    @Override
    public List<Course> getByReceivedGPA(float max, float min) {
        return databaseDao.getByReceivedGPA(max,min);
    }

    @Override
    public List<Course> getByStudyHrs(float max, float min) {
        return databaseDao.getByStudyHrs(max,min);
    }
}
