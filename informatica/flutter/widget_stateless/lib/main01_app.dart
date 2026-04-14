import 'package:flutter/material.dart';

/// Il metodo runApp() di main istanzia un widget senza stato
void main() {
  runApp(MioWidget());
}

class MioWidget extends StatelessWidget {
  //final perchè in StatelessWidget l'attributo deve essere immutabile
  final String testo = "Viva Flutter";

  /// Un widget Stateless DEVE implementare il metodo build che deve includere
  /// tutto il codice per la creazione del widget stesso
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        testo,
        textDirection: TextDirection.ltr,
        style: const TextStyle(color: Colors.yellow),
      ),
    );
  }
}
