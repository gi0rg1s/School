import 'package:flutter/material.dart';
// Semplice Widget che riscrive su un Text il contenuto di un TextField 
// con metodo onSubmitted
void main() {
  runApp(App());
}

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'TextField onSubmitted',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: HomePageProva(title: 'TextField onSubmitted'),
    );
  }
}

class HomePageProva extends StatefulWidget {
  HomePageProva({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _HomePageProvaState createState() => _HomePageProvaState();
}

class _HomePageProvaState extends State<HomePageProva> {
  String _msg = "";
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextField(
             /* Alla conferma dell'inserimento dell'input (return) si
                solleva l'evento onSubmitted.
                Il metodo setState() della classe State aggiorna l'attributo _msg
                con il contenuto di TextField invocando in automatico il metodo
                build che aggiorna tutto l'albero dei widget. Il widget Text qui 
                sotto verrà di conseguenza aggiornato col nuovo valore
              */
              onSubmitted: (String msg) {
                setState(() =>  _msg = msg);
              },
            ),
            /*Divider: Crea una linea orizzontale (o verticale, se usato con VerticalDivider) per separare visivamente i contenuti.*/
            const Divider(),
            Text('Hai scritto: $_msg'),
          ],
        ),
      ),
    );
  }
}
