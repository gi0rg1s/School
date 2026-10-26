namespace WinFormsLibraryAlbum
{
    public class Album
    {
        private int id;
        private string title;
        private string author;
        private int year;
        private string category;
        private int durationMinutes;

        

        public int Id { get => id; set => id = value; }
        public string Title { get => title; set => title = value; }
        public string Author { get => author; set => author = value; }
        public int Year { get => year; set => year = value; }
        public string Category { get => category; set => category = value; }
        public int DurationMinutes { get => durationMinutes; set => durationMinutes = value; }

        public Album(int id, string title, string author, int year, string category, int durationMinutes)
        {
            this.id = id;
            this.title = title;
            this.author = author;
            this.year = year;
            this.category = category;
            this.durationMinutes = durationMinutes;
        }

        public List<Album> getSampleData()
        {
            return new List<Album>
            {
                new Album(1, "You seem pretty sad for a girl so in love", "Olivia Rodrigo", 2026, "pop", 42),
                new Album(2, "GUTS", "Olivia Rodrigo", 2023, "Pop", 48),
                new Album(3, "SOUR", "Olivia Rodrigo", 2022, "Pop", 45),
                new Album(4, "Hello world", "Pinguini Tattici Nucleari", 2024, "Pop", 62),
                new Album(5, "Fake news", "Pinguini Tattici Nucleari", 2023, "Pop", 55)
            };
        }
    }
}
