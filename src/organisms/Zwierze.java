package organisms;

import main.*;

public abstract class Zwierze extends Organizm {

    public Zwierze(int sila, int inicjatywa, char color, int wiek, String rodzaj, Swiat swiat) {
        super(sila, inicjatywa, color, wiek, rodzaj, swiat);
    }
    public abstract void rozmnazanie();

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

        //dodaj wiek;
        grow();
    }

    public void kolizja(Organizm other) {
        if (other == this) return;
        String rodzaj = other.getRodzaj();
        if (czyRozmnazanie(other)) {
            if (other.getWiek() > 10 && this.wiek > 10) {
                if (Util.los(1, 5) == 1)
                this.rozmnazanie();
            }
        }
        else if (!czyOdbilAtak(other)) {
            if (other.getSila() >= sila) {
                if (rodzaj == "CZLOWIEK")
                    swiat.komentuj(" + " + this.rodzaj + " ginie z reki " + rodzaj + "A! + ");
			else
                swiat.komentuj(" + " + this.rodzaj + " ginie w paszczy " + rodzaj + "! + ");
                this.die();
                swiat.world[pos.y][pos.x] = other;
            }
            else {
                if (this.rodzaj == "CZLOWIEK")
                swiat.komentuj(" + " + rodzaj + " ginie z reki " + this.rodzaj + "A! + ");
			else
                swiat.komentuj(" + " + rodzaj + " ginie w paszczy " + this.rodzaj + "A! + ");
                other.die();
                swiat.world[pos.y][pos.x] = this;
            }
        }


        //Zaktualizuj obiekt na planszy
        //...
    }

    public boolean czyOdbilAtak(Organizm atakujacy) {
        return false;
    }

    public boolean czyRozmnazanie(Organizm other) {
        return rodzaj.equals(other.getRodzaj());
    }

}
