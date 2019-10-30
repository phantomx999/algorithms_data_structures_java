//written by phantomx999

public class ContactNode{	//node class
	Contact contact;	//contact object info
	ContactNode next;	//next pointer in node
	
	//constructor
	public ContactNode(Contact c){	
		this.contact = c;
		this.next = null;
	}
	
	//constructor
	public ContactNode(){
		Contact c = new Contact();	//create dummy node at head of list
		c.setName("");	//set lowest char value when comparing strings, used in dummy node
		this.contact = c;	//dummy node helps with avoiding swapping first node with other nodes when using insertion sort
		this.next = null;   //sets up dummy node at head of list and next points to null
		  
	}
	
	//setter
	public void setContact(Contact c){
		contact = c;	
	}
	
	//getter
	public Contact getContact(){
		return contact;
	}
	
	//setter
	public void setNext(ContactNode node){
		next = node;
	}
	
	//getter
	public ContactNode getNext(){
		return next;
	}
	
	//print starting from a particular node to end of list
	public void printFrom(){
		display();
		if(getNext() != null){
			getNext().printFrom();
		}	
	}
	
	//print contact object info in node
	public void display(){
		Contact c = getContact();
		System.out.println(c.toString());
	}
}