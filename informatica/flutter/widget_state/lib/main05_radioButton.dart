import 'package:flutter/material.dart';
/*
* RadioListTile è un widget che permette di creare un bottone Radio
* se value è uguale a groupValue il bottone sarà selezionato
* onChanged è la funzione che viene chiamata quando il bottone viene selezionato
*/
void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: RadioExample(),
    );
  }
}

class RadioExample extends StatefulWidget {
  @override
  _RadioExampleState createState() => _RadioExampleState();
}

class _RadioExampleState extends State<RadioExample> {
  String? _selectedOption = 'Opzione 1';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Esempio Radio Widget')),
      body: Column(
        children: <Widget>[
          RadioListTile<String>(
            title: const Text('Opzione 1'),
            value: 'Opzione 1',
            groupValue: _selectedOption,
            onChanged: (value) {
              setState(() {
                _selectedOption = value;
              });
            },
          ),
          RadioListTile<String>(
            title: const Text('Opzione 2'),
            value: 'Opzione 2',
            groupValue: _selectedOption,
            onChanged: (value) {
              setState(() {
                _selectedOption = value;
              });
            },
          ),
          RadioListTile<String>(
            title: const Text('Opzione 3'),
            value: 'Opzione 3',
            groupValue: _selectedOption,
            onChanged: (value) {
              setState(() {
                _selectedOption = value;
              });
            },
          ),
          const SizedBox(height: 20),
          Text('Selezionato: $_selectedOption')
        ],
      ),
    );
  }
}
