package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
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
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller extends EncryptionObj {
	
	//public AnimationClass anim;
	
	//HOUSAINI LOGIN SCREEN
	@FXML
	private TextField servicenamefield, usernamefield, usernamefield1, passwordfield, passwordfield1, housaininewpassword, passwordstrengthtextfield, housainiusername, accsearch, adminnewpass;
	@FXML
	private Text errortext, servicenametext, usernametext, passwordtext, accerrorlabel, passindicator, statustext, createacctext, loginwithhousainiacctext, loginwithlocalacctext, accwarning, titlepasswordstrengthindicator, subtitlepasswordstrengthindicator, statustextadmin;
	@FXML
	private TextFlow accountdisp, passwordstrengthtextflow, settingsresulttextflow;
	@FXML
	private PasswordField housainioldpassword, housainipassword, adminoldpass;
	@FXML
	private Button createpassbutton, addaccbutton, changeusername, delaccbutton, applysettingsbutton, adminpassbutton;
	@FXML
	private ProgressIndicator passtimeindicator;
	@FXML
	private AnchorPane passanchorpane, loginanchorpane, mainanchorpane;
	@FXML
	private Pane buttoncont, passwordstrengthpane, housainiaccountsettingspane, createaccountoptionspane, acclistpane;
	@FXML
	private ChoiceBox accountselc, resolutionselc;
	@FXML
	private CheckBox reqselctoshowpass, reqpasswordcheckbox, makeusernamechangeablecheckbox, makepasswordchangeablecheckbox, makeaccountdeletablecheckbox, allowautodbbackup;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private TabPane maintabpane;
	@FXML
	private Tab addacctab, showacctab, passstrtab, admindbsettingstab, accsettings, adminsettings;
	@FXML
	private ScrollPane scrollpanewithtextf;
	@FXML
	private ProgressBar passwordstrengthprogressbar;
	@FXML
	private Line passwordstrengthlineseperator;
	@FXML
	private ColorPicker usercolorpicker;
	@FXML
	private VBox uservbox;
	@FXML
	private ImageView settingsimagelocal, settingsimageadmin;
	private static FileReader f;
	
	FileWriter writer;
	boolean canthreadrunagain = true, setactivepassrequired, changecolorofselc, createorchange;
	private static boolean loadlocalaccounts = false, islocalacc = false, reqpass, isadmin = false;
	Font orgfont;
	public static String currentusername;
	public static int currentheldID, currentpreviousmonth, i = 2;
	private static String servicestr = "", namestr = "", passwordstr = "", usersettings, useroptions, temp, temp2;
	private static List<Text> txtlist = new ArrayList<Text>();
	private static List<String> passwordlist = new ArrayList<String>();
	private static Connection connectionatt;
	private static Statement connectionstmt;//DriverManager.getConnection("jdbc:mysql://localhost/accdb?", "acc", "2994mYsQl#");
	private static Font ub, pa;
	
	ArrayList<FadeTransition> ftlistENTER = new ArrayList<FadeTransition>();
	ArrayList<TranslateTransition> ttlistENTER = new ArrayList<TranslateTransition>();
	ArrayList<FadeTransition> ftlistEXIT = new ArrayList<FadeTransition>();
	ArrayList<TranslateTransition> ttlistEXIT = new ArrayList<TranslateTransition>();
	static ArrayList<Text> usernametexts = new ArrayList<Text>();
	static ArrayList<Node> allnodesofvbox = new ArrayList<Node>();
	static ArrayList<Pane> tempsavedaccpanes = new ArrayList<Pane>();
	
	public void loginorcreatePassSeperate(ActionEvent e) throws NoSuchAlgorithmException, IOException, SQLException {
		
			if(canthreadrunagain) {
				
				canthreadrunagain = false;
				setactivepassrequired = true;
				passtimeindicator.setOpacity(1);
				accerrorlabel.setOpacity(1);
				accerrorlabel.setVisible(false);
				accerrorlabel.setVisible(true);
				accerrorlabel.setText("Verifying Password...");
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
							accerrorlabel.setOpacity(changeop);
							timedone += 50;
						}
						accerrorlabel.setOpacity(1);
							}
						};
						
						waitanimation.start();
						//Class.forName("com.mysql.cj.jdbc.Driver");
						connectionatt = DriverManager.getConnection("jdbc:mysql://localhost:3306/HPMdatabase", "HPMuniversaluser", "passwordforHPMuniversaluser");
						connectionstmt = connectionatt.createStatement();
						/*DatabaseMetaData meta = connectionatt.getMetaData();
		                System.out.println("The driver name is " + meta.getDriverName());
						connectionstmt = connectionatt.createStatement();
						connectionstmt.execute("CREATE TABLE IF NOT EXISTS accinfo(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, prevmonth TINYINT(12), password VARCHAR(3000), username VARCHAR(30), settings VARCHAR(10));");
						connectionstmt.execute("CREATE TABLE IF NOT EXISTS accstorage(primark INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id INTEGER NOT NULL, servicename VARCHAR(5000), username VARCHAR(5000), password VARCHAR(5000));");*/
						ResultSet mainrs = connectionstmt.executeQuery("SELECT id, prevmonth, password, username, settings FROM accinfo");
						
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
								temp = EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText() + "E4Af3Sd@", EncryptionObj.hashKey(housainipassword.getText()));
								islocalacc = false;
							}
							}
						} else {
							interrupt();
						}
						waitanimation.join();
						mainrs.close();
						
						} catch(SQLException | NoSuchAlgorithmException | InterruptedException | InvalidKeySpecException sql_nsa_i_CNFE) {
							sql_nsa_i_CNFE.printStackTrace();
							accerrorlabel.setText("Connection Failed");
							accerrorlabel.setOpacity(1);
							try {sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
							interrupt();
						}
					
							if(accfound2) {
							housainipassword.clear();
							housainiusername.clear();
							passtimeindicator.setVisible(false);
							accerrorlabel.setText("Verified! Opening...");
							System.out.println("Held id = " + currentheldID);
							
							
							try {sleep((long)100);} catch (InterruptedException e) {e.printStackTrace();}
							
							Platform.runLater(new Runnable() {
							    @Override
							    public void run() {
							    	Parent root = null;
									try{root = FXMLLoader.load(getClass().getResource("/PasswordManagerMain.fxml"));} catch (IOException e) {e.printStackTrace();}
									stage = (Stage)((Node)e.getSource()).getScene().getWindow();
									scene = new Scene(root);
									scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
									stage.setScene(scene); stage.show(); //maintabpane.setOpacity(0);
									
							    }
							});
							} else {
								accerrorlabel.setText("Account Details are Incorrect");
								passtimeindicator.setVisible(false);
								canthreadrunagain = true;
							}
							
						}
					}
					).start();
			}
			
	}
	
	public static void shutdown() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, SQLException {
		
		System.out.println("SHUTDOWN EXECUTED");
		ResultSet mainrs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1;");
		//mainrs.next();
		String automaticDBbackuplocation = null;
		
		if(mainrs.getBoolean("allowautodbbackup"))
			automaticDBbackuplocation = mainrs.getString("databasebackupfilelocation");
		
		backupDatabasestatic("accdb", automaticDBbackuplocation);
		
		connectionatt.close();
		connectionstmt.close();
    
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
	
	public void signuptoaccTEXT() {
		createpassbutton.setText("Create Account");
		createpassbutton.setOnAction((event) -> {
			new Thread() {
				public void run() {
					try {
						Thread waitanimation = new Thread() {
							public void run() {
							double changeop = 0.4; boolean goup = true; int timedone = 0;
							accerrorlabel.setText("Creating Account");
						
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
							accerrorlabel.setOpacity(changeop);
							timedone += 50;
						}}};
						waitanimation.start();
						boolean allow = true;
						int disallowreason = 0;
						//Class.forName("com.mysql.cj.jdbc.Driver");
						connectionatt = DriverManager.getConnection("jdbc:mysql://localhost:3306/HPMdatabase", "HPMuniversaluser", "passwordforHPMuniversaluser");
						connectionstmt = connectionatt.createStatement();
	                /*System.out.println("The driver name is " + meta.getDriverName());
					connectionstmt = connectionatt.createStatement();
					connectionstmt.execute("CREATE TABLE IF NOT EXISTS accinfo(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, prevmonth TINYINT(12), password VARCHAR(3000), username VARCHAR(30), settings VARCHAR(10));");
					connectionstmt.execute("CREATE TABLE IF NOT EXISTS accstorage(primark INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id INTEGER NOT NULL, servicename VARCHAR(5000), username VARCHAR(5000), password VARCHAR(5000));");
					*/
					ResultSet mainrs = connectionstmt.executeQuery("SELECT id, prevmonth, password, username, settings FROM accinfo");
					while(mainrs.next()) {
						if(mainrs.getString("username").equals(housainiusername.getText())) {
							disallowreason = 1; allow = false;
						}
					}
					mainrs.close();
					if(housainiusername.getText().contains("~")) {
						allow = false; disallowreason = 2;
					}
					waitanimation.join();
						 if(allow){
							connectionstmt.execute("INSERT INTO accinfo(password, username, prevmonth, settings) VALUES('" + EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText())) + "','" + housainiusername.getText() + "'," + LocalDate.now().getMonthValue() +",'0000000000');");
							
							animateboop(accerrorlabel);
							accerrorlabel.setOpacity(1);
							accerrorlabel.setText("Account Created!");
							try {sleep(3000);} catch (InterruptedException e) {}
							accerrorlabel.setOpacity(0);
						} else {
							accerrorlabel.setOpacity(1);
							accerrorlabel.setFill(Color.RED);
							if(disallowreason == 1) accerrorlabel.setText("That username already exists!");
							if(disallowreason == 2) accerrorlabel.setText("Username Cannot contain ~");
							try {sleep(3000);} catch (InterruptedException e) {}
							accerrorlabel.setOpacity(0);
							accerrorlabel.setFill(Color.BLACK);
						}
					} catch (NoSuchAlgorithmException | InterruptedException sql_nsa_iE) {
						sql_nsa_iE.printStackTrace();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					} catch (InvalidKeySpecException e1) {
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
	
	public void accsearchonsearch() {
		//loadaccounts();
		
		if(!accsearch.getText().isEmpty()) {
		ArrayList<Pane> searchedaccpanes = new ArrayList<Pane>();
		for(Node child : tempsavedaccpanes)
			if(tempsavedaccpanes.indexOf(child)>1)
				if(((Text)((Pane)child).getChildren().get(2)).getText().toString().contains(accsearch.getText()))
					searchedaccpanes.add((Pane)child);
			
		uservbox.getChildren().clear();
		for(Node child : searchedaccpanes) uservbox.getChildren().add(child);
		
		} else {
			loadaccounts();
		}
	}

	@FXML
	public void initialize() {
		//loginanchorpane.setScaleX(2);
		//loginanchorpane.setScaleY(2);
		try {
			if(islocalacc) {
				connectionatt.close();
				connectionatt = DriverManager.getConnection("jdbc:sqlite:accdb");
				DatabaseMetaData meta = connectionatt.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				connectionstmt = connectionatt.createStatement();
				
				ResultSet localaccountrs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1;");
				
				allowautodbbackup.setSelected(localaccountrs.getBoolean("allowautodbbackup"));
				localaccountrs.close();
			}
			
			Image settingsimageimg = new Image("/settingsicon.png");
			settingsimagelocal.setImage(settingsimageimg);
			settingsimageadmin.setImage(settingsimageimg);
			
			if(isadmin) {
				maintabpane.getTabs().remove(addacctab);
				maintabpane.getTabs().remove(showacctab);
				maintabpane.getTabs().remove(passstrtab);
				maintabpane.getTabs().remove(accsettings);
			} else {
				maintabpane.getTabs().remove(adminsettings);
				maintabpane.getTabs().remove(admindbsettingstab);
			}
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		loadaccounts();
		System.out.println("Executed");
	}
	
	public void loadaccounts() {
		
		if(loadlocalaccounts) {
		
		ArrayList<Pane> accpanes = new ArrayList<Pane>();
		
		new Thread() {
			public void run() {
				
							uservbox.getChildren().clear();
							
							boolean gray = false;
							
							Pane createaccpane = new Pane();
							Text createacctext = new Text("Create Account");
							createacctext.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
							createacctext.setLayoutX(55);
							createacctext.setLayoutY(26);
							createacctext.setVisible(true);
							createaccpane.getChildren().add(createacctext);
							createaccpane.setMinWidth(276);
							ftlistENTER.add(new FadeTransition());
							ttlistENTER.add(new TranslateTransition());
							ftlistEXIT.add(new FadeTransition());
							ttlistEXIT.add(new TranslateTransition());
							createaccpane.setOnMouseEntered((event) -> {
								TranslateTransition t_anim_OnMouseEntered = new TranslateTransition();
								t_anim_OnMouseEntered.setNode(createaccpane);
								t_anim_OnMouseEntered.setToX(-52);
								t_anim_OnMouseEntered.setInterpolator(Interpolator.EASE_OUT);
								t_anim_OnMouseEntered.setDuration(Duration.millis(100));
								t_anim_OnMouseEntered.play();
							});
							createaccpane.setOnMouseExited((event) -> {
								TranslateTransition t_anim_OnMouseExited = new TranslateTransition();
								t_anim_OnMouseExited.setNode(createaccpane);
								t_anim_OnMouseExited.setToX(0);
								t_anim_OnMouseExited.setInterpolator(Interpolator.EASE_IN);
								t_anim_OnMouseExited.setDuration(Duration.millis(100));
								t_anim_OnMouseExited.play();
							});
							createaccpane.setOnMouseClicked((event) -> {
								createpassbutton.setText("Create Account");
								usercolorpicker.setDisable(false);
								reqpasswordcheckbox.setSelected(true);
								reqpasswordcheckbox.setDisable(false);
								housainipassword.setDisable(false);
								housainiusername.setDisable(false);
								housainiusername.clear();
								housainipassword.clear();
								housainiusername.setPromptText("Username");
								housainipassword.setPromptText("Password");
							});
							createaccpane.setPrefHeight(52);
							createaccpane.setBackground(Background.fill(Color.web("#d71919", 0.33)));
							createaccpane.setVisible(true);
							accpanes.add(createaccpane);
							
							Pane adminaccpane = new Pane();
							Text adminacctext = new Text("DB Admin");
							adminacctext.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
							adminacctext.setLayoutX(55);
							adminacctext.setLayoutY(26);
							adminacctext.setVisible(true);
							adminaccpane.getChildren().add(adminacctext);
							adminaccpane.setMinWidth(276);
							ftlistENTER.add(new FadeTransition());
							ttlistENTER.add(new TranslateTransition());
							ftlistEXIT.add(new FadeTransition());
							ttlistEXIT.add(new TranslateTransition());
							adminaccpane.setOnMouseEntered((event) -> {
								TranslateTransition t_anim_OnMouseEntered = new TranslateTransition();
								t_anim_OnMouseEntered.setNode(adminaccpane);
								t_anim_OnMouseEntered.setToX(-52);
								t_anim_OnMouseEntered.setInterpolator(Interpolator.EASE_OUT);
								t_anim_OnMouseEntered.setDuration(Duration.millis(100));
								t_anim_OnMouseEntered.play();
							});
							adminaccpane.setOnMouseExited((event) -> {
								TranslateTransition t_anim_OnMouseExited = new TranslateTransition();
								t_anim_OnMouseExited.setNode(adminaccpane);
								t_anim_OnMouseExited.setToX(0);
								t_anim_OnMouseExited.setInterpolator(Interpolator.EASE_IN);
								t_anim_OnMouseExited.setDuration(Duration.millis(100));
								t_anim_OnMouseExited.play();
							});
							adminaccpane.setOnMouseClicked((event) -> {
								createpassbutton.setText("Admin Login");
								usercolorpicker.setDisable(true);
								reqpasswordcheckbox.setSelected(false);
								reqpasswordcheckbox.setDisable(true);
								housainipassword.setDisable(false);
								housainiusername.setPromptText("Password");
								housainipassword.setPromptText("Confirm password");
								try {
									ResultSet rs;
									String tempstr;
									rs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1");
									tempstr = rs.getString("adminpass"); 
									if(tempstr != null) {
										housainiusername.setPromptText("");
										housainipassword.setPromptText("Password");
										housainiusername.setDisable(true);
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
								
								housainiusername.clear();
								housainipassword.clear();
								
								
							});
							adminaccpane.setPrefHeight(52);
							adminaccpane.setBackground(Background.fill(Color.web("#4682db", 0.33)));
							ImageView adminicon = new ImageView("/setting.png");
							adminicon.setFitWidth(47);
							adminicon.setFitHeight(51);
							adminicon.setLayoutX(4);
							adminicon.setLayoutY(2);
							adminaccpane.getChildren().add(adminicon);
							adminaccpane.setVisible(true);
							accpanes.add(adminaccpane);
							
							//--------------------------------
							
							usernametexts.clear();
							
							try {
								
							ResultSet mainrs = connectionstmt.executeQuery("SELECT * FROM accinfolocal;");
							while(mainrs.next()) {
								
								String usernameTEMP = mainrs.getString("username"), usercolor = mainrs.getString("usercolor");
								
								accpanes.add(new Pane());
								Circle circle = new Circle();
								usernametexts.add(new Text(usernameTEMP));
								
								Stop[] stops = new Stop[] {new Stop(0, Color.web(usercolor).deriveColor(1,1,1.5,1)), new Stop(1, Color.web(usercolor).deriveColor(1, 1, 0.5, 1))};
								LinearGradient lg1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
								Text logintext = new Text("Log in");
								if(mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(usernameTEMP + "684", EncryptionObj.hashKey(usernameTEMP + "123")))) {
								logintext.setText("No Password");
								}
								logintext.setFont(Font.font("Ubuntu", FontWeight.BOLD,FontPosture.ITALIC, 21));
								logintext.setOpacity(0);
								logintext.setLayoutX(55);
								logintext.setLayoutY(46);
								
								circle.setFill(lg1);
								circle.setRadius(22);
								circle.setTranslateX(26);
								circle.setTranslateY(26);
								circle.setStrokeWidth(3);
								circle.setStroke(Color.BLACK);
								usernametexts.get(i-2).setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
								usernametexts.get(i-2).setTranslateX(55);
								usernametexts.get(i-2).setTranslateY(26);
								accpanes.get(i).getChildren().add(logintext);
								accpanes.get(i).getChildren().add(circle);
								accpanes.get(i).getChildren().add(usernametexts.get(i-2));
								accpanes.get(i).setPrefWidth(224);
								accpanes.get(i).setPrefHeight(52);
								
								accpanes.get(i).setBackground(Background.fill(gray ? Color.LIGHTGRAY : Color.WHITE));
								
								accpanes.get(i).setVisible(true);
								
								gray = !gray;
								
								ftlistENTER.add(new FadeTransition());
								ftlistENTER.get(i).setNode(accpanes.get(i).getChildrenUnmodifiable().get(0));
								ftlistENTER.get(i).setToValue(createaccpane.getBackground().equals(Background.fill(Color.GRAY))? 0.5 : 0.4);
								ftlistENTER.get(i).setDuration(Duration.millis(100));
								ttlistENTER.add(new TranslateTransition());
								ttlistENTER.get(i).setNode(accpanes.get(i));
								ttlistENTER.get(i).setToX(-52);
								ttlistENTER.get(i).setInterpolator(Interpolator.EASE_OUT);
								ttlistENTER.get(i).setDuration(Duration.millis(100));
								
								ftlistEXIT.add(new FadeTransition());
								ftlistEXIT.get(i).setNode(accpanes.get(i).getChildrenUnmodifiable().get(0));
								ftlistEXIT.get(i).setToValue(0);
								ftlistEXIT.get(i).setDuration(Duration.millis(100));
								ttlistEXIT.add(new TranslateTransition());
								ttlistEXIT.get(i).setNode(accpanes.get(i));
								ttlistEXIT.get(i).setToX(0);
								ttlistEXIT.get(i).setInterpolator(Interpolator.EASE_IN);
								ttlistEXIT.get(i).setDuration(Duration.millis(100));
								
								final int t = i;
								
								accpanes.get(i).setOnMouseEntered((event) -> {
									ftlistENTER.get(t).play();
									ttlistENTER.get(t).play();
								});
								
								accpanes.get(i).setOnMouseClicked((event) -> {
									housainipassword.clear();
									housainiusername.setText(usernametexts.get(t-2).getText());
									reqpasswordcheckbox.setDisable(true);
									housainiusername.setDisable(false);
								    createpassbutton.setText("Log in");
								    housainiusername.setPromptText("Username");
									housainipassword.setPromptText("Password");
								    usercolorpicker.setDisable(true);
								    if(logintext.getText().equals("No Password")) {
								    	reqpasswordcheckbox.setSelected(false);
								    	housainipassword.setDisable(true);
								    } else {
								    	reqpasswordcheckbox.setSelected(true);
								    	housainipassword.setDisable(false);
								    }
								});
								
								accpanes.get(i).setOnMouseExited((event) -> {
									ftlistEXIT.get(t).play();
									ttlistEXIT.get(t).play();
								});
								
								i++;
								
								//uservbox.getChildren().add(pane);
							}
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
			//changing the size of the vbox to fit all of accpanes' components
										uservbox.getChildren().clear();
										tempsavedaccpanes = accpanes;
										for(Pane addPane : accpanes) {
											uservbox.getChildren().add(addPane);
											if(uservbox.getPrefHeight() < 52 * uservbox.getChildrenUnmodifiable().size()) 
												uservbox.setPrefHeight(uservbox.getPrefHeight() + 52);
										}
										for(Node nodes : uservbox.getChildren())
										allnodesofvbox.add(nodes);
										i = 2;
									}
								});
							
							} catch(SQLException | NoSuchAlgorithmException | InvalidKeySpecException sqle) {
								sqle.printStackTrace();
							}
			}
		}.start();
		
		}
		
		
		/*try {
		String thing = "TOILET", thing2, thing3;
		thing2 = EncryptionObj.EncryptFuncHOUSAINIPASS(thing, EncryptionObj.hashKey(thing));
		thing3 = EncryptionObj.DecryptFuncHOUSAINIPASS(thing2, EncryptionObj.hashKey(thing));
		System.out.println(thing3);
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public void signuptohousainiaccTEXT() {
		initialize();
	}
		
	public void createorlogintolocalAcc(ActionEvent e) {
		
			/*createpassbutton.setDisable(true);
			acclistpane.setDisable(true);
			reqpasswordcheckbox.setDisable(true);*/
			
			new Thread() {
				public void run() {
					try {
					boolean allow = true;
					
					if(housainiusername.getText().isEmpty() && !housainiusername.isDisabled()) { animateshake(housainiusername); allow = false; }
					
					if(housainipassword.getText().isEmpty() && reqpasswordcheckbox.isSelected()) { animateshake(housainipassword); allow = false; } 
					
					if(!allow) {
						accerrorlabel.setOpacity(1);
						accerrorlabel.setText("Missing Information");
						accerrorlabel.setFill(Color.RED);
						animateboop(accerrorlabel);
					} else {
						
//----------------------------------------------------------------------------------------------------------------------
											//FIRST CASE: Creating an Account
						
						if(createpassbutton.getText().equals("Create Account")) {
							ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password, username FROM accinfolocal");
							while(mainrs.next()) {
								if(housainiusername.getText().equals(mainrs.getString("username"))) {
									allow = false;
									break;
								}
							}
							if(allow) {
//When you unselect the reqpasswordcheckbox, a menu appears with 3 other checkboxes, this will save them to the DB
								
								boolean[] booleansofaccountoptions = {makepasswordchangeablecheckbox.isSelected(), makeusernamechangeablecheckbox.isSelected(), makeaccountdeletablecheckbox.isSelected()};
								StringBuilder strb = new StringBuilder("000");
								
								if(!reqpasswordcheckbox.isSelected()) {
									if(booleansofaccountoptions[0]) strb.setCharAt(0, '1');
									if(booleansofaccountoptions[1]) strb.setCharAt(1, '1');
									if(booleansofaccountoptions[2]) strb.setCharAt(2, '1');
								}
								
							if(reqpasswordcheckbox.isSelected())
								connectionstmt.execute("INSERT INTO accinfolocal(password, username, settings, usercolor, usersettings) VALUES('" + EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText())) + "','" + housainiusername.getText() + "','0000000000','" + toHexString(usercolorpicker.getValue()) + "','" + strb.toString() + "');");
							else
								connectionstmt.execute("INSERT INTO accinfolocal(password, username, settings, usercolor, usersettings) VALUES('" + EncryptionObj.EncryptFuncHOUSAINIPASS(housainiusername.getText() + "684", EncryptionObj.hashKey(housainiusername.getText() + "123")) + "','" + housainiusername.getText() + "','0000000000','" + toHexString(usercolorpicker.getValue()) + "','" + strb.toString() + "');");	
							accerrorlabel.setFill(Color.BLACK);
							accerrorlabel.setText("Created account: " + housainiusername.getText());
							animateboop(accerrorlabel);
							
							Platform.runLater(new Runnable() {
								public void run() {
									loadaccounts();
								}});
									housainiusername.clear();
									housainipassword.clear();
									makepasswordchangeablecheckbox.setSelected(false); makeusernamechangeablecheckbox.setSelected(false); makeaccountdeletablecheckbox.setSelected(false);
							} else {
								accerrorlabel.setFill(Color.RED);
								accerrorlabel.setText("Another account exists with that name");
								animateboop(accerrorlabel);
								sleep(5000);
								accerrorlabel.setFill(Color.BLACK);
								accerrorlabel.setText("");
								makepasswordchangeablecheckbox.setSelected(false); makeusernamechangeablecheckbox.setSelected(false); makeaccountdeletablecheckbox.setSelected(false);
								
							}
						
//----------------------------------------------------------------------------------------------------------------------
									//SECOND CASE: Signing up or Logging in to Admin
						
						} else if(createpassbutton.getText().equals("Admin Login")) {
							
							String EncAdminPassword = null;
							ResultSet rs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1");
							
							if(housainiusername.getText().equals(housainipassword.getText()) || housainiusername.isDisabled()) {
							
								//1. ADMIN LOGIN FIRST CASE: Admin password is undetermined
							
								if(rs.getString("adminpass")==null) {
								
									EncAdminPassword = EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText()));
									connectionstmt.executeUpdate("UPDATE hpmgeneralsettings SET adminpass = '" + EncAdminPassword + "';");
								
									accerrorlabel.setText("Admin Password Set");
									animateboop(accerrorlabel);
									sleep(5000);
									accerrorlabel.setText("");
									
								//2. ADMIN LOGIN SECOND CASE: Admin Login Credentials are Correct
								
								} else if(rs.getString("adminpass").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText())))) {
									
									//animateboop(accerrorlabel);
									isadmin = true;
									
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											double[] exponents = {0.167,0.25,-0.5};
											LoadScene("/PasswordManagerMain.fxml", loginwithhousainiacctext, exponents);
										}
									});
									
								//3. ADMIN LOGIN THIRD CASE: Admin Login Credentials are Incorrect
									
								} else if(!rs.getString("adminpass").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText())))) {
									/*accerrorlabel.setFill(Color.RED);
									accerrorlabel.setText("Admin Login Credentials are Incorrect");
									animateboop(accerrorlabel);
									sleep(5000);
									accerrorlabel.setFill(Color.BLACK);
									accerrorlabel.setText("");*/
								}
							
							}
						
