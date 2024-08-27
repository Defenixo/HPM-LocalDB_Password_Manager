package application;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationHandler extends Controller {
	
	public static void animateshake(Node nameoffield) {
		TranslateTransition translate = new TranslateTransition();
		//servicenamefield.setLayoutX(150);
		nameoffield.setStyle("-fx-text-box-border: #ff0e00; -fx-focus-color: #ff0e00;");
		translate.setNode(nameoffield);
		translate.setDuration(Duration.millis(40));
		translate.setCycleCount(2);
		translate.setByX(15);
		translate.setAutoReverse(true);
		translate.setOnFinished((event) -> {
			TranslateTransition translate2 = new TranslateTransition();
			translate2.setNode(nameoffield);
			translate2.setDuration(Duration.millis(40));
			translate2.setCycleCount(2);
			translate2.setByX(-15);
			translate2.setAutoReverse(true);
			translate2.setOnFinished((event2) -> {
				TranslateTransition translate3 = new TranslateTransition();
				translate3.setNode(nameoffield);
				translate3.setDuration(Duration.millis(40));
				translate3.setCycleCount(2);
				translate3.setByX(15);
				translate3.setAutoReverse(true);
				translate3.setOnFinished((event3) -> {
					TranslateTransition translate4 = new TranslateTransition();
					translate4.setNode(nameoffield);
					translate4.setDuration(Duration.millis(40));
					translate4.setCycleCount(2);
					translate4.setByX(-15);
					translate4.setAutoReverse(true);
					translate4.play();
				});
				translate3.play();
			});
			translate2.play();
		});
		translate.play();
	}
	
	public static void animatescale(Node nameoffield, float amount) {
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(nameoffield);
		scale.setDuration(Duration.millis(300));
		//scale.setCycleCount(2);
		scale.setToX(amount);
		scale.setToY(amount);
		scale.setAutoReverse(true);
		scale.play();
		}
	
	public static void animateboop(Node node) {
		ScaleTransition scalet = new ScaleTransition();
    	scalet.setNode(node);
		scalet.setToX(1.3);
		scalet.setToY(1.3);
		scalet.setDuration(Duration.millis(60));
		scalet.setOnFinished((event) -> {
			ScaleTransition scalet2 = new ScaleTransition();
			scalet2.setNode(node);
			scalet2.setToX(1);
			scalet2.setToY(1);
			scalet2.setDuration(Duration.millis(60));
			scalet2.play();
		});
		scalet.play();
	}
	
	public static void onhoveranimate(Node field, Node text) {
		animatescale(field, 1.25f);
		TranslateTransition translate4 = new TranslateTransition();
		translate4.setNode(text);
		translate4.setDuration(Duration.millis(200));
		translate4.setToY(-40);
		translate4.play();
	}
	
	public static void onhoverexitanimate(Node field, Node text) {
		animatescale(field, 1f);
		TranslateTransition translate5 = new TranslateTransition();
		translate5.setNode(text);
		translate5.setDuration(Duration.millis(200));
		translate5.setToY(0);
		translate5.play();
	}
}
