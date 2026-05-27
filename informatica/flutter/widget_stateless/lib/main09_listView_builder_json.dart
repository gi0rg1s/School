import 'dart:convert';
import 'package:flutter/material.dart';

/// Dimostra l'uso dinamico del Widget ListView istanziato con ListView.builder
/// e popolato con Widget Card e dati strutturati come List<Map<String, String>>
/// tra cui anche immagine caricata dal web

String filmJson = '''[
  {
    "Titolo": "Dunkirk",
    "Regista": "Nolan",
    "Anno": "2019",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/81781%2BYmMoL._SY445_.jpg"
  },
  {
    "Titolo": "L'età giovane",
    "Regista": "Dardenne",
    "Anno": "2020",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/71XC8DYfO6L._SY445_.jpg"
  },
  {
    "Titolo": "TeneT",
    "Regista": "Nolan",
    "Anno": "2020",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/71lg0x8oqTL._SY445_.jpg"
  },
  {
    "Titolo": "Il ponte delle spie",
    "Regista": "Spielberg",
    "Anno": "2016",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/71ylhqePG4L._SY445_.jpg"
  },
  {
    "Titolo": "Jojo Rabbit",
    "Regista": "Waititi",
    "Anno": "2020",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/81Uo8zyGdUL._SY445_.jpg"
  }
]''';

void main() {
  runApp(App());
}

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Lista Film',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: ListaFilm(),
    );
  }
}

class ListaFilm extends StatelessWidget {
  final List<Map<String, dynamic>> film;
  /* Converte da stringa JSON a List<Map<String, dynamic>> direttamente
  senza mappare (deserializzare) i valori in un oggetto
  */
  ListaFilm() : film = List<Map<String, dynamic>>.from(jsonDecode(filmJson));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Lista film Json"),
        ),
        body: Center(
            // Costruisce un widget ListView popolato dinamicamente con array
            child: ListView.builder(
                padding: const EdgeInsets.all(18),
                itemCount: film.length,
                // costruisce una voce di ListView passando automaticamente
                // l'indice della posizione della lista
                itemBuilder: (BuildContext context, int index) {
                  // a questo punto l'indice si usa per ogni elemento dell'array
                  return
                      // al grezzo
                      /*
                  Row(children: [
                    Text(film[index]['Titolo']),
                    Text(" " + film[index]['Regista']),
                    Text(" " + film[index]['Anno']),
                    Image.network(film[index]['Img'], width: 65)
                  ]);*/
                      Card(
                          elevation: 6,
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(20),
                              side: const BorderSide(
                                width: 0.8,
                                color: Colors.blueAccent,
                              )),
                          child: Row(children: [
                            Expanded(
                                child: ListTile(
                              title: Text(
                                film[index]['Titolo'] ?? "",
                                style: const TextStyle(fontSize: 15),
                              ),
                            )),
                            Container(
                              padding: const EdgeInsets.all(10.0),
                              alignment: Alignment.centerLeft,
                              child: Text(film[index]['Regista'] ?? ""),
                            ),
                            Container(
                                padding: const EdgeInsets.all(5.0),
                                alignment: Alignment.centerLeft,
                                child: Text(film[index]['Anno'] ?? "")),
                            Image.network(film[index]['Img'] ?? "", width: 65)
                          ]));
                })));
  }
}
