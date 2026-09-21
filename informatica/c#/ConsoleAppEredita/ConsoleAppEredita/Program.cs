using System.ComponentModel;

SuperClass superClass = new SuperClass();
superClass.Attributo = 1;   //esegue lui il set
Console.WriteLine(superClass.Attributo);
superClass.Metodo();
//SottoClass sottoClass = new SottoClass();
SottoClass sottoClass = new SottoClass { SottoAttributo = 2, Attributo = 1 };
sottoClass.Metodo();

//polimorfismo
List<SuperClass> list = new List<SuperClass>();
list.Add(superClass);
list.Add(sottoClass);

Console.WriteLine("polimorfismo: ");


//binding dinamico --> quello che avrebbe fatto java
//per averlo anceh in c# dobbiamo mettere la parola dynamic
foreach (SuperClass s in list) { 
    s.Metodo();
}

public class SuperClass
{
    private int attributo;

    //constructor
    public SuperClass()
    {
    }

    //constructor
    public SuperClass(int attributo)
    {
        this.Attributo = attributo;
    }

    //non è un metodo è una property e sostituisce i vecchi getter e setter di java
    //get con la lambda expression restituisce l'attributo, il setter, sempre con la lambda expression
    //asseggna all'attributo il valore 
    public int Attributo { get => attributo; set => attributo = value; }

    public virtual void Metodo()
    {
        Console.WriteLine($"ciao amoooo {Attributo}");
    }
}

public class SottoClass : SuperClass
{
    int sottoAttributo;
    public SottoClass()
    {
    }
    /// <summary>
    /// 
    /// </summary>
    /// <param name="attributo"></param>

    //costruttore con attributo in ingresso che sfrutta le lambda expression
    public SottoClass(int attributo, int sottoAttributo) : base(attributo)  //molto più sintetica
    {
        this.Attributo = attributo; //stessa cosa di linea 40 ma con sintassi alla java
        SottoAttributo = sottoAttributo;
    }

    public int SottoAttributo { get => sottoAttributo; set => sottoAttributo = value; }

    public override void Metodo() {
        Console.WriteLine($"ciao amoooo come stai? {sottoAttributo}");
    }
}