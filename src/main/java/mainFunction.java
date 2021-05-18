package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


/**
 *
 * @author JGM
 */
public class mainFunction {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		QuestionaryDto dto = new QuestionaryDto();
		while (dto.isFlag() || (!dto.getSelectedOption().equals(Constants.OPTION_END) && dto.getIterator() < Constants.MAX_ITERATIONS)) {
			Scanner in = new Scanner(System.in);
			System.out.println(Constants.INI_MENU_HEAD);
			System.out.println(Constants.INI_MENU_OPT_1);
			System.out.println(Constants.INI_MENU_OPT_2);
			System.out.println(Constants.INI_MENU_OPT_3);
			try {
				Integer num = in.nextInt();
				dto.setSelectedOption(num);
				if (null != num
						&& (num.equals(Constants.OPTION_MAKE_QUESTION) || num.equals(Constants.OPTION_NEW_QUESTION) 
								||num.equals(Constants.OPTION_END))) {
					funcionality(dto);
				} else {
					dto.setIterator(dto.getIterator()+1);
					dto.setFlag(false);
					System.out.println(Constants.ERROR_MENU_OPTION);
				}
			} catch (Exception e) {
				dto.setIterator(dto.getIterator()+1);
				dto.setFlag(false);
				System.out.println(e.getMessage());
				System.out.println(Constants.ERROR_MENU_OPTION);
			}
		}
		if (dto.getIterator() >= Constants.MAX_ITERATIONS 
				&& !dto.getSelectedOption().equals(Constants.OPTION_END)) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		System.out.println(Constants.END);
	}

	private static void funcionality(QuestionaryDto dto) {

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
			dto.setFlag(false);
			dto.setIterator(11);
			break;
		default:
			dto.setIterator(dto.getIterator()+1);
			dto.setFlag(true);
			System.out.println("dto.getSelectedOption() " + dto.getSelectedOption());
			System.out.println(Constants.ERROR_NOT_HANDLED);
			break;
		}

	}

	@SuppressWarnings("resource")
	private static void moreAskQuestion(QuestionaryDto dto) {
		dto.setIterator(0);
		boolean flag3 = true;
		while (flag3|| dto.getIterator() < Constants.MAX_ITERATIONS) {

			System.out.println(Constants.MORE_ASK_QUESTION);
			System.out.println(Constants.GO_MENU);
			Scanner in = new Scanner(System.in);
			Integer num = in.nextInt();
			if (null != num && (num.equals(Constants.OPTION_MORE_ASK_QUESTIONS) || num.equals(Constants.OPTION_GO_MENU))) {
				dto.setSelectedOption(num);
				if (dto.getSelectedOption().equals(Constants.OPTION_MORE_ASK_QUESTIONS)) {
					dto.setSelectedOption(Constants.OPTION_MORE_ASK_QUESTIONS);
					funcionality(dto);
				} else if (dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
					flag3 = false;
					dto.setIterator(11);
					dto.setFlag(true);
				} else {
					dto.setIterator(dto.getIterator() + 1);
					flag3 = false;
					System.out.println(Constants.ERROR_MENU_OPTION3);
				}
			} else {
				dto.setIterator(dto.getIterator() + 1);
				flag3 = false;
				System.out.println(Constants.ERROR_MENU_OPTION3);
			}
		}
		if (dto.getIterator() >= Constants.MAX_ITERATIONS
				&& !dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		dto.setIterator(0);
		
	}

	@SuppressWarnings("resource")
	private static void askQuestion(QuestionaryDto dto) {
		boolean found = false;
		System.out.println(Constants.INSERT_QUESTION);
		Scanner in = new Scanner(System.in);
		String question = in.nextLine();
		question = question.trim();
		if (null != question && !question.isEmpty()) {
			if (!dto.getLstQuestions().isEmpty()) {
				System.out.println(Constants.ANSWERS_HEADER+question);
				for(QuestionsDto storedQuestion : dto.getLstQuestions()) {
					if (storedQuestion.getQuestion().equals(question)) {
						found = true;
						for(String answer : storedQuestion.getAnswers()) {
							System.out.println(" \t* "+answer);
						}
					}
				}
				if (!found) {
					System.out.println(Constants.QUESTION_NOT_FOUND);
				}
			} else {
				System.out.println(Constants.NO_STORED_QUESTIONS);
			}

		} else {
			System.out.println(Constants.ERROR_EMPTY_INPUT);
		}
	}

	@SuppressWarnings("resource")
	private static void moreQuestions(QuestionaryDto dto) {
		dto.setIterator(0);
		boolean flag2 = true;
		while (flag2 || dto.getIterator() < Constants.MAX_ITERATIONS) {

			System.out.println(Constants.MORE_MAKE_QUESTIONS);
			System.out.println(Constants.GO_MENU);
			Scanner in = new Scanner(System.in);
			Integer num = in.nextInt();
			if (null != num && (num.equals(Constants.OPTION_MORE_MAKE_QUESTIONS) || num.equals(Constants.OPTION_GO_MENU))) {
				dto.setSelectedOption(num);
				if (dto.getSelectedOption().equals(Constants.OPTION_MORE_MAKE_QUESTIONS)) {
					dto.setSelectedOption(Constants.OPTION_NEW_QUESTION);
					funcionality(dto);
				} else if (dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
					flag2 = false;
					dto.setIterator(11);
					dto.setFlag(true);
				} else {
					dto.setIterator(dto.getIterator() + 1);
					flag2 = false;
					System.out.println(Constants.ERROR_MENU_OPTION2);
				}
			} else {
				dto.setIterator(dto.getIterator() + 1);
				flag2 = false;
				System.out.println(Constants.ERROR_MENU_OPTION2);
			}
		}
		if (dto.getIterator() >= Constants.MAX_ITERATIONS
				&& !dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		dto.setIterator(0);
	}

	@SuppressWarnings("resource")
	private static void madeQuestion(QuestionaryDto dto) {
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
			if (!flagErrorQ) {
				String[] answerVector = questionAndAnswers.split("\\?",2);
				Long countQuoteTag = questionAndAnswers.chars().filter(ch -> ch == '\"').count();
				
				if (null != answerVector && !answerVector[1].trim().isEmpty() && (countQuoteTag % 2) == 0
						&& checkAnswersNoQuotes(answerVector[1])) {
					List<String> lstAnswers = new ArrayList<>();
					String [] quotedAnswers = StringUtils.substringsBetween(answerVector[1], "\"", "\"");
					for(String answer : quotedAnswers ) {
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
		} else {
			System.out.println(Constants.ERROR_EMPTY_INPUT);
		}
	}



	private static boolean checkAnswersNoQuotes(String answerVector) {
		String answerVectorAux = answerVector;
		return answerVectorAux.replaceAll(Constants.REGEX_ALL_IN_QUOTES, "").trim().isEmpty();
	}

}
