package hust.project.base.modified.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import hust.project.base.utils.sql_hikari.DatabaseManager;
import hust.project.base.utils.sql_hikari.SQLJavaBridge;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;

public class AttendanceRecordEntity implements AttendanceRecordRepository{

    public AttendanceRecord getAttendanceRecordByRecordId(String recordId) {
        SQLJavaBridge bridge = null;
        try {
            bridge = DatabaseManager.instance().defaulSQLJavaBridge();
            String query = "SELECT record_id, employee_id, fingerscanner_id, date, time FROM attendancerecords WHERE record_id = ?";
            JsonObject json = bridge.queryOne(query, recordId);
            String employeeId = json.get("employee_id").getAsString();
            String fingerscannerId = json.get("fingerscanner_id").getAsString();
            String date = json.get("date").getAsString(); // Fetching as string
            String time = json.get("time").getAsString(); // Fetching as string
            return new AttendanceRecord (recordId, employeeId, fingerscannerId, date, time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateNextRecordId() {
        SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
        try {
            String query = "SELECT record_id FROM attendancerecords ORDER BY record_id DESC LIMIT 1";
            JsonObject json = bridge.queryOne(query);
            if (json != null) {
                String lastId = json.get("record_id").getAsString();
                int num = Integer.parseInt(lastId.substring(6)) + 1;
                return "RECORD" + String.format("%03d", num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0"; // Default value if no records are found or in case of error
    }

    public  void updateAttendanceRecord(String time, String recordId) {
        SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
        try {
            String query = "UPDATE attendancerecords SET time = ? WHERE record_id = ?";
            bridge.update(query, time, recordId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAttendanceRecord(AttendanceRecord attendanceRecordDTO) {
        SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
        try {
            String query = "INSERT INTO attendancerecords (record_id, employee_id, fingerscanner_id, date, time) VALUES (?, ?, ?, ?, ?)";
            bridge.update(query, attendanceRecordDTO.getRecordId(), attendanceRecordDTO.getEmployeeId(), attendanceRecordDTO.getFingerscannerId () , attendanceRecordDTO.getDate(), attendanceRecordDTO.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
