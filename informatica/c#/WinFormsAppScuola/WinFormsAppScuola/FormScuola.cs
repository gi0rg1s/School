using System.ComponentModel;
using WinFormsLibraryScuola;


namespace WinFormsAppScuola
{
    public partial class FormScuola : Form
    {
        BindingList<Studente> studenti;
        public FormScuola()
        {
            InitializeComponent();
            studenti = Studente.GetStudenti();
            dataGridViewStudenti.DataSource = studenti;
        }

        private void buttonCaricaStudenti_Click(object sender, EventArgs e)
        {
            dataGridViewStudenti.DataSource = studenti;
        }

        private void dataGridViewStudenti_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            Studente studente = studenti[e.RowIndex];
            if (e.ColumnIndex == 0)
            {

                //MessageBox.Show(studente.Cognome);
                List<int> voti = studente.Voti;
                var v = voti.Select(vo => new { Voto = vo }).Cast<Object>().ToList();
                FormVoti formVoti = new FormVoti(v);
                formVoti.ShowDialog();
            }
            else
            {
                textBoxMatricola.Text = studente.Matricola.ToString();
                textBoxNome.Text = studente.Nome;
                textBoxCognome.Text = studente.Cognome;
                textBoxDataDiNascita.Text = studente.DataDiNascita.ToString();
            }
        }

        private void buttonCancella_Click(object sender, EventArgs e)
        {
            studenti.RemoveAt(1);
        }

        private void buttonAggiungi_Click(object sender, EventArgs e)
        {
            Studente s = new Studente(Convert.ToInt32(textBoxMatricola.Text), textBoxNome.Text, textBoxCognome.Text, DateTime.Parse(textBoxDataDiNascita.Text), new List<int> { 1, 3, 5 });
            studenti.Add(s);
        }

        private void buttonModifica_Click(object sender, EventArgs e)
        {
            Studente s = studenti.FirstOrDefault(studente => studente.Matricola.ToString() == textBoxMatricola.Text);
            s.Nome = textBoxNome.Text;
            s.Cognome = textBoxCognome.Text;
            s.DataDiNascita = DateTime.Parse(textBoxDataDiNascita.Text);
            dataGridViewStudenti.Refresh();
        }
    }
}
