import java.util.Scanner;
public class SiebDesEratosthenes {

    static int[] prim;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int[] ausgabe = ausgabenPrimzahlen(sc.nextInt());

        for(int i = 0; i < ausgabe.length; i++){

            System.out.println(ausgabe[i]);
        }
            }
        
            public static int[] ausgabenPrimzahlen(int n){

        prim = new int[n];

        for(int i = 0; i < n; i++){
            prim[i] = i + 1;
        }

        prim[0] = -1;

        int a = 2;

        while(a+a <= n){

            if( prim[a-1] != -1){
                durchstreichenVielfache(a, n);
            }
            a++;
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            if(prim[i] != -1){
                count++;
            }
        }
        int[] newprim = new int[count];
        count = 0;
        for(int i = 0; i < prim.length; i++){
            if(prim[i] != -1){
                newprim[count] = prim[i];
                count++;
            }
        }
        return newprim;
    }

    public static void durchstreichenVielfache(int a, int n){

        for(int i = a + a; i <= n; i = i + a){
            prim[i-1] = -1;
        }
    }

}
