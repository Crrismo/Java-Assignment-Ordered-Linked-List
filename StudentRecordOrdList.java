public class StudentRecordOrdList extends OrderedList {
	
	protected int compare(Object obj1, Object obj2) {
		String string_a; 
		String string_b;
		//concatenate surname and name which  together  make  the student record unique
		string_a= ( (StudentRecord)obj1 ).firstName+( (StudentRecord)obj1 ).lastName;
		string_b= ( (StudentRecord)obj2 ).firstName+( (StudentRecord)obj2 ).lastName;
		return string_a.compareTo(string_b);
	}
}
