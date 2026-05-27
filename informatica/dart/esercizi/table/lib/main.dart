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
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
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
    return;
  }

  int tentativi = 0;
  final List<int> _numbers = [-12, 44, 56, 72, 17, 28, 66, 88, 100];
  final List<bool> _scoperti = [
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
  ];
  int _secretNumber = 0;
  bool _gameOver = false;

  @override
  void initState() {
    super.initState();
    _secretNumber = _numbers[Random().nextInt(_numbers.length)];
  }

  bool checkWin(int index) {
    return (_numbers[index] == _secretNumber) && tentativi < 3;
  }

  bool checkDeath() {
    return tentativi >= 3;
  }

  void noPress() {
    return;
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
              backgroundColor: (_gameOver && _numbers[index] == _secretNumber)
                  ? Colors.red
                  : Colors.white,
              surfaceTintColor: Colors.white,
            ),
            onPressed: _gameOver
                ? () => noPress()
                : () {
                    setState(() {
                      _scoperti[index] = true;
                      if (!checkWin(index))
                        tentativi++;
                      else {
                        _gameOver = true;
                        print("congratulazioni hai vinto!");
                      }
                      if (checkDeath()) {
                        _gameOver = true;
                        print(
                          "mi spiace hai perso!, il numero era $_secretNumber",
                        );
                      }
                    });
                  },
            child: Text(_scoperti[index] ? _numbers[index].toString() : '?'),
          );
        },
      ),
    );
  }
}
