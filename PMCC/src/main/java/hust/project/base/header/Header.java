package hust.project.base.header;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Objects;

import static hust.project.base.constants.MetricsConstants.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Header extends BorderPane {
    private TextField searchForm;
    public Header() {
        TextField searchForm = new TextField();
        searchForm.setPrefWidth(MAIN_WIDTH * 0.7 - 30); // Subtract the button width
        searchForm.setPrefHeight(30);
        searchForm.setPromptText("Tìm kiếm");
        searchForm.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px ; -fx-border-width: 1px; -fx-border-color: #000000; -fx-border-radius: 20;");
        Button searchButton = new Button();
        searchButton.setPrefHeight(30);
        searchButton.setPrefWidth(30);
        searchButton.setStyle("-fx-background-color: #ffffff;-fx-background-radius: 20;-fx-border-radius: 20; -fx-border-width: 1px; -fx-border-color: #000000;");
        ImageView searchIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/enter.png"))));
        searchIcon.setFitHeight(20);
        searchIcon.setFitWidth(20);
        searchButton.setGraphic(searchIcon);
        searchButton.setOnAction(e -> {
            System.out.println("Searching ...");
        });
        VBox userInfo = new VBox();
        userInfo.setSpacing(5);
        userInfo.setAlignment(Pos.CENTER);
        Label label = new Label("Hello Admin!");
        label.setStyle("-fx-font-size: 15px; -fx-font-family: 'Arial'; -fx-text-fill: #000000");
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/image/image1.jpg")).toExternalForm()));
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        userInfo.getChildren().addAll(imageView, label);
        HBox headerContainer = new HBox();
        headerContainer.setAlignment(Pos.CENTER);
        headerContainer.setSpacing(20);
        headerContainer.getChildren().addAll(searchForm, searchButton, userInfo);
        setLeft(headerContainer);
    }
}
