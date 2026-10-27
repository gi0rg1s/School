using System.Data;
using Album = WinFormsLibraryAlbum.Album;

namespace WinFormsAlbum
{
    public partial class FormAlbum : Form
    {
        private DataTable categoryAlbum;
        public FormAlbum()
        {
            InitializeComponent();
            categoryAlbum = new DataTable();
            categoryAlbum.Columns.AddRange(new DataColumn[] {
                new DataColumn("Id", typeof(int)),
                new DataColumn("Title", typeof(string)),
                new DataColumn("Author", typeof(string)),
                new DataColumn("Year", typeof(int)),
                new DataColumn("Category", typeof(string)),
                new DataColumn("DurationMinutes", typeof(int)) });

            Album a = new Album(0, "", "", 0, "", 0);
            List<Album> datas = a.getSampleData();

            foreach (Album album in datas)
            {
                categoryAlbum.Rows.Add(album.Id, album.Title, album.Author, album.Year, album.Category, album.DurationMinutes);
            }
            dataGridView.DataSource = categoryAlbum;


            // Populate th ecombobox
            comboBox.Items.Add("Pop");
            comboBox.Items.Add("Rock");
            comboBox.Items.Add("Jazz");
            comboBox.Items.Add("classical");

        }

        private void buttonFiltra_Click(object sender, EventArgs e)
        {
            if(comboBox.SelectedIndex == -1)
            {
                dataGridView.DataSource = categoryAlbum;
                return;
            }
            string selectedGenre = comboBox.Items[comboBox.SelectedIndex].ToString();
            if (selectedGenre != null)
            {
                DataTable filteredTable = new DataTable();
                filteredTable.Columns.AddRange(new DataColumn[] {
                    new DataColumn("Id", typeof(int)),
                    new DataColumn("Title", typeof(string)),
                    new DataColumn("Author", typeof(string)),
                    new DataColumn("Year", typeof(int)),
                    new DataColumn("Category", typeof(string)),
                    new DataColumn("DurationMinutes", typeof(int)) });

                DataRow[] matchingRows = categoryAlbum.Select($"Category = '{selectedGenre}'");
                foreach (DataRow r in matchingRows)
                {
                    filteredTable.ImportRow(r);
                }
                dataGridView.DataSource = filteredTable;
            }
        }

        private void buttonDetails_Click(object sender, EventArgs e)
        {
            Album a = new Album(0, "", "", 0, "", 0);
            DataTable currentTable = dataGridView.DataSource as DataTable;
            if (currentTable == null) return;

            List<Album> albums = currentTable.AsEnumerable().Select(r => new Album(
                id: r.Field<int>("Id"),
                title: r.Field<string>("Title"),
                author: r.Field<string>("Author"),
                year: r.Field<int>("Year"),
                category: r.Field<string>("Category"),
                durationMinutes: r.Field<int>("DurationMinutes")
            )).ToList();

            labelOlder.Text = $"Older Album: {a.getOlderAlbum(albums).Title}";
            labelAvrg.Text = $"Average Duration: {a.getAverageDuration(albums)}";
        }
    }
}
