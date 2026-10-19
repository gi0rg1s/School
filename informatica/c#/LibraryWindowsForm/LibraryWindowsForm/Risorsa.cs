namespace LibreriaBiblioteca
{
    public abstract class Risorsa
    {
        private string codice;
        private string titolo;
        private string autore;
        private int giorniInPrestito;

        protected Risorsa(string codice, string titolo, string autore, int giorniInPrestito)
        {
            this.Codice = codice;
            this.Titolo = titolo;
            this.Autore = autore;
            this.GiorniInPrestito = giorniInPrestito;
        }

        public string Codice { get => codice; set => codice = value; }
        public string Titolo { get => titolo; set => titolo = value; }
        public string Autore { get => autore; set => autore = value; }
        public int GiorniInPrestito { get => giorniInPrestito; set => giorniInPrestito = value; }

        public virtual string stampaDettagli()
        {
            return $"Codice: {Codice}, Titolo: {Titolo}, Autore: {Autore}, Giorni in prestito: {GiorniInPrestito}";
        }
        public virtual double calcolaPenaleRitardo(int giorniPrestitoEffettivi)
        {
            return giorniPrestitoEffettivi > this.giorniInPrestito ? (giorniPrestitoEffettivi - this.giorniInPrestito) * 0.20 : 0;
        }
    }

    public class Libro : Risorsa
    {
        private int numeroPagine;

        public Libro(string codice, string titolo, string autore, int giorniInPrestito, int numeroPagine) : base(codice, titolo, autore, giorniInPrestito)
        {
            this.Codice = codice;
            this.Titolo = titolo;
            this.Autore = autore;
            this.GiorniInPrestito = giorniInPrestito;
            this.NumeroPagine = numeroPagine;
        }

        public int NumeroPagine { get => numeroPagine; set => numeroPagine = value; }

        public string override stampaDettagli()
        {
            return base.stampaDettagli() + $", Numero di pagine: {NumeroPagine}";
        }

        public double override calcolaPenale(int giorniPrestitoEffettivi)
        {
            return base.calcolaPenaleRitardo(giorniPrestitoEffettivi) + 5.00;
        }
    }

    public class Audiolibro : Risorsa
    {
        private int durataMinuti;


        public Audiolibro(string codice, string titolo, string autore, int giorniInPrestito, int durataMinuti) : base(codice, titolo, autore, giorniInPrestito)
        {
            this.Codice = codice;
            this.Titolo = titolo;
            this.Autore = autore;
            this.GiorniInPrestito = giorniInPrestito;
            this.DurataMinuti = durataMinuti;
        }

        public int DurataMinuti { get => durataMinuti; set => durataMinuti = value; }

        public string override stampaDettagli()
        {
            return base.stampaDettagli() + $", Durata in minuti: {DurataMinuti}";
        }

        public double override calcolaPenale(int giorniInPrestitoEffettivi)
        {
            return giorniInPrestitoEffettivi > this.GiorniInPrestito ? 0.30 * (giorniInPrestitoEffettivi - this.GiorniInPrestito) + (0.1 * this.DurataMinuti) : 0;
        }
    }

    public class Catalogo
    {
        private List<Risorsa> catalogo;

        public void aggiungiRisorsa(Risorsa r)
        {
            this.catalogo = r;
        }

        public string visualizzaCatalogo()
        {
            return this.catalogo.Select(r => r.stampaDettagli() + "/n");
        }

        public double calcolaPenaleTotale()
        {
            return this.catalogo.Sum(r => r.calcolaPenaleTotale());
        }
    }
}
