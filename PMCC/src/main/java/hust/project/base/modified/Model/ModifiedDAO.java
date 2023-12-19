package hust.project.base.modified.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hust.project.base.employee_subsystem.Employee;
import hust.project.base.utils.sql_hikari.DatabaseManager;
import hust.project.base.utils.sql_hikari.SQLJavaBridge;
import hust.project.base.modified.Model.ModifiedDTO;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;
public class ModifiedDAO implements ModifiedRepository {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";

    private static String convertDateToString(Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(sqlDate);
    }
    private static String convertTimeToString(Time sqlTime) {
        if (sqlTime == null) {
            return null;
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        return timeFormat.format(sqlTime);
    }

    private static Date convertStringToDate(String date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);
        try {
            return dateFormat.parse (date);
        } catch (ParseException e) {
            e.printStackTrace ();
            return null;
        }
    }

    private String convertDateFormat(String inputDate) {
        try {
            if (inputDate == "******") {
                return null;
            }
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy:MM:dd");
            java.util.Date date = inputFormat.parse(inputDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ModifiedDTO> getAllModifiedDTO() {
        try {
            SQLJavaBridge bridge = DatabaseManager.instance ().defaulSQLJavaBridge ();
            String query = "SELECT request_id, record_id, scan_id, employee_id, date, time, time_modified, date_modified, request_reason, request_status, request_type FROM pmchamcong.modifiedattendancerecords";
            JsonArray json = bridge.query (query);
            List<ModifiedDTO> modifiedRequests = new ArrayList<> ();
            for (JsonElement element : json) {
                JsonObject obj = element.getAsJsonObject ();
                String requestId = obj.get ("request_id").getAsString ();
                String recordId = obj.has("record_id") && !obj.get("record_id").isJsonNull() ? obj.get("record_id").getAsString() : "******";
                String scanId = obj.has("scan_id") && !obj.get("scan_id").isJsonNull() ? obj.get("scan_id").getAsString() : "******";
                String employeeId = obj.get ("employee_id").getAsString ();
                String date = obj.get ("date").getAsString ();
                String time = obj.get ("time").getAsString ();
                String timeModified = obj.has ("time_modified") && !obj.get ("time_modified").isJsonNull () ? obj.get ("time_modified").getAsString () : "******";
                String dateModified = obj.has ("date_modified") && !obj.get ("date_modified").isJsonNull () ? obj.get ("date_modified").getAsString () : "******";
                String requestReason = obj.get ("request_reason").getAsString ();
                String requestStatus = obj.get ("request_status").getAsString ();
                String requestType = obj.get("request_type").getAsString ();
                ModifiedDTO modifiedDTO = new ModifiedDTO (requestId, recordId, scanId, employeeId, date, time, timeModified, dateModified, requestReason, requestStatus, requestType);
                // In thông tin của ModifiedDTO theo định dạng yêu cầu
//                System.out.println("requestId: \"" + modifiedDTO.getRequestId() + "\".");
//                System.out.println("Status: \"" + modifiedDTO.getRequestStatus() + "\".");
//                System.out.println("Reason: \"" + modifiedDTO.getRequestReason() + "\".");
//                System.out.println("----------------");
                modifiedRequests.add (modifiedDTO);
            }
            return modifiedRequests;
        } catch (Exception e) {
            e.printStackTrace ();
            return new ArrayList<> ();
        }
    }



    public void updateAcceptModifiedStatus(String requestId) {
        SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
        try {

            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String query2 = "UPDATE modifiedattendancerecords SET request_status = 'Accepted', date_modified = ?, time_modified = ? WHERE request_id = ?";
            bridge.update(query2, currentDate, currentTime, requestId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateAcceptModifiedRecordId(String requestId, String recordId) {
        SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
        try {

            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String query2 = "UPDATE modifiedattendancerecords SET request_status = 'Accepted', date_modified = ?, time_modified = ?, record_id = ? WHERE request_id = ?";
            bridge.update(query2, currentDate, currentTime, recordId ,requestId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateRejectModifiedStatus(String requestId) {
        SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
        try {

            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String query2 = "UPDATE modifiedattendancerecords SET request_status = 'Rejected', date_modified = ?, time_modified = ? WHERE request_id = ?";
            bridge.update(query2, currentDate, currentTime, requestId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<ModifiedDTO> getAllModifiedDTOs() {
    List<ModifiedDTO> modifiedDTOList = new ArrayList<>();
//    modifiedDTOList.add (new ModifiedDTO ("REQ0001", "RECORD001", "SCAN0001", "EMP001", "2023 - 12 - 16", "08:40:00" ," 09:00:00", "2023-12-16", "Dấu thời gian không chính xác", "Accepted"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0002", "RECORD002", "SCAN0002", "EMP002", "2023 - 12 - 16", "08:35:00" ,null, null, "Máy quét hỏng", "Pending"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0003", "RECORD003", "SCAN0003", "EMP003", "2023 - 12 - 16", "08:40:00" ," 09:10:00", "2023-12-16", "Quên chấm công", "Rejected"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0004", "RECORD004", "SCAN0004", "EMP004", "2023 - 12 - 16", "08:45:00" ,null, null, "Sự cố kỹ thuật", "Pending"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0005", "RECORD005", "SCAN0005", "EMP005", "2023 - 12 - 16", "08:50:00" ," 09:20:00", "2023-12-16", "Dấu thời gian không chính xác", "Accepted"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0006", "RECORD006", "SCAN0006", "EMP006", "2023 - 12 - 16", "08:55:00" ,null, null, "Nhập sai ID", "Pending"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0007", "RECORD007", "SCAN0007", "EMP007", "2023 - 12 - 16", "09:00:00" ," 09:30:00", "2023-12-16", "Dấu thời gian không chính xác", "Rejected"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0008", "RECORD008", "SCAN0008", "EMP008", "2023 - 12 - 16", "09:05:00" ,null, null, "Đến muộn", "Pending"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0009", "RECORD009", "SCAN0009", "EMP009", "2023 - 12 - 16", "09:10:00" ," 09:40:00", "2023-12-16", "Quên chấm công ra", "Accepted"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0010", "RECORD010", "SCAN0010", "EMP010", "2023 - 12 - 16", "09:15:00" ,null, null, "Máy quét không hoạt động", "Pending"));
//    modifiedDTOList.add (new ModifiedDTO ("REQ0011", "RECORD011", "SCAN0011", "EMP011", "2023 - 12 - 16", "09:20:00" ," 09:50:00", "2023-12-16", "Dấu thời gian không chính xác", "Accepted"));
//    System.out.println("getAllModifiedDTOs() called!");
    return modifiedDTOList;
    }


}
