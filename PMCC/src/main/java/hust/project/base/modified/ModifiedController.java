package hust.project.base.modified;

import hust.project.base.employee_subsystem.HRService;
import hust.project.base.modified.View.PendingModifiedView;
import hust.project.base.modified.Model.ModifiedDTO;
import hust.project.base.modified.Model.AttendanceRecordRepository;

public class ModifiedController implements IModified {
    private PendingModifiedView pendingModifiedView;
//    private TableView<ModifiedDTO> requestTable;

    public ModifiedController(PendingModifiedView view) {
        this.pendingModifiedView = view;
        HRService service = new HRService();
//        pendingModifiedView.setDepartment(service.getDepartment());
    }


}
