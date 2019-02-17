package lbms;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Library_member {
String Userid;
String Pwd;
String Role;
Integer MaxBooks;
Integer MaxJournal;
Integer Book_borrowed;
Integer Journal_borrowed;
Library_member(String userid,String pwd,String role)
{
Userid=userid;
Pwd=pwd;
Role=role;
switch(role)
{
case "student":MaxBooks=4;MaxJournal=0;break;
case "instructor":MaxBooks=8;MaxJournal=4;break;
case "librarian":MaxBooks=8;MaxJournal=4;break;
}
}

boolean oktoBorrowBook()
{boolean x=false;
//open the user details file and check book_borrowed with maxbooks 
try {	FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserDetails.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    String strLine;
    br.readLine();
    // Read File Line By Line
    while ((strLine = br.readLine()) != null) {
        strLine = strLine.trim();

        if (strLine.length()!=0){
            String[] students = strLine.split("\\t+");
            
      if(students[0].equals(Userid))
    		 {
    	  Book_borrowed=Integer.parseInt(students[3]);
    	  System.out.println(Book_borrowed);
    	  System.out.println(MaxBooks);
    	  if(Book_borrowed<MaxBooks)
    		x=true;
    	  else 
    		  x= false;
    	    }
          }
        }
   // Close the input stream
    br.close();
} catch (Exception e) {// Catch exception if any
    System.err.println("Error: " + e.getMessage());
}
System.out.println(x);
return x;
}
boolean oktoBorrowJournal()
{boolean x=false;
//open the user details file and check book_borrowed with maxbooks 
try {	FileInputStream fstream = new FileInputStream("C:\\Users\\amrit\\Documents\\College Docs\\Fall 2017\\Software Development\\UserDetails.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    String strLine;
    br.readLine();
    // Read File Line By Line
    while ((strLine = br.readLine()) != null) {
        strLine = strLine.trim();

        if (strLine.length()!=0){
            String[] students = strLine.split("\\t+");
            
      if(students[0].equals(Userid))
    		 {
    	  Journal_borrowed=Integer.parseInt(students[4]);
    	  System.out.println(Journal_borrowed);
    	  System.out.println(MaxJournal);
    	  
    	  if(Journal_borrowed<MaxJournal)
    		x=true;
    	  else
    		  x= false;
    	    }
          }
        }
   // Close the input stream
    br.close();
} catch (Exception e) {// Catch exception if any
    System.err.println("Error: " + e.getMessage());
}
System.out.println(x);
return x;
}
public static void main(String ars[])
{
	Library_member l=new Library_member("at3yb","1234","student");
	boolean check=l.oktoBorrowBook();
System.out.println(check);	
}


}