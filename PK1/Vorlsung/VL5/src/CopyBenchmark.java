import java.io.*;

public class CopyBenchmark {

    public static void main(String[] args) throws IOException {
        File source = new File("PK1/Vorlsung/VL5/files/SamplePNGImage_30mbmb.png"); // Erstelle vorher eine große Datei // vorher erstellen (z. B. 100 MB)
        //File dest1 = new File("copy1.jpg");
        File dest2 = new File("copy2.jpg");
        File dest3 = new File("copy3.jpg");
        //File dest4 = new File("copy3.jpg");
        File dest5 = new File("copy4.jpg");

        long start, end;

        start = System.nanoTime();
     //   copyUnbufferedBytewise(source, dest1);
        end = System.nanoTime();
        System.out.printf("Ungepuffert, byteweise: %.2f ms%n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        copyBufferedBytewise(source, dest2);
        end = System.nanoTime();
        System.out.printf("BufferedInputStream, byteweise: %.2f ms%n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        copyBufferedWithArray(source, dest3);
        end = System.nanoTime();
        System.out.printf("BufferedInputStream mit byte[]: %.2f ms%n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
     //   copyMitRandomAccessFileOhneBuffer(source, dest4);
        end = System.nanoTime();
        System.out.printf("RandomAccessFile: %.2f ms%n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        copyMitRandomAccessFile(source, dest5);
        end = System.nanoTime();
        System.out.printf("RandomAccessFile mit byte[]: %.2f ms%n", (end - start) / 1_000_000.0);
    }

    static void copyUnbufferedBytewise(File from, File to) throws IOException {
        try (
            FileInputStream fis = new FileInputStream(from);
            FileOutputStream fos = new FileOutputStream(to)
        ) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        }
    }

    static void copyBufferedBytewise(File from, File to) throws IOException {
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(from));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(to))
        ) {
            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
        }
    }

    static void copyBufferedWithArray(File from, File to) throws IOException {
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(from));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(to))
        ) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
    }

    static void copyMitRandomAccessFileOhneBuffer(File from, File to) throws IOException {
        try (
            RandomAccessFile in = new RandomAccessFile(from, "r");
            RandomAccessFile out = new RandomAccessFile(to, "rw")
        ) {
            
            int bytesRead;
            while ((bytesRead = in.read()) != -1) {
                out.write(bytesRead);
            }
        }
    }

    static void copyMitRandomAccessFile(File from, File to) throws IOException {
        try (
            RandomAccessFile in = new RandomAccessFile(from, "r");
            RandomAccessFile out = new RandomAccessFile(to, "rw")
        ) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
