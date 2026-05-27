import 'package:flutter/material.dart';

/*
Box Model di Flutter
╔════════════════════════╗  <-- margin (spazio attorno al container)
║   ╔══════════════╗     ║  
║   ║  padding     ║     ║  
║   ║  "Testo"     ║     ║  
║   ╚══════════════╝     ║  
╚════════════════════════╝

📌 Regola generale:
Usa margin per creare spazio tra widget diversi.
Usa padding per dare respiro al contenuto dentro un widget.


📏 Unità di misura principali in Flutter
1️⃣ Logical Pixels (lp o dp - Device-independent Pixels)
  Sono l'unità principale in Flutter.
  Si adattano automaticamente alla densità del display.
  Quando imposti un valore (es. width: 100), stai definendo 100 logical pixels.
  Esempio:
  Container(width: 100, height: 50) // 100x50 logical pixels

2️⃣ Pixel Fisici (Physical Pixels)
  Sono i veri pixel dello schermo e dipendono dalla densità del display.

Formula di conversione:
  Logical Pixels = Physical Pixels / devicePixelRatio
  Esempio:
    Su un dispositivo con devicePixelRatio = 2.0, un widget largo 100 logical pixels occuperà 200 physical pixels.
*/
void main() {
  runApp(
    // Il Container si prende tutto lo spazio disponibile nel widget padre
    // in questo caso il widget padre è l'applicazione stessa
    Container(
      margin: EdgeInsets.all(40), // Spazio ESTERNO di 20px
      padding: EdgeInsets.all(40), // Spazio INTERNO di 10px
      decoration: BoxDecoration(
        color: Colors.blue,
        border: Border.all(color: Colors.redAccent, width: 3),
      ),
      child: Text(
        'Flutter Box Model',
        textDirection: TextDirection.ltr,
        style: TextStyle(color: Colors.white, fontSize: 20),
        textAlign: TextAlign.center,
      ),
    ),
  );
}
