import tree.TreeNode;
import java.util.*;
import java.util.stream.*;

public class LCAOptimized{
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			TreeNode root = TreeNode.generateTree(Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(),0);

			System.out.println(computeLCA(root.left.left,root.left));
		}	
	}

	public static TreeNode computeLCA(TreeNode n1, TreeNode n2){
		HashSet<TreeNode> nodeSet = new HashSet<TreeNode>();

		while(n1 != null || n2 != null){
			if(n1 != null && !nodeSet.add(n1))
				return n1;
			if(n2 != null && !nodeSet.add(n2))
				return n2;

			n1 = n1.parent;
			n2 = n2.parent;
		}			

		return null;
	}
}
