import 'package:flutter/material.dart';

/// Dimostrazione Layout Grid con GridView e costruttore GridView.count
/// il cui attributo children vuole la lista dei widget che popolano le celle
/// della griglia
void main() {
  runApp(Grigliata());
}

class Grigliata extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    const title = 'Esempio Grid - GridView.count';

    return MaterialApp(
      title: title,
      home: Scaffold(
        appBar: AppBar(title: const Text(title)),
        /*
        Il costruttore GridView.count dichiara con crossAxisCount (che per default
        è l'asse orizzontale) quante colonne ci sono nella griglia. Con l'attributo
        children si possono inserire i widget lungo le righe
        */
        body: GridView.count(
          padding: const EdgeInsets.all(20),
          crossAxisSpacing: 10,
          mainAxisSpacing: 10,
          crossAxisCount: 2,
          children: <Widget>[
            Contenitore(colore:400, testo:'Questa è la prima cella'),
            Contenitore(colore:500, testo:'la seconda'),
            Contenitore(colore:600, testo:'qui a fianco immagine caricata dal web'),

             Container(
              padding: const EdgeInsets.all(8),
              color: Colors.teal[400],
              child: Image.network(
                'https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/Google-flutter-logo.png/800px-Google-flutter-logo.png',
                fit: BoxFit.contain,
              ),
            ),
            Container(
              color: Colors.teal[500],
              child: const Center(
                child: Text(
                  'qui a fianco la gatta...',
                  style: TextStyle(color: Colors.white),
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.all(8),
              color: Colors.teal[600],
              /* Valori di BoxFit
              BoxFit.fill: L'immagine viene deformata per riempire tutto lo spazio disponibile, ignorando il rapporto d’aspetto.
              BoxFit.contain: L'immagine viene ridimensionata per adattarsi interamente all’interno dello spazio disponibile, mantenendo il suo rapporto d’aspetto. Potrebbe lasciare spazi vuoti.
              BoxFit.cover: L'immagine riempie l’intero spazio disponibile mantenendo il rapporto d’aspetto. Potrebbe essere ritagliata.
              BoxFit.fitWidth: L’immagine si adatta alla larghezza del contenitore mantenendo il rapporto d’aspetto, ma può uscire dai bordi verticalmente.
              BoxFit.fitHeight: L’immagine si adatta all’altezza del contenitore mantenendo il rapporto d’aspetto, ma può uscire dai bordi orizzontalmente.
              BoxFit.none: L’immagine mantiene le sue dimensioni originali e non viene ridimensionata. Potrebbe essere ritagliata se troppo grande.
              BoxFit.scaleDown: Simile a contain, ma l’immagine viene ridotta solo se è più grande del contenitore; altrimenti, mantiene le sue dimensioni originali.
              */
              child: Image.asset('assets/img/gatta.jpeg', fit: BoxFit.contain),
            ),
          ],
        ),
      ),
    );
  }
}

class Contenitore extends StatelessWidget {
  int colore;
  String testo;
  Contenitore({this.colore = 400, this.testo = 'la seconda'});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(8),
      color: Colors.teal[colore],
      child: Text(testo),
    );
  }
}

