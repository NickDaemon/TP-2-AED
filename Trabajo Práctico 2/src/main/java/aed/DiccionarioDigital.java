package aed;

import java.util.ArrayList;

public class DiccionarioDigital<T> implements Trie<T> {
    private NodoTrie<T> _raiz;
    private int _size;
    
    public class NodoTrie<N> {
        // Hijos.
        ArrayList<NodoTrie<N>> siguientes;

        // Padre.
        NodoTrie<N> padre;

        // Que hijo es ese nodo.
        int indice;

        // Significado.
        N valor;

        NodoTrie() {
            siguientes = new ArrayList<>();
            for (int i = 0; i < 256; i++) {
                siguientes.add(null);
            }
            padre = null;
            // Un nodo nuevo no es hijo de nadie , inicia con un indice invalido.
            indice = -1;
            valor = null;
        }
    }

    // Constructor
    public DiccionarioDigital(){
        _raiz = new NodoTrie<>();
        _size = 0;
    }

    @Override
    public void definir(String clave, T valor) {
    // Actual me dice en que nodo me encuentro.
    NodoTrie<T> actual = this._raiz;

    // Voy a ir iterando sobre cada caracter de la clave.
    for (int i = 0; i < clave.length(); i++) {

        // Posicion indica que "hijo" debo mirar.
        int posicion = (int) clave.charAt(i);
        
        // Sino tiene hijo ,lo creo y actualizo los datos.
        if (actual.siguientes.get(posicion) == null) {

            NodoTrie<T> nuevo = new NodoTrie<>();
            nuevo.padre = actual;
            nuevo.indice = posicion;

            actual.siguientes.set(posicion,nuevo);
            actual = nuevo;

            this._size += 1;
        
        // Si tiene hijo ,me muevo a ese nodo.
        } else {
            actual = actual.siguientes.get(posicion);
        }      
    }
    // Una vez que agregue todos los caracteres actualizo el valor.
    actual.valor = valor;

    
        
    }

    @Override
    public T obtener(String clave) {
    NodoTrie<T> actual = this._raiz;
    
    // Me muevo por los caracteres de la clave
    for (int i = 0; i < clave.length(); i++) {
        int posicion = (int) clave.charAt(i);
        
        // Si ese caracter no está, devuelvo null.
        if (actual.siguientes.get(posicion) == null) {
            return null;

        } else {
            actual = actual.siguientes.get(posicion);
        }
    }
    // Si la clave estaba devuelvo su valor.
    return actual.valor;
}

    @Override
    public void eliminar(String clave) {
    // Busco el nodo a eliminar desde la raiz.
    NodoTrie<T> actual = this._raiz;

    // Itero sobre la longitud de la clave.
    for (int i = 0; i < clave.length(); i++) {
        int posicion = (int) clave.charAt(i);
        
        // Si la clave a eliminar no estaba retorno.
        if (actual.siguientes.get(posicion) == null) {
            return;
        } else {
            actual = actual.siguientes.get(posicion);
        }
    }
    // Actual es el nodo que quiero eliminar
    eliminarNodo(actual);
    }

    public void eliminarNodo(NodoTrie<T> nodo) {
    int j = 0;

    // Uso un ciclo para encontrar hijos
    for (int i = 0; i < 256; i++) {
        if (nodo.siguientes.get(i) == null) {
            j += 1;
        }
    }
    // Si j no es 256 es por que tiene hijos , cambio su valor a null.
    if (j != 256) {
        nodo.valor = null;
        return;
    // Si nodo padre no es null elimino el nodo actual.
    } else if (nodo.padre != null) {
        nodo.padre.siguientes.set(nodo.indice, null);

        // Si nodo padre tiene valor ,eliminar termina ahi.
        if (nodo.padre.valor != null) {
            return;

        // Si no tiene valor salto a padre y recursivamente elimino los necesarios.
        } else {
            eliminarNodo(nodo.padre);
        }
    // Si llego aca estoy en raiz puedo retornar ya que abre terminado.
    } else {
        return;
    }
    }
    
}
     

    

