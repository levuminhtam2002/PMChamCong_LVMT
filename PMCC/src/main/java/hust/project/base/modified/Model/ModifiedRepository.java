package hust.project.base.modified.Model;

import java.util.List;

public interface ModifiedRepository {
    List<ModifiedDTO> getAllModifiedDTOs() ;

    void updateAcceptModifiedStatus(String requestIdO);

    List<ModifiedDTO> getAllModifiedDTO();

    void updateRejectModifiedStatus(String requestId);

}
