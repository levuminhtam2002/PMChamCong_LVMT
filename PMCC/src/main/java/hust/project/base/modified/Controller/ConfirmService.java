package hust.project.base.modified.Controller;

import hust.project.base.modified.Model.AttendanceRecord;
import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;

public class ConfirmService implements IConfirmService{
    private AttendanceRecordRepository attendanceRecordRepo;
    private ModifiedRepository modifiedRepo;

    public ConfirmService(AttendanceRecordRepository attendanceRecordRepo, ModifiedRepository modifiedRepo) {
        this.attendanceRecordRepo = attendanceRecordRepo;
        this.modifiedRepo = modifiedRepo;
    }

    public void handleAcceptAction(ModifiedRecord modifiedDTO) {
        if ("Chỉnh sửa chấm công".equals(modifiedDTO.getRequestType())) {
            updateAttendanceRecord(modifiedDTO);
        } else if ("Thêm chấm công".equals(modifiedDTO.getRequestType())) {
            addAttendanceRecord(modifiedDTO);
        }
    }
    public void handleRejectAction(ModifiedRecord modifiedDTO) {
        if (modifiedDTO != null) {
            modifiedRepo.updateRejectModifiedStatus(modifiedDTO.getRequestId());
            System.out.println("Từ chối yêu cầu thành công");
        }
    }

    private void updateAttendanceRecord(ModifiedRecord modifiedDTO) {
        attendanceRecordRepo.updateAttendanceRecord(modifiedDTO.getTime(), modifiedDTO.getRecordId());
        modifiedRepo.updateAcceptModifiedStatus(modifiedDTO.getRequestId());
        System.out.println("Chấp nhận yêu cầu chỉnh sửa chấm công thành công");
    }

    private void addAttendanceRecord(ModifiedRecord modifiedDTO) {
        String newRecordId = attendanceRecordRepo.generateNextRecordId();
        AttendanceRecord attendanceRecordDTO = new AttendanceRecord();
        attendanceRecordDTO.setRecordId(newRecordId);
        attendanceRecordDTO.setEmployeeId(modifiedDTO.getEmployeeId());
        attendanceRecordDTO.setDate(modifiedDTO.getDate());
        attendanceRecordDTO.setTime(modifiedDTO.getTime());
        attendanceRecordDTO.setFingerscannerId("0");
        attendanceRecordRepo.insertAttendanceRecord(attendanceRecordDTO);
        modifiedRepo.updateAcceptModifiedRecordId(modifiedDTO.getRequestId(), newRecordId);
        System.out.println("Chấp nhận yêu cầu thêm chấm công thành công");
    }

}
