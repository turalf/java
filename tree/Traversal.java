package tree;

import java.util.*;
import java.util.stream.*;

public class Traversal{
	public static void main(String ... args){
		try(Scanner sc = new Scanner(System.in)){
			TreeNode root = TreeNode.generateTree(Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(),0);
			//inOrderTraverse(root);
			postOrderTraverse(root);
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

	public static void postOrderTraverse(TreeNode tree){
		while(tree != null){
			while (tree.left != null) tree = tree.left;

			if(tree.right == null){
				System.out.print(tree.val + "->");
				while(tree.parent != null && (tree.parent.right == null || tree.parent.right == tree)){
					tree = tree.parent;
					System.out.print(tree.val);
				} 

				tree = tree.parent;

				if(tree != null)
					tree = tree.right;
			}
			else {
				tree = tree.right;
			}

			if(tree.left != null)
				tree = tree.left;
			else if (tree.right != null) {
				tree = tree.right;
			}
			else{
				while (tree.parent != null && tree.parent.right == tree) tree = tree.parent;
				tree = tree.parent;

				if(tree != null)	
					tree = tree.right;
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
				while (tree.parent != null && tree.parent.right == tree) tree = tree.parent;
				tree = tree.parent;

				if(tree != null)	
					tree = tree.right;
			}
		}
	}
}
