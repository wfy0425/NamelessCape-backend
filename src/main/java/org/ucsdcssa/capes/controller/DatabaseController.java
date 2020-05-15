package org.ucsdcssa.capes.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.ucsdcssa.capes.dbcomparator.DatabaseComparator;
import org.ucsdcssa.capes.pojo.Table;
import org.ucsdcssa.capes.service.DatabaseService;
import org.ucsdcssa.capes.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ucsdcssa.capes.pojo.User;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ucsdcssa")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService = null;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/getUser",method= RequestMethod.GET)
    @ResponseBody
    public User getUser(Long id) {
        User user = databaseService.getUser(id);
        if(user==null) {
            throw new RuntimeException();
        }
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public JsonResult list() {
        JsonResult jr = new JsonResult();
        jr.setObj(databaseService.listTable());
        jr.setMsg("OK");
        jr.setCode(200L);
        return jr;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/columns/{tableName}",method= RequestMethod.GET)
    public JsonResult ServiceRowsResult(@PathVariable("tableName")String tableName) {
        JsonResult jr = new JsonResult();
        jr.setObj(databaseService.listTableColumn(tableName));
        jr.setMsg("OK");
        jr.setCode(200L);
        return jr;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/print",method= RequestMethod.GET)
    public JsonResult printStructure() {
        JsonResult jr = new JsonResult();
        jr.setObj(databaseService.printStructure());
        jr.setMsg("OK");
        jr.setCode(200L);
        return jr;
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value="/comparedb",method= RequestMethod.POST)
//    public JsonResult CompareDb(@RequestBody JSONObject response) {
////        JSONArray jsonArray = response.getJSONArray("student");
//        String jsonStr1 = JSONArray.toJSONString(response.getJSONObject("student"));
//        Map<String,Table> userListInput = JSONArray.parseObject(jsonStr1, new TypeReference<HashMap<String,Table>>() {});
//        JsonResult jr = new JsonResult();
//        Map<String,Table> userListDb = databaseService.printStructure();
//        DatabaseComparator databaseComparator = new DatabaseComparator();
//        databaseComparator.compareTables(userListDb,userListInput);
//
//        jr.setObj(databaseComparator.getResult());
//        jr.setMsg("OK");
//        jr.setCode(200L);
//        return jr;
//    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value="/createTable",method= RequestMethod.POST)
//    public JsonResult createTable(){
//        Table table=new Table();
//        table.setTableName("stu");
//        Column column = new Column();
//        column.setColumnName("s");
//        column.setColumnType("int(12)");
//        column.setColumnKey("PRI");
//        column.setExtra("auto_increment");
//        HashMap<String,Column> hashMap = new HashMap<>();
//        hashMap.put("s",column);
//        table.setColumns(hashMap);
//        JsonResult jr = new JsonResult();
//        jr.setObj(databaseService.createTable(table));
//        jr.setMsg("success");
//        jr.setCode(200L);
//        return jr;
//    }
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value="/addColumn",method= RequestMethod.POST)
//    public JsonResult addColumn(){
//
//        Column column = new Column();
//        column.setColumnName("a");
//        column.setColumnType("int(12)");
//        column.setColumnKey("");
//        column.setExtra("");
//        JsonResult jr = new JsonResult();
//        jr.setObj(databaseService.addColumn(column));
//        jr.setMsg("success");
//        jr.setCode(200L);
//        return jr;
//    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value="/mergeDb",method= RequestMethod.POST)
//    public JsonResult mergeDb(){
//        JsonResult jr = new JsonResult();
//        jr.setObj(databaseService.mergeDb(generatorName));
//        jr.setMsg("success");
//        jr.setCode(200L);
//        return jr;
//    }

}
