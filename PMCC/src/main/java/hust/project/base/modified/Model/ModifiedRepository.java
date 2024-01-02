package hust.project.base.modified.Model;

import java.util.List;

public interface ModifiedRepository {

    void updateAcceptModifiedStatus(String requestId, String preTime);

    List<ModifiedRecord> getAllModifiedDTO();

    void updateRejectModifiedStatus(String requestId);

    void updateAcceptModifiedRecordId(String requestId, String recordId);

}
