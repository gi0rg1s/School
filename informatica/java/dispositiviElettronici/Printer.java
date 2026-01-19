public class Printer implements Dispositivo {

    @Override
    public void turnOn() {
        System.out.println("Printer acceso.");
    }

    @Override
    public void turnOff() {
        System.out.println("Printer spento.");
    }
    
}
