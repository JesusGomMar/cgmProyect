package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import main.java.Const.Constants;
import main.java.dto.QuestionaryDto;
import main.java.dto.QuestionsDto;

/**
 *
 * @author JGM
 */
public class mainFunction {

	/**
	 * Main function
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		QuestionaryDto dto = new QuestionaryDto();
		// Main loop for main menu
		while (dto.isFlagMain()) {
			if (null != dto.getSelectedOption() && (dto.getSelectedOption().equals(Constants.OPTION_END)
					|| dto.getIterator() >= Constants.MAX_ITERATIONS)) {
				break;
			}
			System.out.println(Constants.INI_MENU_HEAD);
			System.out.println(Constants.INI_MENU_OPT_1);
			System.out.println(Constants.INI_MENU_OPT_2);
			System.out.println(Constants.INI_MENU_OPT_3);
			Scanner in = new Scanner(System.in);
			try {
				Integer num = in.nextInt();
				dto.setSelectedOption(num);
				if (null != num && (num.equals(Constants.OPTION_MAKE_QUESTION)
						|| num.equals(Constants.OPTION_NEW_QUESTION) || num.equals(Constants.OPTION_END))) {
					funcionality(dto);
				} else {
					dto.setIterator(dto.getIterator() + 1);
					dto.setFlagMain(true);
					dto.setSelectedOption(11);
					System.out.println(Constants.ERROR_MENU_OPTION);
				}
			} catch (Exception e) {
				// in case of exception, as for example nullpointer or menu option being a string
				// we add a error iteration and go back to menu
				dto.setIterator(dto.getIterator() + 1);
				dto.setFlagMain(true);
				// we define an option not in the system to bypass end option and avoid nullpointer
				dto.setSelectedOption(11);
				System.out.println(e.getMessage());
				System.out.println(Constants.ERROR_MENU_OPTION);
			}
		}
		// if 9 or more error iterations are made error is shown and the process is ended.
		if (dto.getIterator() >= Constants.MAX_ITERATIONS && !dto.getSelectedOption().equals(Constants.OPTION_END)) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		System.out.println(Constants.END);
	}

	/**
	 * Function that send the user to the selected path
	 * 
	 * @param dto selectedOption with the value
	 */
	public static void funcionality(QuestionaryDto dto) {

		switch (dto.getSelectedOption()) {
		case 1:
			madeQuestion(dto);
			moreQuestions(dto);
			break;
		case 2:
			askQuestion(dto);
			moreAskQuestion(dto);
			break;
		case 3:
			dto.setFlagMain(false);
			// we define an option not in the system to bypass end option and avoid nullpointer
			dto.setIterator(11);
			break;
		default:
			// if an option different than 1,2 or 3 is selected, increment error iterator and show error
			dto.setIterator(dto.getIterator() + 1);
			dto.setFlagMain(true);
			System.out.println(Constants.ERROR_NOT_HANDLED);
			break;
		}

	}

	/**
	 * Function where the user add the questions and answers. we validate that is
	 * not empty
	 * 
	 * @param dto
	 */
	@SuppressWarnings("resource")
	public static void madeQuestion(QuestionaryDto dto) {
		QuestionsDto qDto = new QuestionsDto();
		System.out.println(Constants.MAKE_QUESTION);
		System.out.println(Constants.RULES_MAKE_QUESTION);
		System.out.println(Constants.RULES_MAKE_QUESTION_EXAMPLE);
		Scanner in = new Scanner(System.in);

		String questionAndAnswers = in.nextLine();
		// check that the input is not null or empty removing whitespaces
		if (null != questionAndAnswers && !questionAndAnswers.trim().isEmpty()) {
			// with REGEX_QUESTIONS we get with all tha is left to a question mark, 
			// if no question mark no split is made
			String[] questionVector = questionAndAnswers.split(Constants.REGEX_QUESTIONS);
			// for validations, all question marks are counted
			Long countQuestionTag = questionAndAnswers.chars().filter(ch -> ch == '?').count();
			if (!validateInputNewQuestion(qDto, questionVector, countQuestionTag)) {
				validateInputNewAnswers(dto, qDto, questionAndAnswers);
			}
		} else {
			System.out.println(Constants.ERROR_EMPTY_INPUT);
		}
	}

