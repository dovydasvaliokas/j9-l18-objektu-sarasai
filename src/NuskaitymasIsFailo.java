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
        System.out.println("visuDarbuotojuSuma(imonesDarbuotojai) = " + visuDarbuotojuSuma(imonesDarbuotojai));
        Darbuotojas maxUzdirbantis = daugiausiaiUzdirbantisDarbuotojas(imonesDarbuotojai);
        System.out.println("maxUzdirbantis = " + maxUzdirbantis);

        System.out.println("filtruotiPagalPareigas(imonesDarbuotojai, \"vadovas\") = " + filtruotiPagalPareigas(imonesDarbuotojai, "vadovas"));
        System.out.println("filtruotiPagalPareigas(imonesDarbuotojai, \"vairuotojas\") = " + filtruotiPagalPareigas(imonesDarbuotojai, "vairuotojas"));
    }

    /**
     * Nuskaito darbuotojų sąrašą iš csv failo atskirto kableliais.
     * Pirmą failo eilutę praleidžia, tiesiog nuskaito ir niekur jos neišsaugo. Toliau, suka ciklą iki failo pabaigos ir nuskaito vis po vieną eilutę
     * Tą eilutę skaido į String[] masyvą per kablelį kaip skyriklį. Atskirtas dalis po vieną sudeda į darbuotojo objektą
     * Tą darbuotojo objektą įdeda į ArrayList ir vėl suka ciklą per naujo, kol galiausiai faile nebelieka eilučių.
     * @param failoAdresas csv failo pavadinimas (adresas iki jo)
     * @return darbuotojų sąrašą
     * @throws FileNotFoundException išmeta, jeigu tokio failo nėra
     */
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

    /**
     * Išveda kiekvieno darbuotojo informaciją, jo bonusą bei bendrą algą, t.y. jo bonusą sudėtą su alga
     * apskaiciuotiBonusa() funkcija yra Darbuotojas klasės vidinė funkcija, kurią turi kiekvienas darbuotojas
     * @param darbuotojai darbuotojų sąrašas
     */
    public static void isvestiKiekvienoBonusa(ArrayList<Darbuotojas> darbuotojai) {
        for (Darbuotojas darbuotojas : darbuotojai) {
            System.out.println("darbuotojas = " + darbuotojas );
            System.out.println("Darbuotojo bonusas = " + darbuotojas.apskaiciuotiBonusa());
            double bendraAlga = darbuotojas.getAlga() + darbuotojas.apskaiciuotiBonusa();
            System.out.println("Iš viso darbuotojas turėtų gauti: " + bendraAlga);
        }
    }

    /**
     * Apskaičiuoja visų darbuotojų bendrą sumą
     * @param darbuotojai darbuotojų sąrašas
     * @return sumą
     */
    public static double visuDarbuotojuSuma(ArrayList<Darbuotojas> darbuotojai) {
        double suma = 0;
        for (Darbuotojas darbuotojas : darbuotojai) {
            suma += darbuotojas.getAlga();
        }
        return suma;
    }

    /**
     * Suranda daugiausiai uždirbantį darbuotoją. Iš pradžių sakome, jog pirmas darbuotojas yra LAIKINAI daugiausiai uždirbantis,
     * Tada einame per visus darbuotojus ir kiekvieno darbuotojo algą lyginame su LAIKINAI daugiausiai uždirbančio alga
     * Jeigu dabartinio darbuotojo alga yra didesnė už LAIKINAI didžiausio, reiškias radome naują LAIKINAI daugiausiai uždirbantį
     * Kai pereisime per visus darbuotojus, tas laikinai didžiausiai uždirbantis jau bus GALUTINAI daugiausiai uždirbantis
     * @param darbuotojai darbuotojų sąrašas
     * @return daugiausiai uždirbančio darbuotojo objektas
     */
    public static Darbuotojas daugiausiaiUzdirbantisDarbuotojas(ArrayList<Darbuotojas> darbuotojai) {
        Darbuotojas maxDarbuotojas = darbuotojai.get(0);
        for (Darbuotojas darbuotojas : darbuotojai) {
            if (darbuotojas.getAlga() > maxDarbuotojas.getAlga()) {
                maxDarbuotojas = darbuotojas;
            }
        }
        return maxDarbuotojas;
    }

    /**
     * Išfiltruoja darbuotojus pagal pareigas. Susikuriame naują sąrašą, kuris yra tuščias. Einame per visus darbuotojus
     * Ir jeigu tas darbuotojas turi tokias pareigas, kurių ieškome filtravime, tai jį įdedame į tuščią sąrašą.
     * Jeigu neturi - einame prie sekančių darbuotojų
     * @param visiDarbuotojai visų darbuotojų sąrašas
     * @param ieskomosPareigos pareigos, kurių darbuotojus norime rasti
     * @return darbuotojų sąrašą, kurie turi ieškomas pareigas
     */
    public static ArrayList<Darbuotojas> filtruotiPagalPareigas(ArrayList<Darbuotojas> visiDarbuotojai, String ieskomosPareigos) {
        ArrayList<Darbuotojas> isfiltruotiDarbuotojai = new ArrayList<>();
        for (Darbuotojas darbuotojas : visiDarbuotojai) {
            if (darbuotojas.getPareigos().equals(ieskomosPareigos)) {
                isfiltruotiDarbuotojai.add(darbuotojas);
            }
        }
        return isfiltruotiDarbuotojai;
    }
}
