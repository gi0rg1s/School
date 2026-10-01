using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace prova
{
    public class Studente
    {
        int matricola;
        string nome;
        string cognome;

        public int Matricola { get => matricola; set => matricola = value; }
        public string Nome { get => nome; set => nome = value; }
        public string Cognome { get => cognome; set => cognome = value; }

        public Studente(int matricola, string nome, string cognome)
        {
            this.matricola = matricola;
            this.nome = nome;
            this.cognome = cognome;
        }
    }
}
