import 'dart:math';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Impiccato',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomePageProva(title: 'Impiccato'),
    );
  }
}

class HomePageProva extends StatefulWidget {
  const HomePageProva({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _HomePageProvaState createState() => _HomePageProvaState();
}

class _HomePageProvaState extends State<HomePageProva> {
  final Random _random = Random();
  final TextEditingController _controller = TextEditingController();
  static const int _maxErrori = 10;

//random words list
  final List<String> _paroleImpiccato = const [
    'avventura',
    'bicicletta',
    'cannocchiale',
    'drago',
    'elefante',
    'finestra',
    'girasole',
    'macchina',
    'navigatore',
    'orchestra',
    'pirata',
    'quaderno',
    'stellare',
    'tartaruga',
    'zucchero',
  ];

  String _parolaSelezionata = '';
  String _testoUtente = '';
  String _parolaSchermo = '';
  String _statoPartita = 'Pesca una parola per iniziare';
  bool _haVinto = false;
  bool _gameOver = false;
  int _errori = 0;

  final List<String> _immaginiImpiccato = const [
    'assets/0.png',
    'assets/1.png',
    'assets/2.png',
    'assets/3.png',
    'assets/4.png',
    'assets/4.png',
    'assets/6.png',
    'assets/7.png',
    'assets/8.png',
    'assets/9.png',
    'assets/10.png',
  ];

//check error number and get the right img
  String get _immagineCorrente {
    return _immaginiImpiccato[_errori];
  }

  void _pescaParola() {
    setState(() {
      //get a random word from the previous list
      _parolaSelezionata =
          _paroleImpiccato[_random.nextInt(_paroleImpiccato.length)];
      //initialize the game
      _testoUtente = '';
      _haVinto = false;
      _gameOver = false;
      _errori = 0;
      initialize_parola();
      _statoPartita = 'Nuova parola pescata. Inserisci una lettera.';
      _controller.clear();
    });
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  void initialize_parola() {
    //fill the string with underscores separeted by spaces
    _parolaSchermo = List.filled(_parolaSelezionata.length, '_').join(' ');
  }

  bool checkWin() {
    //if the string does not contain underscores anymore the user won
    final vinto = !_parolaSchermo.contains('_');

    if (vinto) {
      _haVinto = true;
      _statoPartita = 'Hai vinto! La parola era $_parolaSelezionata.';
    }

    return vinto;
  }

  bool checkGameOver() {
    //check the number of errors
    if (_errori >= _maxErrori) {
      _gameOver = true;
      _statoPartita = 'Game over! La parola era $_parolaSelezionata.';
      return true;
    }

    return false;
  }

  void aggiorna_parola() {
    //update the word after every try
    if (_parolaSelezionata.isEmpty ||
        _testoUtente.isEmpty ||
        _haVinto ||
        _gameOver) {
      return;
    }

    final lettere = _parolaSchermo.split(' ');
    final testo = _testoUtente.toLowerCase();
    var letteraTrovata = false;
    var parolaCorretta = false;

//check if the user put only one letter and search it into the selected word
    if (testo.length == 1) {
      for (int i = 0; i < _parolaSelezionata.length; i++) {
        if (_parolaSelezionata[i].toLowerCase() == testo) {
          lettere[i] = _parolaSelezionata[i];
          letteraTrovata = true;
        }
      }
      //if the user put a entire word we check if it is the word to guess
    } else if (testo == _parolaSelezionata.toLowerCase()) {
      parolaCorretta = true;
      for (int i = 0; i < _parolaSelezionata.length; i++) {
        lettere[i] = _parolaSelezionata[i];
      }
    }

    setState(() {
      _parolaSchermo = lettere.join(' ');
      if (letteraTrovata || parolaCorretta) {
        _statoPartita = 'Tentativo corretto.';
      } else {
        _errori++;
        _statoPartita = 'Tentativo sbagliato. Errori: $_errori/$_maxErrori';
      }

      if (checkWin()) {
        return;
      }

      if (checkGameOver()) {
        return;
      }

      if (parolaCorretta) {
        _statoPartita = 'Hai indovinato la parola.';
      } else if (letteraTrovata) {
        _statoPartita = 'Lettera presente nella parola.';
      } else {
        _statoPartita = 'Lettera non presente, riprova.';
      }

      _controller.clear();
      _testoUtente = '';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              const Text(
                'Parola da indovinare:',
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 12),
              Image.asset(
                _immagineCorrente,
                height: 220,
                fit: BoxFit.contain,
              ),
              const SizedBox(height: 12),
              Text(
                'Errori: $_errori / $_maxErrori',
                textAlign: TextAlign.center,
              ),
              const SizedBox(height: 12),
              Text(
                _parolaSchermo.isEmpty
                    ? 'Premi il pulsante per pescare una parola'
                    : _parolaSchermo,
                textAlign: TextAlign.center,
                style: const TextStyle(fontSize: 28, letterSpacing: 4),
              ),
              const SizedBox(height: 24),
              TextField(
                controller: _controller,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Inserisci una lettera o una parola',
                ),
              ),
              const SizedBox(height: 12),
              ElevatedButton(
                onPressed: (_parolaSelezionata.isEmpty || _haVinto || _gameOver)
                    ? null
                    : () {
                        setState(() {
                          _testoUtente = _controller.text;
                        });
                        aggiorna_parola();
                      },
                child: const Text('Invia input'),
              ),
              const SizedBox(height: 12),
              Text(
                _testoUtente.isEmpty
                    ? 'Nessun input inserito'
                    : 'Hai scritto: $_testoUtente',
                textAlign: TextAlign.center,
              ),
              const SizedBox(height: 12),
              Text(
                _statoPartita,
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: _haVinto ? FontWeight.bold : FontWeight.normal,
                ),
              ),
              const SizedBox(height: 12),
              ElevatedButton(
                child: const Text('Pesca una parola casuale'),
                onPressed: _pescaParola,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