	/**
	 * Validation of the question, max length 255 char and we validate the correct
	 * structure of the question
	 * 
	 * @param qDto
	 * @param questionVector   vector with the question made at position 0
	 * @param countQuestionTag number of question tags
	 * @return
	 */
	public static boolean validateInputNewQuestion(QuestionsDto qDto, String[] questionVector,
			Long countQuestionTag) {
		// at least one question mark has to be inserted,
		// and after trim the question has to have more than the question mark
		if (countQuestionTag >= 1 && questionVector[0].trim().length() > 1) {
			// trim the question to avoid whitespaces
			questionVector[0] = questionVector[0].trim();
			// validation of question length
			if (questionVector[0].length() > Constants.MAX_CHAR) {
				System.out.println(Constants.ERROR_MAX_SIZE_EXCEDED);
			} else {
				qDto.setQuestion(questionVector[0]);
			}
		} else {
			System.out.println(Constants.ERROR_WRONG_QUESTION_STRUCTURE);
			return true;
		}
		return false;
	}

	/**
	 * Validation of the answers, length and structure
	 * 
	 * @param dto
	 * @param qDto
	 * @param questionAndAnswers
	 */
	public static void validateInputNewAnswers(QuestionaryDto dto, QuestionsDto qDto, String questionAndAnswers) {
		// the input is divided by question mark in two after the fist question mark
		String[] answerVector = questionAndAnswers.split(Constants.QUESTION_MARK_STRING, 2);
		// all quotes are counted for validation pourposes
		Long countQuoteTag = questionAndAnswers.chars().filter(ch -> ch == '\"').count();
		// validation of not null or empty question, also we assure that an even numer or quotes are inserted
		if (null != answerVector && !answerVector[1].trim().isEmpty() && (countQuoteTag % 2) == 0
				&& checkAnswersNoQuotes(answerVector[1])) {
			List<String> lstAnswers = new ArrayList<>();
			// all answers that are between quotes are selected using StringUtils library
			String[] quotedAnswers = StringUtils.substringsBetween(answerVector[1], Constants.SCAPED_QUOTE_STRING,  Constants.SCAPED_QUOTE_STRING);
			for (String answer : quotedAnswers) {
				String trimAnswer = answer.trim();
				if (trimAnswer.isEmpty()) {
					lstAnswers.add(Constants.EMPTY_ANSWER);
				} else if (trimAnswer.length() > Constants.MAX_CHAR) {
					System.out.println(Constants.ERROR_MAX_SIZE_EXCEDED);
				} else {
					lstAnswers.add(trimAnswer);
				}
			}
			qDto.setAnswers(lstAnswers);
			dto.getLstQuestions().add(qDto);
		} else {
			System.out.println(Constants.ERROR_WRONG_ANSWER_STRUCTURE);
		}
	}

	/**
	 * validation that the answers are all in quotes
	 * 
	 * @param answerVector
	 * @return
	 */
	public static boolean checkAnswersNoQuotes(String answerVector) {
		String answerVectorAux = answerVector;
		// with REGEX_ALL_IN_QUOTES we remove all that is not in quotes,
		// after that we check te result is not empty
		return answerVectorAux.replaceAll(Constants.REGEX_ALL_IN_QUOTES, "").trim().isEmpty();
	}

