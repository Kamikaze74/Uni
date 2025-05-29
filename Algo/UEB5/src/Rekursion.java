public class Rekursion 
{
	public static void rev(int n)
	{
		assert(n >= 0);

		System.out.print(n % 10);
		if (n > 9)
			rev(n / 10);
	}
	
	public static void revIter(int n) 
	{
		assert(n >= 0);
		
		System.out.print(n % 10);
		while(n > 9){
			n = n / 10;
			System.out.print(n % 10);
		}
		
	}


	public static void main(String[] args)
	{
		rev(1234);
		System.out.println();
		
		revIter(1234);
		System.out.println();		
 	}
}
