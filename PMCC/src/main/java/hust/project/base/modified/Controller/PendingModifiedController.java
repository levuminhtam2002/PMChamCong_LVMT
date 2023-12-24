package hust.project.base.modified.Controller;

import hust.project.base.modified.Model.*;
import hust.project.base.modified.View.ModifiedView;
import hust.project.base.modified.View.PendingModifiedView;

import java.util.List;

public class PendingModifiedController {
    private PendingModifiedView view;
    private ModifiedRepository repository;

    public PendingModifiedController(PendingModifiedView view, ModifiedRepository repository) {
        this.view = view;
        this.repository = repository;
        loadPendingRequests();
        view.createActionColumn(this::displayModifiedView);
    }
    private void loadPendingRequests() {
        if (this.repository == null) {
            System.out.println ("modifiedRepository is null");
        } else {
            List<ModifiedRecord> pendingRequests = repository.getAllModifiedDTO();
            view.updateTable (pendingRequests);
        }
    }
    private void displayModifiedView(ModifiedRecord data) {
        ModifiedView modifiedView = new ModifiedView();
        AttendanceRecordRepository attendanceRecordRepository = new AttendanceRecordEntity ();
        ModifiedController modifiedController = new ModifiedController(modifiedView, repository, attendanceRecordRepository);
        modifiedView.display(data);
    }
}
