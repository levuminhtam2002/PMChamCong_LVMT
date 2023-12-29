package hust.project.base.modified.Controller;

import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;
import hust.project.base.modified.View.RejectView;

public class RejectController {
    private RejectView view;
    private IConfirmService confirmService;

    private static RejectController instance;

    public RejectController(RejectView view, IConfirmService confirmService) {
        this.view = view;
        this.confirmService = confirmService;
        setupViewActions();
    }

    public static RejectController getInstance(RejectView view, IConfirmService confirmService) {
        if (instance == null) {
            instance = new RejectController(view, confirmService);
        }
        return instance;
    }

    private void setupViewActions() {
        view.setOnConfirmAction(this::confirmRejection);
        view.setOnCancelAction(this::cancelAcceptance);
    }

    public void confirmRejection(ModifiedRecord modifiedDTO) {
        if (modifiedDTO != null) {
            confirmService.handleRejectAction(modifiedDTO);
            view.close();
            System.out.println("Từ chối thành công");
        }
    }

    public void cancelAcceptance(ModifiedRecord modifiedDTO) {
        if (modifiedDTO != null) {
            System.out.println("Hủy ");
            view.close();
        }
    }
}
