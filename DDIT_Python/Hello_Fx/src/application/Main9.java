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


public class Main9 extends Application {
	
	TextField tf_mine;
	TextField tf_com;
	TextField tf_result;
	
    @Override
    public void start(Stage primaryStage) {
       try {
        
        Parent root = FXMLLoader.load(getClass().getResource("main9.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
        tf_mine = (TextField) scene.lookup("#tf_mine");
        tf_com = (TextField) scene.lookup("#tf_com");
        tf_result = (TextField) scene.lookup("tf_result");
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
    	String a = tf_mine.getText();
    	String b = tf_com.getText();
    	String c = tf_result.getText();
    	
    	double rnd = Math.random();
    	if(rnd > 0.66) {
    		b = "가위";
    	} else if (rnd > 0.33) {
    		b = "바위";
    	} else {
    		b = "보";
    	}
    	
    	if(b.equals("가위")&&a.equals("바위")) c = "이김";
    	if(b.equals("바위")&&a.equals("보")) c = "이김";
    	if(b.equals("보")&&a.equals("가위")) c = "이김";
    	
    	if(b.equals("바위")&&a.equals("가위")) c = "짐";
    	if(b.equals("가위")&&a.equals("보")) c = "짐";
    	if(b.equals("보")&&a.equals("바위")) c = "짐";
    	
    	if(b.equals("가위")&&a.equals("가위")) c = "비김";
    	if(b.equals("보")&&a.equals("보")) c = "비김";
    	if(b.equals("바위")&&a.equals("바위")) c = "비김";
    	
    	tf_com.setText(b);
    	tf_result.setText(c);
    	
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
