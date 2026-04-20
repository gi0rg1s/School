import 'package:flutter/material.dart';

// Login semplice che usa TextFormField all'interno di Form ancora non gestita.
// TextFormField è più ricco di TextField perché espone, tra i vari, suffixIcon
// dove si può inserire un IconButton che risponde a onPressed()
void main() {
  runApp(LoginVediPassword());
}

class LoginVediPassword extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'TextFormField con IconButton',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomeLoginVediPassword(title: 'TextFormField con IconButton'),
    );
  }
}

class HomeLoginVediPassword extends StatefulWidget {
  const HomeLoginVediPassword({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _StatoLoginVediPassword createState() => _StatoLoginVediPassword();
}

class _StatoLoginVediPassword extends State<HomeLoginVediPassword> {
  bool _passwordVisibile = false;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Form(
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
                )),
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
                      suffixIcon: IconButton(
                          icon: Icon(
                            // in base al valore booleano del campo passwordVisible
                            // sceglie l'icona
                            _passwordVisibile
                                ? Icons.visibility
                                : Icons.visibility_off,
                            color: Theme.of(context).primaryColorDark,
                          ),
                          onPressed: () {
                            // aggiorna lo stato modificando il relativo campo boolean
                            // della password visibile
                            setState(() {
                              _passwordVisibile = !_passwordVisibile;
                            });
                          }),
                      // Oppure con le Gesture e il click lungo trattenuto
                      // Sostituisci il widget GestureDetector qui sotto al 
                      // posto di IconButton
                      /*GestureDetector(
                    child: Icon(
                      // Based on passwordVisible state choose the icon
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
                ),*/
                    )))
          ],
        ),
      ),
    );
  }
}
