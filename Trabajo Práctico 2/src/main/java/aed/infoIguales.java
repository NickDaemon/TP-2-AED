package aed;

public class infoIguales {
    public Trie<Materia> otraCarrera;
    public String otraClave;

    // Constructor de la clase --> O(1)
    public infoIguales(Trie<Materia> direccion, String clave){
        this.otraCarrera = direccion;
        this.otraClave = clave;
    }

    public Trie<Materia> otraDireccion(){
        return this.otraCarrera;
    }
    public String claveDireccion(){
        return this.otraClave;
    }

    // Invariante de representacion
    /** 
        *"otraClave" debe ser una clave valida del Trie "otraCarrera"
    */
}
