import 'package:flutter/material.dart';
/*
Named Routes: Utilizziamo initialRoute e routes (di MaterialApp) per definire le schermate.
Passaggio di Parametri: Con Navigator.pushNamed() passiamo un parametro (arguments).
Ricezione del Valore di Ritorno: Aspettiamo il risultato con await e lo mostriamo usando SnackBar.
*/

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navigator Avanzato con Swipe',
      initialRoute: '/',
      routes: {
        '/': (context) => FirstPage(),
        '/second': (context) => SecondPage(),
        '/third': (context) => ThirdPage(),
      },
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
          // con await aspettiamo il risultato che torna dalla Seconda Pagina
          onPressed: () async {
            final result = await Navigator.pushNamed(
              context,
              '/second',
              /** parametro di invio. E' possibile passare più parametri 
               * con lista  arguments: ["Elemento 1", "Elemento 2", "Elemento 3"]
               * per recuperarli:
               * List<String> list = ModalRoute.of(context)?.settings.arguments as List<String>;
              */
              arguments: 'Ciao dalla Prima Pagina!',
            );
            if (result != null) {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text('Risultato: $result')),
              );
            }
          },
          child: const Text('Vai alla Seconda Pagina'),
        ),
      ),
    );
  }
}

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final String message = ModalRoute.of(context)?.settings.arguments as String;

    return Scaffold(
      appBar: AppBar(title: const Text('Seconda Pagina')),
      body: GestureDetector(
        onHorizontalDragUpdate: (details) {
          // Controllo della direzione dello swipe: verso sinistra
          // details.primaryDelta! > 0 trascinamento verso destra.
          if (details.primaryDelta! < 0) {
            Navigator.pushNamed(context, '/third');
          }
        },
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('Messaggio ricevuto: $message'),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  Navigator.pop(context, 'Risposta dalla Seconda Pagina!');
                },
                child: const Text('Torna Indietro con Risposta'),
              ),
              const SizedBox(height: 20),
              const Text(
                  'Trascina verso sinistra (sul messaggio sopra) per andare alla Terza Pagina ⬅️'),
            ],
          ),
        ),
      ),
    );
  }
}

class ThirdPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Terza Pagina')),
      body: Center(
        child: GestureDetector(
          onHorizontalDragUpdate: (details) {
            // Controllo della direzione dello swipe: verso destra
            if (details.primaryDelta! < 0) {
              Navigator.pop(context);
            }
          },
          child: const Text(
              'Trascina verso sinistra per tornare alla Seconda Pagina'),
        ),
      ),
    );
  }
}
