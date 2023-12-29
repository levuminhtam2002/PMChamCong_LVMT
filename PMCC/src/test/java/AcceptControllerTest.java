import hust.project.base.modified.Controller.AcceptController;
import hust.project.base.modified.Controller.IConfirmService;
import hust.project.base.modified.Model.AttendanceRecord;
import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;
import hust.project.base.modified.View.AcceptView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class AcceptControllerTest {
    private AcceptView view;
    private IConfirmService confirmService;
    private AcceptController acceptController;

    @BeforeEach
    void setUp() {
        view = mock(AcceptView.class);
        confirmService = mock(IConfirmService.class);
        acceptController = new AcceptController(view, confirmService);
    }

    @Test
    void testConfirmAcceptance() {
        ModifiedRecord modifiedDTO = mock(ModifiedRecord.class);
        acceptController.confirmAcceptance(modifiedDTO);
        verify(confirmService).handleAcceptAction(modifiedDTO);
        verify(view).close();
    }

    @Test
    void testCancelAcceptance() {
        ModifiedRecord modifiedDTO = mock(ModifiedRecord.class);
        acceptController.cancelAcceptance(modifiedDTO);
        verify(view).close();
    }
}