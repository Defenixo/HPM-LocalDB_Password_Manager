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
import java.nio.file.attribute.PosixFilePermissions;
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
import java.util.Optional;
import java.util.Random;
import java.util.Arrays;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
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
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class Controller extends EncryptionObj {
	
	//public AnimationClass anim;
	
	//HOUSAINI LOGIN SCREEN
	@FXML
	private TextField servicenamefield, usernamefield, usernamefield1, passwordfield, passwordfield1, housaininewpassword, passwordstrengthtextfield, housainiusername, accsearch, adminnewpass, straccsearch;
	@FXML
	private Text errortext, servicenametext, usernametext, passwordtext, accerrorlabel, passindicator, statustext, createacctext, loginwithlocalacctext, accwarning, titlepasswordstrengthindicator, subtitlepasswordstrengthindicator, statustextadmin, changeinfoofacctext;
	@FXML
	private TextFlow passwordstrengthtextflow, settingsresulttextflow;
	@FXML
	private PasswordField housainioldpassword, housainipassword, adminoldpass;
	@FXML
	private Button createpassbutton, addaccbutton, changeusername, delaccbutton, applysettingsbutton, adminpassbutton, AddAccbutton1;
	@FXML
	private ProgressIndicator passtimeindicator;
	@FXML
	private AnchorPane passanchorpane, loginanchorpane, mainanchorpane, storedaccountsanchorpane;
	@FXML
	private Pane buttoncont, passwordstrengthpane, housainiaccountsettingspane, createaccountoptionspane, acclistpane, changeaccountinfopane;
	@FXML
	private ChoiceBox resolutionselc, filterstraccsearch;
	@FXML
	private CheckBox reqselctoshowpass, reqpasswordcheckbox, makeusernamechangeablecheckbox, makepasswordchangeablecheckbox, makeaccountdeletablecheckbox;
	@FXML
	protected CheckBox allowautodbbackup;
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
	private VBox uservbox, accountdisp;
	@FXML
	private ImageView settingsimagelocal, settingsimageadmin;
	private static FileReader f;
	
	FileWriter writer;
	boolean canthreadrunagain = true, setactivepassrequired, changecolorofselc, createorchange;
	private static boolean loadlocalaccounts = false, islocalacc = false, reqpass, isadmin = false;
	Font orgfont;
	public static String currentusername;
	public static int currentheldID, currentpreviousmonth, i = 2, straccprimarktochange;
	private static String servicestr = "", namestr = "", passwordstr = "", usersettings, useroptions, temp, temp2;
	private static List<Text> txtlist = new ArrayList<Text>();
	private static List<String> passwordlist = new ArrayList<String>();
	protected static Connection connectionatt;
	protected static Statement connectionstmt;//DriverManager.getConnection("jdbc:mysql://localhost/accdb?", "acc", "2994mYsQl#");
	
	ArrayList<FadeTransition> ftlistENTER = new ArrayList<FadeTransition>();
	ArrayList<TranslateTransition> ttlistENTER = new ArrayList<TranslateTransition>();
	ArrayList<FadeTransition> ftlistEXIT = new ArrayList<FadeTransition>();
	ArrayList<TranslateTransition> ttlistEXIT = new ArrayList<TranslateTransition>();

	static ArrayList<Pane> allstraccpanes = new ArrayList<Pane>();
	static ArrayList<Text> usernametexts = new ArrayList<Text>();
	static ArrayList<Node> allnodesofvbox = new ArrayList<Node>();
	static ArrayList<Pane> tempsavedaccpanes = new ArrayList<Pane>();
	
	//ALL THAT WHICH IS FOR LOGGING
	protected static boolean advancedlogging = true;
	protected static final Logger logger = LogManager.getLogger();
	
	// Log in/Create Housaini account
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
									try{root = FXMLLoader.load(getClass().getResource(File.separatorChar + "PasswordManagerMain.fxml"));} catch (IOException e) {e.printStackTrace();}
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
	
	//Invoked on shutdown, backs up database
	public static void shutdown() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, SQLException {
		
		if(connectionatt != null) {
		ResultSet mainrs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1;");
		String automaticDBbackuplocation = null;
		
		if(mainrs.getBoolean("allowautodbbackup")) {
			automaticDBbackuplocation = mainrs.getString("databasebackupfilelocation");
			DatabaseFileHandler.backupDatabasestatic("accdb", automaticDBbackuplocation);
		}
		
		connectionatt.close();
		connectionstmt.close();
		}
	}
	
	//Housaini account: Changes button action from Logging in to creating account
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
						
							//THIS REPLACE IT WITH A FADETRANSITION
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
							
							AnimationHandler.animateboop(accerrorlabel);
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
	
	//Local account: allows searching through the local accounts on the login screen
	public void accsearchonsearch() {
		if(!accsearch.getText().isEmpty()) {
			ArrayList<Pane> searchedaccpanes = new ArrayList<Pane>();
			for(Pane localaccountpane : tempsavedaccpanes) { //tempsavedaccpanes is renewed every time loadaccounts is called
				if(((Text)localaccountpane.getChildren().get(2)).getText().contains(accsearch.getText())) //extracts the text object from each pane and compares its text with what the user has searched for
					searchedaccpanes.add(localaccountpane);
			}
			uservbox.getChildren().clear();
			for(Node child : searchedaccpanes) uservbox.getChildren().add(child);
		} else loadaccounts();
	}

	//Invoked upon initalization of any scene
	public void initialize() {
		
		try {
			if(islocalacc) {
				connectionatt.close();
				connectionatt = DriverManager.getConnection("jdbc:sqlite:accdb");
				connectionstmt = connectionatt.createStatement();
				
				ResultSet localaccountrs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1;");
				if(isadmin) allowautodbbackup.setSelected(localaccountrs.getBoolean("allowautodbbackup"));
				localaccountrs.close();
				logger.info("Successfully connected to local database");
			}
			
			settingsimagelocal.setImage(new Image(File.separatorChar + "settingsicon.png"));
			settingsimageadmin.setImage(new Image(File.separatorChar + "settingsicon.png"));
			
			if(!isadmin) {
				maintabpane.getTabs().remove(5);
				maintabpane.getTabs().remove(3);
			} else {
				maintabpane.getTabs().remove(4);
				maintabpane.getTabs().remove(2);
				maintabpane.getTabs().remove(1);
				maintabpane.getTabs().remove(0);
			}
			
		} catch(SQLException se) {
			SQLExceptionHandlerLogger(se, null);
		} catch(NullPointerException ne) {
			logger.error("Could not find settingsicon.png");
		}
		
		if(loadlocalaccounts) {
			loadaccounts();
		}
	}
	
	static ArrayList<String> tempusernames = new ArrayList<String>();
	static ArrayList<String> tempusercolors = new ArrayList<String>();
	static ArrayList<String> temppasswords = new ArrayList<String>();
	
	//Local account: uses the above arraylists to store information of local accounts from the ResultSet, then displays them
	public void loadaccounts() {
		
	if(loadlocalaccounts) {
		
		uservbox.getChildren().clear();
		displaycreateaccanddbadminacc();
		ArrayList<Pane> accpanes = new ArrayList<Pane>();
							
		//--------------------------------
							
		new Thread() {
		public void run() {
		try {
			ResultSet mainrs = connectionstmt.executeQuery("SELECT * FROM accinfolocal;");
			while(mainrs.next()) {
				tempusernames.add(mainrs.getString("username"));
				tempusercolors.add(mainrs.getString("usercolor"));
				temppasswords.add(mainrs.getString("password"));
			}
			
			boolean gray = false;
											
			for(int i = 0; i < tempusernames.size(); i++) {
				String usercolor = tempusercolors.get(i);
											
				accpanes.add(new Pane());
				Circle circle = new Circle();
				Stop[] stops = new Stop[] {new Stop(0, Color.web(usercolor).deriveColor(1,1,1.5,1)), new Stop(1, Color.web(usercolor).deriveColor(1, 1, 0.5, 1))};
				LinearGradient lg1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
				Text logintext = new Text("Log in");
				Text usernametext = new Text(tempusernames.get(i));
				
				if(temppasswords.get(i).equals(EncryptionObj.EncryptFuncHOUSAINIPASS(tempusernames.get(i) + "38FfiGKBdlAO56yh%3400bkg223KgoADAdAZ*(h8", EncryptionObj.hashKey(tempusernames.get(i) + "49GKALLVB(12$$%%#glfoaS:DPFOrllgSDADW"))))
					logintext.setText("No Password");
				
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
				
				usernametext.setFont(Font.font("Ubuntu", FontWeight.BOLD, 17));
				usernametext.setTranslateX(55);
				usernametext.setTranslateY(26);
				
				accpanes.get(i).getChildren().add(logintext);
				accpanes.get(i).getChildren().add(circle);
				accpanes.get(i).getChildren().add(usernametext);
				accpanes.get(i).setPrefWidth(224);
				accpanes.get(i).setPrefHeight(52);
				accpanes.get(i).setBackground(Background.fill(gray ? Color.LIGHTGRAY : Color.WHITE));
				accpanes.get(i).setVisible(true);
											
				gray = !gray;
											
				ftlistENTER.add(new FadeTransition());
				ftlistENTER.get(i).setNode(accpanes.get(i).getChildrenUnmodifiable().get(0));
				ftlistENTER.get(i).setToValue(gray? 0.5 : 0.4);
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
				
				accpanes.get(i).setOnMouseExited((event) -> {
					ftlistEXIT.get(t).play();
					ttlistEXIT.get(t).play();
				});
											
				accpanes.get(i).setOnMouseClicked((event) -> {
					housainipassword.clear();
					housainiusername.setText(usernametext.getText());
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
											
			}
			
			Platform.runLater(new Runnable() {
			public void run() {
				tempsavedaccpanes = accpanes;
				for(Pane addPane : accpanes) {
					uservbox.getChildren().add(addPane);
					if(uservbox.getPrefHeight() < 52 * uservbox.getChildrenUnmodifiable().size()) 
						uservbox.setPrefHeight(uservbox.getPrefHeight() + 52);
					}
				for(Node nodes : uservbox.getChildren()) allnodesofvbox.add(nodes);
				tempusernames.clear();
				tempusercolors.clear();
				temppasswords.clear();
			}});
				
		} catch(SQLException sqle) {
			SQLExceptionHandlerLogger(sqle, null);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			logger.error("Failed Encryption, ", e);
		}
		
		}
		}.start();
							
		}
	}
		
	//Local account: method for the login/create account button.
	public void createorlogintolocalAcc(ActionEvent e) {
			
			new Thread() {
				String input_errorable;
				public void run() {
					try {
					boolean allow = true;
					
					allow = ValidationHandler.ValidateInputofTextField(housainiusername, accerrorlabel, false, 30) && ValidationHandler.ValidateInputofTextField(housainipassword, accerrorlabel, false, 3000);
					
					if(allow) {
						
//----------------------------------------------------------------------------------------------------------------------
											//FIRST CASE: Creating an Account
						
						if(createpassbutton.getText().equals("Create Account")) {
							ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password, username FROM accinfolocal");
							while(mainrs.next()) {
								if((input_errorable = housainiusername.getText()).equals(mainrs.getString("username"))) {
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
									connectionstmt.execute("INSERT INTO accinfolocal(password, username, settings, usercolor, usersettings) VALUES('" + EncryptionObj.EncryptFuncHOUSAINIPASS(housainiusername.getText() + "38FfiGKBdlAO56yh%3400bkg223KgoADAdAZ*(h8", EncryptionObj.hashKey(housainiusername.getText() + "49GKALLVB(12$$%%#glfoaS:DPFOrllgSDADW")) + "','" + housainiusername.getText() + "','0000000000','" + toHexString(usercolorpicker.getValue()) + "','" + strb.toString() + "');");	
							
								accerrorlabel.setFill(Color.BLACK);
								accerrorlabel.setText("Created account: " + housainiusername.getText());
								logger.info("Local account was created: " + housainiusername.getText());
								AnimationHandler.animateboop(accerrorlabel);
							
								Platform.runLater(new Runnable() {
								public void run() {
									loadaccounts();
									reqpasswordcheckbox.setSelected(true);
									requirespassword(new ActionEvent());
								}});
									
								housainiusername.clear();
								housainipassword.clear();
								makepasswordchangeablecheckbox.setSelected(false); makeusernamechangeablecheckbox.setSelected(false); makeaccountdeletablecheckbox.setSelected(false);
							} else {
								accerrorlabel.setFill(Color.RED);
								accerrorlabel.setText("Another account exists with that name");
								AnimationHandler.animateboop(accerrorlabel);
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
									AnimationHandler.animateboop(accerrorlabel);
									sleep(5000);
									accerrorlabel.setText("");
									
								//2. ADMIN LOGIN SECOND CASE: Admin Login Credentials are Correct
								
								} else if(rs.getString("adminpass").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText())))) {
									
									isadmin = true;
									
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											double[] exponents = {0.167,0.25,-0.5};
											LoadScene(File.separatorChar + "PasswordManagerMain.fxml", uservbox, exponents);
										}
									});
									
								//3. ADMIN LOGIN THIRD CASE: Admin Login Credentials are Incorrect
									
								} else if(!rs.getString("adminpass").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText())))) {
									accerrorlabel.setFill(Color.RED);
									accerrorlabel.setText("Incorrect: Wait some time and try again");
									AnimationHandler.animateboop(accerrorlabel);
									createpassbutton.setDisable(true);
									sleep(5000);
									createpassbutton.setDisable(false);
									accerrorlabel.setFill(Color.BLACK);
									accerrorlabel.setText("");
								}
							
							}
						
