public class Smartphone implements Dispositivo {
    
    @Override
    public void turnOn() {
        System.out.println("Smartphone acceso.");
    }

    @Override
    public void turnOff() {
        System.out.println("Smartphone spento.");
    }
    
}
