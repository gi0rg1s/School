namespace WinFormsAppUtente
{
    partial class FormAreaPersonale
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
            labelNome = new Label();
            dati = new Label();
            label1 = new Label();
            label2 = new Label();
            labelUser = new Label();
            labelEmail = new Label();
            buttonLogOut = new Button();
            SuspendLayout();
            // 
            // labelNome
            // 
            labelNome.AutoSize = true;
            labelNome.Location = new Point(52, 39);
            labelNome.Name = "labelNome";
            labelNome.Size = new Size(0, 20);
            labelNome.TabIndex = 0;
            // 
            // dati
            // 
            dati.AutoSize = true;
            dati.Location = new Point(52, 120);
            dati.Name = "dati";
            dati.Size = new Size(76, 20);
            dati.TabIndex = 1;
            dati.Text = "I tuoi dati:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(52, 165);
            label1.Name = "label1";
            label1.Size = new Size(45, 20);
            label1.TabIndex = 2;
            label1.Text = "User: ";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(52, 207);
            label2.Name = "label2";
            label2.Size = new Size(53, 20);
            label2.TabIndex = 3;
            label2.Text = "Email: ";
            // 
            // labelUser
            // 
            labelUser.AutoSize = true;
            labelUser.Location = new Point(159, 165);
            labelUser.Name = "labelUser";
            labelUser.Size = new Size(0, 20);
            labelUser.TabIndex = 4;
            // 
            // labelEmail
            // 
            labelEmail.AutoSize = true;
            labelEmail.Location = new Point(159, 207);
            labelEmail.Name = "labelEmail";
            labelEmail.Size = new Size(0, 20);
            labelEmail.TabIndex = 5;
            // 
            // buttonLogOut
            // 
            buttonLogOut.Location = new Point(52, 312);
            buttonLogOut.Name = "buttonLogOut";
            buttonLogOut.Size = new Size(258, 46);
            buttonLogOut.TabIndex = 6;
            buttonLogOut.Text = "Effettua il LogOut";
            buttonLogOut.UseVisualStyleBackColor = true;
            buttonLogOut.Click += buttonLogOut_Click;
            // 
            // FormAreaPersonale
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(392, 448);
            Controls.Add(buttonLogOut);
            Controls.Add(labelEmail);
            Controls.Add(labelUser);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(dati);
            Controls.Add(labelNome);
            Name = "FormAreaPersonale";
            Text = "FormAreaPersonale";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label labelNome;
        private Label dati;
        private Label label1;
        private Label label2;
        private Label labelUser;
        private Label labelEmail;
        private Button buttonLogOut;
    }
}