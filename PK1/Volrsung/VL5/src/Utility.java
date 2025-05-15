import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class Utility {

public static void main(String[] args) throws IOException{

    sayHello(System.out);
    File f = new File("C:/Users/mikai/Documents/hier.txt");
    File f1 = new File("C:/Users/mikai/Documents/da.txt");

    try {
        OutputStream otp = new FileOutputStream(f);
        sayHello(otp);
        copy(f, f1);
        cat(f1);
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    }

 public static void sayHello(OutputStream s) throws IOException {

 String t = "Hello World";
 OutputStreamWriter sw = new OutputStreamWriter(s);
 sw.write(t.toCharArray());
 sw.flush();
    }

static void cat(File quelle){

        try( RandomAccessFile raf = new RandomAccessFile(quelle, "r")) {
            
            int b = 0;
            {
            while ((b = raf.read()) != -1) 
               System.out.print((char) b);
               

    } 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

     
}

public static void copy(File from, File to) {
    try (
        RandomAccessFile in = new RandomAccessFile(from, "r");
        RandomAccessFile out = new RandomAccessFile(to, "rw")
    ) {
   
    int c;
    while ((c = in.read()) != -1) 
        out.write(c);
    
    } catch (FileNotFoundException e) {
        e.printStackTrace();    System.out.print("File not found");// TODO Ausnahmebehandlung
    } catch (IOException e) {
        e.printStackTrace();    System.out.print("IOException");// TODO Ausnahmebehandlung
    }
}
}