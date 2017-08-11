package Tree;



public class Main 
{
	
	public static void nl()
	{
		System.out.println("");
	}
	
	public static void main(String[] args) 
	{
		int[][] list4 = {
							{2, 1}, 
							{1, 2},
							{2, 1, 3, 4},
							{8, 4, 9, 10, 2, 6, 1, 3, 5, 7},
							{8, 4, 9, 10, 2, 6, 1, 3, 5}, 
							{8, 4, 12, 2, 16, 1}
				/*
							{5, 2, 1, 4, 3},
							{5, 6, 7, 8, 9},
							{5, 6, 4, 1, 2, 3},
							{3, 2, 1, 4, 5, 6, 7, 10, 9, 8},
							{2, 1, 3, 4, 5},
							{4, 3, 2, 1, 5}, 
							{4, 3, 1, 2, 5},
							{2, 1, 3, 5, 4},
							{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
							{10, 9, 8, 6, 5, 4, 3, 2, 1},
				*/
						};
		
		for (int i = 0; i < list4.length; i++)
		{
			System.out.print("No." + (i + 1) + " : ");
			for (int j = 0; j < list4[i].length; j++)
			{
				System.out.print(list4[i][j] + " ");
			}
			nl();
			AVL bt4 = new AVL();
			bt4.addNode(list4[i]);
			bt4.root.calBL();
			bt4.DLR();
			bt4.deleteNode(8);
			bt4.DLR();
			nl();
		}
	}
}
