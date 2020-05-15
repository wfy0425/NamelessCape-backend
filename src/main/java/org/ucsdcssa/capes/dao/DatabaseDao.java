package org.ucsdcssa.capes.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.ucsdcssa.capes.pojo.User;

import java.util.List;
import java.util.Map;

@Repository
public interface DatabaseDao {

    @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Map> listTable();

    @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
    List<Map> listTableColumn(String tableName);

    @Update("${sql}")
    boolean operateBySQL(String sql);

    @Select("select id, user_name as userName, note from t_user where id = #{id}")
    User getUser(Long id);

}
