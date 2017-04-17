public class Organizm implements Comparable<Organizm> {
    private int sila;
    private int inicjatywa;
    private int color;
    private int wiek;
    private String rodzaj;
    private point pos;
    private point old_pos;
    protected Swiat swiat;

    public Organizm(int sila, int inicjatywa, char color, int wiek, String rodzaj, Swiat swiat) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.color = color;
        this.wiek = wiek;
        this.rodzaj = rodzaj;
        this.swiat = swiat;

        this.pos = new point();
        this.old_pos = new point();

        this.pos.x = Util.los(1, swiat.WIDTH - 1);
        this.pos.y = Util.los(1, swiat.HEIGHT - 1);
        this.old_pos = this.pos;

    }

    public int compareTo(Organizm other) {
        int diff = this.inicjatywa - other.getInicjatywa();
        if (diff != 0) return diff;

        if(this.wiek > other.getWiek()) return this.wiek;
        else return other.getWiek();

    }

    public void rozmnazanie() {
    }

    public void akcja() {
    }

    public void kolizja(Organizm other) {
    }

    public void rysowanie() {
        //Screen class
        System.out.println("Obiekt klasy Organizm");
    }

    //gettery
    public int getPosx() {
        return this.pos.x;
    }

    public int getOldPosx() {
        return this.old_pos.x;
    }

    public int getPosy() {
        return this.pos.y;
    }

    public int getOldPosy() {
        return this.old_pos.y;
    }

    public int getWiek() {
        return this.wiek;
    }

    public int getSila() {
        return this.sila;
    }

    public int getInicjatywa() {
        return this.inicjatywa;
    }

    public String getRodzaj() {
        return this.rodzaj;
    }

    //settery
    void setPosx(int x) {
        this.pos.x = x;
    }

    void setPosy(int y) {
        this.pos.y = y;
    }

    void setWiek(int wiek) {
        this.wiek = wiek;
    }

    void setSila(int sila) {
        this.sila = sila;
    }

    boolean czyDozwolonyRuch(point tmp) {
        //...
        return true;
    }

    void grow() {
        this.wiek++;
    }

    void die() {
        if (this.rodzaj == "CZLOWIEK")
            swiat.changeStatement(swiat.getCzyKoniec());
        else {
            //...
        }
    }

    void reallocate() {
    }

    void allocate() {
    }

    point ruch() {
        int kierunek_ruchu = Util.los(0, 3);
        point tmp = new point();
        if (kierunek_ruchu == 0)
            tmp.y--;
        else if (kierunek_ruchu == 1)
            tmp.y++;
        else if (kierunek_ruchu == 2)
            tmp.x--;
        else if (kierunek_ruchu == 3)
            tmp.x++;

        return tmp;
    }
}
