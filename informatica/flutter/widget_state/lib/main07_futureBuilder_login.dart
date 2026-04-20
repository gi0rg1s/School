import 'package:flutter/material.dart';

/* Login Aggiunto autenticazione simulata con FutureBuilder
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
*/

void main() {
  runApp(LoginConFuture());
}

class Utente {
  final String utente;
  final String cognome;
  final String nome;
  final String email;

  Utente({
    required this.utente,
    required this.cognome,
    required this.nome,
    required this.email,
  });

  factory Utente.fromJson(Map<String, dynamic> json) {
    return Utente(
      utente: json['utente'],
      cognome: json['cognome'],
      nome: json['nome'],
      email: json['email'],
    );
  }
}

/// funzione che ritorna un tipo Future. E' simulazione di una chiamata tipo
/// http. Verrà usata per valorizzare l'attributo future di FutureBuilder
Future<Utente> _getProfiloAccount(String utente, String password) async {
  /*
  // contesto reale
  final response = await http.post(
      'https://scuola.pr.it/login.php',
      headers: {
        'Content-type': 'application/x-www-form-urlencoded'
      },
      body: {
        'richiesta': 'getProfilo',
        'utente': utente,
        'password': password
      });
  */
  // simulazione di ritardo elaborazione/attesa
  final profiloJsonRitorno = await Future.delayed(
    const Duration(seconds: 5),
    () =>
    // tipo jSon nativo flutter
    {
      'utente': utente,
      'nome': 'pluto',
      'cognome': 'pippo',
      'email': 'info@pippo.com',
    },
  );

  /* Contesto reale
  if (response.statusCode == 200) {
    // Da response arriva l'array json
    final profiloJsonRitorno = json.decode(response.body);*/
  return Utente.fromJson(profiloJsonRitorno);
}

class LoginConFuture extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Login',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomeLoginConFuture(title: 'Login'),
    );
  }
}

class HomeLoginConFuture extends StatefulWidget {
  const HomeLoginConFuture({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _StatoLoginConFuture createState() => _StatoLoginConFuture();
}

class _StatoLoginConFuture extends State<HomeLoginConFuture> {
  bool _passwordVisibile = false;

  ///Crea una global key che identifica univocamente il Form widget
  final _formKey = GlobalKey<FormState>();
  final _utenteController = TextEditingController();
  final _passwordController = TextEditingController();

  /// Crea reference per Future da assegnare successivamente a widget FutureBuilder
  Future<Utente>? _futuroUtente;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Form(
        key: _formKey,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: TextFormField(
                controller: _utenteController,
                keyboardType: TextInputType.text,
                decoration: InputDecoration(
                  labelText: 'Utente',
                  hintText: 'Inserisci l\'utente',
                  suffixIcon: Icon(
                    Icons.login,
                    color: Theme.of(context).primaryColorDark,
                  ),
                ),
                validator: (value) {
                  if (value!.isEmpty) {
                    return "* richiesto";
                  } else {
                    return null;
                  }
                },
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: TextFormField(
                controller: _passwordController,
                keyboardType: TextInputType.text,
                obscureText: !_passwordVisibile,
                decoration: InputDecoration(
                  labelText: 'Password',
                  hintText: 'Inserisci la password',
                  // il semplice attributo icon di TextFormField
                  // non avrebbe l'ascoltatore onPressed
                  suffixIcon:
                  // le Gesture si può usare il click lungo trattenuto
                  GestureDetector(
                    child: Icon(
                      _passwordVisibile
                          ? Icons.visibility
                          : Icons.visibility_off,
                      color: Theme.of(context).primaryColorDark,
                    ),
                    onLongPress: () {
                      setState(() {
                        _passwordVisibile = true;
                      });
                    },
                    onLongPressUp: () {
                      setState(() {
                        _passwordVisibile = false;
                      });
                    },
                  ),
                ),
                validator: (value) {
                  if (value!.isEmpty) {
                    return "* richiesto";
                  } else if (!isPasswordCompliant(value)) {
                    return "password non idonea";
                  }

                  return null;
                },
              ),
            ),
            ElevatedButton(
              child: const Text('Login'),
              onPressed: () {
                // validate ritorna true se la form è valida
                if (_formKey.currentState!.validate()) {
                  setState(() {
                    try {
                      // Genera il future
                      // Il completamento del future sarà a carico FutureBuilder
                      // che lo trasformerà in in dato concreto <Utente>
                      _futuroUtente = _getProfiloAccount(
                        _utenteController.text,
                        _passwordController.text,
                      );
                    } catch (_) {
                      print('errore login');
                    }
                  });
                  // visualizza Snackbar.
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(
                      content: Text('Ok, valori idonei per autenticazione!'),
                    ),
                  );
                }
              },
            ),
            // Nel widget FutureBuilder inizialmente _futuroUtente sarà null e
            // alla prima costruzione snapshot.hasData() ovviamente sarà falso
            // Nel momento in cui gli verrà attribuito un valore, dopo un
            // SetState(), il builder farà la richiesta alla funzione future che
            // in modo asincrono e lo stesso builder verrà invocato altre 2 volte:
            // 1 con ConnectionState.waiting e 2 ConnectionState.done e con
            //  snapshot.hasData() = true restituendo il valore dell'utente
            FutureBuilder<Utente>(
              future: _futuroUtente,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Text(
                    "Ok account autenticato:\n" +
                        "utente: " +
                        snapshot.data!.utente +
                        "\n" +
                        snapshot.data!.cognome +
                        " " +
                        snapshot.data!.nome +
                        "\neMail: " +
                        snapshot.data!.email,
                  );
                }

                if (snapshot.hasError) {
                  return Text("${snapshot.error}");
                }

                // default, mostra uno spinner di caricamento
                return const CircularProgressIndicator();
              },
            ),
          ],
        ),
      ),
    );
  }

  bool isPasswordCompliant(String password, [int minLength = 6]) {
    if (password.isEmpty) return false;

    bool hasUppercase = password.contains(RegExp(r'[A-Z]'));
    bool hasDigits = password.contains(RegExp(r'[0-9]'));
    bool hasLowercase = password.contains(RegExp(r'[a-z]'));
    bool hasSpecialCharacters = password.contains(
      RegExp(r'[!@#$%^&*(),.?":{}|<>]'),
    );
    bool hasMinLength = password.length >= minLength;

    return hasDigits &
        hasUppercase &
        hasLowercase &
        hasSpecialCharacters &
        hasMinLength;
  }
}
