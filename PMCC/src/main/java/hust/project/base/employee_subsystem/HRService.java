package hust.project.base.employee_subsystem;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hust.project.base.summary_logs.Summary;
import hust.project.base.utils.sql_hikari.DatabaseManager;
import hust.project.base.utils.sql_hikari.SQLJavaBridge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HRService {
    public Employee getEmployee(int id){
        try{
            SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
            String query = "SELECT id,name,code FROM employee WHERE id = ?";
            JsonObject json = bridge.queryOne(query, id);
            String name = json.get("name").getAsString();
            String code = json.get("code").getAsString();
            return new Employee(id, name, code);
        }catch (Exception e){
            return new Employee(0, "0", "0");
        }
    }
////    public department
//    public Department getDepartment(int id){
//        try{
//            SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
//            String query = "SELECT id,name FROM department WHERE id = ?";
//            JsonObject json = bridge.queryOne(query, id);
//            String name = json.get("name").getAsString();
//            return new Department(id, name);
//        }catch (Exception e){
//            return new Department(0, "0");
//        }
//    }
    public List<String> getListDepartments(int adminID){
        try{
            SQLJavaBridge bridge = DatabaseManager.instance().defaulSQLJavaBridge();
            String query = "SELECT d.name FROM department d JOIN employee_department ed ON d.id = ed.department_id WHERE ed.employee_id = ? AND (ed.position = 3 OR ed.position = 4)";
            JsonArray array = bridge.query(query, adminID);
//            List<String> result = array.asList().stream().map(e -> e.getAsJsonObject().get("name").getAsString()).toList();
            List<String> result = new ArrayList<>();
            for(int i = 0; i < array.size(); i++){
                JsonObject json = array.get(i).getAsJsonObject();
                result.add(json.get("name").getAsString());
            }
            System.out.println("list department: " + result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<Summary> getSummary(){
        try{
            return null;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
