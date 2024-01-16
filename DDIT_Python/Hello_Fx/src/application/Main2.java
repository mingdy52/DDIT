package application;
	

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class Main2 extends Application {
    @Override
    public void start(Stage primaryStage) {
       try {
          
          Parent root = FXMLLoader.load(getClass().getResource("main2.fxml"));
          Scene scene = new Scene(root,400,400);
          scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
          TextField tf = (TextField) scene.lookup("#tf");
          Button btn = (Button) scene.lookup("#btn");
          
          btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				String a = tf.getText();
				int b = Integer.parseInt(a);
				b++;
				tf.setText(b + "");
			}
		});
          
          primaryStage.setScene(scene);
          primaryStage.show();
       } catch(Exception e) {
          e.printStackTrace();
       }
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
