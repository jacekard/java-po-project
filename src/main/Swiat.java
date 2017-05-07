package main;

import java.io.*;
import java.lang.String;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

import organisms.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import static java.lang.Integer.parseInt;

public class Swiat {
    public int WIDTH;
    public int HEIGHT;
    private ArrayList<String> komunikaty;
    public ArrayList<Organizm> lista;
    public Organizm[][] world;
    public Czlowiek player;
    public JPanel panel;
    public JFrame frame;
    private JTextArea lista_gatunkow;
    //

    private int turnCount = 0;
    private boolean czyKoniec = false;
    private boolean czySave = false;
    private boolean czyLoad = false;
    private boolean tarczaAlzura = false;
    public boolean ifKeyWasPressed = false;

    public Plansza plansza;
    public Sprite pusty_sprite;

    public boolean getCzyKoniec() {
        return czyKoniec;
    }

    public boolean getCzySave() {
        return czySave;
    }

    public boolean getCzyLoad() {
        return czyLoad;
    }

    public boolean getTarczaAlzura() {
        return tarczaAlzura;
    }

    public void setTarczaAlzura(boolean statement) {
        tarczaAlzura = statement;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setKoniec() {
        czyKoniec = true;
    }

    public void changeStatement(boolean statement) {
        if (statement) statement = false;
        else statement = true;
    }

    public ArrayList<String> getKomunikaty() {
        return komunikaty;
    }

    public void addTurn() {
        turnCount++;
    }

    Swiat(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        int LICZBA_ZWIERZAT = 4;
        int LICZBA_ROSLIN = 10;

        lista = new ArrayList<>();
        komunikaty = new ArrayList<>();

        komentuj("Nowa gra!");

        //alokacja pamięci dla organizmow na planszy
        world = new Organizm[HEIGHT][WIDTH];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                world[y][x] = null;
            }
        }

        //alokacja zwierzat i czlowieka.
        player = new Czlowiek(this);
        lista.add(player);

        for (int k = 0; k < LICZBA_ZWIERZAT; k++) {
            lista.add(new Wilk(this));
            lista.add(new Owca(this));
            lista.add(new Antylopa(this));
            lista.add(new Lis(this));
            lista.add(new Zolw(this));
        }

        for (int k = 0; k < LICZBA_ROSLIN; k++) {
            lista.add(new Trawa(this));
            lista.add(new Trawa(this));
            lista.add(new Mlecz(this));
            lista.add(new Guarana(this));
            lista.add(new Jagody(this));
            lista.add(new Barszcz(this));
        }

        for (int i = lista.size() - 1; i >= 0; i--) {
            if (lista.get(i).getWiek() == -1) {
                lista.remove(i);
            }
        }

        sortujInicjatywa();
    }

    protected void wykonajTure() {
        randomPlants();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getWiek() != -1)
                lista.get(i).akcja();
        }

        for (int i = lista.size() - 1; i >= 0; i--) {
            if (lista.get(i).getWiek() == -1) {
                lista.remove(i);
            }
        }

        if (turnCount >= 500 - 1) {
            changeStatement(czyKoniec);
        }

        addTurn();

    }

    public void listaGatunkow() {

    }

    public void komentuj(String komunikat) {
        komunikaty.add(komunikat);
    }

    public void sortujInicjatywa() {
        Collections.sort(lista);
    }

    public void load() {
        try {
            Scanner odczyt = new Scanner(new File("saves/save.txt"));
            String info;
            String[] tab;
            lista.clear();

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    world[y][x] = null;
                }
            }

            info = odczyt.nextLine();
            tab = info.split(" ");

            world = new Organizm[parseInt(tab[0])][parseInt(tab[1])];
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    world[y][x] = null;
                }
            }

            while(odczyt.hasNextLine()) {
                info = odczyt.nextLine();
                tab = info.split(" ");
                String id = tab[0];
                int x = parseInt(tab[1]);
                int y = parseInt(tab[2]);
                addingNewOrganism(this, id, x, y);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {

            PrintWriter zapis = new PrintWriter("saves/save.txt");
            zapis.println(HEIGHT + " " + WIDTH);

            for (int i = 0; i < lista.size(); i++) {
                zapis.println(
                        lista.get(i).getRodzaj() + " " +
                        lista.get(i).getPosx() + " " + lista.get(i).getPosy());
            }
            komentuj("Zapisano gre!");
            zapis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void randomPlants() {
        if (main.Util.los(1, 200) == 1)
            lista.add(new Guarana(this));

        if (main.Util.los(1, 200) == 2)
            lista.add(new Jagody(this));

        if (main.Util.los(1, 200) == 3)
            lista.add(new Barszcz(this));
    }

    public void addingNewOrganism(Swiat swiat, String id, int x, int y) {
        if(x < 0) x = 0;
        if(y < 0) y = 0;

        switch(id.toUpperCase()) {
            case "CZLOWIEK": lista.add(new Czlowiek(this, x, y));
                break;
            case "ANTYLOPA": lista.add(new Antylopa(this, x, y));
                break;
            case "BARSZCZ": lista.add(new Barszcz(this, x, y));
                break;
            case "GUARANA": lista.add(new Guarana(this, x, y));
                break;
            case "JAGODY": lista.add(new Jagody(this, x, y));
                break;
            case "LIS": lista.add(new Lis(this, x, y));
                break;
            case "MLECZ": lista.add(new Mlecz(this, x, y));
                break;
            case "OWCA": lista.add(new Owca(this, x, y));
                break;
            case "WILK": lista.add(new Wilk(this, x, y));
                break;
            case "TRAWA": lista.add(new Trawa(this, x, y));
                break;
            case "ZOLW": lista.add(new Zolw(this, x, y));
                break;

        }
    }

}