	/**
	 * Menu where we ask if the user want to add more questions or go to main manu
	 * 
	 * @param dto with flags and selected action
	 */
	public static void moreQuestions(QuestionaryDto dto) {
		dto.setIterator(0);
		while (dto.isFlagMake()) {
			if (dto.getIterator() >= Constants.MAX_ITERATIONS) {
				break;
			}
			System.out.println(Constants.MORE_MAKE_QUESTIONS);
			System.out.println(Constants.GO_MENU);
			Scanner in = new Scanner(System.in);
			validateOptionMenuQuestions(dto, in);
		}
		if (dto.getIterator() >= Constants.MAX_ITERATIONS
				&& !dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		dto.setIterator(0);
	}

	/**
	 * Function that validate that the option selected is between the right ones
	 * 
	 * @param dto with flags
	 * @param in  selected option
	 */
	public static void validateOptionMenuQuestions(QuestionaryDto dto, Scanner in) {
		try {
			Integer num = in.nextInt();
			if (null != num
					&& (num.equals(Constants.OPTION_MORE_MAKE_QUESTIONS) || num.equals(Constants.OPTION_GO_MENU))) {
				dto.setSelectedOption(num);
				if (dto.getSelectedOption().equals(Constants.OPTION_MORE_MAKE_QUESTIONS)) {
					dto.setSelectedOption(Constants.OPTION_NEW_QUESTION);
					funcionality(dto);
				} else if (dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
					dto.setIterator(11);
					dto.setFlagMake(false);
					dto.setFlagMain(true);
				} else {
					dto.setIterator(dto.getIterator() + 1);
					dto.setFlagMake(true);
					System.out.println(Constants.ERROR_MENU_OPTION2);
				}
			} else {
				dto.setIterator(dto.getIterator() + 1);
				dto.setFlagMake(true);
				System.out.println(Constants.ERROR_MENU_OPTION2);
			}
		} catch (Exception e) {
			dto.setIterator(dto.getIterator() + 1);
			dto.setFlagMake(true);
			System.out.println(e.getMessage());
			System.out.println(Constants.ERROR_MENU_OPTION2);
		}
	}

	/**
	 * Function that ask por the question and validate that is not empty
	 * 
	 * @param dto
	 */
	@SuppressWarnings("resource")
	public static void askQuestion(QuestionaryDto dto) {
		boolean found = false;
		System.out.println(Constants.INSERT_QUESTION);
		Scanner in = new Scanner(System.in);
		String question = in.nextLine();
		question = question.trim();
		if (null != question && !question.isEmpty()) {
			validateQuestionAsked(dto, found, question);
		} else {
			System.out.println(Constants.ERROR_EMPTY_INPUT);
		}
	}

	/**
	 * Function where we look for a stored question and we print the not found
	 * answer or the right answers
	 * 
	 * @param dto
	 * @param found    initial flag
	 * @param question input string
	 */
	public static void validateQuestionAsked(QuestionaryDto dto, boolean found, String question) {
		if (!dto.getLstQuestions().isEmpty()) {
			System.out.println(Constants.ANSWERS_HEADER + question);
			for (QuestionsDto storedQuestion : dto.getLstQuestions()) {
				if (storedQuestion.getQuestion().equals(question)) {
					found = true;
					for (String answer : storedQuestion.getAnswers()) {
						System.out.println(" \t* " + answer);
					}
				}
			}
			// default answer if no question is found
			if (!found) {
				System.out.println(Constants.QUESTION_NOT_FOUND);
			}
		} else {
			// error if there are no stored question
			System.out.println(Constants.NO_STORED_QUESTIONS);
		}
	}

	/**
	 * Menu that let the user choose between ask more questions or go to main menu
	 * 
	 * @param dto flags neded and selected options
	 */
	@SuppressWarnings("resource")
	public static void moreAskQuestion(QuestionaryDto dto) {
		dto.setIterator(0);
		while (dto.isFlagAsk()) {
			if (dto.getIterator() >= Constants.MAX_ITERATIONS) {
				break;
			}
			System.out.println(Constants.MORE_ASK_QUESTION);
			System.out.println(Constants.GO_MENU);
			Scanner in = new Scanner(System.in);
			try {
				Integer num = in.nextInt();
				validateOptionMenuAsked(dto, num);
			} catch (Exception e) {
				dto.setIterator(dto.getIterator() + 1);
				dto.setFlagAsk(true);
				System.out.println(e.getMessage());
				System.out.println(Constants.ERROR_MENU_OPTION3);
			}
		}
		if (dto.getIterator() >= Constants.MAX_ITERATIONS
				&& !dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
			dto.setFlagMain(true);
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		dto.setIterator(0);

	}

	/**
	 * Function that validate that the selected option is the right one
	 * 
	 * @param dto flags neded
	 * @param num selected option
	 */
	public static void validateOptionMenuAsked(QuestionaryDto dto, Integer num) {
		if (null != num && (num.equals(Constants.OPTION_MORE_ASK_QUESTIONS) || num.equals(Constants.OPTION_GO_MENU))) {
			dto.setSelectedOption(num);
			if (dto.getSelectedOption().equals(Constants.OPTION_MORE_ASK_QUESTIONS)) {
				dto.setSelectedOption(Constants.OPTION_MAKE_QUESTION);
				funcionality(dto);
			} else if (dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
				dto.setIterator(11);
				dto.setFlagAsk(false);
				dto.setFlagMain(true);
			} else {
				dto.setIterator(dto.getIterator() + 1);
				dto.setFlagAsk(true);
				System.out.println(Constants.ERROR_MENU_OPTION3);
			}
		} else {
			dto.setIterator(dto.getIterator() + 1);
			dto.setFlagAsk(true);
			System.out.println(Constants.ERROR_MENU_OPTION3);
		}
	}

}
