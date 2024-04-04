package loginPage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class loginPage extends Application {
    private String pPass = "12345";
    private VBox root = new VBox();
    private Scene originalScene;
    private Stage primaryStage;
    private String userInput = "";
    private String passInput = "";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Health Co. Login Page");

        root.setPadding(new Insets(200, 200, 250, 200));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        TextField user = new TextField();
        user.setPromptText("User ID");
        user.setStyle("-fx-font-size: 1.25em;");

        TextField password = new TextField();
        password.setPromptText("Password");
        password.setStyle("-fx-font-size: 1.25em;");

        Image logoImage = new Image("file:///C:/Users/crazy/OneDrive/Pictures/HealthCoLogo.jpg");
        ImageView logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.setFitWidth(225);
        logo.setFitHeight(225);

        Image lockImage = new Image("file:///C:/Users/crazy/OneDrive/Pictures/lock.png");
        ImageView lock = new ImageView(lockImage);
        lock.setPreserveRatio(true);
        lock.setFitWidth(35);
        lock.setFitHeight(35);

        Image profileImage = new Image("file:///C:/Users/crazy/OneDrive/Pictures/profile.png");
        ImageView profile = new ImageView(profileImage);
        profile.setPreserveRatio(true);
        profile.setFitWidth(35);
        profile.setFitHeight(35);

        HBox userPane = new HBox(5);
        HBox passPane = new HBox(5);
        userPane.setAlignment(Pos.CENTER);
        passPane.setAlignment(Pos.CENTER);
        root.setMargin(userPane, new Insets(0, 15, 0, 0));
        root.setMargin(passPane, new Insets(0, 15, 0, 0));

        userPane.getChildren().addAll(profile, user);
        passPane.getChildren().addAll(lock, password);

        Label respondString = new Label("");
        respondString.setAlignment(Pos.CENTER);

        Button logButton = new Button("Login");
        logButton.setAlignment(Pos.CENTER);

        Label newPatient = new Label("New Patient? Create an account below");
        newPatient.setAlignment(Pos.CENTER);

        Button createButton = new Button("Create Account");
        logButton.setAlignment(Pos.CENTER);

        logButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userInput = user.getText();
                passInput = password.getText();
                respondString.setText("");
                if (userInput.charAt(0) == 'P' && passInput.equals(pPass)) {
                    overlayWithBlankScreen('P');
                    user.setText("");
                    password.setText("");
                } 
                else if (userInput.charAt(0) == 'D' && passInput.equals(pPass)) {
                	overlayWithBlankScreen('D');
                	user.setText("");
                    password.setText("");
                    
                } 
                else if (userInput.charAt(0) == 'N' && passInput.equals(pPass)) {
                	overlayWithBlankScreen('N');
                	user.setText("");
                    password.setText("");
                    
                } 
                else {
                    respondString.setText("Incorrect username or password. Please try again.");
                    password.setText("");
                }
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	overlayWithBlankScreen('X');
            	user.setText("");
                password.setText("");
            }
        });

        root.setStyle("-fx-background-color: #fefffa");
        root.getChildren().addAll(logo, userPane, passPane, respondString, logButton, newPatient, createButton);

        originalScene = new Scene(root, 800, 600);
        primaryStage.setScene(originalScene);
        primaryStage.show();
    }

    private void overlayWithBlankScreen(char role) {
    	
        VBox overlayLayout = new VBox();
        overlayLayout.setStyle("-fx-background-color: white;");
        overlayLayout.setAlignment(Pos.CENTER);
        overlayLayout.setSpacing(20);

        Label viewLabel = new Label("");
        if(role == 'P') {
        	viewLabel.setText("Implementation of Patient View");
        }
        else if(role == 'D') {
        	viewLabel.setText("Implementation of Doctor View");
        }
        else if(role == 'N') {
        	viewLabel.setText("Implementation of Nurse View");
        }
        else {
        	viewLabel.setText("Implementation of creating a new account");
        }
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
        	
        	primaryStage.setTitle("Health Co. Login Page");
            primaryStage.setScene(originalScene);
            
        });

        overlayLayout.getChildren().addAll(viewLabel, backButton);

        Scene overlayScene = new Scene(overlayLayout, 800, 600);

        primaryStage.setScene(overlayScene);
        primaryStage.setTitle("Placeholder View");
        primaryStage.show();
    }
}
