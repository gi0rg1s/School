import 'package:flutter/material.dart';

void main() {
  runApp(GrigliaBuilder());
}

class GrigliaBuilder extends StatelessWidget {

  static const List<String> _immagini = <String>[
    'lib/fotoMira/mira7.jpeg',
    'lib/fotoMira/mira8.jpeg',
    'lib/fotoMira/mira9.jpeg',
    'lib/fotoMira/mira10.jpeg',
    'lib/fotoMira/mira11.jpeg',
    'lib/fotoMira/mira12.jpeg',
  ];

  @override
  Widget build(BuildContext context) {
    const title = 'FOTO DI MIRA - BUILDER';

    return MaterialApp(
      title: title,
      home: Scaffold(
        appBar: AppBar(title: const Text(title)),
        body: GridView.builder(
          padding: const EdgeInsets.all(20),
          itemCount: _immagini.length,
          gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            crossAxisSpacing: 10,
            mainAxisSpacing: 10,
          ),
          itemBuilder: (BuildContext context, int index) {
            return Contenitore(img: _immagini[index]);
          },
        ),
      ),
    );
  }
}

class Contenitore extends StatelessWidget {
  final String img;

  const Contenitore({super.key, required this.img});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Image.asset(
        img,
        fit: BoxFit.cover,
      ),
    );
  }
}
