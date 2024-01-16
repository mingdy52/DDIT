package application;
	

import java.util.Iterator;

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
import javafx.scene.layout.BorderPane;


public class Main4 extends Application {
	
	TextField tf;
	TextArea ta;
	
    @Override
    public void start(Stage primaryStage) {
       try {
          
          Parent root = FXMLLoader.load(getClass().getResource("main4.fxml"));
          Scene scene = new Scene(root,400,400);
          scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
          tf = (TextField) scene.lookup("#tf");
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
    
    public void myclick() {
    	String dan = tf.getText();
		int ddan = Integer.parseInt(dan);
		
		String str = ddan + " * 1 = " + (ddan*1) + "\n"; 
		str += ddan + " * 2 = " + (ddan*2) + "\n"; 
		str += ddan + " * 3 = " + (ddan*3) + "\n"; 
		str += ddan + " * 4 = " + (ddan*4) + "\n"; 
		str += ddan + " * 5 = " + (ddan*5) + "\n"; 
		str += ddan + " * 6 = " + (ddan*6) + "\n"; 
		str += ddan + " * 7 = " + (ddan*7) + "\n"; 
		str += ddan + " * 8 = " + (ddan*8) + "\n"; 
		str += ddan + " * 9 = " + (ddan*9) + "\n"; 
		
		for (int i = 10; i < 20; i++) {
			str += ddan + " * "+ i + " = " + (ddan*i) + "\n"; 
			
		}
		ta.setText(str);
		
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
