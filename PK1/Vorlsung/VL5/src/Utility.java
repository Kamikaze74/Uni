import java.io.*;
public class Utility {

    public static void main(String[] args) {
        System.out.println("→ Start");
    
        sayHelloOhneTryWithResources(System.out); // damit System.out geschlossen wird
        System.out.println("→ sayHello(System.out) fertig");
    
        File f = new File("PK1/Vorlsung/VL5/files/hier.txt");
        File f1 = new File("PK1/Vorlsung/VL5/files/da.txt");
    
        try (FileOutputStream fos = new FileOutputStream(f)) {
            sayHelloWithTryWithResources(fos); // damit fos geschlossen wird // kann ich danach fos nicht mehr benutzen?
            System.out.println("→ sayHello(FileOutputStream) fertig");
        } catch (IOException e) {
            System.out.println("→ Fehler beim Schreiben in Datei:");
            e.printStackTrace();
        }
    
        System.out.println("→ Kopiere Datei …");
    
        try {
            copyMitBufferedInputStream(f, f1);
            System.out.println("→ Kopieren fertig");
        } catch (Exception e) {
            System.out.println("→ Fehler beim Kopieren:");
            e.printStackTrace();
        }
    
        System.out.println("→ Inhalt von f1:");
    
        try {
            cat(f1);
            System.out.println("→ cat fertig");
        } catch (Exception e) {
            System.out.println("→ Fehler in cat:");
            e.printStackTrace();
        }
    
        System.out.println("→ Ende");
    }
    
public static void sayHelloWithTryWithResources(OutputStream s) { // der den den outputstream gibt muss ihn schießen //kein try bock 
        // hier vieleicht erst Deklarieren und im try block Initaliesieren?
    try(OutputStreamWriter osw = new OutputStreamWriter(s)) {
        osw.write("Hello World");
    } catch (IOException e) {
        e.printStackTrace();    System.out.print("IOException");// TODO Ausnahmebehandlung
    }
    }

public static void sayHelloOhneTryWithResources(OutputStream s) {
        PrintStream osw = new PrintStream(s);
        osw.print("Hello World");
    }
    
static void cat(File quelle) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(quelle))) {
            byte[] buffer = new byte[8192];
            int c;
            while ((c = bis.read(buffer)) != -1) {
                System.out.write(buffer, 0, c);  // ← direkt in den stdout-Stream schreiben
            }
            System.out.flush(); // wichtig bei gepufferten Ausgaben
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static void copyMitRandomAccessFile(File from, File to) {
    
    try (
        RandomAccessFile in = new RandomAccessFile(from, "r");
        RandomAccessFile out = new RandomAccessFile(to, "rw");
    ) {

    
    byte[] buffer = new byte[8192];
    int c;
    while ((c = in.read(buffer)) != -1)
        out.write(buffer, 0, c);
    
    } catch (FileNotFoundException e) {
        e.printStackTrace();    System.out.print("File not found");// TODO Ausnahmebehandlung
    } catch (IOException e) {
        e.printStackTrace();    System.out.print("IOException");// TODO Ausnahmebehandlung
    }
}

public static void copyMitBufferedInputStream(File from, File to) {
    
    try (
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(from));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(to))
    ) {
        byte[] buffer = new byte[8192];
        int c;
        while ((c = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, c);
        }
    
    } catch (FileNotFoundException e) {
        e.printStackTrace();    System.out.print("File not found");// TODO Ausnahmebehandlung
    } catch (IOException e) {
        e.printStackTrace();    System.out.print("IOException");// TODO Ausnahmebehandlung
    }
}

public static void copyOhneTryWithResources(File from, File to) {
    
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try{
        bis = new BufferedInputStream(new FileInputStream(from));
        bos = new BufferedOutputStream(new FileOutputStream(to));
    
        byte[] buffer = new byte[8192];
        int c;
        while ((c = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, c);
        }
    
    } catch (FileNotFoundException e) {
        e.printStackTrace();    System.out.print("File not found");// TODO Ausnahmebehandlung
    } catch (IOException e) {
        e.printStackTrace();    System.out.print("IOException");// TODO Ausnahmebehandlung
    } finally {
        try {
            if (bos != null) bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try {
            if (bis != null) bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}