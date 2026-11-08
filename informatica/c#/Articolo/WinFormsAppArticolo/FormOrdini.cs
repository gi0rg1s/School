using System.Data;
using WinFormsLibraryArticolo;

namespace WinFormsAppArticolo
{
    public partial class FormOrdini : Form
    {
        public FormOrdini()
        {
            InitializeComponent();
            Dati d = new Dati();
            DataTable dt = new DataTable();
            dt = d.getOrdini();

            dataGridViewOrdine.DataSource = dt;
        }

        private void dataGridViewOrdine_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            int idOrdine = (int)dataGridViewOrdine.Rows[e.RowIndex].Cells["ID"].Value;
            FormArticoli formArticoli = new FormArticoli(idOrdine);
            formArticoli.ShowDialog();
        }
    }
}
