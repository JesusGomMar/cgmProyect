package main.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.mainFunction;
import main.java.dto.QuestionaryDto;
import main.java.dto.QuestionsDto;

/**
 *
 * @author JGM
 */
class mainFunctionTest {

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void testMain() {
		ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
		System.setIn(in);

		mainFunction.main(null);

	}

	@Test
	void madeQuestionTest() {
		ByteArrayInputStream in = new ByteArrayInputStream("a?\"b\"\n".getBytes());
		System.setIn(in);

		QuestionaryDto dto = new QuestionaryDto();
		mainFunction.madeQuestion(dto);
		assertNotNull(dto.getLstQuestions().get(0).getQuestion());
		assertNotNull(dto.getLstQuestions().get(0).getAnswers().get(0));
	}
	
	@Test
	void askQuestionTest() {
		ByteArrayInputStream in = new ByteArrayInputStream("a?\n".getBytes());
		System.setIn(in);

		QuestionaryDto dto = new QuestionaryDto();
		List<QuestionsDto> lstQuestions = new ArrayList<>();
		QuestionsDto qDto = new QuestionsDto();
		qDto.setQuestion("a?");
		List<String> answers = new ArrayList<>();
		answers.add("\"b\"");
		qDto.setAnswers(answers);
		lstQuestions.add(qDto);
		dto.setLstQuestions(lstQuestions );

		mainFunction.askQuestion(dto);
		assertEquals("Write the desired question\r\n" + 
				"Stored answers for the question a?\r\n" + 
				" 	* \"b\"", outputStreamCaptor.toString().trim());
	}
	
	@Test
	void askQuestion2Test() {
		ByteArrayInputStream in = new ByteArrayInputStream("a?\n".getBytes());
		System.setIn(in);

		QuestionaryDto dto = new QuestionaryDto();
		List<QuestionsDto> lstQuestions = new ArrayList<>();
		QuestionsDto qDto = new QuestionsDto();
		qDto.setQuestion("a");
		List<String> answers = new ArrayList<>();
		answers.add("\"b\"");
		qDto.setAnswers(answers);
		lstQuestions.add(qDto);
		dto.setLstQuestions(lstQuestions );

		mainFunction.askQuestion(dto);
		assertNotNull(dto.getLstQuestions().get(0).getQuestion());
		assertNotNull(dto.getLstQuestions().get(0).getAnswers().get(0));
	}

}
