package organisms;

import main.Sprite;
import main.Swiat;

public class Guarana extends Roslina {

    public Guarana(Swiat swiat) {
        super(0, 0, null, 0, "GUARANA", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 60;
        allocate();
    }

    private Guarana(Swiat swiat, int x, int y) {
        super(0, 0, null, 0, "GUARANA", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 60;
        this.pos.x = x;
        this.pos.y = y;
        allocate();
    }

    public void rozmnazanie() {
        Organizm child = new Guarana(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
    }

    public void kolizja(Organizm other) {
        other.setSila(other.getSila() + 3);
        swiat.komentuj(" + " + other.getRodzaj() + " ma teraz +3 do sily! + ");
        super.kolizja(other);
    }
}
