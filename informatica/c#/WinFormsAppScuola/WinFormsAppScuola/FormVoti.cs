using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using WinFormsLibraryScuola;

namespace WinFormsAppScuola
{
    public partial class FormVoti : Form
    {
        List<Object> voti;
        public FormVoti(List<Object> voti)
        {
            InitializeComponent();
            this.voti = voti;
            dataGridViewVoti.DataSource = voti;
        }
    }
}
