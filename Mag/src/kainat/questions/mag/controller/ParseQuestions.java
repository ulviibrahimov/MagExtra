/**
 * 
 */
package kainat.questions.mag.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kainat.questions.mag.model.Question;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author ulvi
 *
 */
public class ParseQuestions extends DefaultHandler{
	Question question;
	StringBuilder sb;
	String questionType=null;
	List<Question> questionList= new ArrayList<Question>();
	boolean newQuestionFlag=true;
	private Context context;
	public ParseQuestions(Context context){
		this.context=context;
	}
	
	public List<Question> questions(String filename){  
		  try {  
		   // obtain and configure a SAX based parser  
		   SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();  
		  
		   // obtain object for SAX parser  
		   SAXParser saxParser = saxParserFactory.newSAXParser();  
		  
		   // default handler for SAX handler class  
		   // all three methods are written in handler's body  
		   DefaultHandler defaultHandler = new DefaultHandler(){  
		      
		    String questionTextTag="close";  
		    String correctAnswerTag="close";
		    String imageTag="close";  
		    String russianTag="close";
		    String englishTag="close";
		    String logicTag="close";
		    String informaticsTag="close";
		    String frenchTag="close";
		    String questionTag="close";
		    // this method is called every time the parser gets an open tag '<'  
		    // identifies which tag is being open at time by assigning an open flag  
		    public void startElement(String uri, String localName, String qName,  
		      Attributes attributes) throws SAXException {  
		     
		     if (qName.equalsIgnoreCase("russian")) {  
		      russianTag = "open";  
		     }  
		     if (qName.equalsIgnoreCase("english")) {  
		      englishTag = "open";  
		     }  
		     if (qName.equalsIgnoreCase("logic")) {  
		      logicTag = "open";  
		     }  
		     if (qName.equalsIgnoreCase("french")) {  
		      frenchTag = "open";  
		     }  
		     if (qName.equalsIgnoreCase("questionText")) {  
			      questionTextTag = "open";  
			     }  
		     if (qName.equalsIgnoreCase("Informatics")) {  
			      informaticsTag = "open";  
			     }  
		     
		     if (qName.equalsIgnoreCase("correctAnswer")) {  
			      correctAnswerTag = "open";  
			     } 
		     if (qName.equalsIgnoreCase("question")) {  
			      questionTag = "open"; 
			      sb=new StringBuilder();
			     } 
		     if (qName.equalsIgnoreCase("image")) {  
			      imageTag = "open";  
			     } 
		    }  
		  
		    // prints data stored in between '<' and '>' tags  
		    public void characters(char ch[], int start, int length)  
		      throws SAXException {  
		       
		     if (russianTag.equals("open")) {  
		      questionType="russian";  
		     }  
		     if (englishTag.equals("open")) {  
		      questionType="english"; 
		     }  
		     if (logicTag.equals("open")) {  
		    	 questionType="logic";  
		     }  
		     if (frenchTag.equals("open")) {  
		    	 questionType="french";  
		     }  
		     
		     if (informaticsTag.equals("open")) {  
		    	 questionType="informatics";  
		     }  
		     
		     if(questionTag.equals("open")){
		    	 if(newQuestionFlag){
		    		 question = new Question();
		    		 newQuestionFlag=false;
		    	 }
		    	 question.setQuestionType(questionType);
		     }
		     
		     if (questionTextTag.equals("open")) {   
		    	 if (sb!=null) {
		    	        for (int i=start; i<start+length; i++) {
		    	            sb.append(ch[i]);
		    	        }
		    	    }
		    	  //question.setQuestionText(new String(ch, start, length).trim()); 
		    	  //System.out.println("text: "+question.getQuestionText());
		     }
		    
		     if (correctAnswerTag.equals("open")) {  
		    	  question.setCorrectAnswer(new String(ch, start, length).trim()); 
		     }
		     if (imageTag.equals("open")) {  
		    	  question.setImage(new String(ch, start, length).trim()); 
		     }
		    }  
		  
		    // calls by the parser whenever '>' end tag is found in xml   
		    // makes tags flag to 'close'  
		    public void endElement(String uri, String localName, String qName)  
		      throws SAXException {  
		       
		    	if (qName.equalsIgnoreCase("russian")) {  
				      russianTag = "close";  
				     }  
				     if (qName.equalsIgnoreCase("english")) {  
				      englishTag = "close";  
				     }  
				     if (qName.equalsIgnoreCase("logic")) {  
				      logicTag = "close";  
				     }  
				     if (qName.equalsIgnoreCase("french")) {  
				      frenchTag = "close";  
				     }  
				     if (qName.equalsIgnoreCase("questionText")) {  
					      questionTextTag = "close";  
					     }  
				     if (qName.equalsIgnoreCase("informatics")) {  
					      informaticsTag = "close";  
					     } 
				     
				     if (qName.equalsIgnoreCase("image")) {  
					      imageTag = "close";  
					     } 
				     if (qName.equalsIgnoreCase("correctAnswer")) {  
					      correctAnswerTag = "close";  
					     } 
				     if (qName.equalsIgnoreCase("question")) {  
				    	  question.setQuestionText(sb.toString().trim());
					      questionTag = "close";  
					      newQuestionFlag=true;
					      //System.out.println(question.toString());
					      questionList.add(question);
					     } 
				    }  
		   };  
		     
		   // parse the XML specified in the given path and uses supplied  
		   // handler to parse the document  
		   // this calls startElement(), endElement() and character() methods  
		   // accordingly  
		   AssetManager mngr = context.getAssets();
		   InputStream is = mngr.open(filename);;
		   saxParser.parse(is, defaultHandler);  
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }
		return questionList;  
		 }  
		} 

