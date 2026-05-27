import 'package:flutter/material.dart';
import 'main09_paginaDiRisposta.dart';
import 'main09_Film.dart';

/// Dimostra l'uso di Navigator e MaterialPageRoute
/// Navigator gestisce la pila della navigazione della pagine dell'app, permette
/// di instradare una pagina di widget su un'altra o tornare alla precedente
/// MaterialPageRoute crea una nuova pagina a cui accedere con Navigator
///
/// L'utilizzo di questo modello consente al navigatore di passare visivamente
/// da una pagina all'altra. Allo stesso modo, il navigatore può essere utilizzato
/// per mostrare una finestra di dialogo posizionando il widget della finestra
/// di dialogo sopra la pagina corrente.
/// In Flutter le pagine sono chiamate route (percorsi) e sono gestiti da un widget
/// [Navigator]. Il navigatore gestisce uno stack di oggetti [Route] e fornisce
/// due modi per gestire lo stack, l'API dichiarativa [Navigator.pages] o l'API
/// imperativa [Navigator.push] e [Navigator.pop].
/// La proprietà [Scaffold.appBar]) aggiunge automaticamente un pulsante Indietro
/// per la navigazione dell'utente.
///
/// Nell'esempio si passa alla pagina di WidgetRisposta(film) passando il film selezionato

final List<Map<String, String>> film = [
  {
    "Titolo": "Dunkirk",
    "Regista": "Nolan",
    "Anno": "2019",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/81781%2BYmMoL._SY445_.jpg",
  },
  {
    "Titolo": "L'età giovane",
    "Regista": "Dardenne",
    "Anno": "2020",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/71XC8DYfO6L._SY445_.jpg",
  },
  {
    "Titolo": "TeneT",
    "Regista": "Nolan",
    "Anno": "2020",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/71lg0x8oqTL._SY445_.jpg",
  },
  {
    "Titolo": "Il ponte delle spie",
    "Regista": "Spielberg",
    "Anno": "2016",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/71ylhqePG4L._SY445_.jpg",
  },
  {
    "Titolo": "Jojo Rabbit",
    "Regista": "Waititi",
    "Anno": "2020",
    "Img":
        "https://images-na.ssl-images-amazon.com/images/I/81Uo8zyGdUL._SY445_.jpg",
  },
];

void main() {
  runApp(PaginaRichiesta());
}

class PaginaRichiesta extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Pagina Richiesta',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomeListaFilm(title: 'Clicca su un film per locandina'),
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
        child: ListView.builder(
          padding: const EdgeInsets.all(18),
          itemCount: film.length,
          itemBuilder: (BuildContext context, int index) {
            return Card(
              elevation: 6,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20),
                side: const BorderSide(width: 0.8, color: Colors.blueAccent),
              ),
              child: GestureDetector(
                /// Questo GestureDetector viene istanziato per ogni
                /// riga di listView
                onTap: () {
                  /// Gestore della pila delle pagine visualizzate
                  /// push inserisce una pagina nuova
                  Navigator.push(
                    context,

                    /// pagina nuova
                    MaterialPageRoute(
                      /// all'attributo builder di MaterialPageRoute
                      /// si passa l'istanza di un widget
                      /// WidgetRisposta è classe inclusa in file dart
                      /// importato
                      builder:
                          (context) =>
                              WidgetRisposta(Film.fromJson(film[index])),
                    ),
                  );
                },
                child: Row(
                  children: [
                    Expanded(
                      child: ListTile(
                        title: Text(
                          film[index]['Titolo'] ?? "",
                          style: const TextStyle(fontSize: 15),
                        ),
                      ),
                    ),
                    Container(
                      padding: const EdgeInsets.all(10.0),
                      alignment: Alignment.centerLeft,
                      child: Text(film[index]['Regista'] ?? ""),
                    ),
                    Container(
                      padding: const EdgeInsets.all(5.0),
                      alignment: Alignment.centerLeft,
                      child: Text(film[index]['Anno'] ?? ""),
                    ),
                  ],
                ),
              ),
            );
          },
        ),
      ),
    );
  }
}
