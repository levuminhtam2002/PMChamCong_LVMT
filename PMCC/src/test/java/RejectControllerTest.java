import hust.project.base.modified.Controller.IConfirmService;
import hust.project.base.modified.Controller.RejectController;
import hust.project.base.modified.Model.ModifiedRecord;

import hust.project.base.modified.View.RejectView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class RejectControllerTest {
    private RejectView view;
    private IConfirmService confirmService;
    private RejectController rejectController;

    @BeforeEach
    void setUp() {
        view = mock (RejectView.class);
        confirmService = mock (IConfirmService.class);
        rejectController = new RejectController (view, confirmService);
    }

    @Test
    void testConfirmRejection() {
        ModifiedRecord modifiedDTO = mock (ModifiedRecord.class);
        rejectController.confirmRejection (modifiedDTO);
        verify (confirmService).handleRejectAction (modifiedDTO);
        verify (view).close ();
    }

    @Test
    void testCancelAcceptance() {
        ModifiedRecord modifiedDTO = mock (ModifiedRecord.class);
        rejectController.cancelAcceptance (modifiedDTO);
        verify (view).close ();
    }

}