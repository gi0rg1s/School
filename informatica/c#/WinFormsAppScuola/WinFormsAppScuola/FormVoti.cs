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
        List<Object> v;
        public FormVoti(List<Object> voti)
        {
            InitializeComponent();
            v = voti;
            dataGridViewVoti.DataSource = v;
        }
    }
}
