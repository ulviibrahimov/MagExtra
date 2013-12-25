

package kainat.questions.mag.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * @author ulvi
 *
 */
public class Question implements Parcelable, Serializable{
	
	private String questionText;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String choiceE;
	private String correctAnswer;
	private String questionType;
	private String image;
	private Integer colorA;
	private Integer colorB;
	private Integer colorC;
	private Integer colorD;
	private Integer colorE;
	
	public Question(){
		this.questionText=null;
		this.choiceA=null;
		this.choiceB=null;
		this.choiceC=null;
		this.choiceD=null;
		this.choiceE=null;
		this.correctAnswer=null;
		this.questionType=null;
		this.image=null;
	}
	
	public void setQuestionText(String questionText){
		this.questionText=questionText;
	}
	
	public void setChoiceA(String choiceA){
		this.choiceA=choiceA;
	}
	public void setChoiceB(String choiceB){
		this.choiceB=choiceB;
	}
	public void setChoiceC(String choiceC){
		this.choiceC=choiceC;
	}
	public void setChoiceD(String choiceD){
		this.choiceD=choiceD;
	}
	public void setChoiceE(String choiceE){
		this.choiceE=choiceE;
	}
	public void setCorrectAnswer(String correctAnswer){
		this.correctAnswer=correctAnswer;
	}
	public void setQuestionType(String questionType){
		this.questionType=questionType;
	}
	public void setImage(String image){
		this.image=image;
	}
	public void setColorA(Integer colorA){
		this.colorA=colorA;
	}
	public void setColorB(Integer colorB){
		this.colorB=colorB;
	}
	public void setColorC(Integer colorC){
		this.colorC=colorC;
	}
	public void setColorD(Integer colorD){
		this.colorD=colorD;
	}
	public void setColorE(Integer colorE){
		this.colorE=colorE;
	}

	public Integer getColorA(){
		return this.colorA;
	}
	public Integer getColorB(){
		return this.colorB;
	}
	public Integer getColorC(){
		return this.colorC;
	}
	public Integer getColorD(){
		return this.colorD;
	}
	public Integer getColorE(){
		return this.colorE;
	}
	public String getQuestionText(){
		return this.questionText;
	}
	
	public String getChoiceA(){
		return this.choiceA;
	}
	public String getChoiceB(){
		return this.choiceB;
	}
	public String getChoiceC(){
		return this.choiceC;
	}
	public String getChoiceD(){
		return this.choiceD;
	}
	public String getChoiceE(){
		return this.choiceE;
	}
	public String getCorrectAnswer(){
		return this.correctAnswer;
	}
	public String getQuestionType(){
		return this.questionType;
	}
	public String getImage(){
		return this.image;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
