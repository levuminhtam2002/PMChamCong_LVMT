package hust.project.base.modified.modified;

import hust.project.base.modified.Accept.AcceptController;
import hust.project.base.modified.Service.ConfirmService;
import hust.project.base.modified.Service.IConfirmService;
import hust.project.base.modified.reject.RejectController;
import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;
import hust.project.base.modified.Accept.AcceptView;
import hust.project.base.modified.reject.RejectView;


public class ModifiedController{
    private ModifiedView view;
    private ModifiedRepository modifiedRepository;
    private AttendanceRecordRepository attendanceRecordRepository;

    private IConfirmService confirmService;

    private static ModifiedController instance;

    public static ModifiedController getInstance(ModifiedView view, ModifiedRepository modifiedRepository, AttendanceRecordRepository attendanceRecordRepository) {
        if (instance == null) {
            instance = new ModifiedController(view, modifiedRepository, attendanceRecordRepository);
        }
        return instance;
    }


    private ModifiedController(ModifiedView view, ModifiedRepository modifiedRepository, AttendanceRecordRepository attendanceRecordRepository) {
        this.view = view;
        this.modifiedRepository = modifiedRepository;
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.confirmService = new ConfirmService (attendanceRecordRepository,modifiedRepository);
        setupViewActions();
    }
    public void setupViewActions() {
        view.setOnAcceptAction(this::handleAccept);
        view.setOnRejectAction(this::handleReject);
        view.setOnCancelAction(this::handleCancel);
    }
    private void handleAccept(ModifiedRecord data) {
        if (data != null) {
            AcceptView acceptView = AcceptView.instance();
            view.close();
            AcceptController acceptController = AcceptController.getInstance(acceptView, confirmService);
            acceptView.display(data);
        }
    }

    private void handleReject(ModifiedRecord data) {
        if (data != null) {
            RejectView rejectView = RejectView.instance();
            view.close();
            RejectController rejectController = RejectController.getInstance(rejectView, confirmService);
            rejectView.display(data);
        }
    }

    private void handleCancel (ModifiedRecord data){
            view.close ();
    }

}
