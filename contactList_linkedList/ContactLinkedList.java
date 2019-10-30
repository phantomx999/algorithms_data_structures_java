//written by phantomx999

import java.util.Scanner;
import java.io.PrintWriter;	
import java.io.File;		

public class ContactLinkedList{	//linked list for contacts class

	private int length;					//length of list
	private ContactNode ptr = null;		//points to current node after being modified
	private ContactNode root = null;	//headed node
	private int nodeCount = 0;			//counts number of nodes in list

	//constructor
	public ContactLinkedList(int n){	
		if(n < 0){	//checks for invalid, negative length
			n = 0;	
		}
		length = n;	
		ContactNode dummy = new ContactNode();	//creates dummy node at head of list
		ptr = null;								//dummy node at beginning of list points to null
		root = dummy;							//dummy node is the head of the list				
	}
	
	//constructor
	public ContactLinkedList(){
		length = 20;		//default, unspecified length
		ContactNode dummy = new ContactNode();	//creates dummy node at head of list
		ptr = null;
		root = dummy;		//dummy node at head of list		
	}
	
	//adds a node at the end of list that contains the contact... if list is empty, adds the first node containing contact
	public boolean add(Contact c){
		if (c.getName() == null){	//if the node contains no contact object
			System.out.println("Error, cannot add null contact");
			return false;
		}		
		
		if(nodeCount >= length){		//if list is full, cant add node
			System.out.println("Error, list is full");
			return false;
		}
		else{ 	//if list not full
			if(root.getNext() == null && nodeCount < length){	//list is empty
				ContactNode newNode1 = new ContactNode(c);	//creates added in node
				root.setNext(newNode1);	//sets head next pointing to node
				ptr = newNode1;			//ptr points to added node
				ptr.setNext(null);		//pointer next points to nothing
				nodeCount++;			//increase node count in list
				return true;
			}
			else{	//list is not empty and not full
				ContactNode newNode2 = new ContactNode(c);	//creates added in node
			 	while (ptr.getNext() != null && nodeCount < length){	//ptr goes to end of list, makes sure list isnt full
					ptr = ptr.getNext();	//traverse ptr in list
				}
				ptr.setNext(newNode2);	//previous node next points to added node
				
				ptr = newNode2;	//ptr points to added node
			
				nodeCount++;	//increase node count in list
				return true;				
			}
		}	
	}
	
	public Contact find(String name){	//finds string in contact name
		if(root.getNext() == null || ptr == null){	//if list is empty
			System.out.println("Error, no contacts in list, list is empty");
			return null;
		}
		Contact c;	//store found contact
		ContactNode temp = ptr;	//temp traverses nodes in list
		while (temp.getNext() != null){		//starts from right of ptr, goes to end of list
			if(temp.getNext().getContact().getName().contains(name)){	//checks if contact name contains string
				c = temp.getNext().getContact();	//stores this contact
				ptr = temp.getNext();	//sets the ptr to this node
				return c;				//returns this 'found' contact
			}
			temp = temp.getNext();		//traverse temp in list
		}
		
		if(temp.getNext() == null){	//if temp reaches end of list, must loop around and start at beginning of list
			temp = root;	//put temp at head of list
			while (temp.getNext() != ptr.getNext()){	//temp traverses nodes until it reaches original ptr node
				if(temp.getNext().getContact().getName().contains(name)){	//checks if contact name contains string
					c = temp.getNext().getContact();	//store contact
					ptr = temp.getNext();				//set ptr to this node
					return c;							//return this contact
				}
				temp = temp.getNext();					
			}	
		}
		
		//if string not found in any contacts of list
		System.out.printf("Contact name " + name + " not found in list, return.... ");
		return null;
	}
	
