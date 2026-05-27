import 'package:flutter/material.dart';

/// Posizionamenti con Column e Container

void main() => runApp(MioWidget());

class MioWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Responsive",
      theme: ThemeData(
        primarySwatch: Colors.blue,
        canvasColor: const Color.fromARGB(255, 100, 230, 230),
      ),
      home: Responsive(),
    );
  }
}

class Responsive extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Responsive'), backgroundColor: Colors.blueAccent, foregroundColor: Colors.white,),
      body: Container(
        color: Colors.yellow,
        margin: const EdgeInsets.only(top: 26),
        padding: const EdgeInsets.all(16),
        alignment:
            Alignment.center, // Allineamento nel contenitore tutto centrato
        // Column contenitore il cui asse principale 'main' è verticale
        // Il secondario 'cross' è orizzontale
        child: Column(
          // prova con Row
          // La larghezza della colonna è la minima per contenere i widget
          // l'intera colonna si muoverà quindi con alignment del container
          // prova tutti gli allineamenti
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisSize:
              MainAxisSize
                  .max, // tutto lo spazio disponibile nel contenitore, prova a cambiare
          children: <Widget>[
            Container(
              color: Colors.green,
              child: const Text(
                'Font default (Roboto) sfondo verde',
                style: (TextStyle(color: Colors.black)),
              ),
            ),
            // prova a sostituire con Center il quale, non
            // avendo attributo color si deve configurare
            // il textStyle di Text per il colore di sfondo
            Container(
              color: Colors.red,
              child: const Text(
                'Font default sfondo rosso',
              ),
            ),
            Container(
              color: Colors.blue,
              child: const Text(
                'Font Chewy sfondo blu',
                style: TextStyle(fontFamily: 'Chewy', fontSize: 32),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
