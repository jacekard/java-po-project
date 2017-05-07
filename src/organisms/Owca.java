package organisms;

import main.Sprite;
import main.Swiat;

public class Owca extends Zwierze {

    public Owca(Swiat swiat) {
        super(4, 4, null, 0, "OWCA", swiat);
        allocate();
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
    }

    public Owca(Swiat swiat, int x, int y) {
        super(4, 4, null, 0, "OWCA", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        this.pos.x = x;
        this.pos.y = y;
        allocate();
    }

    public void rozmnazanie() {
        Organizm child = new Owca(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
        swiat.komentuj("Urodzila sie mala biala owieczka!");
    }
}