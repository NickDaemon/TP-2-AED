import java.util.ArrayList;

private class Nodo {
    private ArrayList<Nodo>[] siguientes; 
    private T significado;

    Nodo() {

        siguientes = new ArrayList<Nodo>[256];

        for (int i = 0; i < 256; i++) {
            siguientes[i] = null;
        }
        significado = null;   
    }

}

public class Trie {
    private Nodo raiz;

    public Trie() {
        raiz = new Nodo();
    }


    public void definir(String clave, T valor) {
        int len = clave.length();
        Nodo actual = this.raiz;

        for (int i = 0; i < len; i++) {

            if (actual.siguientes[clave.charAt(i)] != null) {
                actual = actual.siguientes[clave.charAt(i)];

            } else {
                Nodo nuevo = new Nodo();
                actual.siguientes[clave.charAt(i)] = nuevo;
                actual = nuevo;
            }
            
        }
        actual.significado = valor;
    } 








}