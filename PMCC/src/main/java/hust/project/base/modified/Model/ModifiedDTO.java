package hust.project.base.modified.Model;

import java.sql.Timestamp;

public class ModifiedDTO {
    private String recordId;
    private String employeeId; // Assuming we just want the ID here
//    private String timestampafter;
//    private String timestampbefore;

    private Timestamp timestampafter;
    private Timestamp timestampbefore;
    private String attendanceType;
    private String scanId;
    private String requestId;
    private String requestReason;
    private String requestStatus;

    public ModifiedDTO(String recordId, String employeeId, Timestamp timestampafter, Timestamp timestampbefore, String attendanceType, String scanId, String requestId, String requestReason, String requestStatus) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.timestampafter = timestampafter;
        this.timestampbefore = timestampbefore;
        this.attendanceType = attendanceType;
        this.scanId = scanId;
        this.requestId = requestId;
        this.requestReason = requestReason;
        this.requestStatus = requestStatus;

    }



    public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
//    public String getTimestampafter() {
//        return timestampafter;
//    }
//    public void setTimestampafter(String timestampafter){
//        this.timestampafter = timestampafter;
//    }
//    public String getTimestampbefore() {
//        return timestampbefore;    }
//   public void setTimestampbefore(String timestampbefore) {this.timestampbefore = timestampbefore; }
    public Timestamp getTimestampafter() {
        return timestampafter;
    }
    public void setTimestampafter(Timestamp timestampafter){
        this.timestampafter = timestampafter;
    }
    public Timestamp getTimestampbefore() {
        return timestampbefore;    }
    public void setTimestampbefore(Timestamp timestampbefore) {this.timestampbefore = timestampbefore; }
    public String getAttendanceType() {
        return attendanceType;
    }
    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }
    public String getScanId() {
        return scanId;
    }
    public void setScanId(String scanId) {
        this.scanId = scanId;
    }
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {        this.requestId = requestId;    }
    public String getRequestReason() {
        return requestReason;
    }
    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }
    public String getRequestStatus() {
        return requestStatus;
    }
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

}
