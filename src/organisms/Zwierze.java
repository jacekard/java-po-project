package organisms;

import main.*;

public abstract class Zwierze extends Organizm {

    public Zwierze(int sila, int inicjatywa, Sprite sprite, int wiek, String rodzaj, Swiat swiat) {
        super(sila, inicjatywa, sprite, wiek, rodzaj, swiat);
    }

    public void akcja() {
        point tmp = ruch();
        tmp.x = 0;
        tmp.y = 0;

        if (pos.x + tmp.x > swiat.WIDTH - 1 || pos.x + tmp.x < 1)
            tmp.x = 0;
        if (pos.y + tmp.y > swiat.HEIGHT - 1 || pos.y + tmp.y < 1)
            tmp.y = 0;

        old_pos = pos;
        //aktualizuj pozycje
        pos.x += tmp.x;
        pos.y += tmp.y;

        if (swiat.world[pos.y][pos.x] != null
                && swiat.world[pos.y][pos.x] != this)
            swiat.world[pos.y][pos.x].kolizja(this);
        else {
            swiat.world[old_pos.y][old_pos.x] = null;
            swiat.world[pos.y][pos.x] = this;
        }

        //dodaj wiek;
        grow();
    }

    public void kolizja(Organizm other) {
        if (other == this) return;
        String rodzaj = other.getRodzaj();
        if (czyRozmnazanie(other))
            this.rozmnazanie();
        else if (!czyOdbilAtak(other)) {
            if (other.getSila() >= sila) {
                if (this.rodzaj.equals("CZLOWIEK"))
                    swiat.komentuj(" + " + this.rodzaj + " ginie z reki " + rodzaj + "A! + ");
                else
                    swiat.komentuj(" + " + this.rodzaj + " ginie w paszczy " + rodzaj + "! + ");
                swiat.world[pos.y][pos.x] = other;
                swiat.world[other.getOldPosy()][other.getOldPosx()] = null;
                this.die();
            } else {
                if (this.rodzaj.equals("CZLOWIEK"))
                    swiat.komentuj(" + " + rodzaj + " ginie z reki " + this.rodzaj + "A! + ");
                else
                    swiat.komentuj(" + " + rodzaj + " ginie w paszczy " + this.rodzaj + "A! + ");
                swiat.world[other.getOldPosy()][other.getOldPosx()] = null;
                other.die();
            }
        }
    }

    public boolean czyOdbilAtak(Organizm atakujacy) {
        return false;
    }

    public boolean czyRozmnazanie(Organizm other) {
        if (rodzaj.equals(other.getRodzaj())) {
            if (Util.los(1, 3) == 1) {
                if (other.getWiek() > 15 && wiek > 15)
                    return true;
            }
        }
        return false;
    }

}
