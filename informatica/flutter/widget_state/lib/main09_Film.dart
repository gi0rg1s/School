class Film {
  final String titolo;
  final String regista;
  final String anno;
  final String img;

  Film({required this.titolo, required this.regista, required this.anno, required this.img});

  factory Film.fromJson(Map<String, dynamic> json) {
    return Film(
        titolo: json['Titolo'],
        regista: json['Regista'],
        anno: json['Anno'],
        img: json["Img"]);
  }
}