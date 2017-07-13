import tree.*;
import java.util.*;
import java.util.stream.*;

public class TreeGenerator{
	static Integer rootId = 0;

	public static void main(String ... args){
			try(Scanner sc = new Scanner(System.in)){
				int [] arr = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				TreeNode result = generateBalancedBSTFromSortedArray(arr,0,arr.length-1);
				Traversal.inOrderTraverseRec(result);
				System.out.println();
				Traversal.preOrderTraverseRec(result);
				System.out.println();
			}
	}

	public static TreeNode generateBalancedBSTFromSortedArray(int[] arr, int start, int end){
		if(start > end)
			return null;

		int mid = (start+end)>>1;
	
		TreeNode root = new TreeNode(arr[mid]);
		root.left = generateBalancedBSTFromSortedArray(arr,start, mid-1);
		root.right = generateBalancedBSTFromSortedArray(arr,mid+1,end);

		return root;
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
