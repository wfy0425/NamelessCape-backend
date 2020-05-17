package org.ucsdcssa.capes.service;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Select;
import org.ucsdcssa.capes.pojo.Column;
import org.ucsdcssa.capes.pojo.Course;
import org.ucsdcssa.capes.pojo.Table;
import org.ucsdcssa.capes.pojo.User;
import org.ucsdcssa.capes.util.DbCompareResult;

import java.util.List;
import java.util.Map;

public interface DatabaseService {
//    List<Map> listTable();
//    List<Map> listTableColumn(String tableName);
//    Map<String, Table> printStructure();
//    boolean createTable(String generatorName, List<Table> tableList);
//    boolean addColumn(String generatorName, List<Column> columnList);
//    boolean modifyColumn(String generatorName, List<Column> columnList);
//    DbCompareResult mergeDb(String generatorName);

//    User getUser(Long id);

    boolean insertAll(JSONArray response);
    List<Course> getByCourse(String department,String courseCode);
    List<Course> getByCourseExtend(String department,String courseCode,String instructor,
                                   Float maxExpectedGPA, Float minExpectedGPA,
                                   Float maxReceivedGPA, Float minReceivedGPA,
                                   Float maxStudyHrs, Float minStudyHrs);
    List<Course> getByTerm(String term);
    List<Course> getByInstructor(String instructor);
    List<Course> getByExpectedGPA(float max, float min);
    List<Course> getByReceivedGPA(float max, float min);
    List<Course> getByStudyHrs(float max, float min);
}