import hust.project.base.modified.Controller.ConfirmService;
import hust.project.base.modified.Controller.IConfirmService;
import hust.project.base.modified.Model.AttendanceRecord;
import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ConfirmServiceTest {
    private AttendanceRecordRepository attendanceRecordRepo;
    private ModifiedRepository modifiedRepo;
    private IConfirmService confirmService;

    @BeforeEach
    void setUp() {
        attendanceRecordRepo = mock(AttendanceRecordRepository.class);
        modifiedRepo = mock(ModifiedRepository.class);
        confirmService = new ConfirmService (attendanceRecordRepo, modifiedRepo);
    }

    @Test
    void testHandleAcceptActionForEdit() {
        ModifiedRecord modifiedDTO = new ModifiedRecord("REQ001", "RECORD001", "SCAN0001", "EMP00001", "16-12-2023", "08:30:00", null, null, "Dấu thời gian không chính xác", "Pending", "Chỉnh sửa chấm công");
        confirmService.handleAcceptAction(modifiedDTO);
        verify(attendanceRecordRepo, times(1)).updateAttendanceRecord(modifiedDTO.getTime(), modifiedDTO.getRecordId());
        verify(modifiedRepo, times(1)).updateAcceptModifiedStatus(modifiedDTO.getRequestId());
    }

    @Test
    void testHandleAcceptActionForAdd() {
        ModifiedRecord modifiedDTO = new ModifiedRecord("REQ002", null, null, "EMP00002", "16-12-2023", "08:35:00", null, null, "Máy quét hỏng", "Pending", "Thêm chấm công");
        when(attendanceRecordRepo.generateNextRecordId()).thenReturn("RECORD007");
        confirmService.handleAcceptAction(modifiedDTO);
        verify(attendanceRecordRepo, times(1)).generateNextRecordId();
        verify(attendanceRecordRepo, times(1)).insertAttendanceRecord(any(AttendanceRecord.class));
        verify(modifiedRepo, times(1)).updateAcceptModifiedRecordId(modifiedDTO.getRequestId(), "RECORD007");
    }

    @Test
    void testHandleRejectAction() {
        ModifiedRecord modifiedDTO = new ModifiedRecord("REQ0003", "RECORD003", "SCAN0003", "EMP00003", "17-12-2023", "09:00:00", null, null, "Forgot to clock in", "Pending", "Reject");
        confirmService.handleRejectAction(modifiedDTO);
        verify(modifiedRepo, times(1)).updateRejectModifiedStatus(modifiedDTO.getRequestId());
    }
}