import 'package:flutter/material.dart';
/*
GlobalKey è una classe che viene utilizzata per identificare un widget in modo 
univoco all'interno di una gerarchia di widget, indipendentemente da dove si trova 
nel widget tree.
È utile per accedere a particolari stati o per eseguire operazioni su un widget 
da una posizione esterna a esso.

Casi d'uso principali di GlobalKey:
Accesso allo stato di un widget: Quando si utilizza uno stato di un widget 
(ad esempio, un StatefulWidget), si può utilizzare un GlobalKey per ottenere 
l'istanza dello stato e interagire con essa. Questo è utile quando vuoi modificare 
lo stato di un widget da una parte diversa dell'applicazione.

Navigazione: Un GlobalKey può essere utilizzato per ottenere l'accesso alla Scaffold 
di una pagina, in modo da poter mostrare il SnackBar, fare il push di una nuova rotta,
o fare altre operazioni relative alla navigazione o al layout.

Widget di tipo Form: Se hai un Form in Flutter, un GlobalKey ti permette di validare, 
salvare o resettare il modulo da un'altra parte della tua app come dimostra questo
esempio.
*/
/* Aggiunto validazione form (utente e password)
 Alla Form, che contiene i TextFormField, viene assegnato un (GlobalKey) all'
 attributo key per identificarla altrove.
 Ad ogni TextFormField viene assegnata una funzione di controllo del valore
 con l'attributo validator
 I metodi validator dei TextFormField verranno invocati in sequenza dal metodo
 validate() della Form a cui appartengono

 Nell'esempio, il metodo validate() verrà invocato da un bottone apposito
 Il controllo della password viene fatto con regEx e notificato con snackBar
 L'icona affiancata alla password risponde alle gesture tipo click lungo 
 */
void main() {
  runApp(LoginValidator());
}

class LoginValidator extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Login',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomeLoginValidator(title: 'Login'),
    );
  }
}

class HomeLoginValidator extends StatefulWidget {
  const HomeLoginValidator({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _StatoLoginValidator createState() => _StatoLoginValidator();
}

class _StatoLoginValidator extends State<HomeLoginValidator> {
  bool _passwordVisibile = false;
  // Crea una global key che identifica univocamente il Form widget
  // e permette la validazione
  final _formKey = GlobalKey<FormState>();

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
                    return "* richiesto"; // va nel messaggio di errore
                  } else {
                    return null; // l'imput è valido
                  }
                },
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: TextFormField(
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
                      // Click lungo
                      setState(() {
                        _passwordVisibile = true;
                      });
                    },
                    onLongPressUp: () {
                      // Clicl lungo rilasciato
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
                // simbolo ! evita il controllo/vincolo che validate possa non
                // avere un valore, cioè i corrispondenti validator
                if (_formKey.currentState!.validate()) {
                  // visualizza Snackbar di scaffold
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Ok, account verificato!')),
                  );
                }
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
