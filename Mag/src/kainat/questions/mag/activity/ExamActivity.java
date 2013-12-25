package kainat.questions.mag.activity;

import java.util.ArrayList;
import java.util.List;

import kainat.questions.mag.R;
import kainat.questions.mag.Helper.ExamHelper;
import kainat.questions.mag.model.Question;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExamActivity extends Activity {
	char[] userAnswers=new char[100];
	List<Question> examQuestionList= new ArrayList<Question>();
	Integer examQuestionIndex=0;
	Integer startEnglish=0;
	Integer startFrench=0;
	Integer startInformatics=0;
	Integer startRussian=0;
	Integer startLogicText=0;
	Integer startLogicImage=0;
	Integer langChoosen=0;
	Integer lightGray=-3355444;
	Integer blue=-16776961;
	Context context=this;
	Button aButton;
	Button bButton;
	Button cButton;
	Button dButton;
	Button eButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		examQuestionList=QuestionsActivity.examQuestionL;
		System.out.println("examsize:"+examQuestionList.size());
		for(int p=0;p<examQuestionList.size();p++){
			userAnswers[p]='n';
			examQuestionList.get(p).setColorA(lightGray);
			examQuestionList.get(p).setColorB(lightGray);
			examQuestionList.get(p).setColorC(lightGray);
			examQuestionList.get(p).setColorD(lightGray);
			examQuestionList.get(p).setColorE(lightGray);
		}
		
		this.displayQuestion(examQuestionList.get(0), 0);	
		startLogicText=ExamHelper.getStartText(0,examQuestionList);
		startInformatics=ExamHelper.getStartInformatics(startLogicText, examQuestionList);
		startEnglish=ExamHelper.getStartEnglish(startInformatics, examQuestionList);
		startRussian=ExamHelper.getStartRussian(startEnglish, examQuestionList);
		startFrench=ExamHelper.getStartFrench(startRussian, examQuestionList);
		
		addListenerOnButtonPrev();
		addListenerOnButtonNext();
		addListenerOnButtonA();
		addListenerOnButtonB();
		addListenerOnButtonC();
		addListenerOnButtonD();
		addListenerOnButtonE();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exam, menu);
		return true;
	}

	public void displayQuestion(Question question, Integer index){
		if(question.getImage()!=null){
			String imageName="image"+question.getImage();
			ImageView img= (ImageView) findViewById(R.id.imageView1);
			int id = getResources().getIdentifier("kainat.questions.mag:drawable/" + imageName, null, null);
			System.out.println("from id: "+id);
			img.setImageResource(id);
		}
		else{
			ImageView img= (ImageView) findViewById(R.id.imageView1);
			img.setImageResource(R.drawable.kainat);
		}

		TextView t=new TextView(this);
		t=(TextView)findViewById(R.id.textView2);
		t.setText("Sual: "+(index+1));
		t=(TextView)findViewById(R.id.textView1);
		t.setText(question.getQuestionText());
		Button aButton=(Button)findViewById(R.id.abutton);
        aButton.setText(question.getChoiceA());
        aButton.setTextSize(10);
        aButton.getBackground().setColorFilter(question.getColorA(),PorterDuff.Mode.MULTIPLY);
        //aButton.setBackgroundColor(question.getColorA());
        
        Button bButton=(Button)findViewById(R.id.bbutton);
        bButton.setText(question.getChoiceB());
        bButton.getBackground().setColorFilter(question.getColorB(),PorterDuff.Mode.MULTIPLY);
        
        Button cButton=(Button)findViewById(R.id.cbutton);
        cButton.setText(question.getChoiceC());
        cButton.getBackground().setColorFilter(question.getColorC(),PorterDuff.Mode.MULTIPLY);
        
        Button dButton=(Button)findViewById(R.id.dbutton);
        dButton.setText(question.getChoiceD());
        dButton.getBackground().setColorFilter(question.getColorD(),PorterDuff.Mode.MULTIPLY);
        
        Button eButton=(Button)findViewById(R.id.ebutton);
        eButton.setText(question.getChoiceE());
        eButton.getBackground().setColorFilter(question.getColorE(),PorterDuff.Mode.MULTIPLY);
	}
	
	public void addListenerOnButtonPrev() {
		 
		Button prevButton = (Button) findViewById(R.id.prevbutton);
 
		prevButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(examQuestionIndex>0){
					examQuestionIndex--;
				}
				else if (examQuestionIndex==0){
					examQuestionIndex=examQuestionList.size()-1;
				}
				displayQuestion(examQuestionList.get(examQuestionIndex),examQuestionIndex);
			}
 
		});
 
	}	
	
	public void addListenerOnButtonNext() {
		 
		Button prevButton = (Button) findViewById(R.id.nextbutton);
 
		prevButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(examQuestionIndex<examQuestionList.size()-1){
					examQuestionIndex++;
				}
				else if (examQuestionIndex==examQuestionList.size()-1){
					examQuestionIndex=0;
				}
				displayQuestion(examQuestionList.get(examQuestionIndex),examQuestionIndex);
			}
 
		});
 
	}	
	
	public void addListenerOnButtonA() {
		 
		aButton = (Button) findViewById(R.id.abutton);
 
		aButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				userAnswers[examQuestionIndex]='a';
				eButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				aButton.getBackground().setColorFilter(blue,PorterDuff.Mode.MULTIPLY);
				bButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				cButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				dButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				examQuestionList.get(examQuestionIndex).setColorA(blue);
				examQuestionList.get(examQuestionIndex).setColorB(lightGray);
				examQuestionList.get(examQuestionIndex).setColorC(lightGray);
				examQuestionList.get(examQuestionIndex).setColorD(lightGray);
				examQuestionList.get(examQuestionIndex).setColorE(lightGray);
			}
 
		});
 
	}	
	public void addListenerOnButtonB() {
		 
		bButton = (Button) findViewById(R.id.bbutton);
 
		bButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				userAnswers[examQuestionIndex]='b';
				eButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				aButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				bButton.getBackground().setColorFilter(blue,PorterDuff.Mode.MULTIPLY);
				cButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				dButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				examQuestionList.get(examQuestionIndex).setColorA(lightGray);
				examQuestionList.get(examQuestionIndex).setColorB(blue);
				examQuestionList.get(examQuestionIndex).setColorC(lightGray);
				examQuestionList.get(examQuestionIndex).setColorD(lightGray);
				examQuestionList.get(examQuestionIndex).setColorE(lightGray);
			}
 
		});
 
	}
	
	public void addListenerOnButtonC() {
		 
		cButton = (Button) findViewById(R.id.cbutton);
 
		cButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				userAnswers[examQuestionIndex]='c';
				cButton.getBackground().setColorFilter(blue,PorterDuff.Mode.MULTIPLY);
				eButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				aButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				bButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				dButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				examQuestionList.get(examQuestionIndex).setColorA(lightGray);
				examQuestionList.get(examQuestionIndex).setColorB(lightGray);
				examQuestionList.get(examQuestionIndex).setColorC(blue);
				examQuestionList.get(examQuestionIndex).setColorD(lightGray);
				examQuestionList.get(examQuestionIndex).setColorE(lightGray);
			}
 
		});
 
	}
	
	public void addListenerOnButtonD() {
		 
		dButton = (Button) findViewById(R.id.dbutton);
 
		dButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				userAnswers[examQuestionIndex]='d';
				dButton.getBackground().setColorFilter(blue,PorterDuff.Mode.MULTIPLY);
				eButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				aButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				bButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				cButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				
				examQuestionList.get(examQuestionIndex).setColorA(lightGray);
				examQuestionList.get(examQuestionIndex).setColorB(lightGray);
				examQuestionList.get(examQuestionIndex).setColorC(lightGray);
				examQuestionList.get(examQuestionIndex).setColorD(blue);
				examQuestionList.get(examQuestionIndex).setColorE(lightGray);
			}
 
		});
 
	}
	
	public void addListenerOnButtonE() {
		 
		eButton = (Button) findViewById(R.id.ebutton);
		
		eButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				userAnswers[examQuestionIndex]='e';
				eButton.getBackground().setColorFilter(blue,PorterDuff.Mode.MULTIPLY);
				aButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				bButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				cButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				dButton.getBackground().setColorFilter(lightGray,PorterDuff.Mode.MULTIPLY);
				examQuestionList.get(examQuestionIndex).setColorA(lightGray);
				examQuestionList.get(examQuestionIndex).setColorB(lightGray);
				examQuestionList.get(examQuestionIndex).setColorC(lightGray);
				examQuestionList.get(examQuestionIndex).setColorD(lightGray);
				examQuestionList.get(examQuestionIndex).setColorE(blue);
			}
 
		});
 
	}
}
