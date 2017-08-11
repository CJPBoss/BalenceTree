package Tree;

class AVL{
	public TreeNode root;
	public TreeNode RP;
	public AVL(){
		root = null;
	}
	
	public AVL(TreeNode t){
		root = new TreeNode(t);
	}
	
	public void LDR(){
		root.LDR();
	}
	
	public void DLR(){
		root.DLR();
	}
	
	public void deleteNode(int info){
		TreeNode t = search(info);
		deleteNode(t);
	}
	
	public void refreshRoot(){
		root = RP.left;
	}
	
	public void deleteNode(TreeNode t){
		if (t == null){
			System.out.println("No such a Node ");
			return ;
		}
		TreeNode parent = t.parent;
		TreeNode left = t.left;
		TreeNode right = t.right;
		Boolean flag = (parent.left == t);
		Boolean lf = left == null;
		Boolean rf = right == null;
		if (lf&& rf){
			if (flag){
				parent.left = null;
			}
			else{
				parent.right = null;
			}
		}
		else if (lf){
			System.out.println("left is null");
			if (flag){
				parent.left = right;
				right.parent = parent;
			}
			else {
				parent.right = right;
				right.parent = parent;
			}
		}
		else if (rf){
			System.out.println("right is null");
			if (flag){
				parent.left = left;
				left.parent = parent;
			}
			else {
				parent.right = left;
				left.parent = parent;
			}
		}
		else {
			TreeNode temp = left;
			if (temp.right == null){
				temp.right = right;
				temp.parent = parent;
				try{
					if (flag){
						parent.left = temp;
					}
					else{
						parent.right = temp;
					}
					right.parent = temp;
				}
				catch(NullPointerException e){ }
				
			}
			else {
				while (temp.right != null){
					temp = temp.right;
				}
				System.out.println("Replace" + temp);
				temp.parent.right = temp.left;
				try{
					temp.left.parent = temp.parent;
				}
				catch(NullPointerException e){ }
			
				temp.left = left;
				left.parent = temp;
				
				temp.right = right;
				try{
					right.parent = temp;
				}
				catch(NullPointerException e){ }
				
				if (flag){
					parent.left = temp;
				}
				else{
					parent.right = temp;
				}
				temp.parent = parent;
			}
		}
		refreshRoot();
		balence();
	}
	
	public void addParent(){
		RP = new TreeNode();
		RP.left = root;
		root.parent = RP;
	}
	
	public void addNode(int info){
		TreeNode n = new TreeNode(info);
		addNode(n);
	}
	
	public void addNode(int[] infos){
		for (int i = 0; i < infos.length; i++){
			addNode(infos[i]);
		}
	}
	
	public void addNode(TreeNode t){
		//System.out.println("New Node" + t);
		if (root == null){
			root = t;
			//System.out.print("addTREE : ");
			//DLR();
			//System.out.println("");
			return ;
		}
		if (root.parent == null){
			addParent();
		}
		TreeNode pos = root;
		//System.out.print("Way : ");
		while (pos != null){
			//System.out.print(pos);
			if (pos.data == t.data){
				//System.out.println("Data : " + t.data + "already exist!");
				return ;
			}
			else if (pos.data > t.data){
				if (pos.left == null){
					pos.left = t;
					t.parent = pos;
					break;
				}
				else{
					pos = pos.left;
				}
			}
			else {
				if (pos.right == null){
					pos.right = t;
					t.parent = pos;
					break;
				}
				else{
					pos = pos.right;
				}
			}
		}
		//System.out.print("addNode : ");
		//DLR();
		//System.out.println("");
		balence();
	}

	public void show(){
		System.out.print("show ");
		DLR();
		System.out.println("show ");
		LDR();
		System.out.println("");
	}
	
	public TreeNode search(TreeNode t, int info){
		if (t == null){
			System.out.println("No data : " + info + " in this tree");
			return null;
		}
		else if (info == t.data){
			return t;
		}
		else if (info > t.data){
			return search(t.right, info);
		}
		else {
			return search(t.left, info);
		}
	}
	
