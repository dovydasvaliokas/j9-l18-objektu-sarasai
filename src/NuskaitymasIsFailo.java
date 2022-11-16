import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NuskaitymasIsFailo {
    public static void main(String[] args) {
        ArrayList<Darbuotojas> imonesDarbuotojai = new ArrayList<>();
        try {
            imonesDarbuotojai = nuskaitytiDarbuotojusIsFailo("darbuotojai.csv");
        }
        catch (FileNotFoundException e) {
            System.out.println("Programa nerado jūsų failo");
        }
        isvestiKiekvienoBonusa(imonesDarbuotojai);
    }

    public static ArrayList<Darbuotojas> nuskaitytiDarbuotojusIsFailo(String failoAdresas) throws FileNotFoundException {
        File failas = new File(failoAdresas);
        Scanner skaitytuvas = new Scanner(failas);
        skaitytuvas.nextLine();
        ArrayList<Darbuotojas> darbuotojai = new ArrayList<>();
        while (skaitytuvas.hasNextLine()) {
            String eilute = skaitytuvas.nextLine();
            String[] isskaldytaEilute = eilute.split(",");
            Darbuotojas darb = new Darbuotojas();
            darb.setVardas(isskaldytaEilute[0]);
            darb.setPavarde(isskaldytaEilute[1]);
            darb.setId(Integer.parseInt(isskaldytaEilute[2]));
            darb.setAlga(Double.parseDouble(isskaldytaEilute[3]));
            String[] darboDienos = isskaldytaEilute[4].split(";");
            darb.setGrafikoDienos(Arrays.asList(darboDienos));
            darb.setPareigos(isskaldytaEilute[5]);
            darbuotojai.add(darb);
        }
        return darbuotojai;
    }

    public static void isvestiKiekvienoBonusa(ArrayList<Darbuotojas> darbuotojai) {
        for (Darbuotojas darbuotojas : darbuotojai) {
            System.out.println("darbuotojas = " + darbuotojas );
            System.out.println("Darbuotojo bonusas = " + darbuotojas.apskaiciuotiBonusa());
            double bendraAlga = darbuotojas.getAlga() + darbuotojas.apskaiciuotiBonusa();
            System.out.println("Iš viso darbuotojas turėtų gauti: " + bendraAlga);
        }
    }
}
