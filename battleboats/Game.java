//written by phantomx999

import java.util.Scanner;

public class Game{
	
	private static boolean debug = false;	//for debug mode
	private static int boatsRemaining = 0;	//keeps track of how many boats not sunk
	private static int turnCounter = 1;		//tracks turn number
	private static int cannonShot = 0;		//tracks number of shots
	
	//checks location where user shot at
	public static void checkShot(int mIndex, int nIndex){
		System.out.println();
		int[][] copyBoard = BattleboatsBoard.getBoard();	//points to the Battleboats debug board
		cannonShot++;	//increase shot counter

		//checks if shot was out of bounds
		if (mIndex < 0 || mIndex >= copyBoard.length || nIndex < 0 || nIndex >= copyBoard[0].length){
			System.out.println("Penalty!  Out of bounds!  Skip turn!");
			turnCounter++;   //gives extra turn penalty
		}
		else{	//all other shots not out of bounds
			int value = 0;
			value = copyBoard[mIndex][nIndex]; //gets the value of Battleboats board at location of shot
			switch (value){
				case -1: System.out.println("Hit Rock, Penalty");	//if user hit rock, give penalty
			     		 System.out.println("Skip next turn.");
			     		 turnCounter++;
				 		 break;
				case 0:  System.out.println("Miss!"); 	//user fired at open space
				  		 copyBoard[mIndex][nIndex] = 7;	//change debug board to 7, mark as a missed but fired at location
				 		 BattleboatsBoard.updateUserBoard(mIndex, nIndex);	//change user board so they see missed location
						 break;
				case 1: 		//when user hits ship, numbered 1-6 depending on board size
				case 2:
				case 3:
				case 4:
				case 5: 
				case 6:	int x = 8;	
						copyBoard[mIndex][nIndex] = x;	//change debug board from ship location to hit location
						if (BattleboatsBoard.checkSunk(value)){	//check if ship has sunk
							System.out.println("Sunk!");
							BattleboatsBoard.updateSunk(x);	//change debug and user board to sunk ship
							boatsRemaining--;	//subtract sunk boat from game
						}
						else{	//if boat has not been sunk yet
							System.out.println("Hit!");	
						}
						break;
				case 7:				//if user shoots at already shot at spot
				case 8:
				case 9:	System.out.println("Penalty!  Already shot at this location.  Skip next turn!");
						turnCounter++;
						break;
				//default: System.out.println("Out of bounds!  Penalty!");	
		 		//	 	 turnCounter++;
			}
			BattleboatsBoard.updateUserBoard(mIndex, nIndex); //updates user board, changes shot at location
		}
		System.out.println();

	}	
	
	//play in debug mode
	public static void setDebug(){
		Scanner d = new Scanner(System.in);
		int x = 0;
		System.out.println("Play on debug mode?  0 = no, 1 = yes");
		x = d.nextInt();
		while (x != 0 && x != 1){
			System.out.println("0 = no, 1 = yes");
			x = d.nextInt();
		}
		
		if (x == 0){
			debug = false;
		}
		else{
			debug = true;
		}
	}

