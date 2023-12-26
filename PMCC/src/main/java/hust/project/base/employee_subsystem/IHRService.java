package hust.project.base.employee_subsystem;

import java.util.List;

public interface IHRService {
    public Employee getEmployee(String employeeId);
    public Department getDepartment(String departmentId);

}
