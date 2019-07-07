import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gracz {
    String nick;
    int punkty;

    Gracz()
    {
        punkty=0;

        File plik = new File("Gracz.txt");
        try
        {
            Scanner odczyt = new Scanner(plik);
            nick=odczyt.nextLine();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
