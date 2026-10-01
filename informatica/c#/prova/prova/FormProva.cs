using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace prova
{
    public partial class FormProva : Form
    {
        List<Studente> studenteList;
        public FormProva()
        {
            InitializeComponent();
            studenteList = new List<Studente>();
            studenteList.Add(new Studente(1, "pippo", "pippo"));
            studenteList.Add(new Studente(2, "topolino", "topolino"));
            studenteList.Add(new Studente(3, "eta", "beta"));
        }


        private void FormProva_Load(object sender, EventArgs e)
        {
            /*
            comboBoxProva.Items.Add("uno");
            comboBoxProva.Items.Add("due");
            comboBoxProva.Items.Add("tre");
            */
            comboBoxProva.DataSource = studenteList;
            comboBoxProva.DisplayMember = "Cognome";
        }

        private void comboBoxProva_SelectedIndexChanged(object sender, EventArgs e)
        {
            Studente s = ((Studente)comboBoxProva.SelectedItem);
            labelProva.Text = s.Cognome + " " + s.Nome;
        }
    }
}
