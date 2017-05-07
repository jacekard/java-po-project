package organisms;

import main.Sprite;
import main.Swiat;
import main.point;

public class Lis extends Zwierze {

    public Lis(Swiat swiat) {
        super(3, 7, null, 0, "LIS", swiat);
        allocate();
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
    }

    public Lis(Swiat swiat, int x, int y) {
        super(3, 7, null, 0, "LIS", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        this.pos.x = x;
        this.pos.y = y;
        allocate();
    }

    public void rozmnazanie() {
        Organizm child = new Lis(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
        swiat.komentuj("Urodzil sie maly rudy lis!");
    }

    public void akcja() {
        //dodaj wiek;
        grow();

        point tmp = ruch();

        if (pos.x + tmp.x > swiat.WIDTH - 1 || pos.x + tmp.x < 1)
            tmp.x = 0;
        if (pos.y + tmp.y > swiat.HEIGHT - 1 || pos.y + tmp.y < 1)
            tmp.y = 0;

        old_pos = new point(pos);
        //aktualizuj pozycje
        pos.x += tmp.x;
        pos.y += tmp.y;

        //lis nie wchodzi na pole, gdy przeciwnik ma wieksza sile
        if (swiat.world[pos.y][pos.x] != null
                && swiat.world[pos.y][pos.x].getSila() > this.sila) {
            swiat.komentuj("Lis zweszyl " + swiat.world[pos.y][pos.x].getRodzaj() + "!");
            this.reallocate();
        }
        if (swiat.world[pos.y][pos.x] != null
                && swiat.world[pos.y][pos.x] != this)
            swiat.world[pos.y][pos.x].kolizja(this);
        else {
            swiat.world[old_pos.y][old_pos.x] = null;
            swiat.world[pos.y][pos.x] = this;
        }
    }
}
