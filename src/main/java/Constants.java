
package main.java;
/**
*
* @author JGM
*/
public class Constants {
	
	public static final String INI_MENU_HEAD = "SELECT ONE OPTION"; 
	public static final String INI_MENU_OPT_1 = "1. ADD QUESTION"; 
	public static final String INI_MENU_OPT_2 = "2. ASK QUESTION";
	public static final String INI_MENU_OPT_3 = "3. END";
	public static final String ERROR_MENU_OPTION = "The only options are the numbers 1, 2 or 3";
	public static final String ERROR_MENU_OPTION2 = "The only options are the numbers 4 or 5";
	public static final String ERROR_MENU_OPTION3 = "The only options are the numbers 6 or 5";
	public static final String ERROR_ITERATIONS_EXCEDED = "More than 9 wrong selections.";
	public static final int  MAX_ITERATIONS = 9;
	public static final String END = "END"; 
	
	public static final String ERROR_NOT_HANDLED = "Not handled error";
	public static final Integer OPTION_NEW_QUESTION = 1;
	public static final Integer OPTION_MAKE_QUESTION = 2;
	public static final Integer OPTION_END = 3;
	public static final Integer OPTION_MORE_MAKE_QUESTIONS = 4;
	public static final Integer OPTION_GO_MENU = 5;
	public static final Integer OPTION_MORE_ASK_QUESTIONS = 6;
	public static final Integer MAX_CHAR = 250;
	public static final String MAKE_QUESTION = "Write your question together with the answers.";
	public static final String RULES_MAKE_QUESTION = "Questions MUST end with '?' char and every answer must be between \"\" ";
	public static final String RULES_MAKE_QUESTION_EXAMPLE = "Example: What is Peters favourite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
	public static final String ERROR_MAX_SIZE_EXCEDED = "Max size is 250 characters.";
	public static final String ERROR_WRONG_QUESTION_STRUCTURE = "No question has been made or no question mark has been found.";
	public static final String ERROR_WRONG_ANSWER_STRUCTURE = "No answer has been made after the question mark or they are not between quotes.";
	public static final String ERROR_EMPTY_INPUT = "Empty input";
	public static final String MORE_MAKE_QUESTIONS = "4. MORE QUESTIONS";
	public static final String GO_MENU = "5. GO MAIN MENU";
	public static final String MORE_ASK_QUESTION = "6. ANOTHER QUESTION TO ASK";

	
	public static final String REGEX_QUESTIONS = "(?<=\\?).*";
	public static final String REGEX_ANSWERS = "(?<=\\?).*";
	public static final String REGEX_ALL_IN_QUOTES = "(\".*?\"|$)";
	public static final String INSERT_QUESTION = "Write the desired question";
	public static final String QUESTION_NOT_FOUND = "\\t * The answer to life, universe and everything is 42";
	public static final String NO_STORED_QUESTIONS = "There are no stored questions, please add some before try guessing.";
	public static final String ANSWERS_HEADER = "Stored answers for the question ";
	public static final String EMPTY_ANSWER = "<Empty answer>";
	
	public Constants() {
		
	}
}
