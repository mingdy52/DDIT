package application;
	

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main12 extends Application {
	
	String com = null;
	
	TextField tf;
	TextArea ta;
	
    @Override
    public void start(Stage primaryStage) {
       try {
        
        Parent root = FXMLLoader.load(getClass().getResource("main12.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 
        setCom();
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
    
    public void setCom() {
    	String[] arr9 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    	ArrayList<String> arr3 = new ArrayList<String>();
    	
    	while(arr3.size() < 3) {
    		int rnd = (int) (Math.random()*9);
    		if(!arr9[rnd].equals("-1")) {
    			arr3.add(arr9[rnd]);
    			arr9[rnd] = "-1";
    		}
    	}
    	
    	System.out.println(arr3);
    	
    	com = arr3.get(0) + arr3.get(1) + arr3.get(2);
    }
    
    public void myclick() {
    	String mine = tf.getText();
    	String bs = getBallStrike(com, mine);
    	
    	String str_old = ta.getText();
    	
    	ta.setText(str_old + mine + " " + bs + "\n");
    	
    	if(bs.equals("3S0B")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("야구게임");
        	alert.setHeaderText(null);
        	alert.setContentText("이김");
        	alert.show();
    	}
    	
    }
    
    private String getBallStrike(String com, String mine) {
    	int strike = 0;
    	int ball = 0;
    	
    	String c1 = com.substring(0, 1);
    	String c2 = com.substring(1, 2);
    	String c3 = com.substring(2, 3);
    	
    	String m1 = mine.substring(0, 1);
    	String m2 = mine.substring(1, 2);
    	String m3 = mine.substring(2, 3);
    	
    	if(c1.equals(m1)) strike++;
    	if(c2.equals(m2)) strike++;
    	if(c3.equals(m3)) strike++;
    	
    	if(c1.equals(m2)||c1.equals(m3)) ball++;
    	if(c2.equals(m1)||c2.equals(m3)) ball++;
    	if(c3.equals(m1)||c3.equals(m2)) ball++;
    	
    	
    	String result = strike + "S" + ball + "B";
    	
    	
		return result;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
