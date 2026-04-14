import 'dart:convert';
import 'package:flutter/material.dart';

/// Dimostra ancora l'uso del widget FutureBuilder.
///
/// Nell'esempio la pagina si presenza con un bottone e un CircularProgressionIndicator.
/// Al click sul bottone il FutureBuilder restituisce il Widget ListView istanziato con
/// ListView.builder quando il metodo Future fetchListaFilm() sarà completato

const String rispostaFilms = '''[
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

class Film {
  final String titolo;
  final String regista;
  final String anno;
  final String img;

  Film(
      {required this.titolo,
      required this.regista,
      required this.anno,
      required this.img});

  factory Film.fromJson(Map<String, dynamic> json) {
    return Film(
        titolo: json['Titolo'],
        regista: json['Regista'],
        anno: json['Anno'],
        img: json["Img"]);
  }
}

class ListaFilm {
  final List<Film> listaFilm;

  ListaFilm({required this.listaFilm});

  /// Acquisisce una List<dynamic> e la trasforma/modella in List<Film>
  factory ListaFilm.fromJson(List<dynamic> listaJson) {
    List<Film> l = [];
    l = listaJson.map((i) => Film.fromJson(i)).toList();
    return ListaFilm(listaFilm: l);
  }
}

Future<List<Film>> fetchListaFilm(String utente, String password) async {
  // Lascio volutamente il codice commentato per dimostrare come sarebbe una
  // chiamata http ad un webservice che restituisce un json
  // RICORDA DI fare import 'package:http/io_client.dart';
  
  /*  final httpClient = HttpClient();
  httpClient.badCertificateCallback =
      (X509Certificate cert, String host, int port) => true;
  final http = IOClient(httpClient);
  final response = await http.post(
      Uri.parse('https://films.com/lista/listafilm'),
      headers: {
        'Content-type': 'application/x-www-form-urlencoded'
      },
      body: {
        'richiesta': 'listafilm',
        'utente': utente,
        'password': password
      });
  if (response.statusCode == 200) {
    // Da response arriva l'array json
    final j = json.decode(response.body);
    if (j == null) throw Exception('Errore!');
    return ListaFilm.fromJson(j).listaFilm;
  } else {
    throw Exception('Errore connessione!');
  }*/
  
  // Simula l'acquisizione di un array JSON (rispostaFilms) tipicamente da un webService
  return Future.delayed(const Duration(seconds: 8), () {
    // Decodifica un array JSon in lista
    final lista = json.decode(rispostaFilms);
    if (lista != null) {
      // ritorna la List<dynamic> trasformata/modellata in List<Film>
      return ListaFilm.fromJson(lista).listaFilm;
    } else {
      throw Exception('Errore');
    }
  });
}

void main() {
  runApp(WidgetListaFilm());
}

class WidgetListaFilm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Lista Film',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomeListaFilm(title: 'Lista Film'),
    );
  }
}

class HomeListaFilm extends StatefulWidget {
  const HomeListaFilm({Key? key, required this.title}) : super(key: key);
  final String title;
  @override
  _StatoListaFilm createState() => _StatoListaFilm();
}

class _StatoListaFilm extends State<HomeListaFilm> {
  Future<List<Film>>? _futuraListaFilm;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Center(
            // Costruisce un widget ListView popolato dinamicamente con array
            child: FutureBuilder<List<Film>>(
                // reference metodo Future
                future: _futuraListaFilm,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return ListView.builder(
                        padding: const EdgeInsets.all(18),
                        // snapshot.data
                        itemCount: snapshot.data!.length,
                        // costruisce una voce di ListView passando automaticamente
                        // l'indice della posizione della lista
                        itemBuilder: (BuildContext context, int index) {
                          // a questo punto l'indice si usa per ogni elemento dell'array
                          return Card(
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
                                    snapshot.data![index].titolo,
                                    style: const TextStyle(fontSize: 15),
                                  ),
                                )),
                                Container(
                                  padding: const EdgeInsets.all(10.0),
                                  alignment: Alignment.centerLeft,
                                  child: Text(snapshot.data![index].regista),
                                ),
                                Container(
                                    padding: const EdgeInsets.all(5.0),
                                    alignment: Alignment.centerLeft,
                                    child: Text(snapshot.data![index].anno)),
                                Image.network(snapshot.data![index].img,
                                    width: 65)
                              ]));
                        });
                  }
                  return Column(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        ElevatedButton(
                            style: ElevatedButton.styleFrom(
                              foregroundColor: Colors.teal,
                              backgroundColor: Colors.white,
                              shape: const BeveledRectangleBorder(
                                  borderRadius:
                                      BorderRadius.all(Radius.circular(5))),
                            ),
                            child: const Text("Piglia i film"),
                            onPressed: () {
                              // Viene valorizzata la proprietà future di questo
                              // widget FutureBuilder
                              // la funzione setState() provocherà la ricostruzione
                              // del widget FutureBuilder invocando la funzione
                              // asincrona Future e di conseguenza ricostruirà
                              // il widget FutureBuilder
                              _futuraListaFilm =
                                  fetchListaFilm("pippo", "pluto");
                              setState(() {});
                            }),
                        const CircularProgressIndicator()
                      ]);
                })));
  }
}
