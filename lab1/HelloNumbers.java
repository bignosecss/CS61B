public class HelloNumbers {
	public static void main(String[] args) {
		int x = 0;
		int cumulativeSum = 0;
		while ( x < 10 ) {
			cumulativeSum = cumulativeSum + x;
			System.out.print( cumulativeSum + " " );
			x = x + 1;
		}
	}
}

