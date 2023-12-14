package hust.project.base.modified.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hust.project.base.employee_subsystem.Employee;
import hust.project.base.utils.sql_hikari.DatabaseManager;
import hust.project.base.utils.sql_hikari.SQLJavaBridge;
import hust.project.base.modified.Model.ModifiedDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModifiedDAO implements AttendanceRecordRepository{
    private static Timestamp convertStringToTimestamp(String strDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(strDate);
            return new Timestamp(parsedDate.getTime());
        } catch(Exception e) { //this generic but you can control another types of exception
            // Handle exception
            return null;
        }
    }

    @Override
    public ModifiedDTO getModifiedByRecordId(String recordId) {
        SQLJavaBridge bridge = null;
        try {
            bridge = DatabaseManager.instance().defaulSQLJavaBridge();
            String query = "SELECT * FROM modifiedattendancerecords WHERE record_id = ?";
            JsonObject json = bridge.queryOne(query, recordId);

            if (json == null) {
                throw new IllegalArgumentException("No record found for the given record ID.");
            }

            String employeeId = json.has("employee_id") ? json.get("employee_id").getAsString() : null;
            String timestampafter = json.has("timestamp") ? json.get("timestamp").getAsString() : null;
            String timestampbefore = json.has("timestamp") ? json.get("timestamp").getAsString() : null;
            String attendanceType = json.has("attendance_Type") ? json.get("attendance_Type").getAsString() : null;
            String scanId = json.has("scan_Id") ? json.get("scan_Id").getAsString() : null;
            String requestId = json.has("request_Id") ? json.get("request_Id").getAsString() : null;
            String requestReason = json.has("request_Reason") ? json.get("request_Reason").getAsString() : null;
            String requestStatus = json.has("request_Status") ? json.get("request_Status").getAsString() : null;
return new ModifiedDTO(recordId, employeeId, convertStringToTimestamp("2023-11-01 8:00:00"), convertStringToTimestamp("2023-11-01 08:00:00"), "Check-in", "FS01", "CS01", "Reason for modification 1", "Pending");
//            return new ModifiedDTO(recordId, employeeId, timestampafter, timestampbefore, attendanceType, scanId, requestId, requestReason, requestStatus);
        } catch (Exception e) {
            // Log the exception, or handle it as per your application's error handling strategy
            throw new RuntimeException("Error while fetching data: " + e.getMessage(), e);
        }
    }
//    @Override
//    public List<ModifiedDTO> getAllModifiedDTOs() {
//        SQLJavaBridge bridge = null;
//        List<ModifiedDTO> modifiedDTOList = new ArrayList<>();
//        try {
//            bridge = DatabaseManager.instance().defaulSQLJavaBridge();
//            String query = "SELECT * FROM modifiedattendancerecords";
//            JsonArray jsonArray = bridge.query(query);
//
//            if (jsonArray == null || jsonArray.size() == 0) {
//                throw new IllegalArgumentException("No records found.");
//            }
//
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JsonObject json = jsonArray.get(i).getAsJsonObject();
//
//                String recordId = json.has("record_id") ? json.get("record_id").getAsString() : null;
//                String employeeId = json.has("employee_id") ? json.get("employee_id").getAsString() : null;
//                String timestampafter = json.has("timestamp") ? json.get("timestamp").getAsString() : null; // Assuming different field
//                String timestampbefore = json.has("timestamp") ? json.get("timestamp").getAsString() : null; // Assuming different field
//                String attendanceType = json.has("attendance_Type") ? json.get("attendance_Type").getAsString() : null;
//                String scanId = json.has("scan_Id") ? json.get("scan_Id").getAsString() : null;
//                String requestId = json.has("request_Id") ? json.get("request_Id").getAsString() : null;
//                String requestReason = json.has("request_Reason") ? json.get("request_Reason").getAsString() : null;
//                String requestStatus = json.has("request_Status") ? json.get("request_Status").getAsString() : null;
//
//                modifiedDTOList.add(new ModifiedDTO(recordId, employeeId, timestampafter, timestampbefore, attendanceType, scanId, requestId, requestReason, requestStatus));
//            }
//
//            return modifiedDTOList;
//        } catch (Exception e) {
//            // Proper logging or handling of the exception
//            throw new RuntimeException("Error while fetching data: " + e.getMessage(), e);
//        } finally {
//            // Ensure resources are closed or returned to pool if necessary
//        }
//    }

    @Override
