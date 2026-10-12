using parcoVeicoli;
using System.ComponentModel;
using System.Collections.Generic;


namespace ParcoVeicoliGrafica
{
    public partial class ParcoVeicoli : Form
    {
        BindingList<Veicolo> veicoli;

        public ParcoVeicoli()
        {
            InitializeComponent();
            veicoli = new BindingList<Veicolo>{
                new Auto(4, "AB123CD", "Fiat", "Punto", 15000),
                new Camion(5000, "EF456GH", "Iveco", "Stralis", 30000),
                new Auto(2, "IJ789KL", "Ford", "Mustang", 20000),
                new Camion(3, "MN012OP", "Scania", "R500", 40000)
            };

            // popola la combobox dei tipi (semplice: nomi)
            comboBoxType.Items.Clear();
            comboBoxType.Items.AddRange(new object[] { "Auto", "Camion" });
            comboBoxType.SelectedIndexChanged += comboBoxType_SelectedIndexChanged;
            buttonAggiungi.Click += buttonAggiungi_Click;
        }

        private void buttonCarica_Click(object sender, EventArgs e)
        {
            parcoVeicoliGrid.DataSource = veicoli;
        }

        private void comboBoxType_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBoxType.SelectedItem == "Auto")
            {
                labelLastAttribute.Text = "Numero di porte";
            }
            else if (comboBoxType.SelectedItem == "Camion")
            {
                labelLastAttribute.Text = "Capacità di carico";
            }
        }

        private void buttonAggiungi_Click(object sender, EventArgs e)
        {
            if (comboBoxType.SelectedItem == "Auto")
            {
                Auto auto = new Auto(int.Parse(textBoxLastAttribute.Text), textBoxTarga.Text, textBoxMarca.Text, textBoxModello.Text, double.Parse(textBoxChilometri.Text));
                veicoli.Add(auto);
            }
            else if (comboBoxType.SelectedItem == "Camion")
            {
                Camion camion = new Camion(double.Parse(textBoxLastAttribute.Text), textBoxTarga.Text, textBoxMarca.Text, textBoxModello.Text, double.Parse(textBoxChilometri.Text));
                veicoli.Add(camion);
            }

        }

        private void parcoVeicoliGrid_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            Veicolo veicolo = veicoli[e.RowIndex];
            if (e.ColumnIndex > 0)
            {
                textBoxChilometri.Text = veicolo.ChilometriPercorsi.ToString();
                textBoxMarca.Text = veicolo.Marca;
                textBoxModello.Text = veicolo.Modello;
                textBoxTarga.Text = veicolo.Targa;
                textBoxLastAttribute.Text = veicolo is Auto auto ? auto.NumeroPorte.ToString() : veicolo is Camion camion ? camion.CapacitaCarico.ToString() : string.Empty;
                labelLastAttribute.Text = veicolo is Auto ? "Numero di porte" : veicolo is Camion ? "Capacità di carico" : string.Empty; ;
            }
        }

        private void buttonElimina_Click(object sender, EventArgs e)
        {
            veicoli.RemoveAt(parcoVeicoliGrid.CurrentCell.RowIndex);
        }

        private void buttonModifica_Click(object sender, EventArgs e)
        {
            Veicolo v = veicoli.FirstOrDefault(veicolo => veicolo.Targa.ToString() == textBoxTarga.Text);
            v.Marca = textBoxMarca.Text;
            v.Modello = textBoxModello.Text;
            v.ChilometriPercorsi = double.Parse(textBoxChilometri.Text);
            if (v is Camion camion)
            {
                camion.ChilometriPercorsi = double.Parse(textBoxLastAttribute.Text);
            }
            else if (v is Auto auto) {
                {
                    auto.NumeroPorte = int.Parse(textBoxLastAttribute.Text);
                }
            }
            parcoVeicoliGrid.Refresh();
        }
    }
}
