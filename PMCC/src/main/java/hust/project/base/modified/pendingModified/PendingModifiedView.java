package hust.project.base.modified.pendingModified;

import hust.project.base.modified.Model.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static hust.project.base.constants.MetricsConstants.MAIN_WIDTH;

public class PendingModifiedView extends VBox {
    private TableView<ModifiedRecord> requestTable;

    private List<ModifiedRecord> fullData;
    private static PendingModifiedView ins;

    private Pagination pagination;
    private static final int ROWS_PER_PAGE = 10;

    public static PendingModifiedView instance() {
        if(ins == null){
            ins = new PendingModifiedView ();
        }
        return ins;
    }
    public PendingModifiedView() {
        initializeComponents();
    }
    private void initializeComponents() {
        setSpacing(20);
        setPrefWidth(MAIN_WIDTH * 0.7);
        setMaxWidth(MAIN_WIDTH * 0.85);
        Label label = new Label("Danh sách các yêu cầu đang chờ");
        label.setStyle("-fx-font-size: 20px;");
        requestTable = createRequestTable();
        pagination = new Pagination();
        pagination.setPageFactory(this::createPage);
        getChildren().addAll(label, requestTable, pagination);
    }

    private VBox createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, fullData.size());
        List<ModifiedRecord> pageData = fullData.subList(fromIndex, toIndex);
        requestTable.setItems(FXCollections.observableArrayList(pageData));
        return new VBox(requestTable);
    }

    public void updateTable(List<ModifiedRecord> data) {
        this.fullData = new ArrayList<>(data);
        int pageCount = (int) Math.ceil(data.size() / (double) ROWS_PER_PAGE);
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(0);
        createPage(0);
    }


    private TableView<ModifiedRecord> createRequestTable() {
        TableView<ModifiedRecord> table = new TableView<>();
        table.setEditable(true);
        TableColumn<ModifiedRecord, Number> sttCol = new TableColumn<>("STT");
        sttCol.setMinWidth(35);
        sttCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(column.getValue()) + 1));
        sttCol.setSortable(false);
        table.getColumns().addAll(
                sttCol,
                createColumn("Nhân viên", "employeeId", String.class),
                createColumn("Ngày checkin", "date", String.class),
                createColumn("Giờ checkin", "time", String.class),
                createColumn("Giờ duyệt", "timeModified", String.class, 100),
                createColumn("Ngày duyệt", "dateModified", String.class, 100),
                createColumn("Ghi chú", "requestReason", String.class, 200),
                createStatusColumn()

        );
        table.setPrefHeight(25 * 18);
        return table;
    }

    private <T> TableColumn<ModifiedRecord, T> createColumn(String title, String property, Class<T> type, double... maxWidth) {
        TableColumn<ModifiedRecord, T> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        if (maxWidth.length > 0) {
            column.setMaxWidth(maxWidth[0]);
        }
        return column;
    }

    private TableColumn<ModifiedRecord, String> createStatusColumn() {
        TableColumn<ModifiedRecord, String> statusCol = new TableColumn<>("Trạng thái");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("requestStatus"));
        statusCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
                if (item != null) {
                    setStyle("-fx-text-fill: " + switch (item) {
                        case "Accepted" -> "green";
                        case "Rejected" -> "red";
                        case "Pending" -> "orange";
                        default -> "black";
                    } + ";");
                }
            }
        });
        return statusCol;
    }

    public void createActionColumn(Consumer<ModifiedRecord> displayAction) {
        TableColumn<ModifiedRecord, Void> actionCol = new TableColumn<>("Hành động");
        actionCol.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button("Xem");

            {
                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-font-weight: bold;");
                ImageView image = new ImageView(new Image (Objects.requireNonNull(getClass().getResourceAsStream("/image/View.png"))));
                image.setFitHeight(20);
                image.setFitWidth(20);
                btn.setGraphic (image);
                btn.setOnAction(event -> {
                    ModifiedRecord data = getTableView().getItems().get(getIndex());
                    if (data != null) {
                        displayAction.accept(data);
                    }
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
        requestTable.getColumns().add(actionCol);
    }

    public void refreshTable() {
        List<ModifiedRecord> updatedData = fetchData();
        fullData.clear();
        fullData.addAll(updatedData);
        requestTable.setItems(FXCollections.observableArrayList(fullData));
        requestTable.refresh();
        updatePagination();

    }

    private void updatePagination() {
        int pageCount = (int) Math.ceil((double) fullData.size() / ROWS_PER_PAGE);
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(0);
    }

    private List<ModifiedRecord> fetchData() {
        ModifiedRepository repository = new ModifiedEntity();
        if (repository == null) {
            System.out.println("ModifiedRepository is null");
            return new ArrayList<>();
        } else {
            return repository.getAllModifiedDTO();
        }
    }



}
