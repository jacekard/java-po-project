package organisms;

import main.Sprite;
import main.Swiat;
import main.Util;
import main.point;

public abstract class Organizm implements Comparable<Organizm> {
    protected int sila;
    protected int inicjatywa;
    protected Sprite sprite;
    protected int wiek;
    protected String rodzaj;
    protected point pos;
    public point old_pos;
    protected Swiat swiat;


    public Organizm(int sila, int inicjatywa, Sprite sprite, int wiek, String rodzaj, Swiat swiat) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.sprite = sprite;
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
        int diff = other.getInicjatywa() - this.inicjatywa;
        if (diff != 0) return diff;

        if(this.wiek > other.getWiek()) return this.wiek;
        else return other.getWiek();

    }

    public abstract void rozmnazanie();

    public abstract void akcja();

    public abstract void kolizja(Organizm other);

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

    public Sprite getSprite() { return this.sprite; };

    public String getRodzaj() {
        return this.rodzaj;
    }

    public String setProperSprite(String name) {
        String path = "src/sprites/";
        String ext = ".png";
        return path+name+ext;
    }

    //settery
    public void setPosx(int x) {
        this.pos.x = x;
    }

    public void setPosy(int y) {
        this.pos.y = y;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    void setSila(int sila) {
        this.sila = sila;
    }

    boolean czyDozwolonyRuch(point tmp) {
        boolean correctX = true;
        boolean correctY = true;
        if (tmp.x > swiat.WIDTH - 1 || tmp.x < 1)
            correctX = false;
        if (tmp.y > swiat.HEIGHT - 1 || tmp.y < 1)
            correctY = false;

        if (correctX && correctY) return true;
        else return	false;
    }

    void grow() {
        this.wiek++;
    }

    void die() {
        if (this.rodzaj == "CZLOWIEK")
            swiat.setKoniec();
        else {
            this.wiek = -1;
        }
    }

    void reallocate() {
        point tmp;
        int rand;
        rand = Util.los(1, 4);
        for (int i = 0; i < 4; i++) {
            switch ((rand + i) % 4 + 1) {
                case 1: 	//case up
                    tmp = pos;
                    tmp.y--;
                    if ((czyDozwolonyRuch(tmp)) &&
                            (swiat.world[tmp.y][tmp.x] == null)) {
                        swiat.world[tmp.y][tmp.x] = this;
                        pos = tmp;
                        return;
                    }
                    break;
                case 2: 	//case down
                    tmp = pos;
                    tmp.y++;
                    if ((czyDozwolonyRuch(tmp)) &&
                            (swiat.world[tmp.y][tmp.x] == null)) {
                        swiat.world[tmp.y][tmp.x] = this;
                        pos = tmp;
                        return;
                    }
                    break;
                case 3:	//case left
                    tmp = pos;
                    tmp.x--;
                    if ((czyDozwolonyRuch(tmp)) &&
                            (swiat.world[tmp.y][tmp.x] == null)) {
                        swiat.world[tmp.y][tmp.x] = this;
                        pos = tmp;
                        return;
                    }
                    break;
                case 4: 	//case right
                    tmp = pos;
                    tmp.x++;
                    if ((czyDozwolonyRuch(tmp)) &&
                            (swiat.world[tmp.y][tmp.x] == null)) {
                        swiat.world[tmp.y][tmp.x] = this;
                        pos = tmp;
                        return;
                    }
                    break;
            }
        }
       // swiat.komentuj("Realokacja obiektu " + this.rodzaj + " nie udala sie ");
        this.die();
    }

    void allocate() {
        if(swiat.world[pos.y][pos.x] == null)
            swiat.world[pos.y][pos.x] = this;
        else reallocate();
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
