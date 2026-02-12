package Characters;

public class Trix extends Fata {

    public static String[] possibleTransformations = {"Darkarix", "Darkarix Sirenix", "Darkarix Bloomix", "Darkarix Mythix", "Darkarix Butterflix", "Darkarix Tynix", "Darkarix Cosmix"};
    protected String transformation;

    public Trix(String nome, String type, int hp, int mana, int potenzaMagica, int difesaMagica, int speed, String transformation) {
        super(nome, type, hp, mana, potenzaMagica, difesaMagica, speed);
        this.transformation = transformation;
    }

    //getters
    public String getTransformation() {
        return transformation;
    }

    //setters
    public void chooseTransformation(int t) {
        this.transformation = possibleTransformations[t];
    }

    @Override
    public String toString() {
        return "Trix{" +
                "nome='" + nome + '\'' +
                ", type='" + type + '\'' +
                ", hp=" + hp +
                ", mana=" + mana +
                ", potenzaMagica=" + potenzaMagica +
                ", difesaMagica=" + difesaMagica +
                ", speed=" + speed +
                ", transformation=" + transformation +
                '}';
    }
    
}
