namespace LibraryWindowsForm
{
    partial class LibraryWindowsForm
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
            risorseGridView = new DataGridView();
            buttonAggiungi = new Button();
            buttonModifica = new Button();
            buttonElimina = new Button();
            buttonCarica = new Button();
            Codice = new Label();
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            textBoxCodice = new TextBox();
            textBoxTitolo = new TextBox();
            textBoxAutore = new TextBox();
            textBoxGiorniPrestito = new TextBox();
            labelLastElement = new Label();
            textBoxLastElement = new TextBox();
            radioButtonLibro = new RadioButton();
            radioButtonAudiolibro = new RadioButton();
            ((System.ComponentModel.ISupportInitialize)risorseGridView).BeginInit();
            SuspendLayout();
            // 
            // risorseGridView
            // 
            risorseGridView.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            risorseGridView.Location = new Point(35, 72);
            risorseGridView.Name = "risorseGridView";
            risorseGridView.RowHeadersWidth = 51;
            risorseGridView.Size = new Size(428, 213);
            risorseGridView.TabIndex = 0;
            risorseGridView.CellContentClick += risorseGridView_CellContentClick;
            // 
            // buttonAggiungi
            // 
            buttonAggiungi.Location = new Point(535, 115);
            buttonAggiungi.Name = "buttonAggiungi";
            buttonAggiungi.Size = new Size(228, 37);
            buttonAggiungi.TabIndex = 1;
            buttonAggiungi.Text = "Aggiungi";
            buttonAggiungi.UseVisualStyleBackColor = true;
            // 
            // buttonModifica
            // 
            buttonModifica.Location = new Point(535, 158);
            buttonModifica.Name = "buttonModifica";
            buttonModifica.Size = new Size(228, 37);
            buttonModifica.TabIndex = 2;
            buttonModifica.Text = "Modifica";
            buttonModifica.UseVisualStyleBackColor = true;
            // 
            // buttonElimina
            // 
            buttonElimina.Location = new Point(535, 201);
            buttonElimina.Name = "buttonElimina";
            buttonElimina.Size = new Size(227, 38);
            buttonElimina.TabIndex = 3;
            buttonElimina.Text = "Elimina";
            buttonElimina.UseVisualStyleBackColor = true;
            // 
            // buttonCarica
            // 
            buttonCarica.Location = new Point(534, 72);
            buttonCarica.Name = "buttonCarica";
            buttonCarica.Size = new Size(228, 37);
            buttonCarica.TabIndex = 4;
            buttonCarica.Text = "Carica";
            buttonCarica.UseVisualStyleBackColor = true;
            buttonCarica.Click += buttonCarica_Click;
            // 
            // Codice
            // 
            Codice.AutoSize = true;
            Codice.Location = new Point(26, 350);
            Codice.Name = "Codice";
            Codice.Size = new Size(55, 20);
            Codice.TabIndex = 5;
            Codice.Text = "Codice";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(164, 350);
            label1.Name = "label1";
            label1.Size = new Size(48, 20);
            label1.TabIndex = 6;
            label1.Text = "Titolo";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(298, 350);
            label2.Name = "label2";
            label2.Size = new Size(54, 20);
            label2.TabIndex = 7;
            label2.Text = "Autore";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(414, 350);
            label3.Name = "label3";
            label3.Size = new Size(121, 20);
            label3.TabIndex = 8;
            label3.Text = "Giorni di prestito";
            // 
            // textBoxCodice
            // 
            textBoxCodice.Location = new Point(26, 391);
            textBoxCodice.Name = "textBoxCodice";
            textBoxCodice.Size = new Size(105, 27);
            textBoxCodice.TabIndex = 9;
            // 
            // textBoxTitolo
            // 
            textBoxTitolo.Location = new Point(164, 391);
            textBoxTitolo.Name = "textBoxTitolo";
            textBoxTitolo.Size = new Size(101, 27);
            textBoxTitolo.TabIndex = 10;
            // 
            // textBoxAutore
            // 
            textBoxAutore.Location = new Point(298, 390);
            textBoxAutore.Name = "textBoxAutore";
            textBoxAutore.Size = new Size(101, 27);
            textBoxAutore.TabIndex = 11;
            // 
            // textBoxGiorniPrestito
            // 
            textBoxGiorniPrestito.Location = new Point(423, 390);
            textBoxGiorniPrestito.Name = "textBoxGiorniPrestito";
            textBoxGiorniPrestito.Size = new Size(130, 27);
            textBoxGiorniPrestito.TabIndex = 12;
            // 
            // labelLastElement
            // 
            labelLastElement.AutoSize = true;
            labelLastElement.Location = new Point(612, 349);
            labelLastElement.Name = "labelLastElement";
            labelLastElement.Size = new Size(0, 20);
            labelLastElement.TabIndex = 13;
            // 
            // textBoxLastElement
            // 
            textBoxLastElement.Location = new Point(602, 390);
            textBoxLastElement.Name = "textBoxLastElement";
            textBoxLastElement.Size = new Size(134, 27);
            textBoxLastElement.TabIndex = 14;
            // 
            // radioButtonLibro
            // 
            radioButtonLibro.AutoSize = true;
            radioButtonLibro.Location = new Point(537, 298);
            radioButtonLibro.Name = "radioButtonLibro";
            radioButtonLibro.Size = new Size(64, 24);
            radioButtonLibro.TabIndex = 15;
            radioButtonLibro.TabStop = true;
            radioButtonLibro.Text = "Libro";
            radioButtonLibro.UseVisualStyleBackColor = true;
            // 
            // radioButtonAudiolibro
            // 
            radioButtonAudiolibro.AutoSize = true;
            radioButtonAudiolibro.Location = new Point(686, 300);
            radioButtonAudiolibro.Name = "radioButtonAudiolibro";
            radioButtonAudiolibro.Size = new Size(101, 24);
            radioButtonAudiolibro.TabIndex = 16;
            radioButtonAudiolibro.TabStop = true;
            radioButtonAudiolibro.Text = "Audiolibro";
            radioButtonAudiolibro.UseVisualStyleBackColor = true;
            // 
            // LibraryWindowsForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(984, 474);
            Controls.Add(radioButtonAudiolibro);
            Controls.Add(radioButtonLibro);
            Controls.Add(textBoxLastElement);
            Controls.Add(labelLastElement);
            Controls.Add(textBoxGiorniPrestito);
            Controls.Add(textBoxAutore);
            Controls.Add(textBoxTitolo);
            Controls.Add(textBoxCodice);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(Codice);
            Controls.Add(buttonCarica);
            Controls.Add(buttonElimina);
            Controls.Add(buttonModifica);
            Controls.Add(buttonAggiungi);
            Controls.Add(risorseGridView);
            Name = "LibraryWindowsForm";
            Text = "LibraryWindowsForm";
            ((System.ComponentModel.ISupportInitialize)risorseGridView).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView risorseGridView;
        private Button buttonAggiungi;
        private Button buttonModifica;
        private Button buttonElimina;
        private Button buttonCarica;
        private Label Codice;
        private Label label1;
        private Label label2;
        private Label label3;
        private TextBox textBoxCodice;
        private TextBox textBoxTitolo;
        private TextBox textBoxAutore;
        private TextBox textBoxGiorniPrestito;
        private Label labelLastElement;
        private TextBox textBoxLastElement;
        private RadioButton radioButtonLibro;
        private RadioButton radioButtonAudiolibro;
    }
}
