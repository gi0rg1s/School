using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using WinFormsLibraryArticolo;

namespace WinFormsAppArticolo
{
    public partial class FormArticoli : Form
    {
        Dati d = new Dati();
        int idOrdine;
        public FormArticoli(int idOrdine)
        {
            InitializeComponent();
            this.idOrdine = idOrdine;
            labelArticoli.Text = $"Articoli dell'ordine {idOrdine}";

            dataGridViewArticoli.DataSource = d.articoliPerOrdine(idOrdine);
        }

        private void buttonCalculate_Click(object sender, EventArgs e)
        {
            importoLabel.Text = d.SommaImporti(idOrdine).ToString();
            mediaLabel.Text = d.MediaImporti(idOrdine).ToString();
        }

        private void buttonAggiungi_Click(object sender, EventArgs e)
        {
            d.AggiungiArticolo(idOrdine);
            dataGridViewArticoli.DataSource = d.articoliPerOrdine(idOrdine);
        }
    }
}
