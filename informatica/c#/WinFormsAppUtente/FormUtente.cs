using System.Data;
using WinFormsLibraryUtente;


namespace WinFormsAppUtente
{
    public partial class FormUtente : Form
    {
        public FormUtente()
        {
            InitializeComponent();

            Data d = new Data();
            dataGridView.DataSource = d.getUserCompleti();
            dataGridViewAnagrafica.DataSource = d.getAnagrafica();
        }

        private void buttonLogIn_Click(object sender, EventArgs e)
        {
            Utente u = new Utente(0, "", "", "");
            FormLogin f = new FormLogin(u.getSampleData());
            f.ShowDialog();
        }

        private void buttonAnagrafica_Click(object sender, EventArgs e)
        {
           
        }
    }
}
