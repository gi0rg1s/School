import 'package:flutter/material.dart';

/// Dimostrazione Layout GRID con GridView.builder
/// che genera dinamicamente una Lista di valori in base al numero fornito
void main() {
  runApp(Grigliata2());
}

class Grigliata2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    const title = 'Esempio Grid';

    return MaterialApp(
      title: title,
      home: Scaffold(
        appBar: AppBar(title: const Text(title)),
        body: Column(
          children: <Widget>[
            Expanded(
              /*
                GridView.builder è un widget usato per creare una griglia (grid) di 
                elementi in modo efficiente, caricando solo quelli visibili sullo schermo. 
                Proprietà Principali
                  gridDelegate: Definisce la disposizione della griglia. Può essere:
                    1) SliverGridDelegateWithFixedCrossAxisCount
                      Definisce una griglia con un numero fisso di colonne.
                      La larghezza delle celle viene calcolata automaticamente in base allo spazio disponibile.
                    2) SliverGridDelegateWithMaxCrossAxisExtent
                      Definisce una griglia in cui ogni cella ha una larghezza massima.
                      Flutter calcola automaticamente quante colonne possono stare nella larghezza dello schermo.
                    Nel caso di SliverGridDelegateWithFixedCrossAxisCount:
                    itemCount: Il numero totale di elementi da visualizzare.
                    itemBuilder: Una funzione che costruisce ogni elemento della griglia basandosi sull'indice.
              */
              child: GridView.builder(
                shrinkWrap: true,
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 3,
                  mainAxisSpacing: 8.0,
                  crossAxisSpacing: 8.0,
                ),
                itemCount: 9,
                // Crea una griglia con 3 colonne. Prova a cambiare
                // lo scrollDirection in horizontal e il crossAxisCount = 4

                //scrollDirection: Axis.horizontal,

                /* Il codice della proprietà itemBuilder viene richiamato 9
                    volte (come itemCount) ritornando ogni volta un ElevatedButton.
                    I bottoni verranno allineati in 3 righe come in crossAxisCount
                  */
                itemBuilder: (context, index) {
                  return ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      foregroundColor: Colors.black,
                      backgroundColor: Colors.orangeAccent,
                      /*surfaceTintColor in un ElevatedButton (o in altri widget Material come Card o Dialog) serve a controllare
                        la tinta applicata alla superficie del widget quando è elevato (cioè quando ha un'ombra (attributo elevation)).
                        Aggiunge una sovrapposizione semitrasparente del colore specificato sulla superficie del bottone, influenzando 
                        leggermente il suo colore di fondo (backgroundColor).
                        Oltre alle ombre, viene applicato un tingimento semitrasparente (surfaceTintColor) alla superficie del widget, che
                        simula l'effetto della luce ambiente sul materiale.
                        Aggiunge una leggera sfumatura al colore di base, come se la luce colpisse la superficie da un'angolazione specifica.
                        rendendo l'interfaccia utente più visivamente accattivante.

                        Interazione con backgroundColor:
                          Se non viene specificato, Flutter usa un colore di default basato sul tema (ThemeData.colorScheme.surfaceTint).
                          Se imposti surfaceTintColor: Colors.transparent, disattivi l'effetto di tingimento.
                      */
                      surfaceTintColor: Colors.white,
                      elevation: 12,
                    ),
                    onPressed:
                        () => print("""
                          Hai fatto click su Num: $index; 
                          Riga: ${index ~/ 3 + 1}; 
                          Colonna: ${index % 3 + 1}
                          """),
                    child: Text(index.toString()),
                  );
                },
              ),
            ),
            const Text(
              'Clicca su un bottone e guarda in console',
              textDirection: TextDirection.ltr,
              style: (TextStyle(color: Colors.redAccent, fontSize: 25)),
            ),
          ],
        ),
      ),
    );
  }
}
