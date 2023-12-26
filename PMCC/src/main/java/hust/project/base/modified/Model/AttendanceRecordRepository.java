package hust.project.base.modified.Model;

import java.util.List;

public interface AttendanceRecordRepository {

    AttendanceRecord getAttendanceRecordByRecordId(String recordId);

    void updateAttendanceRecord(String time, String recordId);

    String generateNextRecordId();

    void insertAttendanceRecord(AttendanceRecord attendanceRecordDTO);

}