public List<ModifiedDTO> getAllModifiedDTOs() {
    List<ModifiedDTO> modifiedDTOList = new ArrayList<>();
    // Hard-coded data
    modifiedDTOList.add(new ModifiedDTO("RC01", "NV20170534", convertStringToTimestamp("2023-11-01 8:00:00"), convertStringToTimestamp("2023-11-01 08:00:00"), "Check-in", "FS01", "CS01", "Reason for modification 1", "Pending"));
    modifiedDTOList.add(new ModifiedDTO("RC02", "NV20170535", convertStringToTimestamp("2023-11-01 09:00:00"), convertStringToTimestamp("2023-11-01 09:00:00"), "Check-in", "FS02", "CS02", "Reason for modification 2", "Approved"));
    modifiedDTOList.add(new ModifiedDTO("RC03", "NV20170536", convertStringToTimestamp("2023-11-01 09:30:00"), convertStringToTimestamp("2023-11-01 09:30:00"), "Check-in", "FS03", "CS03", "Reason for modification 3", "Rejected"));
    modifiedDTOList.add(new ModifiedDTO("RC04", "NV20170537", convertStringToTimestamp("2023-11-01 10:00:00"), convertStringToTimestamp("2023-11-01 10:00:00"), "Check-in", "FS04", "CS04", "Reason for modification 4", "Pending"));
    modifiedDTOList.add(new ModifiedDTO("RC05", "NV20170538", convertStringToTimestamp("2023-11-01 10:30:00"), convertStringToTimestamp("2023-11-01 10:30:00"), "Check-in", "FS05", "CS05", "Reason for modification 5", "Approved"));
    modifiedDTOList.add(new ModifiedDTO("RC06", "NV20170539", convertStringToTimestamp("2023-11-01 11:00:00"), convertStringToTimestamp("2023-11-01 11:00:00"), "Check-in", "FS06", "CS06", "Reason for modification 6", "Rejected"));
    modifiedDTOList.add(new ModifiedDTO("RC07", "NV20170540", convertStringToTimestamp("2023-11-01 11:30:00"), convertStringToTimestamp("2023-11-01 11:30:00"), "Check-in", "FS07", "CS07", "Reason for modification 7", "Pending"));
    modifiedDTOList.add(new ModifiedDTO("RC08", "NV20170541", convertStringToTimestamp("2023-11-01 12:00:00"), convertStringToTimestamp("2023-11-01 12:00:00"), "Check-in", "FS08", "CS08", "Reason for modification 8", "Approved"));
    modifiedDTOList.add(new ModifiedDTO("RC09", "NV20170542", convertStringToTimestamp("2023-11-01 12:30:00"), convertStringToTimestamp("2023-11-01 12:30:00"), "Check-in", "FS09", "CS09", "Reason for modification 9", "Rejected"));
    modifiedDTOList.add(new ModifiedDTO("RC10", "NV20170543", convertStringToTimestamp("2023-11-01 13:00:00"), convertStringToTimestamp("2023-11-01 13:00:00"), "Check-in", "FS10", "CS10", "Reason for modification 10", "Pending"));
    modifiedDTOList.add(new ModifiedDTO("RC01", "NV20170534", convertStringToTimestamp("2023-11-01 8:00:00"), convertStringToTimestamp("2023-11-01 08:00:00"), "Check-in", "FS01", "CS01", "Reason for modification 1", "Pending"));
    modifiedDTOList.add(new ModifiedDTO("RC02", "NV20170535", convertStringToTimestamp("2023-11-01 09:00:00"), convertStringToTimestamp("2023-11-01 09:00:00"), "Check-in", "FS02", "CS02", "Reason for modification 2", "Approved"));
    modifiedDTOList.add(new ModifiedDTO("RC03", "NV20170536", convertStringToTimestamp("2023-11-01 09:30:00"), convertStringToTimestamp("2023-11-01 09:30:00"), "Check-in", "FS03", "CS03", "Reason for modification 3", "Rejected"));
    modifiedDTOList.add(new ModifiedDTO("RC04", "NV20170537", convertStringToTimestamp("2023-11-01 10:00:00"), convertStringToTimestamp("2023-11-01 10:00:00"), "Check-in", "FS04", "CS04", "Reason for modification 4", "Pending"));
    modifiedDTOList.add(new ModifiedDTO("RC05", "NV20170538", convertStringToTimestamp("2023-11-01 10:30:00"), convertStringToTimestamp("2023-11-01 10:30:00"), "Check-in", "FS05", "CS05", "Reason for modification 5", "Approved"));
    modifiedDTOList.add(new ModifiedDTO("RC06", "NV20170539", convertStringToTimestamp("2023-11-01 11:00:00"), convertStringToTimestamp("2023-11-01 11:00:00"), "Check-in", "FS06", "CS06", "Reason for modification 6", "Rejected"));
    modifiedDTOList.add(new ModifiedDTO("RC07", "NV20170540", convertStringToTimestamp("2023-11-01 11:30:00"), convertStringToTimestamp("2023-11-01 11:30:00"), "Check-in", "FS07", "CS07", "Reason for modification 7", "Pending"));
    modifiedDTOList.add(new ModifiedDTO("RC08", "NV20170541", convertStringToTimestamp("2023-11-01 12:00:00"), convertStringToTimestamp("2023-11-01 12:00:00"), "Check-in", "FS08", "CS08", "Reason for modification 8", "Approved"));
    modifiedDTOList.add(new ModifiedDTO("RC09", "NV20170542", convertStringToTimestamp("2023-11-01 12:30:00"), convertStringToTimestamp("2023-11-01 12:30:00"), "Check-in", "FS09", "CS09", "Reason for modification 9", "Rejected"));
    modifiedDTOList.add(new ModifiedDTO("RC10", "NV20170543", convertStringToTimestamp("2023-11-01 13:00:00"), convertStringToTimestamp("2023-11-01 13:00:00"), "Check-in", "FS10", "CS10", "Reason for modification 10", "Pending"));
    System.out.println("getAllModifiedDTOs() called!");
    return modifiedDTOList;
}


}
