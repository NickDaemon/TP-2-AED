package aed;

public class ListaEnlazada<T> {
    private Nodo primero;
    private int size;

    public class Nodo {
        T valor;
        Nodo siguiente;

        Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }
    
    // Constructor de la clase --> O(1).
    public ListaEnlazada() {
        this.primero = null;
        this.size = 0;
    }
    

    // Agrego un elemento --> O(1).
    public void agregarAdelante(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (primero == null) {
            primero = nuevo;
        } else {
            nuevo.siguiente = primero;
            primero = nuevo;
        }
        this.size++;
    }
    

    // Devuelve la longitud de la lista --> O(1).
    public int obtenerSize() {
        return this.size;
    }
    

    // Devuelve el primer elemento de la lista --> O(1).
    public Nodo obtenerPrimero() {
        return this.primero;
    } 
} 

// Invariante de representacion
    /** 
       * size es siempre mayor o igual a 0.
       * Si size es 0 primero es null.
       * Si size es > 0 , primero es distinto de null.
       * size es igual a la cantidad de elementos de la lista, comenzando con primero.   
    */


   

    
