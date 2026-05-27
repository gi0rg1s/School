import 'package:flutter/material.dart';
/*
Navigator.push(): Aggiunge una nuova pagina nello stack di navigazione.
Navigator.pop(): Rimuove la pagina corrente dallo stack, tornando alla precedente.
MaterialPageRoute: Crea l'animazione di transizione tra le pagine.

In pratica le schermate sono widget come qualsiasi altro, ma vengono gestite
come schermate da MaterialApp

Navigator.push:
    Il Navigator è un gestore di stack che mantiene la cronologia delle schermate in un'app Flutter.
    Il metodo push aggiunge una nuova schermata sopra l'attuale nello stack di navigazione.

  Primo argomento: context
    Il context è necessario per accedere al tree widget dell'app e trovare il Navigator corretto.
    È l'oggetto BuildContext, che rappresenta il widget corrente.

  Secondo argomento: MaterialPageRoute
    Crea una transizione tra la pagina corrente e la nuova pagina.
    Prende un builder, che restituisce il widget della nuova schermata (SecondPage).
    MaterialPageRoute segue il design Material, fornendo automaticamente animazioni di transizione standard.
*/
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navigator Demo',
      home: FirstPage(),
    );
  }
}

class FirstPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Prima Pagina')),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => SecondPage()),
            );
          },
          child: const Text('Vai alla Seconda Pagina'),
        ),
      ),
    );
  }
}

// SecondPage non implementa MaterialApp perché è una pagina
// che viene visualizzata sopra la prima, non è l'app principale
class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Seconda Pagina')),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // notare che in pop non è indicata la pagina perché torna
            // semplicemente alla precedente
            Navigator.pop(context); 
          },
          child: const Text('Torna Indietro'),
        ),
      ),
    );
  }
}
