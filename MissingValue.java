import java.util.*;
import java.util.stream.*;

public class  MissingValue{
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			int [] arr = new int[sc.nextInt()];
			for (int i=0;i<arr.length ;i++ )
				arr[i] = sc.nextInt();

			System.out.println("Original array");
			IntStream.range(0,arr.length).forEach(i->{System.out.println(arr[i]);});

			System.out.println("Strange Numbers");
			Arrays.stream(getStrangeNums(arr)).forEach(System.out::println);
		}					
	}	

	private static int[] getStrangeNums(int [] arr){
		int [] result = new int[2];

		int xor = IntStream.range(0,arr.length).map(i->arr[i] ^ i).reduce(0,(i,j)->i^j);

		int bitMask = (~(xor-1)) & xor;

		for (int i =0;i<arr.length;i++) {
			if((bitMask & i) != 0)
				result[0] ^= i;
			else
				result[1] ^= i;

			if((bitMask & arr[i]) != 0)
				result[0] ^= arr[i];
			else
				result[1] ^=arr[i];
		}

		return result;
	}
}
