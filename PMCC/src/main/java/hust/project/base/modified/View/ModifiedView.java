
package hust.project.base.modified.View;

import hust.project.base.modified.Model.ModifiedDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static hust.project.base.constants.MetricsConstants.APPLICATION_HEIGHT;
import static hust.project.base.constants.MetricsConstants.APPLICATION_WIDTH;

public class ModifiedView {

    private TextField nameField;
    private TextField employeeIdField;
    private TextField approverField;
    private TextField dateField;
    private TextField noteField;
    private TextField requestTypeField;
    private TextField requestIdField;
    private TextField originalRecordField;
    private TextField modifiedRecordField;
    private TextField statusField;
    private TextField creatorField;
    private TextField createdDateField;
    private TextField scannerIdField;

    public void display(ModifiedDTO data) {
        Stage window = new Stage();
        Image icon = new Image(getClass().getResourceAsStream("/image/icon.png"));
        window.getIcons().add(icon);
        window.setTitle("Chi tiết yêu cầu");
        window.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: white; -fx-padding: 30; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5;");
        layout.setAlignment(Pos.TOP_CENTER);

        Label titleLabel = new Label("Chi tiết");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        layout.getChildren().add(titleLabel);

        GridPane formLayout = new GridPane();
        formLayout.setVgap(10);
        formLayout.setHgap(10);
        initializeFormFields();
        addFormFieldsToGridPane(formLayout);
        ComboBox<String> requestTypeComboBox = new ComboBox<>();
        requestTypeComboBox.setItems(FXCollections.observableArrayList(
                "Chỉnh sửa chấm công", // Edit Attendance
                "Thêm chấm công",      // Add Attendance
                "Xóa chấm công"        // Delete Attendance
        ));

        requestTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if ("Thêm chấm công".equals(newVal)) {
                clearAndEnableTextFields();
                enableTextColor();
            } else if ("Chỉnh sửa chấm công".equals(newVal)) {
                disableTextFields();
                disableTextColor();
            }
        });
        formLayout.add(requestTypeComboBox, 3, 0);
        formLayout.add(new Label("Loại yêu cầu"), 2, 0);
        formLayout.add(new Label("Họ và tên"), 0, 0);
        formLayout.add(new Label("Mã nhân viên"), 0, 1);
        formLayout.add(new Label("Người duyệt"), 0, 2);
        formLayout.add(new Label("Ngày"), 0, 3);
        formLayout.add(new Label("Ghi chú"), 0, 4);
        formLayout.add(new Label("Mã yêu cầu"), 2, 1);
        formLayout.add(new Label("Bản ghi gốc"), 2, 2);
        formLayout.add(new Label("Sửa thành"), 2, 3);
        formLayout.add(new Label("Trạng thái"), 2, 4);
        formLayout.add(new Label("Người tạo"), 2, 5);
        formLayout.add(new Label("Ngày tạo"), 2, 6);
        formLayout.add(new Label("Id máy quét"), 0, 5);

        layout.getChildren().add(formLayout);

        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        Button acceptButton = new Button("CHẤP NHẬN");
        acceptButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        Button rejectButton = new Button("TỪ CHỐI");
        rejectButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        Button cancelButton = new Button("HỦY BỎ");
        cancelButton.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
        buttonLayout.getChildren().addAll(acceptButton, rejectButton, cancelButton);
        layout.getChildren().add(buttonLayout);

        acceptButton.setOnAction(e -> handleAccept());
        rejectButton.setOnAction(e -> handleReject());
        cancelButton.setOnAction(e -> window.close());

        Scene scene = new Scene(layout, APPLICATION_WIDTH * 0.6, APPLICATION_HEIGHT * 0.6);
        window.setScene(scene);
        window.showAndWait();
    }

    private void initializeFormFields() {
        nameField = new TextField("Nguyễn Văn A");
        employeeIdField = new TextField("NV001");
        approverField = new TextField("Nguyễn Văn B");
        dateField = new TextField("2023-11-01");
        noteField = new TextField("Ghi chú");
        requestTypeField = new TextField("Chỉnh sửa chấm công");
        requestIdField = new TextField("RC001");
        originalRecordField = new TextField("Check-in: 8:45:00");
        modifiedRecordField = new TextField("Check-in: 8:00:00");
        statusField = new TextField("Đang chờ");
        creatorField = new TextField("Nguyễn Văn A");
        createdDateField = new TextField("2023-11-01 17:30:00");
        scannerIdField = new TextField("FS01");
        disableTextFields();
    }

    private void addFormFieldsToGridPane(GridPane formLayout) {
        formLayout.add(nameField, 1, 0);
        formLayout.add(employeeIdField, 1, 1);
        formLayout.add(approverField, 1, 2);
        formLayout.add(dateField, 1, 3);
        formLayout.add(noteField, 1, 4);
        formLayout.add(requestTypeField, 3, 0);
        formLayout.add(requestIdField, 3, 1);
        formLayout.add(originalRecordField, 3, 2);
        formLayout.add(modifiedRecordField, 3, 3);
        formLayout.add(statusField, 3, 4);
        formLayout.add(creatorField, 3, 5);
        formLayout.add(createdDateField, 3, 6);
        formLayout.add(scannerIdField, 1, 5);
    }

    private void clearAndEnableTextFields() {
        nameField.clear();
        nameField.setEditable(true);
        employeeIdField.clear();
        employeeIdField.setEditable(true);
        approverField.clear();
        approverField.setEditable(true);
        dateField.clear();
        dateField.setEditable(true);
        noteField.clear();
        noteField.setEditable(true);
        requestIdField.clear();
        requestIdField.setEditable(true);
        originalRecordField.clear();
        originalRecordField.setEditable(true);
        modifiedRecordField.clear();
        modifiedRecordField.setEditable(true);
        statusField.clear();
        statusField.setEditable(true);
        creatorField.clear();
        creatorField.setEditable(true);
        createdDateField.clear();
        createdDateField.setEditable(true);
        scannerIdField.clear();
        scannerIdField.setEditable(true);
    }

    private void disableTextFields() {
        nameField.setEditable(false);
        employeeIdField.setEditable(false);
        approverField.setEditable(false);
        dateField.setEditable(false);
        noteField.setEditable(false);
        requestIdField.setEditable(false);
        originalRecordField.setEditable(false);
        modifiedRecordField.setEditable(false);
        statusField.setEditable(false);
        creatorField.setEditable(false);
        createdDateField.setEditable(false);
        scannerIdField.setEditable(false);

    }
