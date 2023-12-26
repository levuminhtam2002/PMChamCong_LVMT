package hust.project.base.modified.Controller;

import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;
import hust.project.base.modified.View.RejectView;

public class RejectController {
    private RejectView view;
    private ModifiedRepository modifiedRepo;

    private static RejectController instance;

    private RejectController(RejectView view, ModifiedRepository modifiedRepo) {
        this.view = view;
        this.modifiedRepo = modifiedRepo;
        setupViewActions();
    }

    public static RejectController getInstance(RejectView view, ModifiedRepository modifiedRepo) {
        if (instance == null) {
            instance = new RejectController(view, modifiedRepo);
        }
        return instance;
    }

    private void setupViewActions() {
        view.setOnConfirmAction(this::confirmRejection);
        view.setOnCancelAction(this::cancelAcceptance);
    }

    private void confirmRejection(ModifiedRecord modifiedDTO) {
        if (modifiedDTO != null) {
            modifiedRepo.updateRejectModifiedStatus(modifiedDTO.getRequestId());
            System.out.println("Từ chối thành công");
            view.close();
        }
    }

    private void cancelAcceptance(ModifiedRecord modifiedDTO) {
        if (modifiedDTO != null) {
            System.out.println("Hủy ");
            view.close();
        }
    }

}
