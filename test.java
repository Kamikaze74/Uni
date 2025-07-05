public class test {


    public static void main(String[] args) {
        
        String a = "250";

        int b = Integer.valueOf(a);

        manipulate(b);

        System.out.printf("%n"+  b);
    }

    public static void manipulate(int b)   {   b = b + 5;  }
}
