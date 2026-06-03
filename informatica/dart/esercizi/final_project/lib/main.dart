import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

//tite lists for the home page
const List<String> classici = [
  'Delitto e castigo',
  'Anna Karenina',
  '1984',
  'Il piccolo principe',
  'La metamorfosi',
  'I promessi sposi',
  'uno, nessuno e centomila',
  'Lettera al padre', 
  'Dead poets society',
  'Il barone rampante'
];

//book class
class Libro {
  final String titolo;
  final String autore;
  final String anno;
  final String isbn;
  final String stars;
  final String img;

  Libro(this.titolo, this.autore, this.anno, this.isbn, this.stars, this.img);
}

//function that search books by title or author
//it does an async call so that we can return a future object
Future<List<Libro>> cercaLibri(String query) async {
  final uri = Uri.parse(
    //url for the api with limitations )return the first 5 searches not more)
    'https://openlibrary.org/search.json?q=${Uri.encodeComponent(query)}&limit=5&fields=title,author_name,first_publish_year,isbn',
  );
  //get request with http response 
  final response = await http.get(uri, headers: {
    'User-Agent': 'BookSearchApp (flutter-app)',
  });

  if (response.statusCode != 200) return [];

//converts thhe JSON response to a dart map
  final data = jsonDecode(response.body);
  final docs = (data['docs'] as List<dynamic>?) ?? [];

//we create a book object for every result
  return docs.map((doc) {
    final isbn = (doc['isbn'] as List<dynamic>?)?.firstOrNull?.toString() ?? '';
    return Libro(
      doc['title'] ?? 'Titolo sconosciuto',
      (doc['author_name'] as List<dynamic>?)?.join(', ') ?? 'Autore sconosciuto',
      doc['first_publish_year']?.toString() ?? '—',
      isbn,
      '0',
      //ISBN is fudamental to get the book cover
      isbn.isNotEmpty ? 'https://covers.openlibrary.org/b/isbn/$isbn-M.jpg' : '',
    );
  }).toList();
}

//load the books in the home page
Future<List<Libro>> caricaClassici() async {
  final futures = classici.map((titolo) async {
    try {
      final risultati = await cercaLibri(titolo);
      //if i can find the title i will load the first result
      return risultati.isNotEmpty ? risultati.first : null;
    } catch (_) {
      return null;
    }
  });
  final risultati = await Future.wait(futures);   //future.wait launch all the requests simultaneusly
  return risultati.whereType<Libro>().toList();
}
void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Book Search',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const SearchPage(),
    );
  }
}

class SearchPage extends StatefulWidget {
  const SearchPage({super.key});

  @override
  State<SearchPage> createState() => _SearchPageState();
}

//page state
class _SearchPageState extends State<SearchPage> {
  final _controller = TextEditingController();
  late Future<List<Libro>> _futureClassici;     //initial classics
  List<Libro> _risultatiRicerca = [];           //research results 
  bool _loading = false;
  bool _haRicercato = false;
  String? _errore;

  @override
  void initState() {
    super.initState();
    _futureClassici = caricaClassici();
  }

  //search book by title or author
  //if success or failure the loading status will always return to false
  Future<void> _cerca() async {
    final query = _controller.text.trim();
    if (query.isEmpty) return;
    
    setState(() {
      _loading = true;
      _errore = null;
      _haRicercato = true;
      _risultatiRicerca = [];
    });

    try {
      final risultati = await cercaLibri(query);
      setState(() => _risultatiRicerca = risultati);
    } catch (e) {
      setState(() => _errore = 'Errore durante la ricerca.');
    } finally {
      setState(() => _loading = false);
    }
  }

  void _resetRicerca() {
    setState(() {
      _haRicercato = false;
      _risultatiRicerca = [];
      _controller.clear();
    });
  }

  @override
  //graphic interface
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Book Search'),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: const EdgeInsets.all(12),
            child: Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: _controller,
                    //input search bar
                    decoration: const InputDecoration(
                      hintText: 'Cerca un libro...',
                      border: OutlineInputBorder(),
                    ),
                    onSubmitted: (_) => _cerca(),
                  ),
                ),
                const SizedBox(width: 8),
                ElevatedButton(
                  onPressed: _loading ? null : _cerca,
                  child: const Text('Cerca'),
                ),
                if (_haRicercato) ...[
                  const SizedBox(width: 8),
                  IconButton(
                    onPressed: _resetRicerca,
                    icon: const Icon(Icons.close),
                    tooltip: 'Torna ai classici',
                  ),
                ],
              ],
            ),
          ),
          if (_haRicercato) ...[
            if (_loading)
              const Expanded(child: Center(child: CircularProgressIndicator()))
            else if (_errore != null)
              Expanded(child: Center(child: Text(_errore!)))
            else if (_risultatiRicerca.isEmpty)
              const Expanded(child: Center(child: Text('Nessun risultato.')))
            else
              Expanded(
                child: ListView.builder(
                  padding: const EdgeInsets.symmetric(horizontal: 12),
                  itemCount: _risultatiRicerca.length,
                  itemBuilder: (context, index) =>
                      _LibroTile(libro: _risultatiRicerca[index]),
                ),
              ),
          ] else ...[
            const Padding(
              padding: EdgeInsets.symmetric(horizontal: 16, vertical: 4),
              child: Text(
                'Classici da scoprire',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
            ),
            Expanded(
              child: FutureBuilder<List<Libro>>(
                future: _futureClassici,
                builder: (context, snapshot) {
                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return const Center(child: CircularProgressIndicator());
                  }
                  if (snapshot.hasError) {
                    return const Center(child: Text('Errore nel caricamento.'));
                  }
                  final libri = snapshot.data ?? [];
                  return ListView.builder(
                    padding: const EdgeInsets.symmetric(horizontal: 12),
                    itemCount: libri.length,
                    itemBuilder: (context, index) =>
                        _LibroTile(libro: libri[index]),
                  );
                },
              ),
            ),
          ],
        ],
      ),
    );
  }
}

//main screen
class _LibroTile extends StatelessWidget {
  final Libro libro;
  const _LibroTile({required this.libro});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6),
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          padding: const EdgeInsets.all(12),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
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
                  if (libro.isbn.isNotEmpty) Text('ISBN: ${libro.isbn}'),
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
            ClipRRect(
              borderRadius: BorderRadius.circular(6),
              child: libro.img.isNotEmpty
                  ? Image.network(
                      libro.img,
                      width: 50,
                      height: 70,
                      fit: BoxFit.cover,
                      errorBuilder: (_, __, ___) => _placeholder(),
                    )
                  : _placeholder(),
            ),
            const SizedBox(width: 16),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(libro.titolo,
                      style: const TextStyle(
                          fontWeight: FontWeight.bold, fontSize: 16)),
                  const SizedBox(height: 4),
                  Text(libro.autore,
                      style: TextStyle(
                          color: Colors.grey.shade600, fontSize: 14)),
                  const SizedBox(height: 4),
                  Text(libro.anno,
                      style: TextStyle(
                          color: Colors.grey.shade500, fontSize: 13)),
                ],
              ),
            ),
            const Icon(Icons.chevron_right),
          ],
        ),
      ),
    );
  }

//widget that contains the book
  Widget _placeholder() => Container(
        width: 50,
        height: 70,
        color: Colors.deepPurple.shade100,
        child: const Icon(Icons.book, color: Colors.deepPurple),
      );
}