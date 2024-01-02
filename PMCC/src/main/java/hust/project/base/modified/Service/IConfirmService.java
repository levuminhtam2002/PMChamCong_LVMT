package hust.project.base.modified.Service;

import hust.project.base.modified.Model.ModifiedRecord;

public interface IConfirmService {
    void handleModifiedDTO(ModifiedRecord modifiedDTO);
    void handleRejectAction(ModifiedRecord modifiedDTO);

}
