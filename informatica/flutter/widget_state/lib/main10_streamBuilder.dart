import 'dart:async';
import 'package:flutter/material.dart';

/// Stream: sorgente di eventi di dati asincroni, più precisamente
/// un flusso che dispone di interfacce per ricevere una sequenza di eventi.
/// Es: Lo stream è il fiume e i dati sono i pesci che lo attraversano. Il
/// pescatore si sottoscrive allo stream cercando di pescare i dati ma potrebbe
/// incappare in alghe o scarpe (dati sporchi = error)
///
/// StreamBuilder è un Widget che reagisce ai campi di stato di uno stream e
/// ne aggiorna il contenuto dello stesso SENZA BISOGNO DI CAMBIARE STATO

void main() => runApp(StreamDemo());

class StreamDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: StreamDemoHomePage(),
    );
  }
}

class StreamDemoHomePage extends StatefulWidget {
  @override
  _StreamDemoHomePageState createState() => _StreamDemoHomePageState();
}

class _StreamDemoHomePageState extends State<StreamDemoHomePage> {
  int _counter = 0;
  StreamController<int>? _events;

  @override
  initState() {
    super.initState();
    _events = StreamController<int>(); // istanziazione dello StreamController
    _events?.add(0);
  }

  void _incrementCounter() {
    /// L'evento si trasmette automaticamente allo StreamBuilder senza bisogno
    /// di CAMBIARE LO STATO
    _counter++;
    _events?.add(_counter);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("StreamBuilder test")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text('Hai cliccato:'),
            StreamBuilder(
              stream: _events?.stream,
              builder: (BuildContext context, snapshot) {
                return Text(
                  "${snapshot.data} volte!",
                  style: Theme.of(context).textTheme.bodyMedium,
                );
              },
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Incrementa 1',
        child: const Icon(Icons.add),
      ),
    );
  }
}
