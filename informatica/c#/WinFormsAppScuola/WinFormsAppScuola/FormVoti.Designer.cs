namespace WinFormsAppScuola
{
    partial class FormVoti
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
            dataGridViewVoti = new DataGridView();
            ((System.ComponentModel.ISupportInitialize)dataGridViewVoti).BeginInit();
            SuspendLayout();
            // 
            // dataGridViewVoti
            // 
            dataGridViewVoti.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewVoti.Location = new Point(166, 46);
            dataGridViewVoti.Name = "dataGridViewVoti";
            dataGridViewVoti.RowHeadersWidth = 51;
            dataGridViewVoti.Size = new Size(523, 193);
            dataGridViewVoti.TabIndex = 0;
            // 
            // FormVoti
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(dataGridViewVoti);
            Name = "FormVoti";
            Text = "FormVoti";
            ((System.ComponentModel.ISupportInitialize)dataGridViewVoti).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private DataGridView dataGridViewVoti;
    }
}