using System.ComponentModel;

namespace parcoVeicoli
{
    
    // CLASSE ASTRATTA VEICOLO
    public abstract class Veicolo
    {
        // Attributi
        private string targa;
        private string marca;
        private string modello;
        private double chilometriPercorsi;

        // Costruttore
        public Veicolo(string targa, string marca, string modello, double chilometriPercorsi)
        {
            Targa = targa;
            Marca = marca;
            Modello = modello;
            ChilometriPercorsi = chilometriPercorsi;
        }

        // Proprietà
        public string Targa { get => targa; set => targa = value; }
        public string Marca { get => marca; set => marca = value; }
        public string Modello { get => modello; set => modello = value; }
        public double ChilometriPercorsi { get => chilometriPercorsi; set => chilometriPercorsi = value; }

        // Metodi astratti
        public abstract string StampaDettagli();
        public abstract double CalcolaCostoManutenzione();
    }

    // CLASSE AUTO
    public class Auto : Veicolo
    {
        // Attributo
        private int numeroPorte;

        // Costruttore
        public Auto(int numeroPorte, string targa, string marca, string modello, double chilometriPercorsi)
            : base(targa, marca, modello, chilometriPercorsi)
        {
            NumeroPorte = numeroPorte;
        }

        // Proprietà
        public int NumeroPorte
        {
            get => numeroPorte;
            set => numeroPorte = value;
        }

        // Metodi

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

    // CLASSE CAMION
    public class Camion : Veicolo
    {
        // Attributo
        private double capacitaCarico;

        // Costruttore
        public Camion(double capacitaCarico, string targa, string marca, string modello, double chilometriPercorsi)
            : base(targa, marca, modello, chilometriPercorsi)
        {
            CapacitaCarico = capacitaCarico;
        }

        // Proprietà
        public double CapacitaCarico
        {
            get => capacitaCarico;
            set => capacitaCarico = value;
        }

        // Metodi

        /// <summary>
        /// Ritorna una stringa con i dettagli della classe
        /// </summary>
        /// <returns> Stringa Descrittiva </returns>
        public override string StampaDettagli()
        {
            return $"Targa: {Targa}; Marca: {Marca}; Modello: {Modello}; ChilometriPercorsi: {ChilometriPercorsi}; CapacitaCarico: {CapacitaCarico}";
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
}