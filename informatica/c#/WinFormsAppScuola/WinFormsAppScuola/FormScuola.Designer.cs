namespace WinFormsAppScuola
{
    partial class FormScuola
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
            buttonCaricaStudenti = new Button();
            dataGridViewStudenti = new DataGridView();
            carica = new DataGridViewButtonColumn();
            buttonCancella = new Button();
            textBoxMatricola = new TextBox();
            textBoxNome = new TextBox();
            textBoxCognome = new TextBox();
            textBoxDataDiNascita = new TextBox();
            buttonAggiungi = new Button();
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            label4 = new Label();
            buttonModifica = new Button();
            ((System.ComponentModel.ISupportInitialize)dataGridViewStudenti).BeginInit();
            SuspendLayout();
            // 
            // buttonCaricaStudenti
            // 
            buttonCaricaStudenti.Location = new Point(46, 74);
            buttonCaricaStudenti.Name = "buttonCaricaStudenti";
            buttonCaricaStudenti.Size = new Size(118, 29);
            buttonCaricaStudenti.TabIndex = 0;
            buttonCaricaStudenti.Text = "carica studenti";
            buttonCaricaStudenti.UseVisualStyleBackColor = true;
            buttonCaricaStudenti.Click += buttonCaricaStudenti_Click;
            // 
            // dataGridViewStudenti
            // 
            dataGridViewStudenti.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewStudenti.Columns.AddRange(new DataGridViewColumn[] { carica });
            dataGridViewStudenti.Location = new Point(201, 74);
            dataGridViewStudenti.Name = "dataGridViewStudenti";
            dataGridViewStudenti.RowHeadersWidth = 51;
            dataGridViewStudenti.Size = new Size(564, 136);
            dataGridViewStudenti.TabIndex = 1;
            dataGridViewStudenti.CellContentClick += dataGridViewStudenti_CellContentClick;
            // 
            // carica
            // 
            carica.HeaderText = "carica";
            carica.MinimumWidth = 6;
            carica.Name = "carica";
            carica.Text = "click";
            carica.UseColumnTextForButtonValue = true;
            carica.Width = 125;
            // 
            // buttonCancella
            // 
            buttonCancella.Location = new Point(46, 159);
            buttonCancella.Name = "buttonCancella";
            buttonCancella.Size = new Size(118, 29);
            buttonCancella.TabIndex = 3;
            buttonCancella.Text = "Cancella";
            buttonCancella.UseVisualStyleBackColor = true;
            buttonCancella.Click += buttonCancella_Click;
            // 
            // textBoxMatricola
            // 
            textBoxMatricola.Location = new Point(55, 283);
            textBoxMatricola.Name = "textBoxMatricola";
            textBoxMatricola.Size = new Size(122, 27);
            textBoxMatricola.TabIndex = 4;
            // 
            // textBoxNome
            // 
            textBoxNome.Location = new Point(201, 283);
            textBoxNome.Name = "textBoxNome";
            textBoxNome.Size = new Size(122, 27);
            textBoxNome.TabIndex = 5;
            // 
            // textBoxCognome
            // 
            textBoxCognome.Location = new Point(344, 283);
            textBoxCognome.Name = "textBoxCognome";
            textBoxCognome.Size = new Size(122, 27);
            textBoxCognome.TabIndex = 6;
            // 
            // textBoxDataDiNascita
            // 
            textBoxDataDiNascita.Location = new Point(527, 283);
            textBoxDataDiNascita.Name = "textBoxDataDiNascita";
            textBoxDataDiNascita.Size = new Size(149, 27);
            textBoxDataDiNascita.TabIndex = 7;
            // 
            // buttonAggiungi
            // 
            buttonAggiungi.Location = new Point(55, 356);
            buttonAggiungi.Name = "buttonAggiungi";
            buttonAggiungi.Size = new Size(157, 39);
            buttonAggiungi.TabIndex = 8;
            buttonAggiungi.Text = "aggiungi";
            buttonAggiungi.UseVisualStyleBackColor = true;
            buttonAggiungi.Click += buttonAggiungi_Click;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(64, 247);
            label1.Name = "label1";
            label1.Size = new Size(72, 20);
            label1.TabIndex = 9;
            label1.Text = "Matricola";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(209, 246);
            label2.Name = "label2";
            label2.Size = new Size(47, 20);
            label2.TabIndex = 10;
            label2.Text = "nome";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(355, 245);
            label3.Name = "label3";
            label3.Size = new Size(72, 20);
            label3.TabIndex = 11;
            label3.Text = "cognome";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(534, 243);
            label4.Name = "label4";
            label4.Size = new Size(108, 20);
            label4.TabIndex = 12;
            label4.Text = "Data di nascita";
            // 
            // buttonModifica
            // 
            buttonModifica.Location = new Point(237, 356);
            buttonModifica.Name = "buttonModifica";
            buttonModifica.Size = new Size(157, 39);
            buttonModifica.TabIndex = 13;
            buttonModifica.Text = "modifica";
            buttonModifica.UseVisualStyleBackColor = true;
            buttonModifica.Click += buttonModifica_Click;
            // 
            // FormScuola
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(buttonModifica);
            Controls.Add(label4);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(buttonAggiungi);
            Controls.Add(textBoxDataDiNascita);
            Controls.Add(textBoxCognome);
            Controls.Add(textBoxNome);
            Controls.Add(textBoxMatricola);
            Controls.Add(buttonCancella);
            Controls.Add(dataGridViewStudenti);
            Controls.Add(buttonCaricaStudenti);
            Name = "FormScuola";
            Text = "FormScuola";
            ((System.ComponentModel.ISupportInitialize)dataGridViewStudenti).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button buttonCaricaStudenti;
        private DataGridView dataGridViewStudenti;
        private DataGridViewButtonColumn carica;
        private Button buttonCancella;
        private TextBox textBoxMatricola;
        private TextBox textBoxNome;
        private TextBox textBoxCognome;
        private TextBox textBoxDataDiNascita;
        private Button buttonAggiungi;
        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private Button buttonModifica;
    }
}
