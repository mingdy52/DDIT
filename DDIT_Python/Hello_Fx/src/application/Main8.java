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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main8 extends Application {
	
	Button btn0;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	Button btn_call;
	TextField tf;
	
    @Override
    public void start(Stage primaryStage) {
       try {
        
        Parent root = FXMLLoader.load(getClass().getResource("main8.fxml"));
        Scene scene = new Scene(root,400,400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        

//        
        btn1 = (Button) scene.lookup("#btn1");
        btn2 = (Button) scene.lookup("#btn2");
        btn3 = (Button) scene.lookup("#btn3");
        btn4 = (Button) scene.lookup("#btn4");
        btn5 = (Button) scene.lookup("#btn5");
        btn6 = (Button) scene.lookup("#btn6");
        btn7 = (Button) scene.lookup("#btn7");
        btn8 = (Button) scene.lookup("#btn8");
        btn9 = (Button) scene.lookup("#btn9");
        btn0 = (Button) scene.lookup("#btn0");
        tf =(TextField) scene.lookup("#tf");
        btn_call =  (Button) scene.lookup("#btn_call");
        
        btn1.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				myclick(event);
			}
		});
        btn2.setOnMouseClicked(new EventHandler<Event>() {
        	
        	@Override
        	public void handle(Event event) {
        		myclick(event);
        	}
        });
        btn3.setOnMouseClicked(new EventHandler<Event>() {
        	
        	@Override
        	public void handle(Event event) {
        		myclick(event);
        	}
        });
        
        btn_call.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				myCall(event);
			}
        	
        });
          primaryStage.setScene(scene);
          primaryStage.show();
       } catch(Exception e) {
          e.printStackTrace();
       }
    }
    
    public void myclick(Event event) {
    	Button imsi = (Button) event.getSource();
    	String str_new = imsi.getText();
    	String str_old = tf.getText();
    	
    	tf.setText(str_old + str_new);
    }
    
    public void myCall(Event event) {
    	String str_tel = tf.getText();
    	
    	Alert alert = new Alert(AlertType.NONE);
    	alert.setTitle("calling");
    	alert.setHeaderText(null);
    	alert.setContentText(str_tel);
    	alert.show();
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