	public Contact remove(){	//removes ptr node containing contact from list, shifts ptr to the next node
		if (root.getNext() == null || ptr == null){	//if list is empty
			System.out.println("Error, list is empty, no contacts in list!");
			return null;
		}
		Contact c = ptr.getContact();	//stores current contanct
		
		if(nodeCount == 1){		//if only one node in list
			root.setNext(null);	//head of list next to null
			ptr = null;			//ptr points to nothing
			nodeCount--;		//remove node from list
			return c;			//return contact in this node
		}
		
		//...if more than 1 node in list
		ContactNode previous = root;		//set a node at head of list
		while (previous.getNext() != ptr){	//traverse node until it reaches last node before ptr node
			previous = previous.getNext();
		}
		previous.setNext(ptr.getNext());	//last node before ptr node points to the node after ptr node
		ptr.setNext(null);					//ptr node points to nothing
		if (previous.getNext() != null){
			ptr = previous.getNext();			//ptr now points to this next node too
		}
		else{
			ptr = root.getNext();	
		}
		nodeCount--;						//decrease node size in list
		
		return c;
	}
	
	public Contact getCurrent(){	//displays current contact's name that ptr is pointing to
		if(root.getNext() == null || ptr == null){	//list empty
			System.out.println("Error, list is empty, no contacts in list!");
			return null;
		}
		return ptr.getContact();	//list not empty, return ptr contact
	}
	
	public Contact get(int i){	//get particular indexed node contact in list
		if(root.getNext() == null || ptr == null){	//list empty
			System.out.println("Error, list is empty, no contacts in list!");
			return null;
		}
		if(i < 0){	//invalid, negative index
			System.out.printf("Error, no negative indexes in list, return... ");
			return null;
		}
		
		int nodeIndex = 0;							//finds particular node index
		ContactNode temp = root.getNext();			//temp traverses list
		while (temp != null && nodeIndex < length){	//temp traverses until end of list and index is within length of list
			if (nodeIndex == i){					//found node index
				Contact c = temp.getContact();		//store this contact
				ptr = temp;							//set ptr to this node
				return c;							//return this contact
			}
			nodeIndex++;							//go to next node index
			temp = temp.getNext();					//traverse list
		}
		System.out.printf("Error, index value " + i + " not in list, return... ");	//index value not in list
		return null;		
	}
	
	public Contact next(){	//goes to next node in list and returns this contact
		if(root.getNext() == null || ptr == null){	//list empty
			System.out.println("Error, list is empty, no contacts in list!");
			return null;
		}
		ContactNode temp = ptr.getNext();	//traverses list
		if(temp == null){	//if at end of list, loops around to beginning of list
			temp = root.getNext();			//starts at first node
			Contact c = temp.getContact();	//stores this contact
			ptr = temp;						//set ptr to this node
			return c;						//returns this contact
		}
		else{								//if node not at end of list
			Contact c = temp.getContact();	//store this contact
			ptr = ptr.getNext();			//point to current node
			return c;				
		}
	
	}
	
	public Contact previous(){	//goes to previous node in list and returns contact
		if (root.getNext() == null || ptr == null){	//empty list
			System.out.println("Error, list is empty, no contacts in list!");
			return null;	
		}	
		ContactNode temp = root.getNext();	//temp starts at beginning of list
		if (temp == ptr){	//ptr already at beginning of list, must loop around until it reaches end of list 
			while (temp.getNext() != null){	//temp goes to end of list
				temp = temp.getNext();	
			}
			Contact c = temp.getContact();	//store node contact
			ptr = temp;						//ptr set to last node
			return c;						//return contact
		}
		else{								//when ptr not at beginning of list
			while (temp.getNext() != ptr){		//temp traverses list until it reaches last node before ptr node
				temp = temp.getNext();
			}
			Contact c = temp.getContact();	
			ptr = temp;
			return c;
		}
	}
	
