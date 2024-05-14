package com.example.leave.issue.checker.model;

public class ApprovalHistory {

    private Integer serialNumber;


    private String expenseReportHeaderId;


    private String notifierResponder;


    private String notifierAction;


    private String notifierNote;
    private String actionDate;

    public ApprovalHistory() {
    }

    public ApprovalHistory(Integer serialNumber, String expenseReportHeaderId, String notifierResponder, String notifierAction, String notifierNote) {
        this.serialNumber = serialNumber;
        this.expenseReportHeaderId = expenseReportHeaderId;
        this.notifierResponder = notifierResponder;
        this.notifierAction = notifierAction;
        this.notifierNote = notifierNote;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getExpenseReportHeaderId() {
        return expenseReportHeaderId;
    }

    public void setExpenseReportHeaderId(String expenseReportHeaderId) {
        this.expenseReportHeaderId = expenseReportHeaderId;
    }

    public String getNotifierResponder() {
        return notifierResponder;
    }

    public void setNotifierResponder(String notifierResponder) {
        this.notifierResponder = notifierResponder;
    }

    public String getNotifierAction() {
        return notifierAction;
    }

    public void setNotifierAction(String notifierAction) {
        this.notifierAction = notifierAction;
    }

    public String getNotifierNote() {
        return notifierNote;
    }

    public void setNotifierNote(String notifierNote) {
        this.notifierNote = notifierNote;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }
}
