public class ClosestIntegerWithSameWeight{
	public static void main(String ... args){
		long x = Long.parseLong(args[0]);
		printBinary(x);	
		long closestInt = getClosestInteger(x);
		printBinary(closestInt);	
		System.out.println(Math.abs(closestInt-x));	
	}

	private static void printBinary(long x){
		while(x>0){
			System.out.print(x&1);
			x >>= 1;
		}
		
		System.out.println();
	}

	private static long getClosestInteger(long x){
		long s = x & ~(x-1);
		long ns = (x & ~(x+1))+1;
		long max = Math.max(s,ns);
		
		x ^= max ^(max>>1);

		return x;
	}
}
