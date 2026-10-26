using System.Data;

/*
Esempio che dimostra l'uso di metodi di DataTable e Liste che fanno uso di metodi con Lambda Expression

- table.AsEnumerable() → converte la DataTable in un IEnumerable<DataRow>, necessario per usare Lambda Expression e LINQ.

- Select(...) → proietta (seleziona) i dati dalle righe (DataRow) in un nuovo formato.
Per ogni riga della tabella, “proietta” (cioè seleziona) solo il valore di alcune colonne.
In pratica, invece di avere oggetti DataRow, si può ottenere un'istanza di oggetto con alcuni campi
Tutti gli elementi generati da .Select possono diventare Liste se rielaborate successivamente con .ToList()

- Field<T>("NomeColonna") → metodo estensione che estrae in modo tipizzato i valori dalle colonne.
*/

// Creazione della DataTable
DataTable table = new DataTable();
table.Columns.Add("Id", typeof(int));
table.Columns.Add("Nome", typeof(string));
table.Columns.Add("Eta", typeof(int));

table.Rows.Add(1, "Luca", 25);
table.Rows.Add(2, "Anna", 30);
table.Rows.Add(3, "Marco", 22);
table.Rows.Add(4, "Sara", 28);

/* 1. Conversione di DataTable in lista di oggetti Persona
 * il codice new Persona {...} è un istanziatore equivalente a:
    Persona p = new Persona();
    p.Id = ...;
    p.Nome = ...;
    p.Eta = ...;
    Così nella lambda di Select si risolve in unica istruzione
 */
List<Persona> persone = table.AsEnumerable()
    .Select(r => new Persona
    { // 
        Id = r.Field<int>("Id"),
        Nome = r["Nome"].ToString(),  // In alternativa
        Eta = r.Field<int>("Eta")
    })
    .ToList();

// 2. Filtrare le persone con età > 25
var over25 = persone
    .Where(p => p.Eta > 25)
    .ToList();

Console.WriteLine("Persone con età > 25:");
foreach (var p in over25)
    Console.WriteLine($"{p.Nome} ({p.Eta} anni)");

// 3. Ordinare per nome. Anche evntualmente aggiungere .ThenByDescending(p => p.Eta);
var ordinatePerNome = persone
    .OrderBy(p => p.Nome)
    .ToList();

Console.WriteLine("\nPersone ordinate per nome:");
foreach (var p in ordinatePerNome)
    Console.WriteLine($"{p.Nome} - {p.Eta} anni");

// 4. Proiettare solo alcune proprietà (oggetti anonimi)
var nomiEta = persone
    .Select(p => new { p.Nome, p.Eta })
    .ToList();

Console.WriteLine($"\nSolo Nome ed Età dalla lista di {nomiEta[0].GetType()}:");
foreach (var p in nomiEta)
    Console.WriteLine($"{p.Nome}, {p.Eta} anni");

// Proiettare la DataTable in lista di stringhe (nella lambda non c'è new
List<Object> nomi = table.AsEnumerable()
                .Select(riga => riga["Nome"])  // oppure riga.Field<string>("Nome")
                .ToList();

// 5. Calcolare la media dell’età. Oppure anche Sum / Average / Count / Max / Min
double etaMedia = persone.Average(p => p.Eta);
Console.WriteLine($"\nEtà media: {etaMedia:F1}");  // F1 Formatta 1 decimale

// 6. true se esiste almeno 1 elemento che corrisponde al criterio
bool esiste = persone.Any(nome => nome.Nome == "Sara");
// true se tutti rispondono al criterio
bool tuttiMaggiorenni = persone.All(p => p.Eta >= 18);

// 7. estrae l'elemento specificato, null se non c'è
var primoAdulto = persone.FirstOrDefault(p => p.Eta >= 18);


/*  cosa fa ?
 *  var query = tabella.AsEnumerable()
    .Where(r => r.Field<int>("Eta") > 18)
    .OrderBy(r => r.Field<string>("Cognome"))
    .Select(r => new {
        NomeCompleto = r.Field<string>("Nome") + " " + r.Field<string>("Cognome"),
        Eta = r.Field<int>("Eta")
    });
 */

// 8. join collega 2 liste in base a una chiave
List<Ordine> ordini = new List<Ordine>
    {
        new Ordine { IdPersona = 1, Data = new DateTime(2025, 1, 10), Importo = 120.50 },
        new Ordine { IdPersona = 1, Data = new DateTime(2025, 2, 12), Importo = 80.00 },
        new Ordine { IdPersona = 3, Data = new DateTime(2025, 3, 5), Importo = 200.00 }
    };
/* JOIN tra persone e ordini
   persone.Join(ordini, p => p.Id, o => o.IdPersona, ...) → collega le due liste in base alle chiavi (Id e IdPersona).
   (p, o) => new { ... } → lambda expression che crea un nuovo oggetto anonimo contenente dati da entrambe le liste.
 */
var query = persone.Join(
    ordini,                       // seconda lista
    p => p.Id,                    // chiave nella prima (Persona)
    o => o.IdPersona,             // chiave nella seconda (Ordine)
    (p, o) => new                 // risultato combinato
    {
        p.Nome,
        p.Eta,
        o.Data,
        o.Importo
    });

Console.WriteLine($"\nLista unità tra persone e Ordini:");
foreach (var item in query)
{
    Console.WriteLine($"{item.Nome} ({item.Eta} anni) - Ordine del {item.Data.ToShortDateString()} - Importo: {item.Importo:C}");
}

public class Persona
{
  public int Id { get; set; }
    public string Nome { get; set; }
    public int Eta { get; set; }
}

public class Ordine
{
    public int IdPersona { get; set; }
    public DateTime Data { get; set; }
    public double Importo { get; set; }
}