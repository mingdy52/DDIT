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


public class Main3 extends Application {
	
	TextField tf_a;
	TextField tf_b;
	TextField tf_c;
	
    @Override
    public void start(Stage primaryStage) {
       try {
          
          Parent root = FXMLLoader.load(getClass().getResource("main3.fxml"));
          Scene scene = new Scene(root,400,400);
          scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
          tf_a = (TextField) scene.lookup("#tf_a");
          tf_b = (TextField) scene.lookup("#tf_b");
          tf_c = (TextField) scene.lookup("#tf_c");
          Button btn = (Button) scene.lookup("#btn");
          
          btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				myclick();
			}
		});
          
          primaryStage.setScene(scene);
          primaryStage.show();
       } catch(Exception e) {
          e.printStackTrace();
       }
    }
    
    public void myclick() {
    	String a = tf_a.getText();
		String b = tf_b.getText();
		int aa = Integer.parseInt(a);
		int bb = Integer.parseInt(b);
		int cc = aa + bb;
		
		tf_c.setText(cc + "");
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
