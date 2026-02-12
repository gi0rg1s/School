package Characters;

public class Winx extends Fata {

    public static String[] possibleTransformations = {"Magic Winx", "Charmix", "Enchantix", "Believix", "Sirenix", "Bloomix", "Mythix", "Butterflix", "Tynix", "Cosmix"};


    protected String tranformation;
    public Winx(String nome, String type, int hp, int mana, int potenzaMagica, int difesaMagica, int speed, String tranformation) {
        super(nome, type, hp, mana, potenzaMagica, difesaMagica, speed);
        this.tranformation = tranformation;
    }

    //getters
    public String getTranformation() {
        return tranformation;
    }

    //setters
    public void chooseTransformation(int t) {
        this.tranformation = possibleTransformations[t];
    }

    @Override
    public String toString() {
        return "Winx{" +
                "nome='" + nome + '\'' +
                ", type='" + type + '\'' +
                ", hp=" + hp +
                ", mana=" + mana +
                ", potenzaMagica=" + potenzaMagica +
                ", difesaMagica=" + difesaMagica +
                ", speed=" + speed +
                ", transformations=" + tranformation +
                '}';
    }
    
}
