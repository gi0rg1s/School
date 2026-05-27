import 'package:flutter/material.dart';

void main() {
  runApp(const Griglia());
}

class Griglia extends StatelessWidget {
  const Griglia();

  @override
  Widget build(BuildContext context) {
    const title = 'FOTO DI MIRA';

    return MaterialApp(
      title: title,
      home: Scaffold(
        appBar: AppBar(title: const Text(title)),
        body: GridView.count(
          crossAxisCount: 2,
          padding: const EdgeInsets.all(20),
          crossAxisSpacing: 10,
          mainAxisSpacing: 10,
          children: <Widget>[
            Contenitore(img: 'lib/fotoMira/mira1.jpeg'),
            Contenitore(img: 'lib/fotoMira/mira2.jpeg'),
            Contenitore(img: 'lib/fotoMira/mira3.jpeg'),
            Contenitore(img: 'lib/fotoMira/mira4.jpeg'),
            Contenitore(img: 'lib/fotoMira/mira5.jpeg'),
            Contenitore(img: 'lib/fotoMira/mira6.jpeg'),
          ],
        ),
      ),
    );
  }
}

class Contenitore extends StatelessWidget {
  final String img;
  final int color;

  const Contenitore({required this.img, this.color = 400,});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8),
      ),
      child: Image.asset(
        img,
        fit: BoxFit.cover,
      ),
    );
  }
}