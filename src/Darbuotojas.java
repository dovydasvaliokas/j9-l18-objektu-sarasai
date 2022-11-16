import java.util.ArrayList;
import java.util.List;

public class Darbuotojas {
    private String vardas;
    private String pavarde;
    private int id;
    private double alga;
    private List<String> grafikoDienos;
    private String pareigos;

    public Darbuotojas() {
    }

    public Darbuotojas(String vardas, String pavarde, int id, double alga, List<String> grafikoDienos, String pareigos) {
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.id = id;
        this.alga = alga;
        this.grafikoDienos = grafikoDienos;
        this.pareigos = pareigos;
    }

    //---Klasės funkcijos
    public double apskaiciuotiBonusa() {
        if (alga < 1000) {
            return 100;
        }
        else {
            return 200;
        }
    }

    // Getteriai ir setteriai
    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAlga() {
        return alga;
    }

    public void setAlga(double alga) {
        this.alga = alga;
    }

    public List<String> getGrafikoDienos() {
        return grafikoDienos;
    }

    public void setGrafikoDienos(List<String> grafikoDienos) {
        this.grafikoDienos = grafikoDienos;
    }

    public String getPareigos() {
        return pareigos;
    }

    public void setPareigos(String pareigos) {
        this.pareigos = pareigos;
    }

    @Override
    public String toString() {
        return "Darbuotojas{" +
                "vardas='" + vardas + '\'' +
                ", pavarde='" + pavarde + '\'' +
                ", id=" + id +
                ", alga=" + alga +
                ", grafikoDienos=" + grafikoDienos +
                ", pareigos='" + pareigos + '\'' +
                '}';
    }
}
