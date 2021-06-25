public class HiWorld {

}
class Hola extends HiWorld{
    public static void main(String[] args){
        Hola ob =new Hola(); //object of Hola created
        ob.message(); //method messages() is called on this object of Hola
    }

    public HiWorld message()
    {
        System.out.println("Hello World");
        return this; //currently executing object of B type is returned from a method with superclass return type.
    }
}
