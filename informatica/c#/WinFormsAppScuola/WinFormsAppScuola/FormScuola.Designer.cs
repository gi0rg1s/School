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
            dataGridViewVoti = new DataGridView();
            ((System.ComponentModel.ISupportInitialize)dataGridViewStudenti).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewVoti).BeginInit();
            SuspendLayout();
            // 
            // buttonCaricaStudenti
            // 
            buttonCaricaStudenti.Location = new Point(46, 74);
            buttonCaricaStudenti.Name = "buttonCaricaStudenti";
            buttonCaricaStudenti.Size = new Size(173, 29);
            buttonCaricaStudenti.TabIndex = 0;
            buttonCaricaStudenti.Text = "carica studenti";
            buttonCaricaStudenti.UseVisualStyleBackColor = true;
            buttonCaricaStudenti.Click += buttonCaricaStudenti_Click;
            // 
            // dataGridViewStudenti
            // 
            dataGridViewStudenti.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewStudenti.Columns.AddRange(new DataGridViewColumn[] { carica });
            dataGridViewStudenti.Location = new Point(279, 74);
            dataGridViewStudenti.Name = "dataGridViewStudenti";
            dataGridViewStudenti.RowHeadersWidth = 51;
            dataGridViewStudenti.Size = new Size(486, 136);
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
            // dataGridViewVoti
            // 
            dataGridViewVoti.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewVoti.Location = new Point(279, 241);
            dataGridViewVoti.Name = "dataGridViewVoti";
            dataGridViewVoti.RowHeadersWidth = 51;
            dataGridViewVoti.Size = new Size(486, 116);
            dataGridViewVoti.TabIndex = 2;
            // 
            // FormScuola
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(dataGridViewVoti);
            Controls.Add(dataGridViewStudenti);
            Controls.Add(buttonCaricaStudenti);
            Name = "FormScuola";
            Text = "FormScuola";
            ((System.ComponentModel.ISupportInitialize)dataGridViewStudenti).EndInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewVoti).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private Button buttonCaricaStudenti;
        private DataGridView dataGridViewStudenti;
        private DataGridViewButtonColumn carica;
        private DataGridView dataGridViewVoti;
    }
}
