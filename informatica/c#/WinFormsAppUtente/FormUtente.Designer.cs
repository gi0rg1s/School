namespace WinFormsAppUtente
{
    partial class FormUtente
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
            buttonLogIn = new Button();
            dataGridViewAnagrafica = new DataGridView();
            buttonAnagrafica = new Button();
            ((System.ComponentModel.ISupportInitialize)dataGridView).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewAnagrafica).BeginInit();
            SuspendLayout();
            // 
            // dataGridView
            // 
            dataGridView.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridView.Location = new Point(44, 42);
            dataGridView.Name = "dataGridView";
            dataGridView.RowHeadersWidth = 51;
            dataGridView.Size = new Size(585, 195);
            dataGridView.TabIndex = 0;
            // 
            // buttonLogIn
            // 
            buttonLogIn.Location = new Point(680, 98);
            buttonLogIn.Name = "buttonLogIn";
            buttonLogIn.Size = new Size(281, 41);
            buttonLogIn.TabIndex = 1;
            buttonLogIn.Text = "Effettua Log In";
            buttonLogIn.UseVisualStyleBackColor = true;
            buttonLogIn.Click += buttonLogIn_Click;
            // 
            // dataGridViewAnagrafica
            // 
            dataGridViewAnagrafica.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewAnagrafica.Location = new Point(44, 281);
            dataGridViewAnagrafica.Name = "dataGridViewAnagrafica";
            dataGridViewAnagrafica.RowHeadersWidth = 51;
            dataGridViewAnagrafica.Size = new Size(585, 195);
            dataGridViewAnagrafica.TabIndex = 2;
            // 
            // buttonAnagrafica
            // 
            buttonAnagrafica.Location = new Point(680, 281);
            buttonAnagrafica.Name = "buttonAnagrafica";
            buttonAnagrafica.Size = new Size(281, 41);
            buttonAnagrafica.TabIndex = 3;
            buttonAnagrafica.Text = "Aggiungi anagrafica";
            buttonAnagrafica.UseVisualStyleBackColor = true;
            buttonAnagrafica.Click += buttonAnagrafica_Click;
            // 
            // FormUtente
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1112, 540);
            Controls.Add(buttonAnagrafica);
            Controls.Add(dataGridViewAnagrafica);
            Controls.Add(buttonLogIn);
            Controls.Add(dataGridView);
            Name = "FormUtente";
            Text = "FormUtente";
            ((System.ComponentModel.ISupportInitialize)dataGridView).EndInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewAnagrafica).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private DataGridView dataGridView;
        private Button buttonLogIn;
        private DataGridView dataGridViewAnagrafica;
        private Button buttonAnagrafica;
    }
}
