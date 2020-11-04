
public abstract class OrderedList extends List{
	protected abstract int compare(Object obj1, Object obj2);
	//compare returns zero if the two are equal, >0 if obj1 is greater than onj2,  <0 if obj2 is greater than obj1

//////////1.Find
	public ListNode find(Object newData) {

		ListNode tempNode= firstNode; //tempNode is the node being assessed temporary, used to go through the list 
		 while (tempNode!=null) {	 
			//go through the entire list
			 Object currentData=tempNode.getData();
			//if two Objects are the same it will return it
			if (compare(newData, currentData)==0)
			break;
			//otherwise it moves on to the next one
			else
				tempNode= tempNode.getNext();
		 }
		return tempNode;
	}
////////////2. Insert	
	public boolean insert(Object newData) { //method of inserting new objects
		//2.1 Check if list is empty or data already exists 
		ListNode searchDuplicate = find (newData);//verify if same object is already in the list using 'find' method
        ListNode newNode = new ListNode (newData, null);
       		//check if it's an empty list, if yes return true ->new object can be added
		if(firstNode==null) {
			firstNode= new ListNode(newData,null); //call ListNode to reference node as both first and last
			lastNode = new ListNode(newData,null); 
			return true;//true means no duplicate
		} 
		
		if(searchDuplicate!=null)//if object exists already in the list return false
			return false;
		
		//2.2 Find the right place to add the data
		else {
			ListNode tempNode = firstNode;
            //use do while instead of while do to go through loop at least once, even if there is only 1 element in the list		
			do {
				int orderAfterNode = compare( tempNode.getData(), newNode.getData());
			//2.2.1 If we are not at the end of the list	
				if(tempNode.getNext()!=null) {
					
					ListNode next = tempNode.getNext();
				int orderBeforeNode = compare( next.getData(), newNode.getData() );
							if(orderAfterNode>0) {
							newNode.setNext(tempNode);
							firstNode=newNode;
							return true;
							}
							else if(orderAfterNode<0 && orderBeforeNode>0){//means the object must be between those two: tempNode and tempNode.getNext()
	                        newNode.setNext( next );
	                        tempNode.setNext( newNode );
	                        return true;
	                        
	                    }
						else {//if correct order was not found yet move to another object 
							tempNode = tempNode.getNext(); 
							//verify if we are at the end of the list
	                        if(tempNode.getNext() == null && compare( newData,tempNode.getData() )>0)
	                        { tempNode.setNext(newNode);
	                            lastNode = tempNode;
	                            return true;
	                           
	                        }
	                                              
						}
						}
				//2.2.2 If we are at the end of the list
				else if (tempNode.getNext()==null) {
					if(orderAfterNode>0){
				    firstNode=newNode;
					newNode.setNext(tempNode);
					lastNode = tempNode;
					
					}
					else { 
						tempNode.setNext(newNode);
						newNode=lastNode;
						
					}
					return true;
				}
					
				
} while(tempNode.getNext()!=null );//verifies that there is a next node to go through 
		}
		
		return true;
	}
////////////////3.remove	
	public ListNode remove(Object removeData) {
    //3.1 Check if you can remove(if student record exists)
		//verify that record exists
		if(find(removeData)==null)//d
			return null;
		
		ListNode tempNode = firstNode;
		ListNode beforeTempNode = null;
	 //3.2 Go through loop to find record and remove/overwrite it
		do {
			int findRemoveData = compare(removeData,tempNode.getData());
			if(findRemoveData==0) {//found node that needs to be deleted
				if (beforeTempNode==null) //For data in first position
					firstNode = firstNode.getNext();//the first node received the data of the immediate node instead
				else
					beforeTempNode.setNext(tempNode.getNext());//the data after tempNode will move next to beforeTempNode data
				return tempNode;
			} else {
				beforeTempNode = tempNode;
				tempNode = tempNode.getNext();
			}
			
		}while(tempNode != null);//checks we're not at the last node	
		return null;
	}
	// Since we implement the OrderedList through inheritance let insertAtFront and insertAtBack to just print a message
	public void insertAtFront() {
		System.out.print("\nError! CANNOT insert object at the front");
	}
	
	public void insertAtBack() {
		System.out.print("\nError! CANNOT insert object at the back");
	}
}
