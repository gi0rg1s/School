import java.util.ArrayList;

public class GestoreNotifiche {
    public static void main(String[] args) {
        Notifica emailNotifica = new EmailNotifica();
        Notifica smsNotifica = new SmsNotifica();
        Notifica pushNotifica = new PushNotifica();

        emailNotifica.invia("Ciao via Email!");
        smsNotifica.invia("");
        pushNotifica.invia("Ciao via Push!");

        ArrayList<Notifica> notifiche = new ArrayList<>();
        notifiche.add(emailNotifica);
        notifiche.add(smsNotifica);
        notifiche.add(pushNotifica);

        for (Notifica notifica : notifiche) {
            notifica.invia("Ciao a tutti!");
        }
    }
}
