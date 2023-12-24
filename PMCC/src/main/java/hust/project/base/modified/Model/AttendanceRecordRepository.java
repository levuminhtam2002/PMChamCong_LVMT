package hust.project.base.modified.Model;

import java.util.List;

public interface AttendanceRecordRepository {

    AttendanceRecord getAttendanceRecordByRecordId(String recordId);
    List<AttendanceRecord> getAllAttendanceRecord();

    List<AttendanceRecord> getAttendanceRecordByEmployeeId(String employeeId);

    void updateAttendanceRecord(String time, String recordId);

    String generateNextRecordId();

    void insertAttendanceRecord(AttendanceRecord attendanceRecordDTO);

}
