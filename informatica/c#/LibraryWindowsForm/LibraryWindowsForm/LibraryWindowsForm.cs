using LibreriaBiblioteca;
using System.ComponentModel;
namespace LibraryWindowsForm
{
    public partial class LibraryWindowsForm : Form
    {
        private BindingList<Risorsa> catalogo;
        public LibraryWindowsForm()
        {
            InitializeComponent();
            catalogo = new BindingList<Risorsa>
            {
                new Libro ("1234", "La neve in fondo al mare", "Matteo Bussola", 33, 205),
                new Audiolibro("5678", "Il silenzio delle onde", "Giulia Rossi", 20, 120),
                new Libro("9101", "Il giardino segreto", "Frances Hodgson Burnett", 15, 250),
                new Audiolibro("1121", "La casa degli spiriti", "Isabel Allende", 25, 180)
            };
        }

        private void buttonCarica_Click(object sender, EventArgs e)
        {
            risorseGridView.DataSource = catalogo;
        }

        private void risorseGridView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            Risorsa r = catalogo[e.RowIndex];
            if (e.ColumnIndex > 0)
            {
                textBoxAutore.Text = r.Autore;
                textBoxCodice.Text = r.Codice;
                textBoxGiorniPrestito.Text = r.GiorniInPrestito.ToString();
                textBoxTitolo.Text = r.Titolo;
                labelLastElement.Text = r is Libro ? "Numero di pagine" : r is Audiolibro ? "Minuti totali" : "";
                textBoxLastElement.Text = r is Libro l ? l.NumeroPagine.ToString() : r is Audiolibro a ? a.DurataMinuti.ToString() : "";
            }
        }

        private void buttonElimina_Click(object sender, EventArgs e)
        {
            catalogo.RemoveAt(risorseGridView.CurrentCell.RowIndex);
        }

        private void buttonAggiungi_Click(object sender, EventArgs e)
        {
            if (radioButtonAudiolibro.Checked)
            {
                Audiolibro a = new Audiolibro(textBoxCodice.Text, textBoxTitolo.Text, textBoxAutore.Text, int.Parse(textBoxGiorniPrestito.Text), int.Parse(textBoxLastElement.Text));
                catalogo.Add(a);
            }
            else if (radioButtonLibro.Checked)
            {
                Libro l = new Libro(textBoxCodice.Text, textBoxTitolo.Text, textBoxAutore.Text, int.Parse(textBoxGiorniPrestito.Text), int.Parse(textBoxLastElement.Text));
                catalogo.Add(l);
            }
        }

        private void buttonModifica_Click(object sender, EventArgs e)
        {
            Risorsa r = catalogo.FirstOrDefault(risorsa => risorsa.Codice == textBoxCodice.Text);
            r.Titolo = textBoxTitolo.Text;
            r.Autore = textBoxAutore.Text;
            r.GiorniInPrestito = int.Parse(textBoxGiorniPrestito.Text);

            if (r is Libro libro) {
                libro.NumeroPagine = int.Parse(textBoxLastElement.Text);
            }
            else if(r is Audiolibro audiolibro)
            {
                audiolibro.DurataMinuti = int.Parse(textBoxLastElement.Text);
            }
            risorseGridView.Refresh();
        }
    }
}
