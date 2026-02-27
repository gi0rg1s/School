public class Test {
    public static void main(String[] args) {
        java.util.ArrayList<PersonalComputer> localList = new java.util.ArrayList<>();
        Inventario inventario = new Inventario();

        // create one of each concrete type
        Desktop desktop1 = new Desktop("Intel i5", 16, 512, "Dell", "OptiPlex", "Windows 10", "Tower", "NVIDIA GTX", "Realtek");
        Server server1 = new Server("AMD EPYC", 64, 2048, "HP", "ProLiant", "Linux", "Rack", 2, true);
        Notebook notebook1 = new Notebook("Intel i7", 16, 512, "Asus", "Zenbook", "Windows 11", 1.25, 2.0, 30.0, 22.0, 14.0, true, true, 720);
        Notebook notebook2 = new Notebook("Intel i3", 8, 256, "Lenovo", "IdeaPad", "Windows 10", 0.95, 1.8, 28.0, 20.0, 13.3, false, true, 480);
        Palmare palmare1 = new Palmare("ARM Cortex", 4, 64, "Samsung", "GalaxyTab", "Android", 0.300, 0.8, 10.0, 1.0, 10.1, true, true, "microSD");
        Portatili portatile1 = new Portatili(null, 0, 0, null, null, null, 0, 0, 0, 0, 0, false);
        Fissi fisso1 = new Fissi(null, 0, 0, null, null, null, null);

        
        // add to local list and to inventory (tests aggiungiPC)
        localList.add(desktop1); inventario.aggiungiPC(desktop1);
        localList.add(server1); inventario.aggiungiPC(server1);
        localList.add(notebook1); inventario.aggiungiPC(notebook1);
        localList.add(notebook2); inventario.aggiungiPC(notebook2);
        localList.add(palmare1); inventario.aggiungiPC(palmare1);
        localList.add(portatile1); inventario.aggiungiPC(portatile1);
        localList.add(fisso1); inventario.aggiungiPC(fisso1);

        // rimuoviPC
        System.out.println("==================== Inventario - Test Output ====================");
        System.out.println("Rimozione di notebook2: " + (inventario.rimuoviPC(notebook2) ? "OK" : "FALLITA"));
        // re-add it for further tests
        inventario.aggiungiPC(notebook2);

        // cercaPortatili: portatili sotto 1.5 kg with Wi-Fi
        System.out.println();
        System.out.println("-- Portatili <= 1.5 kg (richiede Wi-Fi) --");
        java.util.ArrayList<PersonalComputer> found = inventario.cercaPortatili(1.5, true);
        if (found.isEmpty()) {
            System.out.println("  (nessun risultato)");
        } else {
            int i = 1;
            for (PersonalComputer pc : found) {
                System.out.println(String.format("  %d) %s", i++, pc.toString()));
            }
        }

        // Now perform the computations requested by the assignment using localList
        // 1) PC with most RAM
        PersonalComputer maxRam = null;
        int sumRam = 0;
        int countRam = 0;
        int portatiliConWifi = 0;
        int serverConRAID = 0;
        Notebook lightestNotebook = null;

        for (PersonalComputer pc : localList) {
            // max RAM and average
            if (maxRam == null || pc.getDimensioneRAM() > maxRam.getDimensioneRAM()) maxRam = pc;
            sumRam += pc.getDimensioneRAM(); countRam++;

            // portatili with Wi-Fi
            if (pc instanceof Portatili) {
                Portatili p = (Portatili) pc;
                if (p.isReteWireless()) portatiliConWifi++;
            }

            // servers with RAID
            if (pc instanceof Server) {
                Server s = (Server) pc;
                if (s.isDischiRAID()) serverConRAID++;
            }

            // lightest notebook
            if (pc instanceof Notebook) {
                Notebook n = (Notebook) pc;
                if (lightestNotebook == null || n.getPeso() < lightestNotebook.getPeso()) lightestNotebook = n;
            }
        }

        System.out.println("\nPC con più RAM: " + (maxRam != null ? maxRam.toString() : "(nessuno)"));
        System.out.println("Media RAM: " + (countRam > 0 ? (double) sumRam / countRam : 0.0));
        System.out.println("Numero di portatili con Wi-Fi: " + portatiliConWifi);
        System.out.println("Numero di server con dischi RAID: " + serverConRAID);
        System.out.println("Notebook più leggero: " + (lightestNotebook != null ? lightestNotebook.toString() : "(nessuno)"));

        // searches: by marca and by OS using localList
        String marcaCercata = "Asus";
        System.out.println("\nPC della marca '" + marcaCercata + "':");
        for (PersonalComputer pc : localList) if (pc.getMarca() != null && pc.getMarca().equalsIgnoreCase(marcaCercata)) System.out.println("  - " + pc.toString());

        String osCercato = "Windows 10";
        System.out.println("\nPC con OS '" + osCercato + "':");
        for (PersonalComputer pc : localList) if (pc.getOs() != null && pc.getOs().equalsIgnoreCase(osCercato)) System.out.println("  - " + pc.toString());

        // demonstrate cercaPortatili for portatili sotto dato peso with/without Wi-Fi
        System.out.println("\nCerca portatili <= 2.0kg (don't care about wifi):");
        for (PersonalComputer pc : inventario.cercaPortatili(2.0, false)) System.out.println("  - " + pc.toString());

        System.out.println("\nCerca portatili <= 2.0kg (require wifi):");
        for (PersonalComputer pc : inventario.cercaPortatili(2.0, true)) System.out.println("  - " + pc.toString());
    }
}
