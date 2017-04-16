
public class project {

    public static void main(String []args) {

        Screen.windowApplication();

        Swiat swiat = new Swiat();
        char zn = '1';
        do {
            swiat.wykonajTure();
        } while(!swiat.czyKoniec);


    }
}
