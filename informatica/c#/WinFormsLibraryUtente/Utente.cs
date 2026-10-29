using System.Data;

namespace WinFormsLibraryUtente
{
    public class Anagrafica
    {
        private int id;
        private String name;
        private String surname;

        public Anagrafica(int id, String name, String surname)
        {
            this.id = id;
            this.name = name;
            this.surname = surname;
        }

        public int Id { get => id; set => id = value; }
        public String Name { get => name; set => name = value; }
        public String Surname { get => surname; set => surname = value; }

        public List<Anagrafica> getSampleData()
        {
            return new List<Anagrafica>
            {
                new Anagrafica ( 1, "Tchen Aaron", "Kpante" ),
                new Anagrafica(2, "Manuel", "Gizzi"),
                new Anagrafica(3, "Diego", "Baccaro"),
                new Anagrafica(4, "Giorgia", "Folloni")
            };
        }
    }

    public class Utente
    {
        private int id;
        private String user;
        private String password;
        private String email;

        public Utente(int id, string user, string password, string email)
        {
            this.id = id;
            this.user = user;
            this.password = password;
            this.email = email;
        }

        public int Id { get => id; set => id = value; }
        public string User { get => user; set => user = value; }
        public string Password { get => password; set => password = value; }
        public string Email { get => email; set => email = value; }


        public List<Utente> getSampleData()
        {
            return new List<Utente>
                {
                    new Utente(1, "gi0rg1s", "I4mTh3bo$$", "giorgis@gmail.com"),
                    new Utente(2, "manu-alt", "Sor4PiPp0&Pap3r1NO", "sora@kingdomhearts.com"),
                    new Utente(3, "mira", "IwantMyfoodMAOMAOMAO", "mao@maomao.maaaaao")
                };
        }
    }


    public class Data
    {
        public DataTable getAnagrafica()
        {
            DataTable dt = new DataTable();
            dt.Columns.AddRange(new DataColumn[]
            {
                new DataColumn("ID", typeof(int)),
                new DataColumn("Name", typeof(String)),
                new DataColumn("surname", typeof(String))
            });

            Anagrafica a = new Anagrafica(0, "", "");
            List<Anagrafica> aa = a.getSampleData();

            foreach (Anagrafica anagrafica in aa) { dt.Rows.Add(anagrafica.Id, anagrafica.Name, anagrafica.Surname); }

            return dt;
        }

        public DataTable getUserCompleti()
        {

            DataTable dt = new DataTable();
            dt.Columns.AddRange(new DataColumn[]
            {
                new DataColumn("ID", typeof(int)),
                new DataColumn("User", typeof(String)),
                new DataColumn("Password", typeof(String)),
                new DataColumn("Email", typeof (String))
            });

            Utente utente = new Utente(0, "", "", "");
            List<Utente> utenti = utente.getSampleData();

            foreach (Utente u in utenti) { dt.Rows.Add(u.Id, u.User, u.Password, u.Email); }

            return dt;
        }
    }

}
