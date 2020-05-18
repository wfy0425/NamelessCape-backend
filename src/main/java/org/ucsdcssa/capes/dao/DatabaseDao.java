package org.ucsdcssa.capes.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.ucsdcssa.capes.pojo.Course;

import java.util.List;

@Repository
public interface DatabaseDao {

//    @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
//    List<Map> listTable();
//
//    @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
//    List<Map> listTableColumn(String tableName);
//
//    @Update("${sql}")
//    boolean operateBySQL(String sql);
//
//    @Select("select id, user_name as userName, note from t_user where id = #{id}")
//    User getUser(Long id);


    @Select("select instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
            "avgGradeExpected, avgGradeReceived, department, courseCode from course_data " +
            "where " +
            "department = #{department} AND courseCode = #{courseCode}")
    List<Course> getByCourse(String department,String courseCode);

    @Select("select instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
            "avgGradeExpected, avgGradeReceived, department, courseCode from course_data " +
            "where " +
            "term = #{term}")
    List<Course> getByTerm(String term);


    @Select("select instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
            "avgGradeExpected, avgGradeReceived, department, courseCode from course_data " +
            "where " +
            "term = #{term}")
    List<Course> getByInstructor(String instructor);

    @Select("select instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
            "avgGradeExpected, avgGradeReceived, department, courseCode from course_data " +
            "where " +
            "avgGradeExpected >= #{min} AND avgGradeExpected <= #{max}")
    List<Course> getByExpectedGPA(float max, float min);

    @Select("select instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
            "avgGradeExpected, avgGradeReceived, department, courseCode from course_data " +
            "where " +
            "avgGradeReceived >= #{min} AND avgGradeReceived <= #{max}")
    List<Course> getByReceivedGPA(float max, float min);

    @Select("select instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
            "avgGradeExpected, avgGradeReceived, department, courseCode from course_data " +
            "where " +
            "studyHrs >= #{min} AND studyHrs <= #{max}")
    List<Course> getByStudyHrs(float max, float min);

    @Insert({
            "<script>",
            "INSERT into course_data" +
                    "(instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
                    "avgGradeExpected, avgGradeReceived, department, courseCode) " +
                    "VALUES" +
                    "<foreach collection='list' item='element' open='' separator=',' close=''>" +
                    "(" +
                    "#{element.instructor}, #{element.course}, #{element.term}, #{element.enroll}, " +
                    "#{element.evalsMade}, #{element.rcmndClass}, #{element.rcmndInstr}, " +
                    "#{element.studyHrs}, #{element.avgGradeExpected}, #{element.avgGradeReceived}, " +
                    "#{element.department}, #{element.courseCode}" +
                    ")" +
                    "</foreach>",
            "</script>"})
    boolean insert(List<Course> list);

    @Select({
            "<script>",
            "select instructor, course, term, enroll, evalsMade, rcmndClass, rcmndInstr, studyHrs, " +
                    "avgGradeExpected, avgGradeReceived, department, courseCode from course_data " +
                    "where " +
                    "department = #{department} AND courseCode = #{courseCode}" +

                    "<if test=\"instructor != null\"> AND instructor = #{instructor} </if>"+
                    "<if test=\"maxExpectedGPA != null\"> <![CDATA[AND avgGradeExpected <= #{maxExpectedGPA}]]> </if>"+
                    "<if test=\"minExpectedGPA != null\"> AND avgGradeExpected >= #{minExpectedGPA} </if>"+
                    "<if test=\"maxReceivedGPA != null\"> <![CDATA[AND avgGradeReceived <= #{maxReceivedGPA}]]> </if>"+
                    "<if test=\"minReceivedGPA != null\"> AND avgGradeReceived >= #{minReceivedGPA} </if>"+
                    "<if test=\"maxStudyHrs != null\"> <![CDATA[AND studyHrs <= #{maxStudyHrs}]]> </if>"+
                    "<if test=\"minStudyHrs != null\"> AND studyHrs >= #{minStudyHrs} </if>",
            "</script>"
    })
    List<Course> getByCourseExtend(String department, String courseCode, String instructor, Float maxExpectedGPA, Float minExpectedGPA, Float maxReceivedGPA, Float minReceivedGPA, Float maxStudyHrs, Float minStudyHrs);

}
