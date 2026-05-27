import 'package:flutter/material.dart';

/*
La versione più recente, Material Design 3 (nota anche come Material You), introduce:
✅ Personalizzazione dinamica dei colori in base al wallpaper dell'utente
✅ Nuovi componenti e stili aggiornati
✅ Migliore accessibilità e leggibilità
✅ Effetti di profondità e trasparenza più sofisticati
Usa Material 3 attivando useMaterial3: true nel tema.
✅ Implementa diversi tipi di pulsanti introdotti in Material 3, come FilledButton e ElevatedButton.
✅ Mostra un NavigationBar in stile Material 3.
✅ Utilizza un tema basato su una palette di colori derivata da un seme (seedColor).
*/
void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Material Design 3 Demo',
      theme: ThemeData(
        useMaterial3: true, // Attiva Material Design 3
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.teal),
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Material Design 3'),
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Colors.white,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {},
              style: ElevatedButton.styleFrom(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12)),
              ),
              child: const Text('Elevated Button'),
            ),
            const SizedBox(height: 16),
            FilledButton(
              onPressed: () {},
              child: const Text('Filled Button'),
            ),
            const SizedBox(height: 16),
            OutlinedButton(
              onPressed: () {},
              child: const Text('Outlined Button'),
            ),
            const SizedBox(height: 16),
            FloatingActionButton(
              onPressed: () {},
              child: const Icon(Icons.add),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () {
          _showDialog(context);
        },
        icon: const Icon(Icons.thumb_up),
        label: const Text('Dialog'),
      ),
      bottomNavigationBar: NavigationBar(
        onDestinationSelected: (index) {
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              content: Text('Hai cliccato l\'icona: ${index+1}'),
            ),
          );
        },
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: 'Home'),
          NavigationDestination(icon: Icon(Icons.search), label: 'Search'),
          NavigationDestination(icon: Icon(Icons.settings), label: 'Settings'),
        ],
      ),
    );
  }

  void _showDialog(BuildContext context) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Dialog'),
        content: const Text('Esempio di dialog box.'),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Annulla'),
          ),
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('OK'),
          ),
        ],
      ),
    );
  }
}
