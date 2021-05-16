package main;

import java.util.List;

/**
 *
 * @author JGM
 */
public class QuestionaryDto {

	private Integer selectedOption;

	private List<QuestionsDto> lstQuestions;

	/**
	 * @return the selectedOption
	 */
	public Integer getSelectedOption() {
		return selectedOption;
	}

	/**
	 * @param selectedOption the selectedOption to set
	 */
	public void setSelectedOption(Integer selectedOption) {
		this.selectedOption = selectedOption;
	}

	/**
	 * @return the lstQuestions
	 */
	public List<QuestionsDto> getLstQuestions() {
		return lstQuestions;
	}

	/**
	 * @param lstQuestions the lstQuestions to set
	 */
	public void setLstQuestions(List<QuestionsDto> lstQuestions) {
		this.lstQuestions = lstQuestions;
	}
}
