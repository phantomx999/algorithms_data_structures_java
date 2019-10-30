//written by phantomx999

public class Random{

	private int p1;		//P1 constant prime value
	private int p2;		//P2 constant prime value
	private int m;		//M constant prime value 
	private int seed;	//seed value, used for initial condition
	private int r;		//random number generated value

	//constructor, initializes member variables 
	public Random(int P1, int P2, int M){
		this.p1 = P1;
		this.p2 = P2;
		this.m = M;
		this.seed = 0;
		this.r = 0;
	}

	//set new seed value
	public void setSeed(int Seed){
		System.out.println("Seed is: " + Seed);
		seed = Seed;
	}

	//return M value
	public int getMaximum(){
		System.out.printf("M value is: " );
		return m;
	}

	//generates random number
	public int random(){
		r = ((p1*seed)+p2)%m;	//random number generator equation	
		seed = r; //sets seed to previous random number to get next random number 
		return r; 	//return random number
	}

	//generate random int number in set range
	public int randomInteger(int lower, int upper){
		int temp = 0;		//									//
		if (lower>upper){	//									//
			temp = lower;	//   swap values if lower > upper	//
			lower = upper;	//									//
			upper = temp;	//									//
		}
		
		if(lower == upper) { //if upper - lower = 0
			System.out.printf("upper range " + upper + " = " + "lower range " + lower + ":  ");
			return lower;
		}
		
		int randomNumber = random();	//generate random number
		
		//map generated random number into set range using equation below:
		int randomRangeInteger = randomNumber * (upper + 1 - lower)/m + lower;
		
		System.out.printf("Random int between " + lower  + " and " + upper + ":  ");
		
		//return new random number within given range 
		return randomRangeInteger;
	}

	//randomly return true/false 
	public boolean randomBoolean(){
		r = random();	//get random number
		if (r%2 == 0) { //even numbers = true	
		 	System.out.printf("Even numbers = true. " + r + " is even, Random Boolean: ");
			return true; }		
		else { //odd numbers = false
	 		System.out.printf("Odd numbers = false. " + r + " is odd, Random Boolean: ");
			return false; }			
	}

	//returns random double value within a given range
	public double randomDouble(double lower, double upper){
		double temp = 0;	//									//
		if (lower>upper){	//									//
			temp = lower;	//	swap values if lower > upper 	//
			lower = upper;	//									//
			upper = temp;	//									//
		}
		
		if(lower == upper) { //if upper - lower = 0
			System.out.printf("upper range " + upper + " = " + "lower range " + lower + ":  ");
			return lower;
		}
		
		//get random number
		double randomNumber = this.random() * 1.0;
		
		//change m value to double
		double doubleM = (double) m;
		
		//map double random number into set range 
		double randomRangeDouble = randomNumber * (upper + 1.0 - lower)/doubleM + lower;

		//if double number > than upper (i.e. 5.123 > 5.0), double value not truncated
		while(randomRangeDouble > upper){ 
			randomNumber = this.random() * 1.0; 	//get a new random number
			
			//map random number into set range
			randomRangeDouble = randomNumber * (upper + 1.0 - lower)/doubleM + lower;
		}

		System.out.printf("Random double between " + lower  + " and " + upper + ": ");

		//return new random number within set range
		return randomRangeDouble;

	}

}
