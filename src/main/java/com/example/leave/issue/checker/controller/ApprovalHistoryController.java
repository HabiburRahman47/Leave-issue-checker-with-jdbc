package com.example.leave.issue.checker.controller;


import com.example.leave.issue.checker.model.ApprovalHistory;
import com.example.leave.issue.checker.model.LeaveAttendanceKey;
import com.example.leave.issue.checker.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApprovalHistoryController {
    @Autowired
    private ApprovalService approvalService;
    @GetMapping("/leave-approval-all")
    public List<LeaveAttendanceKey> getAllApprovalHistory(@RequestParam String startDate, @RequestParam String endDate){
        return approvalService.getAllApprovalHistory(startDate, endDate);
    }

    @GetMapping("/leave-issues-with-approval")
    public List<LeaveAttendanceKey> getLeaveIssueApprovalHistory(@RequestParam String startDate, @RequestParam String endDate){
        return approvalService.getLeaveIssueWithApprovalHistory(startDate, endDate);
    }

    @GetMapping("/leave-approval-history")
    public List<ApprovalHistory> getLeaveApprovalHistory(){
        return approvalService.getLeaveApprovalHistory();
    }
}