	public static void main(String[] args){
	
	 	int row = 0;		//user input row size
		int column = 0;		//column size
	 	int xRock = 0;		//x (column) coordinate for rock placement
	 	int yRock = 0;		//y coordinate for rock
	 	int rowShot = 0;	//y coordinate for shot location 
	 	int columnShot = 0;	//x coordinate for shot 
	
		Scanner s = new Scanner(System.in);
	
		System.out.println("It's time to play BattleBoats!!!!!");
		System.out.println("If you win, I will give you a prize.  But if you lose, you have to make me a sandwich! ");
		System.out.println("LET'S PLAY");

		System.out.printf("\n\n\n\n");
	
	
		//asks user to play in debug mode
		setDebug();
	
		//sets up row and column size of board
		System.out.println("Please enter the size of the board you wish to play on... ");
		System.out.println("Enter the row size for the board (must be an integer value from 3 to 12): ");
		row = s.nextInt();
		while(row < 3 || row > 12){
			System.out.println("Row size must be numbers from 3 to 12, inclusively.  Input a different value: ");
			row = s.nextInt();
		}	
		System.out.println("Enter the column size for the board (must be an integer value from 3 to 12): ");
		column = s.nextInt();
		while(column < 3 || column > 12){
			System.out.println("Column size must be numbers from 3 to 12, inclusively.  Input a different value: ");
			column = s.nextInt();
		}
		
		
		//creates the real board (debug) and the user displayed board 
		BattleboatsBoard grid = new BattleboatsBoard(row, column);
		grid.setEmptyBoard();	//initializes both boards to all empty spots - 0's
		
		
		//sets up rock placement
		System.out.println("Now enter row and column coordinates to place rocks.  ");
		System.out.println("Rocks prevent ships from being placed in the same square");  
		System.out.println("Up to " + grid.getRockTotal() + " rock(s) can be placed.");
						//lets user know how many rocks are left
		do{ 
			System.out.println("Add more coordinates to place more rocks or enter 13 to be done");
			System.out.println("Enter column coordinate for rock (integer from 0 to " + (column - 1) + "):");
			xRock = s.nextInt();
			while (xRock < 0 || xRock >= column && xRock != 13){
				System.out.println("Value must be integer from 0 to " + (column - 1) + ": ");
				xRock = s.nextInt();
			}
			if(xRock == 13){	//user can enter 13 for either x or y if they dont want to place more rocks
				break;
			}
			System.out.println("Enter row coordinate for rock (integer from 0 to " + (column - 1) + "):");

			yRock = s.nextInt();
			while (yRock < 0 || yRock >= row && yRock != 13){
				System.out.println("Value must be integer from 0 to " + (row - 1) + ": ");
				yRock = s.nextInt();
			} 
			if(yRock == 13){
				break;
			}
			if(grid.checkRock(yRock, xRock)){
				grid.rockSetup(yRock, xRock);
			}
		}while(BattleboatsBoard.getRockTotal() > 0 && xRock != 13 && yRock != 13);
		
		
		//sets up boats
		System.out.println();
		System.out.println("Randomly placing boats...");
		grid.boatSetup();
		
		System.out.println();		//lets user know how many boats are on the board
		System.out.printf("There are " + grid.getBoats() + " boat(s) total on the board.\n\n");
		boatsRemaining = grid.getBoats(); 	


		do{
		if (debug){	//if playing debug mode, shows debug board
			System.out.println("Turn: " + turnCounter + "\t\t Shots fired: " + cannonShot + "\t\t Boats Remaining: " + boatsRemaining);
			grid.displayDebugBoard();	
		}
		else{	//normal play mode
			System.out.println("Turn: " + turnCounter + "\t\t Shots fired: " + cannonShot + "\t\t Boats Remaining: " + boatsRemaining);
			grid.userDisplayBoard();	//shows user game board
		}
		
		System.out.println("Choose the column index to fire shot");
		columnShot = s.nextInt();	//fire x coordinate 
		System.out.println("Choose the row index to fire shot.");
		rowShot = s.nextInt();		//fire y coordinate
		Game.checkShot(rowShot, columnShot);	//assess the shot at location
		turnCounter++;	//goes to next turn
		
		}while(boatsRemaining > 0);	//game continues until all boats have been sunk
		
		System.out.println("All boats have been sunk!  You win!");
		System.out.println("Total number of turns: " + (turnCounter-1));
		System.out.println("Total number of shots fired: " + cannonShot);
		System.out.println("Congratulations!  Your prize is $1,000,000,000!!!");
		System.out.println("Unfortunately, I will have to pay you in I-O-U's right now");
		System.out.println("Good job!");
		
		
		
		
		
	
		
		
		
	}
	
	
}