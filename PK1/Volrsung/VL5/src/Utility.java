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

public class Utility {

public static void main(String[] args) throws IOException{

    sayHello(System.out);
    File f = new File("C:/Users/mikai/Documents/hier.txt");
    try {
        OutputStream otp = new FileOutputStream(f);
        sayHello(otp);
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

public static void copy(File from, File to) {
    try (
        InputStream in = new FileInputStream(from);
        BufferedInputStream bis = new BufferedInputStream(in);
        OutputStream out = new FileOutputStream(to);
        BufferedOutputStream bos = new BufferedOutputStream(out);) {
    int c;
    while ((c = in.read()) != -1) 
        out.write(c);

    } catch (FileNotFoundException e) {
// TODO Ausnahmebehandlung
    } catch (IOException e) {
// TODO Ausnahmebehandlung
    }
}
}