//----------------------------------------------------------------------------------------------------------------------
										//THIRD CASE: Logging into an Account
						
						} else {
							allow = false;
							ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password, username, settings, usersettings FROM accinfolocal");
							boolean r = false;
							while(mainrs.next()) {
								if(mainrs.getString("username").equals(housainiusername.getText()) && (mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText()))) || (r = mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainiusername.getText() + "684", EncryptionObj.hashKey(housainiusername.getText() + "123")))))) {
									allow = true; 
									currentheldID = mainrs.getInt("id");
									usersettings = mainrs.getString("settings");
									currentusername = housainiusername.getText();
									reqpass = !r;
									islocalacc = true;
									currentpreviousmonth = 15;
									useroptions = mainrs.getString("usersettings");
									if(!r)
										temp = EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText() + "E4Af3Sd@", EncryptionObj.hashKey(housainipassword.getText()));
									else
										temp = EncryptionObj.EncryptFuncHOUSAINIPASS(Integer.toString(mainrs.getInt("id")) + "%8ggdF3r", EncryptionObj.hashKey("id"));
									//System.out.println(EncryptionObj.DecryptFuncHOUSAINIPASS(mainrs.getString("password"), EncryptionObj.hashKey(housainiusername.getText() + "123")));
									
								}
							}
							if(allow) {
								
								housainipassword.setDisable(false);
								acclistpane.setVisible(true);
								acclistpane.setDisable(false);
								createaccountoptionspane.setVisible(false);
								accerrorlabel.setFill(Color.BLACK);
								accerrorlabel.setText("Verified, Logging you in...");
								sleep(50);
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										double[] exponents = {0.167,0.25,-0.5};
										LoadScene("/PasswordManagerMain.fxml", loginwithhousainiacctext, exponents);
									}
								});
							} else {
								accerrorlabel.setOpacity(1);
								accerrorlabel.setFill(Color.RED);
								accerrorlabel.setText("Wrong account information");
							}
							
							
						}
					}
					
					} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException | InterruptedException sql_nsa_iks_ie) {
						sql_nsa_iks_ie.printStackTrace();
						accerrorlabel.setText("Something went wrong");
					}
					
					/*createpassbutton.setDisable(false);
					acclistpane.setDisable(false);
					reqpasswordcheckbox.setDisable(false);*/
					
				}
			}.start();
		}
	
	public void requirespassword(ActionEvent e) {
		if(!reqpasswordcheckbox.isSelected()) {
			housainipassword.setDisable(true);
			acclistpane.setVisible(false);
			acclistpane.setDisable(true);
			createaccountoptionspane.setVisible(true);
			makepasswordchangeablecheckbox.setSelected(false);
			makeusernamechangeablecheckbox.setSelected(false);
			makeaccountdeletablecheckbox.setSelected(false);
		} else {
			housainipassword.setDisable(false);
			acclistpane.setVisible(true);
			acclistpane.setDisable(false);
			createaccountoptionspane.setVisible(false);
		}
	}
	
	public void localdbbackup(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Back up Database");
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQLite Database Files", "*.db"));
        
        File file = fileChooser.showSaveDialog((Stage)((Node)e.getSource()).getScene().getWindow());
        if (file != null) {
            backupDatabase("accdb", file.getAbsolutePath());
        }
    }
    
    public void automaticdbbackup(ActionEvent e) {
    	
    	try {
			DirectoryChooser directoryChooser = new DirectoryChooser();
	        directoryChooser.setTitle("Automatic Database Backup Location");
	        File filelocation = directoryChooser.showDialog((Stage)((Node)e.getSource()).getScene().getWindow());
			
			boolean createnewuser = true;
	        connectionstmt.executeUpdate("UPDATE hpmgeneralsettings SET databasebackupfilelocation = '" + filelocation.getAbsolutePath() + "' WHERE primark = 1;");
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
		
    }
	
	public void allowautomaticdbbackup(ActionEvent e) {
		try {
			if(allowautodbbackup.isSelected()) {
				connectionstmt.executeUpdate("UPDATE hpmgeneralsettings SET allowautodbbackup = true WHERE primark = 1;");
			} else {
				connectionstmt.executeUpdate("UPDATE hpmgeneralsettings SET allowautodbbackup = false WHERE primark = 1;");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	
	}
	
	public void dbrestore(ActionEvent e) throws IOException {
		/*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Database Backup");
        File file = fileChooser.showSaveDialog((Stage)((Node)e.getSource()).getScene().getWindow());
        
        //StringBuilder fileb = new StringBuilder(file.getPath());
        ArrayList<Character> filebarr = new ArrayList<Character>();
        StringBuilder filepath = new StringBuilder(file.getPath());
        
        /*for(int i = 0; i < filebarr.size(); i++) {
        	filebarr.add(filepath.charAt(i));
        }
        
        int numberofleftslashes, numberofrightslashes, indexofleft, indexofright;
        
        for(char a : filebarr) {
        	
        	if(a=='/') {
        		numberofleftslashes += 1;
        		indexofleft = filebarr.indexOf(a);
        	} else if(a == '\\') {
        		numberofrightslashes += 1;
        		indexofright = filebarr.indexOf(a);
        	}
        			
        }
        
        if(numberofleftslashes > 0 && numberofrightslashes > 0) {
        	throw new IOException("Currently, HPM is unable to support a file path which contains both forward facing slashes and back facing slashes");
        }
        
        replaceDatabaseWithBackup("/accdb", file.getPath(), "/accdb");*/
		
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Backup Database File");
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database Files", "*.db", "*.*"));

        File selectedFile = fileChooser.showOpenDialog((Stage)((Node)e.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            String originalDbPath = null;
			try {originalDbPath = getDatabasePath(connectionatt);} catch (SQLException e1) { e1.printStackTrace(); }
            String backupDbPath = selectedFile.getAbsolutePath();

            try {
                restoreBackup(backupDbPath, originalDbPath);
                System.out.println("Backup restored successfully.");
            } catch (IOException q) {
                System.err.println("Error restoring backup: " + q.getMessage());
                q.printStackTrace();
            }
        }
        
        //Platform.exit();
	}
    
	public void logout(ActionEvent e) throws SQLException {
		islocalacc = false;
		isadmin = false;
		connectionstmt.close();
		connectionatt.close();
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				double[] exponents = {0.167,0.25,-0.5};
				LoadScene("/loginscreen.fxml", mainanchorpane, exponents);
			}
		});
	}
	
	//----------------------------- THE ACTUAL PASSWORD MANAGER -----------------------------------------------
	// ******************************************************************************************************
	//----------------------------- THE ACTUAL PASSWORD MANAGER -----------------------------------------------
	
	//stracc = account belonging to the accstorage database (to differentiate between these and local/housaini accounts)
	
	//adding currently unadded stracc to storage
	public void AddAccounttoStorage(ActionEvent e) throws IOException, NoSuchAlgorithmException {
		
		if(servicenamefield.getText() == null || servicenamefield.getText().isEmpty() || usernamefield.getText() == null || usernamefield.getText().isEmpty() || passwordfield.getText() == null || passwordfield.getText().isEmpty() ) {
			
			//alerting user if a field is empty
			if(servicenamefield.getText() == null || servicenamefield.getText().isEmpty()) animateshake(servicenamefield);
			else servicenamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			if(usernamefield.getText() == null || usernamefield.getText().isEmpty()) animateshake(usernamefield);
			else usernamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			if(passwordfield.getText() == null || passwordfield.getText().isEmpty()) animateshake(passwordfield);
			else passwordfield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			errortext.setText("Field(s) cannot be empty!");
		
		} else {
			
			//setting field backdrop to green
			servicenamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			usernamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			passwordfield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			servicestr = servicenamefield.getText();
			namestr = usernamefield.getText();
			passwordstr = passwordfield.getText();
			
			new Thread() {
				public void run() {
					try {
						sleep(30);
						String tablename = "accstorage";
						if(islocalacc) tablename = "accstoragelocal";
							connectionstmt.executeUpdate("INSERT INTO " + tablename + "(servicename, username, password, id) VALUES('"
							+ EncryptionObj.EncryptFunc(servicestr, temp) + "','"
							+ EncryptionObj.EncryptFunc(namestr, temp) + "','"
							+ EncryptionObj.EncryptFunc(passwordstr, temp) + "',"
							+ currentheldID + ");");
							
							
					
					} catch(SQLException | NoSuchAlgorithmException | InterruptedException sql_nsaE) {
						
						errortext.setText("Database Connection Failed");
						sql_nsaE.printStackTrace();
					}
				}
			}.start();
		}
		
		errortext.setText("Added account: " + usernamefield.getText());
		servicenamefield.setText(null);
		passwordfield.setText(null);
		usernamefield.setText(null);
	
	}
	
	//displays accounts
	public void showaccounts() throws IOException, NoSuchAlgorithmException {
			    
		passwordlist.clear();
		txtlist.clear();
		accountdisp.getChildren().clear();
		accountselc.getItems().clear();
		accountdisp.setStyle("-fx-background-color: #ffffff");
			
		Text announcetext = new Text("Select an Account by Left Clicking on the Service Name" + '\n');
		announcetext.setFill(Color.web("#186f0f"));
		announcetext.setFont(Font.font("Ubuntu", FontWeight.BOLD, 18));
		accountdisp.getChildren().add(announcetext);
		
		new Thread() {
			
			int i = 0;
			
			public void run() {
			try {
				String tablename = "accstorage";
				if(islocalacc) tablename = "accstoragelocal";
				ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM "+tablename+" WHERE id=" + currentheldID);
				while(mainrs.next()) {
				
					servicestr = EncryptionObj.DecryptFunc(mainrs.getString("servicename"), temp);
					namestr = EncryptionObj.DecryptFunc(mainrs.getString("username"), temp);
					passwordstr = EncryptionObj.DecryptFunc(mainrs.getString("password"), temp);
			
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
						
						//gets any text child object that is purple and sets it to white
						for(Node childnode : accountdisp.getChildren()) {
							if(childnode.getClass().equals(Text.class) && (ttemp = (Text)childnode).getFill().equals(Color.PURPLE))
								ttemp.setFill(Color.WHITE);
						}
		    	
						(new Thread() {
							public void run() {
								String tempstr = "";
								
								Paint tempcolor = accwarning.getFill();
								accwarning.setFill(Color.BLUE);
								accwarning.setText("Account has been selected!");
								
								if(usersettings.charAt(0) == '1') {
									Text t = (Text) accountdisp.getChildren().get((accountselc.getItems().indexOf(accountselc.getValue()) + 1)*3 + accountselc.getItems().indexOf(accountselc.getValue()));
						
									t.setFill(Color.PURPLE); //else t.setFill(Color.WHITE);
									t.setOnMouseClicked((event) -> t.setFill(Color.WHITE));
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
						if(accwarning.getText().isEmpty()) accwarning.setText("password: " + passwordstr + " is used multiple times!");
						else accwarning.setText(accwarning.getText().replaceAll(": ", "s: " + passwordstr + ", ").replaceAll(" is used multiple times!", " are used multiple times!"));
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
	
	//deletes the contents of the vbox belonging to the "Stored Accounts" tab upon closing
	public void deletetextfromdisp() {
		accountdisp.getChildren().clear();
		accwarning.setText("");
	}
	
	//deletes a stracc
	public void deleteaccountfromstorage(ActionEvent theactioneventofdel) throws IOException {
			try {
				
				int primarykeyfordeletingline = 0;
				String tablename = "accstorage";
				if(islocalacc) tablename = "accstoragelocal";
				ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM " + tablename + " WHERE id=" + Integer.toString(currentheldID));
				int f = 0; String usernametodelete = "";
				while(mainrs.next()) {
					if(f==accountselc.getItems().indexOf(accountselc.getValue())) {
						System.out.println(primarykeyfordeletingline = mainrs.getInt("primark"));
						usernametodelete = mainrs.getString("username");
						break;
					}
					else f++;
				}
		
				
				Alert alert = new Alert(AlertType.CONFIRMATION, "Confirm Deletion of the account information of: " + EncryptionObj.DecryptFunc(usernametodelete, temp) + "?", ButtonType.YES, ButtonType.NO);
				alert.showAndWait();
				mainrs.close();
				
				if (alert.getResult() == ButtonType.YES) {
					connectionstmt.executeUpdate("DELETE FROM " + tablename + " WHERE primark=" + primarykeyfordeletingline);
					deletetextfromdisp();
					showaccounts();
				}
			} catch(SQLException | NoSuchAlgorithmException | IOException sqlE) {sqlE.printStackTrace();}
	}

	//opens the window from which the stracc's details can be changed, and preparing the name and password in their respective fields
	public void changedetails(ActionEvent e) throws NoSuchAlgorithmException, IOException {
		new Thread() {
			public void run(){
				try {
					String tablename = "accstorage";
					if(islocalacc) tablename = "accstoragelocal";
					ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM " + tablename + " WHERE id=" + Integer.toString(currentheldID));
					
					int f = 0;
					while(mainrs.next()) {
					if(f == accountselc.getItems().indexOf(accountselc.getValue())) {
					passwordfield1.setText(EncryptionObj.DecryptFunc(mainrs.getString("password"), temp));
					usernamefield1.setText(EncryptionObj.DecryptFunc(mainrs.getString("username"), temp));
					break;
						} else f++;		
					}
					} catch (SQLException | NoSuchAlgorithmException sql_nsaE) {sql_nsaE.printStackTrace();}
			}
		}.start();
		
			accountdisp.getChildren().clear();
	
		scrollpanewithtextf.setVisible(false);
		scrollpanewithtextf.setDisable(true);
		
		
	}
	
	//changes the details of the stracc
	public void ChangeAccountofStorage(ActionEvent e) throws IOException, NoSuchAlgorithmException {
		
		new Thread() {
			public void run() {
				try {
					//connectionstmt.executeUpdate("UPDATE accstorage(username, password) VALUES('" + EncryptionObj.EncryptFunc(usernamefield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231") + "','" + EncryptionObj.EncryptFunc(passwordfield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231") + "') WHERE id = " + currentheldID);
					String tablename = "accstorage", tempprimark = "";
					if(islocalacc) tablename = "accstoragelocal";
					ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM "+tablename+" WHERE id=" + Integer.toString(currentheldID));
					int f = 0;
					while(mainrs.next()) {
						if(f == accountselc.getItems().indexOf(accountselc.getValue())) {
						tempprimark = mainrs.getString("primark"); break;
						} else f++;
						}
						connectionstmt.executeUpdate("UPDATE "+tablename+" SET username = '" + EncryptionObj.EncryptFunc(usernamefield1.getText(), temp) + "', password = '" + EncryptionObj.EncryptFunc(passwordfield1.getText(), temp) + "' WHERE primark = " + tempprimark + " ;");
						//
					//mainrs.absolute( + 1);
					/*mainrs.updateString("username", EncryptionObj.EncryptFunc(usernamefield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					mainrs.updateString("password", EncryptionObj.EncryptFunc(passwordfield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231"));
					mainrs.updateRow();*/
					
					//mainrs.close();
					
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

	//cancels the "ChangeAccountofStorage" Operation, and returns back to the account list pane
	public void CancelOperation(ActionEvent e) throws NoSuchAlgorithmException, IOException {
		scrollpanewithtextf.setVisible(true);
		scrollpanewithtextf.setDisable(false);
		usernamefield1.clear();
		passwordfield1.clear();
		scrollpanewithtextf.setVisible(true);
		scrollpanewithtextf.setDisable(false);
		showaccounts();
	}
	
	//evaluates the strength of a password
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
	
	//---------------------------------------------- SETTINGS --------------------------------------------------------
	//################################################################################################################
	//---------------------------------------------- SETTINGS --------------------------------------------------------
		
	//takes the old password, if correct: changes the password of the account to the new password
	public void changepass(ActionEvent e) {
		
		if(!housainioldpassword.getText().isEmpty() && !housaininewpassword.getText().isEmpty()) {
		new Thread() {
			public void run() {
		try {
			if(!islocalacc) {
				ResultSet mainrs = connectionstmt.executeQuery("SELECT id, prevmonth, password, username, settings FROM accinfo WHERE id = " + currentheldID);
			
					mainrs.next();
					if(mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainioldpassword.getText(), EncryptionObj.hashKey(housainioldpassword.getText()))) && mainrs.getString("id").equals(Integer.toString(currentheldID))) {
						connectionstmt.executeUpdate("UPDATE accinfo SET password = '" + EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText(), EncryptionObj.hashKey(housaininewpassword.getText())) + "' WHERE id = " + currentheldID + ";");
					}
				
				
				temp2 = EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText() + "E4Af3Sd@", EncryptionObj.hashKey(housaininewpassword.getText()));
				
				ResultSet straccRS = connectionstmt.executeQuery("SELECT id, servicename, username, password, primark FROM accstorage WHERE id = " + currentheldID);
				
				while(straccRS.next()) {
						
						String tempSN, tempUN, tempP;
						tempSN = EncryptionObj.EncryptFunc(EncryptionObj.DecryptFunc(straccRS.getString("servicename"), temp), temp2);
						tempUN = EncryptionObj.EncryptFunc(EncryptionObj.DecryptFunc(straccRS.getString("username"), temp), temp2);
						tempP = EncryptionObj.EncryptFunc(EncryptionObj.DecryptFunc(straccRS.getString("password"), temp), temp2);
						
						PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE accstorage SET servicename = ?, username = ?, password = ? WHERE primark = ?");
						pstmt.setString(1, tempSN); pstmt.setString(2, tempUN); pstmt.setString(3, tempP);
						pstmt.setInt(4, straccRS.getInt("primark"));
						pstmt.execute();
				}
				
				temp = temp2;
			} else {
				ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password, username, settings FROM accinfolocal WHERE id = " + currentheldID);
				
				while(mainrs.next()) {
					if(mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainioldpassword.getText(), EncryptionObj.hashKey(housainioldpassword.getText()))) && mainrs.getString("id").equals(Integer.toString(currentheldID))) {
						PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE accinfolocal SET password = ? WHERE id = ?");
						pstmt.setString(1, EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText(), EncryptionObj.hashKey(housaininewpassword.getText())));
						pstmt.setInt(2, currentheldID);
						pstmt.execute();
						temp2 = EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText() + "E4Af3Sd@", EncryptionObj.hashKey(housaininewpassword.getText()));
					}
				}
				
				ResultSet straccRS = connectionstmt.executeQuery("SELECT id, servicename, username, password, primark FROM accstoragelocal WHERE id = " + currentheldID);
				
				while(straccRS.next()) {
						
						String tempSN, tempUN, tempP;
						tempSN = EncryptionObj.EncryptFunc(EncryptionObj.DecryptFunc(straccRS.getString("servicename"), temp), temp2);
						tempUN = EncryptionObj.EncryptFunc(EncryptionObj.DecryptFunc(straccRS.getString("username"), temp), temp2);
						tempP = EncryptionObj.EncryptFunc(EncryptionObj.DecryptFunc(straccRS.getString("password"), temp), temp2);
						
						PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE accstoragelocal SET servicename = ?, username = ?, password = ? WHERE primark = ?");
						pstmt.setString(1, tempSN); pstmt.setString(2, tempUN); pstmt.setString(3, tempP);
						pstmt.setInt(4, straccRS.getInt("primark"));
						pstmt.execute();
				}
				
				temp = temp2;
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
	
	//takes the old DB ADMIN password, if correct: changes the password of DB ADMIN to the new password\
	public void changeDBADMINpass(ActionEvent e) {
		if(!adminoldpass.getText().isEmpty() && !adminnewpass.getText().isEmpty()) {
			new Thread() {
				public void run() {
					try {
						ResultSet adminrs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1;");
						final String oldactualpass = adminrs.getString("adminpass"), oldtestedpass = EncryptionObj.EncryptFuncHOUSAINIPASS(adminoldpass.getText(), EncryptionObj.hashKey(adminoldpass.getText())), newpass = EncryptionObj.EncryptFuncHOUSAINIPASS(adminnewpass.getText(), EncryptionObj.hashKey(adminnewpass.getText()));
						if(oldactualpass.equals(oldtestedpass)) {
							connectionstmt.executeUpdate("UPDATE hpmgeneralsettings SET adminpass = '" + newpass + "' WHERE primark = 1;");
							statustextadmin.setVisible(true);
							sleep(5000);
							statustextadmin.setVisible(false);
						} else {
							System.out.println("Wrong Password, " + oldtestedpass + " " + oldactualpass + " " + adminoldpass.getText());
						}
					} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException | InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}.start();
		} else {
		}
	}
	
	//changes the username of the account
	public void changeusername(ActionEvent e) {
		if(!housaininewpassword.getText().isEmpty()) {
			new Thread() {
				public void run() {
					try {
						if(!islocalacc) {
							int mainrs = connectionstmt.executeUpdate("UPDATE accinfo SET username = " + "'" + housaininewpassword.getText() + "'" + " WHERE id = " + currentheldID + ";");
							mainrs = connectionstmt.executeUpdate("UPDATE accinfo SET prevmonth = " + (currentpreviousmonth = LocalDate.now().getMonthValue()) + " WHERE id = " + currentheldID + ";");
						} else {
							PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE accinfolocal SET username = ? WHERE id = ?");
							pstmt.setString(1, housaininewpassword.getText());
							pstmt.setInt(2, currentheldID);
							pstmt.execute();
						}
						
						statustext.setText("Username has been Updated!");
						animateboop(statustext);
						statustext.setVisible(true);
						currentusername = housaininewpassword.getText();
						housaininewpassword.setText("");
						housaininewpassword.setDisable(true);
						createpassbutton.setDisable(true);
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
	
	//this method is automatically executed once the settings tab is opened, for configuration
	public void opensettings() throws IOException {
		if(usersettings == null) usersettings = "0000000000";
		statustext.setVisible(false);
		reqselctoshowpass.setSelected(usersettings.charAt(0) == '1');
		/*if(!reqpass) {
			housaininewpassword.setDisable(true);
			housainioldpassword.setDisable(true);
			createpassbutton.setDisable(true);
		}*/
		resolutionselc.getItems().clear();
		resolutionselc.getItems().add("0.5x (200x300)");
		resolutionselc.getItems().add("1x (400x600)");
		resolutionselc.getItems().add("1.5x (600x900)");
		resolutionselc.getItems().add("2x (800x1200)");
		
		
		f=new FileReader("settingsfile");
		char[] inputfromf = new char[100];
		f.read(inputfromf);
		if(inputfromf[0] == '1') {
			resolutionselc.setValue("1x (400x600)");
		} else if(inputfromf[0] == '2') {
			resolutionselc.setValue("1.5x (600x900)");
		} else if(inputfromf[0] == '3') {
			resolutionselc.setValue("2x (800x1200)");
		} else if(inputfromf[0] == '0') {
			resolutionselc.setValue("0.5x (200x300)");
		}
	}
	
	//executes upon pressing the "apply" button
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
						if(!islocalacc) {
						int mainupdate = connectionstmt.executeUpdate("UPDATE accinfo SET settings = '" + settingscode.toString() + "' WHERE id = " + currentheldID);
						} else {
							PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE accinfolocal SET settings = ? WHERE id = ?");
							pstmt.setString(1, settingscode.toString());
							pstmt.setInt(2, currentheldID);
							pstmt.execute();
						}
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
			
			if(resolutionselc.getValue().equals("1x (400x600)")) {
				try {writetofile("1", "settingsfile", false);} catch (IOException e) {e.printStackTrace();}
			} else if(resolutionselc.getValue().equals("1.5x (600x900)")) {
				try {writetofile("2", "settingsfile", false);} catch (IOException e) {e.printStackTrace();}
			} else if(resolutionselc.getValue().equals("2x (800x1200)")) {
				try {writetofile("3", "settingsfile", false);} catch (IOException e) {e.printStackTrace();}
			} else if(resolutionselc.getValue().equals("0.5x (200x300)")) {
				try {writetofile("0", "settingsfile", false);} catch (IOException e) {e.printStackTrace();}
			}
			
			double[] exponents = {0.167,0.25,-0.5};
			AnchorPane root = LoadScene("/PasswordManagerMain.fxml", applysettingsbutton, exponents);
			((TabPane)((AnchorPane)(root.getChildren().getFirst())).getChildren().getFirst()).getSelectionModel().select(3);
			
		}
			
	//deletes the account from accinfo/accinfolocal and all of its info in the accstorage
	public void deleteAccount(ActionEvent e) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
			ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password FROM accinfo WHERE id = " + currentheldID);
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
							if(!islocalacc) {
								int updatedb = connectionstmt.executeUpdate("DELETE FROM accstorage WHERE id = " + currentheldID);
								int updatedb2 = connectionstmt.executeUpdate("DELETE FROM accinfo WHERE id = " + currentheldID);
							} else {
								PreparedStatement pstmt1 = connectionatt.prepareStatement("DELETE FROM accstoragelocal WHERE id = " + currentheldID);
								PreparedStatement pstmt2 = connectionatt.prepareStatement("DELETE FROM accinfolocal WHERE id = " + currentheldID);
								pstmt1.execute();
								pstmt2.execute();
							}
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
		
	//sets up the other pane (change pass / change username, depending on whats opened currently) and animates it
	public void changepasswordtochangeusername(ActionEvent e) {
			TranslateTransition translate5 = new TranslateTransition();
			translate5.setNode(housainiaccountsettingspane);
			translate5.setDuration(Duration.millis(200));
			translate5.setToX(90);
			translate5.setOnFinished((event) -> {
				housainiaccountsettingspane.setTranslateX(-90);
				
				if(housaininewpassword.getPromptText().equals("New Password")) {
					
					createpassbutton.setDisable(false);
					housaininewpassword.setDisable(false);
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
					}
					if(islocalacc && useroptions.charAt(1) == '0') {
						createpassbutton.setDisable(true);
						housaininewpassword.setDisable(true);
						statustext.setFill(Color.RED);
						statustext.setText("This account's username is unchangeable");
					}
					createpassbutton.setOnAction((event2) -> {
						changeusername(e);
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
					if(useroptions.charAt(0) == '0') {
						createpassbutton.setDisable(true);
						housaininewpassword.setDisable(true);
						housainioldpassword.setDisable(true);
						//statustext.setFill(Color.RED);
						//statustext.setText("This account's username is unchangeable");
					}
				}
				TranslateTransition translate4 = new TranslateTransition(); translate4.setNode(housainiaccountsettingspane); translate4.setDuration(Duration.millis(200)); translate4.setToX(0); translate4.play();
				FadeTransition translate2 = new FadeTransition(); translate2.setNode(housainiaccountsettingspane); translate2.setDuration(Duration.millis(200)); translate2.setToValue(1); translate2.play();
			});
			FadeTransition translate3 = new FadeTransition(); translate3.setNode(housainiaccountsettingspane); translate3.setDuration(Duration.millis(200)); translate3.setToValue(0);
			
			translate5.play();
			translate3.play();
		}
	
	public void changewindowsize(ActionEvent e) {
		
	}
	
	//-------------------------------------- ANIMATIONS & DESIGN ----------------------------------------------------
	// ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	//-------------------------------------- ANIMATIONS & DESIGN ----------------------------------------------------

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

	public void createacctext_ONMOUSEHOVER() {
		createacctext.setFill(Color.AQUA);
	}
	
	public void createacctext_ONMOUSEEXIT() {
		createacctext.setFill(new Color(0.3843, 0.4627, 0.9882, 1.0));
	}
	
	public void createlocalacctext_ONMOUSEHOVER() {
		loginwithlocalacctext.setFill(Color.AQUA);
	}
	
	public void createlocalacctext_ONMOUSEEXIT() {
		loginwithlocalacctext.setFill(new Color(0.3843, 0.4627, 0.9882, 1.0));
	}

	//---------------------------------------------- MISCELLANEOUS -------------------------------------------------------
	// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//---------------------------------------------- MISCELLANEOUS -------------------------------------------------------

	public void signuptolocalaccTEXT() {
		
		try {
			
			connectionatt = DriverManager.getConnection("jdbc:sqlite:accdb");
			DatabaseMetaData meta = connectionatt.getMetaData();
			System.out.println("The driver name is " + meta.getDriverName());
			connectionstmt = connectionatt.createStatement();
			connectionstmt.execute("CREATE TABLE IF NOT EXISTS accinfolocal(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, password VARCHAR(3000), username VARCHAR(30), settings VARCHAR(10), usersettings VARCHAR(10), usercolor VARCHAR(7));");
			connectionstmt.execute("CREATE TABLE IF NOT EXISTS accstoragelocal(primark INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id INTEGER NOT NULL, servicename VARCHAR(5000), username VARCHAR(5000), password VARCHAR(5000));");
			connectionstmt.execute("CREATE TABLE IF NOT EXISTS hpmgeneralsettings(primark INTEGER NOT NULL PRIMARY KEY, databasebackupfilelocation VARCHAR(5000), allowautodbbackup BOOLEAN, adminpass VARCHAR(5000));");
			ResultSet rs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1;");
			if(rs.getInt("primark") != 1) {
			connectionstmt.executeUpdate("INSERT INTO hpmgeneralsettings(primark) VALUES(1);");
			}
			loadlocalaccounts = true;
			
			double[] exponents = {0.25,0.5,-0.25};
			LoadScene("/loginscreenLocal.fxml", loginwithlocalacctext, exponents);
		
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			accerrorlabel.setText("Failed to connect to local Database");
		}
	}

	public AnchorPane LoadScene(String scenename, Node nodetouse, double[] sizeexponents) {
		/*Platform.runLater(new Runnable() {
		    @Override
		    public void run() {*/
		    	try {
		    	AnchorPane root = null;
				try{root = (AnchorPane)FXMLLoader.load(getClass().getResource(scenename));
				//FXMLLoader.load(getClass().getResource(scenename));
				} catch (IOException ioe) {ioe.printStackTrace();}
				
				
				
				f = new FileReader("settingsfile");
				char[] settingsfiletext = new char[1000];
				f.read(settingsfiletext);
				System.out.println(settingsfiletext);
				
				if(settingsfiletext[0] == '2') {
					
					root.setPrefHeight(600); root.setPrefWidth(900);
					AnchorPane rootChild = (AnchorPane)root.getChildren().getFirst();
					rootChild.setScaleX(1.5); rootChild.setScaleY(1.5);
					rootChild.setTranslateX(root.getPrefWidth()*sizeexponents[0]); rootChild.setTranslateY(root.getPrefHeight()*sizeexponents[0]);
					
				} else if(settingsfiletext[0] == '3') {
					
					root.setPrefHeight(800); root.setPrefWidth(1200);
					AnchorPane rootChild = (AnchorPane)root.getChildren().getFirst();
					rootChild.setScaleX(2); rootChild.setScaleY(2);
					rootChild.setTranslateX(root.getPrefWidth()*sizeexponents[1]); rootChild.setTranslateY(root.getPrefHeight()*sizeexponents[1]);
					
				} else if(settingsfiletext[0] == '0') {
					
					root.setPrefHeight(200); root.setPrefWidth(300);
					AnchorPane rootChild = (AnchorPane)root.getChildren().getFirst();
					rootChild.setScaleX(0.5); rootChild.setScaleY(0.5);
					rootChild.setTranslateX(root.getPrefWidth()*sizeexponents[2]); rootChild.setTranslateY(root.getPrefHeight()*sizeexponents[2]);
					
				}
				
				stage = (Stage)nodetouse.getScene().getWindow();
				scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene); stage.show(); //maintabpane.setOpacity(0);
				return root;
		    	} catch (IOException e) {e.printStackTrace();}
		    	return null;
		    }

	private String format(double val) {
	    String in = Integer.toHexString((int) Math.round(val * 255));
	    return in.length() == 1 ? "0" + in : in;
	}

	public String toHexString(Color value) {
	    return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
	            .toUpperCase();
	}

	private void writetofile(String input, String filename, boolean appendmode) throws IOException {
		writer = new FileWriter(filename, appendmode);
		writer.append(input + '\n');
		writer.close();
	}
	
	private void backupDatabase(String sourcePath, String destPath) {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            System.out.println("Database backup successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static void backupDatabasestatic(String sourcePath, String destPath) {
		
		LocalTime currenttimeforbackup = LocalTime.now();
		DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTimeCustom = currenttimeforbackup.format(customFormatter);
        
		String filename = "/accdb BACKUP - " + LocalDate.now() + " - " + formattedTimeCustom;
        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath + filename);
        
        

        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            System.out.println("Database backup successful!");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

	public void restoreBackup(String backupDbPath, String originalDbPath) throws IOException {
        Path backupDbFile = new File(backupDbPath).toPath();
        Path originalDbFile = new File(originalDbPath).toPath();

        // Ensure the backup file exists
        if (!Files.exists(backupDbFile)) {
            throw new IOException("Backup database file does not exist: " + backupDbPath);
        }

        try {
			connectionstmt.close();
			connectionatt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        

        // Replace the original database file with the backup
        Files.copy(backupDbFile, originalDbFile, StandardCopyOption.REPLACE_EXISTING);

        // Optional: Delete the backup file if needed
        // Files.delete(backupDbFile);
    }
	public static String getDatabasePath(Connection conn) throws SQLException {
        String databasePath = null;
        String sql = "PRAGMA database_list";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String path = rs.getString("file");
                if ("main".equals(name)) {
                    databasePath = path;
                    break;
                }
            }
        }
        
        return databasePath; 
	}

}

/*
public void setline(int linenum, String line) throws IOException {
List<String> lines = Files.readAllLines(Paths.get("AccountStorage.txt"), StandardCharsets.UTF_8);
new FileWriter("AccountStorage.txt", false).close();

if(line.equals("-+-+DELETE")) lines.remove(linenum); else lines.set(linenum, line);

for(int i = 0; i < lines.size(); i++) {
	writetofile(lines.get(i), "AccountStorage.txt", true);
}
}
public void confirmnewaccountcreation() throws SQLException, NoSuchAlgorithmException {
		
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
	
	
*/