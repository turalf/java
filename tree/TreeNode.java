package tree;

public class TreeNode{

	public TreeNode left, right, parent;
	int val;

	public TreeNode(int val){
		this.val = val;
	}

	public static TreeNode generateTree(int [] in, int i){
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

	public String toString(){
		return String.valueOf(this.val);
	}

	public int hashCode(){
		return this.val;
	}
}
