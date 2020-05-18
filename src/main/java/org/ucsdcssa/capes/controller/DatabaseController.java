package org.ucsdcssa.capes.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ucsdcssa.capes.exception.BadRequestException;
import org.ucsdcssa.capes.exception.NotFoundException;
import org.ucsdcssa.capes.pojo.Course;
import org.ucsdcssa.capes.service.DatabaseService;
import org.ucsdcssa.capes.util.JsonResult;

import java.util.List;

@RestController
@RequestMapping("/ucsdcssa")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService = null;

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value="/getUser",method= RequestMethod.GET)
//    @ResponseBody
//    public User getUser(Long id) {
//        User user = databaseService.getUser(id);
//        if(user==null) {
//            throw new RuntimeException();
//        }
//        return user;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value="/list",method= RequestMethod.GET)
//    public JsonResult list() {
//        JsonResult jr = new JsonResult();
//        jr.setObj(databaseService.listTable());
//        jr.setMsg("OK");
//        jr.setCode(200L);
//        return jr;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value="/columns/{tableName}",method= RequestMethod.GET)
//    public JsonResult ServiceRowsResult(@PathVariable("tableName")String tableName) {
//        JsonResult jr = new JsonResult();
//        jr.setObj(databaseService.listTableColumn(tableName));
//        jr.setMsg("OK");
//        jr.setCode(200L);
//        return jr;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value="/print",method= RequestMethod.GET)
//    public JsonResult printStructure() {
//        JsonResult jr = new JsonResult();
//        jr.setObj(databaseService.printStructure());
//        jr.setMsg("OK");
//        jr.setCode(200L);
//        return jr;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value="/update",method= RequestMethod.POST)
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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/allCourse", method = RequestMethod.POST)
    public JsonResult insertAll(@RequestBody JSONArray response) {
//        JSONArray jsonArray = response.getJSONArray("student");
        JsonResult jr = new JsonResult();
        jr.setObj(databaseService.insertAll(response));
        jr.setMsg("created");
        jr.setCode(201L);
        return jr;
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/course/{department}.{courseCode}", method = RequestMethod.GET)
//    public JsonResult getByCourse(@PathVariable String department, @PathVariable String courseCode,
//                                  String instructor,
//                                  float maxExpectedGPA, float minExpectedGPA,
//                                  float maxReceivedGPA, float minReceivedGPA,
//                                  float maxStudyHrs, float minStudyHrs) {
//
//        JsonResult jr = new JsonResult();
//        jr.setObj(databaseService.getByCourse(department,courseCode));
//        jr.setMsg("OK");
//        jr.setCode(200L);
//        return jr;
//    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/course/{department}.{courseCode}", method = RequestMethod.GET)
    public List<Course> getByCourseExtend(@PathVariable String department, @PathVariable String courseCode,
                                  String instructor,
                                        Float maxExpectedGPA, Float minExpectedGPA,
                                        Float maxReceivedGPA, Float minReceivedGPA,
                                        Float maxStudyHrs, Float minStudyHrs) {


        if(department==null||department.length()==0||courseCode==null||courseCode.length()==0)
            throw new BadRequestException(400L,"Illegal Argument");
        

        List<Course> ans = databaseService.getByCourseExtend(department,courseCode,instructor,maxExpectedGPA,
                minExpectedGPA,maxReceivedGPA,minReceivedGPA,maxStudyHrs,minStudyHrs);
        if(ans==null||ans.size()==0)
            throw new NotFoundException(404L,"Not Found" );
        return ans;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/term/{term}", method = RequestMethod.GET)
    public List<Course> getByTerm(@PathVariable String term) {
        if(term==null||term.length()==0)
            throw new BadRequestException(400L,"Illegal Argument");
        List<Course> ans = databaseService.getByTerm(term);
        if(ans==null||ans.size()==0)
            throw new NotFoundException(404L,"Not Found" );
        return ans;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/instructor/{instructor}", method = RequestMethod.GET)
    public List<Course> getByInstructor(@PathVariable String instructor) {
        if(instructor==null||instructor.length()==0)
            throw new BadRequestException(400L,"Illegal Argument");
        List<Course> ans = databaseService.getByInstructor(instructor);
        if(ans==null||ans.size()==0)
            throw new NotFoundException(404L,"Not Found" );
        return ans;
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/expectedGPA/{max}.{min}", method = RequestMethod.GET)
    public List<Course> getByExpectedGPA(@PathVariable float max,@PathVariable float min ) {
        if(max>4||min<0)
            throw new BadRequestException(400L,"Illegal Argument");
        List<Course> ans = databaseService.getByExpectedGPA(max,min);
        if(ans==null||ans.size()==0)
            throw new NotFoundException(404L,"Not Found" );
        return ans;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/receivedGPA/{max}.{min}", method = RequestMethod.GET)
    public List<Course> getByReceivedGPA(@PathVariable float max,@PathVariable float min ) {
        if(max>4||min<0)
            throw new BadRequestException(400L,"Illegal Argument");
        List<Course> ans = databaseService.getByReceivedGPA(max,min);
        if(ans==null||ans.size()==0)
            throw new NotFoundException(404L,"Not Found" );
        return ans;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/studyHrs/{max}.{min}", method = RequestMethod.GET)
    public List<Course> getByStudyHrs(@PathVariable float max,@PathVariable float min ) {
        if(min<0)
            throw new BadRequestException(400L,"Illegal Argument");
        List<Course> ans = databaseService.getByStudyHrs(max,min);
        if(ans==null||ans.size()==0)
            throw new NotFoundException(404L,"Not Found" );
        return ans;
    }
}
