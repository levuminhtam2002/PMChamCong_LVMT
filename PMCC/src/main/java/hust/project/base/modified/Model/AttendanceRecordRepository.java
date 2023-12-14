package hust.project.base.modified.Model;

import java.util.List;

public interface AttendanceRecordRepository {
    List<ModifiedDTO> getAllModifiedDTOs() ;
    ModifiedDTO getModifiedByRecordId(String recordId);
}
