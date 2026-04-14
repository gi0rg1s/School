import 'dart:async';
import 'package:flutter/material.dart';

/// Sempre nell'ambito degli stream c'è anche StreamController e StreamTransformer

/// StreamController: Un controller per lo stream, gestisce l'invio di dati,
/// errori ed eventi eseguiti sullo stream.
/// Può essere usato per creare un flusso semplice su cui sottoscrittori possono
/// ascoltare e per inviare eventi a quel flusso.
/// È possibile verificare se il flusso è in pausa o meno e se ha sottoscrittori
/// oltre a ricevere una richiamata quando uno di questi cambia.
/// In uno StreamBuilder, nell'attributo stream, l'istanza di StreamController si
/// può usare così: StreamBuilder(stream: _streamController.stream)

/// StreamTransformer
/// Trasforma un flusso.
/// Es. se lo stream riguarda una digitazione password e la password non è valida
/// il transformer non inserisce il valore nello stream e genera un evento di errore
/// Quando il metodo Stream.transform di uno stream viene richiamato con un
/// StreamTransformer, il flusso chiama il metodo di collegamento sul
/// trasformatore fornito. Il flusso risultante viene quindi restituito dal
/// metodo Stream.transform.
/// Concettualmente, un trasformatore è un metodo incapsulato in Stream
/// Se si vuole trasformare uno stream di uno StreamBuilder sarà così:
/// StreamBuilder(stream: _streamController.stream.transform(_streamTransformer),

/// Form di Login con verifica in tempo reale, lato client, per validità email
/// e lunghezza password con StreamController e StreamTrasformer

void main() {
  runApp(LoginSemplice());
}

class LoginSemplice extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Login',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomeLoginSemplice(title: 'Login'),
    );
  }
}

class HomeLoginSemplice extends StatefulWidget {
  const HomeLoginSemplice({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _StatoLoginSemplice createState() => _StatoLoginSemplice();
}

class _StatoLoginSemplice extends State<HomeLoginSemplice> {
  final StreamController<String> _emailController = StreamController();
  final StreamController<String> _passwordController = StreamController();

  final _emailValidate = StreamTransformer<String, String>.fromHandlers(
    handleData: (email, stream) {
      if (RegExp(
        r"^[_a-z0-9-]+(.[a-z0-9-]+)@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$",
      ).hasMatch(email)) {
        stream.add(email);
      } else {
        stream.addError("email non valida");
      }
    },
  );

  final _passwordValidate = StreamTransformer<String, String>.fromHandlers(
    handleData: (password, stream) {
      if (password.length >= 6) {
        stream.add(password);
      } else {
        stream.addError("password troppo corta");
      }
    },
  );

  @override
  void dispose() {
    super.dispose();
    _emailController.close();
    _passwordController.close();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Row(
              children: <Widget>[
                const Padding(
                  padding: EdgeInsets.all(16.0),
                  child: Icon(Icons.login, color: Colors.pink),
                ),
                Expanded(
                  child: StreamBuilder(
                    stream: _emailController.stream.transform(_emailValidate),
                    builder: (context, snapshot) {
                      return TextField(
                        decoration: InputDecoration(
                          hintText: "email",
                          labelText: "Inserisci l'email",
                          errorText:
                              snapshot.hasError
                                  ? snapshot.error.toString()
                                  : "",
                        ),
                        // Per questo TexField abilità la tastiera
                        // dedicata alla digitazione email
                        keyboardType: TextInputType.emailAddress,
                        // evento sollevato ad ogni digitazione di carattere
                        onChanged: _emailController.sink.add,
                      );
                    },
                  ),
                ),
              ],
            ),
            Row(
              children: <Widget>[
                const Padding(
                  padding: EdgeInsets.all(16.0),
                  child: Icon(Icons.visibility_off, color: Colors.pink),
                ),
                Flexible(
                  child: StreamBuilder(
                    stream: _passwordController.stream.transform(
                      _passwordValidate,
                    ),
                    builder: (context, snapshot) {
                      return TextField(
                        decoration: InputDecoration(
                          hintText: "password",
                          labelText: "Inserisci la password",
                          errorText:
                              snapshot.hasError
                                  ? snapshot.error.toString()
                                  : "",
                        ),
                        // Il TextField è per la password quindi non mostra caratteri
                        obscureText: true,
                        onChanged: _passwordController.sink.add,
                      );
                    },
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
