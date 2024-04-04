package HW4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class HW4Interface extends Application {
    private Scene intakeView;
    private Scene patientIntakeView;
    private Scene CTScanTechView;
    private Scene patientView;
    private Stage primaryStage;
    Font timesNewRomanFont = Font.font("Times New Roman", 20);
    
    private VBox root = new VBox();
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    
    	
    	primaryStage.setTitle("Heart Health Imaging Intake");
    	
    	//Welcome Label at top of intake
    	Label welcomeLabel = new Label("Welcome to Heart Health Imaging and Recording System");
    	welcomeLabel.setAlignment(Pos.CENTER);
    	welcomeLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	//One of the buttons that takes to patient intake
    	Button patientIntakeButton = new Button("Patient Intake");
    	patientIntakeButton.setAlignment(Pos.CENTER);
    	patientIntakeButton.setPrefWidth(250);
    	patientIntakeButton.setPrefHeight(75);
    	patientIntakeButton.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";" + "-fx-background-color: #2c50f5;");
    	patientIntakeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	patientIntake();
            }
        });
        
    	//One of the buttons that takes to CT Technician View
        Button ctIntakeButton = new Button("CT Scan Tech View");
        ctIntakeButton.setAlignment(Pos.CENTER);
        ctIntakeButton.setPrefWidth(250);
        ctIntakeButton.setPrefHeight(75);
        ctIntakeButton.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";" + "-fx-background-color: #2c50f5;");
        ctIntakeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	CTTech();
            }
        });
        
        
        //One of the buttons that takes patient to patient view
        Button patientViewButton = new Button("Patient View");
        patientViewButton.setAlignment(Pos.CENTER);
        patientViewButton.setPrefWidth(250);
        patientViewButton.setPrefHeight(75);
        patientViewButton.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";" + "-fx-background-color: #2c50f5;");
        patientViewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	searchView();
            }
        });
        
        root.getChildren().addAll(welcomeLabel, patientIntakeButton, ctIntakeButton, patientViewButton);
        root.setAlignment(Pos.CENTER);
        
        root.setSpacing(40);
    	
    	intakeView = new Scene(root, 800, 600);
        primaryStage.setScene(intakeView);
        primaryStage.show();
    }
    
    //Patient Intake, from top button that allows for a first time patient to create an account
    private void patientIntake() {
    	VBox root1 = new VBox();
    	
    	primaryStage.setTitle("Patient Intake Form");
    	
    	Label backButton = new Label("<--Back");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle("-fx-underline: true; -fx-text-fill: blue;");
        backButton.setAlignment(Pos.TOP_LEFT);
        
        backButton.setOnMouseClicked(e -> {
        	
        	primaryStage.setTitle("Health Co. Login Page");
            primaryStage.setScene(intakeView);
            
        });
    	
        //Basic setup, sets up UI
    	HBox labelBox = new HBox();
    	Label pIntakeLabel = new Label("Patient Intake Form");
    	labelBox.getChildren().addAll(pIntakeLabel);
    	labelBox.setPadding(new Insets(0, 0, 0, 250));
    	pIntakeLabel.setAlignment(Pos.CENTER);
    	pIntakeLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	//First Name Input HBox Stack
    	HBox fNameBox = new HBox();
    	fNameBox.setSpacing(75);
    	Label fnLabel = new Label("First Name: ");
    	fnLabel.setAlignment(Pos.CENTER_LEFT);
    	fnLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField fnInput = new TextField();
    	fnInput.setPrefWidth(300);
    	fnInput.setPrefHeight(25);
    	fNameBox.getChildren().addAll(fnLabel, fnInput);
    	fNameBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Last Name Input HBox Stack
    	HBox lNameBox = new HBox();
    	lNameBox.setSpacing(75);
    	Label lnLabel = new Label("Last Name: ");
    	lnLabel.setAlignment(Pos.CENTER_LEFT);
    	lnLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField lnInput = new TextField();
    	lnInput.setPrefWidth(300);
    	lnInput.setPrefHeight(25);
    	lNameBox.getChildren().addAll(lnLabel, lnInput);
    	lNameBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Email Box Input HBox Stack
    	HBox emailBox = new HBox();
    	emailBox.setSpacing(78);
    	Label emLabel = new Label("Email:        ");
    	emLabel.setAlignment(Pos.CENTER_LEFT);
    	emLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField emInput = new TextField();
    	emInput.setPrefWidth(300);
    	emInput.setPrefHeight(25);
    	emailBox.getChildren().addAll(emLabel, emInput);
    	emailBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Phone Box Input HBox Stack
    	HBox phoneBox = new HBox();
    	phoneBox.setSpacing(40);
    	Label phLabel = new Label("Phone Number: ");
    	phLabel.setAlignment(Pos.CENTER_LEFT);
    	phLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField phInput = new TextField();
    	phInput.setPrefWidth(300);
    	phInput.setPrefHeight(25);
    	phoneBox.getChildren().addAll(phLabel, phInput);
    	phoneBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Health Box Input HBox Stack
    	HBox healthBox = new HBox();
    	healthBox.setSpacing(40);
    	Label heLabel = new Label("Health History: ");
    	heLabel.setAlignment(Pos.CENTER_LEFT);
    	heLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField heInput = new TextField();
    	heInput.setPrefWidth(300);
    	heInput.setPrefHeight(25);
    	healthBox.getChildren().addAll(heLabel, heInput);
    	healthBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Insurance Box Input HBox Stack, add in save button
    	HBox insuranceBox = new HBox();
    	insuranceBox.setSpacing(54);
    	Label insLabel = new Label("Insurance ID: ");
    	insLabel.setAlignment(Pos.CENTER_LEFT);
    	insLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField insInput = new TextField();
    	insInput.setPrefWidth(300);
    	insInput.setPrefHeight(25);
    	
    	Button saveButton = new Button("Save");
    	saveButton.setPrefWidth(175);
    	saveButton.setPrefHeight(75);
    	saveButton.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";" + "-fx-background-color: #2c50f5;");
    	insuranceBox.getChildren().addAll(insLabel, insInput, saveButton);
    	insuranceBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Saves the information to a file, checks if all fields are full first
    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int newNumber = 0;
            	pIntakeLabel.setText("Patient Intake Form");
            	//Handles error of missing input, this whole box of text is to generate a new unique ID by looking through uniqueID.txt until last line and adding 1
            	if(!fnInput.getText().isEmpty() && !lnInput.getText().isEmpty() && !emInput.getText().isEmpty() && !phInput.getText().isEmpty() && !heInput.getText().isEmpty() && !insInput.getText().isEmpty()) {
	            	//Need to change this to path of whoever is running the file 
            		String filePath = "C:\\Users\\crazy\\Downloads\\ASUHelloWorldJavaFX\\ASUHelloWorldJavaFX\\src\\HW4\\uniqueID.txt";
	            	 try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	                     String line;
	                     String lastLine = null;
	                     while ((line = reader.readLine()) != null) {
	                         lastLine = line;
	                     }
	                     
	                     if (lastLine != null) {
	                         int lastNumber = Integer.parseInt(lastLine.trim());
	                         newNumber = lastNumber + 1;
	                         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
	                             writer.newLine();
	                             writer.write(String.valueOf(newNumber));
	                         }
	                     }
	                  
	            	 } 
	            	catch (FileNotFoundException e) {
						System.out.print("File was not found");
					} 
	            	catch (IOException e) {
						System.out.print("IOException");
					}
	            	
	            	String newFile = "C:\\Users\\crazy\\Downloads\\ASUHelloWorldJavaFX\\ASUHelloWorldJavaFX\\src\\HW4\\" + newNumber + "_PatientInfo.txt";
	            	try {
						File newPatFile = new File(newFile);
						try (BufferedWriter writer = new BufferedWriter(new FileWriter(newPatFile))) {		                
							writer.write(newNumber + "\n");	
							writer.write(fnInput.getText() + "\n");
							writer.write(lnInput.getText() + "\n");
							writer.write(emInput.getText() + "\n");
							writer.write(phInput.getText() + "\n");
							writer.write(heInput.getText() + "\n");
							writer.write(insInput.getText() + "\n");
			            }
					}
	            	catch (IOException e) {
	            		System.out.print("IOException");
					}
	            	primaryStage.setTitle("Heart Health Imaging Intake");
	                primaryStage.setScene(intakeView);
            	}
            	//Does nothing but informs user they need to fill all fields
            	else {
            		pIntakeLabel.setText("Error - Not All Fields Filled Out");
            	}
            }
        });
    	
    	//Add all to root1 
    	root1.getChildren().addAll(backButton, labelBox, fNameBox, lNameBox, emailBox, phoneBox, healthBox, insuranceBox);
    	root1.setAlignment(Pos.CENTER_LEFT);
    	root1.setSpacing(50);
    	root1.setPadding(new Insets(50));
    	patientIntakeView = new Scene(root1, 800, 600);
        primaryStage.setScene(patientIntakeView);
        primaryStage.show();
    }
    
    //CT Technician Input fields for a patient
    private void CTTech() {
    	VBox root2 = new VBox();
    	
    	//Back to original intake
    	Label backButton = new Label("<--Back");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle("-fx-underline: true; -fx-text-fill: blue;");
        backButton.setAlignment(Pos.TOP_LEFT);
        
        backButton.setOnMouseClicked(e -> {
        	
        	primaryStage.setTitle("CT Technician Input Page");
            primaryStage.setScene(intakeView);
            
        });
    	
        //Sets up basic UI for CT technician
    	HBox patientIDBox = new HBox();
    	patientIDBox.setSpacing(230);
    	Label piLabel = new Label("Patient ID: ");
    	piLabel.setAlignment(Pos.CENTER_LEFT);
    	piLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField piInput = new TextField();
    	piInput.setPrefWidth(300);
    	piInput.setPrefHeight(25);
    	patientIDBox.getChildren().addAll(piLabel, piInput);
    	patientIDBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox cacScoreBox = new HBox();
    	cacScoreBox.setSpacing(75);
    	Label cacLabel = new Label("The total Agatson CAC score: ");
    	cacLabel.setAlignment(Pos.CENTER_LEFT);
    	cacLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField cacInput = new TextField();
    	cacInput.setPrefWidth(300);
    	cacInput.setPrefHeight(25);
    	cacScoreBox.getChildren().addAll(cacLabel, cacInput);
    	cacScoreBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox vesselBox = new HBox();
    	cacScoreBox.setSpacing(75);
    	Label vesselLabel = new Label("Vessel Level Agatson CAC score");
    	vesselLabel.setAlignment(Pos.CENTER_LEFT);
    	vesselLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	vesselBox.getChildren().addAll(vesselLabel);
    	
    	HBox lmBox = new HBox();
    	lmBox.setSpacing(280);
    	Label lmLabel = new Label("LM: ");
    	lmLabel.setAlignment(Pos.CENTER_LEFT);
    	lmLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField lmInput = new TextField();
    	lmInput.setPrefWidth(300);
    	lmInput.setPrefHeight(25);
    	lmBox.getChildren().addAll(lmLabel, lmInput);
    	lmBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox ladBox = new HBox();
    	ladBox.setSpacing(268);
    	Label ladLabel = new Label("LAD: ");
    	ladLabel.setAlignment(Pos.CENTER_LEFT);
    	ladLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField ladInput = new TextField();
    	ladInput.setPrefWidth(300);
    	ladInput.setPrefHeight(25);
    	ladBox.getChildren().addAll(ladLabel, ladInput);
    	ladBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox lcxBox = new HBox();
    	lcxBox.setSpacing(268);
    	Label lcxLabel = new Label("LCX: ");
    	lcxLabel.setAlignment(Pos.CENTER_LEFT);
    	lcxLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField lcxInput = new TextField();
    	lcxInput.setPrefWidth(300);
    	lcxInput.setPrefHeight(25);
    	lcxBox.getChildren().addAll(lcxLabel, lcxInput);
    	lcxBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox rcaBox = new HBox();
    	rcaBox.setSpacing(268);
    	Label rcaLabel = new Label("RCA: ");
    	rcaLabel.setAlignment(Pos.CENTER_LEFT);
    	rcaLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField rcaInput = new TextField();
    	rcaInput.setPrefWidth(300);
    	rcaInput.setPrefHeight(25);
    	rcaBox.getChildren().addAll(rcaLabel, rcaInput);
    	rcaBox.setAlignment(Pos.CENTER_LEFT);

    	HBox pdaBox = new HBox();
    	pdaBox.setSpacing(0);
    	Label pdaLabel = new Label("PDA:                                                      ");
    	pdaLabel.setAlignment(Pos.CENTER_LEFT);
    	pdaLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField pdaInput = new TextField();
    	pdaInput.setPrefWidth(300);
    	pdaInput.setPrefHeight(25);
    	
    	Button saveButton = new Button("Save");
    	saveButton.setPrefWidth(175);
    	saveButton.setPrefHeight(75);
    	saveButton.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";" + "-fx-background-color: #2c50f5;");
    	
    	pdaBox.getChildren().addAll(pdaLabel, pdaInput, saveButton);
    	pdaBox.setAlignment(Pos.CENTER_LEFT);
    	
    	//Creates a new file for CT Technician information
    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	vesselLabel.setText("Vessel Level Agatson CAC score");
            	String filePath = "C:\\Users\\crazy\\Downloads\\ASUHelloWorldJavaFX\\ASUHelloWorldJavaFX\\src\\HW4\\uniqueID.txt";
            	int patID = Integer.parseInt(piInput.getText());
            	boolean found = false;
            	//Checks if all fields are full
            	if(!piInput.getText().isEmpty() && !cacInput.getText().isEmpty() && !lmInput.getText().isEmpty() && !ladInput.getText().isEmpty() && !lcxInput.getText().isEmpty() && !rcaInput.getText().isEmpty() && !pdaInput.getText().isEmpty()) {

            		 try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            	            String line;
            	            found = false;
            	            
            	            while ((line = reader.readLine()) != null) {
            	                int lineNumber = Integer.parseInt(line.trim());

            	                if (lineNumber == patID) {
            	                    found = true;
            	                    break;
            	                }
            	            }
            		 } catch (FileNotFoundException e) {
						System.out.print("File not found");
					} catch (IOException e) {
						System.out.print("IO Exception");
					}
            		 //Checks if patient ID exists
            		if(found) {
            			String pathToPatient = "C:\\Users\\crazy\\Downloads\\ASUHelloWorldJavaFX\\ASUHelloWorldJavaFX\\src\\HW4\\" + patID + "CTResults.txt";
            			 try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToPatient))) {
            		            writer.write(cacInput.getText() + "\n");
            		            writer.write(lmInput.getText() + "\n");
            		            writer.write(ladInput.getText() + "\n");
            		            writer.write(lcxInput.getText() + "\n");
            		            writer.write(rcaInput.getText() + "\n");
            		            writer.write(pdaInput.getText() + "\n");
            			 } catch (IOException e) {
							System.out.print("IO Exception");
						}
            			primaryStage.setTitle("Heart Health Imaging Intake");
     	                primaryStage.setScene(intakeView);
            		}
            		//If patient ID is not found offers to input a new one
            		else {
            			vesselLabel.setText("Error - Patient ID not Found");
            		}
            	}
            	else {
            		vesselLabel.setText("Error - Not All Fields Filled Out");
            	}
            }
        });
    	
    	root2.getChildren().addAll(backButton, patientIDBox, cacScoreBox, vesselBox, lmBox, ladBox, lcxBox, rcaBox, pdaBox);
    	root2.setAlignment(Pos.CENTER_LEFT);
    	root2.setSpacing(30);
    	CTScanTechView = new Scene(root2, 800, 600);
        primaryStage.setScene(CTScanTechView);
        primaryStage.show();
    	
    }
    
    //Patient view for patients to look at information health care has collected
    private void patientView(String PatientID) {
    	VBox root3 = new VBox();
    	
    	String firstName = "null";
    	String lastName = "null";
    	String totalCAC = "null";
    	String lm = "null";
    	String lad = "null";
    	String lcx = "null";
    	String rca = "null";
    	String pda = "null";
    	
    	String filePath = "C:\\Users\\crazy\\Downloads\\ASUHelloWorldJavaFX\\ASUHelloWorldJavaFX\\src\\HW4\\" + PatientID + "_PatientInfo.txt";
    	
    	//Grabs all required information from the two files
    	try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the first line
    		String throwaway = reader.readLine();
            firstName = reader.readLine();
            lastName = reader.readLine();
    	} catch (FileNotFoundException e1) {
			System.out.print("File Not Found");
		} catch (IOException e1) {
			System.out.print("IO Exception");
		}
    	
    	String filePath2 = "C:\\Users\\crazy\\Downloads\\ASUHelloWorldJavaFX\\ASUHelloWorldJavaFX\\src\\HW4\\" + PatientID + "CTResults.txt";
    	try (BufferedReader reader = new BufferedReader(new FileReader(filePath2))) {
            // Read the first line
    		totalCAC = reader.readLine();
    		lm = reader.readLine();
    		lad = reader.readLine();
    		lcx = reader.readLine();
    		rca = reader.readLine();
    		pda = reader.readLine();
    	} catch (FileNotFoundException e1) {
			System.out.print("File Not Found");
		} catch (IOException e1) {
			System.out.print("IO Exception");
		}
    	
    	
    	HBox bandn = new HBox();
    	Label backButton = new Label("<--Back   ");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle("-fx-underline: true; -fx-text-fill: blue;");
        backButton.setAlignment(Pos.TOP_LEFT);
        
        backButton.setOnMouseClicked(e -> {
        	
        	primaryStage.setTitle("Search for Patient ID");
            primaryStage.setScene(intakeView);
            
        });
        
        //Sets up UI
        Label name = new Label("Hello, " + firstName + " " + lastName);
        name.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
        bandn.getChildren().addAll(backButton, name);
        bandn.setSpacing(200);
    	
        HBox cacScoreBox = new HBox();
    	cacScoreBox.setSpacing(75);
    	Label cacLabel = new Label("The total Agatson CAC score: ");
    	cacLabel.setAlignment(Pos.CENTER_LEFT);
    	cacLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField cacInput = new TextField(totalCAC);
    	cacInput.setEditable(false);
    	cacInput.setPrefWidth(300);
    	cacInput.setPrefHeight(25);
    	cacScoreBox.getChildren().addAll(cacLabel, cacInput);
    	cacScoreBox.setAlignment(Pos.CENTER_LEFT);
        
    	HBox lmBox = new HBox();
    	lmBox.setSpacing(280);
    	Label lmLabel = new Label("LM: ");
    	lmLabel.setAlignment(Pos.CENTER_LEFT);
    	lmLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField lmInput = new TextField(lm);
    	lmInput.setEditable(false);
    	lmInput.setPrefWidth(300);
    	lmInput.setPrefHeight(25);
    	lmBox.getChildren().addAll(lmLabel, lmInput);
    	lmBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox ladBox = new HBox();
    	ladBox.setSpacing(268);
    	Label ladLabel = new Label("LAD: ");
    	ladLabel.setAlignment(Pos.CENTER_LEFT);
    	ladLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField ladInput = new TextField(lad);
    	ladInput.setEditable(false);
    	ladInput.setPrefWidth(300);
    	ladInput.setPrefHeight(25);
    	ladBox.getChildren().addAll(ladLabel, ladInput);
    	ladBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox lcxBox = new HBox();
    	lcxBox.setSpacing(268);
    	Label lcxLabel = new Label("LCX: ");
    	lcxLabel.setAlignment(Pos.CENTER_LEFT);
    	lcxLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField lcxInput = new TextField(lcx);
    	lcxInput.setEditable(false);
    	lcxInput.setPrefWidth(300);
    	lcxInput.setPrefHeight(25);
    	lcxBox.getChildren().addAll(lcxLabel, lcxInput);
    	lcxBox.setAlignment(Pos.CENTER_LEFT);
    	
    	HBox rcaBox = new HBox();
    	rcaBox.setSpacing(268);
    	Label rcaLabel = new Label("RCA: ");
    	rcaLabel.setAlignment(Pos.CENTER_LEFT);
    	rcaLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField rcaInput = new TextField(rca);
    	rcaInput.setEditable(false);
    	rcaInput.setPrefWidth(300);
    	rcaInput.setPrefHeight(25);
    	rcaBox.getChildren().addAll(rcaLabel, rcaInput);
    	rcaBox.setAlignment(Pos.CENTER_LEFT);

    	HBox pdaBox = new HBox();
    	pdaBox.setSpacing(0);
    	Label pdaLabel = new Label("PDA:                                                      ");
    	pdaLabel.setAlignment(Pos.CENTER_LEFT);
    	pdaLabel.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField pdaInput = new TextField(pda);
    	pdaInput.setEditable(false);
    	pdaInput.setPrefWidth(300);
    	pdaInput.setPrefHeight(25);
    	pdaBox.getChildren().addAll(pdaLabel, pdaInput);
    	pdaBox.setAlignment(Pos.CENTER_LEFT);
    	
    	root3.getChildren().addAll(bandn, cacScoreBox, lmBox, ladBox, lcxBox, rcaBox, pdaBox); 
    	root3.setSpacing(50);
    	patientView = new Scene(root3, 800, 600);
        primaryStage.setScene(patientView);
        primaryStage.show();
    	
    }
    
    //View before patient can see their info, asks for their unique patient ID
    private void searchView() {
    	VBox root4 = new VBox();
    	
    	Label backButton = new Label("<--Back   ");
        backButton.setFont(Font.font("Arial", 20));
        backButton.setStyle("-fx-underline: true; -fx-text-fill: blue;");
        backButton.setAlignment(Pos.TOP_LEFT);
        
        backButton.setOnMouseClicked(e -> {
        	
        	primaryStage.setTitle("Search for Patient ID");
            primaryStage.setScene(intakeView);
            
        });
    	
    	HBox pBox = new HBox();
    	Label enterPID = new Label("Enter the patient ID: ");
    	enterPID.setAlignment(Pos.CENTER);
    	enterPID.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";");
    	
    	TextField pidField = new TextField();
    	pidField.setPrefWidth(300);
    	pidField.setPrefHeight(25);
    	pBox.getChildren().addAll(backButton, enterPID, pidField);
    	pBox.setAlignment(Pos.CENTER);
    	
    	Button enterButton = new Button("Enter");
    	enterButton.setAlignment(Pos.CENTER);
    	enterButton.setPrefWidth(250);
    	enterButton.setPrefHeight(75);
    	enterButton.setStyle("-fx-font: " + timesNewRomanFont.getSize() + "px \"Times New Roman\";" + "-fx-background-color: #2c50f5;");
    	enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String filePath = "C:\\Users\\crazy\\Downloads\\ASUHelloWorldJavaFX\\ASUHelloWorldJavaFX\\src\\HW4\\uniqueID.txt";
            	boolean found = false;
            	try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    	            String line;
    	            found = false;
    	            
    	            while ((line = reader.readLine()) != null) {
    	                int lineNumber = Integer.parseInt(line.trim());

    	                if (lineNumber == Integer.parseInt(pidField.getText())) {
    	                    found = true;
    	                    break;
    	                }
    	            }
    		 } catch (FileNotFoundException e) {
				System.out.print("File not found");
			} catch (IOException e) {
				System.out.print("IO Exception");
			}
            	
            	if(found) {
            		patientView(pidField.getText());
            	}
            	else {
            		enterPID.setText("Error - No such patient found");
            	}
            }
        });
    	
    	root4.getChildren().addAll(pBox, enterButton);
    	root4.setSpacing(50);
    	root4.setAlignment(Pos.CENTER);
    	Scene entPIDView = new Scene(root4, 800, 600);
        primaryStage.setScene(entPIDView);
        primaryStage.show();
    	
    }
    
};