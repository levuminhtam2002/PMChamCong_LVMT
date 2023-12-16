package hust.project.base.modified.Model;

import java.util.List;

public interface AttendanceRecordRepository {

    AttendanceRecordDTO getAttendanceRecordByRecordId(String recordId);
    List<AttendanceRecordDTO> getAllAttendanceRecord();

    List<AttendanceRecordDTO> getAttendanceRecordByEmployeeId(String employeeId);

    void updateAttendanceRecord(String recordId);

    void deleteAttendanceRecord(String recordId);

    void insertAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO);

}
