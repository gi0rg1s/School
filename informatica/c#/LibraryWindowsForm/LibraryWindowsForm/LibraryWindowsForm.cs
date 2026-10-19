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
            if(e.ColumnIndex > 0) {
                
            }
        }
    }
}
