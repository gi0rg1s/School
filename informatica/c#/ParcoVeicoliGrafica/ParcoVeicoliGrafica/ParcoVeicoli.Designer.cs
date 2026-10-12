namespace ParcoVeicoliGrafica
{
    partial class ParcoVeicoli
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
            parcoVeicoliGrid = new DataGridView();
            textBoxTarga = new TextBox();
            labelTarga = new Label();
            textBoxMarca = new TextBox();
            labelMarca = new Label();
            textBoxModello = new TextBox();
            labelModello = new Label();
            textBoxChilometri = new TextBox();
            labelChilometri = new Label();
            buttonAggiungi = new Button();
            buttonModifica = new Button();
            buttonElimina = new Button();
            buttonCarica = new Button();
            comboBoxType = new ComboBox();
            ((System.ComponentModel.ISupportInitialize)parcoVeicoliGrid).BeginInit();
            SuspendLayout();
            // 
            // parcoVeicoliGrid
            // 
            parcoVeicoliGrid.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            parcoVeicoliGrid.Location = new Point(254, 49);
            parcoVeicoliGrid.Name = "parcoVeicoliGrid";
            parcoVeicoliGrid.RowHeadersWidth = 51;
            parcoVeicoliGrid.Size = new Size(461, 200);
            parcoVeicoliGrid.TabIndex = 0;
            // 
            // textBoxTarga
            // 
            textBoxTarga.Location = new Point(34, 390);
            textBoxTarga.Name = "textBoxTarga";
            textBoxTarga.Size = new Size(128, 27);
            textBoxTarga.TabIndex = 1;
            // 
            // labelTarga
            // 
            labelTarga.AutoSize = true;
            labelTarga.Location = new Point(36, 344);
            labelTarga.Name = "labelTarga";
            labelTarga.Size = new Size(45, 20);
            labelTarga.TabIndex = 2;
            labelTarga.Text = "Targa";
            // 
            // textBoxMarca
            // 
            textBoxMarca.Location = new Point(173, 390);
            textBoxMarca.Name = "textBoxMarca";
            textBoxMarca.Size = new Size(129, 27);
            textBoxMarca.TabIndex = 3;
            // 
            // labelMarca
            // 
            labelMarca.AutoSize = true;
            labelMarca.Location = new Point(173, 344);
            labelMarca.Name = "labelMarca";
            labelMarca.Size = new Size(50, 20);
            labelMarca.TabIndex = 4;
            labelMarca.Text = "Marca";
            // 
            // textBoxModello
            // 
            textBoxModello.Location = new Point(320, 390);
            textBoxModello.Name = "textBoxModello";
            textBoxModello.Size = new Size(128, 27);
            textBoxModello.TabIndex = 5;
            // 
            // labelModello
            // 
            labelModello.AutoSize = true;
            labelModello.Location = new Point(320, 347);
            labelModello.Name = "labelModello";
            labelModello.Size = new Size(65, 20);
            labelModello.TabIndex = 6;
            labelModello.Text = "Modello";
            // 
            // textBoxChilometri
            // 
            textBoxChilometri.Location = new Point(454, 390);
            textBoxChilometri.Name = "textBoxChilometri";
            textBoxChilometri.Size = new Size(127, 27);
            textBoxChilometri.TabIndex = 7;
            // 
            // labelChilometri
            // 
            labelChilometri.AutoSize = true;
            labelChilometri.Location = new Point(454, 347);
            labelChilometri.Name = "labelChilometri";
            labelChilometri.Size = new Size(135, 20);
            labelChilometri.TabIndex = 8;
            labelChilometri.Text = "Chilometri percorsi";
            // 
            // buttonAggiungi
            // 
            buttonAggiungi.Location = new Point(37, 129);
            buttonAggiungi.Name = "buttonAggiungi";
            buttonAggiungi.Size = new Size(150, 29);
            buttonAggiungi.TabIndex = 9;
            buttonAggiungi.Text = "Aggiungi";
            buttonAggiungi.UseVisualStyleBackColor = true;
            // 
            // buttonModifica
            // 
            buttonModifica.Location = new Point(37, 164);
            buttonModifica.Name = "buttonModifica";
            buttonModifica.Size = new Size(150, 30);
            buttonModifica.TabIndex = 10;
            buttonModifica.Text = "Modifica";
            buttonModifica.UseVisualStyleBackColor = true;
            // 
            // buttonElimina
            // 
            buttonElimina.Location = new Point(37, 200);
            buttonElimina.Name = "buttonElimina";
            buttonElimina.Size = new Size(150, 31);
            buttonElimina.TabIndex = 11;
            buttonElimina.Text = "Elimina";
            buttonElimina.UseVisualStyleBackColor = true;
            // 
            // buttonCarica
            // 
            buttonCarica.Location = new Point(37, 91);
            buttonCarica.Name = "buttonCarica";
            buttonCarica.Size = new Size(150, 28);
            buttonCarica.TabIndex = 12;
            buttonCarica.Text = "Carica";
            buttonCarica.UseVisualStyleBackColor = true;
            // 
            // comboBoxType
            // 
            comboBoxType.FormattingEnabled = true;
            comboBoxType.Location = new Point(34, 280);
            comboBoxType.Name = "comboBoxType";
            comboBoxType.Size = new Size(179, 28);
            comboBoxType.TabIndex = 13;
            // 
            // ParcoVeicoli
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(comboBoxType);
            Controls.Add(buttonCarica);
            Controls.Add(buttonElimina);
            Controls.Add(buttonModifica);
            Controls.Add(buttonAggiungi);
            Controls.Add(labelChilometri);
            Controls.Add(textBoxChilometri);
            Controls.Add(labelModello);
            Controls.Add(textBoxModello);
            Controls.Add(labelMarca);
            Controls.Add(textBoxMarca);
            Controls.Add(labelTarga);
            Controls.Add(textBoxTarga);
            Controls.Add(parcoVeicoliGrid);
            Name = "ParcoVeicoli";
            Text = "ParcoVeicoli";
            ((System.ComponentModel.ISupportInitialize)parcoVeicoliGrid).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView parcoVeicoliGrid;
        private TextBox textBoxTarga;
        private Label labelTarga;
        private TextBox textBoxMarca;
        private Label labelMarca;
        private TextBox textBoxModello;
        private Label labelModello;
        private TextBox textBoxChilometri;
        private Label labelChilometri;
        private Button buttonAggiungi;
        private Button buttonModifica;
        private Button buttonElimina;
        private Button buttonCarica;
        private ComboBox comboBoxType;
    }
}
