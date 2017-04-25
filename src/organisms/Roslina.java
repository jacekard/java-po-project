package organisms;

import main.*;

public abstract class Roslina extends Organizm {
    protected int maxZakresLosowania;
    private boolean czyRosnie = true;

    public Roslina(int sila, int inicjatywa, Sprite sprite, int wiek, String rodzaj, Swiat swiat) {
        super(sila, inicjatywa, sprite, wiek, rodzaj, swiat);
    }

    public void akcja() {

        if (czyRosnie) {
            grow();
            if (Util.los(1, maxZakresLosowania) == 1)
        if (rozsiewanie())
            rozmnazanie();
        }
    }

    public abstract void rozmnazanie();

    public void kolizja(Organizm other) {
        swiat.komentuj(" + " + other.getRodzaj() + " zjada " + this.rodzaj + "! + ");
        swiat.world[pos.y][pos.x] = other;
        swiat.world[other.getOldPosy()][other.getOldPosx()] = null;
        this.die();
    }

    private boolean rozsiewanie() {
        if (wiek >= 100) czyRosnie = false;
        point tmp = ruch();
        if ((pos.x + tmp.x < swiat.WIDTH - 1 && pos.y + tmp.y > 1)
                && (pos.y + tmp.y < swiat.HEIGHT - 1 && pos.y + tmp.y > 1)) {
            if (swiat.world[pos.y + tmp.y][pos.x + tmp.x] == null) {
                return true;
            }
        }
        return false;
    }
}
