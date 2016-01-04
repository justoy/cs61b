public class DrawTriangle{
	public static void draw(int n)
	{
		for(int i=1;i<=n;++i)
		{
			for(int j=0;j<i;++j)
			{
				System.out.print('*');
			}
			System.out.println();
		}
	}

	public static void main(String[] args) 
	{
		draw(20);
	}
}


