package hust.project.base.modified.Controller;

import hust.project.base.modified.Model.*;
import hust.project.base.modified.View.AcceptView;
import javafx.application.Platform;

public class AcceptController {
    private AcceptView view;
    private IConfirmService confirmService;

    private static AcceptController instance;

    public AcceptController(AcceptView view, IConfirmService confirmService) {
        this.view = view;
        this.confirmService = confirmService;
        setupViewActions();
    }

    public static AcceptController getInstance(AcceptView view, IConfirmService confirmService) {
        if (instance == null) {
            instance = new AcceptController(view, confirmService);
        }
        return instance;
    }

    private void setupViewActions() {
        view.setOnConfirmAction(this::confirmAcceptance);
        view.setOnCancelAction(this::cancelAcceptance);
    }

    public void confirmAcceptance(ModifiedRecord modifiedDTO) {
        if (modifiedDTO != null) {
            confirmService.handleAcceptAction(modifiedDTO);
            view.close();
            System.out.println("Chấp nhận yêu cầu thành công");
        }
    }

    public void cancelAcceptance(ModifiedRecord modifiedDTO) {
        if (modifiedDTO != null) {
            System.out.println("Hủy yêu cầu thành công");
            view.close();
        }
    }
}
