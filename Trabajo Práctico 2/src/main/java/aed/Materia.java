package aed;

public class Materia {
    private ListaEnlazada<String> alumnos;
    private int[] docentes;
    private ListaEnlazada<infoIguales> direcciones;

    
/** Invariante de Representacion:
 
     * 'alumnos' es siempre distinto de null.
     * No hay repetidos en 'alumnos'.
     * 'docentes' no es null y siempre tiene 4 elementos.
     * Los valores de 'docentes' son siempre mayores o iguales a 0.
     * 'direcciones' nunca es null.
     
*/

    // Constructor de clase Materia --> O(1)
    public Materia(){
        this.alumnos = new ListaEnlazada<>();
        this.docentes = new int[4];
        this.direcciones = new ListaEnlazada<>();
    }
    
    // Devuelve la lista de alumnos de la materia. --> O(1)
    public ListaEnlazada<String> obtenerAlumnos(){
        return this.alumnos;
    }
    
    // Devuelve al plantel docente. --> O(1)
    public int[] obtenerPlantel(){
        return this.docentes;
    }

    // Devuelve el mapa de todas las carreras que contienen a Materia
    // Junto con su respectiva clave para encontrarla. --> O(1)
    public ListaEnlazada<infoIguales> obtenerIguales(){
        return this.direcciones;
    }

    // Agregamos un alumno a la Materia --> O(1)
    public void inscribirAlumno(String LU){
        this.alumnos.agregarAdelante(LU);
    }

    // Agregamos un docente al plantel de la Materia --> O(1)
    public void inscribirDocente(String cargo){
        if (cargo.equals("PROF")) {
            this.docentes[0] += 1;

        } else if (cargo.equals("JTP")) {
            this.docentes[1] += 1;

        } else if (cargo.equals("AY1")) {
            this.docentes[2] += 1;

        } else if (cargo.equals("AY2")) {
            this.docentes[3] += 1;

        } else {
            System.out.println("No corresponde a un cargo docente.");
        }      
        }

    // Agregamos otra carrera que tenga la misma materia
    // y su clave correspondiente --> O(1)
    public void guardarDireccion(Trie<Materia> otraCarrera, String clave){
        
        infoIguales direccion = new infoIguales(otraCarrera, clave);
        this.direcciones.agregarAdelante(direccion);
    }           
    }



// Complejidades:
/** 
    * Como en esta clase solo agregamos elementos a una lista enlazada 
    * O devolvemos la lista enlazada (o arreglo) , todos los metodos 
    * tienen complejidad O(1).
*/

