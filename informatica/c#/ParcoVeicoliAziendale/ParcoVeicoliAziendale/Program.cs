using System;
using System.Collections.Generic;

public abstract class Veicolo {

    protected string targa { get; set; } = string.Empty;
    protected string marca { get; set; } = string.Empty;
    protected string modello { get; set; } = string.Empty;
    protected double chilometriPercorsi { get; set; }

    //constructor
    public Veicolo(string targa, string marca, string modello, double chilometriPercorsi)
    {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.chilometriPercorsi = chilometriPercorsi;
    }

    //methods
    /// <summary>
    /// return specifiers of the veichle
    /// </summary>
    /// <returns></returns>
    public virtual string stampaDettagli()
    {
        return ($"Veicolo [Targa: {this.targa}, Marca: {this.marca}, Modello: {this.modello}, Chilometri percorsi: {this.chilometriPercorsi}]");
    } 
    /// <summary>
    /// calculates cost for mantainance
    /// </summary>
    /// <returns></returns>
    public virtual double calcolaCostoManutenzione()
    {
        return 0.05 * this.chilometriPercorsi;
    }
}

public class Auto : Veicolo
{
    private int numeroPorte { get; set; }
    //constructor
    public Auto(string targa, string marca, string modello, double chilometriPercorsi, int numeroPorte) : base(targa, marca, modello, chilometriPercorsi)
    {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.chilometriPercorsi = (double)chilometriPercorsi;
        this.numeroPorte = numeroPorte;
    }

    /// <summary>
    /// return a string with all auto's specifiers
    /// </summary>
    /// <returns></returns>
    public override string stampaDettagli()
    {
        return ($"Auto[Targa: {this.targa}, Marca: {this.marca}, Modello: {this.modello}, Chilometri percorsi: {this.chilometriPercorsi}, Numero porte: {this.numeroPorte}]");
    }

    /// <summary>
    /// calculates cost for mantainance
    /// </summary>
    /// <returns></returns>
    public override double calcolaCostoManutenzione()
    {
        return base.calcolaCostoManutenzione() + 100;
    }
}

public class Camion : Veicolo
{
    private double capacitaCarico { get; set; }
    //constructor
    public Camion(string targa, string marca, string modello, double chilometriPercorsi, int capacitaCarico) : base(targa, marca, modello, chilometriPercorsi)
    {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.chilometriPercorsi = (double)chilometriPercorsi;
        this.capacitaCarico = capacitaCarico;
    }

    /// <summary>
    /// return a string with all camion's specifiers
    /// </summary>
    /// <returns></returns>
    public override string stampaDettagli()
    {
        return ($"Camion[Targa: {this.targa}, Marca: {this.marca}, Modello: {this.modello}, Chilometri percorsi: {this.chilometriPercorsi}, Capacità carico: {this.capacitaCarico}]");
    }

    /// <summary>
    /// calculates cost for mantainance
    /// </summary>
    /// <returns></returns>
    public override double calcolaCostoManutenzione()
    {
        return (0.15 * this.chilometriPercorsi) + (50 * this.capacitaCarico);
    }
}

public class Flotta
{
    private List<Veicolo> veicoloList { get; set; }

    //constructor
    public Flotta()
    {
        this.veicoloList = new List<Veicolo>();
    }

    /// <summary>
    /// adds a veichle to the list
    /// </summary>
    /// <param name="v"></param>
    public void aggiungiVeicolo(Veicolo v)
    {
        this.veicoloList.Add(v);
    }

    /// <summary>
    /// returns a string with all veichles specifiers
    /// </summary>
    /// <returns></returns>
    public String visualizzaLista()
    {
        string str = "";
        foreach(Veicolo v in this.veicoloList)
        {
            str += v.stampaDettagli() + "\n";
        }
        return str;
    }

    /// <summary>
    /// calculates the total cost of all the veichles mantainance
    /// </summary>
    /// <returns></returns>
    public double calcolaCostoTotaleManutenzione()
    {
       return veicoloList.Sum(v => v.calcolaCostoManutenzione());
    }
}

class Program
{
    static void Main(string[] args)
    {
        Flotta flotta = new Flotta();

        Auto auto1 = new Auto("AB123CD", "Fiat", "Panda", 45000, 5);
        flotta.aggiungiVeicolo(auto1);

        Camion camion1 = new Camion("EF456GH", "Iveco", "Daily", 120000, 8);
        flotta.aggiungiVeicolo(camion1);

        Console.WriteLine("=== Flotta Veicoli ===");
        Console.WriteLine(flotta.visualizzaLista());

        Console.WriteLine($"Costo totale manutenzione: {flotta.calcolaCostoTotaleManutenzione():C2}");

    }
}