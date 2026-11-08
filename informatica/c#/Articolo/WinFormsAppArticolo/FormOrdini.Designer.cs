namespace WinFormsAppArticolo
{
    partial class FormOrdini
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
            dataGridViewOrdine = new DataGridView();
            ((System.ComponentModel.ISupportInitialize)dataGridViewOrdine).BeginInit();
            SuspendLayout();
            // 
            // dataGridViewOrdine
            // 
            dataGridViewOrdine.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewOrdine.Location = new Point(47, 55);
            dataGridViewOrdine.Name = "dataGridViewOrdine";
            dataGridViewOrdine.RowHeadersWidth = 51;
            dataGridViewOrdine.Size = new Size(533, 244);
            dataGridViewOrdine.TabIndex = 0;
            dataGridViewOrdine.CellContentClick += dataGridViewOrdine_CellContentClick;
            // 
            // FormArticolo
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1183, 491);
            Controls.Add(dataGridViewOrdine);
            Name = "FormArticolo";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)dataGridViewOrdine).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private DataGridView dataGridViewOrdine;
    }
}
