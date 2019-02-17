package lbms;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.sql.Date;
import java.util.Date;
import java.util.ArrayList;
public class book {
	String Book_ID;
	String Book_Type;
	String Book_Subject;
	String Book_Name;
	String Book_Author;
	String Total_Copies;
	Integer Borrowed_Copies=0;
	String userid,book_borrowed,journal_borrowed,role;
	private FileWriter fwrite;
	private BufferedReader br;
	String f="C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
	int line=0;  ArrayList<String> studentTokens = new ArrayList<String>();

	
	book(String ID,String Type,String Subject,String Name,String Author,String totalcopies )
	{
		Book_ID=ID;
		Book_Type=Type;
		Book_Subject=Subject;
		Book_Name=Name;
		Book_Author=Author;
		Total_Copies=totalcopies;
		//System.out.println(Book_ID+"\t"+Book_Type+"\t"+Book_Subject+"\t"+Book_Name+"\t"+Book_Author+"\t"+Total_Copies);

}
	void makeborrow() 
	{
		
		try {
		    FileInputStream fstream = new FileInputStream(f);
		    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		    String strLine;
		    br.readLine();line++;
		    // Read File Line By Line
		    while ((strLine = br.readLine()) != null) {
		        strLine = strLine.trim();

		        if (strLine.length()!=0){
		            String[] students = strLine.split("\\t+");
		            
		      if(students[0].equals(Book_ID))
		    		  {studentTokens.add(students[0]);
		    		  studentTokens.add(students[1]);
		    		  studentTokens.add(students[2]);
		    		  studentTokens.add(students[3]);
		    		  studentTokens.add(students[4]);
		    		  studentTokens.add((students[5]));
		    		  Integer borrowed_books=Integer.parseInt(students[6])+1;
		    		  studentTokens.add(Integer.toString(borrowed_books));
		    		  
		    		  
		    	  //copy_total=Integer.parseInt(students[5]);
		    	  //System.out.println(line);
		    		break;
		    	//  studentTokens.add(Integer.parseInt(students[5])-Integer.parseInt(students[6]));//the available copies
		    		  }
		           
		      else line++;
		            }
		        }

		   // System.out.println(line+"\nend\n"+studentTokens.get(0));
		       // Close the input stream
		    br.close();
		} catch (Exception e) {// Catch exception if any
		    System.err.println("Error: " + e.getMessage());
		}

			removeNthLine(f,line);
			//System.out.println(" cx");
			
			try//if new book values then inserts automatically.
			{
			    String filename= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
			    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			    fw.write(studentTokens.get(0)+"\t"+studentTokens.get(1)+"\t"+ studentTokens.get(2)+"\t"+studentTokens.get(3)+"\t"+studentTokens.get(4)+"\t"+studentTokens.get(5)+"\t"+studentTokens.get(6)+"\n");
			    System.out.println("Done insertion");
			 
			    fw.close();
			}
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
			

	}
private void removeNthLine(String f, int line)  {
		// TODO Auto-generated method stub
	  File tmp;
	try {
		System.out.println("remove nth line");
		tmp = File.createTempFile("tmp", "");
	    BufferedReader br = new BufferedReader(new FileReader(f));
	    BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

	    for (int i = 0; i < line; i++)
	        bw.write(String.format("%s%n", br.readLine()));

	    br.readLine();

	    String l;
	    while (null != (l = br.readLine()))
	        bw.write(String.format("%s%n", l));

	    br.close();
	    bw.close();

	    File oldFile = new File(f);
	    if (oldFile.delete())
	        tmp.renameTo(oldFile);}
	    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
void Add(String ID,String Type,String Subject,String Name,String Author)	
{/*initially check if same book_id is present or book_name and author is present,
	if present increment total copies//modify the file content
	else add the new book //insert data into a field.
	*/Book_ID=ID;
	Book_Subject=Subject;
	Book_Name=Name;
	Book_Author=Author;
	
	Borrowed_Copies=0;
	Book_Type=Type;
	int line=0;
	int chk=0;
	System.out.println(Book_ID+"  "+Book_Name);
	try //if existing book then adds up copies (look on )
	{Integer x;
	String y; 
		 FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        StringBuilder fileContent=new StringBuilder();
	        String strLine;
	        br.readLine();
	       line++;
	        // Read File Line By Line
	        while ((strLine = br.readLine()) != null) {
	            strLine = strLine.trim();

	            if (strLine.length()!=0){
	                String[] students = strLine.split("\\t+");
	                
	          if(students[3].equals(Book_Name))
	        		  {chk=1;
	        	  studentTokens.add(students[0]);
	    		  studentTokens.add(students[1]);
	    		  studentTokens.add(students[2]);
	    		  studentTokens.add(students[3]);
	    		  studentTokens.add(students[4]);
	    		  Integer total_copies=Integer.parseInt(students[5])+1;
	    		  //studentTokens.add((students[5]));
	    		  System.out.println("Book count is incremented here"+total_copies);
	    		  studentTokens.add(Integer.toString(total_copies));
	    		  studentTokens.add((students[6]));
	    		  //match=true;
	    		  break;
	    
	    		  }
	           
	          else line++;
	            }
	        }
	    br.close();
	} catch (Exception e) {// Catch exception if any
	    System.err.println("Error: " + e.getMessage());
	}

		removeNthLine(f,line);
if(chk==1) {
		try// book values then inserts automatically.
		{
		    String filename= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    fw.write(studentTokens.get(0)+"\t"+studentTokens.get(1)+"\t"+ studentTokens.get(2)+"\t"+studentTokens.get(3)+"\t"+studentTokens.get(4)+"\t"+studentTokens.get(5)+"\t"+studentTokens.get(6)+"\n");
		    System.out.println("Done insertion");
		 
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}	
}
else {
	try//if new book values then inserts automatically.
	{
	     FileWriter fw = new FileWriter(f,true); //the true will append the new data
	    fw.write(Book_ID+"\t"+Book_Type+"\t"+ Book_Subject+"\t"+Book_Name+"\t"+Book_Author+"\t"+"1"+"\t0\n");
	    System.out.println("Done insertion");
	   fw.close();
	}
	catch(IOException ioe)
	{
	    System.err.println("IOException: " + ioe.getMessage());
	}
}
	
		
		
}
void borrow_book()
{  
    	  makeborrow();
    	  System.out.println("Made Borrow..");
    	  updateorder();
    	  System.out.println("Made Order..");
    	  updatebookuserdetails();
    	  System.out.println("incremented in user detail");
    	  
 }
      
     


public void updateorder() {
	// TODO Auto-generated method stub
	try
	{
	    String filename= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\Order.txt";
	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
	    Date date = new Date();
		String Borrowed_Date = date.toString();
		 int days=30;
	    DateCalc dc=new DateCalc();
	    DateCalc.main(null);
	    String Return_Date=DateCalc.addDays(date, days);
	   
		System.out.println("Todays date: "+Borrowed_Date);
	    System.out.println(Borrowed_Date);
	    String Book_userid = currentuser();
	    //fw.write("\n");
	    fw.write(Book_ID+"\t"+Book_Name+"\t"+Book_userid+"\t"+Borrowed_Date+"\t"+Return_Date+"\n");
	    System.out.println("Done insertion");
	 
	    fw.close();
	}
	catch(IOException ioe)
	{
	    System.err.println("IOException: " + ioe.getMessage());
	}


	
}
private String currentuser() {
	try//inserts user logged in details
	{
	  //System.out.println("try");
	  FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserLoggedIn.txt");
        br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
       
        // Read File Line By Line
        while ((strLine = br.readLine())!= null)
         {
            //System.out.println("while");
        	strLine = strLine.trim();
        	if (strLine.length()!=0)
            {
                String[] user = strLine.split("\\t+");
                userid=user[0];
               
            }
        	}
        br.close();
	}
	catch(IOException ioe)
	{
	    System.err.println("IOException: " + ioe.getMessage());
	}
	return userid;
	}

	

public void updatebookuserdetails()
{
	
	String f="C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserDetails.txt";
	int line=0;  ArrayList<String> userTokens = new ArrayList<String>();

	try {
	    FileInputStream fstream = new FileInputStream(f);
	    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	    String strLine;
	    br.readLine();
	    line++;
	    String cuser=currentuser();
	    // Read File Line By Line
	    while ((strLine = br.readLine()) != null) {
	        strLine = strLine.trim();

	        if (strLine.length()!=0){
	            String[] users = strLine.split("\\t+");
	            
	      if(users[0].equals(cuser))
	    		  {userTokens.add(users[0]);
	    		  userTokens.add(users[1]);
	    		  userTokens.add(users[2]);
	    		 
	    		  Integer borrowed_books=Integer.parseInt(users[3])+1;
	    		  userTokens.add(Integer.toString(borrowed_books));
	    		  userTokens.add(users[4]);
	    		  
	    		  
	    	  //copy_total=Integer.parseInt(students[5]);
	    	  //System.out.println(line);
	    		break;
	    	//  studentTokens.add(Integer.parseInt(students[5])-Integer.parseInt(students[6]));//the available copies
	    		  }
	           
	      else line++;
	            }
	        }

	   // System.out.println(line+"\nend\n"+studentTokens.get(0));
	       // Close the input stream
	    br.close();
	} catch (Exception e) {// Catch exception if any
	    System.err.println("Error: " + e.getMessage());
	}

		removeNthLine(f,line);
		//System.out.println(" cx");
		
		try//if new book values then inserts automatically.
		{
		    String filename= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserDetails.txt";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    fw.write(userTokens.get(0)+"\t"+userTokens.get(1)+"\t"+ userTokens.get(2)+"\t"+userTokens.get(3)+"\t"+userTokens.get(4)+"\n");
		    System.out.println("borrw books inc in userdetails");
		 
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
}
public String currentrole() {
	
	try//inserts user logged in details
	{
	  //System.out.println("try");
	  FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserLoggedIn.txt");
        br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
       
        // Read File Line By Line
        while ((strLine = br.readLine())!= null)
         {
            //System.out.println("while");
        	strLine = strLine.trim();
        	if (strLine.length()!=0)
            {
                String[] user = strLine.split("\\t+");
                role=user[2];
               
            }
        	}
        br.close();
	}
	catch(IOException ioe)
	{
	    System.err.println("IOException: " + ioe.getMessage());
	}
	return role;
	}
void delete_book(String ID)
{
	
	
	Book_ID=ID;
	System.out.println(Book_ID);
	String f="C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
	int line=0; 
	 ArrayList<String> studentTokens = new ArrayList<String>();
try {	
	FileInputStream fstream = new FileInputStream(f);
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    //StringBuilder fileContent=new StringBuilder();
    String strLine;
    br.readLine();
    line++;
    //System.out.println(br.readLine());
    // Read File Line By Line
    Integer x;String y;
    while ((strLine = br.readLine()) != null) {
        strLine = strLine.trim();

        if (strLine.length()!=0){
            String[] students = strLine.split("\\t+");
            
      if(students[0].equals(Book_ID))
    		  {
    	  studentTokens.add(students[0]);
		  studentTokens.add(students[1]);
		  studentTokens.add(students[2]);
		  studentTokens.add(students[3]);
		  studentTokens.add(students[4]);
		  x=Integer.parseInt(students[5]);
    	  x-=1;    	  //decrement one copy
    	  y=x.toString();
    	  students[5]=y;
    	  System.out.println(y);
    	  studentTokens.add((students[5]));
    	  studentTokens.add((students[6]));//logic crct till here
    	  break;
      }
      else line++;
      }} br.close();
} catch (Exception e) {// Catch exception if any
    System.err.println("Error: " + e.getMessage());
}
    removeNthLine(f,line);
    try//if new book values then inserts automatically.
	{
	    String filename= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
	    fw.write(studentTokens.get(0)+"\t"+studentTokens.get(1)+"\t"+ studentTokens.get(2)+"\t"+studentTokens.get(3)+"\t"+studentTokens.get(4)+"\t"+studentTokens.get(5)+"\t"+studentTokens.get(6)+"\n");
	    System.out.println("Done insertion");
	 
	    fw.close();
	}
	catch(IOException ioe)
	{
	    System.err.println("IOException: " + ioe.getMessage());
	}
      
}

}
