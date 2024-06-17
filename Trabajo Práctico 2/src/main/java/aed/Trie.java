package aed;

public interface Trie<T> {
    /**
     * Inserta un valor T en el Trie en la clave pasada como argumento.
     */
    void definir(String clave, T valor);
    
    /**
     * Busca y retorna el valor asociado a la clave especificada en el Trie.
     * Si la clave no se encuentra devuelve null.
     */
    T obtener(String clave);
    
    /**
     * Elimina la clave y su valor asociado del Trie.
     */
    void eliminar(String clave);
    
    
}