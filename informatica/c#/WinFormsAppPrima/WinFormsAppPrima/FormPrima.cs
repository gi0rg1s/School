namespace WinFormsAppPrima
{
    public partial class FormPrima : Form
    {
        private int counter;
        public FormPrima()
        {
            InitializeComponent();
        }

        private void buttonCliccami_Click(object sender, EventArgs e)
        {
            //i wanna go into the puzzoooooo
            //cant handle this 1min more
            MessageBox.Show($"hai cliccato il bottone {this.counter} volte");
        }

        private void FormPrima_Load(object sender, EventArgs e)
        {
            this.counter++;
        }
    }
}
