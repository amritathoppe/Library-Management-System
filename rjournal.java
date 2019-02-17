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
//import java.util.Date;
import java.util.ArrayList;
public class rjournal {
	String Journal_ID;
	String Journal_Name;
	String Journal_Userid;
	String Journal_Borroweddate;
	String Journal_Returndate;
	String Journal_Return;
	Integer Borrowed_Copies=0;
	String userid,book_borrowed,journal_borrowed,role;
	//private FileWriter fwrite;
	private BufferedReader br;
	String f="C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
	int line=0;  ArrayList<String> studentTokens = new ArrayList<String>();

	
	rjournal(String ID,String Name,String Userid,String Borrowed_date,String Return_date,String Return )
	{
		Journal_ID=ID;
		Journal_Name=Name;
		Journal_Userid=Userid;
		Journal_Borroweddate=Borrowed_date;
		Journal_Returndate=Return_date;
		Journal_Return=Return;
		//System.out.println(Book_ID+"\t"+Book_Type+"\t"+Book_Subject+"\t"+Book_Name+"\t"+Book_Author+"\t"+Total_Copies);

}
	void makereturn() 
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
		            
		      if(students[0].equals(Journal_ID))
		    		  {studentTokens.add(students[0]);
		    		  studentTokens.add(students[1]);
		    		  studentTokens.add(students[2]);
		    		  studentTokens.add(students[3]);
		    		  studentTokens.add(students[4]);
		    		  studentTokens.add((students[5]));
		    		  Integer borrowed_books=Integer.parseInt(students[6])-1;
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

void return_journal()
{  
    	  makereturn();
    	  System.out.println("Made Return..");
    	  updateorder();
    	  System.out.println("deleted in Order..");
    	  updatebookuserdetails();
    	  System.out.println("decremented journal in user detail");
    	  
 }
      
     


public void updateorder() {
	String f= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\Order.txt";
	int line=0;
	// TODO Auto-generated method stub
	try
	{
	    
	    FileInputStream fstream = new FileInputStream(f);
	    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	    String strLine;
	    br.readLine();line++;
	    // Read File Line By Line
	    while ((strLine = br.readLine()) != null) {
	        strLine = strLine.trim();

	        if (strLine.length()!=0){
	            String[] orders = strLine.split("\\t+");
	            
	      if((orders[0].equals(Journal_ID))&&(orders[1].equals(Journal_Name))&&(orders[2].equals(Journal_Userid))&&(orders[3].equals(Journal_Borroweddate))&&(orders[4].equals(Journal_Returndate)))
	    		  {
	    	 System.out.println("order passed");
	    	 break;
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
	    		  userTokens.add(users[3]);
	    		  Integer borrowed_books=Integer.parseInt(users[4])-1;
	    		  userTokens.add(Integer.toString(borrowed_books));
	    		  
	    		  
	    		  
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
		    System.out.println("borrow journals dec in userdetails");
		 
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
}