	public void insertionSort(){	//use insertion sort to sort contacts alphabetically by name
	
		if(root.getNext() == null || ptr == null){	//empty list
			System.out.println("Error, list is empty, cannot sort list!");
		}
		else if (nodeCount == 1){	//list doesnt need to be sorted, only 1 contact
			System.out.println("Only 1 contact in list, nothing to sort");
		}
		else{	//2 or more contacts, must be sorted
			ContactNode originalPtrLocation = ptr; 	//stores original ptr node
			ptr = root.getNext();					//sets ptr to node after headed node
			ContactNode previous = root;			//sets previous to headed node, points to node right before ptr node
			ContactNode beforePrevious = root;		//points to head too, eventually points to node right before previous node
			
			while(ptr != null){	//traverses until the end of list
			
				//originally sets up previous to point to first node after head, has ptr traverse until it gets to... 
				//... the end of list, also alphabetically compares each contact name between ptr node with the previous node contact 
				while(previous == root ||  ptr.getNext() != null && ptr.getContact().getName().compareTo(previous.getContact().getName()) >= 0){
					beforePrevious = previous;	//moves before previous to next node down list
					previous = ptr;				//previous moves to next node
					ptr = ptr.getNext();		//ptr moves to next node
				}
				ContactNode oldPtr = ptr;		//stores original ptr node when a node swap occurs
				
				//when two nodes must be swapped to be sorted, continues down to beginning of list until nodes need not be swapped
				while(ptr.getContact().getName().compareTo(previous.getContact().getName()) < 0){	//if reaches dummy node, swapping has ended
					previous.setNext(ptr.getNext());	//node before ptr points to node after ptr
					ptr.setNext(previous);				//ptr node now points to node before it
					beforePrevious.setNext(ptr);		//node before previous node now points to ptr node
					
					ContactNode temp = previous;
					previous = beforePrevious;			//move back previous node to node before it
					beforePrevious = root;				//set beforePrevious to head of list
					
					//traverse beforePrevious until it reaches node before previous node and previous node cant be the head of list
					while(beforePrevious.getNext() != previous && previous != root){		
						beforePrevious = beforePrevious.getNext();
					}
				}
				ptr = oldPtr;			//ptr now points to node it was pointing to before swapping
				previous = ptr;			//previous moves to next node in list
				ptr = ptr.getNext();	//ptr moves to next node in list after previous node
			}
			ptr = originalPtrLocation;	//finally, moves ptr back to original node before insertion sort was called
		}
	}

	public boolean write(String fileName){	//writes list to a txt file
		if (root.getNext() == null || ptr == null){	//list empty
			System.out.println("Error, list is empty, cannot write list to file!");
			return false;	
		}
		PrintWriter p = null;	//accesses txt file
		try{
			p = new PrintWriter(new File(fileName));	//successfully accesses file
		} catch (Exception e){	//if file access unsuccessful
			return false;
		}
		ContactNode temp = root.getNext();	//starts at first node after the head of list
		for(int x = 0; x < this.length; x++){	//traverses list
			if(temp != null){				//if temp doesnt reach end of list
				p.println(temp.getContact().toString());	//print contact info in node to file
				temp = temp.getNext();						//temp moves to next node
			}
		}
		p.close();	//closes access to file
		return true;	
	}
	
	public boolean read(String fileName){	//reads file contacts to list
		if (length < 1){	//length less than 1, cant read in contacts
			System.out.println("Error, list length less than 1, cant read in contacts from file");
			return false;
		}
		if(nodeCount == length){	//full list, cant read in more contacts
			System.out.println("List is full, cannot read in new contacts ");
			return false;
		}
		
		Scanner s = null;	//scans the file
		try{
			s = new Scanner(new File(fileName));	//successfully scans file
		} catch (Exception e){	//catches exception when cant scan file
			return false;
		}
		
		while(s.hasNext() && nodeCount < length){	//checks for more characters on line and if list is still not full
			String nextName = s.nextLine();		//acquires contact name
		 	long nextPhone = s.nextLong();		//gets contact phone number
		 	s.nextLine();						//clears out line buffer
			String nextAddress = s.nextLine();	//gets contact address
			String nextComments = s.nextLine();	//gets contact comments
			
			Contact nextCon = new Contact(nextName, nextPhone, nextAddress, nextComments);	//creates this contact
			this.add(nextCon);	//adds this contact to list	
		}
		return true;	//notifies that contacts were added successfully
	}
	
	
	public void printLinkedList(){	//prints entire list
		if (root.getNext() == null || ptr == null){	//list empty
			System.out.println("Error, list is empty, no contacts in list!");	
		}
		else{	//list not empty
			ContactNode temp = root.getNext();	//starts at first real node
			System.out.println(temp.getContact().toString());	//prints out node contact info
			while(temp.getNext()!= null){	//traverses list til end of list
				System.out.println(temp.getNext().getContact().toString()); //prints out next node contact infor
				temp = temp.getNext();
			}
		}
	}



