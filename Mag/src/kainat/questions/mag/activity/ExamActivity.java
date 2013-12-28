package kainat.questions.mag.activity;

import java.util.ArrayList;
import java.util.List;

import kainat.questions.mag.R;
import kainat.questions.mag.Helper.ExamHelper;
import kainat.questions.mag.model.Question;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExamActivity extends Activity {
	public static List<String> userAnswers=new ArrayList<String>();
	public static List<Question> examQuestionList;
	public static Integer examQuestionIndex;
	public static Integer startEnglish;
	public static Integer startFrench;
	public static Integer startInformatics;
	public static Integer startRussian;
	public static Integer startLogicText;
	public static Integer startLogicImage;
	public static Integer langChoosen;
	public static Integer startLang;
	public static Integer lightGray=-3355444;
	public static Integer blue=-16776961;
	Context context=this;
	Button aButton;
	Button bButton;
	Button cButton;
	Button dButton;		
	Button eButton;
	TextView t;
	TextView timer;
	public static boolean examFirstRun=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exam);
		Window w= getWindow();
		w.setTitle("Sınaq");
		
		

		addListenerOnButtonPrev();
		addListenerOnButtonNext();
		addListenerOnButtonA();
		addListenerOnButtonB();
		addListenerOnButtonC();
		addListenerOnButtonD();
		addListenerOnButtonE();
	}
	@Override
	protected void onResume()
	{
	   super.onResume();
	   if(examFirstRun){
		    langChoosen=0;
			startLogicText=0;
			startInformatics=0;
			startEnglish=0;
			startRussian=0;
			startFrench=0;
			examQuestionIndex=0;
			examQuestionList= new ArrayList<Question>();

           ExamHelper.addQuestions(QuestionsActivity.questionL, examQuestionList, 0, QuestionsActivity.startLogicText, 15);			//image questions
           ExamHelper.addQuestions(QuestionsActivity.questionL, examQuestionList, QuestionsActivity.startLogicText, QuestionsActivity.startInformatics, 35); //text logic
           ExamHelper.addQuestions(QuestionsActivity.questionL, examQuestionList, QuestionsActivity.startInformatics, QuestionsActivity.startEnglish,25);//informatics
           System.out.println("size"+examQuestionList);
    	   for(int p=0;p<examQuestionList.size();p++){
    				userAnswers.add(null);
    				examQuestionList.get(p).setColorA(lightGray);
    				examQuestionList.get(p).setColorB(lightGray);
    				examQuestionList.get(p).setColorC(lightGray);
    				examQuestionList.get(p).setColorD(lightGray);
    				examQuestionList.get(p).setColorE(lightGray);
    			}
		AlertDialog.Builder langAlert;
	    final CharSequence[] languages = {"Ingilis dili","Rus dili","Fransız dili"};
	    langAlert = new AlertDialog.Builder(context);
	    langAlert.setCancelable(false);
	    langAlert.setTitle("Xarici dil seçin");
	    langAlert.setSingleChoiceItems(languages,-1, new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick(DialogInterface dialog, int which) 
	        {		//System.out.println("examsize:"+examQuestionList.size());
	            if(languages[which]=="Ingilis dili")
	            {
	            	langChoosen=1;
	            	
	            	}
	            else if (languages[which]=="Rus dili")
	            {	langChoosen=2;
	            	
	            }
	            else if (languages[which]=="Fransız dili")
	            {
	            	langChoosen=3;
	            	
	            }
	        }
	    });		
	    langAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    		if(langChoosen==0){
	    			ExamHelper.addQuestions(QuestionsActivity.questionL, examQuestionList, QuestionsActivity.startEnglish, QuestionsActivity.startRussian,25);    //english
	    		}
	    		if(langChoosen==1){
	    			ExamHelper.addQuestions(QuestionsActivity.questionL, examQuestionList, QuestionsActivity.startEnglish, QuestionsActivity.startRussian,25);    //english
	    		}	               
	    		else if(langChoosen==2){
	    			ExamHelper.addQuestions(QuestionsActivity.questionL, examQuestionList, QuestionsActivity.startRussian, QuestionsActivity.startFrench,25);	  //russian
	    		}
	    		else if(langChoosen==3){
	    			ExamHelper.addQuestions(QuestionsActivity.questionL, examQuestionList, QuestionsActivity.startFrench, QuestionsActivity.questionL.size(),25); //french
	    		}
	    	
	    		System.out.println("eqlist"+examQuestionList.size());
	    		for(int p=0;p<examQuestionList.size();p++){
					userAnswers.add(null);
					examQuestionList.get(p).setColorA(lightGray);
					examQuestionList.get(p).setColorB(lightGray);
					examQuestionList.get(p).setColorC(lightGray);
					examQuestionList.get(p).setColorD(lightGray);
					examQuestionList.get(p).setColorE(lightGray);
				}
	    		System.out.println("size"+examQuestionList.size());
			    startLogicText=ExamHelper.getStartText(0,examQuestionList);
				startInformatics=ExamHelper.getStartInformatics(startLogicText, examQuestionList);
				startEnglish=ExamHelper.getStartEnglish(startInformatics, examQuestionList);
				startRussian=ExamHelper.getStartRussian(startEnglish, examQuestionList);
				startFrench=ExamHelper.getStartFrench(startRussian, examQuestionList);
				if(startEnglish>0)
					startLang=startEnglish;
				else if(startRussian>0)
					startLang=startRussian;
				else if(startFrench>0)
					startLang=startFrench;
	    	}
	    	});	
	    langAlert.show();
	    timer=new TextView(this);
		timer=(TextView)findViewById(R.id.timerView);
		new CountDownTimer(9000000, 10000) {
			 
		     public void onTick(long millisUntilFinished) {
		    	 long minutes= millisUntilFinished / 60000;
		    	 timer.setText("Vaxt: " +(minutes+1)+" dəq");
		    	 
		     }

		     public void onFinish() {
		         showResult();
		     }
		  }.start();
      		
			examFirstRun=false;
		}
	   System.out.println("examindex: "+examQuestionIndex);
	   System.out.println("examsize: "+examQuestionList.size());
	   this.displayQuestion(examQuestionList.get(examQuestionIndex), examQuestionIndex);
	   
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exam_questions_menu, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder exitAlert = new AlertDialog.Builder(this);

    	exitAlert.setTitle("Çıxış");
    	exitAlert.setMessage("Sınağı tərk edəcəyiniz halda nəticəniz hesablanmayacaq! Çıxmaq istədiyinizdən əminsinizmi?");
    	exitAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    		examFirstRun=true;
    		ExamActivity.this.finish();
            //startActivity(intent);
    	  }
    	});

    	exitAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    	  }
    	});

    	exitAlert.show();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.itemrussian:
	        	examQuestionIndex=startRussian;
	            displayQuestion(examQuestionList.get(examQuestionIndex),examQuestionIndex);
	            return true;
	        case R.id.iteminformatics:
	        	examQuestionIndex=startInformatics;
	            displayQuestion(examQuestionList.get(examQuestionIndex),examQuestionIndex);
	            return true;
	        case R.id.itemlang:
	        	examQuestionIndex=startLang;
	        	displayQuestion(examQuestionList.get(examQuestionIndex),examQuestionIndex);
	            return true;
	        case R.id.itemlogic:
	            examQuestionIndex=0;
	            displayQuestion(examQuestionList.get(examQuestionIndex),examQuestionIndex);
	            return true;
	        case R.id.choosequestion:
	        	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	        	alert.setTitle("Sual seç");
	        	alert.setMessage("Sualın nömrəsini daxil edin : ( 1 - "+examQuestionList.size()+" )");

	        	// Set an EditText view to get user input 
	        	final EditText input = new EditText(this);
	        	alert.setView(input);

	        	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        	  Editable value = input.getText();
	        	  String questionNumber=value.toString();
	        	  try{
	        		  
	        		  int temp=Integer.parseInt(questionNumber)-1;
	        		  if(temp>=examQuestionList.size() || temp<0){
	        			  Toast.makeText(context, "( 1 - "+examQuestionList.size()+" ) intervalında ədəd daxil edin!", 1).show();
	        		  }
	        		  
	        		  else{
	        			  examQuestionIndex=Integer.parseInt(questionNumber)-1;
	        			  displayQuestion(examQuestionList.get(examQuestionIndex),examQuestionIndex);
	        		  }
	        		  }catch(NumberFormatException e){
	        			  Toast.makeText(context, "( 1 - "+examQuestionList.size()+" ) intervalında ədəd daxil edin!", 1).show();
	        		  }
	        	  }
	        	});

	        	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        	  public void onClick(DialogInterface dialog, int whichButton) {
	        	    // Canceled.
	        	  }
	        	});

	        	alert.show();
	        	return true;
	        case R.id.exit:
	        	AlertDialog.Builder exitAlert = new AlertDialog.Builder(this);

	        	exitAlert.setTitle("Çıxış");
	        	exitAlert.setCancelable(false);
	        	exitAlert.setMessage("Sınağı tərk edəcəyiniz halda nəticəniz hesablanmayacaq! Çıxmaq istədiyinizdən əminsinizmi?");
	        	exitAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        		examFirstRun=true;
	        		ExamActivity.this.finish();
		            //startActivity(intent);
	        	  }
	        	});

	        	exitAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        	  public void onClick(DialogInterface dialog, int whichButton) {
	        	    // Canceled.
	        	  }
	        	});

	        	exitAlert.show();
	        	return true;
	        case R.id.result:
	        	showResult();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	public void displayQuestion(Question question, final Integer index){
		if(question.getImage()!=null){
			String imageName="image"+question.getImage();
			ImageView img= (ImageView) findViewById(R.id.imageView1);
			final int id = getResources().getIdentifier("kainat.questions.mag:drawable/" + imageName, null, null);
			System.out.println("from id: "+id);
			img.setImageResource(id);
			img.setClickable(true);
			img.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v) {
			        Intent imageIntent = new Intent(ExamActivity.this,ImageActivity.class);
			        imageIntent.putExtra("imageId", id);
			        startActivity(imageIntent);
			    }
			});
		}
		else{
			ImageView img= (ImageView) findViewById(R.id.imageView1);
			img.setImageResource(R.drawable.kainat);
			img.setClickable(false);
		}
		t=new TextView(this);
		
		
		t=(TextView)findViewById(R.id.questionView);
		t.setText("Sual: "+(index+1));

		TextView x=new TextView(this);
		x=(TextView)findViewById(R.id.textView1);
		x.setText(question.getQuestionText());
		Button aButton=(Button)findViewById(R.id.abutton);
        aButton.getBackground().setColorFilter(question.getColorA(),PorterDuff.Mode.MULTIPLY);
        //aButton.setBackgroundColor(question.getColorA());
        
        Button bButton=(Button)findViewById(R.id.bbutton);
        bButton.getBackground().setColorFilter(question.getColorB(),PorterDuff.Mode.MULTIPLY);
        
        Button cButton=(Button)findViewById(R.id.cbutton);
        cButton.getBackground().setColorFilter(question.getColorC(),PorterDuff.Mode.MULTIPLY);
        
        Button dButton=(Button)findViewById(R.id.dbutton);
        dButton.getBackground().setColorFilter(question.getColorD(),PorterDuff.Mode.MULTIPLY);
        
        Button eButton=(Button)findViewById(R.id.ebutton);
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
				userAnswers.set(examQuestionIndex, "a");
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
				userAnswers.set(examQuestionIndex, "b");
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
				userAnswers.set(examQuestionIndex, "c");
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
				userAnswers.set(examQuestionIndex, "d");
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
				userAnswers.set(examQuestionIndex, "e");
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
	
	public void showResult(){
		Integer logicCorrect=0;
        Integer informaticsCorrect=0;
        Integer langCorrect=0;
        Integer logicNotAnswered=0;
        Integer informaticsNotAnswered=0;
        Integer langNotAnswered=0;
        if(startInformatics>0){
        	for(int f=0;f<startInformatics;f++){
        		if(userAnswers.get(f)==null){
        			logicNotAnswered++;
        		}
        		else if(userAnswers.get(f).equals(examQuestionList.get(f).getCorrectAnswer())){
        			logicCorrect++;
        			System.out.println("here");
        		}
        		
        	}
        }
        if(startInformatics>0&&startLang>0){
        	for(int f=startInformatics;f<startLang;f++){
        		if(userAnswers.get(f)==null){
        			informaticsNotAnswered++;
        		}
        		else if(userAnswers.get(f).equals(examQuestionList.get(f).getCorrectAnswer())){
        			informaticsCorrect++;
        		}
        	}
        }
        if(startLang>0&&examQuestionList.size()>0){
        	for(int f=startLang;f<examQuestionList.size();f++){
        		if(userAnswers.get(f)==null){
        			langNotAnswered++;
        		}
        		else if(userAnswers.get(f).equals(examQuestionList.get(f).getCorrectAnswer())){
        			langCorrect++;
        		}
        	}
        }
        	AlertDialog.Builder resultAlert = new AlertDialog.Builder(this);
        	resultAlert.setCancelable(false);
        	resultAlert.setTitle("Nəticə");
        	resultAlert.setMessage("Məntiq:  Düzgün: "+ logicCorrect+", səhv: "+(50-logicNotAnswered-logicCorrect)+"\n\n"+
        							"Informatika:  Düzgün: "+ informaticsCorrect+", səhv: "+(25-informaticsNotAnswered-informaticsCorrect)+"\n\n"+
        							"Xarici dil:  Düzgün: "+ langCorrect+", səhv: "+(25-langNotAnswered-langCorrect+"\n\n"+
        							"Toplam:  "+(langCorrect+logicCorrect+informaticsCorrect)+" bal"));
        	resultAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
        		examFirstRun=true;
        		ExamActivity.this.finish();
        	  }
        	});

        	

        	resultAlert.show();
	}
}
