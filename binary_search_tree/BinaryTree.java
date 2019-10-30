//written by phantomx999

//BST
public class BinaryTree {	 
							
	private int size;		//keeps count of nodes in tree
	private TreeNode root;	//root node of BST

	//constructor, root node created
	public BinaryTree(TreeNode r){
		this.root = r;
		this.size = 1;
	}
	
	//constructor, no root node
	public BinaryTree(){
		this.root = null;
		this.size = 0;
	}

	//constructor, root node created and value set
	public BinaryTree(int s){
		TreeNode dummy = new TreeNode();
		this.root = dummy;
		this.root.setData(s);
		this.size = 1;
	}

	//returns number of nodes in tree
	public int size(){ 
		if (size == 0){	//empty tree
			System.out.println("Error, tree is empty --> zero size");
		}
		return size;
	}
	
	//adds a new node with passed in value to BST
	public void add(int value){
		if (root == null){	//when tree is empty
			TreeNode tr = new TreeNode(value);
			this.root = tr;	//create root node with value
			this.size++;	//size = 1
		}
		else{	//root node already present
			root = addHelper(root, value);	//call helper
			this.size++;	//add one more node to BST
		}
	}
	
	//getter 
	public TreeNode getRoot(){
		return root;
	}
	
	private TreeNode addHelper(TreeNode current, int value){	//called from add(), adds node to BST
		if (current == null){	//if current location is null
			current = new TreeNode(value);	//create new node with value here
			return current;		//return node
		}
		else if (current.getData() >= value){	//if value < than current node, go to left branch of BST
			current.setLeft(addHelper(current.getLeft(), value));	//keep going down left branch
		}
		else if (current.getData() < value){	//if value > than current node, go to right branch
			current.setRight(addHelper(current.getRight(), value));	//keep going down right branch
		}
		return current;	//return created node
	}
	
	//get indexed node value
	public int get(int i){	
		if(root == null){	//tree empty
			System.out.println("Error, tree is empty!");
			return -1;
		}
		else{	//tree not empty
			int[] ar = this.toArray();	//create sorted array from BST node values
			if(i >= 0 && i < ar.length)	//if index is in  array bounds
				return ar[i];	//return array value
			System.out.printf("Error, index out of bounds!  Return: 0\n");	//else index out of array bounds
			return 0;
		}
	}
	
	//finds indexed node in BST and sets new value in node
	public void set(int i, int value){
		if (root == null){	//empty BST
			System.out.println("Error, tree is empty, no values to change!");
		}
		else{	//not empty BST
			int[] ar = this.toArray();	//create sorted array from BST node values
					
			if(i >= 0 && i < ar.length){	//if index is in bounds
				int storeOldValue = ar[i];	//stores old value of node to be changed
				boolean done = false; 		//checks if nodes value was changed
				changeTreeValue(this.root, storeOldValue, value, done); //changes nodes value, helper call
				
				//tree has been altered so may not be BST anymore, have to create new BST with updated values
				int[] newArray = this.toArray();	//put BST values in sorted array
				BinaryTree tempTree = this.toBalancedBinaryTree(newArray);	//makes sorted array to balanced binary tree
				this.root = tempTree.root;	//sets original tree to new tree
			}
			else{	//index out of bounds
				System.out.println("Error, index is out of bounds!");
			}	
		}
	}
	
	//removes indexed node from tree
	public int remove(int i) {
    	int[] ar = this.toArray();	// BST values into sorted array
    	if (i > size() - 1 || i < 0) {	//index out of bounds
        	System.out.println("index over the range, return index");
        	return i;
    	}
    	int element = ar[i];	//stores value in index

    	TreeNode ptr = root;	//traverses BST
    	TreeNode parent = null;	//parent to child ptr 

		//ptr is pointing to node, have not found sought value
    	while (ptr != null && element != ptr.getData()) {
        	parent = ptr;	//move parent down to child
        	if (ptr.getData() <= element)	//go to right child node if value >
            	ptr = ptr.getRight();
        	else
            	ptr = ptr.getLeft();		//go to left child node if value <
    	}
    	if (ptr == null) {          // CASE 1: not found, tree unchanged
        	System.out.println("Nothing to remove, return index");
        	size = 0;
        	root = null;
        	return i;
    	} else if (ptr.getLeft() == null) {  // no left branch
        	if (ptr == root) {      // CASE 2: found at root
            	root = root.getRight();
        	} else {                 // CASE 3: found but not at root
            	if (ptr == parent.getLeft())
                	parent.setLeft(ptr.getRight());
            	else
                	parent.setRight(ptr.getRight());
        	}
    	} else {                     // CASE 4: found, and left branch exists
        	ptr.setData(ptr.getLeft().getRightMostData());
        	ptr.setLeft(ptr.getLeft().removeRightMost());
    	}

    	arrayChange();	//change array due to removal
    	return element;	//return value of node removed
	}

	//makes new array to reflect change
	private int[] arrayChange() {
    	int[] ar = this.toArray();
    	int[] temp = new int[size - 1];
    	int index = 0;
    	for (int i = 1; i < size; i++) {	//new array with 1 less index
        	temp[index++] = ar[i];
    	}
    	size--;	//reduce size since node removed
    	return temp;
	}
	
