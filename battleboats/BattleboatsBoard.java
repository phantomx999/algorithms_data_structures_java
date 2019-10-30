//written by phantomx999

import java.util.Scanner;

public class BattleboatsBoard{   //rock = -1, empty = 0, miss = 7, ships = 1-6, hit ship = 8, sunk ship = 9
	private static int row = 0;		//row size of board
	private static int col = 0;		//board column size
	private static int[][] board;	//real (debug) board
	private static int[][] userBoard;	//what user sees board for normal play
	private static int emptySpace = 0;	//empty location
	private static int rockTotal = 0;	//total # of rocks
	private static int rockCounter = 0;
	private static int rock = -1;		//-1 for rocks on board 
	private static int boatCounter = 0;	//labels boats from 1 to 6
	private static int boatNumber = 0;	//boat index for array of boats
	private static int boatTotal = 0;	//total number of boats in play
	private static Boat[] ships;		//array of boats to be created and placed
	private static int boatLength = 3;
	
	//constructor
	public BattleboatsBoard(int m, int n){
		this.row = m;
		this.col = n;
		this.board = new int[row][col];	//create real board size
		this.userBoard = new int[row][col];	//create user board size
		rockTotal = Math.min(m,n) - 2; //computes # of total rocks based on board size
		int rock = -1;
		this.emptySpace = 0;
	}
	
	//updates user board after shot
	public static void updateUserBoard(int row, int column){		
		if(board[row][column] == 0 || board[row][column] == 7){	//if shot hits nothing
			userBoard[row][column] = 7;    //mark as missed location
		}
		else if((board[row][column] >= 1 && board[row][column] <= 6) || board[row][column] == 8){	
			userBoard[row][column] = 8;	//if shot hits ship and doesnt sink or ship has been hitm mark as hit
		} 
		
		for(int i = 0; i < board.length; i++){	//if ship is sunk on debug board, user board also displays sunk ship
			for(int j = 0; j < board[i].length; j++){
				if(board[row][column] == 9){
					userBoard[row][column] = board[row][column];
				}
			}
		}
	}
	
	public static int[][] getUserBoard(){	//get user board
		return userBoard;
	}
	
