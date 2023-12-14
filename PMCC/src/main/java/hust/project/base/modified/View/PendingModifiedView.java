package hust.project.base.modified.View;
import hust.project.base.header.SearchCallBack;
import hust.project.base.modified.Model.AttendanceRecordDTO;
import hust.project.base.modified.Model.AttendanceRecordRepository;
import hust.project.base.modified.Model.ModifiedDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.util.Callback;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hust.project.base.constants.MetricsConstants.MAIN_WIDTH;
public class PendingModifiedView extends VBox implements SearchCallBack {
    private  TableView<ModifiedDTO> requestTable;
    private static AttendanceRecordRepository attendanceRecordRepository;

//    private static Home ins;
//    public static Home instance(){
//        if(ins == null){
//            ins = new Home();
//        }
//        return ins;
//    }
    private static PendingModifiedView ins;
    public static PendingModifiedView instance(){
        if(ins == null){
            ins = new PendingModifiedView(attendanceRecordRepository);
        }
        return ins;
    }

    public  PendingModifiedView(AttendanceRecordRepository repo){
        this.attendanceRecordRepository = repo;
        setSpacing(20);
        setPrefWidth(MAIN_WIDTH * 0.7);
        setMaxWidth(MAIN_WIDTH * 0.84);
        Label label = new Label("Danh sách các yêu cầu đang chờ");
        label.setStyle("-fx-font-size: 20px;");
        getChildren().addAll(label, createRequestTable());
    }
    private TableView<ModifiedDTO> createRequestTable() {
        TableView<ModifiedDTO> requestTable = new TableView<>();
        if (attendanceRecordRepository == null) {
            System.out.println("attendanceRecordRepository is null!");
            return null; // or handle the null scenario appropriately
        }
        List<ModifiedDTO> modifiedDTOList = attendanceRecordRepository.getAllModifiedDTOs();
        TableColumn<ModifiedDTO, Number> sttCol = new TableColumn<>("STT");
        sttCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(requestTable.getItems().indexOf(column.getValue()) + 1));
        TableColumn<ModifiedDTO, String> maYeuCauCol = new TableColumn<>("Mã yêu cầu");
        maYeuCauCol.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        TableColumn<ModifiedDTO, String> loaiYeuCauCol = new TableColumn<>("Loại yêu cầu");
        loaiYeuCauCol.setCellValueFactory(new PropertyValueFactory<>("attendanceType"));
        TableColumn<ModifiedDTO, String> trangThaiCol = new TableColumn<>("Trạng thái");
        trangThaiCol.setCellValueFactory(new PropertyValueFactory<>("requestStatus"));
        TableColumn<ModifiedDTO, String> nhanVienCol = new TableColumn<>("Nhân viên");
        nhanVienCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        TableColumn<ModifiedDTO, Timestamp> ngayTaoCol = new TableColumn<>("Ngày tạo");
        ngayTaoCol.setCellValueFactory(new PropertyValueFactory<>("timestampbefore"));
        TableColumn<ModifiedDTO, Timestamp> ngayDuyetCol = new TableColumn<>("Ngày duyệt");
        ngayDuyetCol.setCellValueFactory(new PropertyValueFactory<>("timestampafter"));
        TableColumn<ModifiedDTO, String> tacVuCol = new TableColumn<>("Tác vụ");
        tacVuCol.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        TableColumn<ModifiedDTO, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(cellFactory);

        requestTable.getColumns().addAll(sttCol, maYeuCauCol, loaiYeuCauCol, nhanVienCol, trangThaiCol, ngayTaoCol, ngayDuyetCol, tacVuCol, actionCol);
        requestTable.setPrefHeight(25* 18);
        requestTable.setItems(FXCollections.observableArrayList(modifiedDTOList));
        return requestTable;
    }


        public List<ModifiedDTO> getModifiedDTOs(){
        return new ArrayList<>();
    }

    Callback<TableColumn<ModifiedDTO, Void>, TableCell<ModifiedDTO, Void>> cellFactory = new Callback<>() {
        @Override
        public TableCell<ModifiedDTO, Void> call(final TableColumn<ModifiedDTO, Void> param) {
            final TableCell<ModifiedDTO, Void> cell = new TableCell<>() {

                private final Button btn = new Button("READ");
                {
                    btn.setStyle(" -fx-background-color: transparent;-fx-text-fill: blue;-fx-font-weight: bold;");
                }

                {
                    btn.setOnAction((ActionEvent event) -> {
                        ModifiedDTO data = getTableView().getItems().get(getIndex());
                        openModidiedView(data);
                    });
                }

                {
                    btn.setOnAction((ActionEvent event) -> {
                        ModifiedDTO data = getTableView().getItems().get(getIndex());
                        openModidiedView(data);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };
    @Override
    public void performSearch(String searchText) {
        filterTable(searchText);
    }
    public void filterTable(String searchText) {
        if (attendanceRecordRepository == null) {
            System.out.println("attendanceRecordRepository is null!");
            return; // or handle the null scenario appropriately
        }

        List<ModifiedDTO> filteredList = attendanceRecordRepository.getAllModifiedDTOs().stream()
                .filter(dto -> dto.getEmployeeId().contains(searchText) || dto.getRecordId().contains(searchText))
                .collect(Collectors.toList());

        requestTable.setItems(FXCollections.observableArrayList(filteredList));
    }

    private void openModidiedView(ModifiedDTO data) {
        ModifiedView modifiedView = new ModifiedView();
        modifiedView.display(data);
    }
    public List<AttendanceRecordDTO> getAttendanceRecordDTOs(){
        return new ArrayList<>();
    }

}
