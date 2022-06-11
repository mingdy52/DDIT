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
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       try {
          
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("hello.fxml"));
          Parent root = loader.load();
            
          Scene scene = new Scene(root,400,400);
          scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
          Label lbl = (Label) scene.lookup("#lbl");
          Button btn = (Button) scene.lookup("#btn");
          
          btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				lbl.setText("good evening");
				System.out.println("MyClick");
			}
		});
          
          primaryStage.setScene(scene);
          primaryStage.show();
       } catch(Exception e) {
          e.printStackTrace();
       }
    }
}
