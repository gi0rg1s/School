import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:http/io_client.dart';

class Quadro {
  final String QQ_TitoloQuadro;
  final String QQ_AnnoEsecuzione;
  final String QQ_Tecnica;
  final String QQ_Url;

  Quadro({
    required this.QQ_TitoloQuadro,
    required this.QQ_AnnoEsecuzione,
    required this.QQ_Tecnica,
    required this.QQ_Url,
  });

  factory Quadro.fromJson(Map<String, dynamic> json) {
    return Quadro(
      QQ_TitoloQuadro: json['QQ_TitoloQuadro'],
      QQ_AnnoEsecuzione: json['QQ_AnnoEsecuzione'],
      QQ_Tecnica: json['QQ_Tecnica'],
      QQ_Url: json['QQ_Url'],
    );
  }
}

class ListaQuadri {
  final List<Quadro> listaQuadri;

  ListaQuadri({required this.listaQuadri});

  /// Acquisisce una List<dynamic> e la trasforma/modella in List<Film>
  factory ListaQuadri.fromJson(List<dynamic> listaJson) {
    List<Quadro> l = [];
    l = listaJson.map((i) => Quadro.fromJson(i)).toList();
    return ListaQuadri(listaQuadri: l);
  }
}

Future<List<Quadro>> fetchListaQuadri(String codice) async {
  final httpClient = HttpClient();
  httpClient.badCertificateCallback =
      (X509Certificate cert, String host, int port) => true;
  final http = IOClient(httpClient);
  final response = await http.post(
    Uri.parse('https://riservata.itis.pr.it/dbarte/controller.php'),
    headers: {'Content-type': 'application/x-www-form-urlencoded'},
    body: {'funzione': 'getOpereArtista', 'artista': codice},
  );
  if (response.statusCode == 200) {
    // Da response arriva l'array json
    final j = json.decode(response.body);
    if (j == null) throw Exception('Errore!');
    return ListaQuadri.fromJson(j).listaQuadri;
  } else {
    throw Exception('Errore connessione!');
  }
}

class HomeListaQuadri extends StatefulWidget {
  const HomeListaQuadri({
    required this.codiceArtista,
    required this.nomeArtista,
  });
  final String codiceArtista;
  final String nomeArtista;
  @override
  _StatoListaQuadri createState() => _StatoListaQuadri();
}

class _StatoListaQuadri extends State<HomeListaQuadri> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("quadri di ${widget.nomeArtista}")),
      body: Center(
        // Costruisce un widget ListView popolato dinamicamente con array
        child: FutureBuilder<List<Quadro>>(
          // reference metodo Future
          future: fetchListaQuadri(widget.codiceArtista),
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
                      ),
                    ),
                    child: Row(
                      children: [
                        Expanded(
                          child: ListTile(
                            title: Text(
                              snapshot.data![index].QQ_TitoloQuadro,
                              style: const TextStyle(fontSize: 15),
                            ),
                          ),
                        ),
                        Container(
                          padding: const EdgeInsets.all(10.0),
                          alignment: Alignment.centerLeft,
                          child: Text(snapshot.data![index].QQ_Tecnica),
                        ),
                        Container(
                          padding: const EdgeInsets.all(5.0),
                          alignment: Alignment.centerLeft,
                          child: Text(snapshot.data![index].QQ_AnnoEsecuzione),
                        ),
                        Image.network(snapshot.data![index].QQ_Url, width: 65),
                      ],
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
