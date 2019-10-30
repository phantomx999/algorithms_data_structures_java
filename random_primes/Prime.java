//written by phantomx999

public class Prime{

	//checks if number is prime
	public static boolean isPrime(int number){
		if (number == 2) {	// immediately checks if number = 2
			return true;}
			
		if (number < 3 || number % 2 == 0) {  //checks if number is < 3 
			return false;}					  // or divisible by 2 --> not a prime number
			
		//checks if number is divisible by any remaining odd divisors (starting at 3) 
		//up to square root of the number	
		for (int divisor = 3; divisor <= Math.sqrt(number); divisor = divisor + 2){
			if (number % divisor == 0) { 
			return false; } 				//not a prime number
		}
		return true; 					//process of elimination --> prime number
	}

	//checks for Mersenne number
	public static boolean isMersennePrime(int number){
		
		boolean primeOrNot = isPrime(number);	//check for prime
		
		System.out.printf(number + " is a Mersenne number? "); 

		if (primeOrNot){		//if prime, check for Mersenne number
			//start k value at 2, test k until > (number + 1) --> Mersenne Equation
			for (int kValue = 2; Math.pow(2, kValue) <= number+1; kValue++){
				//create Mersenne number from given k value
				int mersennePrime = (int) (Math.pow(2, kValue) - 1);
				if (mersennePrime == number) { return true; } //checks passed in number
															//is equal to Mersenne number  
			}    
		}
		return false;	//if number is not prime or Mersenne number
	}

	//returns the "nth" prime number 
	public static int prime(int n){
		
		//for bad input, default message
		if (n<1) {
			System.out.printf("Inputted Number " + n + " less than 1, return ");
			return -1;}
			
		int count = 0;	//counter for primes
		int number = 2; //number tested for prime, starts at 2
		
		while(count < n){	//counts # of primes until nth prime is reached
			if (isPrime(number)) { //check for prime
				count++;	//counts # prime numbers
			}
			number++;		//continues to check more prime numbers
		}
		System.out.printf("#" + n + " prime number is: " );  
		return number-1;	// subtract 1 to get last nth prime number 
		 					// (number increased by n + 1 so subtract by 1)
	}

	//returns the first "n" prime numbers in array
	public static int[] primeArray(int howMany){
		
		//for bad input
		if (howMany < 1) {
			System.out.printf("Inputted Number " + howMany + " less than 1, return ");
			return null;
		}
			
		//array of "n" length that contains prime numbers
		int[] numbersPrime = new int[howMany];	
		int number = 2;			//tested number for prime
		int i = 0;				//counts prime numbers
		while (i < howMany){		//compares current prime count vs. desired prime count
			if (isPrime(number)){		//checks for prime
				numbersPrime[i] = number;	//stores prime number into array		
				i++;						//increase prime count
			}
			number++;	//finds next prime number
		}
		
		System.out.printf("First " + howMany + " prime numbers are: ");
		return numbersPrime;	//returns array of first n prime numbers
	}

	//computes and returns the prime factorization of a number
	public static int[] primeFactors(int number){
		int numberStorage = number;		//stores original number, used for output
		
		//for bad input
		if (number <= 1) {
			System.out.printf("The number " + number + " is less than/equal to 1, return ");
			return null;
		}
				
		int primeIndex = 0;		//index for prime factorization array
		int divisor = 2;		//prime factors that divide into tested number
		
		//test case when original number is already prime 
		if (isPrime(number)) {	//checks for prime 
			
			//creates array that has no extra 0's at end (only length 1)
			int[] factors1 = new int[1];
			
			factors1[primeIndex] = number;	//stores prime number into array	
			
			System.out.printf("The prime factorization of " + numberStorage + " is: [ ");
			return factors1;
		}
		
		
		int[] factors2 = new int[64];  //first array for unknown lengths, stores prime factors
		int n = 0; //counter for prime factors

		//test case when original number is not prime
		while(!isPrime(number)){	//checks for not prime
			if (number % divisor == 0){		//tests if number is divisible by numbers >=2
				n++;						//counts prime factors
				factors2[primeIndex] = divisor;	//stores prime number into array
				primeIndex++;	//next array index
				
				//divides by prime factor to get new number to test
				number = number/divisor; 
			
				//resets factor back to previous factor to check for factor repeats
				divisor = divisor-1; 
			}
			divisor++;	//tests for next divisor value
		}
		factors2[primeIndex] = number;	//last prime value stored in array
		n++; //counts last prime factor in first array		
				
		//create new array to get rid of extra zeros at end of first array
		int[] newArray = new int[n];	//new array length = counter of prime factors
		int j = 0;		//index for new array
			
		//copy non zero values of first array into new array	
		for (int i = 0; i < factors2.length; i++){
			if (factors2[i] != 0){	//checks non zero values in first array
				newArray[j] = factors2[i];	//puts these non zero values into new array
				j++;	//increments index
			}
		}
		
		System.out.printf("The prime factorization of " + numberStorage + " is: [ ");
		return newArray; 
		
	}

}