	public static void main(String[] args){		//includes every test case I could think of...
		
		//first check all methods for sunny day scenarios....
		ContactLinkedList contactList =  new ContactLinkedList(4);	//ordinary length size list
		
		Contact contact1 = new Contact("Jimmy Brown", 9988777, "1345 Hill St", "bus driver");
		Contact contact2 = new Contact("Mike Tyson", 6655777, "123457 Boxing Ave", "world champ");
		Contact contact3 = new Contact("Jimi Hendrix", 58688, "123 Cemetery Drive", "Guitar God");
		Contact contact4 = new Contact("Jack Sparrow", 34534, "456 Pirates Cove", "Professional 'ladies' man");
	

		ContactNode node1 = new ContactNode(contact1);
		ContactNode node2 = new ContactNode(contact2);
		ContactNode node3 = new ContactNode(contact3);
		ContactNode node4 = new ContactNode(contact4);
		ContactNode node5 = new ContactNode(contact2);
		
		System.out.println("ptr currently points to --> " + contactList.getCurrent());
		System.out.println();
		System.out.println();		
		System.out.println();
		System.out.println("Next contact: " + contactList.next());
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Number of contacts in list: " + contactList.nodeCount);
		System.out.println();

		
		System.out.println("Contact added? " + contactList.add(node1.getContact()));
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);
		System.out.println("Contact added? " + contactList.add(node2.getContact()));
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);
		System.out.println("Contact added? " + contactList.add(node3.getContact()));
		System.out.println("ptr --> " + contactList.getCurrent().toString());				
		System.out.println("Number of contacts in list: " + contactList.nodeCount);
		System.out.println("Contact added? " + contactList.add(node4.getContact()));
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());		
		System.out.println("Number of contacts in list: " + contactList.nodeCount);
		System.out.println("Contact added? " + contactList.add(node5.getContact()));
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);

		

		//check printFrom method
		System.out.println();
		System.out.println("Print list.... (program line 375) ");
		node1.printFrom();
		node2.printFrom();
		node3.printFrom();
		node4.printFrom();
		System.out.println();
		System.out.println();
		
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();
		System.out.println();
		System.out.println("Print list... (Program line 389) ");		
		contactList.printLinkedList();
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);

		System.out.println();
		System.out.println();

		System.out.println();
		System.out.println();
		System.out.println();


		System.out.println("Look for string \"mi He\" in list...");
		System.out.println("Found contact: " + contactList.find("mi He"));
		System.out.println();	
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);
	
		System.out.println();
		System.out.println();

		System.out.println(contactList.find("68hf zz 8g"));
		System.out.println();

		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);
						
		System.out.println();
		System.out.println("Print list again... (Program line 419) " );
		
		contactList.printLinkedList();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Get Current: " + contactList.getCurrent().toString());
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);		
		System.out.println();
		System.out.println();
		System.out.println("Contact removed: " + contactList.remove());
		System.out.println();		
		System.out.println("Get Current: " + contactList.getCurrent().toString());
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());		
		System.out.println("Number of contacts in list: " + contactList.nodeCount);		
				
		System.out.println();
		System.out.println();
		System.out.println("Print list after removing contact... (Program line 439) ");
		contactList.printLinkedList();
		System.out.println();
		System.out.println();		
		System.out.println();
		System.out.println("Get current: " + contactList.getCurrent().toString());
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);		
		
		System.out.println();		
		System.out.println();
		System.out.println("Get index -1: ");	
		System.out.println(contactList.get(-1));	
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());		
		System.out.println();		
		System.out.println();
		System.out.println("Get index 0: " + contactList.get(0).toString());			
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());		
		System.out.println();
		System.out.println("Get index 2: " + contactList.get(2).toString());	
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();
		System.out.println(contactList.get(3));	
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());	
		System.out.println();
		System.out.println(contactList.get(-1));	
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());		
		System.out.println();
		System.out.println();
		System.out.println("Print current list...(program line 473) ");
		contactList.printLinkedList();
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());		
		System.out.println();
		System.out.println();
		System.out.println("Next contact: " + contactList.next().toString());
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());		
		System.out.println();
		System.out.println("Next contact: " + contactList.next().toString());
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());		
		System.out.println();
		System.out.println("Next contact: " + contactList.next().toString());
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();
		System.out.println();
		System.out.println("Next contact: " + contactList.next().toString());
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();
		System.out.println("Print current list....(program line 496) ");
		contactList.printLinkedList();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Previous contact: " + contactList.previous().toString());
		System.out.println();
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Previous contact: " + contactList.previous().toString());
		System.out.println();
		System.out.println();
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("List before insertion sort... (program line 516) ");
		contactList.printLinkedList();
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();

		System.out.println();
		System.out.println();
		contactList.insertionSort();
		System.out.println("List after insertion sort... (program line 528) ");
		contactList.printLinkedList();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();			
		System.out.println("ptr --> " + contactList.getCurrent().toString());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Previous contact: " + contactList.previous().toString());
		System.out.println();
		System.out.println();
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);		
		System.out.println();
		System.out.println();		
		System.out.println();
		System.out.println();
		System.out.println("Contact removed: " + contactList.remove().toString());
		System.out.println();
		System.out.println();
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + contactList.nodeCount);		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Print out list... (program line 556) ");
		contactList.printLinkedList();
		System.out.println();
		System.out.println();
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Write to text file.....(check \"project2writeTest.txt\" for file writing)");
		contactList.write("project2writeTest.txt");
		System.out.println("ptr --> " + contactList.ptr.getContact().toString());		
		System.out.println("Number of contacts in list: " + contactList.nodeCount);		
		System.out.println();
		System.out.println();
		System.out.println("Read to text file.....(check \"project2readTest.txt\" for file reading)");		
		System.out.println("After read and write: ");
		contactList.read("project2readTest.txt");		
		contactList.printLinkedList();
		System.out.println();
		System.out.println();
		System.out.println();		
		System.out.println("Get Current: " + contactList.getCurrent().toString());		
		System.out.println("Number of contacts in list: " + contactList.nodeCount);			
		System.out.printf("\n\n\nptr --> " + contactList.ptr.getContact().toString() + "\n\n");
		System.out.printf("\n\n\n\nPrint after insertion sort... (program line 582)\n");
		contactList.insertionSort();
		contactList.printLinkedList();
		System.out.println();
		System.out.println();
		System.out.println();		
		System.out.println("Get Current: " + contactList.getCurrent().toString());		
		System.out.println("Number of contacts in list: " + contactList.nodeCount);				
		System.out.printf("\n\n\nptr --> " + contactList.ptr.getContact().toString() + "\n\n");


