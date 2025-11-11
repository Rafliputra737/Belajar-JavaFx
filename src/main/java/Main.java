/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author LENOVO LOQ
 */


import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url= new File ("src/main/java/View/Beranda.fxml").toURI().toURL();
        Scene s = new Scene (FXMLLoader.load(url));
        // show stage
        stage.setScene(s);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
    
    


