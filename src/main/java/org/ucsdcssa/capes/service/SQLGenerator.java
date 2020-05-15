package org.ucsdcssa.capes.service;

import org.ucsdcssa.capes.pojo.Column;
import org.ucsdcssa.capes.pojo.Table;

import java.util.List;

public interface SQLGenerator {

    String createTable(List<Table> tableList);
    String addColumn(List<Column> columnList);
    String modifyColumn(List<Column> columnList);
    String deleteTable(List<Table> tableList);
    String deleteColumn(List<Column> columnList);

}
