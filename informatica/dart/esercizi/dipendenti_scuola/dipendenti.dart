abstract class Persona {
  String _nominativo;
  String _sesso;
  DateTime _dataDiNascita;
  double _stipendioBase;

  Persona([this._nominativo = "", this._sesso = "", this._stipendioBase = 0.0,DateTime? dataDiNascita])
    : _dataDiNascita = dataDiNascita ?? DateTime.now();

  String get nominativo => _nominativo;
  String get sesso => _sesso;
  DateTime get dataDiNascita => _dataDiNascita;
  double get stipendioBase => _stipendioBase;

  double getStipendio();

  @override
  String toString() {
    return "Persona(nome: $nominativo, sesso: $sesso, dataDiNascita: ${dataDiNascita.toIso8601String()}, stipendioBase: ${stipendioBase.toStringAsFixed(2)})";
  }
}

class Impiegato extends Persona {
  int _livello;
  int _oreStraordinari;
  double _pagaPerStraordinari;

  Impiegato(this._livello,  this._oreStraordinari, this._pagaPerStraordinari, {String nominativo = "", String sesso = "", double stipendioBase = 0.0, DateTime? dataDiNascita})
    : super(nominativo, sesso, stipendioBase, dataDiNascita);

  int get livello => _livello;
  double get pagaPerStraordinari => _pagaPerStraordinari;
  int get oreStraordinari => _oreStraordinari;

  @override
  double getStipendio() {
    return stipendioBase + (oreStraordinari * pagaPerStraordinari);
  }

  @override
  String toString() {
    return "Impiegato(${super.toString()}, livello: $livello, oreStraordinari: $oreStraordinari, pagaPerStraordinari: ${pagaPerStraordinari.toStringAsFixed(2)}, stipendioTotale: ${getStipendio().toStringAsFixed(2)})";
  }
}

class Docente extends Persona {
  int _numeroOre;

  Docente(this._numeroOre,  {String nominativo = "", String sesso = "", double stipendioBase = 0.0, DateTime? dataDiNascita})
    : super(nominativo, sesso, stipendioBase, dataDiNascita);

  int get numeroOre => _numeroOre;

  @override
  double getStipendio() {
    return numeroOre * stipendioBase;
  }

  @override
  String toString() {
    return "Docente(${super.toString()}, numeroOre: $numeroOre, stipendioTotale: ${getStipendio().toStringAsFixed(2)})";
  }
}

void main() {
  final dipendenti = <Persona>[
    Impiegato(3, 12, 15.0, nominativo: "Mario Rossi", sesso: "M", stipendioBase: 1400.0, dataDiNascita: DateTime(1990, 5, 12),),
    Docente(80, nominativo: "Giulia Bianchi", sesso: "F", stipendioBase: 22.5, dataDiNascita: DateTime(1985, 11, 3),),];

  for (final dipendente in dipendenti) {
    print(dipendente);
    print("Stipendio calcolato: ${dipendente.getStipendio().toStringAsFixed(2)} euro\n");
  }
}