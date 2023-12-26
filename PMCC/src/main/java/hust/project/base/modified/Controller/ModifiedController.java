package hust.project.base.modified.Controller;

import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedRecord;
import hust.project.base.modified.Model.ModifiedRepository;
import hust.project.base.modified.View.AcceptView;
import hust.project.base.modified.View.ModifiedView;
import hust.project.base.modified.View.RejectView;


public class ModifiedController{
    private ModifiedView view;
    private ModifiedRepository modifiedRepository;
    private AttendanceRecordRepository attendanceRecordRepository;

//    private static AcceptController instance;

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
        setupViewActions();
    }
    public void setupViewActions() {
        view.setOnAcceptAction(this::handleAccept);
        view.setOnRejectAction(this::handleReject);
        view.setOnCancelAction(this::handleCancel);
    }
    private void handleAccept(ModifiedRecord data) {
        if (data != null) {
            // Using Singleton pattern
            AcceptView acceptView = AcceptView.instance();
            view.close();
            AcceptController acceptController = AcceptController.getInstance(acceptView, attendanceRecordRepository, modifiedRepository);
            acceptView.display(data);
        }
    }

    private void handleReject(ModifiedRecord data) {
        if (data != null) {
            RejectView rejectView = RejectView.instance();
            view.close();
            RejectController rejectController = RejectController.getInstance(rejectView, modifiedRepository);
            rejectView.display(data);
        }
    }

    private void handleCancel (ModifiedRecord data){
            view.close ();
    }

}
