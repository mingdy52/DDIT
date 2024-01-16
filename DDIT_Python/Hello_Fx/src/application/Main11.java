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


public class Main11 extends Application {
	
	TextField tf_fitst;
	TextField tf_last;
	TextArea ta;
	
    @Override
    public void start(Stage primaryStage) {
       try {
        
        Parent root = FXMLLoader.load(getClass().getResource("main11.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
        tf_fitst = (TextField) scene.lookup("#tf_fitst");
        tf_last = (TextField) scene.lookup("#tf_last");
        ta = (TextArea) scene.lookup("#ta");
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
    
    public String drawStar(int cnt) {
    	String ret = "";
    	for (int i = 0; i < cnt; i++) {
    		ret += "*";
		}
    	ret += "\n";
    	
    	return ret;
    }
    
    public void myclick() {
    	int first = Integer.parseInt(tf_fitst.getText());
    	int last = Integer.parseInt(tf_last.getText());
    	
    	String txt = "";
    	
    	for (int i = first; i >= last; i--) {
    		txt += drawStar(i);
			
		}
    	ta.setText(txt);
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
