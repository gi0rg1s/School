import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:http/io_client.dart';
import 'main12_webservice2.dart';

class Artista {
  final String AR_CodiceArtista;
  final String AR_Nome;
  final String AR_Alias;
  final String AR_DataNascita;

  Artista({
    required this.AR_CodiceArtista,
    required this.AR_Nome,
    required this.AR_Alias,
    required this.AR_DataNascita,
  });

  factory Artista.fromJson(Map<String, dynamic> json) {
    return Artista(
      AR_CodiceArtista: json['AR_CodiceArtista'],
      AR_Nome: json['AR_Nome'],
      AR_Alias: json['AR_Alias'],
      AR_DataNascita: json['AR_DataNascita'],
    );
  }
}

class ListaArtisti {
  final List<Artista> listaArtisti;

  ListaArtisti({required this.listaArtisti});

  /// Acquisisce una List<dynamic> e la trasforma/modella in List<Film>
  factory ListaArtisti.fromJson(List<dynamic> listaJson) {
    List<Artista> l = [];
    l = listaJson.map((i) => Artista.fromJson(i)).toList();
    return ListaArtisti(listaArtisti: l);
  }
}

Future<List<Artista>> fetchListaArtisti() async {
  // Lascio volutamente il codice commentato per dimostrare come sarebbe una
  // chiamata http ad un webservice che restituisce un json
  // RICORDA DI fare import 'package:http/io_client.dart';

  final httpClient = HttpClient();
  httpClient.badCertificateCallback =
      (X509Certificate cert, String host, int port) => true;
  final http = IOClient(httpClient);
  final response = await http.post(
    Uri.parse('https://riservata.itis.pr.it/dbarte/controller.php'),
    headers: {'Content-type': 'application/x-www-form-urlencoded'},
    body: {'funzione': 'getArtisti'},
  );
  if (response.statusCode == 200) {
    // Da response arriva l'array json
    final j = json.decode(response.body);
    if (j == null) throw Exception('Errore!');
    return ListaArtisti.fromJson(j).listaArtisti;
  } else {
    throw Exception('Errore connessione!');
  }
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
      home: const HomeListaFilm(title: 'Lista Artisti'),
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
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        // Costruisce un widget ListView popolato dinamicamente con array
        child: FutureBuilder<List<Artista>>(
          // reference metodo Future
          future: fetchListaArtisti(),
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
                  return GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder:
                              (context) => HomeListaQuadri(
                                codiceArtista:
                                    snapshot.data![index].AR_CodiceArtista,
                                nomeArtista: snapshot.data![index].AR_Nome,
                              ),
                        ),
                      );
                    },
                    child: Card(
                      elevation: 6,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20),
                        side: const BorderSide(
                          width: 0.8,
                          color: Colors.blueAccent,
                        ),
                      ),
                      child: Row(
                        children: [
                          Expanded(
                            child: ListTile(
                              title: Text(
                                snapshot.data![index].AR_CodiceArtista,
                                style: const TextStyle(fontSize: 15),
                              ),
                            ),
                          ),
                          Container(
                            padding: const EdgeInsets.all(10.0),
                            alignment: Alignment.centerLeft,
                            child: Text(snapshot.data![index].AR_Nome),
                          ),
                          Container(
                            padding: const EdgeInsets.all(5.0),
                            alignment: Alignment.centerLeft,
                            child: Text(snapshot.data![index].AR_Alias),
                          ),
                          Container(
                            padding: const EdgeInsets.all(5.0),
                            alignment: Alignment.centerLeft,
                            child: Text(snapshot.data![index].AR_DataNascita),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              );
            }
            return Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [const CircularProgressIndicator()],
            );
          },
        ),
      ),
    );
  }
}
