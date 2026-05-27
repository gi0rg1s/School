import 'package:flutter/material.dart';

/// Ossatura completa di un'app Flutter
/// Il metodo build di StatelessWidget ritorna il widget MaterialApp che
/// incapsula tutta l'applicazione.
/// Material Design è un sistema di progettazione grafico creato da Google per
/// aiutare i programmatori a creare esperienze digitali di alta qualità per
/// Android, iOS, Flutter e il Web.

void main() => runApp(MioWidget());

class MioWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    /*
🔹 Funzioni principali di MaterialApp
      Definisce il tema 🎨
      Puoi personalizzare colori, font e stili attraverso la proprietà theme.

      Gestisce la navigazione 🔄
      Utilizza routes, initialRoute e onGenerateRoute per spostarsi tra le schermate.

      Supporta la localizzazione 🌍
      Configura le lingue e la direzione del testo con locale e supportedLocales.

      Fornisce l'accesso a widget di Material Design 📱
      Attiva elementi come bottoni, app bar, snackbar e molto altro.

      ThemeData:
        Flutter consente di definire temi globali per l'intera applicazione utilizzando ThemeData.
        ThemeData consente di personalizzare colori, stili di testo e altri aspetti visivi.
        ColorScheme:
        All'interno di ThemeData, ColorScheme è utilizzato per definire una serie di colori armonizzati.
     */
    return MaterialApp(
      title: "Material App",
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.deepPurple,
        ), // Crea un ColorScheme da un colore "seme"
        useMaterial3: true,
      ),
      home: LoginPage(),
    );
  }
}

/// 2 modi per ottenere lo stesso risultato widget

class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    /** Il widget Scaffold in Flutter è un contenitore che fornisce una struttura di base per le app con Material Design. 
     * È essenziale per la creazione di un'interfaccia coerente e include componenti predefiniti come 
     * appBar, drawer, floating action button, bottom navigation bar e altro.
    */
    return Scaffold(
      appBar: AppBar(title: const Text('Layout Demo')),
      body: Center(
        // contenitore principale centrato a tutta pagina per 1 child
        /**
         * Container generico con 1 solo child con attributi
         * - padding (spazio tra contenuto)
         * - decoration con relativa shape (forma con bordo)
         * - child
         */
        child: Container(
          padding: const EdgeInsets.all(32),
          decoration: ShapeDecoration(
            color: Colors.green,
            shape: Border.all(color: Colors.black26, width: 2),
          ),
          child: const Text("Login"),
        ),
      ),
    );
  }
}
