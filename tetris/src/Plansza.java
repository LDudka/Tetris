import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Plansza {
    public int szerokosc;
    public int dlugosc;


    class PolePlanszy {
        public boolean czyJest;
        public Color kolor;

        public PolePlanszy() {
            czyJest = false;
        }
    }

    public PolePlanszy[][] pola;

    public Plansza(int szerokosc, int dlugosc)
    {
        this.szerokosc=szerokosc;
        this.dlugosc=dlugosc;
        pola= new PolePlanszy[szerokosc][dlugosc];
        for(int i=0;i<szerokosc;i++)
        {
            for (int j=0;j<dlugosc;j++)
            {
                pola[i][j]= new PolePlanszy();
            }
        }
    }

    void stopowanie(Klocek nowy) {
        pola[nowy.polozenie[0].x][nowy.polozenie[0].y].czyJest = true;
        pola[nowy.polozenie[1].x][nowy.polozenie[1].y].czyJest = true;
        pola[nowy.polozenie[2].x][nowy.polozenie[2].y].czyJest = true;
        pola[nowy.polozenie[3].x][nowy.polozenie[3].y].czyJest = true;

        pola[nowy.polozenie[0].x][nowy.polozenie[0].y].kolor = nowy.kolor;
        pola[nowy.polozenie[1].x][nowy.polozenie[1].y].kolor = nowy.kolor;
        pola[nowy.polozenie[2].x][nowy.polozenie[2].y].kolor = nowy.kolor;
        pola[nowy.polozenie[3].x][nowy.polozenie[3].y].kolor = nowy.kolor;


        ArrayList<Integer> linieDousuniecia = new ArrayList<Integer>();
        linieDousuniecia.add(new Integer(nowy.polozenie[0].y));
        for(int i=1;i<4;i++)
        {
            boolean czyDodac=true;
            for (Integer j: linieDousuniecia)
            {
                if(nowy.polozenie[i].y==j)
                {
                    czyDodac=false;
                    break;
                }
            }
            if(czyDodac)
            {
                linieDousuniecia.add(new Integer(nowy.polozenie[i].y));
            }
        }

        linieDousuniecia.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }

            }
        });

        for (Integer j: linieDousuniecia)
        {
            boolean czyUsuwac = true;
            for (int i = 0; i < 10; i++)
            {
                if (this.pola[i][j].czyJest == false)
                {
                    czyUsuwac = false;
                    break;
                }
            }
            if (czyUsuwac)
            {
                for (int i = 0; i < 10; i++)
                {
                    for (int k = j; k > 0; k--)
                    {
                        pola[i][k].czyJest=pola[i][k-1].czyJest;
                        pola[i][k].kolor=pola[i][k-1].kolor;
                    }
                }
                for (int i = 0; i < 10; i++)
                {
                    pola[i][0].czyJest=false;
                }

                Kontroler.getKontroler().skasowaneLinie++;
                if(Kontroler.getKontroler().skasowaneLinie==5)
                {
                    Kontroler.getKontroler().skasowaneLinie=0;
                    Kontroler.getKontroler().gracz.punkty*=2;
                    Kontroler.getKontroler().poziom++;
                }
                else
                {
                    Kontroler.getKontroler().gracz.punkty++;
                }
            }
        }
    }




}