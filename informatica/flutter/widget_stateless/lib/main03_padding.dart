import 'package:flutter/material.dart';

/// DecoratedBox e Padding
void main() => runApp(MioWidget());

class MioWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "material App",
      theme: ThemeData(primarySwatch: Colors.blue),
      home: LoginPage(),
    );
  }
}

/// 2 modi per ottenere lo stesso risultato widget

class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Layout Demo')),
      body: Center(
        /* contenitore principale centrato a tutta pagina, 1 child
          - Container con attributi
            - decoration con (decora il contenitore con colore e bordo)
            - child con riga testo ma con Padding per spazio interno
              si poteva usare direttamente il padding di Container
         */
        child: Container(
          decoration: BoxDecoration(
            color: Colors.green,
            border: Border.all(color: Colors.black, width: 2),
          ),
          child: const Padding(
            padding: EdgeInsets.all(30),
            child: Text("Login"),
          ),
        ),
      ),
    );
  }
}
