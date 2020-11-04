import java.util.Scanner; 
public class StudentRecordOrdListTest {
	public static int choose;
	StudentRecordOrdList studRecOrdList = new StudentRecordOrdList();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);// create writing option
		StudentRecordTest studRecTest = new StudentRecordTest();
		while(true) {
			studRecTest.getOption(scan);
		}
	}
	
	public void getOption(Scanner scan) {
		String firstName; 
		String lastName;
		int SN; //student number is an integer
		String scanSN;
		Object studentInfo; 
		ListNode studentTest;
		System.out.print("\nStudent Record Menu:");
		System.out.print("\nType the number of the option you want\n");
		System.out.print("1. Add new student record\n2. Remove a student record\n3. Add the average mark for student\n4. Print the record of one student\n5. Print all exissting student records\n6. Exit program\n");
		String choice = scan.next();
		
		try {
			choose = Integer.parseInt(choice);
			
			switch(choose) {
			//Add student record
			case 1:
				System.out.println("Write the student's first name:");
				firstName = scan.next();
				System.out.println("Write the student's last name:");
				lastName = scan.next();
				while(true) {
					System.out.println("Write the student number:");
					scanSN = scan.next();
					
					try {
						
						SN = Integer.parseInt(scanSN);
						studentInfo = new StudentRecord(firstName, lastName, SN);
						boolean testSN = studRecOrdList.insert(studentInfo);
						if(testSN ==true )
							System.out.println("Student record added!");		
						else if (testSN== false) System.out.println("Error! This student record is already in the database.");
						return;
					}
					catch(NumberFormatException ex){//check to make sure only an integer is input
			    		System.out.println("Error! The student number should be an integer value\n");
			    	}
				}
			//Remove student record
			case 2:
				
				System.out.println("Write the first name of the student you want to remove:");
				firstName = scan.next();
				System.out.println("Write the last name of the student you want to remove:");
				lastName = scan.next();
				studentInfo = new StudentRecord(firstName, lastName, 0);
				ListNode removeTest = studRecOrdList.remove(studentInfo);
				//verify if the student you want to remove exists or not in the list
				if(removeTest!=null)
					System.out.println("The student record was removed!");
				else
					System.out.println("Error! The student record of " + firstName+ " "+ lastName + " is not in the database");
				return;
			//Add an average mark of the student	
			case 3:
				
				System.out.println("Write the first name of the student:");
				firstName = scan.next();
				System.out.println("Write the last name of the student:");
				lastName = scan.next();
				studentInfo = new StudentRecord(firstName,lastName,0);// we don't consider sn
				studentTest = studRecOrdList.find(studentInfo);
				//before adding mark check that the student record is in our data 
				if(studentTest==null)
					System.out.println("Error! The student record of " + firstName+ " "+ lastName + " is not in the database");
				
				else {
					while(true) {
						System.out.println("Write the average mark of student:");
						String avgMarkScan =scan.next();
						
						try {
							float studentMark = Float.parseFloat(avgMarkScan);
							StudentRecord avgMark = (StudentRecord) studentTest.getData();
							avgMark.mark = studentMark;
							if(avgMark.mark>0) {
								System.out.println("The average mark was added!");	
								break;}
							else 
							{
							System.out.println( "\nHint:The average mark must be a positive number" );		
							}																		
						}
						//verify that the mark  is a number
						catch(NumberFormatException ex){
				    		System.out.println("Error! The average mark is a number");
			    	}
						
			}
				}
				return;
			//Print a student record	
			case 4:
          
				System.out.println("\nWrite the first name of the student:");
				firstName = scan.next();
				System.out.println("\nWrite the last name of the student:");
				lastName = scan.next();
				studentInfo = new StudentRecord(firstName,lastName,0);//student number is not considered
				studentTest = studRecOrdList.find(studentInfo);
				//check if the student record exists
				if(studentTest==null)
					System.out.println("Error! This student record does not exist!");
				else {
					System.out.println("The Student record:");
					StudentRecord readRecord = (StudentRecord) studentTest.getData();
					System.out.print(readRecord.toString());
				}
				
				return;
				//Printing all student records 
			case 5:
				
				ListNode emptyTest = studRecOrdList.getFirst();
				//test to see if there are an records
				if(emptyTest== null)
					System.out.println("Error There are no student records!");
				else
					System.out.print(studRecOrdList.toString());
				return;
				//Exiting the program
			case 6:
				
				System.exit(0);
				System.out.println("You chose to leave the program");
				return;
			default:
				System.out.println("\nChoose an option from 1 to 6");
			}
			
		}//verify it's an integer
		catch(NumberFormatException ex){
    		System.out.println("Error!\nChoose an option from 1 to 6");
    	}
	}
}
