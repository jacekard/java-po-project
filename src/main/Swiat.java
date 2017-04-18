package main;

import java.io.*;
import java.lang.*;
import java.util.Collections;
import java.util.ArrayList;
import organisms.*;

public class Swiat {
    public final int WIDTH = 40;
    public final int HEIGHT = 20;

    ArrayList<Organizm> lista;
    ArrayList<String> komunikaty;
    Organizm[][] world;
    //

    private int turnCount = 0;
    private boolean czyKoniec = false;
    private boolean czySave = false;
    private boolean czyLoad = false;
    private boolean tarczaAlzura = false;
    private boolean czyRespawn = false;

    public void changeStatement(boolean statement) {
        if (statement) statement = false;
        else statement = true;
    }
    public boolean getCzyKoniec() { return czyKoniec; }
    public boolean getCzySave() { return czySave; }
    public boolean getCzyLoad() { return czyLoad; }
    public boolean getTarczaAlzura() { return tarczaAlzura; }
    public boolean getCzyRespawn() { return czyRespawn; }

    public void addTurn() { turnCount++; }

    Swiat() {
        int LICZBA_ZWIERZAT = 0;
        int LICZBA_ROSLIN = 0;

        lista = new ArrayList<>();
        komunikaty = new ArrayList<>();

        //alokacja pamięci dla organizmow na siatce
        world = new Organizm[HEIGHT][WIDTH];
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
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
        //lista.add(
        sortujInicjatywa();
    }

    public void wykonajTure() {
        if (czySave) save();
        if (czyLoad) load();
        if (czyRespawn) respawn(); //dodawanie zwierzat przy kliknieciu!

        int poz_x, poz_y;

        lista.trimToSize();
        for (Organizm org : lista) {
            if( org == null) break;
            poz_x = org.getPosx();
            poz_y = org.getPosy();
            org.akcja();
            world[poz_y][poz_x] = null;
            world[org.getOldPosy()][org.getOldPosx()] = org;
        }
        if (turnCount >= 500 - 1) {
            komentuj("zakończono symulacje!");
            changeStatement(czyKoniec);
        }

        addTurn();

        //tutaj jakas funkcja rysowania, ktora rysuje obiekty po klasie czlowiek
        //... prawdopodobnie statyczna funkcja z klasy main.Screen
    }

    public void rysujInterfejs() {

    }

    public void listaGatunkow() {

    }

    public void Rysuj() {

    }

    public void rysujMape() {

    }

    public void komentuj(String komunikat) {
        komunikaty.add(komunikat);
    }

    public void wypiszKomunikaty(int x, int y) {
        if(komunikaty.size() == 0)
            komentuj("Brak nowych komunikatow");

        for(String str : komunikaty)
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

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    if (world[y][x] == null) buff.write(".");
                    else if (world[y][x].getRodzaj() == "CZLOWIEK") buff.write("c");
			        else if (world[y][x].getRodzaj() == "WILK") buff.write("w");
			        else if (world[y][x].getRodzaj() == "OWCA") buff.write("o");
			        else if (world[y][x].getRodzaj() == "LIS") buff.write("l");
			        else if (world[y][x].getRodzaj() == "ZOLW") buff.write("z");
			        else if (world[y][x].getRodzaj() == "ANTYLOPA") buff.write("a");
			        else if (world[y][x].getRodzaj() == "TRAWA") buff.write("t");
			        else if (world[y][x].getRodzaj() == "MLECZ") buff.write("m");
			        else if (world[y][x].getRodzaj() == "GUARANA") buff.write("g");
			        else if (world[y][x].getRodzaj() == "JAGODY") buff.write("j");
			        else if (world[y][x].getRodzaj() == "BARSZCZ") buff.write("b");
                }
                buff.newLine();
            }

            if (tarczaAlzura) buff.write("1");
            else buff.write("0");

            buff.write(turnCount-1);

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
            while (y0 != HEIGHT) {
                n = file.read();
                if ( (char)n == '\n') {
                    x0 = -1;
                    ++y0;
                }
                if((char)n == 'c') return; //add czlowiek;
                else if((char)n == 'w') return; //add wilk
                else if((char)n == 'a') return; //add antylopa
                else if((char)n == 'o') return;//add owca
                else if((char)n == 'z') return; //add zolw
                else if((char)n == 'l') return; //add lis
                else if((char)n == 'g') return; //add guarana
                else if((char)n == 't') return; //add trawa
                else if((char)n == 'm') return; //add mlecz
                else if((char)n == 'j') return; //add jagody
                else if((char)n == 'b') return; //add barszcz
                ++x0;
            }

            a = file.read();
            if(a == 1) tarczaAlzura = true;
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
