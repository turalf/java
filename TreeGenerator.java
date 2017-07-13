import tree.*;
import java.util.*;
import java.util.stream.*;

public class TreeGenerator{
	static Integer rootId = 0;

	public static void main(String ... args){
			try(Scanner sc = new Scanner(System.in)){
				int [] arr = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				TreeNode result = generateBSTFromPreOrderTraversal(arr,Integer.MIN_VALUE,Integer.MAX_VALUE);
				Traversal.inOrderTraverseRec(result);
			}
	}

	public static TreeNode generateBSTFromPreOrderTraversal(int[] arr, int startVal, int endVal){
		if(rootId >= arr.length || arr[rootId] < startVal || arr[rootId] > endVal)
				return null;

		TreeNode root = new TreeNode(arr[rootId]);

		rootId++;
		root.left = generateBSTFromPreOrderTraversal(arr,startVal, root.val);
		root.right = generateBSTFromPreOrderTraversal(arr,root.val, endVal);

		return root;
	
	}


}