//    ĐỂ BACKGROUND COLOR CỦA TEXTFIELD FALSE ENITABLE LÀ MÀU XÁM
    private void disableTextColor(){
        nameField.setStyle("-fx-background-color: #DDDDDD");
        employeeIdField.setStyle("-fx-background-color: #DDDDDD");
        approverField.setStyle("-fx-background-color: #DDDDDD");
        dateField.setStyle("-fx-background-color: #DDDDDD");
        noteField.setStyle("-fx-background-color: #DDDDDD");
        requestIdField.setStyle("-fx-background-color: #DDDDDD");
        originalRecordField.setStyle("-fx-background-color: #DDDDDD");
        modifiedRecordField.setStyle("-fx-background-color: #DDDDDD");
        statusField.setStyle("-fx-background-color: #DDDDDD");
        creatorField.setStyle("-fx-background-color: #DDDDDD");
        createdDateField.setStyle("-fx-background-color: #DDDDDD");
        scannerIdField.setStyle("-fx-background-color: #DDDDDD");
    }
    private void enableTextColor(){
        nameField.setStyle("-fx-background-color: white");
        employeeIdField.setStyle("-fx-background-color: white");
        approverField.setStyle("-fx-background-color: white");
        dateField.setStyle("-fx-background-color: white");
        noteField.setStyle("-fx-background-color: white");
        requestIdField.setStyle("-fx-background-color: white");
        originalRecordField.setStyle("-fx-background-color: white");
        modifiedRecordField.setStyle("-fx-background-color: white");
        statusField.setStyle("-fx-background-color: white");
        creatorField.setStyle("-fx-background-color: white");
        createdDateField.setStyle("-fx-background-color: white");
        scannerIdField.setStyle("-fx-background-color: white");
    }

    private void handleAccept() {
        AcceptView acceptView = new AcceptView();
        acceptView.display();
    }

    private void handleReject() {
        RejectView rejectView = new RejectView();
        rejectView.display();
    }
}
