/*
  dal sito https://dart.dev/codelabs/async-await
  Provare a cambiare il tempo in secondi del delayed di fetchUserOrder con 2 o 3  
*/
Future<void> printOrderMessage() async {
  print('Awaiting user order...');
  var order = await fetchUserOrder();
  print('Your order is: $order');
}

Future<String> fetchUserOrder() {
  // Imagine that this function is more complex and slow.
  return Future.delayed(const Duration(seconds: 3), () => 'Large Latte');
}

void main() async {
  countSeconds(4);
  printOrderMessage();
}

/*
Perché non serve await o then?
Non c'è bisogno di aspettare un valore di ritorno: Future.delayed(...) viene eseguito e pianificato, ma non restituisce un valore che deve essere usato nel codice successivo.
Il ciclo for è sincrono: Genera subito tutti i Future, che vengono messi in coda ed eseguiti in background senza bloccare il programma principale.
Non c'è bisogno di gestire risultati: La funzione Future.delayed(...) esegue semplicemente print(i), senza dover passare dati ad altre parti del programma.
*/
void countSeconds(int s) {
  for (var i = 1; i <= s; i++) {
    Future.delayed(Duration(seconds: i), () => print(i));
  }
}