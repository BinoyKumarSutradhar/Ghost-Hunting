package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller grid = new Controller(9);

        double height = 9.0 * 70.0;
        double width = 9.0 * 50.0;
        Scene scene = new Scene(grid,height,width);

        primaryStage.setTitle("*****Ghost Hunting*****");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
