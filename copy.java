package lbms;

public class copy {
String type,Book_Totalcopies;	
book b;journal j;
Integer Total_COPIES;
Integer Borrowed_COPIES;
//journal j;
copy(book b1)
{
b=b1;
Total_COPIES=Integer.parseInt(b.Total_Copies);

}
public copy(journal j2) {
	// TODO Auto-generated constructor stub
	journal j=j2;
	
}
void Borrow(book B)//done
{book b=B;
	b.borrow_book();
}
void Borrow(journal j)//done
{journal j1=j;
	j1.borrow_Journal();
}
void add(book b1)//done
{b=b1;
//b1.Add(ID,Type,Subject, Name, Author);
b.Add(b.Book_ID, b.Book_Type,b.Book_Subject,b.Book_Name,b.Book_Author);
}
void add(journal j)
{journal j1=j;
j1.Add(j1.Journal_ID, j1.Journal_Type,j1.Journal_Subject,j1.Journal_Name,j1.Journal_Author);
}
void return_copy(rbook B)
		{
	B.return_book();
		}
void return_copy(rjournal J) {
	J.return_journal();
}

void Delete(book b)//done
{
	b.delete_book(b.Book_ID);
}
void Delete(journal j)
{
	j.delete_Journal(j.Journal_ID,j.Total_Copies);
	}

}

