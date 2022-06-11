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


public class Main7 extends Application {
	
	 Label lbl_1; 
	 Label lbl_2; 
	 Label lbl_3; 
	 Label lbl_4; 
	 Label lbl_5; 
	 Label lbl_6; 
	
    @Override
    public void start(Stage primaryStage) {
       try {
        
        Parent root = FXMLLoader.load(getClass().getResource("main7.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        lbl_1 = (Label) scene.lookup("#lbl_1");
        lbl_2 = (Label) scene.lookup("#lbl_2");
        lbl_3 = (Label) scene.lookup("#lbl_3");
        lbl_4 = (Label) scene.lookup("#lbl_4");
        lbl_5 = (Label) scene.lookup("#lbl_5");
        lbl_6 = (Label) scene.lookup("#lbl_6");
        
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
    	String result = null;
    	
    	Random rd = new Random();
    	int arr45[] = {
    		1,2,3,4,5,		6,7,8,9,10,
    		11,12,13,14,15,	16,17,18,19,20,
    		21,22,23,24,25,	26,27,28,29,30,
    		31,32,33,34,35,	36,37,38,39,40,
    		41,42,43,44,45
    	};
    	
    	for (int i = 0; i < 1000; i++) {
    		int rnd = (int) Math.random()*45;
    		int a = arr45[1];
    		int b = arr45[0];
    		arr45[0] = a;
    		arr45[rnd] = b;
		}
    	
    	lbl_1.setText(arr45[0] + "");
    	lbl_2.setText(arr45[1] + "");
    	lbl_3.setText(arr45[2] + "");
    	lbl_4.setText(arr45[3] + "");
    	lbl_5.setText(arr45[4] + "");
    	lbl_6.setText(arr45[5] + "");
    	
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
