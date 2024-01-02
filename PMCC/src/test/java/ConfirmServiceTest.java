import hust.project.base.modified.Service.ConfirmService;
import hust.project.base.modified.Service.IConfirmService;
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
        AttendanceRecord mockRecord = mock(AttendanceRecord.class);
        when(mockRecord.getTime()).thenReturn("08:30:00");
        when(attendanceRecordRepo.getAttendanceRecordByRecordId("RECORD001")).thenReturn(mockRecord);
        ModifiedRecord modifiedDTO = new ModifiedRecord("REQ001", "RECORD001", "SCAN0001", "EMP00001", null,"16-12-2023", "08:30:00", null, null, "Dấu thời gian không chính xác", "Pending", "Chỉnh sửa chấm công");
        confirmService.handleAcceptAction(modifiedDTO);
        verify(attendanceRecordRepo, times(1)).updateAttendanceRecord(modifiedDTO.getTime(), modifiedDTO.getRecordId());
        verify(modifiedRepo, times(1)).updateAcceptModifiedStatus(modifiedDTO.getRequestId(),"08:30:00");
    }

    @Test
    void testHandleAcceptActionForAdd() {
        ModifiedRecord modifiedDTO = new ModifiedRecord("REQ002", null, null, "EMP00002", null,"16-12-2023", "08:35:00", null, null, "Máy quét hỏng", "Pending", "Thêm chấm công");
        when(attendanceRecordRepo.generateNextRecordId()).thenReturn("RECORD007");
        confirmService.handleAcceptAction(modifiedDTO);
        verify(attendanceRecordRepo, times(1)).generateNextRecordId();
        verify(attendanceRecordRepo, times(1)).insertAttendanceRecord(any(AttendanceRecord.class));
        verify(modifiedRepo, times(1)).updateAcceptModifiedRecordId(modifiedDTO.getRequestId(), "RECORD007");
    }

    @Test
    void testHandleRejectAction() {
        ModifiedRecord modifiedDTO = new ModifiedRecord("REQ0003", "RECORD003", "SCAN0003", "EMP00003", null,"17-12-2023", "09:00:00", null, null, "Forgot to clock in", "Pending", "Reject");
        confirmService.handleRejectAction(modifiedDTO);
        verify(modifiedRepo, times(1)).updateRejectModifiedStatus(modifiedDTO.getRequestId());
    }
    @Test
    void testUpdateAttendanceRecordSuccess() {
        attendanceRecordRepo.updateAttendanceRecord("09:00:00", "RECORD001");
        verify(attendanceRecordRepo, times(1)).updateAttendanceRecord("09:00:00", "RECORD001");
    }

    @Test
    void testUpdateAttendanceRecordNotFound() {
        attendanceRecordRepo.updateAttendanceRecord("09:00:00", "RECORD999");
        verify(attendanceRecordRepo, times(1)).updateAttendanceRecord("09:00:00", "RECORD999");
    }
//    b. Test updateAcceptModifiedStatus(String requestId)

    @Test
    void testUpdateAcceptModifiedStatusSuccess() {
        modifiedRepo.updateAcceptModifiedStatus("REQ0001","08:30:01");
        verify(modifiedRepo, times(1)).updateAcceptModifiedStatus("REQ0001","08:30:01");
    }

    @Test
    void testUpdateAcceptModifiedStatusNotFound() {
        modifiedRepo.updateAcceptModifiedStatus("REQ9999","08:30:01");
        verify(modifiedRepo, times(1)).updateAcceptModifiedStatus("REQ9999","08:30:01");
    }
    //    Test updateAcceptModifiedRecordId(String requestId, String recordId)
    @Test
    void testUpdateAcceptModifiedRecordIdSuccess() {
        modifiedRepo.updateAcceptModifiedRecordId("REQ0011", "RECORD007");
        verify(modifiedRepo, times(1)).updateAcceptModifiedRecordId("REQ0011", "RECORD007");
    }

    @Test
    void testUpdateAcceptModifiedRecordIdNotFound() {
        modifiedRepo.updateAcceptModifiedRecordId("REQ9999", "RECORD007");
        verify(modifiedRepo, times(1)).updateAcceptModifiedRecordId("REQ9999", "RECORD007");
    }

    //    d. Test updateRejectModifiedStatus(String requestId)
    @Test
    void testUpdateRejectModifiedStatusSuccess() {
        modifiedRepo.updateRejectModifiedStatus("REQ0001");
        verify(modifiedRepo, times(1)).updateRejectModifiedStatus("REQ0001");
    }

    @Test
    void testUpdateRejectModifiedStatusNotFound() {
        modifiedRepo.updateRejectModifiedStatus("REQ9999");
        verify(modifiedRepo, times(1)).updateRejectModifiedStatus("REQ9999");
    }

    @Test
    void testGenerateNextRecordIdWithData() {
        when(attendanceRecordRepo.generateNextRecordId()).thenReturn("RECORD024");
        String nextId = attendanceRecordRepo.generateNextRecordId();
        assertEquals("RECORD024", nextId);
        verify(attendanceRecordRepo, times(1)).generateNextRecordId();
    }

    @Test
    void testGenerateNextRecordIdWithNoData() {
        when(attendanceRecordRepo.generateNextRecordId()).thenReturn("RECORD001");
        String nextId = attendanceRecordRepo.generateNextRecordId();
        assertEquals("RECORD001", nextId);
        verify(attendanceRecordRepo, times(1)).generateNextRecordId();
    }

    // Thêm các test cases cho insertAttendanceRecord
    @Test
    void testInsertAttendanceRecordSuccess() {
        ModifiedRecord modifiedRecord = new ModifiedRecord("REQ001", "RECORD001", "SCAN0001", "EMP001", null,"16-12-2023", "08:00:00", null, null, "Dấu thời gian không chính xác", "Pending", "Thêm chấm công");
        when(attendanceRecordRepo.generateNextRecordId()).thenReturn("RECORD001");
        confirmService.handleAcceptAction (modifiedRecord);
        verify(attendanceRecordRepo, times(1)).insertAttendanceRecord(any(AttendanceRecord.class));
    }
    @Test
    void testInsertAttendanceRecordWithExistingId() {
        ModifiedRecord modifiedRecord = new ModifiedRecord("REQ002", null, null, "EMP002", null,"16-12-2023", "09:00:00", null, null, "Máy quét hỏng", "Pending", "Thêm chấm công");
        when(attendanceRecordRepo.generateNextRecordId()).thenReturn("RECORD001");
        confirmService.handleAcceptAction (modifiedRecord);
        verify(attendanceRecordRepo, times(1)).insertAttendanceRecord(any(AttendanceRecord.class));
    }
}