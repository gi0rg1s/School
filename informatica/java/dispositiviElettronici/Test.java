public class Test {
    public static void main(String[] args) {
        Dispositivo computer = new Computer();
        Dispositivo smartphone = new Smartphone();
        Dispositivo printer = new Printer();

        computer.turnOn();
        computer.turnOff();
        ((Computer) computer).restart();

        printer.turnOn();
        printer.turnOff();

        smartphone.turnOn();
        smartphone.turnOff();

        Computer c1 = new Computer();
        c1.restart();
        Printer p1 = new Printer();
        Smartphone s1 = new Smartphone();

        c1.turnOn();
        p1.turnOn();
        s1.turnOn();
    }
}