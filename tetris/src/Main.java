import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



/*class KlasaPoRunambel implements Runnable
{

    @Override
    public void run() {

    }
}*/

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Okno();
            }
        });

        //Thread watek= new Thread(new KlasaPoRunambel());

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
