package org.ucsdcssa.capes.service;

import org.ucsdcssa.capes.pojo.Column;
import org.ucsdcssa.capes.pojo.Table;
import org.ucsdcssa.capes.pojo.User;
import org.ucsdcssa.capes.util.DbCompareResult;

import java.util.List;
import java.util.Map;

public interface DatabaseService {
    List<Map> listTable();
    List<Map> listTableColumn(String tableName);
    Map<String, Table> printStructure();
    boolean createTable(String generatorName, List<Table> tableList);
    boolean addColumn(String generatorName, List<Column> columnList);
    boolean modifyColumn(String generatorName, List<Column> columnList);
//    DbCompareResult mergeDb(String generatorName);

    User getUser(Long id);
}