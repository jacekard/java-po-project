package organisms;

import main.Sprite;
import main.Swiat;
import main.Util;

public class Barszcz extends Roslina {

    public Barszcz(Swiat swiat) {
        super(99, 0, null, 0, "BARSZCZ", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 70;
        allocate();
    }

    public Barszcz(Swiat swiat, int x, int y) {
        super(99, 0, null, 0, "BARSZCZ", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 70;
        this.pos.x = x;
        this.pos.y = y;
        allocate();
    }

    public void rozmnazanie() {
        Organizm child = new Barszcz(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
    }

    public void kolizja(Organizm other) {
        if (!other.getRodzaj().equals("CYBER-OWCA")) {
            swiat.komentuj(" + " + other.getRodzaj() + " ginie otruty przez " + rodzaj + "! + ");
            other.die();
            swiat.world[other.getOldPosy()][other.getOldPosx()] = null;
            swiat.world[pos.y][pos.x] = null;
            this.die();
        }
    }

    public void akcja() {
        super.akcja();
        Organizm tmp;
        if (pos.y - 1 < swiat.HEIGHT - 1 && pos.y - 1 > 1) {
            tmp = swiat.world[pos.y - 1][pos.x];
            if (tmp != null && (tmp instanceof Zwierze)) {
                swiat.komentuj(" + " + tmp.getRodzaj() + " ginie przez trujace opary BARSZCZU! + ");
                tmp.die();
                swiat.world[pos.y - 1][pos.x] = null;
            }
        }

        if (pos.y + 1 < swiat.HEIGHT - 1 && pos.y + 1 > 1) {
            tmp = swiat.world[pos.y + 1][pos.x];
            if (tmp != null && (tmp instanceof Zwierze)) {
                swiat.komentuj(" + " + tmp.getRodzaj() + " ginie przez trujace opary BARSZCZU! + ");
                tmp.die();
                swiat.world[pos.y + 1][pos.x] = null;

            }
        }

        if (pos.x + 1 < swiat.WIDTH - 1 && pos.x + 1 > 1) {
            tmp = swiat.world[pos.y][pos.x + 1];
            if (tmp != null && (tmp instanceof Zwierze)) {
                swiat.komentuj(" + " + tmp.getRodzaj() + " ginie przez trujace opary BARSZCZU! + ");
                tmp.die();
                swiat.world[pos.y][pos.x + 1] = null;

            }
        }

        if (pos.x - 1 < swiat.WIDTH - 1 && pos.x - 1 > 1) {
            tmp = swiat.world[pos.y][pos.x - 1];
            if (tmp != null && (tmp instanceof Zwierze)) {
                swiat.komentuj(" + " + tmp.getRodzaj() + " ginie przez trujace opary BARSZCZU! + ");
                tmp.die();
                swiat.world[pos.y][pos.x - 1] = null;

            }
        }
    }
}