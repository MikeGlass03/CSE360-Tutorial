package EvenRecognizerTestbed;

import javafx.scene.control.TextArea;

public class EvenRecognizer {
	/**
	 * <p> Title: FSM-translated EvenRecognizer. </p>
	 * 
	 * <p> Description: A demonstration of the mechanical translation of Finite State Machine 
	 * diagram into an executable Java program using the Even Recognizer. The code 
	 * detailed design is based on a while loop with a select list</p>
	 * 
	 * <p> Copyright: Lynn Robert Carter © 2022 - 2024 </p>
	 * 
	 * @author Lynn Robert Carter
	 * 
	 * @version 1.00		2022-02-07	Initial baseline
	 * @version 1.01		2022-03-16	Removed unneeded break statements in the switch statement
	 * @version 2.0			2022-03-18	Corrected the leading zero counting defect and the zero 
	 * 										after an odd digit defect.
	 * @version 3.0			2024-01-28	Enhance to support Spring 2024
	 */

	/**********************************************************************************************
	 * 
	 * Result attributes to be used for GUI applications where a detailed error message and a 
	 * pointer to the character of the error will enhance the user experience.
	 * 
	 */

	public static String evenRecogErrorMessage = "";	// The error message text
	public static String evenRecogInput = "";			// The input being processed
	public static int evenRecogIndexofError = -1;		// The index where the error was located
	private static int state = 0;						// The current state value
	private static int nextState = 0;					// The next state value
	private static boolean finalState = false;			// Is this state a final state?
	private static String inputLine = "";				// The input line
	private static char currentChar;					// The current character in the line
	private static int currentCharNdx;					// The index of the current character
	private static boolean running;						// The flag that specifies if the FSM is 
														// 		running
	private static int numericValueSize = 0;			// A numeric value may not exceed 16 characters
	private static String msg;							// The next output for the console and the GUI
	private static String resultMsg;					// The string used to output the trace to the GUI
	private static TextArea result;						// Reference to the GUI TextArea

	/**********
	 * This private method display the input line and then on a line under it displays an up arrow
	 * at the point where an error should one be detected.  This method is designed to be used to 
	 * display the error message on the console terminal.
	 * 
	 * @param input				The input string
	 * @param currentCharNdx	The location where an error was found
	 * @return					Two lines, the entire input line followed by a line with an up arrow
	 */
	private static String displayInput(String input, int currentCharNdx) {
		// Fetch the entire input line
		String result = input.substring(0,currentCharNdx) + "?\n";
		return result;
	}


	private static void displayDebuggingInfo() {
		if (state == 3)				// See if the current state was a final state
			finalState = true;
		else
			finalState = false;
		// Display the current state of the FSM as part of an execution trace
		// and append the information onto the string for the GUI TextArea
		if (currentCharNdx >= inputLine.length())
		{
			// display the line with the current state numbers aligned
			msg = ((state > 99) ? " " : (state > 9) ? "  " : "   ") + state + 
					((finalState) ? "       F   " : "           ") + "None";
		}
		else
		{
			// display the line with the current state numbers aligned with the next input character
			msg = ((state > 99) ? " " : (state > 9) ? "  " : "   ") + state + 
					((finalState) ? "       F   " : "           ") + "  " + currentChar + " " + 
					((nextState > 99) ? "" : (nextState > 9) || (nextState == -1) ? "   " : "    ") + 
					nextState + "     " + numericValueSize;
		}
		// Output the line to the console and append it to the TextAtrea string
		System.out.println(msg);
		resultMsg += msg + "\n";
	}

	private static void moveToNextCharacter() {
		// Advance to the next character in the input
		currentCharNdx++;
		if (currentCharNdx < inputLine.length())
			// Fetch the next character
			currentChar = inputLine.charAt(currentCharNdx);
		else {
			// If at the end of the input line, simulate a blank and stop the recognition loop
			currentChar = ' ';
			running = false;
		}
	}

