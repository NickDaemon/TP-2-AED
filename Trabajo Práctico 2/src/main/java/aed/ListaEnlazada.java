package aed;

public class ListaEnlazada<T> {
    public Nodo primero;
    public int size;

    public class Nodo {
        T valor;
        Nodo siguiente;

        Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    public ListaEnlazada() {
        this.primero = null;
        this.size = 0;
    }

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

    public int obtenerSize() {
        return this.size;
    }

    public Nodo obtenerPrimero() {
        return this.primero;
    }
    
}

   

    
