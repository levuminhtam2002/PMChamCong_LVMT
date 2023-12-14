module hust.project.base {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires com.google.gson;
    requires org.slf4j;

    opens hust.project.base to javafx.fxml;
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
}