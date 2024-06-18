package aed;
import aed.DiccionarioDigital;

public class DiccionarioDigitalTests {
    public static void main(String[] args) {
        DiccionarioDigital<Integer> nuevo = new DiccionarioDigital<>();
        nuevo.definir("hola", 1);
        nuevo.definir("adios",2);
        nuevo.definir("bul",4);
        nuevo.definir("bal",5);
        
       
        nuevo.inOrder();
       
        
        
}
}
