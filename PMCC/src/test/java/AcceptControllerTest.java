import hust.project.base.modified.Accept.AcceptController;
import hust.project.base.modified.Service.IConfirmService;
import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Accept.AcceptView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        verify(confirmService).handleModifiedDTO (modifiedDTO);
        verify(view).close();
    }

    @Test
    void testCancelAcceptance() {
        ModifiedRecord modifiedDTO = mock(ModifiedRecord.class);
        acceptController.cancelAcceptance(modifiedDTO);
        verify(view).close();
    }
}