package Algo.Ueb11_Suche;

class Suche {


    public static void main(String[] args) {
        
        String[] worte = {"ich","du","er"};

        System.out.print(binaereSuche(worte, "du"));
    }

    static boolean binaereSuche(final String[] worte, final String begriff)
    {
        int links = 0;
        int rechts = worte.length;

        while(links < rechts)
        {
            int m = (rechts + links) / 2;
            int comp = worte[m].compareTo(begriff);

            if( comp < 0 )
                rechts = m + 1;

            else if( comp > 0 )
                links = m - 1;

            else if( comp == 0 )
                return true;
        }
        return false;
    }
}