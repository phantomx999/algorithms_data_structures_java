//written by phantomx999

public class Boat{
	
	private int length;	//boat is length 3 in array
	private int row;	//row where head of boat will be placed
	private int col;	//column where head of boat will be placed
	private boolean horizontal;	//direction of boat
	private boolean vertical;
	
	//constructor
	public Boat(int row, int col){ //takes in size of board
		this.length = 3;
		this.row = row;
		this.col = col;
		this.horizontal = false;
		this.vertical = false;
	}
	
	public void setBoatDirection(){	//sets up boat direction
		double x = 0.0;
		x = Math.random();	//gets random double 0 to 1
		if(x > 0.5){
			this.vertical = false;
			this.horizontal = true;
		}
		else{
			this.horizontal = false;
			this.vertical = true;
		}
	}
	
	public void setBoatCoordinates(int r, int c){	//takes in size of board
		double x = 0.0;
		double y = 0.0;
		
		x = Math.random();	//gets random double 0 to 1
		x = x * c;			//multiply number by column size of board
		x = Math.floor(x);	
		this.col = (int) x;	//assigns column location for head of boat
		
		y = Math.random();
		y = y * r;
		y = Math.floor(y);
		this.row = (int) y;	//assigns row location for head of boat

	}
	
	//getters
	public int getLength(){
		return length;
	}
	
	public int getBoatRow(){
		return row;
	}
	
	public int getBoatCol(){
		return col;
	}
	
	public boolean getHorizontal(){
		return horizontal;
	}
	
	public boolean getVertical(){
		return vertical;
	}
	
} 