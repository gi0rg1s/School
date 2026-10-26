namespace WinFormsAppDataTableDemo
{
    partial class FormDemo
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            buttonFiltraStudenti = new Button();
            buttonStudenti = new Button();
            buttonAggiungi = new Button();
            buttonSelezionato = new Button();
            buttonLeggiDati = new Button();
            dataGridViewProva = new DataGridView();
            buttonChiudi = new Button();
            ((System.ComponentModel.ISupportInitialize)dataGridViewProva).BeginInit();
            SuspendLayout();
            // 
            // buttonFiltraStudenti
            // 
            buttonFiltraStudenti.Location = new Point(616, 40);
            buttonFiltraStudenti.Margin = new Padding(3, 4, 3, 4);
            buttonFiltraStudenti.Name = "buttonFiltraStudenti";
            buttonFiltraStudenti.Size = new Size(176, 32);
            buttonFiltraStudenti.TabIndex = 15;
            buttonFiltraStudenti.Text = "Filtra studenti per classe";
            buttonFiltraStudenti.UseVisualStyleBackColor = true;
            buttonFiltraStudenti.Click += buttonFiltraStudenti_Click;
            // 
            // buttonStudenti
            // 
            buttonStudenti.Location = new Point(431, 40);
            buttonStudenti.Margin = new Padding(3, 4, 3, 4);
            buttonStudenti.Name = "buttonStudenti";
            buttonStudenti.Size = new Size(176, 32);
            buttonStudenti.TabIndex = 14;
            buttonStudenti.Text = "Carica studenti da lista";
            buttonStudenti.UseVisualStyleBackColor = true;
            buttonStudenti.Click += buttonStudenti_Click;
            // 
            // buttonAggiungi
            // 
            buttonAggiungi.Location = new Point(273, 36);
            buttonAggiungi.Margin = new Padding(3, 4, 3, 4);
            buttonAggiungi.Name = "buttonAggiungi";
            buttonAggiungi.Size = new Size(151, 33);
            buttonAggiungi.TabIndex = 13;
            buttonAggiungi.Text = "aggiungi uno";
            buttonAggiungi.UseVisualStyleBackColor = true;
            buttonAggiungi.Click += buttonAggiungi_Click;
            // 
            // buttonSelezionato
            // 
            buttonSelezionato.Location = new Point(453, 372);
            buttonSelezionato.Margin = new Padding(3, 4, 3, 4);
            buttonSelezionato.Name = "buttonSelezionato";
            buttonSelezionato.Size = new Size(191, 56);
            buttonSelezionato.TabIndex = 12;
            buttonSelezionato.Text = "Mostra riga Selezionata";
            buttonSelezionato.UseVisualStyleBackColor = true;
            buttonSelezionato.Click += buttonSelezionato_Click;
            // 
            // buttonLeggiDati
            // 
            buttonLeggiDati.Location = new Point(73, 33);
            buttonLeggiDati.Margin = new Padding(3, 4, 3, 4);
            buttonLeggiDati.Name = "buttonLeggiDati";
            buttonLeggiDati.Size = new Size(193, 37);
            buttonLeggiDati.TabIndex = 11;
            buttonLeggiDati.Text = "Scorri e leggi tutti i dati";
            buttonLeggiDati.UseVisualStyleBackColor = true;
            buttonLeggiDati.Click += buttonLeggiDati_Click;
            // 
            // dataGridViewProva
            // 
            dataGridViewProva.BackgroundColor = Color.Yellow;
            dataGridViewProva.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewProva.Location = new Point(73, 81);
            dataGridViewProva.Margin = new Padding(3, 4, 3, 4);
            dataGridViewProva.Name = "dataGridViewProva";
            dataGridViewProva.RowHeadersWidth = 51;
            dataGridViewProva.Size = new Size(767, 287);
            dataGridViewProva.TabIndex = 10;
            dataGridViewProva.CellContentClick += dataGridViewProva_CellContentClick;
            // 
            // buttonChiudi
            // 
            buttonChiudi.Font = new Font("Microsoft Sans Serif", 9F, FontStyle.Bold, GraphicsUnit.Point, 0);
            buttonChiudi.Location = new Point(185, 372);
            buttonChiudi.Margin = new Padding(3, 4, 3, 4);
            buttonChiudi.Name = "buttonChiudi";
            buttonChiudi.Size = new Size(103, 56);
            buttonChiudi.TabIndex = 9;
            buttonChiudi.Text = "&Chiudi";
            buttonChiudi.Click += buttonChiudi_Click;
            // 
            // FormDemo
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(920, 443);
            Controls.Add(buttonFiltraStudenti);
            Controls.Add(buttonStudenti);
            Controls.Add(buttonAggiungi);
            Controls.Add(buttonSelezionato);
            Controls.Add(buttonLeggiDati);
            Controls.Add(dataGridViewProva);
            Controls.Add(buttonChiudi);
            Margin = new Padding(3, 4, 3, 4);
            Name = "FormDemo";
            Text = "DataTableDemo";
            Load += FormDemo_Load;
            ((System.ComponentModel.ISupportInitialize)dataGridViewProva).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private Button buttonFiltraStudenti;
        private Button buttonStudenti;
        private Button buttonAggiungi;
        private Button buttonSelezionato;
        private Button buttonLeggiDati;
        private DataGridView dataGridViewProva;
        private Button buttonChiudi;
    }
}
