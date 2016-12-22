/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rafael
 */
public class FinalProject extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        URL fxmlUrl = this.getClass().getClassLoader().getResource("finalproject/view/FXMLVBoxMain.fxml");
        
        VBox root = FXMLLoader.load(fxmlUrl);
        
        Scene scene = new Scene(root, 778, 624 );
        
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Final Project - Projeto Interdisciplinar");
        primaryStage.getIcons().add(new Image("finalproject/image/f.png"));
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
