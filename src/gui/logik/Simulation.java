package gui.logik;

import java.util.PriorityQueue;
import java.util.Random;

public class Simulation
{

    public static void main(String[] args)
    {

        Uhrzeit minAutoAnkunft = new UhrzeitImpl(0, 0, 30);
        Uhrzeit maxAutoAnkunft = new UhrzeitImpl(0, 2, 0);

        Random rand = new Random();

        int minAnkunft = minAutoAnkunft.gesamtZeitSekunden();
        int maxAnkunft = maxAutoAnkunft.gesamtZeitSekunden();

        int naechstesAutoEinfahrtInt = minAnkunft + rand.nextInt(maxAnkunft - minAnkunft);

        Uhrzeit startzeit = new UhrzeitImpl(10, 0, 0);
        Uhrzeit naechstesAutoEinfahrt = new UhrzeitImpl(naechstesAutoEinfahrtInt).addiere(startzeit);

        Uhrzeit autoAbstand = new UhrzeitImpl(3);
        Uhrzeit baustellenZeit = new UhrzeitImpl(20);
        Uhrzeit minParkDauer = new UhrzeitImpl(0, 30, 0);
        Uhrzeit maxParkDauer = new UhrzeitImpl(1, 0, 0);

        int parkplatzKapazitaet = 100;
        int einfahrtKapazitaet = 10;
        Uhrzeit maxRot = new UhrzeitImpl(0, 2, 0);

        ZustandsUmgebung umgebung = new ZustandsUmgebung(autoAbstand, baustellenZeit,
                minParkDauer, maxParkDauer, minAutoAnkunft,
                maxAutoAnkunft, maxRot, parkplatzKapazitaet,
                einfahrtKapazitaet);

        Zustand zustand = new ZustandImpl(0, 0, Zustand.STOP_BEIDE, naechstesAutoEinfahrt,
                startzeit, new PriorityQueue<Uhrzeit>(), new PriorityQueue<Uhrzeit>(),
                naechstesAutoEinfahrt, new UhrzeitImpl(0, 0, 0), umgebung,0);

        System.out.println(zustand);

        for (int i = 0; i < 50; i++)
        {
            zustand = zustand.naechsterZustand();
            IOSystem.loggeZustand(zustand);
            System.out.println(zustand);
        }
    }
}
