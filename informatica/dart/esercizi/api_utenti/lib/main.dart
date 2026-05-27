import 'package:flutter/material.dart';
import 'utente.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

Future<List<Utente>> fetchUtenti() async {
  try {
    final uri = Uri.parse('https://jsonplaceholder.typicode.com/users');

    final response = await http.get(uri, headers: {'Accept': 'application/json'});

    if (response.statusCode == 200) {
      final listaUtentiMap = jsonDecode(response.body);

      return listaUtentiMap.map<Utente>((e) => Utente.fromJson(e)).toList();
    } else {
      throw Exception(
        'Errore HTTP ${response.statusCode}: ${response.reasonPhrase}\n${response.body}',
      );
    }
  } catch (e) {
    throw Exception('Errore di rete/parsing: $e');
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
      title: 'API REST Demo',
      home: const PaginaUtenti(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class PaginaUtenti extends StatefulWidget {
  const PaginaUtenti({super.key});

  @override
  State<PaginaUtenti> createState() => _PaginaUtentiState();
}

class _PaginaUtentiState extends State<PaginaUtenti> {
  Future<List<Utente>>? futuriUtenti;

  @override
  void initState() {
    super.initState();
    //futuriUtenti = fetchUtenti();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Elenco utenti'),
      ),
      body: Column(
        children: [
          Expanded(
            child: FutureBuilder<List<Utente>>(
              future: futuriUtenti,
              builder: (context, snapshot) {
                if (futuriUtenti == null) {
                  return const Center(child: CircularProgressIndicator());
                }
                
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(child: CircularProgressIndicator());
                }
            
                if (snapshot.hasError) {
                  return Center(
                    child: Text('Errore: ${snapshot.error}'),
                  );
                }
            
                if (!snapshot.hasData || snapshot.data!.isEmpty) {
                  return const Center(
                    child: Text('Nessun utente trovato'),
                  );
                }
            
                final utenti = snapshot.data!;
            
                return ListView.builder(
                  itemCount: utenti.length,
                  itemBuilder: (context, index) {
                    final utente = utenti[index];
            
                    return Card(
                      margin: const EdgeInsets.all(8),
                      child: ListTile(
                        leading: CircleAvatar(
                          child: Text(utente.id.toString()),
                        ),
                        title: Text(utente.nome),
                        subtitle: Text('${utente.email}\n${utente.citta}'),
                        isThreeLine: true,
                      ),
                    );
                  },
                );
              },
            ),
          ),
          ElevatedButton(onPressed: () {
            setState(() {
              futuriUtenti = fetchUtenti();
            });
          }, child: const Text('Carica Utenti'))
        ],
      ),
      
    );
  }
}