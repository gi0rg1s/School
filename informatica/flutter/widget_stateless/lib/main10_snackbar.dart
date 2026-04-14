import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  final String _title = 'Snackbar';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
      home: Scaffold(
        appBar: AppBar(title: Text(_title)),
        body: const Center(child: MyStatelessWidget()),
      ),
    );
  }
}

class MyStatelessWidget extends StatelessWidget {
  const MyStatelessWidget();

  void mostra_snackbar(context) {
    /*
    ScaffoldMessenger.of(context) ottiene il riferimento allo
    ScaffoldMessenger più vicino a questo contesto.
    */
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: const Text('W Snackbar!'),
        action: SnackBarAction(label: 'Fai qualcosa...', onPressed: () {}),
      ),
    );
    /* O Più sinteticamente
    ScaffoldMessenger.of(
      context,
    ).showSnackBar(SnackBar(content: Text('W Snackbar!')));*/
  }

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      child: const Text('Show Snackbar'),
      onPressed: () {
        mostra_snackbar(context);
      },
    );
  }
}
