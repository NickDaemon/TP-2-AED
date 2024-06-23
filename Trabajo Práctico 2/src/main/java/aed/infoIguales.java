package aed;

public class infoIguales {
    Trie<Materia> otraCarrera;
    String otraClave;

    // Invariante de representacion
    /** 
        * 'otraCarrera' no debe ser null.
        * 'otraClave' no debe ser null.
        * 'otraClave' debe ser una clave valida del Trie 'otraCarrera'.
    */

    // Constructor de la clase --> O(1)
    public infoIguales(Trie<Materia> direccion, String clave){
        this.otraCarrera = direccion;
        this.otraClave = clave;
    }
    
    // Accedo al trie 'otraCarrera' --> O(1).
    public Trie<Materia> otraDireccion(){
        return this.otraCarrera;
    }

    // Accedo a la clave que voy a buscar en el trie 'otraCarrera' --> O(1).
    public String claveDireccion(){
        return this.otraClave;
    }

    
}
