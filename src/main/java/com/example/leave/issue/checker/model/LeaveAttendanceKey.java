package com.example.leave.issue.checker.model;

import java.util.List;

public class LeaveAttendanceKey {
    private Long reportHeaderId;
    private String entryDate;
    private String statusFlg;
    private String wfItemKey;
    private String empId;
    private String leaveStartDate;
    private String leaveEndDate;
    private String leaveType;
    private String workingDate;
    private String status;
    private String actionStatus;
    private List<ApprovalHistory> approvalHistories;

    public LeaveAttendanceKey() {
    }

    public LeaveAttendanceKey(Long reportHeaderId, String entryDate, String statusFlg, String wfItemKey, String empId, String leaveStartDate, String leaveEndDate, String leaveType, String workingDate, String status, String actionStatus, List<ApprovalHistory> approvalHistories) {
        this.reportHeaderId = reportHeaderId;
        this.entryDate = entryDate;
        this.statusFlg = statusFlg;
        this.wfItemKey = wfItemKey;
        this.empId = empId;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.leaveType = leaveType;
        this.workingDate = workingDate;
        this.status = status;
        this.actionStatus = actionStatus;
        this.approvalHistories = approvalHistories;
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

    public String getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
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

    public List<ApprovalHistory> getApprovalHistories() {
        return approvalHistories;
    }

    public void setApprovalHistories(List<ApprovalHistory> approvalHistories) {
        this.approvalHistories = approvalHistories;
    }
}
