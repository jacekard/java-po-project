package main;

import java.io.*;
import java.lang.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.ListIterator;

import organisms.*;

import javax.swing.*;

public class Swiat {
    public int WIDTH = 40;
    public int HEIGHT = 20;

    private ArrayList<String> komunikaty;
    public ArrayList<Organizm> lista;
    public Organizm[][] world;
    public JPanel panel;
    public JFrame frame;
    private JTextArea lista_gatunkow;
    //

    private int turnCount = 0;
    private boolean czyKoniec = false;
    private boolean czySave = false;
    private boolean czyLoad = false;
    private boolean tarczaAlzura = false;
    private boolean czyRespawn = false;
    public Plansza plansza;
    public Sprite pusty_sprite;
    public void changeStatement(boolean statement) {
        if (statement) statement = false;
        else statement = true;
    }

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

    public boolean getCzyRespawn() {
        return czyRespawn;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void addTurn() {
        turnCount++;
    }

    public void Rysuj() {
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                if(world[y][x] == null) {
                     //plansza.drawSprite(pusty_sprite, x, y);
                }
                else {
                    world[y][x].rysowanie();
                    int ilosc = lista.size();
                }
            }
        }
    }

    Swiat(int WIDTH, int HEIGHT, JPanel panel, JFrame frame) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        int LICZBA_ZWIERZAT = 0;
        int LICZBA_ROSLIN = 0;
        this.panel = panel;
        this.frame = frame;



        plansza = new Plansza(frame, panel);
        pusty_sprite = new Sprite(true);
        lista = new ArrayList<>();
        komunikaty = new ArrayList<>();

        komentuj("Nowa gra!");

        //test wyswietlania spritów!
        //plansza.drawSprite(pusty_sprite, 55,15);

        //alokacja pamięci dla organizmow na planszy
        world = new Organizm[HEIGHT][WIDTH];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                world[y][x] = null;
            }
        }
        //alokacja zwierzat i czlowieka.
        for (int k = 0; k < LICZBA_ZWIERZAT; k++) {
            //lista.add(new organisms.Organizm());
        }

        for (int k = 0; k < LICZBA_ROSLIN; k++) {
            //lista.add(new organisms.Organizm());
        }
        lista.add(new Czlowiek(this));
        lista.add(new Wilk(this));
        lista.add(new Trawa(this));
        //sortujInicjatywa();

        Rysuj();
    }

    public void wykonajTure() {
        if (czySave) save();
        if (czyLoad) load();
        if (czyRespawn) respawn(); //dodawanie zwierzat przy kliknieciu!

        //randomPlants();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getWiek() != -1)
                lista.get(i).akcja();
        }

        for (int i = lista.size() - 1; i >= 0; i--) {
            if (lista.get(i).getWiek() == -1) {
                lista.remove(i);
            }
        }

        if (turnCount >= 100 - 1) {
            komentuj("zakończono symulacje!");
            changeStatement(czyKoniec);
        }

        Rysuj();
        addTurn();
        //wypiszKomunikaty();
    }

    public void listaGatunkow() {

    }

    public void komentuj(String komunikat) {
        komunikaty.add(komunikat);
    }

    public void wypiszKomunikaty(int x, int y) {
        if (komunikaty.size() == 0)
            komentuj("Brak nowych komunikatow");

        for (String str : komunikaty)
            System.out.println(str);

        komunikaty.clear();
    }

    public void sortujInicjatywa() {
        Collections.sort(lista);
    }

    public void save() {
        changeStatement(czySave);

        try {
            FileWriter plik = new FileWriter("../Saves/save.txt", true);
            BufferedWriter buff = new BufferedWriter(plik);

            buff.write(WIDTH + " ");
            buff.write(HEIGHT);
            buff.newLine();

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    if (world[y][x] == null) buff.write(".");
                    else if (world[y][x].getRodzaj().equals("CZLOWIEK")) buff.write("c");
                    else if (world[y][x].getRodzaj().equals("WILK")) buff.write("w");
                    else if (world[y][x].getRodzaj().equals("OWCA")) buff.write("o");
                    else if (world[y][x].getRodzaj().equals("LIS")) buff.write("l");
                    else if (world[y][x].getRodzaj().equals("ZOLW")) buff.write("z");
                    else if (world[y][x].getRodzaj().equals("ANTYLOPA")) buff.write("a");
                    else if (world[y][x].getRodzaj().equals("TRAWA")) buff.write("t");
                    else if (world[y][x].getRodzaj().equals("MLECZ")) buff.write("m");
                    else if (world[y][x].getRodzaj().equals("GUARANA")) buff.write("g");
                    else if (world[y][x].getRodzaj().equals("JAGODY")) buff.write("j");
                    else if (world[y][x].getRodzaj().equals("BARSZCZ")) buff.write("b");
                }
                buff.newLine();
            }

            if (tarczaAlzura) buff.write("1");
            else buff.write("0");

            buff.write(turnCount - 1);

            plik.close();

            komentuj("Zapisano gre!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        changeStatement(czyLoad);

        lista.clear();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                world[y][x] = null;
            }
        }

        int x0 = 0;
        int y0 = 0;
        int a, b;
        try {
            FileReader file = new FileReader("../Saves/save.txt");
            int n;

            WIDTH = file.read();
            HEIGHT = file.read();

            world = new Organizm[HEIGHT][WIDTH];
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    world[y][x] = null;
                }
            }

            n = file.read();

            while (y0 != HEIGHT) {
                n = file.read();
                if ((char) n == '\n') {
                    x0 = -1;
                    ++y0;
                }
                if ((char) n == 'c') return; //add czlowiek;
                else if ((char) n == 'w') return; //add wilk
                else if ((char) n == 'a') return; //add antylopa
                else if ((char) n == 'o') return;//add owca
                else if ((char) n == 'z') return; //add zolw
                else if ((char) n == 'l') return; //add lis
                else if ((char) n == 'g') return; //add guarana
                else if ((char) n == 't') return; //add trawa
                else if ((char) n == 'm') return; //add mlecz
                else if ((char) n == 'j') return; //add jagody
                else if ((char) n == 'b') return; //add barszcz
                ++x0;
            }

            a = file.read();
            if (a == 1) tarczaAlzura = true;
            b = file.read();
            turnCount = b;
            komentuj("Wczytano gre!");

            file.close();

        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    public void respawn() {
        //.. Obsluga myszy
    }

    public void randomPlants() {
        //if(main.Util.los(1,200) == 1)
        //dodaj Guarana
        // if(main.Util.los(1,200) == 2)
        //dodaj Jagody
        // if(main.Util.los(1,200) == 3)
        //dodaj Barszcz
    }


}