	public TreeNode search(int info){
		return search(root, info);
	}
	
	public void leftRotate(int info){
		TreeNode t = search(root, info);
		leftRotate(t);
	}
	/*Left Rotate 
	 parent			parent
	  \				 \
	   t			  right
	  / \			 /	\
	 t.l right 		t	 rS
		/ \		   / \
	   lS  rS	  t.l lS
	
	*parent link to t's Right(sub)
	*right's LeftSubNode(if it has one)(right sub node) link to t  
	*t(sub) link to Right(left sub node)
	*/
	public void leftRotate(TreeNode t){
		//t, parent, right 's object look up
		TreeNode temp = t;
		//System.out.print("main rotate : ");
		//DLR();
		while (temp.right != null){
			//System.out.println(" l now pos" + temp.data);
			temp = temp.right;
			//System.out.print("main rotate : ");
			//DLR();
			if (temp.bl > 0){
				//System.out.println("L -> R rotate");
				rightRotate(temp);
			}
		}
		//System.out.print("main rotate : ");
		//DLR();
		TreeNode parent = t.parent;
		TreeNode right = t.right;
		if (right == null|| parent == null|| t == null){
			return;
		}
		
		if (parent.left != null&& parent.left.data == t.data){	//parent link to right
			parent.left = right;
		}
		else{
			parent.right = right;
		}
		right.parent = parent;	//right link to parent
		
		t.right = right.left;	//t sub change to right's left
		if (right.left != null){
			right.left.parent = t;
		}
		
		right.left = t;			//right link to t
		t.parent = right;		//t link to right
		
	}
	
	public void rightRotate(int info){
		TreeNode t = search(root, info);
		rightRotate(t);
	}
	/*
	  Right Rotate
	 		parent			parent
	 	   / 			   /
	 	  t				  left
	 	 / \			 / \
	   left	t.r			lS  t
	   / \				   / \
	  lS  rS			  rS  t.r
	
	*parent link to Left(sub)
	*t link to Left's RightSubNode(left sub node)(if it has one)
	*Left link to t(right sub node)
	*/
	public void rightRotate(TreeNode t){
		TreeNode temp = t;
		while (temp.left != null){
			//System.out.println(" r now pos" + temp.data);
			temp = temp.left;
			if (temp.bl < 0){
				//System.out.println("R -> L rotate");
				leftRotate(temp);
			}
		}
		//the same as left rotate
		TreeNode parent = t.parent;
		TreeNode left = t.left;
		
		if (left == null|| parent == null || t == null){
			return ; 
		}
		
		if (parent.right != null&& parent.right.data == t.data){	//parent <--> left
			parent.right = left;
		}
		else {
			parent.left = left;
		}
		left.parent = parent;

		t.left = left.right;	//rS <--> t
		if (left.right != null){
			left.right.parent = t;
		}
		
		left.right = t;	//t <--> left
		t.parent = left;
	}
	
	private void balence(TreeNode t){
		if (t == null){
			return ;
		}
		//System.out.println("now is " + t);
		if (t.bl > 1){
			TreeNode next = t;
			while (next.left.bl > 1){
				//System.out.print("bl " + next.data + " ");
				next = next.left;
			}
			//System.out.println("fbl " + next.data + " ");
			rightRotate(next);
			refreshRoot();
			return ;
		}
		else if (t.bl < -1){
			TreeNode next = t;
			while (next.right.bl < -1){
				//System.out.print("bl " + next.data + " ");
				next = next.right;
			}
			//System.out.println("fbl " + next.data + " ");
			leftRotate(next);
			refreshRoot();
			return ;
		}
		else { 
		}
		balence(t.left);
		balence(t.right);
	}
	
	public void balence(){
		root.calBL();
		balence(root);
	}
	//only for testing, don't use
	public void rotate(int info){	
		TreeNode t = search(info);
		if (t.left == null){
			leftRotate(t);
		}
		else{
			rightRotate(t);
		}
	}
}
