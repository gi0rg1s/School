import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

/*
  ChangeNotifier è una classe fornita dal framework Flutter che facilita la gestione dello stato reattivo.
👉 Funziona come un osservabile, notificando automaticamente i widget che stanno ascoltando quando cambia lo stato.
*/
class ContatoreNotifier extends ChangeNotifier {
  int _count = 0;

  int get count => _count;

  void increment() {
    _count++;
    notifyListeners(); // Notifica ai widget in ascolto
  }
}

void main() {
  runApp(
    /*
    ChangeNotifierProvider è un widget fornito dalla libreria Provider.
      Crea un'istanza di un ChangeNotifier.
      Fornisce questa istanza ai widget discendenti.
      I widget che usano il ChangeNotifier vengono ricostruiti automaticamente quando i dati cambiano.
    */
    ChangeNotifierProvider(
      create:
          (context) =>
              ContatoreNotifier(), //istanza del provider con il notifier
      child: MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: CounterScreen());
  }
}

class CounterScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Counter Notifier")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("Conteggio:", style: TextStyle(fontSize: 20)),
            Consumer<ContatoreNotifier>(
              builder: (context, counterNotifier, child) {
                return Text(
                  "${counterNotifier.count}",
                  style: TextStyle(fontSize: 40, fontWeight: FontWeight.bold),
                );
              },
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                context.read<ContatoreNotifier>().increment();
              },
              child: Text("Incrementa"),
            ),
          ],
        ),
      ),
    );
  }
}
