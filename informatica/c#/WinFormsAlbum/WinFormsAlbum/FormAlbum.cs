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
        }
    }
}
