package hust.project.base.modified.Controller;

import hust.project.base.modified.Model.ModifiedRecord;

public interface IConfirmService {
    void handleAcceptAction(ModifiedRecord modifiedDTO);
    void handleRejectAction(ModifiedRecord modifiedDTO);

}
