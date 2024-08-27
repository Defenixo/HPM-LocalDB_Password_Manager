package application;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ValidationHandler extends Controller {

	//This method denys SQL injections and makes sure the inputted string does not contain any spaces or special characters, and isn't empty.
	public static boolean ValidateInputofTextField(TextField field, int maximumLength) {
		
		boolean validated = true;
		//char issue;
		
		if(field.getText().isEmpty() && !field.isDisabled()) validated = false;
		
		if(field.getText().length() > maximumLength) validated = false;
		
		for(Character individualcharactersofInput : field.getText().toCharArray()) {
			if(!Character.isLetterOrDigit(individualcharactersofInput) || Character.isWhitespace(individualcharactersofInput)) {
				AnimationHandler.animateshake(field);
				validated = false;
				//issue = individualcharactersofInput;
				break;
			}
		}
		
		if(validated) field.setStyle("-fx-text-box-border: 0068ff; -fx-focus-color: 0068ff;");
		
		return validated;
	}
	
	public static boolean ValidateInputofTextField(TextField field, Text errortext, boolean dologging, int maximumLength) {
		
		boolean validated = true;
		char issue = 'N';
		
		if(field.getText().isEmpty() && !field.isDisabled()) {
			validated = false;
			issue = 'H';
		}
		
		if(field.getText().length() > maximumLength) {
			validated = false;
			issue = 'L';
		}
		
		for(Character individualcharactersofInput : field.getText().toCharArray()) {
			if(!Character.isLetterOrDigit(individualcharactersofInput) || Character.isWhitespace(individualcharactersofInput)) {
				validated = false;
				issue = individualcharactersofInput;
				break;
			}
		}
		
		if(!validated) {
			errortext.setOpacity(1);
			errortext.setFill(Color.RED);
			AnimationHandler.animateshake(field);
			
			if(issue == 'H') {
				errortext.setText("Missing Information");
				if(dologging) logger.error("User tried to run an operation but the required '" + field.getPromptText() + "' field was empty");
			} else if(issue == 'L') {
				errortext.setText("Input's too long");
				if(dologging) logger.error("User tried to run an operation but the required '" + field.getPromptText() + "' field had too many characters (maximum = " + Integer.toString(maximumLength) + ")");
			} else {
				errortext.setText("Don't use Spaces/Special Characters");
				if(dologging) logger.error("User tried to run an operation but the required '" + field.getPromptText() + "' field had special character/space: '" + issue + "'");
			}
			
		} else {
			if(validated) field.setStyle("-fx-text-box-border: #0068ff; -fx-focus-color: #0068ff;");
		}
		
		return validated;
	}
}
