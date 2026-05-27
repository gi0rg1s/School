// Minimal pure Dart console program — no Flutter dependencies

void main() {
  final items = List.generate(20, (i) => 'Item ${i + 1}');
  print('GridView Count (console)');
  for (var i = 0; i < items.length; i += 2) {
    final left = items[i];
    final right = (i + 1 < items.length) ? items[i + 1] : '';
    print('${left.padRight(12)}  ${right}');
  }
}