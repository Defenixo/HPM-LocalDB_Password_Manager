package application;
	
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main extends Application {
	
	protected static final Logger logger = LogManager.getLogger(Main.class);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Class.forName("org.sqlite.JDBC");
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/loginscreen.fxml"));
			//root.setPrefHeight(800); root.setPrefWidth(1200);
			double x,y;
			
			/*for(Node child : root.getChildren()) {
				child.setScaleX(1.5);
				child.setScaleY(1.5);
				x = child.getLayoutX();
				y = child.getLayoutY();
				child.setLayoutX(x*1.5);
				child.setLayoutY(y*1.5);
			}*/
			//root.setLayoutX(0.5*root.getPrefWidth()); root.setLayoutX(0.5*root.getPrefHeight());
			FileReader f = new FileReader("settingsfile");
			char[] settingsfiletext = new char[1000];
			f.read(settingsfiletext);
			if(settingsfiletext[0] == '2') {
				
				root.setPrefHeight(600); root.setPrefWidth(900);
				AnchorPane rootChild = (AnchorPane)root.getChildren().getFirst();
				rootChild.setScaleX(1.5); rootChild.setScaleY(1.5);
				rootChild.setTranslateX(root.getPrefWidth()*0.167); rootChild.setTranslateY(root.getPrefHeight()*0.167);
				
			} else if(settingsfiletext[0] == '3') {
				
				root.setPrefHeight(800); root.setPrefWidth(1200);
				AnchorPane rootChild = (AnchorPane)root.getChildren().getFirst();
				rootChild.setScaleX(2); rootChild.setScaleY(2);
				rootChild.setTranslateX(root.getPrefWidth()*0.25); rootChild.setTranslateY(root.getPrefHeight()*0.25);
				
			} else if(settingsfiletext[0] == '0') {
				
				root.setPrefHeight(200); root.setPrefWidth(300);
				AnchorPane rootChild = (AnchorPane)root.getChildren().getFirst();
				rootChild.setScaleX(0.5); rootChild.setScaleY(0.5);
				rootChild.setTranslateX(root.getPrefWidth()*-0.5); rootChild.setTranslateY(root.getPrefHeight()*-0.5);
				
			}
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			/*AnchorPane rootChild = (AnchorPane)root.getChildren().getFirst();
			rootChild.setScaleX(2); rootChild.setScaleY(2);
			rootChild.setTranslateX(root.getPrefWidth()*0.25); rootChild.setTranslateY(root.getPrefHeight()*0.25);*/
			
			//x = rootChild.getLayoutX(); y = rootChild.getLayoutY();
			//System.out.println(x + "|" + y);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Hussaini Password Manager");
			
		    primaryStage.setOnCloseRequest(event -> {
		        try {
					Controller.shutdown();
				} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException | SQLException e) {
					e.printStackTrace();
				}
		    });
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
