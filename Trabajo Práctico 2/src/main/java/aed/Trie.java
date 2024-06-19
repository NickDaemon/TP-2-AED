package aed;

import java.util.ArrayList;

public class Trie<T> {
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
    public Trie(){
        _raiz = new NodoTrie<>();
        _size = 0;
    }

  
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

            
        
        // Si tiene hijo ,me muevo a ese nodo.
        } else {
            actual = actual.siguientes.get(posicion);
        }      
    }
    // Una vez que agregue todos los caracteres actualizo el valor y agrando _size.
    this._size += 1;
    actual.valor = valor;   
    }

  
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
    // Si la clave estaba devolvera el valor de su significado.
    return actual.valor;
}


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
    // Si j no es 256 es por que tiene hijos , cambio el valor del nodo a null y retorno.
    if (j != 256) {
        nodo.valor = null;
        this._size -= 1;
        return;

    // Si tiene hijos y nodo padre no es null elimino el nodo actual.
    } else if (nodo.padre != null) {
        nodo.padre.siguientes.set(nodo.indice, null);

        // Si nodo padre tiene valor ,eliminar termina ahi.
        if (nodo.padre.valor != null) {
            this._size -= 1;
            return;

        // Si no tiene valor salto a padre y recursivamente elimino los necesarios.
        } else {
            eliminarNodo(nodo.padre);
        }

    // Si llego aca estoy en raiz puedo retornar ya que abre terminado.
    } else {
        this._size -= 1;
        return;
    }
    }

    public String[] inOrder() {
        String[] claves = new String[_size];
        String palabra = "";
        Integer index = 0;
        int actual = 0;
        
        coleccionar(claves,palabra,index,actual,this._raiz);

        for (int i = 0; i < claves.length; i++) {
            System.out.println("La clave es: " + claves[i]);
        }

     
        return claves;
    }
    
    public void coleccionar(String[] claves, String palabra, Integer index, int actual , NodoTrie<T> nodo){
    
    while (actual < 256 && nodo.siguientes.get(actual) == null) {
          actual += 1;
    }
    // Si nodo no tiene hijos y padre es null entonces estoy en raiz sin hijos, puedo retornar.
    if (actual == 256 && nodo.padre == null) {
        return;
    
    // Si nodo no tiene hijos pero su padre no es null retrocedo y vuelvo a empezar.
    // Pero ahora busco en padre,los hijos mayores a mi posicion.
    } else if (actual == 256 && nodo.padre != null) {
        actual = nodo.indice + 1;

        // "Desapilo" mi letra de palabra.
        if (!palabra.isEmpty()) {
            palabra = palabra.substring(0, palabra.length() - 1);
        }
        coleccionar(claves, palabra, index, actual, nodo.padre);

    // Si nodo tiene hijo y ese hijo no tiene valor , apilo al hijo en palabra
    // Y me muevo a ese hijo.
    } else if (actual != 256 && nodo.siguientes.get(actual).valor == null) {
        int letra = actual;
        palabra += (char) letra;
        actual = 0;
        coleccionar(claves, palabra, index, actual, nodo.siguientes.get(letra));

    // Si nodo tiene hijo y tiene valor lo apilo en palabra lo agrego a string[]
    // Y vuelvo a empezar
    } else {
        int letra = actual;
        palabra += (char) letra;
        claves[index] = palabra;
        index += 1;
        actual = 0;
        coleccionar(claves, palabra, index, actual, nodo.siguientes.get(letra));

    }
}
}
     

    

