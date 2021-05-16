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
		boolean flag = true;
		int iterator = 0;
		QuestionaryDto dto = new QuestionaryDto();
		while (flag || iterator > Constants.MAX_ITERATIONS) {
			Scanner in = new Scanner(System.in);
			System.out.println(Constants.INI_MENU_HEAD);
			System.out.println(Constants.INI_MENU_OPT_1);
			System.out.println(Constants.INI_MENU_OPT_2);
			try {
				Integer num = in.nextInt();
				if (null != num
						&& (num.equals(Constants.OPTION_ASK_QUESTION) || num.equals(Constants.OPTION_NEW_QUESTION))) {
					dto.setSelectedOption(num);
					funcionality(dto);
					flag = false;
				} else {
					iterator++;
					System.out.println(Constants.ERROR_MENU_OPTION);
				}
			} catch (Exception e) {
				iterator++;
				System.out.println(Constants.ERROR_MENU_OPTION);
			}
		}
		if (iterator > Constants.MAX_ITERATIONS) {
			System.out.println(Constants.ERROR_ITERATIONS_EXCEDED);
		}
		System.out.println(Constants.END);
	}

	private static void funcionality(QuestionaryDto dto) {
		
		switch (dto.getSelectedOption()) {
		case 1 :
//			answerQuestion(dto);

			break;
		case 2 :
			askQuestion(dto);

			break;	
		default:
			System.out.println(Constants.ERROR_NOT_HANDLED);
			break;
		}

	}

	private static void askQuestion(QuestionaryDto dto) {
		QuestionsDto qDto = new QuestionsDto();
		System.out.println(Constants.MAKE_QUESTION);
		System.out.println(Constants.RULES_MAKE_QUESTION);
		System.out.println(Constants.RULES_MAKE_QUESTION_EXAMPLE);
		Scanner in = new Scanner(System.in);
		
		String questionAndAnswers = in.nextLine();
		
		if (null != questionAndAnswers && !questionAndAnswers.isEmpty()) {
			String [] questionVector = questionAndAnswers.split(Constants.REGEX_QUESTIONS);
			String [] answerVector =  questionAndAnswers.split("\"");
			if (null != questionVector && questionVector.length>1 && !questionVector[0].isEmpty()) {
				for (int i = 0; i < questionVector.length; i++) {
					questionVector[i] = questionVector[i].trim();
				}
				// TODO validar 250 char en las preguntas
				qDto.setQuestion(questionVector[0]);
			} else {
				System.out.println(Constants.ERROR_WRONG_QUESTION_STRUCTURE);
			}
			
			if (null != answerVector && answerVector.length > 2) {
				List<String> answers = new ArrayList<>();
				for (int j = 0; j < answerVector.length; j++) {
					answers.add(answerVector[j].trim()) ;
				}
				qDto.setAnswers(answers);
				// TODO validar 250 char en las respuestas
			} else {
				System.out.println(Constants.ERROR_WRONG_ANSWER_STRUCTURE);
			}
		} else {
			System.out.println(Constants.ERROR_EMPTY_INPUT);
		}
		
		


		
		System.out.println("questionVector: "+questionVector[0]);
		System.out.println("lenth: "+questionAndAnswers.length());
		System.out.println("questionAndAnswers: "+questionAndAnswers);
	}

}
