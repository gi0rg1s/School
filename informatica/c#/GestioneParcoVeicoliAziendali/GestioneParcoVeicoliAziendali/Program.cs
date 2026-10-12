// MAIN
Auto auto1 = new Auto(4, "AB123CD", "Fiat", "Panda", 15000);
Camion camion1 = new Camion(20.5, "XY456ZZ", "Iveco", "Daily", 80000);

Flotta flotta = new Flotta();
flotta.AggiungiVeicolo(auto1);
flotta.AggiungiVeicolo(camion1);

Console.WriteLine(flotta.VisualizzaFlotta());
Console.WriteLine($"Costo totale manutenzione: {flotta.CalcolaCostoTotaleManutenzione()} Euro");

Console.WriteLine("Fine del programma.");
Console.ReadLine();
// ============================================================================================================
//CLASSE VEICOLO 
abstract public class Veicolo
{
    //Attributi
    private string targa;
    private string marca;
    private string modello;
    private double chilometriPercorsi;

    //Costruttore
    public Veicolo(string targa, string marca, string modello, double chilometriPercorsi)
    {
        Targa = targa;
        Marca = marca;
        Modello = modello;
        ChilometriPercorsi = chilometriPercorsi;
    }

    //Proprietà
    public string Targa { get => targa; set => targa = value; }
    public string Marca { get => marca; set => marca = value; }
    public string Modello { get => modello; set => modello = value; }
    public double ChilometriPercorsi { get => chilometriPercorsi; set => chilometriPercorsi = value; }

    //Metodi 
    public abstract string StampaDettagli();
    public abstract double CalcolaCostoManutenzione();

}
// ============================================================================================================

//CLASSE AUTO
public class Auto : Veicolo
{
    //Attributo
    int numeroPorte;

    //Costruttore
    public Auto(int numeroPorte, string targa, string marca, string modello, double chilometriPercorsi) : base(targa, marca, modello, chilometriPercorsi)
    {
        NumeroPorte = numeroPorte;
    }

    //Proprietà
    public int NumeroPorte
    {
        get => numeroPorte; set => numeroPorte = value;
    }
    //Metodi

    /// <summary>
    /// Ritorna una stringa con i dettagli della classe
    /// </summary>
    /// <returns> Stringa Descrittiva </returns>
    public override string StampaDettagli()
    {
        return $"Targa: {Targa}; Marca: {Marca}; Modello: {Modello}; ChilometriPercorsi: {ChilometriPercorsi}; NumeroPorte: {NumeroPorte}";
    }

    /// <summary>
    /// Calcola il costo della Manutenzione aggiungendo la tassa fissa di 100€
    /// </summary>
    /// <returns> Costo della manutenzione </returns>
    public override double CalcolaCostoManutenzione()
    {
        return (ChilometriPercorsi * 0.05) + 100;
    }
}
// ============================================================================================================

//Classe Camion
public class Camion : Veicolo
{
    //Attributo
    double capacitaCarico;

    //Costruttore
    public Camion(double capacitaCarico, string targa, string marca, string modello, double chilometriPercorsi) : base(targa, marca, modello, chilometriPercorsi)
    {
        CapacitaCarico = capacitaCarico;
    }

    //Proprietà
    public double CapacitaCarico
    {
        get => capacitaCarico; set => capacitaCarico = value;
    }
    //Metodi

    /// <summary>
    /// Ritorna una stringa con i dettagli della classe
    /// </summary>
    /// <returns> Stringa Descrittiva </returns>
    public override string StampaDettagli()
    {
        return $"Targa: {Targa}; Marca: {Marca}; Modello: {Modello}; ChilometriPercorsi: {ChilometriPercorsi}; CapacitaCarico: {capacitaCarico}";
    }

    /// <summary>
    /// Calcola il costo della Manutenzione aggiungendo la tassa fissa di 100€
    /// </summary>
    /// <returns> Costo della manutenzione </returns>
    public override double CalcolaCostoManutenzione()
    {
        return (ChilometriPercorsi * 0.15) + (CapacitaCarico * 50);
    }
}
// ============================================================================================================

//CLASSE FLOTTA
public class Flotta
{
    //Attributo
    private List<Veicolo> veicoli = new List<Veicolo>();

    //Metodi

    /// <summary>
    /// Aggiunge un nuovo veicolo alla flotta
    /// </summary>
    /// <param name="v"> Veicolo da aggiungere </param>
    public void AggiungiVeicolo(Veicolo v)
    {
        veicoli.Add(v);
    }

    /// <summary>
    /// Cicla la lista dei veicoli e stampa i dettagli di ciascuno sfruttando il polimorfismo
    /// </summary>
    public string VisualizzaFlotta()
    {
        string InfoFlotta = "";
        foreach (Veicolo v in veicoli)
        {
            InfoFlotta += "\n- " + (v.StampaDettagli());
        }
        return InfoFlotta;
    }

    /// <summary>
    /// Calcola la somma dei costi di manutenzione di tutti i veicoli della flotta
    /// </summary>
    /// <returns> Costo totale di manutenzione </returns>
    public double CalcolaCostoTotaleManutenzione()
    {
        double costoTotale = 0;

        foreach (Veicolo v in veicoli)
        {
            costoTotale += v.CalcolaCostoManutenzione();
        }

        return costoTotale;
    }
}