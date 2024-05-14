package com.example.leave.issue.checker.model;

public class LeaveAttendance {
    private Long reportHeaderId;
    private String entryDate;
    private String statusFlg;
    private String wfItemKey;
    private String creationDate;
    private String empId;
    private String leaveStartDate;
    private String leaveEndDate;
    private String leaveType;
    private Integer srlNum;
    private String wHour;
    private String actInTime;
    private String actOutTime;
    private String employeeNumber;
    private String employmentCategory;
    private String dept;
    private String groupName;
    private String workingDate;
    private String workingDay;
    private String status;
    private String actionStatus;

    public LeaveAttendance() {
    }

    public LeaveAttendance(Long reportHeaderId, String entryDate, String statusFlg, String wfItemKey, String creationDate, String empId, String leaveStartDate, String leaveEndDate, String leaveType, Integer srlNum, String wHour, String actInTime, String actOutTime, String employeeNumber, String employmentCategory, String dept, String groupName, String workingDate, String workingDay, String status, String actionStatus) {
        this.reportHeaderId = reportHeaderId;
        this.entryDate = entryDate;
        this.statusFlg = statusFlg;
        this.wfItemKey = wfItemKey;
        this.creationDate = creationDate;
        this.empId = empId;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.leaveType = leaveType;
        this.srlNum = srlNum;
        this.wHour = wHour;
        this.actInTime = actInTime;
        this.actOutTime = actOutTime;
        this.employeeNumber = employeeNumber;
        this.employmentCategory = employmentCategory;
        this.dept = dept;
        this.groupName = groupName;
        this.workingDate = workingDate;
        this.workingDay = workingDay;
        this.status = status;
        this.actionStatus = actionStatus;
    }

    public Long getReportHeaderId() {
        return reportHeaderId;
    }

    public void setReportHeaderId(Long reportHeaderId) {
        this.reportHeaderId = reportHeaderId;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getStatusFlg() {
        return statusFlg;
    }

    public void setStatusFlg(String statusFlg) {
        this.statusFlg = statusFlg;
    }

    public String getWfItemKey() {
        return wfItemKey;
    }

    public void setWfItemKey(String wfItemKey) {
        this.wfItemKey = wfItemKey;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(String leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public String getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(String leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getSrlNum() {
        return srlNum;
    }

    public void setSrlNum(Integer srlNum) {
        this.srlNum = srlNum;
    }

    public String getwHour() {
        return wHour;
    }

    public void setwHour(String wHour) {
        this.wHour = wHour;
    }

    public String getActInTime() {
        return actInTime;
    }

    public void setActInTime(String actInTime) {
        this.actInTime = actInTime;
    }

    public String getActOutTime() {
        return actOutTime;
    }

    public void setActOutTime(String actOutTime) {
        this.actOutTime = actOutTime;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmploymentCategory() {
        return employmentCategory;
    }

    public void setEmploymentCategory(String employmentCategory) {
        this.employmentCategory = employmentCategory;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }
}
