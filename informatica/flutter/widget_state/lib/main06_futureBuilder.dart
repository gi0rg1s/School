import 'dart:async';
import 'package:flutter/material.dart';

/* Semplice uso di FutureBuilder
  FutureBuilder è un Widget che si costruisce sulla base del completamento
  di una chiamata future.
  In pratica il FB viene creato asincronicamente con la risposta al metodo
  Future indicato nell'attributo future se istanziato.
  Il valore dell'attributo future normalmente non deve essere generato durante la
  prima costruzione di FB (State.build o StatelessWidget.build) altrimenti ogni
  volta che il genitore viene ricostruito l'attività asincrona verrà riavviata.
  Meglio generare con la ricostruzione dello State (State.setState()).
  La ricostruzione del widget è programmata per dopo il completamento del future,
  ma è disaccoppiata dalla tempistica del future.
  Il callback del builder viene chiamato a discrezione della pipeline Flutter
  e riceverà quindi una sottosequenza di chiamate dipendente dal timing degli
  snapshot che rappresentano l'interazione con il future.

  Nell'esempio sotto erroneamente all'attributo future di FB è stato valorizzato
  richiamando un metodo Future. 
  PROVA AD AGGIUNGERE BOTTONE E VALORIZZARE future DA onPressed() 
  */
void main() {
  runApp(MaterialApp(home: FutureBuilderDemo()));
}

class FutureBuilderDemo extends StatefulWidget {
  @override
  _StatoFutureBuilderDemo createState() => _StatoFutureBuilderDemo();
}

class _StatoFutureBuilderDemo extends State<FutureBuilderDemo> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('FutureBuilder Demo')),
      body: FutureBuilder(
        // Invoca la funzione di tipo Future<String> che metterà il widget in
        // stato di attesa fino al completamento asincrono (non bloccante) della
        // funzione stessa
        // In pratica: il codice del builder viene invocato immediatamente
        // durante la costruzione del FutureBuilder, l'attributo future viene
        // valorizzato immediatamente con la funzione getFutureData() che quando
        // sarà completata verrà richiamato il builder finirà di costruire
        // il widget stesso
        future: getFutureData(),
        builder: (BuildContext context, AsyncSnapshot snapshot) {
          if (snapshot.hasData) {
            return Center(child: Text(snapshot.data));
          } else {
            return const Center(child: CircularProgressIndicator());
          }
        },
      ),
    );
  }

  Future<String> getFutureData() async =>
      await Future.delayed(const Duration(seconds: 6), () => 'Dati Ricevuti');
}
