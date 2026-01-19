public class VeicoloAnfibio implements Volante, Nuotante{
    private String nome;

    //constructor
    public VeicoloAnfibio(String nome){
        this.nome = nome;
    }

    public void vola(){
        System.out.println(nome + " sta volando!\n");
    }

    public void nuota(){
        System.out.println(nome + " sta nuotando!\n");
    }
}
