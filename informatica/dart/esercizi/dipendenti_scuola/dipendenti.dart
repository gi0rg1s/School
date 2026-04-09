import 'dart:ffi';

abstract class Persona{
  String _nominativo;
  String _sesso;
  DateTime _dataDiNascita;

  Persona([this._nominativo = "", this._sesso = "", DateTime? dataDiNascita])
    : _dataDiNascita = dataDiNascita ?? DateTime.now();

//getters
  String get nominativo => _nominativo;
  String get sesso => _sesso;
  DateTime get dataDiNascita => _dataDiNascita;

  @override
  String toString() {
    return "[nome = $nominativo; sesso = $sesso; data di nascita = $dataDiNascita";
  }
}

class Docente extends Persona{
  Int _livello;
  Float _stipendioBase;

  Docente(this._livello, this._stipendioBase, {String nominativo = "", String sesso = "", DateTime? dataDiNascita})
    : super(nominativo, sesso, dataDiNascita);

  Int get livello => _livello;
  Float get stipendioBase => _stipendioBase;

  @override
  String toString(){
    return ", livello = $livello, stipendio base mensile = $stipendioBase";
  }
}