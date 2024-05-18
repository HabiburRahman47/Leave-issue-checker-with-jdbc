package com.example.leave.issue.checker.dao;

import com.example.leave.issue.checker.model.ApprovalHistory;
import com.example.leave.issue.checker.model.LeaveAttendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApprovalHistoryDB {
    Connection conn = null;

    public ApprovalHistoryDB(){
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.25.3.15:1521/SSGPROD","xxma","ssgil91118smart");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<LeaveAttendance> fetchLeaveAttendance(String startDate, String endDate){
        List<LeaveAttendance> leaveAttendances = new ArrayList<>();
        String query = "SELECT\n" +
                "        *\n" +
                "FROM\n" +
                "  (  select \n" +
                "    hdr.report_header_id,\n" +
                "    entry_date,\n" +
                "    status_flg,\n" +
                "    WF_ITEM_KEY,\n" +
                "    hdr.creation_date,\n" +
                "    employee_number,\n" +
                "    leave_start_date,\n" +
                "    leave_end_date,\n" +
                "    leave_type\n" +
                "from \n" +
                "    APPS.XXSSGIL_APPROVAL_LV_HDR HDR,\n" +
                "    APPS.XXSSGIL_APPROVAL_LV_LINE LINE\n" +
                "where \n" +
                "    HDR.REPORT_HEADER_ID = LINE.REPORT_HEADER_ID) leave,\n" +
                "(SELECT \n" +
                "ROWNUM SRL_NUM,\n" +
                "TRUNC (\n" +
                "          ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60 / (24 * 60 * 60))\n" +
                "       || ':'\n" +
                "       || TRUNC (\n" +
                "             MOD ( ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60,\n" +
                "                  (24 * 60 * 60))\n" +
                "             / (60 * 60))\n" +
                "       || ':'\n" +
                "       || TRUNC (\n" +
                "             MOD ( ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60, (60 * 60))\n" +
                "             / 60)\n" +
                "       || ':'\n" +
                "       || TRUNC (MOD ( ( (ACT_OUT_TIME - ACT_IN_TIME)) * 24 * 60 * 60, 60))\n" +
                "          W_HOUR,\n" +
                "       TO_CHAR (ACT_IN_TIME, 'HH:MI:SS AM ') ACT_IN_TIME,\n" +
                "       TO_CHAR (ACT_OUT_TIME, 'HH:MI:SS AM ') ACT_OUT_TIME,\n" +
                "       --EMP_NAME,\n" +
                "       EMPLOYEE_NUMBER,\n" +
                "       EMPLOYMENT_CATEGORY,\n" +
                "       DEPT,\n" +
                "       GROUP_NAME,\n" +
                "       WORKING_DATE,\n" +
                "       WORKING_DAY,\n" +
                "       CASE WHEN STATUS='P' THEN 'Present' WHEN STATUS ='H' THEN 'Holiday' WHEN STATUS='LV' THEN 'Leave' WHEN STATUS='A' THEN 'Absent' WHEN STATUS='L' THEN 'Late' \n" +
                "       WHEN STATUS='O' THEN 'Offday' ELSE NULL END AS STATUS,\n" +
                "       Leave_Type\n" +
                "--       LOCATION_CODE,\n" +
                "--       PAYROLL_NAME,\n" +
                "--      VAL\n" +
                "  FROM (  SELECT DISTINCT\n" +
                "                 TO_DATE (ACT_IN_TIME, 'DD-MON-YYYY HH24:MI:SS ') ACT_IN_TIME,\n" +
                "                 TO_DATE (ACT_OUT_TIME, 'DD-MON-YYYY HH24:MI:SS ') ACT_OUT_TIME,\n" +
                "                 (   PAPF.FIRST_NAME\n" +
                "                  || ' '\n" +
                "                  || PAPF.MIDDLE_NAMES\n" +
                "                  || ' '\n" +
                "                  || PAPF.LAST_NAME)\n" +
                "                    AS EMP_NAME,\n" +
                "                 PAPF.EMPLOYEE_NUMBER,\n" +
                "                 FLV.MEANING EMPLOYMENT_CATEGORY,\n" +
                "                 HAOU.NAME DEPT,\n" +
                "                 --  pj.NAME,\n" +
                "                 PPG.GROUP_NAME,\n" +
                "                 XESD.WORKING_DATE,\n" +
                "                 TO_CHAR (XESD.WORKING_DATE, 'Day') WORKING_DAY,\n" +
                "                 XESD.STATUS,\n" +
                "                 paat.name Leave_Type,\n" +
                "                 HL.LOCATION_CODE,\n" +
                "                 PAYROLL.PAYROLL_NAME,\n" +
                "                 DECODE (FLV2.MEANING,\n" +
                "                         'Sales Field Officer', 'Sales',\n" +
                "                         'Hospital Employee', 'Hospital',\n" +
                "                         NULL)\n" +
                "                    VAL\n" +
                "            FROM XXSSGIL.XXSSGIL_EMP_SHIFT_PATT_ALLOC XESP,\n" +
                "                 XXSSGIL.XXSSGIL_SHIFT_PATTERN_DEFN XSPD,\n" +
                "                 XXSSGIL.XXSSGIL_EMP_SHIFT_ALLOC_DET XESD,\n" +
                "                 APPS.PER_ALL_PEOPLE_F PAPF,\n" +
                "                 --per_jobs pj,\n" +
                "                 APPS.PER_ALL_ASSIGNMENTS_F PAAF,\n" +
                "                 APPS.HR_LOCATIONS HL,\n" +
                "                 APPLSYS.FND_LOOKUP_VALUES FLV,\n" +
                "                 APPS.HR_ALL_ORGANIZATION_UNITS HAOU,\n" +
                "                 APPS.PAY_PEOPLE_GROUPS PPG,\n" +
                "                 APPS.PAY_ALL_PAYROLLS_F PAYROLL,\n" +
                "                 APPS.PER_PAY_BASES PPB,\n" +
                "                 APPS.PER_ABSENCE_ATTENDANCES PAT,\n" +
                "                 APPS.PER_ABSENCE_ATTENDANCE_TYPES PAAT,\n" +
                "                 APPLSYS.FND_LOOKUP_VALUES FLV2\n" +
                "           --   apps.per_abs_attendance_types_v attv,\n" +
                "           -- apps.per_absence_attendances paa\n" +
                "           WHERE PAPF.PERSON_ID = XESP.PERSON_ID\n" +
                "                 AND XESP.CREATION_DATE BETWEEN PAPF.EFFECTIVE_START_DATE\n" +
                "                                            AND PAPF.EFFECTIVE_END_DATE\n" +
                "                 AND PAAF.PERSON_ID = PAPF.PERSON_ID\n" +
                "                 --AND paaf.job_id = pj.job_id\n" +
                "                 --AND XESP.CREATION_DATE BETWEEN PAAF.EFFECTIVE_START_DATE AND PAAF.EFFECTIVE_END_DATE\n" +
                "                 AND HL.LOCATION_ID(+) = PAAF.LOCATION_ID\n" +
                "                 AND FLV.LOOKUP_CODE(+) = PPB.NAME\n" +
                "                 AND UPPER (FLV.LOOKUP_TYPE(+)) =\n" +
                "                        UPPER ('SSGIL_BD_EMPL_CAT_SALARY_BASIS')\n" +
                "                 AND FLV.ENABLED_FLAG(+) = 'Y'\n" +
                "                 AND HAOU.ORGANIZATION_ID = PAAF.ORGANIZATION_ID\n" +
                "                 AND PPG.PEOPLE_GROUP_ID(+) = PAAF.PEOPLE_GROUP_ID\n" +
                "                 AND PPG.ENABLED_FLAG(+) = 'Y'\n" +
                "                 AND PPG.END_DATE_ACTIVE(+) IS NULL\n" +
                "                 AND XSPD.PATTERN_NUMBER = XESP.SHIFT_PATTERN\n" +
                "                 AND XESD.PATT_ALLOC_ID = XESP.PATT_ALLOC_ID\n" +
                "                 AND PAAF.CREATION_DATE BETWEEN PAYROLL.EFFECTIVE_START_DATE(+)\n" +
                "                                            AND PAYROLL.EFFECTIVE_END_DATE(+)\n" +
                "                 AND PAYROLL.PAYROLL_ID(+) = PAAF.PAYROLL_ID\n" +
                "                 --- join for getting leave name ----\n" +
                "                 AND XESD.WORKING_DATE BETWEEN XESP.START_DATE\n" +
                "                                           AND XESP.END_DATE\n" +
                "                 AND PAAF.PAY_BASIS_ID = PPB.PAY_BASIS_ID\n" +
                "                 AND XESD.PERSON_ID = PAT.PERSON_ID(+)\n" +
                "                 --AND paaf.business_group_id = pj.business_group_id\n" +
                "                 --and paa.absence_attendance_type_id = attv.absence_attendance_type_id\n" +
                "                 --and PAPF.person_id=paa.person_id\n" +
                "                 AND XESD.WORKING_DATE BETWEEN PAT.DATE_START(+)\n" +
                "                                           AND PAT.DATE_END(+)\n" +
                "                 AND PAT.ABSENCE_ATTENDANCE_TYPE_ID =\n" +
                "                        PAAT.ABSENCE_ATTENDANCE_TYPE_ID(+)\n" +
                "                 AND PAAT.DATE_END(+) IS NULL\n" +
                "                 --- join for emp category--------\n" +
                "                 AND FLV2.LOOKUP_TYPE(+) = 'EMPLOYEE_CATG'\n" +
                "                 AND FLV2.ENABLED_FLAG(+) = 'Y'\n" +
                "                 AND FLV2.END_DATE_ACTIVE(+) IS NULL\n" +
                "                 -- correction over version 1.1 of attendance update logic to include hospital--\n" +
                "                 -- add in condition for cases 5,6,7 to include hospital category------\n" +
                "                 AND FLV2.MEANING(+) IN\n" +
                "                        ('Sales Field Officer', 'Hospital Employee')\n" +
                "                 AND PAAF.EMPLOYEE_CATEGORY = FLV2.LOOKUP_CODE(+)\n" +
                "                 /*Commented due to assignment changes in between Employee wise Pattern Allocation on 25-May-2016*/\n" +
                "                 AND XESD.WORKING_DATE BETWEEN PAAF.EFFECTIVE_START_DATE\n" +
                "                                           AND PAAF.EFFECTIVE_END_DATE\n" +
                "                 AND PAAF.PERSON_ID = XESD.PERSON_ID\n" +
                "--               AND TRIM (XESD.WORKING_DATE) BETWEEN TO_DATE ('$from_date', 'YYYY-MM-DD')\n" +
                "--                             AND TO_DATE ('$to_date', 'YYYY-MM-DD')\n" +
                "                 AND TRUNC (XESD.WORKING_DATE) BETWEEN ?--:P_DATE_FROM \n" +
                "                 AND  ?--:P_DATE_TO\n" +
                "        ORDER BY XESD.WORKING_DATE, PAPF.EMPLOYEE_NUMBER ASC)) Attend\n" +
                "where\n" +
                "Attend.EMPLOYEE_NUMBER = leave.EMPLOYEE_NUMBER \n" +
                "and Attend.status in ('Absent','Late')  \n" +
                "and leave.STATUS_FLG <>  'Rejected'\n" +
                "--and leave.STATUS_FLG  in ( 'Approved')\n" +
                "--and leave.report_header_id = '13789'\n" +
                "--and Attend.leave_type is null\n" +
                "and TO_DATE(Attend.WORKING_DATE, 'DD-Mon-YY') BETWEEN TO_DATE(leave.leave_start_date, 'DD-Mon-YY HH:MI:SS AM') AND TO_DATE(leave.leave_end_date, 'DD-Mon-YY HH:MI:SS AM')\n" +
                "order by leave.entry_date asc";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, startDate);
            st.setString(2,endDate);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                LeaveAttendance leaveAttendance = new LeaveAttendance();
                leaveAttendance.setReportHeaderId(rs.getLong(1));
                leaveAttendance.setEntryDate(rs.getString(2));
                leaveAttendance.setStatusFlg(rs.getString(3));
                leaveAttendance.setWfItemKey(rs.getString(4));
                leaveAttendance.setCreationDate(rs.getString(5));
                leaveAttendance.setEmpId(rs.getString(6));
                leaveAttendance.setLeaveStartDate(rs.getString(7));
                leaveAttendance.setLeaveEndDate(rs.getString(8));
                leaveAttendance.setLeaveType(rs.getString(9));
                leaveAttendance.setSrlNum(rs.getInt(10));
                leaveAttendance.setwHour(rs.getString(11));
                leaveAttendance.setActInTime(rs.getString(12));
                leaveAttendance.setActOutTime(rs.getString(13));
                leaveAttendance.setEmployeeNumber(rs.getString(14));
                leaveAttendance.setEmploymentCategory(rs.getString(15));
                leaveAttendance.setDept(rs.getString(16));
                leaveAttendance.setGroupName(rs.getString(17));
                leaveAttendance.setWorkingDate(rs.getString(18));
                leaveAttendance.setWorkingDay(rs.getString(19));
                leaveAttendance.setStatus(rs.getString(20));
                leaveAttendance.setActionStatus(rs.getString(21));
                leaveAttendances.add(leaveAttendance);

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return leaveAttendances;

    }
    public List<ApprovalHistory> fetchApprovalHistory(String headerId){
        List<ApprovalHistory> approvalHistories = new ArrayList<>();
        String query = "select\n" +
                "    SRL_NUM,\n" +
                "    EXPENSE_REPORT_HDR_ID,\n" +
                "    NTF_RESPONDER,\n" +
                "    NTF_ACTION,\n" +
                "    NTF_NOTE\n" +
                "from \n" +
                "    XXSSGIL.XXSSGIL_APPROVAL_HISTORYS_VV \n" +
                "where \n" +
                "    EXPENSE_REPORT_HDR_ID =? \n"+
                "ORDER BY SRL_NUM DESC \n"+
                "FETCH FIRST 1 ROW ONLY";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,headerId);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                ApprovalHistory apphis = new ApprovalHistory();
                apphis.setSerialNumber(rs.getInt(1));
                apphis.setExpenseReportHeaderId(rs.getString(2));
                apphis.setNotifierResponder(rs.getString(3));
                apphis.setNotifierAction(rs.getString(4));
                apphis.setNotifierNote(rs.getString(5));
                approvalHistories.add(apphis);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return approvalHistories;
    }

    public List<ApprovalHistory> fetchLeaveApprovalHistory(String headerId){
        List<ApprovalHistory> approvalHistories = new ArrayList<>();

        String query = "SELECT ROWNUM SRL_NUM,\n" +
                "        NTF_RESPONDER,\n" +
                "        CASE\n" +
                "           WHEN APPROVER_NAME IS NULL THEN NTF_RESPONDER\n" +
                "           ELSE APPROVER_NAME\n" +
                "        END\n" +
                "           APPROVER_NAME,\n" +
                "        APPROVER_ACTION,\n" +
                "        NTF_NOTE NOTE,\n" +
                "        ACTION_DATE\n" +
                "   FROM                                                                     --\n" +
                "       (                                                                    --\n" +
                "        SELECT  SRL_NUM,\n" +
                "                ACTION_DATE,\n" +
                "                NTF_RESPONDER,\n" +
                "                APPROVER_NAME,\n" +
                "                APPROVER_ACTION,\n" +
                "                NTF_NOTE\n" +
                "           FROM                                                             --\n" +
                "               (  SELECT X.SRL_NUM SRL_NUM,\n" +
                "                         X.NTF_RESPONDER,\n" +
                "                         CASE\n" +
                "                            WHEN PPF.FULL_NAME IS NULL\n" +
                "                            THEN\n" +
                "                               (SELECT DISPLAY_NAME\n" +
                "                                  FROM APPS.WF_ROLES\n" +
                "                                 WHERE NAME = X.NTF_RESPONDER)\n" +
                "                            ELSE\n" +
                "                               PPF.FULL_NAME\n" +
                "                         END\n" +
                "                            AS APPROVER_NAME,\n" +
                "                         INITCAP (X.NTF_ACTION) AS APPROVER_ACTION,\n" +
                "                         REPLACE (REPLACE (X.NTF_NOTE, CHR (13), ' '),\n" +
                "                                  CHR (10),\n" +
                "                                  '')\n" +
                "                            AS NTF_NOTE,\n" +
                "                         TO_CHAR (X.CREATION_DATE, 'DD-MON-RR HH24:MI')\n" +
                "                            AS ACTION_DATE\n" +
                "                    FROM                          --xxssgil_er_approval_hist x\n" +
                "                        XXSSGIL.XXSSGIL_APPROVAL_HISTORYS_VV X,\n" +
                "                         APPS.PER_ALL_PEOPLE_F PPF\n" +
                "                   WHERE X.EMPLOYEE_ID = PPF.PERSON_ID(+)\n" +
                "                         AND TRUNC (X.CREATION_DATE) BETWEEN PPF.EFFECTIVE_START_DATE(+)\n" +
                "                                                         AND PPF.EFFECTIVE_END_DATE(+)\n" +
                "                         AND X.EXPENSE_REPORT_HDR_ID =?\n" +
                "                ORDER BY X.SRL_NUM)\n" +
                "        UNION ALL\n" +
                "        (SELECT SRL_NUM,\n" +
                "                ACTION_DATE,\n" +
                "                NTF_RESPONDER,\n" +
                "                APPROVER_NAME,\n" +
                "                APPROVER_ACTION,\n" +
                "                NTF_NOTE\n" +
                "           FROM (SELECT 0 AS SRL_NUM,\n" +
                "                       WUR.USER_NAME AS NTF_RESPONDER,\n" +
                "                        (SELECT PAPF.FULL_NAME\n" +
                "                           FROM APPS.PER_ALL_PEOPLE_F PAPF,\n" +
                "                                APPS.PER_ALL_ASSIGNMENTS_F PAAF\n" +
                "                          WHERE     1 = 1\n" +
                "                                AND PAPF.BUSINESS_GROUP_ID = '84'\n" +
                "                                AND PAPF.EMPLOYEE_NUMBER = WUR.USER_NAME --'3023'\n" +
                "                                AND PAPF.PERSON_ID = PAAF.PERSON_ID\n" +
                "                                AND TRUNC (SYSDATE) BETWEEN PAPF.EFFECTIVE_START_DATE\n" +
                "                                                        AND PAPF.EFFECTIVE_END_DATE\n" +
                "                                AND TRUNC (SYSDATE) BETWEEN PAAF.EFFECTIVE_START_DATE\n" +
                "                                                        AND PAAF.EFFECTIVE_END_DATE)\n" +
                "                           AS APPROVER_NAME,\n" +
                "                        'Pending' AS APPROVER_ACTION,\n" +
                "                        '' AS NTF_NOTE,\n" +
                "                        '' AS ACTION_DATE\n" +
                "                   FROM APPS.XXSSGIL_APPROVAL_LV_HDR H,\n" +
                "                        APPS.XXSSGIL_APPROVAL_LV_LINE L,\n" +
                "                        APPS.WF_USER_ROLES WUR,\n" +
                "                        APPS.WF_NOTIFICATIONS N\n" +
                "                  WHERE     1 = 1\n" +
                "                        AND H.REPORT_HEADER_ID =?\n" +
                "                        AND WUR.ROLE_NAME = N.TO_USER\n" +
                "                        AND H.WF_ITEM_KEY = N.ITEM_KEY\n" +
                "                        AND L.REPORT_HEADER_ID = H.REPORT_HEADER_ID\n" +
                "                        AND N.STATUS = 'OPEN'\n" +
                "                        AND H.STATUS_FLG = 'In-Process')))";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,headerId);
            st.setString(2,headerId);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                ApprovalHistory apphis = new ApprovalHistory();
                apphis.setSerialNumber(rs.getInt(1));
                apphis.setExpenseReportHeaderId(rs.getString(2));
                apphis.setNotifierResponder(rs.getString(3));
                apphis.setNotifierAction(rs.getString(4));
                apphis.setNotifierNote(rs.getString(5));
                apphis.setActionDate(rs.getString(6));
                approvalHistories.add(apphis);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return approvalHistories;
    }
}
