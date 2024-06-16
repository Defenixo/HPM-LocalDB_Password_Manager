package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller extends EncryptionObj {
	
	//public AnimationClass anim;
	@FXML
	private TextField servicenamefield, usernamefield, usernamefield1, passwordfield, passwordfield1, housaininewpassword, passwordstrengthtextfield, housainiusername;
	@FXML
	private Text errortext, servicenametext, usernametext, passwordtext, passindicator, statustext, createacctext, accwarning, titlepasswordstrengthindicator, subtitlepasswordstrengthindicator;
	@FXML
	private TextFlow accountdisp, passwordstrengthtextflow, settingsresulttextflow;
	@FXML
	private PasswordField housainioldpassword, housainipassword;
	@FXML
	private Button createpassbutton, addaccbutton, changeusername;
	@FXML
	private ProgressIndicator passtimeindicator;
	@FXML
	private AnchorPane passanchorpane, loginanchorpane;
	@FXML
	private Pane buttoncont, passwordstrengthpane, housainiaccountsettingspane;
	@FXML
	private ChoiceBox accountselc;
	@FXML
	private CheckBox reqselctoshowpass;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private TabPane maintabpane;
	@FXML
	private Tab addacctab;
	@FXML
	private ScrollPane scrollpanewithtextf;
	@FXML
	private ProgressBar passwordstrengthprogressbar;
	@FXML
	private Line passwordstrengthlineseperator;
	
	FileWriter writer;
	boolean createorchange;
	boolean canthreadrunagain = true, setactivepassrequired, changecolorofselc;
	Font orgfont;
	public static String currentusername;
	public static int currentheldID, currentpreviousmonth;
	private static String servicestr = "", namestr = "", passwordstr = "", usersettings;
	private static List<Text> txtlist = new ArrayList<Text>();
	private static List<String> passwordlist = new ArrayList<String>();
	private static Connection connectionatt;
	private static Statement connectionstmt;//DriverManager.getConnection("jdbc:mysql://localhost/accdb?", "acc", "2994mYsQl#");
	private static Font ub, pa;
	
	
	public void AddAccounttoStorage(ActionEvent e) throws IOException, NoSuchAlgorithmException {
		
		if(servicenamefield.getText() == null || servicenamefield.getText().isEmpty() || usernamefield.getText() == null || usernamefield.getText().isEmpty() || passwordfield.getText() == null || passwordfield.getText().isEmpty() ) {
			if(servicenamefield.getText() == null || servicenamefield.getText().isEmpty()) animateshake(servicenamefield);
			else servicenamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			if(usernamefield.getText() == null || usernamefield.getText().isEmpty()) animateshake(usernamefield);
			else usernamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			if(passwordfield.getText() == null || passwordfield.getText().isEmpty()) animateshake(passwordfield);
			else passwordfield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			errortext.setText("Field(s) cannot be empty!");
		} else {
			servicenamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			usernamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			passwordfield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			servicestr = servicenamefield.getText();
			namestr = usernamefield.getText();
			passwordstr = passwordfield.getText();
			new Thread() {
				public void run() {
					try {
					ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM accstorage");
					mainrs.moveToInsertRow();
					mainrs.updateString("servicename", EncryptionObj.EncryptFunc(servicestr, "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					mainrs.updateString("username", EncryptionObj.EncryptFunc(namestr, "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					mainrs.updateString("password", EncryptionObj.EncryptFunc(passwordstr, "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					mainrs.updateInt("id", currentheldID);
					mainrs.insertRow();
					} catch(SQLException | NoSuchAlgorithmException sql_nsaE) {
						errortext.setText("Database Connection Failed");
						sql_nsaE.printStackTrace();
					}
				}
			}.start();
			errortext.setText("Added account: " + usernamefield.getText());
		}
		
		servicenamefield.setText(null);
		passwordfield.setText(null);
		usernamefield.setText(null);
	}
	
	public void showaccounts() throws IOException, NoSuchAlgorithmException {
		
		/*ColorInput color = new ColorInput();
		color.setPaint(Color.BLACK);
		color.setWidth(Double.MAX_VALUE);
		color.setHeight(Double.MAX_VALUE);

		Blend blend = new Blend(BlendMode.DIFFERENCE);
		blend.setBottomInput(color);

		accwarning.setEffect(blend); */
		
		//System.out.println(loggedin);
		//if(loggedin || !requirespass.isSelected()) {
			//buttoncont.setVisible(false);
		
			    
			accountdisp.getChildren().clear();
			passwordlist.clear();
			txtlist.clear();
			accountselc.getItems().clear();
			accountdisp.setStyle("-fx-background-color: #ffffff");
			Text announcetext = new Text("Select an Account by Left Clicking on the Service Name" + '\n');
			announcetext.setFill(Color.web("#186f0f"));
			announcetext.setFont(Font.font("Ubuntu", FontWeight.BOLD, 18));
			accountdisp.getChildren().add(announcetext);
		
		//boolean loggedinwithpass = Integer.parseInt(new StringBuilder(passin.readLine()).substring(0, 2)) <= 23, allow;
		
		new Thread() {
			
			int i = 0;
			public void run() {
		try {
			ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM accstorage WHERE id=" + currentheldID);
			//fulllinestr = (EncryptionObj.DecryptFunc(new StringBuilder(originalfulllinestr = in.readLine()).deleteCharAt(0).toString(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"))) != null
		while(mainrs.next()) {
				
				servicestr = EncryptionObj.DecryptFunc(mainrs.getString("servicename"), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231");
				namestr = EncryptionObj.DecryptFunc(mainrs.getString("username"), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231");
				passwordstr = EncryptionObj.DecryptFunc(mainrs.getString("password"), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231");
			
			txtlist.add(new Text('\n' + servicestr + '\n'));
		    txtlist.add(new Text("Username/Email: " + namestr + '\n'));
		    txtlist.add(new Text("Password: " + passwordstr + '\n'));
		    txtlist.get(i).setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
		    txtlist.get(i).setUnderline(true);
		    txtlist.get(i + 1).setFont(Font.font("Ubuntu", 24));
		    txtlist.get(i + 2).setFont(Font.font("Ubuntu", 24));
		    if(usersettings.charAt(0) == '1') txtlist.get(i + 2).setFill(Color.WHITE);
		    final int finI = i;
		    txtlist.get(i).setOnMouseEntered((event) -> {txtlist.get(finI).setFill(Color.AQUAMARINE);});
		    txtlist.get(i).setOnMouseClicked((event) -> {
		    	accountselc.setValue(accountselc.getItems().get(finI/3));
		    	
		    	animateboop(accwarning);
		    	
		    	Text ttemp = new Text();
		    	
		    	for(int h = 0; h < accountdisp.getChildren().size(); h++) {
		    		if(accountdisp.getChildren().get(h).getClass().equals(Text.class)) {
		    			if((ttemp = (Text)accountdisp.getChildren().get(h)).getFill().equals(Color.PURPLE))
		    			ttemp.setFill(Color.WHITE);
		    		}
		    	}
		    	
		    	(new Thread() {
					public void run() {
						String tempstr = "";
						
						Paint tempcolor = accwarning.getFill();
						accwarning.setFill(Color.BLUE);
						accwarning.setText("Account has been selected!");
						if(usersettings.charAt(0) == '1') {
						Text t = (Text) accountdisp.getChildren().get((accountselc.getItems().indexOf(accountselc.getValue()) + 1)*3 + accountselc.getItems().indexOf(accountselc.getValue()));
						t.setFill(Color.PURPLE);
						}
						try {sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
						accwarning.setFill(tempcolor);
						accwarning.setText(tempstr);
					}
				}).start();
		    });
		    txtlist.get(i).setOnMouseExited((event) -> {
		    	txtlist.get(finI).setFill(Color.BLACK);
		    });
		    
		    accountselc.getItems().add(servicestr + ": " + namestr);
		    
		    if(!passwordlist.contains(passwordstr)) {
				passwordlist.add(passwordstr);
			} else {
				if(accwarning.getText().isEmpty() || accwarning.getText().equals("")) {
					accwarning.setText("password: " + passwordstr + " is used multiple times!");
				} else {
					accwarning.setText(accwarning.getText().replaceAll(": ", "s: " + passwordstr + ", ").replaceAll(" is used multiple times!", " are used multiple times!"));
				}
				passwordlist.add(passwordstr);
			}
				
		    i = i + 3;
		    servicestr = ""; namestr = ""; passwordstr = "";
		    
			
			}
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				
				for(int g = 0; g < txtlist.size(); g += 3) {
				    accountdisp.getChildren().add(txtlist.get(g));
					accountdisp.getChildren().add(txtlist.get(g + 1));
					accountdisp.getChildren().add(txtlist.get(g + 2));
					Line seperatorline = new Line();
					seperatorline.setStartX(-500);
					
					accountdisp.getChildren().add(seperatorline);
					}
			}});
			
			} catch(NullPointerException | SQLException | NoSuchAlgorithmException np_sql_nsa_murl_ioE) {np_sql_nsa_murl_ioE.printStackTrace();}
		}}.start();
	}
	
	public void deletetextfromdisp() {
		accountdisp.getChildren().clear();
		accwarning.setText("");
	}
	
	public void changepass(ActionEvent e) {
		
		if(!housainioldpassword.getText().isEmpty() && !housaininewpassword.getText().isEmpty()) {
		new Thread() {
			public void run() {
		try {
			ResultSet mainrs = connectionstmt.executeQuery("SELECT id, prevmonth, password, username, settings FROM accinfo WHERE id = " + currentheldID);
			mainrs.beforeFirst();
			while(mainrs.next()) {
				if(mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainioldpassword.getText(), EncryptionObj.hashKey(housainioldpassword.getText()))) && mainrs.getString("id").equals(Integer.toString(currentheldID))) {
					mainrs.updateString("password", EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText(), EncryptionObj.hashKey(housaininewpassword.getText())));
					mainrs.updateRow();
				}
			}
			statustext.setText("Password has been Updated!");
			animateboop(statustext);
			statustext.setVisible(true);
			housainioldpassword.setText("");
			housaininewpassword.setText("");
			} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException sql_nsaE) {
				sql_nsaE.printStackTrace();
			}
		
		}
	}.start();
	} else {
		if(housaininewpassword.getText().isEmpty() || housaininewpassword.getText() == null)
			animateshake(housaininewpassword);
		else
			animateshake(housainioldpassword);
	}
	}
	
	public void changeusernameHOUSAINI(ActionEvent e) {
		if(!housaininewpassword.getText().isEmpty()) {
			new Thread() {
				public void run() {
			try {
				int mainrs = connectionstmt.executeUpdate("UPDATE accinfo SET username = " + '"' + housaininewpassword.getText() + '"' + " WHERE id = " + currentheldID);
				statustext.setText("Username has been Updated!");
				animateboop(statustext);
				statustext.setVisible(true);
				currentusername = housaininewpassword.getText();
				housaininewpassword.setText("");
				//LocalDate currentdate = ;
				housaininewpassword.setDisable(true);
				createpassbutton.setDisable(true);
				mainrs = connectionstmt.executeUpdate("UPDATE accinfo SET prevmonth = " + '"' + (currentpreviousmonth = LocalDate.now().getMonthValue()) + '"' + " WHERE id = " + currentheldID);
				sleep(3000);
				statustext.setText("Current Username: " + currentusername);
				} catch (SQLException | InterruptedException IE_sqlE) {
					IE_sqlE.printStackTrace();
				}
			
			}
		}.start();
		} else {
				animateshake(housaininewpassword);
		}
	}
	
	private void writetofile(String input, String filename, boolean appendmode) throws IOException {
		writer = new FileWriter(filename, appendmode);
		writer.append(input + '\n');
		writer.close();
	}
	
	public void setline(int linenum, String line) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("AccountStorage.txt"), StandardCharsets.UTF_8);
        new FileWriter("AccountStorage.txt", false).close();
        
        if(line.equals("-+-+DELETE")) lines.remove(linenum); else lines.set(linenum, line);
        
        for(int i = 0; i < lines.size(); i++) {
        	writetofile(lines.get(i), "AccountStorage.txt", true);
        }
	}
	
	public void deleteaccountfromstorage(ActionEvent theactioneventofdel) throws IOException {
		new Thread() {
			public void run() {
				try {
		ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM accstorage WHERE id=" + Integer.toString(currentheldID));
		mainrs.absolute(accountselc.getItems().indexOf(accountselc.getValue()) + 1);
		int primarykeyfordeletingline = mainrs.getInt("primark");
		mainrs.close();
		connectionstmt.executeUpdate("DELETE FROM accstorage WHERE primark=" + primarykeyfordeletingline);
				} catch(SQLException sqlE) {sqlE.printStackTrace();}
			}
		}.start();
	}
	
	public void ChangeScene(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/PasswordManagerMain.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene); stage.show();	
		}
	
	public void loginorcreatePassSeperate(ActionEvent e) throws NoSuchAlgorithmException, IOException, SQLException {
		
			if(canthreadrunagain) {
				
				canthreadrunagain = false;
				setactivepassrequired = true;
				passtimeindicator.setOpacity(1);
				passindicator.setOpacity(1);
				passindicator.setVisible(false);
				passindicator.setVisible(true);
				passindicator.setText("Verifying Password...");
				passtimeindicator.setVisible(false);
				passtimeindicator.setVisible(true);
					
				(new Thread() {
				public void run() {
					boolean accfound2 = false;
					try {
						Thread waitanimation = new Thread() {
							public void run() {
							double changeop = 0.4; boolean goup = true; int timedone = 0;
						
						while(timedone < 4300) {
							if(changeop < 0.9 && goup)
							changeop += 0.1;
							else if(changeop > 0.9 && goup) {
								goup = false;
							} else if(changeop > 0.1 && !goup) {
								changeop -= 0.1;
							} else if(changeop < 0.1 && !goup) {
								goup = true;
							}
							try { sleep((long)100); } catch (InterruptedException e) { e.printStackTrace(); }
							passindicator.setOpacity(changeop);
							timedone += 50;
						}
						passindicator.setOpacity(1);
							}
						};
						
						waitanimation.start();
						connectionatt = DriverManager.getConnection("jdbc:mysql://localhost/accdb?", "acc", "2994mYsQl#");
						connectionstmt = connectionatt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						ResultSet mainrs = connectionstmt.executeQuery("SELECT id, prevmonth, password, username, settings FROM accinfo");
						
						mainrs.beforeFirst();
						if(!housainipassword.getText().isEmpty()) {
						while(mainrs.next() && !accfound2) {
							if(mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText()))) && mainrs.getString("username").equals(housainiusername.getText())) {
								accfound2 = true;
								currentusername = mainrs.getString("username");
								currentheldID = mainrs.getInt("id");
								usersettings = mainrs.getString("settings");
								currentpreviousmonth = mainrs.getInt("prevmonth");
								if(currentpreviousmonth < LocalDate.now().getMonthValue()) {
									currentpreviousmonth = 0;
								}
								}
							}
						} else {
							interrupt();
						}
						waitanimation.join();
						
						} catch(SQLException | NoSuchAlgorithmException | InterruptedException | InvalidKeySpecException sqlE_nsa_iE) {
							sqlE_nsa_iE.printStackTrace();
							passindicator.setText("Connection Failed");
							passindicator.setOpacity(1);
							try {sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
							interrupt();
						}
					
							if(accfound2) {
							housainipassword.clear();
							housainiusername.clear();
							passtimeindicator.setVisible(false);
							passindicator.setText("Verified! Opening...");
							
							
							
							try {sleep((long)100);} catch (InterruptedException e) {e.printStackTrace();}
							
							Platform.runLater(new Runnable() {
							    @Override
							    public void run() {
							    	//Main.logger.info("Logged into (" + currentusername + ") Successfully");
							    	Parent root = null;
									try{root = FXMLLoader.load(getClass().getResource("/PasswordManagerMain.fxml"));} catch (IOException e) {e.printStackTrace();}
									stage = (Stage)((Node)e.getSource()).getScene().getWindow();
									scene = new Scene(root);
									scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
									stage.setScene(scene); stage.show(); //maintabpane.setOpacity(0);
									
									/*
									 * THIS IS FOR DARK MODE, ENABLE WHEN READY
									 * 
									 * ColorInput color = new ColorInput();
									color.setPaint(Color.WHITE);
									color.setWidth(Double.MAX_VALUE);
									color.setHeight(Double.MAX_VALUE);

									Blend blend = new Blend(BlendMode.DIFFERENCE);
									blend.setBottomInput(color);

									root.setEffect(blend);*/
									
							    }
							});
							} else {
								passindicator.setText("Account Details are Incorrect");
								passtimeindicator.setVisible(false);
								canthreadrunagain = true;
							}
							
						}
					}
					).start();
			}
			
	}
	
	/*public void confirmnewaccountcreation() throws SQLException, NoSuchAlgorithmException {
		
		new Thread() {
			public void run() {
				try {
					Thread waitanimation = new Thread() {
						public void run() {
						double changeop = 0.4; boolean goup = true; int timedone = 0;
					
					while(timedone < 4300) {
						if(changeop < 0.9 && goup)
						changeop += 0.1;
						else if(changeop > 0.9 && goup) {
							goup = false;
						} else if(changeop > 0.1 && !goup) {
							changeop -= 0.1;
						} else if(changeop < 0.1 && !goup) {
							goup = true;
						}
						try { sleep((long)100); } catch (InterruptedException e) { e.printStackTrace(); }
						passindicator.setOpacity(changeop);
						timedone += 50;
					}}};
					waitanimation.start();
					
				Statement connectionstmt = connectionatt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet mainrs = connectionstmt.executeQuery("SELECT id, prevmonth, password, username, loggedin FROM accinfo");
				mainrs.moveToInsertRow();
				mainrs.updateString("password", EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText(), EncryptionObj.hashKey(housaininewpassword.getText())));
				mainrs.updateString("username", housainiusername.getText());
				mainrs.insertRow();
				waitanimation.join();
				} catch (SQLException | NoSuchAlgorithmException | InterruptedException | InvalidKeySpecException sql_nsa_iE) {
					sql_nsa_iE.printStackTrace();
				}
				passindicator.setText("Account Created!");
				try {sleep(3000);} catch (InterruptedException e) {}
				passindicator.setVisible(false);
				passindicator.setText("Verifying Password...");
				passindicator.setFill(Color.BLACK);
			}
		}.start();
	}
	
	public void makepassnotreq() throws NoSuchAlgorithmException, IOException {
		BufferedReader readfile = new BufferedReader(new FileReader("HousainiPass.txt"));
		String orgline;
		orgline = readfile.readLine();
		
		
		//$** , #*$
		if(requirespass.isSelected()) {
			//if(orgline.contains("#*$") && orgline.charAt(0) == '#') {
				writetofile(orgline.replaceAll(new StringBuilder(orgline).substring(0, 2), Integer.toString((int)Math.floor(Math.random() *(23 - 11 + 1) + 11))), "HousainiPass.txt", false);
				//System.out.println(orgline + " + first");
			//}
		} else {
			//if(orgline.charAt(0) == '$') {
				writetofile(orgline.replaceAll(new StringBuilder(orgline).substring(0, 2), Integer.toString((int)Math.floor(Math.random() *(39 - 24 + 1) + 24))), "HousainiPass.txt", false);
				//System.out.println(orgline + " + second");
			//}
		}
		
		readfile.close();
	}*/
	
	public void opensettings() throws IOException {
		if(usersettings == null) usersettings = "0000000000";
		statustext.setVisible(false);
		reqselctoshowpass.setSelected(usersettings.charAt(0) == '1');
	}
	
	public void changedetails(ActionEvent e) throws NoSuchAlgorithmException, IOException {
		new Thread() {
			public void run(){
				try {
					ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM accstorage WHERE id=" + Integer.toString(currentheldID));
					
					mainrs.absolute(accountselc.getItems().indexOf(accountselc.getValue()) + 1);
					passwordfield1.setText(EncryptionObj.DecryptFunc(mainrs.getString("password"), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					usernamefield1.setText(EncryptionObj.DecryptFunc(mainrs.getString("username"), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
				} catch (SQLException | NoSuchAlgorithmException sql_nsaE) {sql_nsaE.printStackTrace();}
			}
		}.start();
		
			accountdisp.getChildren().clear();
	
		scrollpanewithtextf.setVisible(false);
		scrollpanewithtextf.setDisable(true);
		
		
	}
	
	public void ChangeAccountofStorage(ActionEvent e) throws IOException, NoSuchAlgorithmException {
		
		new Thread() {
			public void run() {
				try {
					ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM accstorage WHERE id=" + Integer.toString(currentheldID));
					
					mainrs.absolute(accountselc.getItems().indexOf(accountselc.getValue()) + 1);
					mainrs.updateString("username", EncryptionObj.EncryptFunc(usernamefield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					mainrs.updateString("password", EncryptionObj.EncryptFunc(passwordfield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					mainrs.updateRow();
					
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
						scrollpanewithtextf.setVisible(true);
						scrollpanewithtextf.setDisable(false);
						
						try {showaccounts();} catch (NoSuchAlgorithmException | IOException nsa_ioE) {nsa_ioE.printStackTrace();}
						animateboop(accwarning);
						}
					});
					
					
					String tempstr = accwarning.getText();
					Paint tempcolor = accwarning.getFill();
					accwarning.setFill(Color.BLUE);
					accwarning.setText("Account has been Updated!");
					try {sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
					accwarning.setFill(tempcolor);
					accwarning.setText(tempstr);
					
				} catch (SQLException | NoSuchAlgorithmException sql_nsaE) {sql_nsaE.printStackTrace();}
			}
		}.start();
	}
	
	public void CancelOperation(ActionEvent e) {
		scrollpanewithtextf.setVisible(true);
		scrollpanewithtextf.setDisable(false);
		usernamefield1.clear();
		passwordfield1.clear();
	}
	
	public void shutdown() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, SQLException {
		/*BufferedReader readfile = new BufferedReader(new FileReader("HousainiPass.txt"));
		String orgline;
		String linestr = EncryptionObj.DecryptFunc(new StringBuilder(orgline = readfile.readLine()).toString(), "343+54-69a434fFGERF!@$%43334=\\قثق34ب");
		
		if(Integer.parseInt(new StringBuilder(orgline).substring(0, 2)) >= 24) {
			
				writetofile(orgline.replaceAll(new StringBuilder(orgline).substring(0, 2), Integer.toString((int)Math.floor(Math.random() *(39 - 24 + 1) + 24))), "HousainiPass.txt", false);
			
		}
		
		readfile.close();*/
		connectionatt.close();
		connectionstmt.close();
    }
	
	private void animateshake(Node nameoffield) {
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
	
	public void animatescale(Node nameoffield, float amount) {
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(nameoffield);
		scale.setDuration(Duration.millis(300));
		//scale.setCycleCount(2);
		scale.setToX(amount);
		scale.setToY(amount);
		scale.setAutoReverse(true);
		scale.play();
		}
	
	private void animateboop(Node node) {
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
	
	public void SERVICENAMEFIELD_onhoveranimate() {
		animatescale(servicenamefield, 1.25f);
		TranslateTransition translate4 = new TranslateTransition();
		translate4.setNode(servicenametext);
		translate4.setDuration(Duration.millis(200));
		translate4.setToY(-40);
		translate4.play();
	}
	
	public void SERVICENAMEFIELD_onhoverexitanimate() {
		animatescale(servicenamefield, 1f);
		TranslateTransition translate5 = new TranslateTransition();
		translate5.setNode(servicenametext);
		translate5.setDuration(Duration.millis(200));
		translate5.setToY(0);
		translate5.play();
	}
	
	public void USERNAMEFIELD_onhoveranimate() {
		animatescale(usernamefield, 1.25f);
		TranslateTransition translate6 = new TranslateTransition();
		translate6.setNode(usernametext);
		translate6.setDuration(Duration.millis(200));
		translate6.setToY(-40);
		translate6.play();
	}
	
	public void USERNAMEFIELD_onhoverexitanimate() {
		animatescale(usernamefield, 1f);
		TranslateTransition translate7 = new TranslateTransition();
		translate7.setNode(usernametext);
		translate7.setDuration(Duration.millis(200));
		translate7.setToY(0);
		translate7.play();
	}
	
	public void PASSWORDFIELD_onhoveranimate() {
		animatescale(passwordfield, 1.25f);
		TranslateTransition translate8 = new TranslateTransition();
		translate8.setNode(passwordtext);
		translate8.setDuration(Duration.millis(200));
		translate8.setToY(-38);
		translate8.play();
	}
	
	public void PASSWORDFIELD_onhoverexitanimate() {
		
		animatescale(passwordfield, 1f);
		TranslateTransition translate9 = new TranslateTransition();
		translate9.setNode(passwordtext);
		translate9.setDuration(Duration.millis(200));
		translate9.setToY(0);
		translate9.play();
	}
	
	public void PASSWORDSTRENGTHPROGRESSBAR_onhoveranimate() {
		/*animatescale(passwordstrengthprogressbar, 1.05f); //84
		passwordstrengthpane.setLayoutY(44);
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(passwordstrengthpane);
		translate.setDuration(Duration.millis(70));
		translate.setToY(84);
		translate.play();*/
	}
	
	public void PASSWORDSTRENGTHPROGRESSBAR_onhoverexitanimate() {
		animatescale(passwordstrengthprogressbar, 1f);
	}
	
	public void passwordevaluation() {
		String in = passwordstrengthtextfield.getText(), charsmissing = "";
		boolean hasuppercase = false, haslowercase = false;
		int numsrequired = 0, lettersrequired = 0, progressbarval = 0;
		ArrayList<Character> inarr = new ArrayList<Character>();
		ArrayList<Text> precautions = new ArrayList<Text>();
		ArrayList<Integer> progress = new ArrayList<Integer>();
		
		passwordstrengthtextflow.getChildren().clear();
		
		if(in.length() < 32 && in.length() > 6) {
		for(int i = 0; i < in.length(); i++) {
			inarr.add(in.charAt(i));
		}
		if(in.length() <= 11) {
			precautions.add(new Text("• Password is too short" + '\n'));
			progress.add(-30);
			//passwordstrengthtextflow.getChildren().add(precautions);
		}
		//charsmissing = charsmissing + "SOMETHING ELSE";
			for(int i = 0; i < inarr.size(); i++) {
			
			if(!Character.isLetter(inarr.get(i)) && !Character.isDigit(inarr.get(i))) {
				charsmissing = charsmissing + inarr.get(i);
				progress.add(5);
			}
			
			if(Character.isLetter(inarr.get(i))) {
				if(Character.isUpperCase(inarr.get(i))) { hasuppercase = true; progress.add(3); }
				else { haslowercase = true; progress.add(2); }
				lettersrequired+=1;
			}
			
			if(Character.isDigit(inarr.get(i))) numsrequired += 1;
			}
			
			if(charsmissing.length() < 5) {
				precautions.add(new Text("• Password contains less than 5 special characters" + '\n'));
				progress.add(-15);
				//passwordstrengthtextflow.getChildren().add(precautions);
			} else if(charsmissing.length() > in.length() * 0.5){
				precautions.add(new Text("• Password contains too many special characters" + '\n'));
				progress.add((int)(charsmissing.length() * -1) * 5);
			}
			
			if(!hasuppercase) { precautions.add(new Text("• Password lacks uppercase letters" + '\n')); progress.add(-10);}
			
			if(!haslowercase) { precautions.add(new Text("• Password lacks lowercase letters" + '\n')); progress.add(-5);}
			
			if(numsrequired < 5)  { precautions.add(new Text("• Password lacks numbers (min. 5)" + '\n')); progress.add(-10);}
			
			else { 
				
				if(numsrequired > (int)in.length()*0.5) { progress.add(numsrequired); precautions.add(new Text("• Password has too many numbers" + '\n')); }
				else progress.add(numsrequired * 3);
					
			}
			
			for(int i = 0; i < inarr.size(); i++) {
				char dig = inarr.get(i);
				if(Character.isDigit(dig) && i + 1 < inarr.size()) {
					if(Character.getNumericValue(inarr.get(i + 1)) == Character.getNumericValue(dig) + 1 && i + 2 < inarr.size()) {
					if(Character.getNumericValue(inarr.get(i + 2)) == Character.getNumericValue(inarr.get(i + 1)) + 1 ) {
					if(precautions.size() > 0 && precautions.get(0).getId() == null) {
						precautions.add(0,new Text("• Password has predictable number orders" + '\n'));
						precautions.get(0).setId("prec_orderednums");
						progress.add(-10);
					}}}}
			}
			
		} else {
			if(in.length() < 6)
				precautions.add(new Text("Below Minimum Characters allowed for a Password on most websites"));
			else if(in.length() > 32)
				precautions.add(new Text("Above Maximum Characters allowed for a Password on most websites"));
		}
		
		if(lettersrequired > (int)(in.length() * 0.8)) {
			precautions.add(new Text("• Password is almost (or) entirely letters" + '\n'));
			progress.add((lettersrequired*3)*-1);
		}
			
			precautions.forEach((Text) -> {
				Text.setFont(Font.font("Ubuntu", FontWeight.BOLD, 23));
				Text.setFill(Color.web("#a80000"));
			}); 
			
			for(int i = 0; i < precautions.size(); i++) {
				passwordstrengthtextflow.getChildren().add(precautions.get(i));
			}
			
			for(int i = 0; i < progress.size(); i++) {
				progressbarval += progress.get(i);
			}
			
			
			if(progressbarval > 0) {
				if(progressbarval > 100) passwordstrengthprogressbar.setProgress(100);
				else passwordstrengthprogressbar.setProgress((double)progressbarval / 100);
			}
			
			if(progressbarval <= 15) {
				titlepasswordstrengthindicator.setText("Extremely Weak");
				titlepasswordstrengthindicator.setFill(Color.rgb(255, 98, 98));
				titlepasswordstrengthindicator.setStroke(Color.rgb(255, 45, 45));
				subtitlepasswordstrengthindicator.setText("You must take with the advised precautions to protect your account");
			} else if(progressbarval <= 30) {
				titlepasswordstrengthindicator.setText("Weak");
				titlepasswordstrengthindicator.setFill(Color.rgb(255, 141, 141));
				titlepasswordstrengthindicator.setStroke(Color.rgb(255, 91, 91));
				subtitlepasswordstrengthindicator.setText("It's advisable to take into account the following precautions");
			} else if(progressbarval <= 53) {
				titlepasswordstrengthindicator.setText("Okay");
				titlepasswordstrengthindicator.setFill(Color.rgb(255, 255, 255));
				titlepasswordstrengthindicator.setStroke(Color.rgb(0, 0, 0));
				subtitlepasswordstrengthindicator.setText("Great for casual and unimportant accounts");
			} else if(progressbarval <= 80) {
				titlepasswordstrengthindicator.setText("Strong");
				titlepasswordstrengthindicator.setFill(Color.rgb(124, 255, 140));
				titlepasswordstrengthindicator.setStroke(Color.rgb(42, 117, 47));
				subtitlepasswordstrengthindicator.setText("Overall amazing password, but it can still be improved");
			} else if(progressbarval >= 80) {
				titlepasswordstrengthindicator.setText("Extremely Strong");
				titlepasswordstrengthindicator.setFill(Color.rgb(0, 255, 156));
				titlepasswordstrengthindicator.setStroke(Color.rgb(31, 174, 38));
				subtitlepasswordstrengthindicator.setText("Very hard to crack, store it somewhere safe!");
			}
			
			
			
			
			
			//precautions.setFill(Color.web("#a80000"));
	}
	
	public void usernamesetter(String str) {
		currentusername = str;
	}
	
	public void passwordstrengthonselectionchanged() {
		/*ColorInput color = new ColorInput();
		color.setPaint(Color.WHITE);
		color.setWidth(Double.MAX_VALUE);
		color.setHeight(Double.MAX_VALUE);

		Blend blend = new Blend(BlendMode.DIFFERENCE);
		blend.setBottomInput(color);

		passwordstrengthpane.setEffect(blend);
		titlepasswordstrengthindicator.setEffect(blend);
		passwordstrengthlineseperator.setEffect(blend);
		subtitlepasswordstrengthindicator.setEffect(blend);
		passwordstrengthtextfield.setEffect(blend);
		passwordstrengthprogressbar.setEffect(blend);*/
		
	}
	
	/*public void TEXTenlarge(Text texttoanim) {
		System.out.println("Ran");
		new Thread() {
			public void run() {
				for(int i = 0; i < 15; i++) {
					texttoanim.setFont(Font.font(texttoanim.getFont().getName(), FontWeight.BOLD, 30 + i));
					try {sleep(10);} catch (InterruptedException e) {}
				}
			}
		}.start();
	}*/
	
	public void signuptoaccTEXT() {
		createpassbutton.setText("Create Account");
		createpassbutton.setOnAction((event) -> {
			new Thread() {
				public void run() {
					try {
						Thread waitanimation = new Thread() {
							public void run() {
							double changeop = 0.4; boolean goup = true; int timedone = 0;
							passindicator.setText("Creating Account");
						
						while(timedone < 4300) {
							if(changeop < 0.9 && goup)
							changeop += 0.1;
							else if(changeop > 0.9 && goup) {
								goup = false;
							} else if(changeop > 0.1 && !goup) {
								changeop -= 0.1;
							} else if(changeop < 0.1 && !goup) {
								goup = true;
							}
							try { sleep((long)100); } catch (InterruptedException e) { e.printStackTrace(); }
							passindicator.setOpacity(changeop);
							timedone += 50;
						}}};
						waitanimation.start();
						boolean allow = true;
					connectionatt = DriverManager.getConnection("jdbc:mysql://localhost/accdb?", "acc", "2994mYsQl#");
					connectionstmt = connectionatt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet mainrs = connectionstmt.executeQuery("SELECT id, prevmonth, password, username, settings FROM accinfo");
					while(mainrs.next()) {
						if(mainrs.getString("username").equals(housainiusername.getText()))allow = false;
					}
					waitanimation.join();
						 if(allow){
							mainrs.moveToInsertRow();
							mainrs.updateString("password", EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText())));
							mainrs.updateString("username", housainiusername.getText());
							mainrs.updateInt("prevmonth", LocalDate.now().getMonthValue());
							mainrs.insertRow();
							
							animateboop(passindicator);
							passindicator.setOpacity(1);
							passindicator.setText("Account Created!");
							try {sleep(3000);} catch (InterruptedException e) {}
							passindicator.setText("Create Housaini Account");
							passindicator.setFill(Color.BLACK);
						} else {
							passindicator.setOpacity(1);
							passindicator.setFill(Color.RED);
							passindicator.setText("That username already exists!");
							try {sleep(3000);} catch (InterruptedException e) {}
							passindicator.setFill(Color.BLACK);
						}
					} catch (SQLException | NoSuchAlgorithmException | InterruptedException sql_nsa_iE) {
						sql_nsa_iE.printStackTrace();
					} catch (InvalidKeySpecException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}.start();
		});
		
		passindicator.setText("Create Housaini Account");
		createacctext.setText("Log In with Housaini Account");
		createacctext.setOnMouseClicked((event) -> {
			passindicator.setOpacity(1);
			createpassbutton.setText("Log in");
			passindicator.setText("Log in with Housaini Account");
			createpassbutton.setOnAction((event2) -> {
				try {loginorcreatePassSeperate(event2);} catch (NoSuchAlgorithmException | IOException | SQLException e) {e.printStackTrace();}
			});
			createacctext.setOnMouseClicked((event3) -> {signuptoaccTEXT();});
		});
				
	}
	
	public void createacctext_ONMOUSEHOVER() {
		createacctext.setFill(Color.AQUA);
	}
	
	public void createacctext_ONMOUSEEXIT() {
		createacctext.setFill(new Color(0.3843, 0.4627, 0.9882, 1.0));
	}
	
	public void changesettings() {
		StringBuilder settingscode = new StringBuilder("0000000000");
		if(reqselctoshowpass.isSelected()) {
			settingscode.setCharAt(0, '1');
		}
		new Thread() {
			public void run() {
				Text result = new Text("Updated Settings Successfully");
				result.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
				try {
					int mainupdate = connectionstmt.executeUpdate("UPDATE accinfo SET settings = " + settingscode.toString() + " WHERE id = " + currentheldID);
					settingsresulttextflow.getChildren().add(result);
				} catch(SQLException sqle) {
					sqle.printStackTrace();
					result.setText("Connection Failed");
					Text resulterror = new Text("Current Settings will apply for this session only");
					resulterror.setFont(Font.font("Ubuntu", FontWeight.BOLD, 14));
					settingsresulttextflow.getChildren().add(result);
					settingsresulttextflow.getChildren().add(resulterror);
				} finally {
					usersettings = settingscode.toString();
				}
			}
		}.start();
		
	}
	
	public void deleteHousainiAccount(ActionEvent e) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password FROM accinfo WHERE id = " + currentheldID);
		mainrs.beforeFirst();
		mainrs.next();
		TextInputDialog td = new TextInputDialog("Password");
		td.setHeaderText("Deleting the Account will delete all the saved data of the stored accounts, and will be unrecoverable." + '\n' + "To confirm deletion, Enter in your password");
		td.showAndWait();
		if(EncryptionObj.EncryptFuncHOUSAINIPASS(td.getEditor().getText(), EncryptionObj.hashKey(td.getEditor().getText())).equals(mainrs.getString("password"))) {
			Parent root = null;
			try{root = FXMLLoader.load(getClass().getResource("/loginscreen.fxml"));} catch (IOException ioe) {ioe.printStackTrace();}
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene); stage.show();
			new Thread() {
				public void run() {
					try {
						int updatedb = connectionstmt.executeUpdate("DELETE FROM accstorage WHERE id = " + currentheldID);
						int updatedb2 = connectionstmt.executeUpdate("DELETE FROM accinfo WHERE id = " + currentheldID);
						usersettings = null;
						currentusername = null;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
		} else {
			Alert wrongpassword = new Alert(AlertType.ERROR);
			wrongpassword.setHeaderText("Wrong Password" + '\n' + "Account not deleted");
			wrongpassword.showAndWait();
		}
	}
	
	public void changepasswordtochangeusername(ActionEvent e) {
		TranslateTransition translate5 = new TranslateTransition();
		translate5.setNode(housainiaccountsettingspane);
		translate5.setDuration(Duration.millis(200));
		translate5.setToX(90);
		translate5.setOnFinished((event) -> {
			housainiaccountsettingspane.setTranslateX(-90);
			
			if(housaininewpassword.getPromptText().equals("New Password")) {
				
				housaininewpassword.setPromptText("New Username");
				housainioldpassword.setVisible(false);
				statustext.setTranslateX(-150);
				statustext.setText("Current Username: " + currentusername);
				statustext.setVisible(true);
				createpassbutton.setText("Change Username");
				changeusername.setText("Change Password");
				if(LocalDate.now().getMonthValue() == currentpreviousmonth) {
					createpassbutton.setDisable(true);
					housaininewpassword.setDisable(true);
					statustext.setFill(Color.RED);
					statustext.setText("already changed username this month (" + currentusername + ")");
				} else {
					
				}
				createpassbutton.setOnAction((event2) -> {
					changeusernameHOUSAINI(event2);
				});
				
			} else if(housaininewpassword.getPromptText().equals("New Username")) {
				createpassbutton.setDisable(false);
				housaininewpassword.setDisable(false);
				statustext.setFill(Color.BLACK);
				housaininewpassword.setPromptText("New Password");
				housainioldpassword.setVisible(true);
				statustext.setTranslateX(0);
				statustext.setText("Password has been Updated!");
				statustext.setVisible(false);
				createpassbutton.setText("Change Password");
				createpassbutton.setOnAction((event2) -> {
					changepass(event2);
				});
				changeusername.setText("Change Username");
			}
			TranslateTransition translate4 = new TranslateTransition();
			translate4.setNode(housainiaccountsettingspane);
			translate4.setDuration(Duration.millis(200));
			translate4.setToX(0);
			translate4.play();
			FadeTransition translate2 = new FadeTransition();
			translate2.setNode(housainiaccountsettingspane);
			translate2.setDuration(Duration.millis(200));
			translate2.setToValue(1);
			translate2.play();
		});
		FadeTransition translate3 = new FadeTransition();
		translate3.setNode(housainiaccountsettingspane);
		translate3.setDuration(Duration.millis(200));
		translate3.setToValue(0);
		
		translate5.play();
		translate3.play();
	}
}


