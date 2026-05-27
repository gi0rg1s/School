import 'package:flutter/material.dart';

/// ListView è il widget di scorrimento più comunemente utilizzato.
/// Visualizza i suoi widget uno dopo l'altro nella direzione di scorrimento.
/// Nell'asse incrociato, i widget devono riempire ListView.
///
/// Nell'esempio
/// ListView creata con ListView.builder e popolata con array bidimensionale
void main() => runApp(Lista());

class Lista extends StatelessWidget {
  final List<Map<String, dynamic>> voci = [
    {'testo': 'Allarme', 'icona': Icons.access_alarm},
    {'testo': 'Collezione', 'icona': Icons.collections},
    {'testo': 'Pdf', 'icona': Icons.picture_as_pdf},
    {'testo': 'Camera', 'icona': Icons.camera},
    {'testo': 'Regalo', 'icona': Icons.card_giftcard},
    {'testo': 'Edit', 'icona': Icons.mode_edit},
    {'testo': 'Bug', 'icona': Icons.bug_report},
    {'testo': 'Zoom in', 'icona': Icons.zoom_in},
    {'testo': 'Zoom', 'icona': Icons.zoom_out},
  ];

  /*
  // Oppure + semplice
  final stringhe = [
    "Allarme","Collezione","Pdf","Camera","Regalo","Edit","Bug","Zoom in","Zoom"];
  final icone = [
    Icons.access_alarm,Icons.collections,Icons.picture_as_pdf,Icons.camera,
    Icons.card_giftcard,Icons.mode_edit,Icons.bug_report,
    Icons.zoom_in,Icons.zoom_out
  ];*/
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ListView Demo',
      theme: ThemeData(primarySwatch: Colors.indigo),
      home: Scaffold(
        appBar: AppBar(title: const Text('ListView Demo')),
        // Crea ListView di 9 elementi indicizzati tipo Card
        body: Column(
          mainAxisSize: MainAxisSize.max,
          children: [
            const Card(
              margin: EdgeInsets.all(8),
              elevation: 10,
              color: Colors.deepOrange,
              child: Text("ListView demo"),
            ),
            Expanded(
              child: ListView.builder(
                itemCount: 9,
                scrollDirection: Axis.vertical,
                itemBuilder: (context, index) {
                  // Card: Una scheda in stile material design: un pannello con angoli
                  // arrotondati e un'ombra in elevazione.
                  // Una scheda è un contenitore utilizzato per rappresentare alcune
                  // informazioni correlate, ad esempio un album, una posizione
                  // geografica, un pasto, i dettagli di contatto, ecc.
                  return Card(
                    elevation: 12,
                    shape: const StadiumBorder(
                      side: BorderSide(color: Colors.deepOrange, width: 1.0),
                    ),
                    // ListTile: una singola riga ad altezza fissa che in genere
                    // contiene del testo e un'icona iniziale o finale.
                    child: ListTile(
                      // accesso ai valori di chiave e valore del map tramite indice
                      leading: Icon(
                        voci[index]['icona'],
                        color: Colors.deepOrange,
                        size: 30,
                      ),
                      title: Text(voci[index]['testo']),
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
