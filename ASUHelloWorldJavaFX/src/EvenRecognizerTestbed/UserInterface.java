package EvenRecognizerTestbed;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.TextArea;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface testbed to develop and test UI ideas.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2022 - 2024 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2022-02-07 The JavaFX-based GUI for the implementation of a testbed
 * @version 3.00		2024-01-28 Enhance the the GUI to include a display of the execution grace
 * 										of the Finite State Machine
 *  
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager
	   for this application. Rather we manually control the location of each graphical element for
	   exact control of the look and feel. */
	private final double BUTTON_WIDTH = 40;

	// These are the application values required by the user interface
	private Label label_ApplicationTitle = new Label("Even Number Recognizer Testbed");
	private Label label_MNumericValue = new Label("Enter a numeric value here and then press 'Go'!");
	private TextField text_NumericValue = new TextField();
	private Button button_Go= new Button("Go");
	private Label label_errNumericValue = new Label("");	
    private Label noInputFound = new Label("");
	private TextFlow errNumericValue;
    private Text errNumericValuePart1 = new Text();
    private Text errNumericValuePart2 = new Text();
    private Label validNumericValue = new Label("");
    private TextArea result = new TextArea();

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
		
		// Label theScene with the name of the testbed, centered at the top of the pane
		setupLabelUI(label_ApplicationTitle, "Arial", 24, EvenRecognizerTestbed.WINDOW_WIDTH, 
				Pos.CENTER, 0, 10);
		
		// Label the email address input field with a title just above it, left aligned
		setupLabelUI(label_MNumericValue, "Arial", 14, EvenRecognizerTestbed.WINDOW_WIDTH-10, 
				Pos.BASELINE_LEFT, 10, 50);
		
		// Establish the text input operand field and when anything changes in the numeric value,
		// the code will process the entire input to ensure that it is valid or in error.
		setupTextUI(text_NumericValue, "Arial", 18, EvenRecognizerTestbed.WINDOW_WIDTH-20,
				Pos.BASELINE_LEFT, 10, 70, true);
		text_NumericValue.textProperty().addListener((observable, oldValue, newValue) 
				-> {setNumericValue(); });
		
		// Establish an error message for when there is no input
		noInputFound.setTextFill(Color.RED);
		noInputFound.setAlignment(Pos.BASELINE_LEFT);
		setupLabelUI(noInputFound, "Arial", 14, EvenRecognizerTestbed.WINDOW_WIDTH-10, 
				Pos.BASELINE_LEFT, 10, 110);		
		
		// Establish an error message for the numeric value, left aligned
		label_errNumericValue.setTextFill(Color.RED);
		label_errNumericValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errNumericValue, "Arial", 14,  
						EvenRecognizerTestbed.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 126);		
		
		// Establish the Go button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Go, "Symbol", 24, BUTTON_WIDTH, Pos.BASELINE_LEFT, 
						EvenRecognizerTestbed.WINDOW_WIDTH/2-BUTTON_WIDTH/2, 180);
		button_Go.setOnAction((event) -> { performGo(); });
		
		// Error Message components for the numeric value
		errNumericValuePart1.setFill(Color.BLACK);
	    errNumericValuePart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errNumericValuePart2.setFill(Color.RED);
	    errNumericValuePart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errNumericValue = new TextFlow(errNumericValuePart1, errNumericValuePart2);
		errNumericValue.setMinWidth(EvenRecognizerTestbed.WINDOW_WIDTH-10); 
		errNumericValue.setLayoutX(20);  
		errNumericValue.setLayoutY(100);
		
		// Valid numeric value message
		validNumericValue.setTextFill(Color.GREEN);
		validNumericValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(validNumericValue, "Arial", 18,  
						EvenRecognizerTestbed.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 126);				
		
		// Establish the TextArea to hold the sequence of FSM transitions
		result.setMinWidth(EvenRecognizerTestbed.WINDOW_WIDTH-20);
		result.setMaxWidth(EvenRecognizerTestbed.WINDOW_WIDTH-20);
		result.setMinHeight(EvenRecognizerTestbed.WINDOW_HEIGHT-260);
		result.setMaxHeight(EvenRecognizerTestbed.WINDOW_HEIGHT-260);
		result.setLayoutX(10);
		result.setLayoutY(250);
		result.setEditable(false);
		result.setFont(Font.font("Monaco", 10));

		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_ApplicationTitle, label_MNumericValue, text_NumericValue, 
				noInputFound, label_errNumericValue, button_Go, errNumericValue, validNumericValue,
				result);

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	private void setNumericValue() {
		label_errNumericValue.setText("");
		noInputFound.setText("");
		errNumericValuePart1.setText("");
		errNumericValuePart2.setText("");
		validNumericValue.setText("");
//		performGo();			// To debug, remove the comment "//" at the beginning of the line
	}
	
	
	private void performGo() {
		String msg;
		String inputText = text_NumericValue.getText();
		if (inputText.isEmpty()) {
			msg = "No input text found!";
		    noInputFound.setText(msg);
		    result.setText(msg);
		}
		else
		{
			String errMessage = EvenRecognizer.checkForEvenValue(inputText, result);
			if (errMessage != "") {
				System.out.println(errMessage);
				label_errNumericValue.setText(EvenRecognizer.evenRecogErrorMessage);
				if (EvenRecognizer.evenRecogIndexofError <= -1) return;
				String input = EvenRecognizer.evenRecogInput;
				errNumericValuePart1.setText(input.substring(0, 
						EvenRecognizer.evenRecogIndexofError));
				errNumericValuePart2.setText("\u21EB");
			}
			else {
				System.out.println("Success! The numeric value is even.");
				msg = "Success! The numeric value is even.";
				validNumericValue.setText(msg);
				result.setText(result.getText() + msg);
			}
		}
	}
}
