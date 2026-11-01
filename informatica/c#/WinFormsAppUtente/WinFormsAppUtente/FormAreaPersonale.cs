using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using WinFormsLibraryUtente;

namespace WinFormsAppUtente
{
    public partial class FormAreaPersonale : Form
    {
        private Utente u;
        public FormAreaPersonale(Utente user)
        {
            InitializeComponent();
            u = user;
            labelEmail.Text = u.Email;
            labelUser.Text = u.User;
            labelNome.Text = $"Bentornato {u.User}!";
        }

        private void buttonLogOut_Click(object sender, EventArgs e)
        {
            FormUtente f = new FormUtente();
            f.ShowDialog();
        }
    }
}