//----------------------------------------------------------------------------------------------------------------------
										//THIRD CASE: Logging into an Account
						
						} else {
							allow = false;
							ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password, username, settings, usersettings FROM accinfolocal");
							boolean r = false;
							while(mainrs.next()) {
								if(mainrs.getString("username").equals(housainiusername.getText()) && (mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText(), EncryptionObj.hashKey(housainipassword.getText()))) || (r = mainrs.getString("password").equals(EncryptionObj.EncryptFuncHOUSAINIPASS(housainiusername.getText() + "38FfiGKBdlAO56yh%3400bkg223KgoADAdAZ*(h8", EncryptionObj.hashKey(housainiusername.getText() + "49GKALLVB(12$$%%#glfoaS:DPFOrllgSDADW")))))) {
									allow = true; 
									currentheldID = mainrs.getInt("id");
									usersettings = mainrs.getString("settings");
									currentusername = housainiusername.getText();
									reqpass = !r;
									islocalacc = true;
									currentpreviousmonth = 15;
									if(!reqpass) useroptions = mainrs.getString("usersettings");
									if(!r)
										temp = EncryptionObj.EncryptFuncHOUSAINIPASS(housainipassword.getText() + "E4Af3Sd@", EncryptionObj.hashKey(housainipassword.getText() + "38FfiGKBdl94994FOKSALL560870-GL12#$@(*(fkgkall34ltg)(r%**goADAdAZ*(h8)"));
									else
										temp = EncryptionObj.EncryptFuncHOUSAINIPASS(Integer.toString(mainrs.getInt("id")) + "%8ggdF3r", EncryptionObj.hashKey("id") + "38FfiGKBdl94994FOKSALL560870-GL12#$@(*(fkgkall34ltg)(r%**goADAdAZ*(h8");
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
										LoadScene(File.separatorChar + "PasswordManagerMain.fxml", uservbox, exponents);
									}
								});
							} else {
								accerrorlabel.setOpacity(1);
								accerrorlabel.setFill(Color.RED);
								accerrorlabel.setText("Wrong account information");
							}
							
						}
					}
					
					} catch (SQLException sqle) {
						SQLExceptionHandlerLogger(sqle, input_errorable);
					} catch(NoSuchAlgorithmException | InvalidKeySpecException | InterruptedException nsa_iks_ie) {
						logger.error("An error occured due to Encryption issues or an Interruption in the thread: ", nsa_iks_ie);
					}
				}
			}.start();
		}
	
	//Local account: if password is not required, show this window
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
	
	//DATABASE: allow automatically backing up db every time application closes
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
	
	//DATABASE: Refers to the other Method in the DatabaseFileHandler class
	public void localdbbackupController(ActionEvent e) {
		DatabaseFileHandler.localdbbackup(e);
	}
		
	//DATABASE: Refers to the other Method in the DatabaseFileHandler class
	public void automaticdbbackupController(ActionEvent e) {
		DatabaseFileHandler.automaticdbbackup(e);
	}
		
	//DATABASE: Refers to the other Method in the DatabaseFileHandler class
	public void dbrestoreController(ActionEvent e) {
		DatabaseFileHandler.dbrestore(e);
	}
	
	//log out of current account and return back to the login screen
	public void logout(ActionEvent e) throws SQLException {
		islocalacc = false;
		isadmin = false;
		connectionstmt.close();
		connectionatt.close();
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				double[] exponents = {0.167,0.25,-0.5};
				LoadScene(File.separatorChar + "loginscreen.fxml", mainanchorpane, exponents);
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
			if(servicenamefield.getText() == null || servicenamefield.getText().isEmpty()) AnimationHandler.animateshake(servicenamefield);
			else servicenamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			if(usernamefield.getText() == null || usernamefield.getText().isEmpty()) AnimationHandler.animateshake(usernamefield);
			else usernamefield.setStyle("-fx-text-box-border: #33c6ff; -fx-focus-color: #33c6ff;");
			if(passwordfield.getText() == null || passwordfield.getText().isEmpty()) AnimationHandler.animateshake(passwordfield);
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
	
	//displays accounts, invoked on opening the "stored accounts" tab
	public void showaccounts() throws IOException, NoSuchAlgorithmException {
			    
		passwordlist.clear();
		txtlist.clear();
		accountdisp.getChildren().clear();
		accountdisp.setStyle("-fx-background-color: #ffffff");
		BorderStroke buttonborder = new BorderStroke(Color.rgb(0, 0, 0, 0.8), BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2));
		AddAccbutton1.setBorder(new Border(buttonborder));
		AddAccbutton1.setBackground(Background.fill(Color.rgb(150,150,150,0.5)));
		AddAccbutton1.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
		AddAccbutton1.setTextFill(Color.BLACK);
		AddAccbutton1.setOnMouseEntered(event -> {
			AddAccbutton1.setBackground(Background.fill(Color.rgb(210, 210, 210, 0.6)));
		}); 
		AddAccbutton1.setOnMouseExited(event -> {
			AddAccbutton1.setBackground(Background.fill(Color.rgb(150, 150, 150, 0.6)));
		});
		filterstraccsearch.getItems().add("Filter by:");
		filterstraccsearch.setValue(filterstraccsearch.getItems().get(0));
		filterstraccsearch.getItems().add("Service name");
		filterstraccsearch.getItems().add("Username");
		filterstraccsearch.getItems().add("Password");
		
		new Thread() {
			
			int i = 0;
			
			public void run() {
				
			try {
				String tablename = "accstorage";
				if(islocalacc) tablename = "accstoragelocal";
				ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM "+tablename+" WHERE id=" + currentheldID);
				ArrayList<Pane> panearr = new ArrayList<Pane>();
				ArrayList<TextFlow> tfarr = new ArrayList<TextFlow>();
				ArrayList<String> srv = new ArrayList<String>();
				ArrayList<String> usr = new ArrayList<String>();
				ArrayList<String> pwd = new ArrayList<String>();
				
				while(mainrs.next()) {
				
					srv.add(EncryptionObj.DecryptFunc(mainrs.getString("servicename"), temp));
					usr.add(namestr = EncryptionObj.DecryptFunc(mainrs.getString("username"), temp));
					pwd.add(passwordstr = EncryptionObj.DecryptFunc(mainrs.getString("password"), temp));
			
					panearr.add(new Pane());
					panearr.get(i).setBackground(Background.fill(Color.WHITE));
					tfarr.add(new TextFlow());
					
					i++;
				}
				
				Platform.runLater(() -> {
						System.out.println(panearr.size());
						scrollpanewithtextf.setHbarPolicy(ScrollBarPolicy.NEVER);
						boolean showpass = usersettings.charAt(0) == '1' ? false : true;
						
						for(int i = 0; i < panearr.size(); i++) {
							
							final int f = i;
							
							panearr.get(i).getChildren().add(tfarr.get(i));
							tfarr.get(i).getChildren().add(new Text(srv.get(i) + '\n'));
							((Text)tfarr.get(i).getChildren().get(0)).setFont(Font.font("Ubuntu", FontWeight.BOLD, 30));
							((Text)tfarr.get(i).getChildren().get(0)).setUnderline(true);
							tfarr.get(i).getChildren().add(new Text(usr.get(i) + '\n'));
							((Text)tfarr.get(i).getChildren().get(1)).setFont(Font.font("Ubuntu", 24));
							tfarr.get(i).getChildren().add(new Text(pwd.get(i)));
							((Text)tfarr.get(i).getChildren().get(2)).setFont(Font.font("Ubuntu", 24));
							if(!showpass) ((Text)tfarr.get(i).getChildren().get(2)).setVisible(false);
							
							//creating the HBox that will hold the three icons for: editing, deleting, and showing/hiding passwords
							HBox temphb = new HBox(); temphb.setSpacing(36); temphb.setOpacity(60); temphb.setPrefWidth(100);
							
							//creating the VBox that will hold the previous HBox and a Text explaining what each icon does
							VBox layered = new VBox(); layered.getChildren().add(temphb); layered.getChildren().add(new TextFlow());
							
							//creating the text that will explain the icons
							((TextFlow)layered.getChildren().get(1)).getChildren().add(new Text());
							Text iconinfo = (Text)((TextFlow)layered.getChildren().get(1)).getChildren().get(0);
							iconinfo.setFont(Font.font("Ubuntu", FontWeight.BOLD, 14));
							iconinfo.setFill(new Color(0.1399999976158142, 0.3538853287696838, 0.5, 1.0));
							Glow b = new Glow();
							b.setLevel(0.5);
							iconinfo.setEffect(b);
							((TextFlow)layered.getChildren().get(1)).setTextAlignment(TextAlignment.CENTER);
							((TextFlow)layered.getChildren().get(1)).setPrefWidth(150);
							
					        Distant light1 = new Distant();light1.setAzimuth(116.16);light1.setElevation(124.53);light1.setColor(new Color(0.4399999976158142, 0.7538853287696838, 1.0, 1.0));
					        
					        Lighting lighting1 = new Lighting();lighting1.setDiffuseConstant(2.0);lighting1.setSpecularConstant(0.6);lighting1.setSpecularExponent(11.16);lighting1.setSurfaceScale(2.2);lighting1.setLight(light1);lighting1.setBumpInput(new Shadow());
					        
					        Distant light2 = new Distant();light2.setAzimuth(116.16);light2.setElevation(124.53);light2.setColor(new Color(0.4399999976158142, 0.7538853287696838, 1.0, 1.0));
					        
					        Lighting lighting2 = new Lighting();lighting2.setDiffuseConstant(2.0);lighting2.setSpecularConstant(0.6);lighting2.setSpecularExponent(11.16);lighting2.setSurfaceScale(2.2);lighting2.setLight(light2);lighting2.setBumpInput(new Shadow());
					        
					        Distant light3 = new Distant();light3.setAzimuth(116.16);light3.setElevation(124.53);light3.setColor(new Color(0.4399999976158142, 0.7538853287696838, 1.0, 1.0));
					        
					        Lighting lighting3 = new Lighting();lighting3.setDiffuseConstant(2.0);lighting3.setSpecularConstant(0.6);lighting3.setSpecularExponent(11.16);lighting3.setSurfaceScale(2.2);lighting3.setLight(light3);lighting3.setBumpInput(new Shadow());
							
					        //Creating the first icon (editing)
					        
					        //FIRST ICON --------------------------------------------------
					        temphb.getChildren().add(new ImageView()); 
					        ImageView tempB1 = new ImageView();
					        Rectangle overlay1 = new Rectangle(25, 25);
					        overlay1.setFill(Color.TRANSPARENT);
					        tempB1.setImage(new Image("edit.png"));tempB1.setPreserveRatio(true);tempB1.setFitWidth(25);tempB1.setFitHeight(25);tempB1.setEffect(lighting1);
					        
					        //Scaling up when mouse enters the imageview
					        overlay1.setOnMouseEntered(event -> {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB1);sc.setToX(1.3);sc.setToY(1.3);sc.setInterpolator(Interpolator.EASE_BOTH);sc.setDuration(Duration.millis(200));sc.play();
					            iconinfo.setText("Change info");
					        });
					        
					        //Scaling down...
					        overlay1.setOnMouseExited(event -> {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB1);sc.setToX(1);sc.setToY(1);sc.setInterpolator(Interpolator.EASE_BOTH);sc.setDuration(Duration.millis(200));sc.play();
					            iconinfo.setText("");
					        });
					        
					        overlay1.setOnMouseClicked(event -> {
					        	//if condition to deny opening the editing GUI more than once
					        	if(scrollpanewithtextf.getEffect() == null || (scrollpanewithtextf.getEffect() != null && ((GaussianBlur)scrollpanewithtextf.getEffect()).getRadius() == 0.0)) {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB1);sc.setToX(0.6);sc.setToY(0.6);sc.setInterpolator(Interpolator.EASE_OUT);sc.setDuration(Duration.millis(50));
					            sc.setOnFinished(eventOnAnimationFinished -> {ScaleTransition sc2 = new ScaleTransition();sc2.setNode(tempB1);sc2.setToX(1.3);sc2.setToY(1.3);sc2.setInterpolator(Interpolator.EASE_BOTH);sc2.setDuration(Duration.millis(50));sc2.play();});
					            sc.play();
					            
					            GaussianBlur blur = new GaussianBlur(0); scrollpanewithtextf.setEffect(blur);
					            Timeline startblur = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 0)), new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 10))); startblur.play();
					            
					            //changing the background to blue (editing specific)
					            changeaccountinfopane.setBackground(Background.fill(new Color(0.1399999976158142, 0.3538853287696838, 0.5, 0.3)));
					            TranslateTransition CAIPtt = new TranslateTransition(); CAIPtt.setNode(changeaccountinfopane); CAIPtt.setToX(0); CAIPtt.setInterpolator(Interpolator.EASE_IN); CAIPtt.setDuration(Duration.millis(200)); 
					            CAIPtt.setOnFinished(caipttonfinishedevent -> {
					            	scrollpanewithtextf.setOnMouseClicked(eventSPWTfevent -> {
					            		Timeline endblur = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 10)), new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 0))); endblur.play();
							            TranslateTransition CAIPtto = new TranslateTransition(); CAIPtto.setNode(changeaccountinfopane); CAIPtto.setToX(-300); CAIPtto.setInterpolator(Interpolator.EASE_IN); CAIPtto.setDuration(Duration.millis(200)); CAIPtto.play();
							            scrollpanewithtextf.setOnMouseClicked(null);
						            });
					            });
					            CAIPtt.play();
					            
					            //settings specific to each button
					            straccprimarktochange = f;
					            AddAccbutton1.setLayoutY(124);
					            AddAccbutton1.setOnAction(actionevent -> {
					            	try {ChangeAccountofStorage(actionevent);} catch (IOException | NoSuchAlgorithmException e) {e.printStackTrace();}
					            	Timeline endblur = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 10)), new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 0))); endblur.play();
							        TranslateTransition CAIPtto = new TranslateTransition(); CAIPtto.setNode(changeaccountinfopane); CAIPtto.setToX(-300); CAIPtto.setInterpolator(Interpolator.EASE_IN); CAIPtto.setDuration(Duration.millis(200)); CAIPtto.play();
							        scrollpanewithtextf.setOnMouseClicked(null);
					            });
					            changeinfoofacctext.setText("Change info of " + ((Text)tfarr.get(f).getChildren().get(1)).getText().replaceAll("\n", ""));
					            usernamefield1.setText(((Text)tfarr.get(f).getChildren().get(1)).getText().replaceAll("\n", ""));
				            	passwordfield1.setText(((Text)tfarr.get(f).getChildren().get(2)).getText().replaceAll("\n", ""));
				            	AddAccbutton1.setText("Update");
					            usernamefield1.setVisible(true); usernamefield1.setDisable(false);
					            passwordfield1.setVisible(true); passwordfield1.setDisable(false);
					        	}
					        });
					        
					        //overlaying a rect over the button with a transition to prevent weird behaviour when hovering
					        StackPane stackPane1 = new StackPane();stackPane1.getChildren().addAll(tempB1, overlay1);temphb.getChildren().set(0, stackPane1);

					        //SECOND ICON --------------------------------------------------
					        ImageView tempB2 = new ImageView();
					        Rectangle overlay2 = new Rectangle(25, 25);
					        overlay2.setFill(Color.TRANSPARENT);
					        tempB2.setImage(new Image("bin.png"));tempB2.setPreserveRatio(true);tempB2.setFitWidth(25);tempB2.setFitHeight(25);tempB2.setEffect(lighting2);

					        overlay2.setOnMouseEntered(event -> {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB2);sc.setToX(1.3);sc.setToY(1.3);sc.setInterpolator(Interpolator.EASE_BOTH);sc.setDuration(Duration.millis(200));sc.play();
					            iconinfo.setText("Delete account info");
					        });
					        
					        overlay2.setOnMouseClicked(event -> {
					        	//if condition to deny opening the editing GUI more than once
					        	if(scrollpanewithtextf.getEffect() == null || (scrollpanewithtextf.getEffect() != null && ((GaussianBlur)scrollpanewithtextf.getEffect()).getRadius() == 0.0)) {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB2);sc.setToX(0.6);sc.setToY(0.6);sc.setInterpolator(Interpolator.EASE_OUT);sc.setDuration(Duration.millis(50));
					            sc.setOnFinished(eventOnAnimationFinished -> {ScaleTransition sc2 = new ScaleTransition();sc2.setNode(tempB2);sc2.setToX(1.3);sc2.setToY(1.3);sc2.setInterpolator(Interpolator.EASE_BOTH);sc2.setDuration(Duration.millis(50));sc2.play();});
					            sc.play();
					            
					            GaussianBlur blur = new GaussianBlur(0); scrollpanewithtextf.setEffect(blur);
					            Timeline startblur = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 0)), new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 10))); startblur.play();
					            
					            //changing the background to blue (editing specific)
					            changeaccountinfopane.setBackground(Background.fill(new Color(0.5, 0.1399999976158142, 0.3538853287696838, 0.3)));
					            TranslateTransition CAIPtt = new TranslateTransition(); CAIPtt.setNode(changeaccountinfopane); CAIPtt.setToX(0); CAIPtt.setInterpolator(Interpolator.EASE_IN); CAIPtt.setDuration(Duration.millis(200)); 
					            CAIPtt.setOnFinished(caipttonfinishedevent -> {
					            		scrollpanewithtextf.setOnMouseClicked(eventSPWTfevent -> {
					            		Timeline endblur = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 10)), new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 0))); endblur.play();
							            TranslateTransition CAIPtto = new TranslateTransition(); CAIPtto.setNode(changeaccountinfopane); CAIPtto.setToX(-300); CAIPtto.setInterpolator(Interpolator.EASE_IN); CAIPtto.setDuration(Duration.millis(200)); CAIPtto.play();
							            scrollpanewithtextf.setOnMouseClicked(null);
						            });
					            });
					            CAIPtt.play();
					            
					            //settings specific to the button
					            straccprimarktochange = f;
					            AddAccbutton1.setLayoutY(38);
					            AddAccbutton1.setOnAction(actionevent -> {
					            	try {deleteaccountfromstorage(actionevent);} catch (IOException e) {e.printStackTrace();}
					            	Timeline endblur = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(blur.radiusProperty(), 10)), new KeyFrame(Duration.millis(200), new KeyValue(blur.radiusProperty(), 0))); endblur.play();
							        TranslateTransition CAIPtto = new TranslateTransition(); CAIPtto.setNode(changeaccountinfopane); CAIPtto.setToX(-300); CAIPtto.setInterpolator(Interpolator.EASE_IN); CAIPtto.setDuration(Duration.millis(200)); CAIPtto.play();
							        scrollpanewithtextf.setOnMouseClicked(null);
					            });
					            AddAccbutton1.setText("Confirm");
					            changeinfoofacctext.setText("Confirm Deletion: " + ((Text)tfarr.get(f).getChildren().get(1)).getText().replaceAll("\n", ""));
					            usernamefield1.setVisible(false); usernamefield1.setDisable(true);
					            passwordfield1.setVisible(false); passwordfield1.setDisable(true);
					            }
					        });

					        overlay2.setOnMouseExited(event -> {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB2);sc.setToX(1);sc.setToY(1);sc.setInterpolator(Interpolator.EASE_BOTH);sc.setDuration(Duration.millis(200));sc.play();
					            iconinfo.setText("");
					        });

					        StackPane stackPane2 = new StackPane();stackPane2.getChildren().addAll(tempB2, overlay2);temphb.getChildren().add(stackPane2);

					        //THIRD ICON ---------------------------------------------------------
					        ImageView tempB3 = new ImageView();
					        Rectangle overlay3 = new Rectangle(25, 25);
					        overlay3.setFill(Color.TRANSPARENT);
					        if(((Text)tfarr.get(i).getChildren().get(2)).isVisible()) tempB3.setImage(new Image("hide.png"));
					        else tempB3.setImage(new Image("show.png"));
					        tempB3.setPreserveRatio(true);tempB3.setFitWidth(25);tempB3.setFitHeight(25);tempB3.setEffect(lighting3);

					        overlay3.setOnMouseEntered(event -> {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB3);sc.setToX(1.3);sc.setToY(1.3);sc.setInterpolator(Interpolator.EASE_BOTH);sc.setDuration(Duration.millis(200));sc.play();
					            iconinfo.setText(((Text)tfarr.get(f).getChildren().get(2)).isVisible() ? "Hide Password" : "Show Password");
					        });
					        
					        overlay3.setOnMouseClicked(event -> {
					        	boolean passvisible = ((Text)tfarr.get(f).getChildren().get(2)).isVisible();
					        	ScaleTransition sc = new ScaleTransition();sc.setNode(tempB3);sc.setToX(0.6);sc.setToY(0.6);sc.setInterpolator(Interpolator.EASE_OUT);sc.setDuration(Duration.millis(50));
					            sc.setOnFinished(eventOnAnimationFinished -> {ScaleTransition sc2 = new ScaleTransition();sc2.setNode(tempB3);sc2.setToX(1.3);sc2.setToY(1.3);sc2.setInterpolator(Interpolator.EASE_BOTH);sc2.setDuration(Duration.millis(50));sc2.play();});
					            sc.play();
					            
					            TranslateTransition ttsendtohide = new TranslateTransition();
					            ttsendtohide.setNode(tfarr.get(f).getChildren().get(2));
					            ttsendtohide.setToX(-1 * ((Text)tfarr.get(f).getChildren().get(2)).getText().length() * 8);
					            ttsendtohide.setInterpolator(Interpolator.EASE_BOTH);
					            ttsendtohide.setDuration(Duration.millis(100));
					            ttsendtohide.setOnFinished(event2 -> {
					            	tfarr.get(f).getChildren().get(2).setVisible(false);
					            	iconinfo.setText("Show Password");
					            	tempB3.setImage(new Image("show.png"));
					            });
					            
					            TranslateTransition ttsendtoshow = new TranslateTransition();
					            ttsendtoshow.setNode(tfarr.get(f).getChildren().get(2));
					            ttsendtoshow.setFromX(-1 * ((Text)tfarr.get(f).getChildren().get(2)).getText().length() * 8);
					            ttsendtoshow.setToX(0);
					            ttsendtoshow.setInterpolator(Interpolator.EASE_BOTH);
					            ttsendtoshow.setDuration(Duration.millis(100));
					            ttsendtoshow.setOnFinished(event2 -> {
					            	tempB3.setImage(new Image("hide.png"));
					            	iconinfo.setText("Hide Password");
					            });
					            
					            if(passvisible) ttsendtohide.play();
					            else {
					            	tfarr.get(f).getChildren().get(2).setVisible(true);
					            	ttsendtoshow.play();
					            }
					            
					        });

					        overlay3.setOnMouseExited(event -> {
					            ScaleTransition sc = new ScaleTransition();sc.setNode(tempB3);sc.setToX(1);sc.setToY(1);sc.setInterpolator(Interpolator.EASE_BOTH);sc.setDuration(Duration.millis(200));sc.play();iconinfo.setText("");
					        });

					        StackPane stackPane3 = new StackPane();stackPane3.getChildren().addAll(tempB3, overlay3);temphb.getChildren().add(stackPane3);
							
					        //INDIVIDUAL PANE ADDITION AND FINALIZATION:
							panearr.get(i).getChildren().add(layered);
							
							panearr.get(i).setOnMouseMoved(event -> {
					            light1.setAzimuth(Math.toDegrees(calculateAngle(tempB1, event)) + 180);
					            light2.setAzimuth(Math.toDegrees(calculateAngle(tempB2, event)) + 180);
					            light3.setAzimuth(Math.toDegrees(calculateAngle(tempB3, event)) + 180);
							});
							
							layered.setTranslateX(600);
							
							panearr.get(i).setOnMouseEntered(event -> {
								ObjectProperty<Color> color = new SimpleObjectProperty<Color>((Color)panearr.get(f).getBackground().getFills().getFirst().getFill());
						        color.addListener((observable, oldValue, newValue) -> {
						            BackgroundFill backgroundFill = new BackgroundFill(newValue, CornerRadii.EMPTY, null);
						            panearr.get(f).setBackground(new Background(backgroundFill));
						        });
						        
								Timeline entertimeline = new Timeline(
							            new KeyFrame(Duration.ZERO, new KeyValue(color, (Color)panearr.get(f).getBackground().getFills().getFirst().getFill())),
							            new KeyFrame(Duration.millis(150), new KeyValue(color, Color.LIGHTBLUE))
							        );
								entertimeline.setCycleCount(1);
								entertimeline.play();
								
								TranslateTransition tt = new TranslateTransition();
								tt.setNode(layered);
								tt.setToX(450);
								tt.setDuration(Duration.millis(120));
								tt.play();
							});
							
							panearr.get(i).setOnMouseExited(event -> {
								
								ObjectProperty<Color> color = new SimpleObjectProperty<Color>((Color)panearr.get(f).getBackground().getFills().getFirst().getFill());
						        color.addListener((observable, oldValue, newValue) -> {
						            BackgroundFill backgroundFill = new BackgroundFill(newValue, CornerRadii.EMPTY, null);
						            panearr.get(f).setBackground(new Background(backgroundFill));
						        });
						        
								Timeline exittimeline = new Timeline(
							            new KeyFrame(Duration.ZERO, new KeyValue(color, (Color)panearr.get(f).getBackground().getFills().getFirst().getFill())),
							            new KeyFrame(Duration.millis(150), new KeyValue(color, Color.WHITE))
							        );
								exittimeline.setCycleCount(1);
								exittimeline.play();
								
								TranslateTransition to = new TranslateTransition();
								to.setNode(layered);
								to.setToX(600);
								to.setDuration(Duration.millis(120));
								to.play();
							});
							
							if(i % 2 == 0) panearr.get(i).setBackground(Background.fill(Color.WHITE));
							else panearr.get(i).setBackground(Background.fill(Color.LIGHTGRAY));
							accountdisp.getChildren().add(panearr.get(i));
							Line seperatorline = new Line();
							seperatorline.setStartX(-670);
							accountdisp.getChildren().add(seperatorline);
							if(i > 2) {
								accountdisp.setPrefHeight(i*114);
							}
							scrollpanewithtextf.setVbarPolicy(ScrollBarPolicy.NEVER);
							storedaccountsanchorpane.setOnKeyPressed(event -> {
								handleSmoothScrollKeyPressed(event, scrollpanewithtextf);
							});
						}
						
						for(Node n : accountdisp.getChildren()) 
							if(n instanceof Pane)
							allstraccpanes.add((Pane)n);
				});
				
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
		new Thread() {
			public void run() {
			try {
				int primarykeyfordeletingline = 0;
				String tablename = "accstorage";
				if(islocalacc) tablename = "accstoragelocal";
				ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM " + tablename + " WHERE id=" + Integer.toString(currentheldID));
				int f = 0;
				while(mainrs.next()) {
					if(f==straccprimarktochange) {
						System.out.println((primarykeyfordeletingline = mainrs.getInt("primark")) + " THIS WILL BE CHANGED");
						break;
					}
					else f++;
				}
				mainrs.close();
				connectionstmt.executeUpdate("DELETE FROM " + tablename + " WHERE primark=" + primarykeyfordeletingline);
				
				Platform.runLater(() -> {
					deletetextfromdisp();
					try {showaccounts();} catch (NoSuchAlgorithmException | IOException e) {e.printStackTrace();}
				});
			
			} catch(SQLException sqlE) {sqlE.printStackTrace();}
		}}.start();
	}

	//changes the details of the stracc
	public void ChangeAccountofStorage(ActionEvent e) throws IOException, NoSuchAlgorithmException {
		
		new Thread() {
			public void run() {
				try {
					//connectionstmt.executeUpdate("UPDATE accstorage(username, password) VALUES('" + EncryptionObj.EncryptFunc(usernamefield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231") + "','" + EncryptionObj.EncryptFunc(passwordfield1.getText(), "-+-قثق[{}]dSec--343GJRIIDL__PP#$FAWEAL$1231344556324231") + "') WHERE id = " + currentheldID);
					String tablename = "accstorage", tempprimark = "";
					if(islocalacc) tablename = "accstoragelocal";
					ResultSet mainrs = connectionstmt.executeQuery("SELECT primark, id, servicename, username, password FROM "+tablename+" WHERE id='" + Integer.toString(currentheldID) + "';");
					int f = 0;
					while(mainrs.next()) {
						if(f == straccprimarktochange) {
						tempprimark = mainrs.getString("primark"); break;
						} else f++;
						}
						PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE " + tablename + " SET username = ?, password = ? WHERE primark = ?");
					    pstmt.setString(1, EncryptionObj.EncryptFunc(usernamefield1.getText(), temp));
					    pstmt.setString(2, EncryptionObj.EncryptFunc(passwordfield1.getText(), temp));
					    pstmt.setInt(3, Integer.parseInt(tempprimark));
					    pstmt.executeUpdate();
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
						AnimationHandler.animateboop(accwarning);
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
	
	//every key typed will invoke this method, it will take all the vbox contents and filter through them.
	public void searchstraccs() {
		if(!straccsearch.getText().isEmpty()) {
			//filterstraccsearch.getItems().add("Service name");
			//filterstraccsearch.getItems().add("username");
			//filterstraccsearch.getItems().add("password");
		ArrayList<Pane> filtered = new ArrayList<Pane>();
		ArrayList<Text> filteredtexts = new ArrayList<Text>();
		Text texttemp; int whichtexttoapplyfilter;
		switch(((String)filterstraccsearch.getValue())) {
			case "Service name": whichtexttoapplyfilter = 0; break;
			case "Username": whichtexttoapplyfilter = 1; break;
			case "Pasword": whichtexttoapplyfilter = 2; break;
			default: whichtexttoapplyfilter = 1; break;
		}
		for(Pane p : allstraccpanes) {
			if((texttemp = ((Text)((TextFlow)p.getChildren().get(0)).getChildren().get(whichtexttoapplyfilter))).getText().contains(straccsearch.getText())) {
				filtered.add(p);
				filteredtexts.add(texttemp);
			}
		}
		
		deletetextfromdisp();
		
		for(Pane p : filtered) {
			accountdisp.getChildren().add(p);
			Line seperatorline = new Line();
			seperatorline.setStartX(-670);
			accountdisp.getChildren().add(seperatorline);
		}
		
		} else {
			deletetextfromdisp();
			try {showaccounts();} catch (NoSuchAlgorithmException | IOException e) {e.printStackTrace();}
		}
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
						temp2 = EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText() + "E4Af3Sd@", EncryptionObj.hashKey(housaininewpassword.getText() + "38FfiGKBdl94994FOKSALL560870-GL12#$@(*(fkgkall34ltg)(r%**goADAdAZ*(h8)"));
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
			AnimationHandler.animateboop(statustext);
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
			AnimationHandler.animateshake(housaininewpassword);
		else
			AnimationHandler.animateshake(housainioldpassword);
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
							if(reqpass) {
								PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE accinfolocal SET username = ? WHERE id = ?");
								pstmt.setString(1, housaininewpassword.getText());
								pstmt.setInt(2, currentheldID);
								pstmt.execute();
							} else {
								PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE accinfolocal SET username = ?, password = ? WHERE id = ?");
								pstmt.setString(1, housaininewpassword.getText());
								pstmt.setString(2, EncryptionObj.EncryptFuncHOUSAINIPASS(housaininewpassword.getText() + "38FfiGKBdlAO56yh%3400bkg223KgoADAdAZ*(h8", EncryptionObj.hashKey(housaininewpassword.getText() + "49GKALLVB(12$$%%#glfoaS:DPFOrllgSDADW")));
								pstmt.setInt(3, currentheldID);
								pstmt.execute();
							}
						}
						
						statustext.setText("Username has been Updated!");
						AnimationHandler.animateboop(statustext);
						statustext.setVisible(true);
						currentusername = housaininewpassword.getText();
						housaininewpassword.setText("");
						housaininewpassword.setDisable(true);
						createpassbutton.setDisable(true);
						sleep(3000);
						statustext.setText("Current Username: " + currentusername);
					} catch (SQLException | InterruptedException | NoSuchAlgorithmException | InvalidKeySpecException IE_sqlE) {
						IE_sqlE.printStackTrace();
					}
			
			}
		}.start();
		} else {
				AnimationHandler.animateshake(housaininewpassword);
		}
	}
	
	//this method is automatically executed once the settings tab is opened, for configuration
	public void opensettings() throws IOException {
		if(usersettings == null) usersettings = "0000000000";
		statustext.setVisible(false);
		reqselctoshowpass.setSelected(usersettings.charAt(0) == '1');
		if(!reqpass) {
			housaininewpassword.setDisable(true);
			housainioldpassword.setDisable(true);
			createpassbutton.setDisable(true);
			statustext.setText("Password unchangeable");
		}
		resolutionselc.getItems().clear();
		resolutionselc.getItems().add("0.5x (200x300)");
		resolutionselc.getItems().add("1x (400x600)");
		resolutionselc.getItems().add("1.5x (600x900)");
		resolutionselc.getItems().add("2x (800x1200)");
		if(!reqpass && useroptions.charAt(2) == '0') delaccbutton.setDisable(true);
		
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
						String tablename = "accinfolocal";
						if(!islocalacc) tablename = "accinfo";
							
							PreparedStatement pstmt = connectionatt.prepareStatement("UPDATE "+tablename+" SET settings = ? WHERE id = ?");
							pstmt.setString(1, settingscode.toString());
							pstmt.setInt(2, currentheldID);
							pstmt.execute();
							
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
			AnchorPane root = LoadScene(File.separatorChar + "PasswordManagerMain.fxml", applysettingsbutton, exponents);
			((TabPane)((AnchorPane)(root.getChildren().getFirst())).getChildren().getFirst()).getSelectionModel().select(3);
			
		}
			
	//deletes the account from accinfo/accinfolocal and all of its info in the accstorage
	public void deleteAccount(ActionEvent e) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		if(!islocalacc || reqpass ||islocalacc && useroptions.charAt(2) == '1') {
		String tablename = islocalacc ? "accinfolocal" : "accinfo";
		ResultSet mainrs = connectionstmt.executeQuery("SELECT id, password FROM "+tablename+" WHERE id = " + currentheldID);
		mainrs.next();
		TextInputDialog td = new TextInputDialog("Password");
		
			if(!islocalacc || reqpass) {
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
									connectionstmt.executeUpdate("DELETE FROM accstorage WHERE id = " + currentheldID);
									connectionstmt.executeUpdate("DELETE FROM accinfo WHERE id = " + currentheldID);
								} else {
									PreparedStatement pstmt1 = connectionatt.prepareStatement("DELETE FROM accstoragelocal WHERE id = " + currentheldID);
									PreparedStatement pstmt2 = connectionatt.prepareStatement("DELETE FROM accinfolocal WHERE id = " + currentheldID);
									pstmt1.execute();
									pstmt2.execute();
								}
								usersettings = null;
								currentusername = null;
							} catch (SQLException e) {e.printStackTrace();}
						}
					}.start();
				} else {
					Alert wrongpassword = new Alert(AlertType.ERROR);
					wrongpassword.setHeaderText("Wrong Password" + '\n' + "Account not deleted");
					wrongpassword.showAndWait();
				}
			} else {
				td.setHeaderText("Deleting the Account will delete all the saved data of the stored accounts, and will be unrecoverable." + '\n' + "To confirm deletion, write 'confirm'");
				Optional<String> res = td.showAndWait();
				if(res.isPresent() && td.getResult().equals("confirm")) {
				Parent root = null;
				try{root = FXMLLoader.load(getClass().getResource("/loginscreen.fxml"));} catch (IOException ioe) {ioe.printStackTrace();}
				stage = (Stage)((Node)e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene); stage.show();
				new Thread() {
					public void run() {
						try {
								connectionstmt.executeUpdate("DELETE FROM accstoragelocal WHERE id = " + currentheldID);
								connectionstmt.executeUpdate("DELETE FROM accinfolocal WHERE id = " + currentheldID);
								usersettings = null;
								currentusername = null;
							} catch (SQLException e) {e.printStackTrace();}
						}
					}.start();
				}
			}
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
					if(!reqpass && useroptions.charAt(1) == '0') {
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
					if(!reqpass && useroptions.charAt(0) == '0') {
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
	
	//-------------------------------------- ANIMATIONS & DESIGN ----------------------------------------------------
	// ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	//-------------------------------------- ANIMATIONS & DESIGN ----------------------------------------------------

	public void SERVICENAMEFIELD_onhoveranimateController() {
		AnimationHandler.onhoveranimate(servicenamefield, servicenametext);
	}
	
	public void SERVICENAMEFIELD_onhoverexitanimateController() {
		AnimationHandler.onhoverexitanimate(servicenamefield, servicenametext);
	}
	
	public void USERNAMEFIELD_onhoveranimateController() {
		AnimationHandler.onhoveranimate(usernamefield, usernametext);
	}
	
	public void USERNAMEFIELD_onhoverexitanimateController() {
		AnimationHandler.onhoverexitanimate(usernamefield, usernametext);
	}
	
	public void PASSWORDFIELD_onhoveranimateController() {
		AnimationHandler.onhoveranimate(passwordfield, passwordtext);
	}
	
	public void PASSWORDFIELD_onhoverexitanimateController() {
		AnimationHandler.onhoverexitanimate(passwordfield, passwordtext);
	}

	//---------------------------------------------- MISCELLANEOUS -------------------------------------------------------
	// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	//---------------------------------------------- MISCELLANEOUS -------------------------------------------------------

	public void signuptolocalaccTEXT() throws IOException, InterruptedException {
		
		try {
			
			connectionatt = DriverManager.getConnection("jdbc:sqlite:accdb");
			connectionstmt = connectionatt.createStatement();
			File f = new File(DatabaseFileHandler.getDatabasePath(connectionatt));
			
			//ProcessBuilder processBuilder = new ProcessBuilder("chown", "root:root", f.toPath().toString());
            //processBuilder.inheritIO(); // To see any output/errors from the command
            //Process process = processBuilder.start();
            //process.waitFor();
            //if (process.exitValue() == 0) {
            //    System.out.println("File ownership changed to root successfully.");
            //} else {
            //    System.out.println("Failed to change file ownership. Exit code: " + process.exitValue());
            //}
            
			connectionstmt.execute("CREATE TABLE IF NOT EXISTS accinfolocal(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, password VARCHAR(3000), username VARCHAR(30), settings VARCHAR(10), usersettings VARCHAR(10), usercolor VARCHAR(7));");
			connectionstmt.execute("CREATE TABLE IF NOT EXISTS accstoragelocal(primark INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id INTEGER NOT NULL, servicename VARCHAR(5000), username VARCHAR(5000), password VARCHAR(5000));");
			connectionstmt.execute("CREATE TABLE IF NOT EXISTS hpmgeneralsettings(primark INTEGER NOT NULL PRIMARY KEY, databasebackupfilelocation VARCHAR(5000), allowautodbbackup BOOLEAN, adminpass VARCHAR(5000));");
			ResultSet rs = connectionstmt.executeQuery("SELECT * FROM hpmgeneralsettings WHERE primark = 1;");
			if(rs.getInt("primark") != 1) {
			connectionstmt.executeUpdate("INSERT INTO hpmgeneralsettings(primark) VALUES(1);");
			}
			loadlocalaccounts = true;
			
			double[] exponents = {0.25,0.5,-0.25};
			LoadScene(File.separatorChar + "loginscreenLocal.fxml", loginwithlocalacctext, exponents);
		
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			accerrorlabel.setText("Failed to connect to local Database");
		}
	}

	public AnchorPane LoadScene(String scenename, Node nodetouse, double[] sizeexponents) {
		/*Platform.runLater(new Runnable() {
		    @Override
		    public void run() {*/
		if(scenename.equals(File.separatorChar + "PasswordManagerMain.fxml")) loadlocalaccounts = false;
		    	try {
		    	AnchorPane root = null;
		    	root = (AnchorPane)FXMLLoader.load(getClass().getResource(scenename));
				
				
				
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
				//scene.getStylesheets().add(getClass().getResource(File.separatorChar + "application.css").toExternalForm());
				stage.setScene(scene); stage.show(); //maintabpane.setOpacity(0);
				return root;
		    	} catch (Exception e) {e.printStackTrace();}
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
	
	private void displaycreateaccanddbadminacc() {
		ArrayList<Pane> accpanes = new ArrayList<Pane>();
		uservbox.getChildren().clear();
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
		ImageView adminicon = new ImageView(File.separatorChar + "setting.png");
		adminicon.setFitWidth(47);
		adminicon.setFitHeight(51);
		adminicon.setLayoutX(4);
		adminicon.setLayoutY(2);
		adminaccpane.getChildren().add(adminicon);
		adminaccpane.setVisible(true);
		accpanes.add(adminaccpane);
		
		for(Pane addPane : accpanes)
			uservbox.getChildren().add(addPane);
	}
	
	private double calculateAngle(Node node, MouseEvent event) {
	    // Get the center coordinates of the Node
	    Bounds bounds = node.localToScene(node.getBoundsInLocal());
	    double centerX = bounds.getMinX() + bounds.getWidth() / 2.0;
	    double centerY = bounds.getMinY() + bounds.getHeight() / 2.0;

	    // Get the mouse coordinates relative to the scene
	    double mouseX = event.getSceneX();
	    double mouseY = event.getSceneY();

	    // Calculate the difference in coordinates
	    double deltaX = mouseX - centerX;
	    double deltaY = mouseY - centerY;

	    // Calculate the angle using Math.atan2
	    return Math.atan2(deltaY, deltaX);
	}
	
	private void smoothScroll(ScrollPane scrollPane, double vValue) {
        Timeline timeline = new Timeline();
        KeyValue kvV0 = new KeyValue(scrollPane.vvalueProperty(), 0);
        KeyValue kvV1 = new KeyValue(scrollPane.vvalueProperty(), vValue);
        KeyFrame kf0 = new KeyFrame(Duration.millis(100), kvV0);
        KeyFrame kf1 = new KeyFrame(Duration.millis(100), kvV1);
        timeline.getKeyFrames().add(kf0);
        timeline.getKeyFrames().add(kf1);
        timeline.play();
    }
	
	private void handleSmoothScrollKeyPressed(KeyEvent event, ScrollPane scrollPane) {
        double vValue = scrollPane.getVvalue();

        switch (event.getCode()) {
            case UP:
                vValue -= 0.2;
                vValue = Math.max(vValue, 0); // Ensure it doesn't scroll beyond the top
                smoothScroll(scrollPane,  vValue);
                break;
            case DOWN:
                vValue += 0.2;
                vValue = Math.min(vValue, 1); // Ensure it doesn't scroll beyond the bottom
                smoothScroll(scrollPane,  vValue);
                break;
		default:
			break;
        }
	}
	
	public static ArrayList<Integer> getCharacterIndexes(String str, String substr) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int index = str.indexOf(substr);
        while (index >= 0) {
            for (int i = 0; i < substr.length(); i++) {
                indexes.add(index + i);
            }
            index = str.indexOf(substr, index + 1);
        }
        return indexes;
    }
	
	private void SQLExceptionHandlerLogger(SQLException sqle, String input) {
		String sqlState = sqle.getSQLState();
	    switch (sqlState) {
	        case "08000":
	            logger.error("08000: Failed to connect to Database, Check your internet connection, otherwise HPM Server is down");
	            break;
	        case "08003":
	            logger.error("08003: Failed to modify data in local Database due to an invalid/closed connection with it");
	            break;
	        case "08006":
	        	logger.error("08006: Connection with Database failed while trying modifying data");
	            break;
	        case "22000":
	        	logger.error("22000: Invalid data type " + input);
	            break;
	        case "22001":
	        	logger.error("22001: User put in " + input + ", this string is too long");
	            break;
	        case "23000":
	        	logger.error("23000: Integrity Constraint Violation");
	            break;
	        case "23502":
	        	logger.error("23502: NOT NULL Violation");
	            break;
	        case "40001":
	        	logger.error("40001: Trying to modify data when there is already a concurrent modification happening from another source");
	            break;
	        default:
	            break;
	    }
	    if(advancedlogging) logger.error("Full Stacktrace: ", sqle);
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