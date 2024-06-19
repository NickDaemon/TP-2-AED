package aed;


public class DiccionarioDigitalTests {
    public static void main(String[] args) {
        Trie<Integer> nuevo = new Trie<>();
        nuevo.definir("hola", 1);
        nuevo.definir("adios",2);
        nuevo.definir("bul",4);
        nuevo.definir("bal",5);
        
       
        nuevo.inOrder();
       
        
        
}
}
