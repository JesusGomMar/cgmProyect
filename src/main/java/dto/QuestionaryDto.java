package main.java.dto;

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

	private boolean flagMain;
	
	private boolean flagMake;
	
	private boolean flagAsk;
	
	
	/**
	 * Constructor
	 */
	public QuestionaryDto() {
		flagMain = true;
		flagMake = true;
		flagAsk = true;
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
	 * @return the flagMain
	 */
	public boolean isFlagMain() {
		return flagMain;
	}

	/**
	 * @param flagMain the flagMain to set
	 */
	public void setFlagMain(boolean flagMain) {
		this.flagMain = flagMain;
	}

	/**
	 * @return the flagMake
	 */
	public boolean isFlagMake() {
		return flagMake;
	}

	/**
	 * @param flagMake the flagMake to set
	 */
	public void setFlagMake(boolean flagMake) {
		this.flagMake = flagMake;
	}

	/**
	 * @return the flagAsk
	 */
	public boolean isFlagAsk() {
		return flagAsk;
	}

	/**
	 * @param flagAsk the flagAsk to set
	 */
	public void setFlagAsk(boolean flagAsk) {
		this.flagAsk = flagAsk;
	}


}
