package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DatabaseFileHandler extends Controller {
	//DATABASE: manual db backup
		public static void localdbbackup(ActionEvent e) {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Back up Database");
	        
	        File file = fileChooser.showSaveDialog((Stage)((Node)e.getSource()).getScene().getWindow());
	        if (file != null) {
	            backupDatabase("accdb", file.getAbsolutePath());
	        }
	    }
	    
		//DATABASE: specify automatic db backup location
	    public static void automaticdbbackup(ActionEvent e) {
	    	
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
		
		//DATABASE: restore an old backup for a database
		public static void dbrestore(ActionEvent e) {
			
			FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Select Backup Database File");
	        
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
			
		}
		
		public static void backupDatabase(String sourcePath, String destPath) {
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
		
		public static void backupDatabasestatic(String sourcePath, String destPath) {
			
			LocalTime currenttimeforbackup = LocalTime.now();
			DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        String formattedTimeCustom = currenttimeforbackup.format(customFormatter);
	        
			String filename = File.separatorChar + "accdb BACKUP - " + LocalDate.now() + " - " + formattedTimeCustom;
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

		public static void restoreBackup(String backupDbPath, String originalDbPath) throws IOException {
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
