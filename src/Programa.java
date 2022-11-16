import java.util.ArrayList;
import java.util.Arrays;

public class Programa {
    public static void main(String[] args) {
        Darbuotojas darb1 = new Darbuotojas("Antanas", "Antanauskas", 1502, 989.63, Arrays.asList("pirmadienis", "antradienis"), "administratorius");
        System.out.println("darb1 = " + darb1);
        Darbuotojas darb2 = new Darbuotojas("Vardenis", "Pavardenis", 1378, 1686.57, Arrays.asList("pirmadienis", "trečiadienis", "penktadienis"), "vadovas");
        System.out.println("darb2 = " + darb2);

        // Susikuriu darbuotojų sąrašą
        ArrayList<Darbuotojas> darbuotojai = new ArrayList<>();
        darbuotojai.add(darb1);
        darbuotojai.add(darb2);

        System.out.println("darbuotojai = " + darbuotojai);
    }
}