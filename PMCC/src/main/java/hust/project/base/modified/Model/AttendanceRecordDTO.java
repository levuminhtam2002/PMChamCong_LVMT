package hust.project.base.modified.Model;

import java.sql.Timestamp;

public class AttendanceRecordDTO {

    private String recordId;
    private String employeeId;
    private String fingerscannerId;
    private Timestamp timestamp;
    private String attendanceType;


    public String getRecordId() {
        return recordId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public String getFingerscannerId() {
        return fingerscannerId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setFingerscannerId(String fingerscannerId) {
        this.fingerscannerId = fingerscannerId;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;    }
}