	//called from set(), helper method
	private void changeTreeValue(TreeNode current, int old, int val, boolean done){		//takes in current ptr, old node value	
		if (!done){	//have not changed value yet											//new value to update, tests if done
			if (current.getLeft() == null && current.getRight() == null){	//no child nodes
				if(current.getData() == old){	//if old value has been found
					current.setData(val);		//change old value to new value
					done = true;				//change occurred
				}
			}
			else if (current.getLeft() == null){	//only right child node
				if(current.getData() == old){	
					current.setData(val);
					done = true;
				}
				changeTreeValue(current.getRight(), old, val, done);	//continue to go right branch of BST
			}
			else if (current.getRight() == null){	//only left child node
				if(current.getData() == old){
					current.setData(val);
					done = true;;
				}
				changeTreeValue(current.getLeft(), old, val, done);	//continue to left branch
			}
			else{	//two child nodes
				if(current.getData() == old){	//test for old value
					current.setData(val);
					done = true;
				}
				changeTreeValue(current.getLeft(), old, val, done);	//go to left branch
				changeTreeValue(current.getRight(), old, val, done);	//go to right branch
			}
		}
	}
	
	//takes BST node values, make into sorted array
	public int[] toArray(){
		int[] arr = new int[size];	//array same size as BST
		TreeNode temp = root;	//temp traverses BST
		if(temp == null){	//tree empty
			return null;  
		}
		else if (size == 1) {	//only root node BST, set array[0]
        	arr[0] = root.getData();
        	return arr;
        }
		else{	//more than one node in BST
				int countIndex = 0;	//index for new array
				countIndex = traverseTreeStoreInt(temp, arr, countIndex);	//goes through BST and stores values in array, helper()
				
				
				int i = 0;	//for insertion sort
				int j = 0;	// ""
				int n = 0;	// ""
				for(i = 1; i < arr.length; i++){	//insertion sort algorithm used by Dovolis in class, credit wikipedia
				n = arr[i];
				for(j = i - 1; j >= 0 && n < arr[j]; j--){
					arr[j+1] = arr[j];
					arr[j] = n;
				}
			}	//array now sorted
		}
		return arr;	
	}
	
	private int traverseTreeStoreInt(TreeNode current, int[] a, int index){	//called from toArray()
		if(index == (size() - 1) || current == null){	//when array is full or ptr no longer at node
			return 0;
		}
		if(index < size){	//array still has < values than BST
			if(current.getLeft() != null){	//traverse left branch
				index = traverseTreeStoreInt(current.getLeft(), a, index);
			}
			if(current.getRight() != null){	//traverse right branch
				index = traverseTreeStoreInt(current.getRight(), a, index);	
			}
		}	
		a[index] = current.getData();	//current ptr node value stored in array
		return index + 1;	//go to next array index
	}
	
	//makes stringy binary tree (similar to linked list)
	public BinaryTree toBinaryTree(int[] array){	
		if (array.length == 0){	//array has no values and no length
			System.out.println("Array is length 0, has no values!");
			return null;
		}	
		boolean sorted = true;			//checks if array is sorted	
		for(int z = 0; z < array.length - 1; z++){	
	 		if(array[1+z] < array[z]){
	 			sorted = false;
	 		}
		}		
		while(!sorted){ 		//sorts array if necessary
			for(int i = 1; i < array.length; i++){
					int n = array[i];
					for(int j = i - 1; j >= 0 && n < array[j]; j--){
						array[j+1] = array[j];
						array[j] = n;
					}
			}
			sorted = true;	
		}	
		BinaryTree firstIndexRoot = new BinaryTree();	//create stringy tree
		firstIndexRoot.add(array[0]);	//set first, lowest value from array to BST
		for(int i = 1; i < array.length; i++){	//continues to add values from array into BST nodes
			firstIndexRoot.add(array[i]);			
		}
		return firstIndexRoot;
	}
	
	
	//creates a more balanced BST
	public BinaryTree toBalancedBinaryTree(int[] array){
		if (array.length == 0){	//array.length = 0, 
			System.out.println("Array is length 0, has no values!");
			return null;
		}	
		if (array.length == 1){	//array only has 1 value
			System.out.println("Array consists of only one value, no need to make balanced tree");
			TreeNode n = new TreeNode(array[0]);
			BinaryTree oneNodeT = new BinaryTree(n);
			return oneNodeT;
		}
		boolean sorted = true;	//check if array sorted
		for(int z = 0; z < array.length - 1; z++){
	 		if(array[1+z] < array[z]){
	 			sorted = false;
	 		}
		}		
		while(!sorted){	//sort array if necessary
			for(int i = 1; i < array.length; i++){
					int n = array[i];
					for(int j = i - 1; j >= 0 && n < array[j]; j--){
						array[j+1] = array[j];
						array[j] = n;
					}
			}
			sorted = true;	
		}	
		int start = 0;	//first index of array
		int end = array.length - 1;	//last index of array
		int length = array.length;	//length of array
		BinaryTree bTree = new BinaryTree();	//empty trees
		BinaryTree balancedTree = new BinaryTree();
		TreeNode parent = balancedTree.root;
												//pass in ptr, parent, original array, index 0, last index, number of created nodes, and size of array
		balancedTree.root = bTree.createBalancedTree(bTree.root, parent, array, start, end, 0, length);	//creates more balanced tree
		balancedTree.size = length;	//reset size if necessary
		
		return balancedTree;
	}
	
