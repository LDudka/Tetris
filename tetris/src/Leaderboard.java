import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Leaderboard {
    int[] wyniki;
    String[] nicki;

    Leaderboard()
    {
        wyniki= new int[10];
        nicki = new String[10];

        File plik = new File("Wyniki.txt");
        try
        {
            Scanner odczyt = new Scanner(plik);
            for(int i=0;i<10;i++)
            {
                nicki[i]=odczyt.nextLine();
                wyniki[i]=Integer.parseInt(odczyt.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void sprobujWstawic(String graczNick, int graczWynik)
    {
        for(int i=0;i<10;i++)
        {
            if(graczWynik>wyniki[i])
            {
                for(int j=9;j>i;j--)
                {
                    wyniki[j]=wyniki[j-1];
                    nicki[j]=nicki[j-1];
                }
                wyniki[i]=graczWynik;
                nicki[i]=graczNick;
                break;
            }
        }
    }

    public  void zapisDoPliku()
    {

        File plik = new File("Wyniki.txt");
        try
        {
            PrintWriter zapis = new PrintWriter(plik);
            for(int i=0;i<10;i++)
            {
                zapis.println(nicki[i]);
                zapis.println(wyniki[i]);
            }
            zapis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }



    }
}