	/**********
	 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
	 * method.
	 * 
	 * @param input		The input string for the Finite State Machine
	 * @param r			TextArea reference so this method can output the FSM trace to the GUI
	 * @return			An output string that is empty if every things is okay or it will be
	 * 						a string with a help description of the error follow by two lines
	 * 						that shows the input line follow by a line with an up arrow at the
	 *						point where the error was found.
	 */
	public static String checkForEvenValue(String input, TextArea r) {
		// Do not process if there is no input
		if(input.length() <= 0) return "";
		
		// Set up the TextArea for output via the GUI
		result = r;
		resultMsg = "";
		
		// The following are the local variable used to perform the Finite State Machine simulation
		state = 0;							// This is the FSM state number
		inputLine = input;					// Save the reference to the input line as a global
		currentCharNdx = 0;					// The index of the current character
		currentChar = input.charAt(0);		// The current character from the above indexed position

		// The Finite State Machines continues until the end of the input is reached or at some 
		// state the current character does not match any valid transition to a next state

		evenRecogInput = input;				// Save a copy of the input
		running = true;						// Start the loop
		nextState = -1;						// There is no next state
		
		// Output the FSM Trace to the console and append to the TextArea string
		msg = "\nCurrent Final Input  Next  Number\nState   State Char  State  Size";
		System.out.println(msg);
		resultMsg += msg + "\n";

		// The Finite State Machines continues until the end of the input is reached or at some 
		// state the current character does not match any valid transition to a next state
		while (running) {
			// The switch statement takes the execution to the code for the current state, where
			// that code sees whether or not the current character is valid to transition to a
			// next state
			finalState = false;		// The initial state is not a final state
			switch (state) {
			case 0: // FSM State 0
				// Reset the Numeric Value size
				numericValueSize = 0;

				// State 0 has 3 valid transition that are addressed by an if statement.
				
				// The current character is must be checked against 12 options (the ten numeric
				// and the "+" and "-" characters). If any are matched the FSM must go to one of
				// three states
				// 1, 3, 5, 7, 9 -> State 1
				if (currentChar == '1' || currentChar == '3' ||			// Check for odd
						currentChar == '5' || currentChar == '7' ||
						currentChar == '9') {
					nextState = 1;			// The new FSM state based on this input
					
					// Count the odd digit
					numericValueSize++;
				}
				// 0, +, - -> State 2
				else if (currentChar == '0' || currentChar == '+' ||	// Check for 0, +, -
						currentChar == '9') {
					nextState = 2;			// The new FSM state based on this input
					
					// Count leading zeroes
					numericValueSize++;								
				}
				// 2, 4, 6, 8 -> State 3
				else if (currentChar == '2' || currentChar == '4' ||	// Check for even
						currentChar == '6' || currentChar == '8') {
					nextState = 3;			// The new FSM state based on this input
					
					// Count the even digit
					numericValueSize++;				
				}				
				// If it is none of those characters, the FSM halts
				else {
					running = false;		// Stop the FSM loop
				}
				
				// The execution of State 0 is finished
				break;
			
			case 1:  // FSM State 1
				// State 1 has two valid transitions, an odd digit transitions back to
				// state 1, while an even transitions to state 3 

				// 1, 3, 5, 7, 9 -> State 1
				if (currentChar == '1' || currentChar == '3' ||			// Check for odd
						currentChar == '5' || currentChar == '7' ||
						currentChar == '9') {
					nextState = 1;			// The new FSM state based on this input
					
					// Count the odd digit
					numericValueSize++;
				}
				// 0, 2, 4, 6, 8 -> State 3
				else if (currentChar == '0' || currentChar == '2' ||	// Check for even
						currentChar == '4' || currentChar == '6' ||
						currentChar == '8') {
					nextState = 3;			// The new FSM state based on this input
					
					// Count the even digit
					numericValueSize++;
				}				
				// If it is neither even or odd, the FSM halts
				else {
					running = false;		// Stop the FSM loop
				}
				// The execution of State 1 is finished
				break;			
				
			case 2:  // FSM State 2
				// State 2 deals with leading zeros, a leading "+" or a leading "-" and has the
				// similar transitions as State 0.  (There is no transition for a "+" or a "-".)
				
				// The current character is must be checked against 10 options. If any are matched
				// the FSM must go to one of three states

				// 1, 3, 5, 7, 9 -> State 1
				if (currentChar == '1' || currentChar == '3' ||			// Check for odd
						currentChar == '5' || currentChar == '7' ||
						currentChar == '9') {
					nextState = 1;			// The new FSM state based on this input
					
					// Count the odd digit
					numericValueSize++;
				}
				// 0 -> State 2
				else if (currentChar == '0') {
					nextState = 2;			// The new FSM state based on this input
					
					// Count the leading zero digit
					numericValueSize++;								
				}
				// 2, 4, 6, 8 -> State 3
				else if (currentChar == '2' || currentChar == '4' ||	// Check for even
						currentChar == '6' || currentChar == '8') {
					nextState = 3;			// The new FSM state based on this input
					
					// Count the even digit
					numericValueSize++;
				}				

				// If it is none of those characters, the FSM halts
				else {
					running = false;		// Stop the FSM loop
				}

				// The execution of State 2 is finished
				break;
	
			case 3: // FSM State 3
				// State 3 has two valid transition.  Each is addressed by an if statement.

				// 1, 3, 5, 7, 9 -> State 1
				if (currentChar == '1' || currentChar == '3' ||			// Check for odd
						currentChar == '5' || currentChar == '7' ||
						currentChar == '9') {
					nextState = 1;			// The new FSM state based on this input

					// Count the odd digit
					numericValueSize++;
				}
				// 0, 2, 4, 6, 8 -> State 3
				else if (currentChar == '0' || currentChar == '2' ||	// Check for even
						currentChar == '4' || currentChar == '6' ||
						currentChar == '8') {
					nextState = 3;			// The new FSM state based on this input

					// Count the even digit
					numericValueSize++;				
				}				

				// If it is none of those characters, the FSM halts
				else {
					running = false;		// Stop the FSM loop
				}

				// The execution of State 3 is finished
				break;
			}
			
			// When an iteration finishes, we must determine if the FSM is still running
			// successful or not.
			if (running) {
				// If it is, we must output the debugging information and move to the next character
				displayDebuggingInfo();
				// When the processing of a state has finished, the FSM proceeds to the next character
				// in the input and if there is one, it fetches that character and updates the 
				// currentChar.  If there is no next character the currentChar is set to a blank.
				moveToNextCharacter();
				
				// If the input is too long, we must display an appropriate error message and return
				if (numericValueSize > 16) {
					evenRecogErrorMessage = "A valid numeric value must be no longer than 16 characters.\n";
					// Append the error message to the TextArea string and then display it via the GUI
					result.setText(resultMsg + "\n" + evenRecogErrorMessage + displayInput(input, 16));
					
					// Return the error message as the method's returned value
					return evenRecogErrorMessage + displayInput(input, 16);
				}

				// Move to the next state
				state = nextState;			// Move to the new state
				nextState = -1;				// Signal at this point there is not next state
			}
			// Should the FSM get here, the loop starts again
		}
		
		// When the execution gets here, the FSM loop has terminated
		
		// Display the final debugging information
		displayDebuggingInfo();
		
		// Set up and display the message to the console and the GIUI
		msg = "The loop has ended.";
		System.out.println(msg);
		resultMsg += msg + "\n";

		// Prepare to provide the proper end of loop feedback... Success to Failure
		evenRecogIndexofError = currentCharNdx;		// Copy the index of the current character;
		
		// When the FSM halts, we must determine if the situation is an error or not.  That depends
		// of the current state of the FSM and whether or not the whole string has been consumed.
		// This switch directs the execution to separate code for each of the FSM states and that
		// makes it possible for this code to display a very specific error message to improve the
		// user experience.
		switch (state) {
		case 0:
			// State 0 is not a final state, so we can return a very specific error message
			evenRecogIndexofError = currentCharNdx-1;		// Copy the index of the current character;
			evenRecogErrorMessage = "The input must start with a numeric digit\n";
			
			// Append the error message to the TextArea string and then display it to the GUI
			result.setText(resultMsg + "\n" + inputLine + "\n" + evenRecogErrorMessage);
			
			// Return from this recognizer with the error message as the returned value
			return evenRecogErrorMessage;

		case 1:
			// State 1 is not a final state, so we can return a very specific error message
			evenRecogIndexofError = currentCharNdx-1;		// Copy the index of the current character;
			evenRecogErrorMessage = "The input is odd since the last digit is odd\n";
			
			// Append the error message to the TextArea string and then display it to the GUI
			result.setText(resultMsg + "\n" + evenRecogErrorMessage + inputLine + "\n" +
					displayInput(input, currentCharNdx-1));
			
			// Return from this recognizer with the error message as the returned value
			return evenRecogErrorMessage + displayInput(input, currentCharNdx-1);

		case 2:
			// State 2 is not a final state, so we can return a very specific error message
			evenRecogIndexofError = currentCharNdx-1;		// Copy the index of the current character;
			evenRecogErrorMessage = "Zero is not considered to be even.\n";
			
			// Append the error message to the TextArea string and then display it it to the GUI
			result.setText(resultMsg + "\n" + evenRecogErrorMessage + inputLine + "\n" + 
					displayInput(input, currentCharNdx-1));
			
			// Return from this recognizer with the error message as the returned value
			return evenRecogErrorMessage + displayInput(input, currentCharNdx-1);
						
		case 3:
			// State 3 is a Final State, so this is not an error if the input is empty.
			if (currentCharNdx<input.length()) {
				// If not all of the string has been consumed, we point to the current character
				// in the input line and specify what that character must be in order to move
				// forward.
				evenRecogIndexofError = currentCharNdx;		// Copy the index of the current character;
				evenRecogErrorMessage = "A numeric value may only contain digits\n";
				
				// Append the error message to the TextArea string and then display it to the GUI
				result.setText(resultMsg + "\n" + evenRecogErrorMessage + "\n" + inputLine + 
						"\n" + displayInput(input, currentCharNdx));
				
				// Return from this recognizer with the error message as the returned value
				return evenRecogErrorMessage + "\n" + inputLine + "\n" + 
					displayInput(input, currentCharNdx);
			}
			else 
			{
				// A final state was reach and there are no more input characters, so this is a
				// successful FSM execution
				evenRecogIndexofError = -1;		// Signal that there is no error marker
				evenRecogErrorMessage = "";		// Signal that there is no error message
				
				// Display the trace to the GUI
				result.setText(resultMsg);
				return evenRecogErrorMessage;
			}
			
		default:
			return "";
		}
		
	}
}
