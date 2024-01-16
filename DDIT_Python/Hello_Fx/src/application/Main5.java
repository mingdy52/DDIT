package application;
	

import java.util.Iterator;
import java.util.Random;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main5 extends Application {
	
	TextField tf_1;
	TextField tf_2;
	TextField tf_3;
	TextField tf_4;
	
    @Override
    public void start(Stage primaryStage) {
       try {
        
        Parent root = FXMLLoader.load(getClass().getResource("main6.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
        tf_1 = (TextField) scene.lookup("#tf_1");
        tf_2 = (TextField) scene.lookup("#tf_2");
        tf_3 = (TextField) scene.lookup("#tf_3");
        tf_4 = (TextField) scene.lookup("#tf_4");
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
    	String a = tf_1.getText();
    	String b = tf_2.getText();
    	String c = tf_3.getText();
    	
    	int aa = Integer.parseInt(a);
    	int bb = Integer.parseInt(b);
    	int cc = Integer.parseInt(c);
    	
    	int result = 0;
    	
    	
    	for (int i = aa; i <= bb; i++) {
    		if(i % cc ==0) result += i;
		}
		
    	tf_4.setText(result + "");
    	
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