	//called from toBalancedBinaryTree(), creates a more balanced tree by breaking down array in smaller arrays and putting values into BST
	private TreeNode createBalancedTree(TreeNode current, TreeNode parent, int[] arr, int start, int end, int size, int maxSize){
		int mid = (start + end)/2;	//finds middle index of all created arrays
		if (start > end){	//done breaking down arrays
			return null;
		}
		if (start == end && arr[mid] <= parent.getData() && start != (maxSize - 1)){	//when all lower values in original array
			return current;																//have been already added to new tree
		} 																			//avoids infinity loop when start = end = 0
		
		if (current == null && size == 0){	//tree currently empty
			current = new TreeNode(arr[mid]);	//create root node
			size++;
			parent = current;					//set parent to root
			createBalancedTree(current, parent, arr, start, mid, size, maxSize);	//go toward left branch
			createBalancedTree(current, parent, arr, mid+1, end, size, maxSize);	//go toward right branch
		} 
		else if (size < maxSize && start < end){	//BST still doesnt have all values from array 
			TreeNode dummy = new TreeNode(arr[mid]);	//create new node with value
			if (arr[mid] <= parent.getData()){	//if less than parent, put new node on left branch
				parent.setLeft(dummy);
				size++;
				current = parent.getLeft();		//traverse down left branch
				parent = current;
			}
			else{	//if more than parent, put new node on right branch
				current.setRight(dummy);
				size++;
				current = parent.getRight();	//traverse down right branch
				parent = current;
			}				
			createBalancedTree(current, parent, arr, start, mid, size, maxSize);	//continue adding lower value nodes down left branch	
			createBalancedTree(current, parent, arr, mid + 1, end, size, maxSize);	// "" down right branch			
		}
		else if (size < maxSize && start == end && start == maxSize-1){	//when get to last index of original array, adds in right most node
				TreeNode dummy = new TreeNode(arr[start]);
				current.setRight(dummy);
				size++;
				current = parent.getRight();
				parent = current;
		}
		
		return parent;	
	}
	
	//finds highest level branch in tree
	public int depth(){
		if (root == null){	//tree empty
			return 0;
		}
		else if (root.getLeft() == null && root.getRight() == null){	//tree only has root node
			return 1;
		}
		else{
			int countDepth = 1;	//start at root node
			int depthLeft = 0;	//goes down left branches
			int depthRight = 0;	//goes down right branches
			if(root.getLeft() != null)	//go to left
				depthLeft = traverseTreeDepth(root.getLeft(), countDepth);
			if(root.getRight() != null)		//go to right
				depthRight = traverseTreeDepth(root.getRight(), countDepth);
			if (depthLeft >= depthRight){	//compares left and right branches, findest max number level
				return depthLeft;
			}
			else{
				return depthRight;
			}
		}
	}
	
	private int traverseTreeDepth(TreeNode current, int depth){ //called from depth(), goes down tree and counts level
		if(current.getLeft() == null && current.getRight() == null){	//no more child nodes, level++
			return depth + 1;
		}
	 	else if (current.getLeft() == null && current.getRight() != null){	//go down right branch, level++
		 	return traverseTreeDepth(current.getRight(), depth + 1);
		}
		else if (current.getRight() == null && current.getLeft() != null){	//go down left branch, level++
		 	return traverseTreeDepth(current.getLeft(), depth + 1);
		}
		else{	//finds the max of either left or right branch when two child nodes present
		return Math.max(traverseTreeDepth(current.getLeft(), depth + 1), traverseTreeDepth(current.getRight(), depth + 1));
		}
	} 
	
	
	//We did not write this printBinaryTree method, giving credit to Anurag Agarwal from Stack Overflow website,
	// as he said we could use his code below to print out tree for test cases.
	public void printBinaryTree(TreeNode root, int level){  
 		if(root==null)
        	return;
    	printBinaryTree(root.getRight(), level+1);
    	if(level!=0){
        	for(int i=0;i<level-1;i++)
            	System.out.print("|\t");
            	System.out.println("|-------"+root.getData());
    	}
    	else
        System.out.println(root.getData());
    	printBinaryTree(root.getLeft(), level+1);
	} 
	
	//sorts an array and prints it
	public void printArray(){
		if (this.getRoot() == null){	//empty tree
			return;
		}
		int[] ar = this.toArray();
		if (ar == null || ar.length < 0) {	//array empty
        System.out.println("No element at all");
		} 
   		else if (ar.length == 0) {
        System.out.println(ar[0]);
		}
		else{	//print array
			for(int i = 0; i < ar.length; i++){
				System.out.printf(ar[i] + " ");
			}
			System.out.println();
		}
	}

}