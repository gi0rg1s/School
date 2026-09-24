using System;
using System.Collections.Generic;
using System.Linq;

namespace LambdaDemo
{
    public class Utente
    {
        public string Nome { get; set; } = string.Empty;
        public string Categoria { get; set; } = string.Empty; // "Gold", "Silver", "Standard"
        public double TotaleSpeso { get; set; }
        public bool Attivo { get; set; }
    }
    class Program
    {
        delegate int del(int i);
        static void Main(string[] args)
        {
            /// Creazione di un array di numeri interi
            int[] numeri = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };
            // Va bene anche List<int> numeri = new List<int> { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 };


            /// METODI ESTENSIONE LINQ CON ARRAY SEMPLICI ED ESPRESSIONI LAMBDA

            // espressione lambda nella query linq Count che conteggia i num solo dispari
            int quantiDispari = numeri.Count(n => n % 2 == 1); //5
            Console.Out.WriteLine("Num. Valori dispari: " + quantiDispari);

            // espressione lambda assegnata a delegate poi richiamata con parametro 5
            del delegato = x => x * x;
            int j = delegato(5); //j = 25 
            Console.Out.WriteLine("Quadrato di 5: " + j);
            // Si riassegna allo stesso delegato un'altra espressione lambda con stessa firma del tipo
            delegato = x => Math.Max(x, 10);
            Console.Out.WriteLine("Il maggiore tra " + j + " e 10 è " + delegato(j)); //25

            // oppure in modo compatto senza predichiarazione delegate
            Func<int, int> square = x => x * x;
            Console.WriteLine("Quadrato: " + square(5));  // 25

            // query linq su array numeri per estrarre array ridotto a numeri < 5
            Console.Out.WriteLine("Valori < 5 :");
            var numeriMinori5 = from n in numeri
                                    where n < 5
                                    select n;
            foreach (int i in numeriMinori5)
                Console.Out.WriteLine(i);

            Console.Out.WriteLine();

            // query linq lambda su array numeri per estrarre da array numeri e creare nuovo array ridotto a numeri pari
            var numeriPari = numeri.Where(n => n % 2 == 0).ToList();
            Console.Out.WriteLine("Numeri pari: " + string.Join(" ", numeriPari));

            // query linq lambda su array numeri per estrarre array ridotto a numeri pari e successivamente trasformare in nuovo array (proiezione) elevando al quadrato
            var numeriElevati = numeriPari
                          .Where(n => n % 2 == 0)
                          .Select(n => Math.Pow(n, 2)).ToList();
            Console.WriteLine("Numeri pari elevati: " + string.Join(" ", numeriElevati));


            Console.WriteLine("\nPremi INVIO per continuare...\n");
            Console.In.ReadLine();


            /// ---1. **************** METODI ESTENSIONE LINQ CON LISTE DI CLASSI PERSONALIZZATE ED ESPRESSIONI LAMBDA

            // --- 2. PREPARAZIONE DEI DATI ---
            var utenti = new List<Utente>
            {
                new Utente { Nome = "Mario", Categoria = "Gold", TotaleSpeso = 150.00, Attivo = true },
                new Utente { Nome = "Anna", Categoria = "Silver", TotaleSpeso = 80.00, Attivo = true },
                new Utente { Nome = "Luca", Categoria = "Gold", TotaleSpeso = 300.00, Attivo = false },
                new Utente { Nome = "Elena", Categoria = "Standard", TotaleSpeso = 20.00, Attivo = true },
                new Utente { Nome = "Stefano", Categoria = "Gold", TotaleSpeso = 500.00, Attivo = true }
            };

            Console.WriteLine("=== ESEMPI METODI DI ESTENSIONE SINGOLI ===\n");

            // --- 3. ESEMPI SINGOLI ---

