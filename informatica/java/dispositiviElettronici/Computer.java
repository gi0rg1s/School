public class Computer implements Dispositivo {

    @Override
    public void turnOn() {
        System.out.println("Computer acceso.");
    }

    @Override
    public void turnOff() {
        System.out.println("Computer spento.");
    }

    public void restart() {
        System.out.println("Computer riavviato.");
    }
}