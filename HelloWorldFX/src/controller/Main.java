package controller;


import datamodel.DataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/views/anasayfa.fxml"));

//        GridPane root = new GridPane();
//        root.setAlignment(Pos.CENTER);
//        root.setVgap(10);
//        root.setHgap(10);
//        Label merhaba = new Label("Merhaba hoş geldiniz.");
//        merhaba.setTextFill(Color.GREEN);
//        merhaba.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
//        root.getChildren().add(merhaba);

        primaryStage.setTitle("Hello JavaFX!");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        DataSource.getInstance().open();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
