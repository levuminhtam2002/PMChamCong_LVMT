package hust.project.base.modified.Model;

import java.util.List;

public interface ModifiedRepository {
    List<ModifiedDTO> getAllModifiedDTOs() ;

    void updateModifiedStatus();

    List<ModifiedDTO> getAllModifiedDTO();

}
