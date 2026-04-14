import 'package:flutter/material.dart';
import 'main09_Film.dart';

/// Pagina secondaria in risposta alla precedente che visualizza un film passato
/// come parametro
class WidgetRisposta extends StatelessWidget {
  final Film film;
  WidgetRisposta(Film f) : film = f;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Film scelto")),
      body: Container(
        color: Colors.yellow,
        margin: const EdgeInsets.only(top: 26),
        padding: const EdgeInsets.all(16),
        alignment: Alignment.center,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisSize: MainAxisSize.max,
          children: <Widget>[
            Text(
              film.titolo,
              style: (const TextStyle(color: Colors.red, fontSize: 35)),
            ),
            Text(
              film.regista,
              style: (const TextStyle(color: Colors.blue, fontSize: 35)),
            ),
            Text(
              film.anno,
              style: (const TextStyle(color: Colors.black, fontSize: 35)),
            ),
            Image.network(film.img),
          ],
        ),
      ),
    );
  }
}
