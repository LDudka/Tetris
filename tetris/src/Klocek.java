import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Scanner;

public class Klocek {
    public enum Ksztalt
    {
        LINIA,
        SCHODEK,
        ZYGZAKP,
        ZYGZAKL,
        KWADRAT,
        LP,
        LL
    }
    public Scanner odczyt=new Scanner(System.in);
    String strzalka;
    public Ksztalt ksztalt;
    public short obrot;
    public Polozenie[] polozenie= new Polozenie[4];
    public Color kolor;

    public  Klocek()
    {

    }

    public static Klocek losuj()
    {
        Klocek nowy= new Klocek();
        nowy.obrot=0;
        Random random= new Random();
        int wartosc=random.nextInt(7);
        switch(wartosc)
        {
            case 0:
                nowy.ksztalt=Ksztalt.LINIA;
                nowy.kolor=Color.BLUE;
                nowy.polozenie[0]=new Polozenie(3,0);
                nowy.polozenie[1]=new Polozenie(4,0);
                nowy.polozenie[2]=new Polozenie(5,0);
                nowy.polozenie[3]=new Polozenie(6,0);
                break;
            case 1:
                nowy.ksztalt=Ksztalt.LP;
                nowy.kolor=Color.red;
                nowy.polozenie[0]=new Polozenie(3,1);
                nowy.polozenie[1]=new Polozenie(3,0);
                nowy.polozenie[2]=new Polozenie(4,0);
                nowy.polozenie[3]=new Polozenie(5,0);
                break;
            case 2:
                nowy.ksztalt=Ksztalt.LL;
                nowy.kolor=Color.green;
                nowy.polozenie[0]=new Polozenie(3,0);
                nowy.polozenie[1]=new Polozenie(4,0);
                nowy.polozenie[2]=new Polozenie(5,0);
                nowy.polozenie[3]=new Polozenie(5,1);
                break;
            case 3:
                nowy.ksztalt=Ksztalt.ZYGZAKL;
                nowy.kolor=Color.yellow;
                nowy.polozenie[0]=new Polozenie(3,0);
                nowy.polozenie[1]=new Polozenie(4,0);
                nowy.polozenie[2]=new Polozenie(4,1);
                nowy.polozenie[3]=new Polozenie(5,1);
                break;
            case 4:
                nowy.ksztalt=Ksztalt.ZYGZAKP;
                nowy.kolor=Color.ORANGE;
                nowy.polozenie[0]=new Polozenie(3,1);
                nowy.polozenie[1]=new Polozenie(4,1);
                nowy.polozenie[2]=new Polozenie(4,0);
                nowy.polozenie[3]=new Polozenie(5,0);
                break;
            case 5:
                nowy.ksztalt=Ksztalt.KWADRAT;
                nowy.kolor=Color.MAGENTA;
                nowy.polozenie[0]=new Polozenie(3,0);
                nowy.polozenie[1]=new Polozenie(3,1);
                nowy.polozenie[2]=new Polozenie(4,0);
                nowy.polozenie[3]=new Polozenie(4,1);
                break;
            case 6:
                nowy.ksztalt=Ksztalt.SCHODEK;
                nowy.kolor=Color.gray;
                nowy.polozenie[0]=new Polozenie(3,1);
                nowy.polozenie[1]=new Polozenie(4,0);
                nowy.polozenie[2]=new Polozenie(4,1);
                nowy.polozenie[3]=new Polozenie(5,1);
                break;
        }
        return  nowy;
    }

    public void ruchLewo()
    {
        boolean czyMozliwyRuch=true;
        for(int i=0;i<4;i++)
        {
            if(polozenie[i].x==0||Kontroler.getKontroler().plansza.pola[polozenie[i].x-1][polozenie[i].y].czyJest==true)
            {
                czyMozliwyRuch=false;
                break;
            }
        }
        if(czyMozliwyRuch)
        {
            for (int i = 0; i < 4; i++)
            {
                polozenie[i].x = polozenie[i].x - 1;
            }
        }
    }

