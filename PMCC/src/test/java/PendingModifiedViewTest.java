
import hust.project.base.modified.View.PendingModifiedView;
import hust.project.base.modified.Model.ModifiedRecord;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//test case đã được thiết kế để kiểm tra:
//
//        - Sự tồn tại của `TableView` và nội dung của nó.
//        - Sự tồn tại và nội dung của `Label`.
//        - Tên của một cột cụ thể trong `TableView`.
public class PendingModifiedViewTest extends ApplicationTest {
    private PendingModifiedView pendingModifiedView;
    private TableView<ModifiedRecord> table;

    @BeforeEach
    void setUp() {
        // Khởi tạo PendingModifiedView
        pendingModifiedView = new PendingModifiedView();
        table = (TableView<ModifiedRecord>) pendingModifiedView.getChildren().get(1);
    }

    @Test
    void testTableNotNull() {
        assertNotNull(table, "Table should not be null");
    }

    @Test
    void testTableColumnsCount() {
        int expectedColumns = 7; // Số lượng cột dự kiến
        assertEquals(expectedColumns, table.getColumns().size(), "Table should have " + expectedColumns + " columns");
    }

    @Test
    void testLabelNotNull() {
        Label label = (Label) pendingModifiedView.getChildren().get(0);
        assertNotNull(label, "Label should not be null");
    }

    @Test
    void testLabelContent() {
        Label label = (Label) pendingModifiedView.getChildren().get(0);
        assertEquals("Danh sách các yêu cầu đang chờ", label.getText(), "Label text should match");
    }

    // Kiểm tra cột "Nhân viên"
    @Test
    void testEmployeeColumn() {
        String expectedColumnName = "Nhân viên";
        TableColumn<ModifiedRecord, ?> employeeColumn = table.getColumns().get(1);
        assertEquals(expectedColumnName, employeeColumn.getText(), "Column name should match");
    }
    @Test
    void testTimeCheckinColumn() {
        String expectedColumnName = "Giờ checkin";
        TableColumn<ModifiedRecord, ?> timeCheckinColumn = table.getColumns().get(3);
        assertEquals(expectedColumnName, timeCheckinColumn.getText(), "Column name should match");

    }

    @Test
    void testUpdateTable() {
        // Tạo dữ liệu giả lập cho newData
        List<ModifiedRecord> newData = new ArrayList<> ();
        newData.add(new ModifiedRecord ("REQ0007", "RECORD007", "SCAN0007", "EMP007", "16-12-2023", "09:00:01", null, null, "Dấu thời gian không chính xác", "Pending", "Chỉnh sửa chấm công"));
        newData.add(new ModifiedRecord ("REQ0008", "RECORD008", "SCAN0008", "EMP008", "16-12-2023", "09:05:01", null, null, "Đến muộn", "Pending", "Chỉnh sửa chấm công"));
        // Cập nhật bảng với dữ liệu mới
        pendingModifiedView.updateTable(newData);

        // Kiểm tra xem bảng có cùng số lượng mục như newData không
        assertEquals(newData.size(), table.getItems().size(), "Table should contain the same number of items as the new data list");

        for (int i = 0; i < newData.size(); i++) {
            ModifiedRecord expected = newData.get(i);
            ModifiedRecord actual = table.getItems().get(i);
            assertEquals(expected.getRequestId(), actual.getRequestId(), "Request ID should match");
            assertEquals(expected.getEmployeeId(), actual.getEmployeeId(), "Employee ID should match");
            // Kiểm tra các trường dữ liệu khác theo cách tương tự
        }
    }
}
