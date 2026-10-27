namespace WinFormsAlbum
{
    partial class FormAlbum
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
            dataGridView = new DataGridView();
            comboBox = new ComboBox();
            buttonFiltra = new Button();
            buttonDetails = new Button();
            labelOlder = new Label();
            labelAvrg = new Label();
            ((System.ComponentModel.ISupportInitialize)dataGridView).BeginInit();
            SuspendLayout();
            // 
            // dataGridView
            // 
            dataGridView.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridView.Location = new Point(37, 31);
            dataGridView.Name = "dataGridView";
            dataGridView.RowHeadersWidth = 51;
            dataGridView.Size = new Size(637, 154);
            dataGridView.TabIndex = 0;
            // 
            // comboBox
            // 
            comboBox.FormattingEnabled = true;
            comboBox.Location = new Point(730, 137);
            comboBox.Name = "comboBox";
            comboBox.Size = new Size(196, 28);
            comboBox.TabIndex = 1;
            // 
            // buttonFiltra
            // 
            buttonFiltra.Location = new Point(730, 182);
            buttonFiltra.Name = "buttonFiltra";
            buttonFiltra.Size = new Size(196, 31);
            buttonFiltra.TabIndex = 2;
            buttonFiltra.Text = "Filtra";
            buttonFiltra.UseVisualStyleBackColor = true;
            buttonFiltra.Click += buttonFiltra_Click;
            // 
            // buttonDetails
            // 
            buttonDetails.Location = new Point(37, 229);
            buttonDetails.Name = "buttonDetails";
            buttonDetails.Size = new Size(196, 31);
            buttonDetails.TabIndex = 4;
            buttonDetails.Text = "Visualiizza dettagli";
            buttonDetails.UseVisualStyleBackColor = true;
            buttonDetails.Click += buttonDetails_Click;
            // 
            // labelOlder
            // 
            labelOlder.AutoSize = true;
            labelOlder.Location = new Point(37, 284);
            labelOlder.Name = "labelOlder";
            labelOlder.Size = new Size(0, 20);
            labelOlder.TabIndex = 5;
            // 
            // labelAvrg
            // 
            labelAvrg.AutoSize = true;
            labelAvrg.Location = new Point(37, 330);
            labelAvrg.Name = "labelAvrg";
            labelAvrg.Size = new Size(0, 20);
            labelAvrg.TabIndex = 6;
            // 
            // FormAlbum
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(979, 480);
            Controls.Add(labelAvrg);
            Controls.Add(labelOlder);
            Controls.Add(buttonDetails);
            Controls.Add(buttonFiltra);
            Controls.Add(comboBox);
            Controls.Add(dataGridView);
            Name = "FormAlbum";
            Text = "FormAlbum";
            ((System.ComponentModel.ISupportInitialize)dataGridView).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView dataGridView;
        private ComboBox comboBox;
        private Button buttonFiltra;
        private Button buttonDetails;
        private Label labelOlder;
        private Label labelAvrg;
    }
}
