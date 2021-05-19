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
		while (dto.isFlagMain()) {
			if (null != dto.getSelectedOption() && (dto.getSelectedOption().equals(Constants.OPTION_END)
					|| dto.getIterator() >= Constants.MAX_ITERATIONS)) {
				break;
			}
			Scanner in = new Scanner(System.in);
			System.out.println(Constants.INI_MENU_HEAD);
			System.out.println(Constants.INI_MENU_OPT_1);
			System.out.println(Constants.INI_MENU_OPT_2);
			System.out.println(Constants.INI_MENU_OPT_3);
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
				dto.setIterator(dto.getIterator() + 1);
				dto.setFlagMain(true);
				dto.setSelectedOption(11);
				System.out.println(e.getMessage());
				System.out.println(Constants.ERROR_MENU_OPTION);
			}
		}
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
			dto.setIterator(11);
			break;
		default:
			dto.setIterator(dto.getIterator() + 1);
			dto.setFlagMain(true);
			System.out.println(Constants.ERROR_NOT_HANDLED);
			break;
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
			if (!found) {
				System.out.println(Constants.QUESTION_NOT_FOUND);
			}
		} else {
			System.out.println(Constants.NO_STORED_QUESTIONS);
		}
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

		if (null != questionAndAnswers && !questionAndAnswers.trim().isEmpty()) {
			boolean flagErrorQ = false;
			String[] questionVector = questionAndAnswers.split(Constants.REGEX_QUESTIONS);
			Long countQuestionTag = questionAndAnswers.chars().filter(ch -> ch == '?').count();
			flagErrorQ = validateInputNewQuestion(qDto, flagErrorQ, questionVector, countQuestionTag);
			if (!flagErrorQ) {
				validateInputNewAnswers(dto, qDto, questionAndAnswers);
			}
		} else {
			System.out.println(Constants.ERROR_EMPTY_INPUT);
		}
	}

	/**
	 * Validation of the answers, length and structure
	 * 
	 * @param dto
	 * @param qDto
	 * @param questionAndAnswers
	 */
	public static void validateInputNewAnswers(QuestionaryDto dto, QuestionsDto qDto, String questionAndAnswers) {
		String[] answerVector = questionAndAnswers.split("\\?", 2);
		Long countQuoteTag = questionAndAnswers.chars().filter(ch -> ch == '\"').count();

		if (null != answerVector && !answerVector[1].trim().isEmpty() && (countQuoteTag % 2) == 0
				&& checkAnswersNoQuotes(answerVector[1])) {
			List<String> lstAnswers = new ArrayList<>();
			String[] quotedAnswers = StringUtils.substringsBetween(answerVector[1], "\"", "\"");
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
	 * Validation of the question, max length 255 char and we validate the correct
	 * structure of the question
	 * 
	 * @param qDto
	 * @param flagErrorQ
	 * @param questionVector   vector with the question made at position 0
	 * @param countQuestionTag number of question tags
	 * @return
	 */
	public static boolean validateInputNewQuestion(QuestionsDto qDto, boolean flagErrorQ, String[] questionVector,
			Long countQuestionTag) {
		if (countQuestionTag >= 1 && questionVector[0].trim().length() > 1) {
			for (int i = 0; i < questionVector.length; i++) {
				questionVector[i] = questionVector[i].trim();
			}
			if (questionVector[0].length() > Constants.MAX_CHAR) {
				System.out.println(Constants.ERROR_MAX_SIZE_EXCEDED);
			} else {
				qDto.setQuestion(questionVector[0]);
			}
		} else {
			flagErrorQ = true;
			System.out.println(Constants.ERROR_WRONG_QUESTION_STRUCTURE);
		}
		return flagErrorQ;
	}

	/**
	 * validation that the answers are all in quotes
	 * 
	 * @param answerVector
	 * @return
	 */
	public static boolean checkAnswersNoQuotes(String answerVector) {
		String answerVectorAux = answerVector;
		return answerVectorAux.replaceAll(Constants.REGEX_ALL_IN_QUOTES, "").trim().isEmpty();
	}

}
