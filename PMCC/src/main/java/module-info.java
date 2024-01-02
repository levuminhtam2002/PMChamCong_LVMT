module hust.project.base {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires com.google.gson;
    requires org.slf4j;
    exports hust.project.base;
    exports hust.project.base.home;
    opens hust.project.base.home to javafx.fxml;
    exports hust.project.base.dashboard;
    opens hust.project.base.dashboard to javafx.fxml;
    exports hust.project.base.navbar;
    opens hust.project.base.navbar to javafx.fxml;
    exports hust.project.base.header;
    opens hust.project.base.header to javafx.fxml;
    opens hust.project.base.modified.Model to javafx.base;
    exports hust.project.base.modified.Service;
    opens hust.project.base.modified.Service to javafx.fxml;
    exports hust.project.base.modified.Model;
    exports hust.project.base.modified.modified;
    opens hust.project.base.modified.modified to javafx.fxml;
    exports hust.project.base.modified.pendingModified;
    opens hust.project.base.modified.pendingModified to javafx.fxml;
    exports hust.project.base.modified.Accept;
    opens hust.project.base.modified.Accept to javafx.fxml;
    exports hust.project.base.modified.reject;
    opens hust.project.base.modified.reject to javafx.fxml;
}