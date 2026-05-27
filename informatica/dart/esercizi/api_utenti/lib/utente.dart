class Utente {
  final int id;
  final String nome;
  final String email;
  final String citta;

  Utente({
    required this.id,
    required this.nome,
    required this.email,
    required this.citta,
  });

  factory Utente.fromJson(Map<String, dynamic> json) {
    return Utente(
      id: json['id'],
      nome: json['name'],
      email: json['email'],
      citta: json['address']['city'],
    );
  }
}