namespace WinFormsAppArticolo
{
    partial class FormArticoli
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            dataGridViewArticoli = new DataGridView();
            labelArticoli = new Label();
            label1 = new Label();
            label2 = new Label();
            importoLabel = new Label();
            mediaLabel = new Label();
            buttonCalculate = new Button();
            buttonAggiungi = new Button();
            textBoxName = new TextBox();
            textBoxPrice = new TextBox();
            label3 = new Label();
            Prezzo = new Label();
            ((System.ComponentModel.ISupportInitialize)dataGridViewArticoli).BeginInit();
            SuspendLayout();
            // 
            // dataGridViewArticoli
            // 
            dataGridViewArticoli.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewArticoli.Location = new Point(23, 39);
            dataGridViewArticoli.Name = "dataGridViewArticoli";
            dataGridViewArticoli.RowHeadersWidth = 51;
            dataGridViewArticoli.Size = new Size(424, 191);
            dataGridViewArticoli.TabIndex = 0;
            // 
            // labelArticoli
            // 
            labelArticoli.AutoSize = true;
            labelArticoli.Location = new Point(47, 42);
            labelArticoli.Name = "labelArticoli";
            labelArticoli.Size = new Size(0, 20);
            labelArticoli.TabIndex = 1;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(480, 39);
            label1.Name = "label1";
            label1.Size = new Size(113, 20);
            label1.TabIndex = 2;
            label1.Text = "Importo totale: ";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(480, 88);
            label2.Name = "label2";
            label2.Size = new Size(205, 20);
            label2.TabIndex = 3;
            label2.Text = "Media di prezzo per articolo: ";
            // 
            // importoLabel
            // 
            importoLabel.AutoSize = true;
            importoLabel.Location = new Point(692, 39);
            importoLabel.Name = "importoLabel";
            importoLabel.Size = new Size(0, 20);
            importoLabel.TabIndex = 4;
            // 
            // mediaLabel
            // 
            mediaLabel.AutoSize = true;
            mediaLabel.Location = new Point(692, 88);
            mediaLabel.Name = "mediaLabel";
            mediaLabel.Size = new Size(0, 20);
            mediaLabel.TabIndex = 5;
            // 
            // buttonCalculate
            // 
            buttonCalculate.Location = new Point(480, 154);
            buttonCalculate.Name = "buttonCalculate";
            buttonCalculate.Size = new Size(212, 29);
            buttonCalculate.TabIndex = 6;
            buttonCalculate.Text = "Calcola";
            buttonCalculate.UseVisualStyleBackColor = true;
            buttonCalculate.Click += buttonCalculate_Click;
            // 
            // buttonAggiungi
            // 
            buttonAggiungi.Location = new Point(436, 336);
            buttonAggiungi.Name = "buttonAggiungi";
            buttonAggiungi.Size = new Size(209, 30);
            buttonAggiungi.TabIndex = 7;
            buttonAggiungi.Text = "Aggiungi";
            buttonAggiungi.UseVisualStyleBackColor = true;
            buttonAggiungi.Click += buttonAggiungi_Click;
            // 
            // textBoxName
            // 
            textBoxName.Location = new Point(23, 336);
            textBoxName.Name = "textBoxName";
            textBoxName.Size = new Size(142, 27);
            textBoxName.TabIndex = 8;
            // 
            // textBoxPrice
            // 
            textBoxPrice.Location = new Point(207, 336);
            textBoxPrice.Name = "textBoxPrice";
            textBoxPrice.Size = new Size(142, 27);
            textBoxPrice.TabIndex = 10;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(23, 299);
            label3.Name = "label3";
            label3.Size = new Size(50, 20);
            label3.TabIndex = 11;
            label3.Text = "Nome";
            // 
            // Prezzo
            // 
            Prezzo.AutoSize = true;
            Prezzo.Location = new Point(207, 299);
            Prezzo.Name = "Prezzo";
            Prezzo.Size = new Size(53, 20);
            Prezzo.TabIndex = 12;
            Prezzo.Text = "Prezzo";
            // 
            // FormArticoli
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(Prezzo);
            Controls.Add(label3);
            Controls.Add(textBoxPrice);
            Controls.Add(textBoxName);
            Controls.Add(buttonAggiungi);
            Controls.Add(buttonCalculate);
            Controls.Add(mediaLabel);
            Controls.Add(importoLabel);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(labelArticoli);
            Controls.Add(dataGridViewArticoli);
            Name = "FormArticoli";
            Text = "FormArticoli";
            ((System.ComponentModel.ISupportInitialize)dataGridViewArticoli).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView dataGridViewArticoli;
        private Label labelArticoli;
        private Label label1;
        private Label label2;
        private Label importoLabel;
        private Label mediaLabel;
        private Button buttonCalculate;
        private Button buttonAggiungi;
        private TextBox textBoxName;
        private TextBox textBoxPrice;
        private Label label3;
        private Label Prezzo;
    }
}