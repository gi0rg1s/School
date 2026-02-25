import java.util.ArrayList;

public class Cassa {
    public static void main(String[] args) {
        MetodoPagamento m1;
        m1 = new CartaDiCredito("1234-5678-9012-3456", 1000.0);
        m1.paga(100.0);

        m1 = new PayPal("user@example.com", 500.0);
        m1.paga(50.0);

        m1 = new BuonoRegalo("BUONO123", 20.0);
        m1.paga(25.0);  //generate error

        ArrayList<MetodoPagamento> metodi = new ArrayList<>();
        metodi.add(new CartaDiCredito("9876-5432-1098-7654", 200.0));
        metodi.add(new PayPal("user2@example.com", 300.0));
        metodi.add(new BuonoRegalo("BUONO456", 50.0));  

        for (MetodoPagamento metodo : metodi) {
            metodo.paga(30.0);
        }
    }
}
