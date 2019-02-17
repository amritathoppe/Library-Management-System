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
public class journal {
	String Journal_ID;
	String Journal_Type;
	String Journal_Subject;
	String Journal_Name;
	String Journal_Author;
	String Total_Copies;
	Integer Borrowed_Copies=0;
	String userid,book_borrowed,journal_borrowed,role;
	private FileWriter fwrite;
	private BufferedReader br;
	int line=0; 
	 ArrayList<String> studentTokens = new ArrayList<String>();
	 String f="C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
	
	journal(String ID,String Type,String Subject,String Name,String Author,String totalcopies )
	{
		Journal_ID=ID;
		Journal_Type="journal";
		Journal_Subject=Subject;
		Journal_Name=Name;
		Journal_Author=Author;
		Total_Copies=totalcopies;
		//System.out.println(Book_ID+"\t"+Book_Type+"\t"+Book_Subject+"\t"+Book_Name+"\t"+Book_Author+"\t"+Total_Copies);

}
	void makeborrow() 
	{
		
		int line=0;  ArrayList<String> studentTokens = new ArrayList<String>();

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
			    
			    FileWriter fw = new FileWriter(f,true); //the true will append the new data
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
	*/Journal_ID=ID;
	Journal_Subject=Subject;
	Journal_Name=Name;
	Journal_Author=Author;
	
	Borrowed_Copies=0;
	Journal_Type=Type;
	System.out.println(Journal_ID+"  "+Journal_Name);
	try //if exsisting book then adds up copies (look on )
	{Integer x;
	String y;
		 FileInputStream fstream = new FileInputStream(f);
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        StringBuilder fileContent=new StringBuilder();
	        String strLine;
	        br.readLine();
	        // Read File Line By Line
	        while ((strLine = br.readLine()) != null) {
	            strLine = strLine.trim();

	            if (strLine.length()!=0){
	                String[] students = strLine.split("\\t+");
	                
	          if(students[3].equals(Journal_Name))
	        		  {
	        	  x=Integer.parseInt(students[4]);
	        	  x+=1;    	  
	        	  y=x.toString();students[4]=y;System.out.println(y);
	        	  String newline=students[4];
	        	  fileContent.append(newline);
	        	  	        		  }}}FileWriter fwrite=new FileWriter(f,true);
	        	  	        		  BufferedWriter out=new BufferedWriter(fwrite);
	        	  	        		  out.close();
	          br.close();
	    	    } catch (Exception e) {// Catch exception if any
	    	        System.err.println("Error: " + e.getMessage());
	    	    }
	removeNthLine(f,line);
	try//if new book values then inserts automatically.
	{
	    String filename= "C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\LibraryDetails.txt";
	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
	    fw.write(Journal_ID+"\t"+Journal_Type+"\t"+ Journal_Subject+"\t"+Journal_Name+"\t"+Journal_Author+"\t"+"1"+"\t0\n");
	    System.out.println("Done insertion");
	  /*  fw.write("\nSivacharan\tPhysics\t11-01-2019\n");//appends the string to the file
	    fw.write("\nAmrita\tBusiness Economics\t11-10-2019\n");//appends the string to the file
	    fw.write("Chintu\tABC\t11-01-2019\n");//appends the string to the file*/
	    fw.close();
	}
	catch(IOException ioe)
	{
	    System.err.println("IOException: " + ioe.getMessage());
	}
}
void borrow_Journal()
{  
    	  makeborrow();
    	  System.out.println("Made Borrow..");
    	  updateorder();
    	  System.out.println("Made Order..");
    	  updateJournaluserdetails();
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
	    String Journal_userid = currentuser();
	    //fw.write("\t");
	  // fw.write("\n");//(evrything til2 has cum due to this only)
	  // fw.write(Journal_ID+"\t"+Journal_Name+"\t"+Journal_userid+"\t"+Borrowed_Date+"\t"+Return_Date);
	   fw.write(Journal_ID+"\t"+Journal_Name+"\t"+Journal_userid+"\t"+Borrowed_Date+"\t"+Return_Date+"\n");//actual added j4 but after retunr j5 no
	   
	   System.out.println("Done insertion");
	 
	    fw.close();
	}
	catch(IOException ioe)
	{
	    System.err.println("IOException: " + ioe.getMessage());
	}


	
}
public String currentuser() {
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

	

public void updateJournaluserdetails()
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
	    		  Integer borrowed_books=Integer.parseInt(users[4])+1;
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
		    System.out.println("borrw books inc in userdetails");
		 
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
}
void delete_Journal(String ID,String Total_copies)
{
	int total=Integer.parseInt(Total_copies);
	if(total>1)
{Journal_ID=ID;
	System.out.println(Journal_ID);
	
	
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
            
      if(students[0].equals(Journal_ID))
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
      }}
    removeNthLine(f,line);
    fwrite = new FileWriter(f,true);
    fwrite.write(studentTokens.get(0)+"\t"+studentTokens.get(1)+"\t"+ studentTokens.get(2)+"\t"+studentTokens.get(3)+"\t"+studentTokens.get(4)+"\t"+studentTokens.get(5)+"\t"+studentTokens.get(6)+"\n");
    System.out.println("Done insertion");
 
      br.close();
}
catch(IOException ioe)
{
    System.err.println("IOException: " + ioe.getMessage());
}
}
	else
		removeNthLine(f,line);

}}
