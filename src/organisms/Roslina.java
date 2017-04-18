package organisms;

import main.*;

public abstract class Roslina extends Organizm {
    public Roslina(int sila, int inicjatywa, char color, int wiek, String rodzaj, Swiat swiat) {
        super(sila, inicjatywa, color, wiek, rodzaj, swiat);
    }

    public abstract void akcja();

    public abstract void rozmnazanie();

    public void kolizja(Organizm other) {
        swiat.komentuj(" + " + other.getRodzaj() + " zjada " + this.rodzaj + "! + ");
        this.die();
    }

    public boolean rozsiewanie() {
        point tmp = ruch();
        if ((pos.x + tmp.x < swiat.WIDTH - 1 && pos.y + tmp.y > 1) &&
                (pos.y + tmp.y < swiat.HEIGHT - 1 && pos.y + tmp.y > 1)) {
            if (swiat.world[pos.y + tmp.y][pos.x + tmp.x] == null) {
                return true;
            }
        }
        return false;
    }
}
