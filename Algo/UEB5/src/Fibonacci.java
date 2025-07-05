public class Fibonacci {

    public static int fib(int n)
	{
        if(0 == n)
            return 0;

          else if(1 == n)
            return 1;
          else
            return fib(n-1)+fib(n-2);
	}
	
	public static int fibIter(int n) 
	{
		int c1 = 0;
        int c2 = 1;

        while(n != 0){
            int puffer = c1;
            c1 += c2;
            c2 = puffer;
            n--;
        }
        return c1;
	}


	public static void main(String[] args)
	{
        for(int i = 0; i < 9; i++){
		System.out.println(fib(i));
		
		System.out.println(fibIter(i));	}	
}

}
