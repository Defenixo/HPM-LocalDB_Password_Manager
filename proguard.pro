# Keep the main application entry point
-keep public class application.Main {
    public static void main(java.lang.String[]);
}

# Keep all classes with the specified package name
-keep class application {
    *;
}

# Allow access modifications
-allowaccessmodification
-dontwarn com.example.**
-dontwarn org.openjfx.**
-dontwarn org.apache.logging.log4j.**
-dontwarn mysql.**
-dontwarn org.xerial.**
-keep class javafx.** { *; }
-keep class org.apache.logging.log4j.** { *; }
-keep class com.mysql.** { *; }
-keep class org.sqlite.** { *; }

# Add additional ProGuard rules as needed

