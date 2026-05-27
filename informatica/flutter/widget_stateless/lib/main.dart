import 'package:flutter/material.dart';

/// Codice minimale per creare un'app con widget senza stato
void main() {
  runApp(

    Container(
      margin: EdgeInsets.all(40),
      padding: EdgeInsets.all(40),
      decoration: BoxDecoration(
        color: const Color.fromARGB(255, 34, 34, 255),
        border: Border.all(color: Colors.redAccent, width: 3)
      ),
    
    child:Text(
      'Stack EMU8086',
      textDirection: TextDirection.ltr,
      style: TextStyle(color: const Color.fromARGB(255, 15, 202, 115)),
      textAlign: TextAlign.center,
      ),
    ),
  );
}
