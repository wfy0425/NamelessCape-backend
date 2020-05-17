package org.ucsdcssa.capes.pojo;

public class Course {

    private String instructor;
    private String course;
    private String term;
    private Integer enroll;
    private Integer evalsMade;
    private Float rcmndClass;
    private Float rcmndInstr;
    private Float studyHrs;
    private Float avgGradeExpected;
    private Float avgGradeReceived;
    private String department;
    private String courseCode;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public Integer getEvalsMade() {
        return evalsMade;
    }

    public void setEvalsMade(Integer evalsMade) {
        this.evalsMade = evalsMade;
    }

    public Float getRcmndClass() {
        return rcmndClass;
    }

    public void setRcmndClass(Float rcmndClass) {
        this.rcmndClass = rcmndClass;
    }

    public Float getRcmndInstr() {
        return rcmndInstr;
    }

    public void setRcmndInstr(Float rcmndInstr) {
        this.rcmndInstr = rcmndInstr;
    }

    public Float getStudyHrs() {
        return studyHrs;
    }

    public void setStudyHrs(Float studyHrs) {
        this.studyHrs = studyHrs;
    }

    public Float getAvgGradeExpected() {
        return avgGradeExpected;
    }

    public void setAvgGradeExpected(Float avgGradeExpected) {
        this.avgGradeExpected = avgGradeExpected;
    }

    public Float getAvgGradeReceived() {
        return avgGradeReceived;
    }

    public void setAvgGradeReceived(Float avgGradeReceived) {
        this.avgGradeReceived = avgGradeReceived;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