	public static void setEmptyBoard(){	//initializes both boards to 0's
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				board[i][j] = emptySpace;
				userBoard[i][j] = emptySpace;
			}
		}
	}
	
	//check if rock can be placed in location
	public static boolean checkRock(int i, int j){
		if (board[i][j] != -1){
			return true;
		}
		else{	//if rock already placed at location
			System.out.println("Another rock was placed here already, try again!");
			return false;
		}
	}
	
	public static void setRock(int i, int j){	//places rock on both boards
		board[i][j] = rock;
		userBoard[i][j] = rock;
		System.out.println("Rock placed");
		rockCounter++;
	}
	
	public static int getRockTotal(){	//get total # of rocks
		return rockTotal;
	}
	
	public static void totalBoats(){	//assess # of boats to create per board size
		if(row == 3 || col == 3){
			boatTotal = 1;
		}
		else if((row > 3 && row <= 5) || (col > 3 && col <= 5)){
			boatTotal = 2;
		}
		else if((row > 5 && row <= 7) || (col > 5 && col <= 7)){
			boatTotal = 3;
		}
		else if((row > 7 && row <= 9) || (col > 7 && col <= 9)){
			boatTotal = 4;
		}
		else if((row > 9 && row <= 12) || (col > 9 && col <= 12)){
			boatTotal = 6;
		}
	}
	
	public static void createBoat(){ 	//boat creation
		ships = new Boat[boatTotal]; 	//allocate memory for array # of boats
		for(int i = 0; i < boatTotal; i++){
			ships[i] = new Boat(row, col);	//create a new boat and pass in board dimensions
			do{
				ships[i].setBoatDirection();	//sets direction of boat
				ships[i].setBoatCoordinates(row, col);	//sets boat location
			}while(!BattleboatsBoard.checkBoatPlacement(i));	//checks if boat can be placed in location
			
			BattleboatsBoard.placeBoat(i);	//places boat in location
		}
	}
	
	public static boolean checkBoatPlacement(int boatNumber){ //pass in each individual boat and check placement
		
		int mRow = ships[boatNumber].getBoatRow();	//get boat row
		int nCol = ships[boatNumber].getBoatCol();	//get boat col
		
		boolean horizontal = ships[boatNumber].getHorizontal();	//get boat direction
		boolean vertical = ships[boatNumber].getVertical();
		
		if (horizontal){	
			if (nCol >= (col - 2)){	//head of horizontal boat length 3 cant be placed in last 2 columns of board
				return false;		//or it will be out of bounds
			}
			for (int i = 0; i < boatLength; i++){	//if a rock or another boat is already placed here
				if (board[mRow][nCol+i] == -1 || (board[mRow][nCol+i] >= 1 && board[mRow][nCol+i] <= 6)){
					return false;
				}
			}		
		}
		else if (vertical){
			if (mRow >= (row - 2)){	//head of vertical boat length 3 cant be placed in last 2 rows of board
				return false;		//or it will be out of bounds
			}
			for (int i = 0; i < boatLength; i++){	//check for already placed rock or boat
				if (board[mRow+i][nCol] == -1 || (board[mRow+i][nCol] >= 1 && board[mRow+i][nCol] <= 6)){
					return false;
				}
			}
		} 	
		return true;	//if boat passes all conditions, then it can be placed at location
	}
	
	public static void placeBoat(int boatNumber){	//places each individuals boat
		
		int mRow = ships[boatNumber].getBoatRow();
		int nCol = ships[boatNumber].getBoatCol();
		boolean horizontal = ships[boatNumber].getHorizontal();
		boolean vertical = ships[boatNumber].getVertical();
		
		boatCounter++;	//labels each boat from 1 to 6 on debug board
		if (horizontal){
			for(int i = 0; i < boatLength; i++){	//places horizontal boat at head location and 2 columns down
				board[mRow][nCol+i] = boatCounter;	//labels boat number on board
			}
		}
		else{
			for(int i = 0; i < boatLength; i++){  //places vertical boat at head location and 2 rows down
				board[mRow+i][nCol] = boatCounter;
			}
		}
		
	}
	
	public static void boardSetup(int m, int n){	//initializes both boards
		BattleboatsBoard.setEmptyBoard();
	}
	
	public static void rockSetup(int i, int j){	//sets up rock placement
			BattleboatsBoard.setRock(i,j);
			rockTotal--;
	}
	
	public static void boatSetup(){	//finds total boats and creates them
		BattleboatsBoard.totalBoats();
		BattleboatsBoard.createBoat();
	}
	
	public static int getBoats(){	//get total # boats
		return boatTotal;
	}
	
	public static int[][] getBoard(){	//get debug board
		return board;
	}
	
	public static void displayDebugBoard(){		//displays debug board

		System.out.println("DEBUG DISPLAY BOARD");
	
		for(int i = 0; i < row; i++){
			System.out.printf("| ");
			for(int j = 0; j < col; j++){
				if(board[i][j] == -1){		//display R for rock
					System.out.printf("R ");
				}
				else if(board[i][j] == 7){
					System.out.printf("M ");	//M for miss
				}
				else if(board[i][j] == 8){
					System.out.printf("X ");	//X for hit
				}
				else if(board[i][j] == 9){
					System.out.printf("S ");	//S for sunk
				}
				else{
					System.out.printf(board[i][j] + " ");
				}
			}
			System.out.printf(" |    " + i);
			System.out.println();
		}
		System.out.println("--------------------------");
		System.out.printf("  ");
		for(int i = 0; i < col; i++){
			System.out.printf(i + " ");
		}
		System.out.println();
		System.out.println("     column  ");
		System.out.println();
		System.out.println("0 = ???    R = Rock   1 - 6 = Boats    M = Missed shot    X = hit!   S = Sunk");
		System.out.println();

	}
	
	public static void userDisplayBoard(){	//displays user/normal board
	
		System.out.println("USER DISPLAY BOARD");
		
		for(int i = 0; i < row; i++){
			System.out.printf("| ");
			for(int j = 0; j < col; j++){
				if(board[i][j] == -1){
					System.out.printf("R ");
				}
				else if (board[i][j] == 7){
					System.out.printf("M ");
				}
				else if(board[i][j] == 8){
					System.out.printf("X ");
				}
				else if(board[i][j] == 9){
					System.out.printf("S ");
				}
				else{
					System.out.printf(userBoard[i][j] + " ");
				}
			}
			System.out.printf(" |    " + i);
			System.out.println();
		}
		System.out.println("---------------------------");
		System.out.printf("  ");
		for(int i = 0; i < col; i++){
			System.out.printf(i + " ");
		}
		System.out.println();
		System.out.println("     column  ");
		System.out.println();
		System.out.println("0 = ???    R = Rock    M = Missed shot    X = hit!   S = Sunk");	
		System.out.println();

	}
	
	public static boolean checkSunk(int val){	//check if boat has been sunk
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if (board[i][j] == val){	//boat number (1 to 6) still shows up on board, not sunk
					return false;
				}
			}
		}
		return true;	//boat number no longer is on board, changed to all hits (value 8's), ship has been sunk
	}
	
	public static void updateSunk(int val){	//changes both boards to value 9 for sunk ship
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] == val){
					board[i][j] = 9;
					userBoard[i][j] = 9;
				}
			}
		}
	
	}
}