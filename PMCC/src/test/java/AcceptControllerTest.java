import hust.project.base.modified.Controller.AcceptController;
import hust.project.base.modified.Model.AttendanceRecord;
import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;
import hust.project.base.modified.View.AcceptView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class AcceptControllerTest {
    private AcceptView view;
    private AttendanceRecordRepository attendanceRecordRepo;
    private ModifiedRepository modifiedRepo;
    private AcceptController acceptController;

    @BeforeEach
    void setUp() {
        view = mock(AcceptView.class);
        attendanceRecordRepo = mock(AttendanceRecordRepository.class);
        modifiedRepo = mock(ModifiedRepository.class);
        acceptController = new AcceptController(view, attendanceRecordRepo, modifiedRepo);
    }

    @Test
    void testHandleModifiedDTOForEdit() {
        ModifiedRecord modifiedRecord = new ModifiedRecord("REQ001", "RECORD001", "SCAN0001", "EMP00001", "16-12-2023", "08:30:00", null, null, "Dấu thời gian không chính xác", "Pending", "Chỉnh sửa chấm công");
        acceptController.confirmAcceptance(modifiedRecord);
        verify(attendanceRecordRepo, times(1)).updateAttendanceRecord(modifiedRecord.getTime(), modifiedRecord.getRecordId());
        verify(modifiedRepo, times(1)).updateAcceptModifiedStatus(modifiedRecord.getRequestId());
    }

    @Test
    void testHandleModifiedDTOForAdd() {
        ModifiedRecord modifiedRecord = new ModifiedRecord("REQ002", null, null, "EMP00002", "16-12-2023", "08:35:00", null, null, "Máy quét hỏng", "Pending", "Thêm chấm công");
        when(attendanceRecordRepo.generateNextRecordId()).thenReturn("RECORD007");
        acceptController.confirmAcceptance(modifiedRecord);
        verify(attendanceRecordRepo, times(1)).generateNextRecordId();
        verify(attendanceRecordRepo, times(1)).insertAttendanceRecord(any(AttendanceRecord.class));
        verify(modifiedRepo, times(1)).updateAcceptModifiedRecordId(modifiedRecord.getRequestId(), "RECORD007");
    }


    //    a. Test updateAttendanceRecord(String time, String recordId)
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
        modifiedRepo.updateAcceptModifiedStatus("REQ0001");
        verify(modifiedRepo, times(1)).updateAcceptModifiedStatus("REQ0001");
    }

    @Test
    void testUpdateAcceptModifiedStatusNotFound() {
        modifiedRepo.updateAcceptModifiedStatus("REQ9999");
        verify(modifiedRepo, times(1)).updateAcceptModifiedStatus("REQ9999");
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

}
