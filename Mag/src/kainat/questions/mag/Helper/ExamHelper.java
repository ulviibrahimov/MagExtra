package kainat.questions.mag.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kainat.questions.mag.model.Question;
import android.os.CountDownTimer;
import android.widget.TextView;

public class ExamHelper {
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static void addQuestions(List<Question> originalList,List<Question> examList,Integer start,Integer end,Integer limit){
		 List<Integer> questionIndexes= new ArrayList<Integer>();
		 Integer count=0;
		 while(true){
         	Integer temp = randInt(start, end-1);
         	if(questionIndexes.contains(temp)){
         		;
         	}
         	else{
         		questionIndexes.add(temp);
         		examList.add(originalList.get(temp));
         		count++;
         	}
         	if(count==limit)
         		break;
         } 
	}
	
	public static Integer getStartInformatics(Integer startIndex,List<Question> originalList){
		Integer start=0;
		for (int i =startIndex;i<originalList.size();i++){
        	if(originalList.get(i).getQuestionType().equals("informatics")){
        		start=i;
        		break;
        	}
        }
		return start;
	}
	
	public static Integer getStartEnglish(Integer startIndex,List<Question> originalList){
		Integer start=0;
		for (int i =startIndex;i<originalList.size();i++){
        	if(originalList.get(i).getQuestionType().equals("english")){
        		start=i;
        		break;
        	}
        }
		return start;
	}
	
	public static Integer getStartRussian(Integer startIndex,List<Question> originalList){
		Integer start=0;
		for (int i =startIndex;i<originalList.size();i++){
        	if(originalList.get(i).getQuestionType().equals("russian")){
        		start=i;
        		break;
        	}
        }
		return start;
	}
	
	public static Integer getStartFrench(Integer StartIndex,List<Question> originalList){
		Integer start=0;
		for (int i =0;i<originalList.size();i++){
        	if(originalList.get(i).getQuestionType().equals("french")){
        		start=i;
        		break;
        	}
        }
		return start;
	}
	
	public static Integer getStartText(Integer StartIndex,List<Question> originalList){
		Integer start=0;
		for (int i =0;i<originalList.size();i++){
        	if(originalList.get(i).getImage()==null){
        		start=i;
        		break;
        	}
        }
		return start;
	}
	
	public static void newTimer(final TextView tv){
		new CountDownTimer(9000000, 10000) {
			 
		     public void onTick(long millisUntilFinished) {
		    	 long minutes= millisUntilFinished / 60000;
		    	 int showHours=(int) (minutes/60);
		    	 int showMinutes= (int) (minutes-60*showHours+1);
		    	 if(showHours>0){
		    		 
		    		 tv.setText("Qalan vaxt:  " + showHours+"saat "+showMinutes+"dəqiqə\n");
		    	 }
		    	 else{
		    		 tv.setText("Qalan vaxt: "+showMinutes+"dəqiqə\n");
		    	 }
		     }

		     public void onFinish() {
		         tv.setText("done!");
		     }
		  }.start();
		
	}
	
}
