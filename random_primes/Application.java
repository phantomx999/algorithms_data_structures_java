//written by phantomx999
//2016
//random number generator and prime number class

public class Application{

	public static void main(String[] args){

		Random number = new Random(7919, 65537, 102611);
		
		//get M value
		System.out.println(number.getMaximum());
			
		System.out.println();
		
	
		//first 7 "random" values when seed = 97		
		number.setSeed(97);
		for(int i = 0; i < 7; i++){
			System.out.println("Random number: " + number.random());
		}
		System.out.println();
		
		//first 7 "random" values when seed = 45598
		number.setSeed(45598);
		for(int i = 0; i < 7; i++){
			System.out.println("Random number: " + number.random());
		}
		System.out.println();

		//test when seed = 97 again, check for same values
		number.setSeed(97);
		for(int i = 0; i < 7; i++){
			System.out.println("Random number: " + number.random());
		}
		System.out.println();

		//test when seed = 45598 again, check for same values
		number.setSeed(45598);
		for(int i = 0; i < 7; i++){
			System.out.println("Random number: " + number.random());
		}
		System.out.println();

		//continue to test more seeds....
		number.setSeed(0);
		for(int i = 0; i < 7; i++){
			System.out.println("Random number: " + number.random());
		}
		System.out.println();
		
		number.setSeed(1);
		for(int i = 0; i < 7; i++){
			System.out.println("Random number: " + number.random());
		}
		System.out.println();

		number.setSeed(-31);
		for(int i = 0; i < 7; i++){
			System.out.println("Random number: " + number.random());
		}
		System.out.println();




		//show that M hasn't been changed
		System.out.println(number.getMaximum());
		System.out.println();




		//tests for random Boolean: true/false
		number.setSeed(99998);
		for (int i = 0 ; i < 15; i++){
			System.out.println(number.randomBoolean());
		}
		System.out.println();



		
		//test for random int numbers within given range
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomInteger(1, 5));
		}
		System.out.println();
	
		//more test cases....
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomInteger(95, 100));
		}
		System.out.println();
		
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomInteger(-2, 1));
		}
		System.out.println();

		//test for when lower > upper, swap values
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomInteger(2, 0));
		}
		System.out.println();
		
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomInteger(5, 1));
		}
		System.out.println();
		
		//test when lower = upper
		for(int i = 0; i < 3; i++){
			System.out.println(number.randomInteger(9999, 9999));
		}
		System.out.println();





		//tests for double random numbers within set range...
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomDouble(1.0, 5.0));
		}
		System.out.println();
		
		//more test cases...
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomDouble(20.00, 30.00));
		}
		System.out.println();
		
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomDouble(-3, 0));
		}
		System.out.println();
		
		//when lower > upper, swap values
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomDouble(5.0, 1.0));
		}
		System.out.println();
		
		
		
		//call methods in different orders
		for(int i = 0; i < 10; i++){
			System.out.println(number.randomInteger(-1, 1));
		}
		System.out.println();
		
		//when upper = lower
		for(int i = 0; i < 3; i++){
			System.out.println(number.randomDouble(1, 1));
		}
		System.out.println();
		
		System.out.println(number.randomBoolean());
		
		//check M value again
		System.out.printf("M value has not been changed this whole time, ");
		System.out.println(number.getMaximum());
		
		System.out.println();
		
		
		

		//check some numbers for prime
		System.out.println("1 is a prime number: " + Prime.isPrime(1)); 
		System.out.println("2 is a prime number: " + Prime.isPrime(2));
		System.out.println("0  is a prime number: " + Prime.isPrime(0));
		System.out.println("-1 is a prime number: " + Prime.isPrime(-1));
		System.out.println("7 is a prime number: " + Prime.isPrime(7));
		System.out.println("8 is a prime number: " + Prime.isPrime(8));
		System.out.println("19 is a prime number: " + Prime.isPrime(19));
		System.out.printf("102611 is a prime number: " + Prime.isPrime(102611)  + "\n\n");


		//check some numbers for Mersenne Prime
		System.out.println(Prime.isMersennePrime(3));
		System.out.println(Prime.isMersennePrime(4));
		System.out.println(Prime.isMersennePrime(7));
		System.out.println(Prime.isMersennePrime(31));
		System.out.println(Prime.isMersennePrime(63));
		System.out.println(Prime.isMersennePrime(127));
		System.out.println(Prime.isMersennePrime(1));
		System.out.println(Prime.isMersennePrime(0));
		System.out.println(Prime.isMersennePrime(-1));
		System.out.printf(Prime.isMersennePrime(11) + "\n\n");


		//find nth prime number
		System.out.println(Prime.prime(-1)); 	 //bad data test
		System.out.println(Prime.prime(0));		 //bad data test
		System.out.println(Prime.prime(1));
		System.out.println(Prime.prime(2));
		System.out.println(Prime.prime(3));
		System.out.println(Prime.prime(4));
		System.out.println(Prime.prime(539));
		System.out.println(Prime.prime(9999));
		System.out.println(Prime.prime(10000));
		System.out.println(Prime.prime(99999));

		System.out.println();

		
		//finds first n prime numbers
		System.out.println(Prime.primeArray(-3));	//bad data test
		System.out.println(Prime.primeArray(0));	//bad data test
		
		//finds first n prime numbers, prints values of array
		int[] firstPrime = Prime.primeArray(1);
		for(int numbers : firstPrime){
			System.out.print(numbers + " ");
		}
		System.out.println();
		
		//more test cases...
		int[] firstPrimeNumbers = Prime.primeArray(5);
		for(int numbers : firstPrimeNumbers){
			System.out.print(numbers + " ");
		}
		System.out.println();
		
		int[] firstPrimeNumbers2 = Prime.primeArray(10);
		for(int numbers : firstPrimeNumbers2){
			System.out.print(numbers + " ");
		}
		System.out.println();
		
		int[] firstPrimeNumbers3 = Prime.primeArray(100);
		for(int numbers : firstPrimeNumbers3){
			System.out.print(numbers + " ");
		}
		System.out.println();
		System.out.println();
		
		
		
		
		//finds prime factors of set number, prints array
		int[] primeFactorization = Prime.primeFactors(630);
		for(int factors : primeFactorization){
			System.out.print(factors + " ");
		}
		System.out.println("]");
		
		//more test cases...
		int[] primeFactorization2 = Prime.primeFactors(7);
		for(int factors2 : primeFactorization2){
			System.out.print(factors2 + " ");
		}
		System.out.println("]");
		
		int[] primeFactorization3 = Prime.primeFactors(3567);
		for(int factors3 : primeFactorization3){
			System.out.print(factors3 + " ");
		}
		System.out.println("]");
		
		int[] primeFactorization4 = Prime.primeFactors(786954);
		for(int factors4 : primeFactorization4){
			System.out.print(factors4 + " ");
		}
		System.out.println("]");
		
		int[] primeFactorization5 = Prime.primeFactors(102611);
		for(int factors5 : primeFactorization5){
			System.out.print(factors5 + " ");
		}
		System.out.println("]");
		
		int[] primeFactorization6 = Prime.primeFactors(9900);
		for(int factors6 : primeFactorization6){
			System.out.print(factors6 + " ");
		}
		System.out.println("]");
		
		System.out.println(Prime.primeFactors(0));  //bad data tested
		
		System.out.println(Prime.primeFactors(1)); //instructions
												   //said to return values of primeFactors
												   //less than 1 as null, but the number
												   // 1 should also return null as the
												   // number 1 has no prime factors.  											   

	}

}
