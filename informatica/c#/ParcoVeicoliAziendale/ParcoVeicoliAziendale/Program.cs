using System;
using System.Collections.Generic;

public class Veicolo {

    public string Targa { get; set; } = string.Empty;
    public string Marca { get; set; } = string.Empty;
    public string Modello { get; set; } = string.Empty;
    public double ChilometriPercorsi { get; set; }

    //constructor
    public Veicolo(string targa, string marca, string modello, double chilometriPercorsi)
    {
        Targa = targa;
        Marca = marca;
        Modello = modello;
        ChilometriPercorsi = chilometriPercorsi;
    }

    //methods
    /// <summary>
    /// return specifiers of the veichle
    /// </summary>
    /// <returns></returns>
    public virtual string StampaDettagli()
    {
        return ($"Veicolo [Targa: {this.Targa}, Marca: {this.Marca}, Modello: {this.Modello}, Chilometri percorsi: {this.ChilometriPercorsi}]");
    } 
    /// <summary>
    /// calculates cost for mantainance
    /// </summary>
    /// <returns></returns>
    public virtual double CalcolaCostoManutenzione()
    {
        return 0.05 * this.ChilometriPercorsi;
    }
}

public class Auto : Veicolo
{
    public int NumeroPorte { get; set; }
    //constructor
    public Auto(string targa, string marca, string modello, double chilometriPercorsi, int numeroPorte) : base(targa, marca, modello, chilometriPercorsi)
    {
        this.Targa = targa;
        this.Marca = marca;
        this.Modello = modello;
        this.ChilometriPercorsi = (double)chilometriPercorsi;
        this.NumeroPorte = numeroPorte;
    }

    /// <summary>
    /// return a string with all auto's specifiers
    /// </summary>
    /// <returns></returns>
    public override string StampaDettagli()
    {
        return ($"Auto[Targa: {this.Targa}, Marca: {this.Marca}, Modello: {this.Modello}, Chilometri percorsi: {this.ChilometriPercorsi}, Numero porte: {this.NumeroPorte}]");
    }

    /// <summary>
    /// calculates cost for mantainance
    /// </summary>
    /// <returns></returns>
    public override double CalcolaCostoManutenzione()
    {
        return base.CalcolaCostoManutenzione() + 100;
    }
}

public class Camion : Veicolo
{
    public double CapacitaCarico { get; set; }
    //constructor
    public Camion(string targa, string marca, string modello, double chilometriPercorsi, int capacitaCarico) : base(targa, marca, modello, chilometriPercorsi)
    {
        this.Targa = targa;
        this.Marca = marca;
        this.Modello = modello;
        this.ChilometriPercorsi = (double)chilometriPercorsi;
        this.CapacitaCarico = capacitaCarico;
    }

    /// <summary>
    /// return a string with all camion's specifiers
    /// </summary>
    /// <returns></returns>
    public override string StampaDettagli()
    {
        return ($"Camion[Targa: {this.Targa}, Marca: {this.Marca}, Modello: {this.Modello}, Chilometri percorsi: {this.ChilometriPercorsi}, Capacità carico: {this.CapacitaCarico}]");
    }

    /// <summary>
    /// calculates cost for mantainance
    /// </summary>
    /// <returns></returns>
    public override double CalcolaCostoManutenzione()
    {
        return (0.15 * this.ChilometriPercorsi) + (50 * this.CapacitaCarico);
    }
}

public class Flotta
{
    List<Veicolo> VeicoloList { get; set; }

    //constructor
    public Flotta()
    {
        VeicoloList = new List<Veicolo>();
    }

    /// <summary>
    /// adds a veichle to the list
    /// </summary>
    /// <param name="v"></param>
    public void aggiungiVeicolo(Veicolo v)
    {
        this.VeicoloList.Add(v);
    }

    /// <summary>
    /// returns a string with all veichles specifiers
    /// </summary>
    /// <returns></returns>
    public String VisualizzaLista()
    {
        string str = "";
        foreach(Veicolo v in this.VeicoloList)
        {
            str += v.StampaDettagli() + "\n";
        }
        return str;
    }

    /// <summary>
    /// calculates the total cost of all the veichles mantainance
    /// </summary>
    /// <returns></returns>
    public double CalcolaCostoTotaleManutenzione()
    {
        double cost = 0;
        foreach(Veicolo v in this.VeicoloList)
        {
            cost += v.CalcolaCostoManutenzione();
        }
        return cost;
    }
}

class Program
{
    static void Main(string[] args)
    {
        Flotta flotta = new Flotta();

        // 1. Inserire una nuova Auto
        Auto auto1 = new Auto("AB123CD", "Fiat", "Panda", 45000, 5);
        flotta.aggiungiVeicolo(auto1);

        // 2. Inserire un nuovo Camion
        Camion camion1 = new Camion("EF456GH", "Iveco", "Daily", 120000, 8);
        flotta.aggiungiVeicolo(camion1);

        // 3. Visualizzare tutti i veicoli della flotta
        Console.WriteLine("=== Flotta Veicoli ===");
        Console.WriteLine(flotta.VisualizzaLista());

        // 4. Visualizzare il costo totale di manutenzione
        Console.WriteLine($"Costo totale manutenzione: {flotta.CalcolaCostoTotaleManutenzione():C2}");

        // 5. Uscire dal programma
        Console.WriteLine("Premi un tasto per uscire...");
        Console.ReadKey();
    }
}