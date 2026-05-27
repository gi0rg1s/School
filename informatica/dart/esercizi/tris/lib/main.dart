import 'package:flutter/material.dart';
import 'dart:math';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(colorScheme: .fromSeed(seedColor: Colors.deepPurple)),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
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
  final List<String> bottoni = List.generate(9, (index) => 'assets/Empty.png');
  String player = Random().nextBool() ? 'assets/circle.png' : 'assets/x.png';

  void _handleButtonPress(int index) {
    if (bottoni[index] == 'assets/Empty.png' && !checkWin()) {
      setState(() {
        bottoni[index] = player;
        player = (player == 'assets/x.png' ? 'assets/circle.png' : 'assets/x.png');
      });
    }
  }

  bool checkWin(){
    return(
      (bottoni[0] == bottoni[1] && bottoni[1] == bottoni[2] && bottoni[0] != 'assets/Empty.png')
      || (bottoni[3] == bottoni[4] && bottoni[4] == bottoni[5] && bottoni[3] != 'assets/Empty.png')
      || (bottoni[6] == bottoni[7] && bottoni[7] == bottoni[8] && bottoni[6] != 'assets/Empty.png')
      || (bottoni[0] == bottoni[3] && bottoni[3] == bottoni[6] && bottoni[0] != 'assets/Empty.png')
      || (bottoni[1] == bottoni[4] && bottoni[4] == bottoni[7] && bottoni[1] != 'assets/Empty.png')
      || (bottoni[2] == bottoni[5] && bottoni[5] == bottoni[8] && bottoni[2] != 'assets/Empty.png')
      || (bottoni[0] == bottoni[4] && bottoni[4] == bottoni[8] && bottoni[0] != 'assets/Empty.png')
      || (bottoni[2] == bottoni[4] && bottoni[4] == bottoni[6] && bottoni[2] != 'assets/Empty.png'));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GridView.builder(
        padding: const EdgeInsets.all(16.0),
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 3,
          mainAxisSpacing: 8.0,
          crossAxisSpacing: 8.0,
        ),
        itemCount: 9,
        itemBuilder: (context, index) {
          return ElevatedButton(
            style: ElevatedButton.styleFrom(
              foregroundColor: Colors.black,
              backgroundColor: const Color.fromARGB(255, 236, 188, 231),
              surfaceTintColor: Colors.white,
            ),
            onPressed: () => _handleButtonPress(index),
            child: Image.asset(bottoni[index]),
          );
        },
      ),
    );
  }
}
