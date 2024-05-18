package com.example.leave.issue.checker.service;

import com.example.leave.issue.checker.dao.ApprovalHistoryDB;
import com.example.leave.issue.checker.model.ApprovalHistory;
import com.example.leave.issue.checker.model.LeaveAttendance;
import com.example.leave.issue.checker.model.LeaveAttendanceKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApprovalService {
    ApprovalHistoryDB db = new ApprovalHistoryDB();

    public List<ApprovalHistory> getLeaveApprovalHistory(){
        return db.fetchLeaveApprovalHistory("17960");

    }

    public List<LeaveAttendanceKey> getAllApprovalHistory(String startDate, String endDate) {

        List<LeaveAttendance> leaveAttendanceList = db.fetchLeaveAttendance(startDate, endDate);

        List<LeaveAttendanceKey> leaveAttendanceKeys = new ArrayList<>();
        for (LeaveAttendance leaveAttendance : leaveAttendanceList) {
            LeaveAttendanceKey leaveAttendanceKey = new LeaveAttendanceKey();
            String headerId = String.valueOf(leaveAttendance.getReportHeaderId());
            //leave approval data
            List<ApprovalHistory> leaveApproval = db.fetchLeaveApprovalHistory(headerId);

            // Only if all approvals are not pending, we add this to our list
            leaveAttendanceKey.setReportHeaderId(leaveAttendance.getReportHeaderId());
            leaveAttendanceKey.setEntryDate(leaveAttendance.getEntryDate());
            leaveAttendanceKey.setStatusFlg(leaveAttendance.getStatusFlg());
            leaveAttendanceKey.setWfItemKey(leaveAttendance.getWfItemKey());
            leaveAttendanceKey.setEmpId(leaveAttendance.getEmpId());
            leaveAttendanceKey.setLeaveStartDate(leaveAttendance.getLeaveEndDate());
            leaveAttendanceKey.setLeaveEndDate(leaveAttendance.getLeaveStartDate());
            leaveAttendanceKey.setLeaveType(leaveAttendance.getLeaveType());
            leaveAttendanceKey.setWorkingDate(leaveAttendance.getWorkingDate());
            leaveAttendanceKey.setStatus(leaveAttendance.getStatus());
            leaveAttendanceKey.setActionStatus(leaveAttendance.getActionStatus());
            leaveAttendanceKey.setApprovalHistories(leaveApproval);
            leaveAttendanceKeys.add(leaveAttendanceKey);
        }
        return leaveAttendanceKeys;
    }
    public List<LeaveAttendanceKey> getLeaveIssueWithApprovalHistory(String startDate, String endDate) {

        List<LeaveAttendance> leaveAttendanceList = db.fetchLeaveAttendance(startDate,endDate);

        List<LeaveAttendanceKey> leaveAttendanceKeys = new ArrayList<>();
        for (LeaveAttendance leaveAttendance : leaveAttendanceList) {
            LeaveAttendanceKey leaveAttendanceKey = new LeaveAttendanceKey();
            String headerId = String.valueOf(leaveAttendance.getReportHeaderId());
            //leave approval data
            List<ApprovalHistory> leaveApproval = db.fetchLeaveApprovalHistory(headerId);

            boolean allApproved = true; // Flag to check if all approvals are not pending

            for (ApprovalHistory approval : leaveApproval) {
                if ("Pending".equals(approval.getNotifierAction())) {
                    allApproved = false;
                    break; // If any approval is pending, we skip this entry
                }
            }

            if (allApproved && leaveAttendance.getActionStatus() == null) {
                // Only if all approvals are not pending, we add this to our list
                leaveAttendanceKey.setReportHeaderId(leaveAttendance.getReportHeaderId());
                leaveAttendanceKey.setEntryDate(leaveAttendance.getEntryDate());
                leaveAttendanceKey.setStatusFlg(leaveAttendance.getStatusFlg());
                leaveAttendanceKey.setWfItemKey(leaveAttendance.getWfItemKey());
                leaveAttendanceKey.setEmpId(leaveAttendance.getEmpId());
                leaveAttendanceKey.setLeaveStartDate(leaveAttendance.getLeaveEndDate());
                leaveAttendanceKey.setLeaveEndDate(leaveAttendance.getLeaveStartDate());
                leaveAttendanceKey.setLeaveType(leaveAttendance.getLeaveType());
                leaveAttendanceKey.setWorkingDate(leaveAttendance.getWorkingDate());
                leaveAttendanceKey.setStatus(leaveAttendance.getStatus());
                leaveAttendanceKey.setActionStatus(leaveAttendance.getActionStatus());
                leaveAttendanceKey.setApprovalHistories(leaveApproval);
                leaveAttendanceKeys.add(leaveAttendanceKey);
            }
        }

        return leaveAttendanceKeys;
    }

}
