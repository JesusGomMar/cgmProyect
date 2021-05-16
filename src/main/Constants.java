
package main;
/**
*
* @author JGM
*/
public class Constants {
	
	public static final String INI_MENU_HEAD = "SELECT ONE OPTION"; 
	public static final String INI_MENU_OPT_1 = "1. ADD QUESTION"; 
	public static final String INI_MENU_OPT_2 = "2. ASK QUESTION";
	public static final String ERROR_MENU_OPTION = "The only options are the numbers 1 or 2";
	public static final String ERROR_ITERATIONS_EXCEDED = "More than 9 wrong selections.";
	public static final int  MAX_ITERATIONS = 9;
	public static final String END = "END"; 
	
	public static final String ERROR_NOT_HANDLED = "Not handled error";
	public static final Integer OPTION_NEW_QUESTION = 1;
	public static final Integer OPTION_ASK_QUESTION = 2;
	public static final String MAKE_QUESTION = "Write your question together with the answers.";
	public static final String RULES_MAKE_QUESTION = "Questions MUST end with '?' char and every answer must be between \"\" ";
	public static final String RULES_MAKE_QUESTION_EXAMPLE = "Example: What is Peters favourite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";

	
	public static  String REGEX_QUESTIONS = "(?<=\\?).*";
	public static  String REGEX_ANSWERS = "(?<=\\?).*";
}
