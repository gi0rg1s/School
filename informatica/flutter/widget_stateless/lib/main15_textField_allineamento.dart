import 'package:flutter/material.dart';

/// Form di Login semplice (senza uso di stato) con uso di TexField e attributo decoration
/// Per adattamento usa Flexible e Expanded, widget che controllano come si
/// flette/adatta un elemento di una riga o colonna per riempire lo spazio
/// disponibile nell'asse principale (ad esempio, orizzontalmente per una riga o
/// verticalmente per una colonna).
/// Il widget Expanded in flutter è una scorciatoia di Flexible con l'adattamento
/// default di FlexFit.loose
/// Nel caso del Widget TexField su una Row/Column Expande/Flexible sono
/// obbligatori. Vedi sotto perché ...

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
      home: _LoginSemplice(),
    );
  }
}

class _LoginSemplice extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Login Senza Stato")),
      body: const Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            /*
            Una Row con un semplice TextField contenuto causa un errore di layout 
            perché il motore flutter non sa come espandere in direzione orizzontale,
            quindi dobbiamo vincolare la larghezza del TextField inserendolo in un 
            contenitore, ci sono almeno 3 modi per farlo..

            Use Expanded
            Row(
              children: <Widget>[
                Expanded(child: TextField()),
                OtherWidget(),
              ],
            )
            Use Flexible
            Row(
              children: <Widget>[
                Flexible(child: TextField()),
                OtherWidget(),
              ],
            )
            Wrap it in Container or SizedBox and provide width
            Row(
              children: <Widget>[
                SizedBox(width: 100, child: TextField()),
                OtherWidget(),
              ],
            )
            https://itnext.io/flutter-responsive-apps-flexible-vs-expanded-ff8cc92b468f
            */
            Row(
              children: <Widget>[
                Padding(
                  padding: EdgeInsets.all(16.0),
                  child: Icon(Icons.login, color: Colors.pink),
                ),
                Expanded(  // PROVA A RIMUOVERE QUESTO WIDGET E VEDI COSA SUCCEDE
                  child: TextField(
                    decoration: InputDecoration(
                      labelText: "Inserisci l'utente",
                    ),
                  ),
                ),
              ],
            ),
            Row(
              children: <Widget>[
                Padding(
                  padding: EdgeInsets.all(16.0),
                  child: Icon(Icons.visibility_off, color: Colors.pink),
                ),
                Flexible(
                  fit: FlexFit.loose, //default (riempie lo spazio disponibile)
                  child: TextField(
                    decoration: InputDecoration(
                      labelText: "Inserisci la password",
                    ),
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
