package fi.tuni.prog3.calc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Calculator extends Application {

    private TextField firstTxt = new TextField();
    private TextField secondTxt = new TextField();
    private Label resultTxt = new Label();
    
    private void createFirstRow(GridPane grid) {
        Label lbl = new Label("First operand:");
        grid.add(lbl, 0, 0);
        grid.add(firstTxt, 1, 0);
        lbl.setId("labelOp1");
        firstTxt.setId("fieldOp1");
    }

    private void createSecondRow(GridPane grid) {
        Label lbl = new Label("Second operand:");
        grid.add(lbl, 0, 1);
        grid.add(secondTxt, 1, 1);
        lbl.setId("labelOp2");
        secondTxt.setId("fieldOp2");
    }
    
    private void createBtnRow(GridPane grid) {
        HBox box = new HBox(1);
        box.getChildren().add(createAddBtn());
        box.getChildren().add(createSubtractBtn());
        box.getChildren().add(createMultiplytBtn());
        box.getChildren().add(createDividetBtn());
        grid.add(box, 0, 2, 2, 1);
    }
    
    private Button createAddBtn() {
        Button btn = new Button("Add");
        btn.setId("btnAdd");
        btn.setOnAction((event) -> {
            resultTxt.setText(Integer.toString(
                    Integer.valueOf(firstTxt.getText())
                            + Integer.valueOf(secondTxt.getText())));
        });
        return btn;
    }
    
    private Button createSubtractBtn() {
        Button btn = new Button("Subtract");
        btn.setId("btnSub");
        btn.setOnAction((event) -> {
            resultTxt.setText(Integer.toString(
                    Integer.valueOf(firstTxt.getText())
                            - Integer.valueOf(secondTxt.getText())));
        });
        return btn;
    }
    
    private Button createMultiplytBtn() {
        Button btn = new Button("Multiply");
        btn.setId("btnMul");
        btn.setOnAction((event) -> {
            resultTxt.setText(Integer.toString(
                    Integer.valueOf(firstTxt.getText())
                            * Integer.valueOf(secondTxt.getText())));
        });
        return btn;
    }
    
    private Button createDividetBtn() {
        Button btn = new Button("Divide");
        btn.setId("btnDiv");
        btn.setOnAction((event) -> {
            resultTxt.setText(Integer.toString(
                    Integer.valueOf(firstTxt.getText())
                            / Integer.valueOf(secondTxt.getText())));
        });
        return btn;
    }
    
    private void createResultRow(GridPane grid) {
        Label lbl = new Label("Result:");
        grid.add(lbl, 0, 3);
        lbl.setId("labelRes");
        resultTxt.setId("fieldRes");
        resultTxt.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(resultTxt, 1, 3);
    }
    
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 300, 150);
        
        createFirstRow(grid);
        createSecondRow(grid);
        createBtnRow(grid);
        createResultRow(grid);
        
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}