import java.util.*;
import java.util.stream.*;

public class Traversal{
	public static void main(String ... args){
		try(Scanner sc = new Scanner(System.in)){
			inOrderTraverse(generateTree(Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(),0));
			preOrderTraverse(generateTree(Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(),0));
		}
	}

	public static void inOrderTraverse(TreeNode tree){
		while(tree != null){
			while(tree.left != null){
				tree = tree.left;
			}

			System.out.print(tree.val +" ");

			if(tree.right != null){
				tree = tree.right;
			}
			else{
				while(tree.parent != null && tree == tree.parent.right) tree = tree.parent;
				tree = tree.parent;

				if (tree!=null) {
					System.out.print(tree.val +" ");
					tree = tree.right;
				}
			}
		}
	}

	public static void preOrderTraverse(TreeNode tree){
		while(tree != null){
			System.out.print(tree.val);

			if(tree.left != null)
				tree = tree.left;
			else if (tree.right != null) {
				tree = tree.right;
			}
			else{
				while (tree.parent != null && tree.parent.right = tree) tree = tree.parent;
					
			}
		}
	}
	private static TreeNode generateTree(int [] in, int i){
		if(i >= in.length || in[i] == -1)
			return null;

		TreeNode t = new TreeNode(in[i]);

		t.left = generateTree(in,2*i+1);
		if (t.left != null)
			t.left.parent = t;
		
		t.right = generateTree(in,2*i + 2);
		if (t.right != null) 
			t.right.parent = t;
		
		return t;
	}
}

class TreeNode{
	TreeNode left, right, parent;
	int val;

	public TreeNode(int val){
		this.val = val;
	}
}
