using System.Data;

namespace WinFormsLibraryArticolo
{
    public class Ordine
    {
        int idOrdine;
        int idCliente;
        DateTime data;

        public Ordine(int idOrdine, int idCliente, DateTime data)
        {
            this.IdOrdine = idOrdine;
            this.IdCliente = idCliente;
            this.Data = data;
        }

        public int IdOrdine { get => idOrdine; set => idOrdine = value; }
        public int IdCliente { get => idCliente; set => idCliente = value; }
        public DateTime Data { get => data; set => data = value; }

        public List<Ordine> getSampleData()
        {
            return new List<Ordine>(){
                new Ordine(1, 1, DateTime.Parse("2000-12-02")),
                new Ordine(2, 1, DateTime.Parse("2022-3-15")),
                new Ordine(3, 2, DateTime.Parse("2026-11-11"))
            };
        }
    }

    public class Articolo
    {
        String nomeArticolo;
        int idOrdine;
        double importo;

        public Articolo(String nomeArticolo, int idOrdine, double importo)
        {
            this.NomeArticolo = nomeArticolo;
            this.IdOrdine = idOrdine;
            this.Importo = importo;
        }

        public string NomeArticolo { get => nomeArticolo; set => nomeArticolo = value; }
        public int IdOrdine { get => idOrdine; set => idOrdine = value; }
        public double Importo { get => importo; set => importo = value; }

        public List<Articolo> getSampleData()
        {
            return new List<Articolo>(){
                new Articolo("Cuffiette col filo", 2, 13.00),
                new Articolo("Orgoglio e pregiudizio", 1, 11.90),
                new Articolo("Cime tempestose", 1, 10.00)
            };
        }
    }

    public class Dati
    {
        
        public DataTable getArticoli()
        {
            DataTable dt = new DataTable();
            dt.Columns.AddRange(new DataColumn[]
            {
                new DataColumn("Nome", typeof(String)),
                new DataColumn("ID Ordine", typeof(int)),
                new DataColumn("Prezzo", typeof(double))
            });

            Articolo a = new Articolo("", 0, 0.00);
            List<Articolo> aa = a.getSampleData();

            foreach (Articolo articolo in aa) { dt.Rows.Add(articolo.NomeArticolo, articolo.IdOrdine, articolo.Importo); }

            return dt;
        }

        public DataTable getOrdini()
        {
            DataTable dt = new DataTable();
            dt.Columns.AddRange(new DataColumn[]
            {
                new DataColumn("ID", typeof(int)),
                new DataColumn("ID Cliente", typeof(int)),
                new DataColumn("Data di ordine", typeof(DateTime))
            });

            Ordine o = new Ordine(0, 0, DateTime.Parse("2000-2-20"));
            List<Ordine> oo = o.getSampleData();

            foreach (Ordine ordine in oo) { dt.Rows.Add(ordine.IdOrdine, ordine.IdCliente, ordine.Data); }
            
            return dt;
        }

        public DataTable articoliPerOrdine(int idOrdine)
        {
            DataTable articles = getArticoli();
            DataTable result = articles.Clone(); // same structure, without any lines

            foreach (DataRow riga in articles.Select("[ID Ordine] = " + idOrdine))
                result.ImportRow(riga);

            return result;
        }

        public double SommaImporti(int idOrdine)
        {
            double somma = 0;
            foreach (DataRow riga in articoliPerOrdine(idOrdine).Rows)
                somma += Convert.ToDouble(riga["Prezzo"]);

            return somma;
        }

        public double MediaImporti(int idOrdine)
        {
            DataTable dt = articoliPerOrdine(idOrdine);
            if (dt.Rows.Count == 0) return 0;
            return SommaImporti(idOrdine) / dt.Rows.Count;
        }
    }
}
