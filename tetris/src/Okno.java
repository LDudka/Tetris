import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Okno extends JFrame
{

    Timer timer;
    boolean wyswietlonyPanelKoncaGry=false;

    public class WynikiPanel extends  JPanel
    {
        JTextField[] wynikiGraczy;
        JTextField napisWyniki;

        public  WynikiPanel()
        {
            setSize(new Dimension(350, 1000));

            FlowLayout layout= new FlowLayout();
            setLayout(layout);

            Okno.this.setLayout(null );

            napisWyniki= new JTextField();

            napisWyniki.setFont(new Font("Arial",0,30));
            napisWyniki.setEditable(false);

            this.add(napisWyniki);

            wynikiGraczy= new JTextField[10];

            for(int i=0;i<10;i++)
            {
                wynikiGraczy[i]= new JTextField();

                wynikiGraczy[i].setFont(new Font("Arial",0,20));
                wynikiGraczy[i].setEditable(false);

                this.add(wynikiGraczy[i]);
            }
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            napisWyniki.setSize(280, 100);
            napisWyniki.setLocation(new Point(0,100));
            napisWyniki.setText("Wyniki: ");

            for(int i=0;i<10;i++)
            {
                wynikiGraczy[i].setSize(280, 75);
                wynikiGraczy[i].setLocation(new Point(0,200+75*i));
                wynikiGraczy[i].setText(Kontroler.getKontroler().leaderBoard.nicki[i]+" "+Kontroler.getKontroler().leaderBoard.wyniki[i]);
            }
        }
    }


    public class KoniecGryPanel extends  JPanel
    {
        JTextField poziomWartosc;
        JTextField punktyWartosc;

        JButton przyciskRestart;
        JTextField komunikat;

        public  KoniecGryPanel()
        {

            setSize(new Dimension(350, 1000));

            komunikat= new JTextField();
            komunikat.setVisible(false);
            this.add(komunikat);

            //FlowLayout layout= new FlowLayout();
            SpringLayout layout = new SpringLayout();
            setLayout(layout);

            Okno.this.setLayout(layout );

            przyciskRestart=new JButton("Restart");
            layout.putConstraint(SpringLayout.NORTH,przyciskRestart,400, SpringLayout.NORTH,this);
            layout.putConstraint(SpringLayout.NORTH,komunikat,275,SpringLayout.NORTH,this);

            //przyciskRestart.setSize(300, 300);
            //przyciskRestart.setLocation(new Point(150,220));

            przyciskRestart.setFont(new Font("Arial",0,60));

            przyciskRestart.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Kontroler.getKontroler().restart();
                    przyciskRestart.setVisible(false);
                    komunikat.setVisible(false);
                    wyswietlonyPanelKoncaGry=false;
                }
            });
            add(przyciskRestart);
            przyciskRestart.setVisible(false);


            poziomWartosc= new JTextField();

            poziomWartosc.setFont(new Font("Arial",0,60));
            poziomWartosc.setEditable(false);

            this.add(poziomWartosc);

            punktyWartosc= new JTextField();

            punktyWartosc.setFont(new Font("Arial",0,50));
            punktyWartosc.setEditable(false);

            this.add(punktyWartosc);

        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            poziomWartosc.setSize(280, 100);
            poziomWartosc.setLocation(new Point(0,525));
            poziomWartosc.setText("Poziom: "+Kontroler.getKontroler().poziom);
            punktyWartosc.setSize(280, 100);
            punktyWartosc.setLocation(new Point(0,625));
            punktyWartosc.setText("Punkty: "+Kontroler.getKontroler().gracz.punkty);


            if(Kontroler.getKontroler().koniecGry==true)
            {
                if(wyswietlonyPanelKoncaGry==false)
                {
                    komunikat.setSize(280, 100);
                    komunikat.setLocation(new Point(0,425));
                    komunikat.setText("Koniec gry");

                    komunikat.setEditable(false);
                    komunikat.setFont(new Font("Arial",0,60));

                    komunikat.setVisible(true);

                    przyciskRestart.setVisible(true);
                    przyciskRestart.setSize(280, 100);
                    przyciskRestart.setLocation(new Point(0,125));

                    wyswietlonyPanelKoncaGry=true;
                }
               // Line2D linia= new Line2D.Float(20,20,200,200);
                //g2d.draw(linia);

            }
        }
    }

    public class PlanszaPanel extends JPanel
    {
        Line2D[] liniePionowe;
        Line2D[] liniePoziome;
        Rectangle2D[][] pola;

        JButton przycisk;

        public PlanszaPanel() {
            setSize(new Dimension(800, 1000));

            przycisk=new JButton("Graj");

            przycisk.setSize(300, 300);
            przycisk.setLocation(new Point(150,220));

            przycisk.setFont(new Font("Arial",0,60));

            przycisk.addActionListener(new ActionListener()
            {
                 @Override
                 public void actionPerformed(ActionEvent e)
                 {
                     Kontroler.getKontroler().startGry=true;
                     przycisk.setVisible(false);
                 }
            });
            add(przycisk);



            liniePionowe = new Line2D[15];
            liniePoziome= new Line2D[25];

            for(int i=0;i<11;i++)
            {
                liniePionowe[i]= new Line2D.Float(40.0f *i+200, 100.0f,40.0f *i+200,900.0f);
            }
            for(int i=0;i<21;i++)
            {
                liniePoziome[i]= new Line2D.Float(200.0f, 40.0f*i+100,600.0f,40.0f*i+100);
            }

            liniePionowe[11]= new Line2D.Float(199, 98,199,902.0f);
            liniePionowe[12]= new Line2D.Float(198, 98,198,902.0f);
            liniePionowe[13]= new Line2D.Float(601, 98,601,902.0f);
            liniePionowe[14]= new Line2D.Float(602, 98,602,902.0f);



            liniePoziome[21]= new Line2D.Float(198.0f, 99,602.0f,99);
            liniePoziome[22]= new Line2D.Float(198.0f, 98,602.0f,98);
            liniePoziome[23]= new Line2D.Float(198.0f, 901,602.0f,901);
            liniePoziome[24]= new Line2D.Float(198.0f, 902,602.0f,902);

            Plansza plansza=Kontroler.getKontroler().plansza;
            pola=new Rectangle2D[plansza.szerokosc][plansza.dlugosc];
            for(int i=0;i<plansza.szerokosc;i++)
            {
                for(int j=0;j<plansza.dlugosc;j++)
                {
                    pola[i][j]= new Rectangle2D.Float(i*40.0f+201,j*40.0f+101,38.0f,38.0f);
                }
            }

            /*
            liniePionowe[11]= new Line2D.Float(40.0f *11+161, 100.0f,40.0f*11+161,900.0f);
            liniePionowe[12]= new Line2D.Float(40.0f *12+122, 100.0f,40.0f *12+122,900.0f);
            liniePionowe[13]= new Line2D.Float(40.0f+159, 100.0f,40.0f+159,900.0f);
            liniePionowe[14]= new Line2D.Float(40.0f+158, 100.0f,40.0f+158,900.0f);



            liniePoziome[21]= new Line2D.Float(198.0f, 40.0f+59,602.0f,40.0f+59);
            liniePoziome[22]= new Line2D.Float(198.0f, 40.0f+58,602.0f,40.0f+58);
            liniePoziome[23]= new Line2D.Float(198.0f, 40.0f*23-18,602.0f,40.0f*23-18);
            liniePoziome[24]= new Line2D.Float(198.0f, 40.0f*23-19,602.0f,40.0f*23-19);
            */
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            if(Kontroler.getKontroler().startGry==false)
            {
                przycisk.setSize(300, 200);
                przycisk.setLocation(new Point(275,300));
            }
            else {

                for (int i = 0; i < liniePionowe.length; i++) {
                    g2d.draw(liniePionowe[i]);
                }
                for (int i = 0; i < liniePoziome.length; i++) {
                    g2d.draw(liniePoziome[i]);
                }

                Rectangle2D[] aktywnyKlocek = new Rectangle2D[4];
                for (int i = 0; i < 4; i++) {
                    aktywnyKlocek[i] = new Rectangle2D.Float(Kontroler.getKontroler().aktywnyKlocek.polozenie[i].x * 40.0f + 201, Kontroler.getKontroler().aktywnyKlocek.polozenie[i].y * 40.0f + 101, 38.0f, 38.0f);
                }

                g2d.setPaint(Kontroler.getKontroler().aktywnyKlocek.kolor);
                for (int i = 0; i < 4; i++) {
                    g2d.fill(aktywnyKlocek[i]);
                    g2d.draw(aktywnyKlocek[i]);
                }

                Plansza plansza = Kontroler.getKontroler().plansza;

                for (int i = 0; i < plansza.szerokosc; i++) {
                    for (int j = 0; j < plansza.dlugosc; j++) {
                        if (plansza.pola[i][j].czyJest == true) {
                            g2d.setPaint(plansza.pola[i][j].kolor);
                            g2d.fill(pola[i][j]);
                            g2d.draw(pola[i][j]);
                        }
                    }
                }
            }
        }
    }

    public void refreshScreen() {
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.setRepeats(true);
        // Aprox. 60 FPS
        timer.setDelay(17);
        timer.start();
    }

    Okno()
    {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ImageIcon ikona= new ImageIcon());
        //setIconImage(ikona);

        setSize(1500, 1000);


        //JButton przycisk=new JButton("Generuj");

        setFocusable(true);

        //przycisk.setSize(300, 300);
        //przycisk.setLocation(new Point(150,120));
        setResizable(false);
        //komunikat.setEditable(false);
        //komunikat.setFont(new Font("Arial",0,30));
        //przycisk.addActionListener(new ActionListener() {

           // @Override
            //public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                //komunikat.setText(new wnetrze().generuj());
          //  }
        //});

        //FlowLayout layout= new FlowLayout();
        //setLayout(layout);

        PlanszaPanel panelPlanszy= new PlanszaPanel();
        panelPlanszy.setLocation(new Point(350,0));
        add(panelPlanszy);

        KoniecGryPanel panelKoniecGry= new KoniecGryPanel();
        panelKoniecGry.setLocation(new Point(1150,0));

        WynikiPanel wynikiPanel= new WynikiPanel();
        wynikiPanel.setLocation(new Point(0,0));
//
//        JTextField komunikat= new JTextField();
//        komunikat.setSize(300, 50);
//        komunikat.setLocation(new Point(0,0));
//        komunikat.setText("hasło:");
//
//        panelKoniecGry.add(komunikat);

        add(panelKoniecGry);
        add(wynikiPanel);


        setLayout(null);
        //add(przycisk);
        //this.add(komunikat);

        //JPanel panel = new JPanel();
        //add(panel);
        //panel.add(comp)

        setVisible(true);

        //System.out.println("abc");

        addKeyListener(Kontroler.getKontroler());

        refreshScreen();
    }


}
