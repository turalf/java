import java.util.*;
import java.util.stream.*;

public class Powerset{
	public static void main(String [] args){
		try(Scanner sc = new Scanner(System.in)){
			int [] arr = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int i=0;i<arr.length;i++){
				printPowerset(arr,i,new LinkedList<Integer>());
			}
		}
	}

	public static void printPowerset(int [] set, int start, List<Integer> subSet){
		subSet.add(set[start]);

		System.out.println(subSet);

		for(int i=start+1;i<set.length;i++){
			printPowerset(set, i, subSet);
		}	

		subSet.remove(subSet.size() - 1);
	}
} 
