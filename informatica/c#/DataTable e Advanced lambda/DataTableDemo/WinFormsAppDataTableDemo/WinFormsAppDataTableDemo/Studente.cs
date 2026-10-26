using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataGridDemo
{
    public class Studente
    {
        string nome;
        string cognome;
        int matricola;
        string classe;

        public Studente(string nome, string cognome, int matricola, string classe)
        {
            this.Nome=nome;
            this.Cognome=cognome;
            this.Matricola=matricola;
            this.Classe=classe;
        }

        public string Nome { get => nome; set => nome=value; }
        public string Cognome { get => cognome; set => cognome=value; }
        public int Matricola { get => matricola; set => matricola=value; }
        public string Classe { get => classe; set => classe=value; }

        public static List <Studente> getDati()
        {
            return new List<Studente> { 
                new Studente("pippo", "pippo", 123, "5c2"), 
                new Studente("pluto", "pluto", 234, "5c2"), 
                new Studente("paperino", "paperino", 345, "5a2"),
                new Studente("eta", "beta", 333, "5a2")
            };
        }
    }
}
