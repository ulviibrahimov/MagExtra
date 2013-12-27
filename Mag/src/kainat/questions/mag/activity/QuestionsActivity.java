package kainat.questions.mag.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kainat.questions.mag.R;
import kainat.questions.mag.Helper.ExamHelper;
import kainat.questions.mag.controller.ParseQuestions;
import kainat.questions.mag.model.Question;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends Activity {
	public static List<Question> questionL= new ArrayList<Question>();
	public static Integer questionIndex=0;
	public static List<Question> examQuestionL;
	public static Integer startEnglish=0;
	public static boolean firstRun=true;
	public static Integer startFrench=0;
	public static Integer startInformatics=0;
	public static Integer startRussian=0;
	public static Integer startLogicText=0;
	public static Integer startLogicImage=0;
	public static Integer langChoosen;
	Context context=this;
	Button aButton;
	Button bButton;
	Button cButton;
	Button dButton;
	Button eButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		langChoosen=0;
		if(firstRun){
			firstRun=false;
			ParseQuestions pq=new ParseQuestions(context);
			questionL=pq.questions("testquestions.xml");
			AlertDialog.Builder langAlert;
		    final CharSequence[] languages = {"Ingilis dili","Rus dili","Fransız dili"};
		    langAlert = new AlertDialog.Builder(context);
		    langAlert.setTitle("Xarici dil seçin");
		    langAlert.setSingleChoiceItems(languages,-1, new DialogInterface.OnClickListener()
		    {
		        @Override
		        public void onClick(DialogInterface dialog, int which) 
		        {
		            if(languages[which]=="Ingilis dili")
		            {
		            	langChoosen=1;
		            }
		            else if (languages[which]=="Rus dili")
		            {
		            	langChoosen=2;
		            }
		            else if (languages[which]=="Fransız dili")
		            {
		            	langChoosen=3;
		            }
		        }
		    });
		    langAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int whichButton) {
		    		
		    	  }
		    	});	
		    langAlert.show();
		startLogicText=ExamHelper.getStartText(0,questionL);
		startInformatics=ExamHelper.getStartInformatics(startLogicText, questionL);
		startEnglish=ExamHelper.getStartEnglish(startInformatics, questionL);
		startRussian=ExamHelper.getStartRussian(startEnglish, questionL);
		startFrench=ExamHelper.getStartFrench(startRussian, questionL);
		}
		this.displayQuestion(questionL.get(0), 0);	
		
		addListenerOnButtonPrev();
		addListenerOnButtonNext();
		addListenerOnButtonA();
		addListenerOnButtonB();
		addListenerOnButtonC();
		addListenerOnButtonD();
		addListenerOnButtonE();
	}
	@Override
	public void onBackPressed() {
		AlertDialog.Builder exitAlert = new AlertDialog.Builder(this);

    	exitAlert.setTitle("Çıxış");
    	exitAlert.setMessage("Çıxmaq istədiyinizdən əminsinizmi?");
    	exitAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    		System.exit(1);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds itegulpembems to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questions_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.itemrussian:
	        	questionIndex=startRussian;
	            displayQuestion(questionL.get(questionIndex),questionIndex);
	            return true;
	        case R.id.iteminformatics:
	            questionIndex=startInformatics;
	            displayQuestion(questionL.get(questionIndex),questionIndex);
	            return true;
	        case R.id.itemenglish:
	            questionIndex=startEnglish;
	            displayQuestion(questionL.get(questionIndex),questionIndex);
	            return true;
	        case R.id.itemlogic:
	            questionIndex=0;
	            displayQuestion(questionL.get(questionIndex),questionIndex);
	            return true;
	        case R.id.itemfrench:
	            questionIndex=startFrench;
	            displayQuestion(questionL.get(questionIndex),questionIndex);
	            return true;
	        case R.id.choosequestion:
	        	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	        	alert.setTitle("Sual seç");
	        	alert.setMessage("Sualın nömrəsini daxil edin : ( 1 - "+questionL.size()+" )");

	        	// Set an EditText view to get user input 
	        	final EditText input = new EditText(this);
	        	alert.setView(input);

	        	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        	  Editable value = input.getText();
	        	  String questionNumber=value.toString();
	        	  try{
	        		  
	        		  int temp=Integer.parseInt(questionNumber)-1;
	        		  if(temp>=questionL.size() || temp<0){
	        			  Toast.makeText(context, "( 1 - "+questionL.size()+" ) intervalında ədəd daxil edin!", 1).show();
	        		  }
	        		  
	        		  else{
	        			  questionIndex=Integer.parseInt(questionNumber)-1;
	        			  displayQuestion(questionL.get(questionIndex),questionIndex);
	        		  }
	        		  }catch(NumberFormatException e){
	        			  Toast.makeText(context, "( 1 - "+questionL.size()+" ) intervalında ədəd daxil edin!", 1).show();
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
	        case R.id.randomquestion:
	        	Random rand = new Random();
	        	questionIndex = rand.nextInt(questionL.size());
	        	displayQuestion(questionL.get(questionIndex),questionIndex);
	        	return true;
	        case R.id.exit:
	        	AlertDialog.Builder exitAlert = new AlertDialog.Builder(this);

	        	exitAlert.setTitle("Çıxış");
	        	exitAlert.setMessage("Çıxmaq istədiyinizdən əminsinizmi?");
	        	exitAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        		System.exit(1);
	        	  }
	        	});

	        	exitAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        	  public void onClick(DialogInterface dialog, int whichButton) {
	        	    // Canceled.
	        	  }
	        	});

	        	exitAlert.show();
	        	return true;
	        case R.id.exam:
	        	examQuestionL= new ArrayList<Question>();

	            ExamHelper.addQuestions(questionL, examQuestionL, 0, startLogicText, 15);			//image questions
	            ExamHelper.addQuestions(questionL, examQuestionL, startLogicText, startInformatics, 35); //text logic
	            ExamHelper.addQuestions(questionL, examQuestionL, startInformatics, startEnglish,25);//informatics
	            if(langChoosen==1)
	            ExamHelper.addQuestions(questionL, examQuestionL, startEnglish, startRussian,25);    //english
	            else if(langChoosen==2)
	            ExamHelper.addQuestions(questionL, examQuestionL, startRussian, startFrench,25);	  //russian
	            else if(langChoosen==3)
	            ExamHelper.addQuestions(questionL, examQuestionL, startFrench, questionL.size(),25); //french
	            System.out.println("size"+examQuestionL.size());
	            Intent intent = new Intent(this, ExamActivity.class);
	            startActivity(intent);
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void displayQuestion(Question question, Integer index){
		if(question.getImage()!=null){
			String imageName="image"+question.getImage();
			ImageView img= (ImageView) findViewById(R.id.imageView1);
			final int id = getResources().getIdentifier("kainat.questions.mag:drawable/" + imageName, null, null);
			System.out.println("from id: "+id);
			img.setImageResource(id);
			img.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v) {
			        Intent imageIntent = new Intent(QuestionsActivity.this,ImageActivity.class);
			        imageIntent.putExtra("imageId", id);
			        startActivity(imageIntent);
			    }
			});
		}
		else{
			ImageView img= (ImageView) findViewById(R.id.imageView1);
			img.setImageResource(R.drawable.kainat);

			//TouchImageView timg = (TouchImageView) findViewById(R.id.imageView1);
	        //timg.setMaxZoom(4);

		}

		TextView t=new TextView(this);
		t=(TextView)findViewById(R.id.questionView);
		t.setText("Sual: "+(index+1));
		t=(TextView)findViewById(R.id.questionText);
		t.setText(question.getQuestionText());

	}

	public void addListenerOnButtonPrev() {
		 
		Button prevButton = (Button) findViewById(R.id.prevbutton);
 
		prevButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(questionIndex>0){
					questionIndex--;
				}
				else if (questionIndex==0){
					questionIndex=questionL.size()-1;
				}
				displayQuestion(questionL.get(questionIndex),questionIndex);
			}
 
		});
 
	}	
	
	public void addListenerOnButtonNext() {
		 
		Button prevButton = (Button) findViewById(R.id.nextbutton);
 
		prevButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(questionIndex<questionL.size()-1){
					questionIndex++;
				}
				else if (questionIndex==questionL.size()-1){
					questionIndex=0;
				}
				displayQuestion(questionL.get(questionIndex),questionIndex);
			}
 
		});
 
	}	
	
	public void addListenerOnButtonA() {
		 
		aButton = (Button) findViewById(R.id.abutton);
 
		aButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(questionL.get(questionIndex).getCorrectAnswer().equals("a")){
					Toast toast=Toast.makeText(context, "Cavabınız düzgündür!", 1);
					toast.getView().setBackgroundColor(Color.GREEN);
					toast.show();
				}
				else{
					Toast toast=Toast.makeText(context, "Cavabınız səhvdir!", 1);
					toast.getView().setBackgroundColor(Color.RED);
					toast.show();
				}
			}
 
		});
 
	}	
	public void addListenerOnButtonB() {
		 
		bButton = (Button) findViewById(R.id.bbutton);
 
		bButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(questionL.get(questionIndex).getCorrectAnswer().equals("b")){
					Toast toast=Toast.makeText(context, "Cavabınız düzgündür!", 1);
					toast.getView().setBackgroundColor(Color.GREEN);
					toast.show();
				}
				else{
					Toast toast=Toast.makeText(context, "Cavabınız səhvdir!", 1);
					toast.getView().setBackgroundColor(Color.RED);
					toast.show();    
				}
			}
 
		});
 
	}
	
	public void addListenerOnButtonC() {
		 
		cButton = (Button) findViewById(R.id.cbutton);
 
		cButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(questionL.get(questionIndex).getCorrectAnswer().equals("c")){
					Toast toast=Toast.makeText(context, "Cavabınız düzgündür!", 1);
					toast.getView().setBackgroundColor(Color.GREEN);
					toast.show();
				}
				else{
					Toast toast=Toast.makeText(context, "Cavabınız səhvdir!", 1);
					toast.getView().setBackgroundColor(Color.RED);
					toast.show();
				}
			}
 
		});
 
	}
	
	public void addListenerOnButtonD() {
		 
		dButton = (Button) findViewById(R.id.dbutton);
 
		dButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(questionL.get(questionIndex).getCorrectAnswer().equals("d")){
					Toast toast=Toast.makeText(context, "Cavabınız düzgündür!", 1);
					toast.getView().setBackgroundColor(Color.GREEN);
					toast.show();
				}
				else{
					Toast toast=Toast.makeText(context, "Cavabınız səhvdir!", 1);
					toast.getView().setBackgroundColor(Color.RED);
					toast.show();
				}
			}
 
		});
 
	}
	
	public void addListenerOnButtonE() {
		 
		eButton = (Button) findViewById(R.id.ebutton);
 
		eButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(questionL.get(questionIndex).getCorrectAnswer().equals("e")){
					Toast toast=Toast.makeText(context, "Cavabınız düzgündür!", 1);
					toast.getView().setBackgroundColor(Color.GREEN);
					toast.show();
				}
				else{
					Toast toast=Toast.makeText(context, "Cavabınız səhvdir!", 1);
					toast.getView().setBackgroundColor(Color.RED);
					toast.show();
				}
			}
 
		});
 
	}
}
