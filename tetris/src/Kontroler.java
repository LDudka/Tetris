import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Kontroler implements KeyListener {

    private static Kontroler instancja=null;

    public static Kontroler getKontroler()
    {
        if(instancja==null)
        {
            instancja = new Kontroler();
        }
        return instancja;
    }

    public Plansza plansza;
    public Klocek aktywnyKlocek, nastepnyKlocek;
    public boolean koniecGry=false;
    public  Gracz gracz;
    public int poziom=1;
    public int skasowaneLinie=0;
    public  Leaderboard leaderBoard;
    public  boolean startGry=false;

    private Kontroler()
    {
        leaderBoard= new Leaderboard();
        gracz=new Gracz();
        plansza=new Plansza(10, 20);
        aktywnyKlocek=Klocek.losuj();
        nastepnyKlocek=Klocek.losuj();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(koniecGry==false && startGry==true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    aktywnyKlocek.ruchLewo();
                    break;
                case KeyEvent.VK_RIGHT:
                    aktywnyKlocek.ruchPrawo();
                    break;
                case KeyEvent.VK_DOWN:
                    aktywnyKlocek.ruchdol();
                    break;
                case KeyEvent.VK_UP:
                    aktywnyKlocek.obrot();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void metodaWatku() throws InterruptedException {
        while (startGry==false)
        {
            Thread.sleep(1000);
        }
        while(koniecGry==false)
        {
            aktywnyKlocek.ruchdol();
            Thread.sleep((int)(1000.0f/(0.9f+(float)Kontroler.getKontroler().poziom/10)));
        }
        leaderBoard.sprobujWstawic(gracz.nick,gracz.punkty);
        leaderBoard.zapisDoPliku();
    }

    public  void restart()
    {
        koniecGry=false;
        skasowaneLinie=0;
        gracz.punkty=0;
        poziom=0;
        for(int i=0;i<plansza.szerokosc;i++)
        {
            for(int j=0;j<plansza.dlugosc;j++)
            {
                plansza.pola[i][j].czyJest=false;
            }
        }
        aktywnyKlocek=Klocek.losuj();
        nastepnyKlocek=Klocek.losuj();

        Thread watek= new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Kontroler.getKontroler().metodaWatku();
                }
                catch (InterruptedException e) {

                }
            }
        });
        watek.start();
    }
}
