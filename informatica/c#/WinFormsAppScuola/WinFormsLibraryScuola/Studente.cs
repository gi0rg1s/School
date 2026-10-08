using System.ComponentModel;

namespace WinFormsLibraryScuola
{
    public class Studente
    {
        int matricola;
        string nome;
        string cognome;
        DateTime dataDiNascita;
        List<int> voti;

        public Studente()
        {
        }

        //constructor
        public Studente(int matricola, string nome, string cognome, DateTime dataDiNascita, List<int> voti)
        {
            this.Matricola = matricola;
            this.Nome = nome;
            this.Cognome = cognome;
            this.DataDiNascita = dataDiNascita;
            this.Voti = voti;
        }

        //getters and setters
        public int Matricola { get => matricola; set => matricola = value; }
        public string Nome { get => nome; set => nome = value; }
        public string Cognome { get => cognome; set => cognome = value; }
        public DateTime DataDiNascita { get => dataDiNascita; set => dataDiNascita = value; }
        public List<int> Voti { get => voti; set => voti = value; }

        public static BindingList<Studente> GetStudenti(){
            return new BindingList<Studente>
            {
                new Studente(12, "pippo", "pippo", DateTime.Parse("2008-04-08"), new List<int>{6, 8, 2, 10}),
                new Studente{matricola=11, cognome="topolino", nome="topolino", dataDiNascita=DateTime.Parse("2002-01-30"), voti= new List<int>{6, 9, 7}},
                new Studente{matricola=11, cognome="pluto", nome="pluto", dataDiNascita=DateTime.Parse("2002-12-20"), voti= new List<int>{8, 9, 7}}
            };
        }
    }
}