            // 1. Where (Filtro)
            var utentiGold = utenti.Where(u => u.Categoria == "Gold");
            // Questa riga sotto è per dimostrare che l'aggiunta di un nuovo utente Gold viene fatta in differita
            // cioé quando la lista verrà iterata. Es con .ToList(). utentiGold infatti conterrà anche l'utente Giulia aggiunto dopo la definizione della query
            utenti.Add(new Utente { Nome = "Giulia", Categoria = "Gold", TotaleSpeso = 250.00, Attivo = true }); // Aggiunta di un nuovo utente Gold per test
            Console.WriteLine("Utenti Gold trovati:");
            foreach (var u in utentiGold) Console.WriteLine($"- {u.Nome}");

            // 2. Select (Trasformazione/Proiezione)
            var soliNomi = utenti.Select(u => u.Nome).ToList();  // Non in differita perché con ToList() viene eseguita subito la query
            Console.WriteLine($"\nLista dei soli nomi: {string.Join(", ", soliNomi)}");

            // 3. OrderByDescending (Ordinamento)
            var ordinatiPerSpesa = utenti.OrderByDescending(u => u.TotaleSpeso);
            Console.WriteLine("\nUtenti ordinati per spesa decrescente:");
            foreach (var u in ordinatiPerSpesa) Console.WriteLine($"- {u.Nome}: €{u.TotaleSpeso}");

            // 4. FirstOrDefault (Ricerca singola con safe-fail)
            var utenteDisattivato = utenti.FirstOrDefault(u => !u.Attivo);
            Console.WriteLine($"\nPrimo utente non attivo: {utenteDisattivato?.Nome ?? "Nessuno"}");

            // 5. Sum (Aggregazione numerica)
            double spesaTotale = utenti.Sum(u => u.TotaleSpeso);
            Console.WriteLine($"\nSpesa totale di tutti gli utenti: €{spesaTotale}");

            // 6. Count (Conteggio con condizione)
            int quantiAttivi = utenti.Count(u => u.Attivo);
            Console.WriteLine($"Numero di utenti attivi: {quantiAttivi}");


            Console.WriteLine("\n=========================================");
            Console.WriteLine("=== ESEMPIO PIPELINE COMPLESSA (PAGINAZIONE) ===\n");

            // --- 4. ESEMPIO PIPELINE COMPLESSA ---
            int elementiPerPagina = 2;
            int numeroPagina = 1; // Pagina 1 (mostrerà i primi 2 utenti Gold attivi più ricchi)

            var paginaPremium = utenti
                .Where(u => u.Attivo && u.Categoria == "Gold") // 1. Filtra solo Gold attivi (Stefano, Mario)
                .OrderByDescending(u => u.TotaleSpeso)          // 2. Ordina per spesa (Stefano: 500, Mario: 150)
                .Skip((numeroPagina - 1) * elementiPerPagina)  // 3. Salta elementi delle pagine precedenti (0 elementi saltati)
                .Take(elementiPerPagina)                       // 4. Prendi solo il numero di elementi richiesti (2 elementi)
                .ToList();                                     // 5. Esegui la query e crea la lista finale

            Console.WriteLine($"Risultati Pagina {numeroPagina} (Max {elementiPerPagina} elementi):");
            foreach (var u in paginaPremium)
            {
                Console.WriteLine($"- {u.Nome} ({u.Categoria}) ha speso €{u.TotaleSpeso}");
            }


            Console.WriteLine("\n=========================================");
            Console.WriteLine("=== ESEMPIO PIPELINE CON PROIEZIONE SELECT ===\n");

            // --- 5. PIPELINE CON SELEZIONE ANONIMA ---
            var reportNomi = utenti
                .Where(u => u.Attivo && u.Categoria == "Gold")
                .OrderByDescending(u => u.TotaleSpeso)
                .Select(u => new {
                    u.Nome,
                    MessaggioSpesa = $"{u.Nome} ha speso un totale di €{u.TotaleSpeso}"
                })
                .ToList();

            Console.WriteLine("Report generato al volo con tipi anonimi:");
            foreach (var item in reportNomi)
            {
                // Notare come 'item' non sia un 'Utente', ma ha accesso solo alle proprietà create nel Select
                Console.WriteLine($"Nome: {item.Nome} -> {item.MessaggioSpesa}");
            }
        }
    }
}
