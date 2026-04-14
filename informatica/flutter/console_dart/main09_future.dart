import 'dart:async';

/// Dimostra come il metodo .then() di un Future sia il ritorno asincrono
/// di una chiamata che ha avuto termine/risposta
/// Nelle chiamate NON C'è await come gli esempi precedenti
/// then vuole un parametro funzione (callback) che verrà invocata quando il
/// processo asincrono sarà completato. In questo esempio infatti la funzione
/// fetchOrdineUtente non restituisce niente perché il risultato
/// è gestito direttamente nella callback di then

void stampaOrdine(String ordine) {
  print(ordine);
}

void fetchOrdineUtente() =>
    Future.delayed(Duration(seconds: 5), () => "Cavolfiore")
        .then((valore) => stampaOrdine(valore));

// metodo equivalente alternativo al precedente
void fetchOrdineUtente2() {  
  Future f = Future.delayed(Duration(seconds: 5), () => "Cavolfiore");
  f.then((valore) => stampaOrdine(valore));
}

void creaOrdineAsincrono() {
  fetchOrdineUtente2();
}

void main() {
  print("\nAttendi ordine...");

  /// ritorna String: 'Tuo ordine: 'Cavolfiore' perché il Future è completato
  /// in quanto con .then si è atteso il completamento asincrono del processo
  print("Ordine:");
  fetchOrdineUtente2();
}
