import 'package:flutter/material.dart';
import 'dart:convert';

String books = '''[
  {
    "Titolo": "La neve in fondo al mare",
    "Autore": "Matteo Bussola",
    "Anno": "2024",
    "ISBN": "9788806260651",
    "Stars": "4",
    "img": "imgs/la_neve_in_fondo_al_mare.jpg"
  },
  {
    "Titolo": "Qualcosa di lilla",
    "Autore": "Maruska Albertazzi",
    "Anno": "2026",
    "ISBN": "9788828219729",
    "Stars": "4",
    "img": "imgs/qualcosa_di_lilla.jpg"
  },
  {
    "Titolo": "Delitto e castigo",
    "Autore": "Fëdor Dostoevskij",
    "Anno": "1866",
    "ISBN": "9780192833839",
    "Stars": "5",
    "img": "imgs/delitto_e_castigo.jpg"
  },
  {
    "Titolo": "Lettera al padre",
    "Autore": "Franz Kafka",
    "Anno": "1919",
    "ISBN": "9788807900310",
    "Stars": "5",
    "img": "imgs/lettera_al_padre.jpg"
  },
  {
    "Titolo": "Il nome della rosa",
    "Autore": "Umberto Eco",
    "Anno": "1980",
    "ISBN": "9788845292613",
    "Stars": "5",
    "img": "imgs/il_nome_della_rosa.jpg"
  },
  {
    "Titolo": "Se questo è un uomo",
    "Autore": "Primo Levi",
    "Anno": "1947",
    "ISBN": "9788806818807",
    "Stars": "5",
    "img": "imgs/se_questo_e_un_uomo.jpg"
  },
  {
    "Titolo": "La coscienza di Zeno",
    "Autore": "Italo Svevo",
    "Anno": "1923",
    "ISBN": "9788807900068",
    "Stars": "4",
    "img": "imgs/la_coscienza_di_zeno.jpg"
  },
  {
    "Titolo": "La metamorfosi",
    "Autore": "Franz Kafka",
    "Anno": "1915",
    "ISBN": "9788807900174",
    "Stars": "5",
    "img": "imgs/la_metamorfosi.jpg"
  },
  {
    "Titolo": "L'\''alchimista",
    "Autore": "Paulo Coelho",
    "Anno": "1988",
    "ISBN": "9788850217397",
    "Stars": "4",
    "img": "imgs/l_alchimista.jpg"
  },
  {
    "Titolo": "Il piccolo principe",
    "Autore": "Antoine de Saint-Exupéry",
    "Anno": "1943",
    "ISBN": "9788845926174",
    "Stars": "5",
    "img": "imgs/il_piccolo_principe.jpg"
  },
  {
    "Titolo": "Anna Karenina",
    "Autore": "Lev Tolstoj",
    "Anno": "1878",
    "ISBN": "9788804668121",
    "Stars": "5",
    "img": "imgs/anna_karenina.jpg"
  },
  {
    "Titolo": "Il fu Mattia Pascal",
    "Autore": "Luigi Pirandello",
    "Anno": "1904",
    "ISBN": "9788804501547",
    "Stars": "4",
    "img": "imgs/il_fu_mattia_pascal.jpg"
  },
  {
    "Titolo": "1984",
    "Autore": "George Orwell",
    "Anno": "1949",
    "ISBN": "9788804730781",
    "Stars": "5",
    "img": "imgs/1984.jpg"
  }
]''';

class Libro {
  final String titolo;
  final String autore;
  final String anno;
  final String isbn;
  final String stars;
  final String img;

  Libro(this.titolo, this.autore, this.anno, this.isbn, this.stars, this.img);

  Libro.fromJson(Map<String, dynamic> json)
      : titolo = json['Titolo'],
        autore = json['Autore'],
        anno = json['Anno'],
        isbn = json['ISBN'],
        stars = json['Stars'],
        img = json['img'];
}

class ListaLibri {
  final List<Libro> listaLibri;
  ListaLibri({required this.listaLibri});

  factory ListaLibri.fromJson(List<dynamic> parsedJson) {
    List<Libro> listaLibri =
        parsedJson.map((libro) => Libro.fromJson(libro)).toList();
    return ListaLibri(listaLibri: listaLibri);
  }
}

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'My favourite books ever',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(title: 'My favourite books ever'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  late ListaLibri listaLibri;

  @override
  void initState() {
    super.initState();
    final parsed = jsonDecode(books) as List<dynamic>;
    listaLibri = ListaLibri.fromJson(parsed);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: ListView.builder(
        padding: const EdgeInsets.all(12),
        itemCount: listaLibri.listaLibri.length,
        itemBuilder: (context, index) {
          final libro = listaLibri.listaLibri[index];
          return _LibroButton(libro: libro);
        },
      ),
    );
  }
}

class _LibroButton extends StatelessWidget {
  final Libro libro;
  const _LibroButton({required this.libro});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6),
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          padding: const EdgeInsets.all(12),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
          alignment: Alignment.centerLeft,
        ),
        onPressed: () {
          showDialog(
            context: context,
            builder: (_) => AlertDialog(
              title: Text(libro.titolo),
              content: Column(
                mainAxisSize: MainAxisSize.min,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Autore: ${libro.autore}'),
                  Text('Anno: ${libro.anno}'),
                  Text('ISBN: ${libro.isbn}'),
                  Text('Stelle: ${'⭐' * int.parse(libro.stars)}'),
                ],
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Text('Chiudi'),
                ),
              ],
            ),
          );
        },
        child: Row(
          children: [
            // Immagine copertina
            ClipRRect(
              borderRadius: BorderRadius.circular(6),
              child: Image.asset(
                libro.img,
                width: 50,
                height: 70,
                fit: BoxFit.cover,
                errorBuilder: (_, __, ___) => Container(
                  width: 50,
                  height: 70,
                  color: Colors.deepPurple.shade100,
                  child: const Icon(Icons.book, color: Colors.deepPurple),
                ),
              ),
            ),
            const SizedBox(width: 16),
            // Info libro
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    libro.titolo,
                    style: const TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 16,
                    ),
                  ),
                  const SizedBox(height: 4),
                  Text(
                    libro.autore,
                    style: TextStyle(
                      color: Colors.grey.shade600,
                      fontSize: 14,
                    ),
                  ),
                  const SizedBox(height: 4),
                  Text(                          // <-- RIMUOVI queste 4 righe
                    '⭐' * int.parse(libro.stars), // <-- 
                    style: const TextStyle(fontSize: 13), // <-- 
                  ),                             // <--
                ],
              ),
            ),
            const Icon(Icons.chevron_right),
          ],
        ),
      ),
    );
  }
}