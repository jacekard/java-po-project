package organisms;

import main.*;

public class Wilk extends Zwierze {

    public Wilk(Swiat swiat) {
        super(9, 5, null, 0, "WILK", swiat);
        allocate();
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
    }

    private Wilk(Swiat swiat, int x, int y) {
        super(9, 5, null, 0, "WILK", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        this.pos.x = x;
        this.pos.y = y;
        allocate();

    }

    public void rozmnazanie() {
        Organizm child = new Wilk(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
        swiat.komentuj("Urodzil sie maly szary wilk!");
    }
}