    public void ruchPrawo()
    {
        boolean czyMozliwyRuch=true;
        for(int i=0;i<4;i++)
        {
            if(polozenie[i].x==9||Kontroler.getKontroler().plansza.pola[polozenie[i].x+1][polozenie[i].y].czyJest==true)
            {
                czyMozliwyRuch=false;
                break;
            }
        }
        if(czyMozliwyRuch)
        {
            for (int i = 0; i < 4; i++)
            {
                polozenie[i].x = polozenie[i].x + 1;
            }
        }
    }

    public void obrot()
    {
        Plansza plansza= Kontroler.getKontroler().plansza;

        switch (this.ksztalt)
        {
            case LINIA:
                //[][][][]
                if(obrot==0)
                {
                    if(this.polozenie[1].y - 1>=0 && this.polozenie[1].y + 2 <=19 &&
                        plansza.pola[this.polozenie[1].x][this.polozenie[1].y - 1].czyJest==false &&
                            plansza.pola[this.polozenie[1].x][this.polozenie[1].y + 1].czyJest==false &&
                            plansza.pola[this.polozenie[1].x][this.polozenie[1].y + 2].czyJest==false
                    )
                    {
                        this.polozenie[0].x = this.polozenie[1].x;
                        this.polozenie[2].x = this.polozenie[1].x;
                        this.polozenie[3].x = this.polozenie[1].x;

                        this.polozenie[0].y = this.polozenie[1].y - 1;
                        this.polozenie[2].y = this.polozenie[1].y + 1;
                        this.polozenie[3].y = this.polozenie[1].y + 2;
                        obrot = 1;
                    }
                }
                //[]
                //[]
                //[]
                //[]
                else
                {
                    if(this.polozenie[1].x-1>=0 && this.polozenie[1].x+2<=9 &&
                        plansza.pola[polozenie[1].x-1][polozenie[1].y].czyJest==false&&
                        plansza.pola[polozenie[1].x+1][polozenie[1].y].czyJest==false&&
                        plansza.pola[polozenie[1].x+2][polozenie[1].y].czyJest==false)

                        {
                        this.polozenie[0].x = this.polozenie[1].x - 1;
                        this.polozenie[2].x = this.polozenie[1].x + 1;
                        this.polozenie[3].x = this.polozenie[1].x + 2;

                        this.polozenie[0].y = this.polozenie[1].y;
                        this.polozenie[2].y = this.polozenie[1].y;
                        this.polozenie[3].y = this.polozenie[1].y;
                        obrot = 0;
                    }
                }
                break;

            case SCHODEK:
                if(obrot==0)
                {
                    if(this.polozenie[2].y+1<=19 && plansza.pola[polozenie[2].x][polozenie[2].y+1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x;


                        this.polozenie[0].y = this.polozenie[2].y + 1;

                        obrot = 1;
                    }
                }
                else if(obrot==1)
                {
                    if(this.polozenie[2].x-1>=0 && plansza.pola[polozenie[2].x-1][polozenie[2].y].czyJest==false)
                    {
                        this.polozenie[1].x = this.polozenie[2].x - 1;

                        this.polozenie[1].y = this.polozenie[2].y;

                        obrot = 2;
                    }
                }
                else if(obrot==2) {
                    if (this.polozenie[2].y - 1 >= 0 && plansza.pola[polozenie[2].x][polozenie[2].y - 1].czyJest == false)
                    {
                        this.polozenie[3].x = this.polozenie[2].x;

                        this.polozenie[3].y = this.polozenie[2].y - 1;
                        obrot = 3;
                    }
                }
                else if(obrot==3)
                {
                    if(this.polozenie[2].x+1<=9 && plansza.pola[polozenie[2].x+1][polozenie[2].y].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x - 1;
                        this.polozenie[0].y = this.polozenie[2].y;

                        this.polozenie[1].x = this.polozenie[2].x;
                        this.polozenie[1].y = this.polozenie[2].y - 1;

                        this.polozenie[3].x = this.polozenie[2].x + 1;
                        this.polozenie[3].y = this.polozenie[2].y;
                        obrot = 0;
                    }
                }
                break;

            case ZYGZAKP:
                if(obrot==0)
                {
                    if(this.polozenie[2].y-1>=0 && this.polozenie[2].y+1<=19 &&
                            plansza.pola[polozenie[2].x-1][polozenie[2].y-1].czyJest==false
                            && plansza.pola[polozenie[2].x-1][polozenie[2].y].czyJest==false
                            && plansza.pola[polozenie[2].x][polozenie[2].y+1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x - 1;
                        this.polozenie[0].y = this.polozenie[2].y - 1;

                        this.polozenie[1].x = this.polozenie[2].x - 1;
                        this.polozenie[1].y = this.polozenie[2].y;

                        this.polozenie[3].x = this.polozenie[2].x;
                        this.polozenie[3].y = this.polozenie[2].y + 1;

                        obrot = 1;
                    }
                }

                else if(obrot == 1)
                {
                    if(this.polozenie[2].x-1>=0 && this.polozenie[2].x+1<=9 &&
                        plansza.pola[polozenie[2].x+1][polozenie[2].y-1].czyJest==false &&
                        plansza.pola[polozenie[2].x][polozenie[2].y-1].czyJest==false &&
                        plansza.pola[polozenie[2].x-1][polozenie[2].y].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x + 1;
                        this.polozenie[0].y = this.polozenie[2].y - 1;

                        this.polozenie[1].x = this.polozenie[2].x;
                        this.polozenie[1].y = this.polozenie[2].y - 1;

                        this.polozenie[3].x = this.polozenie[2].x - 1;
                        this.polozenie[3].y = this.polozenie[2].y;

                        obrot = 0;
                    }
                }
                    break;

            case ZYGZAKL:
                if(obrot==0)
                {
                    if(this.polozenie[2].y-1>=0 && this.polozenie[2].y+1<=19
                        && plansza.pola[polozenie[2].x+1][polozenie[2].y-1].czyJest==false
                        && plansza.pola[polozenie[2].x+1][polozenie[2].y].czyJest==false
                        && plansza.pola[polozenie[2].x][polozenie[2].y+1].czyJest==false)
                    {

                        this.polozenie[0].x = this.polozenie[2].x + 1;
                        this.polozenie[0].y = this.polozenie[2].y - 1;

                        this.polozenie[1].x = this.polozenie[2].x + 1;
                        this.polozenie[1].y = this.polozenie[2].y;

                        this.polozenie[3].x = this.polozenie[2].x;
                        this.polozenie[3].y = this.polozenie[2].y + 1;

                        obrot = 1;
                    }
                }
                else if(obrot==1)
                {
                    if(this.polozenie[2].x-1>=0 && this.polozenie[2].x+1<=9
                            && plansza.pola[polozenie[2].x-1][polozenie[2].y-1].czyJest==false
                            && plansza.pola[polozenie[2].x][polozenie[2].y-1].czyJest==false
                            && plansza.pola[polozenie[2].x+1][polozenie[2].y].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x - 1;
                        this.polozenie[0].y = this.polozenie[2].y - 1;

                        this.polozenie[1].x = this.polozenie[2].x;
                        this.polozenie[1].y = this.polozenie[2].y - 1;

                        this.polozenie[3].x = this.polozenie[2].x + 1;
                        this.polozenie[3].y = this.polozenie[2].y;

                        obrot = 0;
                    }
                }
                break;

            case LP:
                if(obrot==0)
                {
                    if(this.polozenie[2].y-1>=0 && this.polozenie[2].y+1<=19
                            && plansza.pola[polozenie[2].x-1][polozenie[2].y-1].czyJest==false
                            && plansza.pola[polozenie[2].x][polozenie[2].y-1].czyJest==false
                            && plansza.pola[polozenie[2].x][polozenie[2].y+1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x - 1;
                        this.polozenie[0].y = this.polozenie[2].y - 1;

                        this.polozenie[1].x = this.polozenie[2].x;
                        this.polozenie[1].y = this.polozenie[2].y - 1;

                        this.polozenie[3].x = this.polozenie[2].x;
                        this.polozenie[3].y = this.polozenie[2].y + 1;

                        obrot = 1;
                    }
                }
                else if(obrot==1) {
                    if (this.polozenie[2].x - 1 >= 0 && this.polozenie[2].x + 1 <= 9
                            && plansza.pola[polozenie[2].x + 1][polozenie[2].y - 1].czyJest == false
                            && plansza.pola[polozenie[2].x + 1][polozenie[2].y].czyJest == false
                            && plansza.pola[polozenie[2].x - 1][polozenie[2].y].czyJest == false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x + 1;
                        this.polozenie[0].y = this.polozenie[2].y - 1;

                        this.polozenie[1].x = this.polozenie[2].x + 1;
                        this.polozenie[1].y = this.polozenie[2].y;

                        this.polozenie[3].x = this.polozenie[2].x - 1;
                        this.polozenie[3].y = this.polozenie[2].y;

                        obrot = 2;
                    }
                }
                else if(obrot==2)
                {
                    if(this.polozenie[2].y-1>=0 && this.polozenie[2].y+1<=19
                            && plansza.pola[polozenie[2].x+1][polozenie[2].y+1].czyJest==false
                            && plansza.pola[polozenie[2].x][polozenie[2].y+1].czyJest==false
                            && plansza.pola[polozenie[2].x][polozenie[2].y-1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[2].x + 1;
                        this.polozenie[0].y = this.polozenie[2].y + 1;

                        this.polozenie[1].x = this.polozenie[2].x;
                        this.polozenie[1].y = this.polozenie[2].y + 1;

                        this.polozenie[3].x = this.polozenie[2].x;
                        this.polozenie[3].y = this.polozenie[2].y - 1;

                        obrot = 3;
                    }
                }
                else if(obrot==3)
            {
                if (this.polozenie[2].x - 1 >= 0 && this.polozenie[2].x + 1 <= 9
                        && plansza.pola[polozenie[2].x - 1][polozenie[2].y + 1].czyJest == false
                        && plansza.pola[polozenie[2].x - 1][polozenie[2].y].czyJest == false
                        && plansza.pola[polozenie[2].x + 1][polozenie[2].y].czyJest == false)
                {
                    this.polozenie[0].x = this.polozenie[2].x - 1;
                    this.polozenie[0].y = this.polozenie[2].y + 1;

                    this.polozenie[1].x = this.polozenie[2].x - 1;
                    this.polozenie[1].y = this.polozenie[2].y;

                    this.polozenie[3].x = this.polozenie[2].x + 1;
                    this.polozenie[3].y = this.polozenie[2].y;

                    obrot = 0;
                }
            }
                break;

            case LL:
                if(obrot==0)
                {
                    if(this.polozenie[2].y-1>=0 && this.polozenie[2].y+1<=19
                            && plansza.pola[polozenie[1].x][polozenie[1].y-1].czyJest==false
                            && plansza.pola[polozenie[1].x][polozenie[1].y+1].czyJest==false
                            && plansza.pola[polozenie[1].x-1][polozenie[1].y+1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[1].x;
                        this.polozenie[0].y = this.polozenie[1].y - 1;

                        this.polozenie[2].x = this.polozenie[1].x;
                        this.polozenie[2].y = this.polozenie[1].y + 1;

                        this.polozenie[3].x = this.polozenie[1].x - 1;
                        this.polozenie[3].y = this.polozenie[1].y + 1;

                        obrot = 1;
                    }
                }
                else if(obrot==1)
                {
                    if(this.polozenie[2].x-1>=0 && this.polozenie[2].x+1<=9
                            && plansza.pola[polozenie[1].x+1][polozenie[1].y].czyJest==false
                            && plansza.pola[polozenie[1].x-1][polozenie[1].y].czyJest==false
                            && plansza.pola[polozenie[1].x-1][polozenie[1].y-1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[1].x + 1;
                        this.polozenie[0].y = this.polozenie[1].y;

                        this.polozenie[2].x = this.polozenie[1].x - 1;
                        this.polozenie[2].y = this.polozenie[1].y;

                        this.polozenie[3].x = this.polozenie[1].x - 1;
                        this.polozenie[3].y = this.polozenie[1].y - 1;

                        obrot = 2;
                    }
                }
                else if(obrot==2)
                {
                    if(this.polozenie[2].y-1>=0 && this.polozenie[2].y+1<=19
                            && plansza.pola[polozenie[1].x][polozenie[1].y+1].czyJest==false
                            && plansza.pola[polozenie[1].x][polozenie[1].y-1].czyJest==false
                            && plansza.pola[polozenie[1].x+1][polozenie[1].y-1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[1].x;
                        this.polozenie[0].y = this.polozenie[1].y + 1;

                        this.polozenie[2].x = this.polozenie[1].x;
                        this.polozenie[2].y = this.polozenie[1].y - 1;

                        this.polozenie[3].x = this.polozenie[1].x + 1;
                        this.polozenie[3].y = this.polozenie[1].y - 1;

                        obrot = 3;
                    }
                }
                else if(obrot==3)
                {
                    if(this.polozenie[2].x-1>=0 && this.polozenie[2].x+1<=9
                            && plansza.pola[polozenie[1].x-1][polozenie[1].y].czyJest==false
                            && plansza.pola[polozenie[1].x+1][polozenie[1].y].czyJest==false
                            && plansza.pola[polozenie[1].x+1][polozenie[1].y+1].czyJest==false)
                    {
                        this.polozenie[0].x = this.polozenie[1].x - 1;
                        this.polozenie[0].y = this.polozenie[1].y;

                        this.polozenie[2].x = this.polozenie[1].x + 1;
                        this.polozenie[2].y = this.polozenie[1].y;

                        this.polozenie[3].x = this.polozenie[1].x + 1;
                        this.polozenie[3].y = this.polozenie[1].y + 1;

                        obrot = 0;
                    }
                }
                break;
        }
    }

    public void ruchdol()
    {
        boolean czyMozliwyRuch=true;
        for(int i=0;i<4;i++)
        {
            if(polozenie[i].y==19||Kontroler.getKontroler().plansza.pola[polozenie[i].x][polozenie[i].y+1].czyJest==true)
            {
                czyMozliwyRuch=false;
                break;
            }
        }
        if(czyMozliwyRuch)
        {
            for (int i = 0; i < 4; i++)
            {
                polozenie[i].y = polozenie[i].y + 1;
            }
        }
        else
        {
            Kontroler.getKontroler().plansza.stopowanie(this);


            for(int i=0;i<Kontroler.getKontroler().nastepnyKlocek.polozenie.length;i++)
            {
                if(Kontroler.getKontroler().plansza.pola[Kontroler.getKontroler().nastepnyKlocek.polozenie[i].x][Kontroler.getKontroler().nastepnyKlocek.polozenie[i].y].czyJest==true)
                {
                    Kontroler.getKontroler().koniecGry=true;
                    break;
                }
            }

            if(Kontroler.getKontroler().koniecGry==false)
            {
                Kontroler.getKontroler().aktywnyKlocek=Kontroler.getKontroler().nastepnyKlocek;
                Kontroler.getKontroler().nastepnyKlocek=Klocek.losuj();
            }
        }
    }
}