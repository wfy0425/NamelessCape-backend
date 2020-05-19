# Nameless Cape-backend

This project is the backend of the Nameless Cape. Nameless Cape aims to improve the overall course selection and comparison experience of UCSD students. This project uses Spring Boot, MyBatis, MySQL, fastJSON and based on RESTful API.


## Quick Start
### Get Course Information By Course Name
Get course information by *POST* method though */namelesscape/course/COURSENAME.COUESECODE* 
```
http://localhost:8080/namelesscape/course/CSE.100
```
*instructor, maxExpectedGPA, minExpectedGPA, maxReceivedGPA, minReceivedGPA, maxStudyHrs, minStudyHrs* can also be passed by *form-data* to get limit conditions.

A JSON file will be returned if the query success. 
```
[
    {
        "instructor": "Smith, Jhon",
        "course": "CSE 100 - Advanced Data Structures (A)",
        "term": "FA19",
        "enroll": 179,
        "evalsMade": 101,
        "rcmndClass": 93.8,
        "rcmndInstr": 46.9,
        "studyHrs": 15.01,
        "avgGradeExpected": 3.2,
        "avgGradeReceived": 2.43,
        "department": "CSE",
        "courseCode": "100"
    }
]
```
### Get Course Information By Instructor Name
Get course information by *GET* method though */namelesscape/instructor/INSTRUCTOR* 
```
http://localhost:8080/namelesscape/instructor/Smith, Jhon
```

A JSON file will be returned if the query success. 

### Get Course Information By Term
Get course information by *GET* method though */namelesscape/term/TERM* 
```
http://localhost:8080/namelesscape/term/FA19
```

A JSON file will be returned if the query success. 

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Server Framework
* [MyBatis](https://mybatis.org/) - Persistence Framework
* [MyBatis](https://mybatis.org/) - Persistence Framework
* [MySQL](https://www.mysql.com/) - Database
* [fastjson](https://github.com/alibaba/fastjson) - Parse JSON Object


## Authors

* **Fengyuan Wu** - *Author* - [PurpleBooth](https://github.com/PurpleBooth)


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

《深入浅出Spring Boot 2.x》 （杨开振）