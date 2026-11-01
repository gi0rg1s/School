using Microsoft.VisualBasic.ApplicationServices;
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
    public partial class FormLogin : Form
    {
        private List<Utente> users;
        public FormLogin(List<Utente> u)
        {
            InitializeComponent();
            users = u;
        }

        private void buttonLog_Click(object sender, EventArgs e)
        {
            Utente utente = users.FirstOrDefault(u => u.Email == textBoxEmail.Text && u.Password == textBoxPassword.Text);
            if (utente != null) { 
                FormAreaPersonale personal = new FormAreaPersonale(utente);
                personal.ShowDialog();
            }
        }
    }
}
