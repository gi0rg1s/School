using DataGridDemo;
using System.Data;

namespace WinFormsAppDataTableDemo
{
    public partial class FormDemo : Form
    {
        private DataTable dt;
        public FormDemo()
        {
            InitializeComponent();
        }

        // carica i dati sulla DataTable che servirà per popolare la Griglia
        public void CaricaDati()
        {
            dt = new DataTable();
            DataRow dr;

            dt.TableName = "Dati"; // Non strettamente necessario
            dt.Columns.Add("Cognome", typeof(string));
            dt.Columns.Add("Nome");
            dt.Columns.Add("Telefono");
            //dt.Columns.Add("pippo");


            /* Viene creata una riga direttamente col metodo di DataTable
			   che avendo già allocato/istanziato 3 colonne evidentemente genera
			   altrettanti elementi sulla riga stessa.
			*/
            //aggiunge una nuova riga per poi popolarla con i dati dello studente
            dr = dt.NewRow();

            dr[0] = "Rossi";
            dr[1] = "Mario";
            dr[2] = "123456";
            //dr[3] = "oijoij"; //Non può esistere

            dt.Rows.Add(dr);

            dr = dt.NewRow();

            dr[0] = "Verdi";
            dr[1] = "Maria";
            dr[2] = "123456";

            dt.Rows.Add(dr);

            // Costruttore rapido Add per aggiungere riga in DataTable
            dt.Rows.Add("Bianchi", "Ugo", 632);

            dt.WriteXml("Rubrica.xml", XmlWriteMode.WriteSchema);

            dt.AcceptChanges();
            dataGridViewProva.DataSource = dt;
        }

        private void FormDemo_Load(object sender, EventArgs e)
        {
            CaricaDati();
        }
        /// <summary>
        /// Scorre la dt, visualizza le righe una per volta compreso lo stato.
        /// In ADO.NET ogni DataRow ha una proprietà RowState (di tipo enum DataRowState)
        /// che indica lo stato della riga in relazione al DataTable.
        /// Ecco tutti i possibili stati in una tabella riassuntiva:
        /// Detached La riga è stata creata ma non è ancora aggiunta a nessuna DataTable.
        /// Unchanged   La riga non è stata modificata da quando è stata caricata o dopo l’ultimo AcceptChanges().
        /// Added La riga è stata aggiunta a una DataTable, ma AcceptChanges() non è ancora stato chiamato.
        /// Modified La riga è stata modificata rispetto al suo stato originale.
        /// Deleted La riga è stata contrassegnata per l’eliminazione tramite Delete(),
        /// Gli stati passano a unchanged quando viene fatto AcceptChanges().
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonLeggiDati_Click(object sender, EventArgs e)
        {
            foreach (DataRow riga in ((DataTable)dataGridViewProva.DataSource).Rows)
                if (riga.RowState != DataRowState.Deleted)
                    MessageBox.Show(riga["Nome"].ToString() +
                                ' ' +
                                riga["Cognome"].ToString() +
                                " - Stato Riga: " +
                                riga.RowState);
                else
                    MessageBox.Show("Riga eliminata - Stato Riga: " + riga.RowState);

            ((DataTable)dataGridViewProva.DataSource).AcceptChanges(); // MODIFICA LO STATO DELLE RIGHE IN Unchanged
        }
        /// <summary>
        /// Aggiunta sintetica di una riga alla dt
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonAggiungi_Click(object sender, EventArgs e)
        {
            // refresh della datagridview automatico
            // Metodo Add con overload per creare riga intera in DataTable
            dt.Rows.Add("aggiunto", "aggiunto", 123);
        }
        /// <summary>
        /// istanzia dt  crea la struttura e inserisce i dati di esempio con metodi sintetici
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonStudenti_Click(object sender, EventArgs e)
        {
            dt = new DataTable();
            dt.Columns.AddRange(new DataColumn[] {
                    new("nome"),
                    new DataColumn("cognome"),
                    new DataColumn("matricola"),
                    new DataColumn("classe")
                });
            foreach (Studente s in Studente.getDati())
                // DataTable può usare il metodo Add con overload per creare riga intera
                dt.Rows.Add(s.Nome, s.Cognome, s.Matricola, s.Classe);

            dataGridViewProva.DataSource = dt;
        }
        /// <summary>
        /// Filtra studenti dalla dt, clona struttura in altra datatable e ricopia dati filtrati 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonFiltraStudenti_Click(object sender, EventArgs e)
        {
            DataRow[] rowsFiltrate = dt.Select("Classe = '5a2'");  // Filtra righe dalla dt
            DataTable dt2 = new DataTable();
            dt2 = dt.Clone(); // clona SOLO struttura

            // Importa i dati filtrati dalla vecchia alla nuova DataTable
            foreach (DataRow row in rowsFiltrate)
            {
                //Console.WriteLine($"Nome: {row["Nome"]}, Cognome: {row["Cognome"]}, Classe: {row["Classe"]}");
                dt2.ImportRow(row);
            }

            dataGridViewProva.DataSource = dt2; // assegna nuova dt2 alla griglia
        }

        private void buttonChiudi_Click(object sender, EventArgs e)
        {
            Close();
        }
        /// <summary>
        /// mostra riga selezionata dall'utente sulla griglia
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonSelezionato_Click(object sender, EventArgs e)
        {
            if (dataGridViewProva.SelectedRows.Count > 0)
                MessageBox.Show("Selezionato: " + dataGridViewProva.SelectedRows[0].Cells[0].Value.ToString());
            else
                MessageBox.Show("Nessuna riga selezionata !");
        }

        private void dataGridViewProva_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            DataRowView drv = dataGridViewProva.Rows[e.RowIndex].DataBoundItem as DataRowView;
            MessageBox.Show(drv.Row["cognome"].ToString() + " " + drv.Row["nome"].ToString());
        }
    }
}
