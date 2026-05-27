import 'dart:convert';

String books = '''{
    "Titolo": "La neve in fondo al mare",
    "Autore": "Matteo Bussola",
    "Anno": "2024",
    "ISBN": "9788806260651"
  },
  {
    "Titolo": "Qualcosa di lilla",
    "Autore": "Maruska Albertazzi",
    "Anno": "2026",
    "ISBN": "9788828219729"
  },
  {
    "Titolo": "Delitto e castigo",
    "Autore": "Fëdor Dostoevskij",
    "Anno": "1866",
    "ISBN": "9780192833839"
  },
  {
    "Titolo": "Lettera al padre",
    "Autore": "Franz Kafka",
    "Anno": "1919",
    "ISBN": "9788807900310"
  }
''';

class Libro {
  final String titolo;
  final String autore;
  final String anno;
  final String isbn;

  Libro(this.titolo, this.autore, this.anno, this.isbn);
  Libro.fromJson(Map<String, dynamic> json)
    : titolo = json['Titolo'],
      autore = json['Autore'],
      anno = json["Anno"],
      isbn = json["ISBN"];
}

class ListaLibri {
  final List<Libro> listaLibri;
  ListaLibri({required this.listaLibri});

  factory ListaLibri.fromJson(List<dynamic> parsedJson) {
    List<Libro> listaLibri = parsedJson
        .map((libro) => Libro.fromJson(libro))
        .toList();
    return ListaLibri(listaLibri: listaLibri);
  }
}

void main(List<String> args) {
  print("Trasforma in lista di classe Libro da stringa json");
  List<dynamic> booksMap = jsonDecode('[$books]');
  List<Libro> libri = ListaLibri.fromJson(booksMap).listaLibri;

  for (final libro in libri) {
    print('${libro.titolo}, ${libro.autore}, ${libro.anno}, ${libro.isbn}');
  }
}
