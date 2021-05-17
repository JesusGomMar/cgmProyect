package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
						&& (num.equals(Constants.OPTION_ASK_QUESTION) || num.equals(Constants.OPTION_NEW_QUESTION) 
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
		if (dto.getIterator() >= Constants.MAX_ITERATIONS && !dto.getSelectedOption().equals(Constants.OPTION_END)) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		System.out.println(Constants.END);
	}

	private static void funcionality(QuestionaryDto dto) {

		switch (dto.getSelectedOption()) {
		case 1:
//			answerQuestion(dto);
			break;
		case 2:
			askQuestion(dto);
			moreQuestions(dto);
			break;
		case 3:
			dto.setFlag(false);
			dto.setIterator(11);
			break;
		default:
			dto.setIterator(dto.getIterator()+1);
			dto.setFlag(true);
			System.out.println(Constants.ERROR_NOT_HANDLED);
			break;
		}

	}

	@SuppressWarnings("resource")
	private static void moreQuestions(QuestionaryDto dto) {
		dto.setIterator(0);
		boolean flag2 = true;
		while (flag2 || dto.getIterator() < Constants.MAX_ITERATIONS) {

			System.out.println(Constants.MORE_QUESTIONS);
			System.out.println(Constants.GO_MENU);
			Scanner in = new Scanner(System.in);
			Integer num = in.nextInt();
			if (null != num && (num.equals(Constants.OPTION_MORE_QUESTIONS) || num.equals(Constants.OPTION_GO_MENU))) {
				dto.setSelectedOption(num);
				if (dto.getSelectedOption().equals(Constants.OPTION_MORE_QUESTIONS)) {
					dto.setSelectedOption(Constants.OPTION_ASK_QUESTION);
					funcionality(dto);
				} else if (dto.getSelectedOption().equals(Constants.OPTION_GO_MENU)) {
					flag2 = false;
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
		if (dto.getIterator() >= Constants.MAX_ITERATIONS) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		dto.setIterator(0);
	}

	@SuppressWarnings("resource")
	private static void askQuestion(QuestionaryDto dto) {
		QuestionsDto qDto = new QuestionsDto();
		System.out.println(Constants.MAKE_QUESTION);
		System.out.println(Constants.RULES_MAKE_QUESTION);
		System.out.println(Constants.RULES_MAKE_QUESTION_EXAMPLE);
		Scanner in = new Scanner(System.in);

		String questionAndAnswers = in.nextLine();

		if (null != questionAndAnswers && !questionAndAnswers.isEmpty()) {
			String[] questionVector = questionAndAnswers.split(Constants.REGEX_QUESTIONS);
			String[] answerVector = questionAndAnswers.split("\"");
			if (null != questionVector && questionVector.length == 1 && !questionVector[0].isEmpty()) {
				for (int i = 0; i < questionVector.length; i++) {
					questionVector[i] = questionVector[i].trim();
				}
				if (questionVector[0].length() > Constants.MAX_CHAR) {
					System.out.println(Constants.ERROR_MAX_SIZE_EXCEDED);
				} else {
					qDto.setQuestion(questionVector[0]);
				}
			} else {
				System.out.println(Constants.ERROR_WRONG_QUESTION_STRUCTURE);
			}
			if (null != answerVector && answerVector.length >= 2) {
				List<String> answers = new ArrayList<>();
				for (int j = 1; j < answerVector.length; j++) {
					String trimAnswer = answerVector[j].trim();
					if (trimAnswer.isEmpty() || trimAnswer.length() > Constants.MAX_CHAR) {
						System.out.println(Constants.ERROR_MAX_SIZE_EXCEDED);
					} else {
						answers.add(trimAnswer);
					}
				}
				qDto.setAnswers(answers);
				dto.getLstQuestions().add(qDto);
			} else {
				System.out.println(Constants.ERROR_WRONG_ANSWER_STRUCTURE);
			}
		} else {
			System.out.println(Constants.ERROR_EMPTY_INPUT);
		}
	}

}
