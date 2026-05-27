import 'package:flutter/material.dart';
/*
L'uso di un widget Expanded fa sì che un widget di una Riga o di una Colonna si 
espanda per riempire lo spazio disponibile nell'asse principale ( orizzontalmente 
per una Row o verticalmente per una Column). 
Se si espandono più widget (children), lo spazio disponibile viene diviso tra loro in base al 
fattore flex.
Nell'esempio ci sono righe con 3 Container con incluso un Text.
Normalmente Row e Column dividono lo spazio libero in base alle dimensioni dei 
widget figli che normalmente non si adattano (flessibilità).
Se si vuole che alcuni widget riempiano lo spazio extra, allora si avvolgono in
widget Expanded.
Senza fornire alcuna proprietà aggiuntiva flex, i widget espansi utilizzano tutto 
lo spazio rimanente allo stesso modo.
Se si vuole vuoi dare una proporzione differente per i vari Expanded si può usare 
il fattore flex.

L'attributo flex di Expanded controlla quanto spazio un widget deve occupare 
all'interno di un Row, Column o Flex, rispetto agli altri widget con proprietà flex.

https://www.geeksforgeeks.org/flutter-flexible-widget/#:~:text=Flexible%20is%20a%20built%2Din,the%20default%20fit%20of%20FlexFit.
https://medium.com/@apmntechdev/flutter-expanded-and-flex-cfd4e9f1e069
https://itnext.io/flutter-responsive-apps-flexible-vs-expanded-ff8cc92b468f
*/

void main() => runApp(MioWidget());

class MioWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Responsive",
      theme: ThemeData(
        primarySwatch: Colors.blue,
        canvasColor: const Color.fromARGB(255, 100, 230, 230),
      ),
      home: Responsive(),
    );
  }
}

class Responsive extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Responsive')),
      body: Column(
        children: [
          Row(
            children:[
              Container(
                padding: const EdgeInsets.all(12),
                color: Colors.green,
                child: const Text("A"),
              ),
              Container(
                padding: const EdgeInsets.all(12),
                color: Colors.white,
                child: const Text("B"),
              ),
              Container(
                padding: const EdgeInsets.all(12),
                color: Colors.red,
                child: const Text("C"),
              ),
            ],
          ),
          const Spacer(),
          Row(
            children: <Widget>[
              Container(
                padding: const EdgeInsets.all(12),
                color: Colors.green,
                child: const Text("A"),
              ),
              Container(
                padding: const EdgeInsets.all(12),
                color: Colors.white,
                child: const Text("B"),
              ),
              Expanded(
                child: Container(
                  padding: const EdgeInsets.all(12),
                  color: Colors.red,
                  child: const Text("C"),
                ),
              ),
            ],
          ),
          const Spacer(),
          Row(
            children: <Widget>[
              Container(
                padding: const EdgeInsets.all(12),
                color: Colors.green,
                child: const Text("A"),
              ),
              Expanded(
                child: Container(
                  padding: const EdgeInsets.all(12),
                  color: Colors.white,
                  child: const Text("B"),
                ),
              ),
              Expanded(
                child: Container(
                  padding: const EdgeInsets.all(12),
                  color: Colors.red,
                  child: const Text("C"),
                ),
              ),
            ],
          ),
          const Spacer(),
          Row(
            children: <Widget>[
              Expanded(
                flex: 1,
                child: Container(
                  padding: const EdgeInsets.all(12),
                  color: Colors.green,
                  child: const Text("A"),
                ),
              ),
              // Con 3 expanded con flex a 1 2 3 lo spazio viene diviso in 6 parti
              Expanded(
                flex: 2,
                child: // si espande per 2/6 parti nello spazio libero
                    Container(
                  padding: const EdgeInsets.all(12),
                  color: Colors.white,
                  child: const Text("B"),
                ),
              ),
              Expanded(
                flex: 3,
                child: // si espande per 3/6 parti nello spazio libero
                    Container(
                  padding: const EdgeInsets.all(12),
                  color: Colors.red,
                  child: const Text("C"),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