//////now check for rainy day scenarios/////

		//ordinary length value list, but list is empty..
		System.out.printf("\n\n\nNow try methods with empty list with length > 0..\n");
		ContactLinkedList emptyList = new ContactLinkedList(3);
		System.out.println("Get Current: " + emptyList.getCurrent());				
		System.out.println("ptr --> " + emptyList.ptr);
		System.out.println("Number of contacts in list: " + emptyList.nodeCount);
		System.out.println("Find contact: " + emptyList.find(""));
		System.out.println("Get Current: " + emptyList.getCurrent());				
		System.out.println("ptr --> " + emptyList.ptr);
		System.out.println("Number of contacts in list: " + emptyList.nodeCount);
		System.out.println("Contact removed: " + emptyList.remove()); 
		System.out.println("Get Current: " + emptyList.getCurrent());				
		System.out.println("ptr --> " + emptyList.ptr);
		System.out.println("Number of contacts in list: " + emptyList.nodeCount);
		System.out.println("Get index -1: " + emptyList.get(-1));
		System.out.println("Get index 0: " + emptyList.get(0));
		System.out.println("Get index 1: " + emptyList.get(1));
		System.out.println("Get index 2: " + emptyList.get(2));
		System.out.println("Get index 3: " + emptyList.get(3));
		System.out.println("Get Current: " + emptyList.getCurrent());				
		System.out.println("ptr --> " + emptyList.ptr);
		System.out.println("Number of contacts in list: " + emptyList.nodeCount);		
		System.out.println("Next Contact " + emptyList.next());
		System.out.println("Get Current: " + emptyList.getCurrent());				
		System.out.println("ptr --> " + emptyList.ptr);
		System.out.println("Number of contacts in list: " + emptyList.nodeCount);
		System.out.println("Previous Contact " + emptyList.previous());
		System.out.println("Get Current: " + emptyList.getCurrent());				
		System.out.println("ptr --> " + emptyList.ptr);
		System.out.println("Number of contacts in list: " + emptyList.nodeCount);
		System.out.println("Try insertion sort on empty list: ");
		emptyList.insertionSort();
		System.out.println("Try printing empty list: ");
		emptyList.printLinkedList();
		System.out.println("Try writing from empty list: ");
		emptyList.write("project3writeTest.txt");
		System.out.println("Try reading into empty list... \"(Check project3readTest.txt)\" ");
		emptyList.read("project3readTest.txt");		
		System.out.println("ptr --> " + emptyList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + emptyList.nodeCount);
		System.out.println("\nNow try printing list after successfully reading in contacts from file: ");
		emptyList.printLinkedList();
		System.out.println("\n\nTry insertion sort on list: ");
		emptyList.insertionSort();
		emptyList.printLinkedList();

		
		
		System.out.println();
		
		//lists less than 1 length, test rainy day scenarios...
		System.out.printf("\n\n\nNow try methods with list that is <= 0 size length..\n");
		ContactLinkedList zeroList = new ContactLinkedList(-1);  //constructor forces invalid, negative lengths to equal length 0
		System.out.println("Get Current: " + zeroList.getCurrent());				
		System.out.println("ptr --> " + zeroList.ptr);
		System.out.println("Number of contacts in list: " + zeroList.nodeCount);
		System.out.println("Add contact? " + zeroList.add(node1.getContact()));		
		System.out.println("Find contact: " + zeroList.find(""));
		System.out.println("Get Current: " + zeroList.getCurrent());				
		System.out.println("ptr --> " + zeroList.ptr);
		System.out.println("Number of contacts in list: " + zeroList.nodeCount);
		System.out.println("Contact removed: " + zeroList.remove()); 
		System.out.println("Get Current: " + zeroList.getCurrent());				
		System.out.println("ptr --> " + zeroList.ptr);
		System.out.println("Number of contacts in list: " + zeroList.nodeCount);
		System.out.println("Get index -1: " + zeroList.get(-1));
		System.out.println("Get index 0: " + zeroList.get(0));
		System.out.println("Get index 1: " + zeroList.get(1));
		System.out.println("Get index 2: " + zeroList.get(2));
		System.out.println("Get index 3: " + zeroList.get(3));
		System.out.println("Get Current: " + zeroList.getCurrent());				
		System.out.println("ptr --> " + zeroList.ptr);
		System.out.println("Number of contacts in list: " + zeroList.nodeCount);		
		System.out.println("Next Contact " + zeroList.next());
		System.out.println("Get Current: " + zeroList.getCurrent());				
		System.out.println("ptr --> " + zeroList.ptr);
		System.out.println("Number of contacts in list: " + zeroList.nodeCount);
		System.out.println("Previous Contact " + zeroList.previous());
		System.out.println("Get Current: " + zeroList.getCurrent());				
		System.out.println("ptr --> " + zeroList.ptr);
		System.out.println("Number of contacts in list: " + zeroList.nodeCount);
		System.out.println("Try insertion sort on empty list: ");
		zeroList.insertionSort();
		System.out.println("Try printing list: ");
		zeroList.printLinkedList();
		System.out.println("Try writing list: ");
		zeroList.write("project2writeTest.txt");
		System.out.println("Try reading list: ");
		zeroList.read("project2readTest.txt");		
		System.out.println("ptr --> " + zeroList.ptr);
		System.out.println("Number of contacts in list: " + zeroList.nodeCount);
		
		System.out.println();
		
		//list length one, test boundary scenarios such as remove() on one node and then list becomes empty...etc.
		System.out.printf("\n\n\nNow try methods with list length that is = 1, " +
		"test remove(), write(), read(), and other methods with 1 contact and full list scenarios..\n");
		ContactLinkedList oneList = new ContactLinkedList(1); 
		Contact c1 = new Contact("Sue Mary", 222, "13 blue st", "friend");
  		Contact c2 = new Contact("Joe Smith", 321, "145 red St", "neighbor");
		ContactNode n1 = new ContactNode(c1);
		ContactNode n2 = new ContactNode(c2);
		System.out.println();
		System.out.println("Get Current: " + oneList.getCurrent());				
		System.out.println("ptr --> " + oneList.ptr);
		System.out.println("Number of contacts in list: " + oneList.nodeCount);
		System.out.println("Contact added? " + oneList.add(n1.getContact()));	
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);
		System.out.println("Contact added? " + oneList.add(n2.getContact()));		
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);		
		System.out.println("Find contact: " + oneList.find("ry"));
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);
		System.out.println(oneList.find("tttt"));
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);
		System.out.println("Contact removed: " + oneList.remove().toString());
		System.out.println("Get Current: " + oneList.getCurrent());				
		System.out.println("ptr --> " + oneList.ptr);
		System.out.println("Number of contacts in list: " + oneList.nodeCount);				
		System.out.println("Contact added: " + oneList.add(n2.getContact()));						 
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);
		System.out.println("Get index -1: ");
		System.out.println(oneList.get(-1));
		System.out.println("Get index 0: " + oneList.get(0).toString());
		System.out.println(oneList.get(1));
		System.out.println(oneList.get(2));
		System.out.println(oneList.get(3));
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);		
		System.out.println("Next Contact " + oneList.next().toString());
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);
		System.out.println("Previous Contact: " + oneList.previous().toString());
		System.out.println("Get Current: " + oneList.getCurrent().toString());				
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);
		System.out.println("Try insertion sort on list: ");
		oneList.insertionSort();
		System.out.println("Try printing list: ");
		oneList.printLinkedList();
		System.out.println("Try writing on list..  Check \"project3writeText.txt\" ");
		oneList.write("project3writeTest.txt");
		System.out.println("Try reading on list.. Check \"project3readText.txt\" ");
		oneList.read("project3readTest.txt");		
		System.out.println("ptr --> " + oneList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + oneList.nodeCount);

		System.out.println();
		
		//more testing...
		System.out.printf("\n\n\nNow try methods with list length that is = 2...\n");
		ContactLinkedList twoList = new ContactLinkedList(2); 
		Contact c3 = new Contact("J C", 867, "17 blue rd", "son");
  		Contact c4 = new Contact("T J", 399, "15 red rd", "dad");
		ContactNode n3 = new ContactNode(c3);
		ContactNode n4 = new ContactNode(c4);
		System.out.println();
		System.out.println("Get Current: " + twoList.getCurrent());				
		System.out.println("ptr --> " + twoList.ptr);
		System.out.println("Number of contacts in list: " + twoList.nodeCount);
		System.out.println("Contact added? " + twoList.add(n3.getContact()));	
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);
		System.out.println("Contact added? " + twoList.add(n4.getContact()));		
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);	
		System.out.println("Find contact: " + twoList.find("T J"));
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());	
		System.out.println("Number of contacts in list: " + twoList.nodeCount);			
		System.out.println("Find contact: " + twoList.find(" C"));
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);
		System.out.println(oneList.find("T M"));
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);
		System.out.println("Contact removed: " + twoList.remove().toString());
		System.out.println("Get Current: " + twoList.getCurrent());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);				
		System.out.println("Contact added? " + twoList.add(n1.getContact()));						 
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);
		System.out.println("Get index -1: ");
		System.out.println(twoList.get(-1));
		System.out.println("Get index 0: " + twoList.get(0).toString());
		System.out.println("Get index 1: " + twoList.get(1));
		System.out.println(twoList.get(2));
		System.out.println(twoList.get(3));
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);		
		System.out.println("Next Contact " + twoList.next().toString());
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);
		System.out.println("Previous Contact " + twoList.previous().toString());
		System.out.println("Get Current: " + twoList.getCurrent().toString());				
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);
		System.out.println("Try printing list: ");
		twoList.printLinkedList();		
		System.out.println("Try insertion sort on list: ");
		twoList.insertionSort();
		System.out.println("Try printing list: ");
		twoList.printLinkedList();
		System.out.println("Try writing on list..  Check \"project4writeText.txt\" ");
		twoList.write("project4writeTest.txt");
		System.out.println("Try reading on list.. Check \"project4readText.txt\" ");
		twoList.read("project4readTest.txt");		
		System.out.println("ptr --> " + twoList.ptr.getContact().toString());
		System.out.println("Number of contacts in list: " + twoList.nodeCount);

		//test for null contacts and null data nodes to avoid adding them to list
		System.out.println("\n\n\n\n\n\nTry to add null contact to list..."); 
		ContactLinkedList badList = new ContactLinkedList(3);
		Contact bad = new Contact();
		ContactNode badNode = new ContactNode(bad);
		System.out.println("Contact added? " + badList.add(bad));
		System.out.println("Get Current: " + badList.getCurrent());
		System.out.println("Contact added? " + badList.add(c1));
		System.out.println("Get Current: " + badList.getCurrent().toString());
		System.out.println("Contact added? " + badList.add(c2));
		System.out.println("Get Current: " + badList.getCurrent().toString());		
		badList.add(badNode.getContact());
		System.out.println("Get Current: " + badList.getCurrent().toString());				
		System.out.println("Contact added? " + badList.add(c3));
		System.out.println("Get Current: " + badList.getCurrent().toString());
		badList.add(bad);
		System.out.println("Get Current: " + badList.getCurrent().toString());		
		System.out.println("\n\nPrint list: ");
		badList.printLinkedList();
		
		//checks if insertion sort can handle duplicate contacts
		System.out.println("\n\n\n\nTry sorting a new list with duplicate contacts\n");
		ContactLinkedList dupList = new ContactLinkedList(3);
		dupList.add(c1);
		dupList.add(c1);
		dupList.add(c2);
		System.out.println("Print list before insertion sort: ");
		dupList.printLinkedList();
		dupList.insertionSort();
		System.out.println("\n\nPrint list after insertion sort: ");
		dupList.printLinkedList();
		
		System.out.println("\n\nptr --> " + dupList.ptr.getContact().toString());
		
		//check if removal of last contact, ptr wraps around to beginning of list
		System.out.println("\n\nPrevious Contact " + dupList.previous().toString());
		System.out.println("Get Current: " + dupList.getCurrent().toString());		
		System.out.println("Remove Contact: " + dupList.remove().toString());
		System.out.println("Print list after removal: ");
		dupList.printLinkedList();
		System.out.println("Get Current: " + dupList.getCurrent().toString());		


		System.out.println("\n\nEnd of program.....boomshakalaka!");

	}

}