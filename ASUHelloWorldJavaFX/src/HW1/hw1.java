package HW1;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;

public class hw1 extends Application {
	private double cost = 0.0;
	private String storeCost = "";
	
	public static void main(String[] args) {
    launch(args);
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Joe's Deli");
		
		//Root node that is used to store everything
		VBox root = new VBox();
		
		//This HBox stores label at top of screen
		HBox joeHBox = new HBox();
		Label joeDeli = new Label("Joe's Deli");
		joeDeli.setAlignment(Pos.CENTER);
		joeDeli.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 32;");
		joeHBox.getChildren().add(joeDeli);
		joeHBox.setAlignment(Pos.TOP_CENTER);
		joeHBox.setPadding(new Insets(20));
		
		//Stores the 2 questions, food and drink, and the bill
		HBox questionsAndBill = new HBox();
		questionsAndBill.setPadding(new Insets(100, 0, 100, 50));
		questionsAndBill.setSpacing(100);
		
		//Stores the food question, which is 4 check boxes and a label.
		VBox foodStack = new VBox();
		foodStack.setSpacing(10);
		
		Label foodLab = new Label("Eat:");
		foodLab.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		foodStack.getChildren().add(foodLab);
		
		CheckBox egg = new CheckBox("Egg Sandwich");
		egg.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		foodStack.getChildren().add(egg);

		CheckBox chicken = new CheckBox("Chicken Sandwich");
		chicken.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		foodStack.getChildren().add(chicken);
		
		CheckBox bagel = new CheckBox("Bagel");
		bagel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		foodStack.getChildren().add(bagel);
		
		CheckBox potatoSalad = new CheckBox("Potato Salad");
		potatoSalad.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		foodStack.getChildren().add(potatoSalad);
		
		//Stores the drink question, which is 4 radio buttons and a label, so only 1 can be selected at once.
		VBox drinkStack = new VBox();
		drinkStack.setSpacing(10);
		ToggleGroup drinkChoice = new ToggleGroup();
		
		Label drinkLab = new Label("Drink: ");
		drinkLab.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		drinkStack.getChildren().add(drinkLab);
		
		RadioButton blackTea = new RadioButton("Black Tea");
		blackTea.setToggleGroup(drinkChoice);
		blackTea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		drinkStack.getChildren().add(blackTea);
		
		RadioButton greenTea = new RadioButton("Green Tea");
		greenTea.setToggleGroup(drinkChoice);
		greenTea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		drinkStack.getChildren().add(greenTea);
		
		RadioButton coffee = new RadioButton("Coffee");
		coffee.setToggleGroup(drinkChoice);
		coffee.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		drinkStack.getChildren().add(coffee);
		
		RadioButton orangeJuice = new RadioButton("Orange Juice");
		orangeJuice.setToggleGroup(drinkChoice);
		orangeJuice.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		drinkStack.getChildren().add(orangeJuice);
		
		//Stores the bill and the cost beneath it, which is stores alongside the food and drink question in questiosAndBill
		VBox billStackAndCost = new VBox();
		Label costLabel = new Label("");
		costLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		costLabel.setAlignment(Pos.BOTTOM_CENTER);
		
		//Bill stack, stores user selection and bill label
		VBox billStack = new VBox();
		Label bill = new Label("Bill: ");
		bill.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16;");
		billStack.getChildren().add(bill);
		billStack.setAlignment(Pos.TOP_CENTER);
		billStack.setPadding(new Insets(10));
		billStack.setPrefSize(200, 300);
		billStack.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		
		billStackAndCost.getChildren().addAll(billStack, costLabel);
		
		//Stores the buttons at the bottom of the screen
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		buttons.setSpacing(100);
		buttons.setPadding(new Insets(50));
		
		//Order Button, when it is clicked takes all selections from food and drink into the billStack VBox
		Button orderButton = new Button("Order");
		orderButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		orderButton.setAlignment(Pos.CENTER);
		orderButton.setPrefSize(100, 50);
		buttons.getChildren().add(orderButton);
		EventHandler<ActionEvent> orderButtonClick = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				if(egg.isSelected()) {
					cost += 7.99;
					billStack.getChildren().add(new Label("$7.99, " + egg.getText()));
				}
				if(chicken.isSelected()) {
					cost += 9.99;
					billStack.getChildren().add(new Label("$9.99, " + chicken.getText()));
				}
				if(bagel.isSelected()) {
					cost += 2.50;
					billStack.getChildren().add(new Label("$2.50, " + bagel.getText()));
				}
				if(potatoSalad.isSelected()) {
					cost += 4.49;
					billStack.getChildren().add(new Label("$4.49, " + potatoSalad.getText()));
				}
				if(drinkChoice.getSelectedToggle() != null) {
					if(((RadioButton) drinkChoice.getSelectedToggle()).getText() == "Black Tea") {
						cost += 1.25;
						storeCost = "$1.25, ";
					}
					if(((RadioButton) drinkChoice.getSelectedToggle()).getText() == "Green Tea") {
						cost += 0.99;
						storeCost = "$0.99, ";
					}
					if(((RadioButton) drinkChoice.getSelectedToggle()).getText() == "Orange Juice") {
						cost += 2.25;
						storeCost = "$2.25, ";
					}
					if(((RadioButton) drinkChoice.getSelectedToggle()).getText() == "Coffee") {
						cost += 1.99;
						storeCost = "$1.99, ";
					}
					billStack.getChildren().add(new Label(storeCost + ((RadioButton) drinkChoice.getSelectedToggle()).getText()));
				}
				costLabel.setText("Pre-Tax Cost: $" + String.format("%.2f", cost));
			}
		};
		
		orderButton.setOnAction(orderButtonClick);
			
		//Cancel button, clears billStack and gets rid of all selections from the user
		Button cancelButton = new Button("Cancel");
		cancelButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		cancelButton.setAlignment(Pos.CENTER);
		cancelButton.setPrefSize(100, 50);
		buttons.getChildren().add(cancelButton);
		EventHandler<ActionEvent> cancelButtonClick = new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				egg.setSelected(false);
				chicken.setSelected(false);
				bagel.setSelected(false);
				potatoSalad.setSelected(false);
				drinkChoice.selectToggle(null);
				billStackAndCost.getChildren().clear();
				billStack.getChildren().clear();
				billStack.getChildren().add(bill);
				cost = 0;
				costLabel.setText("");
				billStackAndCost.getChildren().addAll(billStack, costLabel);
			}
		};
		
		cancelButton.setOnAction(cancelButtonClick);
		
		//Confirm button, gets rid of all user selections and displays final cost to user
		Button confirmButton = new Button("Confirm");
		confirmButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		confirmButton.setAlignment(Pos.CENTER);
		confirmButton.setPrefSize(100, 50);
		buttons.getChildren().add(confirmButton);
		
		EventHandler<ActionEvent> confirmButtonClick = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				cost = cost * 1.07;
				egg.setSelected(false);
				chicken.setSelected(false);
				bagel.setSelected(false);
				potatoSalad.setSelected(false);
				drinkChoice.selectToggle(null);
				costLabel.setText("Final Cost Plus Tax: $" + String.format("%.2f", cost));
			}
		};
		
		confirmButton.setOnAction(confirmButtonClick);
		
		//Displays the screen to user
		questionsAndBill.getChildren().addAll(foodStack, drinkStack, billStackAndCost);
		root.getChildren().addAll(joeHBox, questionsAndBill, buttons);
		
        primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
		
	}
	
}