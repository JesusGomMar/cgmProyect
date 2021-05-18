package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JGM
 */
public class QuestionaryDto {

	private Integer selectedOption;

	private List<QuestionsDto> lstQuestions;
	
	private Integer iterator;

	private boolean flag;
	
	
	
	/**
	 * Constructor
	 */
	public QuestionaryDto() {
		flag = true;
		iterator = 0;
		lstQuestions = new ArrayList<>();
	}

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

	/**
	 * @return the iterator
	 */
	public Integer getIterator() {
		return iterator;
	}

	/**
	 * @param iterator the iterator to set
	 */
	public void setIterator(Integer iterator) {
		this.iterator = iterator;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
