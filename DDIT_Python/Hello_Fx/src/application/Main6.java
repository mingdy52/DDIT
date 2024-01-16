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


public class Main6 extends Application {
	
	TextField tf_mine;
	TextField tf_com;
	TextField tf_result;
	
    @Override
    public void start(Stage primaryStage) {
       try {
        
        Parent root = FXMLLoader.load(getClass().getResource("main5.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
        tf_mine = (TextField) scene.lookup("#tf_mine");
        tf_com = (TextField) scene.lookup("#tf_com");
        tf_result = (TextField) scene.lookup("#tf_result");
        Button btn = (Button) scene.lookup("#btn");
        
        tf_mine.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER) {
					myclick();
				}
			}
		
        });
          
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
    	String mine = tf_mine.getText();
    	String com = tf_com.getText();
    	String result = null;
    	
    	Random rd = new Random();
    	int bb = rd.nextInt(2) + 1;
    	
    	if(bb == 1) {
    		com = "홀";
    	} else {
    		com = "짝";
    	}
    	
    	if(mine.equals(com)) {
    	} else {
    		result = "패배";
    	}
		
    	tf_com.setText(com);
    	tf_result.setText(result);
    	
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
