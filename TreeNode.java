package Tree;

class TreeNode {
	public int data;
	public TreeNode left;
	public int bl;
	public int layer;
	public TreeNode right;
	public TreeNode parent;
	public TreeNode(){
		data = -1;
		bl = 0;
		layer = -1;
		left = null;
		right = null;
		parent = null;
	}
	
	public TreeNode(int d){
		data = d;
		bl = 0;
		layer = -1;
		left = null;
		right = null;
		parent = null;
	}
	
	public TreeNode(TreeNode t){
		data = t.data;
		bl = t.bl;
		layer = t.layer;
		left = t.left;
		right = t.right;
		parent = t.parent;
	}
	
	public TreeNode getLeftChild(){
		return left;
	}
	
	public TreeNode getRightChild(){
		return right;
	}
	
	public TreeNode getParent(){
		return parent;
	}
	
	private int calBL(TreeNode t, int layer){
		if (t == null){
			return layer;
		}
		t.layer = layer;
		int lmax = calBL(t.left, layer + 1);
		int rmax = calBL(t.right, layer + 1);
		t.bl = lmax - rmax;
		return lmax > rmax ? lmax : rmax;
	}
	
	public void calBL(){
		calBL(this, 0);
	}
	
	public String toString(){
		return new String("(" + data + " : " + bl + ") ");
	}
	
	private void DLR(TreeNode t){
		if (t == null){
			return ;
		}
		System.out.print(t);
		DLR(t.left);
		DLR(t.right);
	}
	
	public void DLR(){
		System.out.print("DLR : ");
		DLR(this);
		System.out.println("");
	}
	
	private void LDR(TreeNode t){
		if (t == null){
			return ;
		}
		LDR(t.left);
		System.out.print(t);
		LDR(t.right);
	}
	
	public void LDR(){
		System.out.print("LDR : ");
		LDR(this);
		System.out.println("");
	}
	
	private void LRD(TreeNode t){
		if (t == null){
			return;
		}
		
		LRD(t.left);
		LRD(t.right);
		System.out.print(t);
	}
	
	public void LRD(){
		LRD(this);
		System.out.println("");
	}
}

