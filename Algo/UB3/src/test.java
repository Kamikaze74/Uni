public class test {

    public static void main(String[] args) {
     

        // Aufagebe 1
        Link <String> mittleresElement = new Link<>("Test", null);
        Link <String> ende = new Link<>("Letzter", null);
        mittleresElement.naechster = ende;
        Link <String> anfang = new Link<>("Erster", mittleresElement);
        
    }

}
