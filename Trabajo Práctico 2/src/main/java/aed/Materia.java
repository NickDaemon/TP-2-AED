package aed;

public class Materia {
    private ListaEnlazada<String> alumnos;
    private int[] docentes;
    private ListaEnlazada<infoIguales> direcciones;

    // Constructor de la clase --> O(1)
    public Materia(){
        this.alumnos = new ListaEnlazada<>();
        this.docentes = new int[4];
        this.direcciones = new ListaEnlazada<>();

    }
    
    // Devuelve la lista de alumnos de la materia --> O(1)
    public ListaEnlazada<String> obtenerAlumnos(){
        return this.alumnos;
    }
    
    // Devuelve los docentes --> O(1)
    public int[] obtenerPlantel(){
        return this.docentes;
    }

    // Devuelve la lista de carreras que tienen la misma materia
    // Junto con la clave para encontrarla --> O(1)
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

        }else if (cargo.equals("AY1")) {
            this.docentes[2] += 1;

        } else if (cargo.equals("AY2")) {
            this.docentes[3] += 1;

        } else {
            System.out.println("No corresponde a un cargo docente");
        }
            
        }

    // Agregamos otra carrera que tenga la misma materia
    // y su clave correspondiente --> O(1)
    public void guardarDireccion(Trie<Materia> otraCarrera, String clave){
        infoIguales direccion = new infoIguales(otraCarrera, clave);
        this.direcciones.agregarAdelante(direccion);
    }
           
        
    }

// Invariante de Representacion:
/**
     * Las longitudes de los elementos en "alumnos" son todas iguales.
     * No hay repetidos en "alumnos".
     * No hay repetidos en "mismasMaterias".
     */

// Complejidades:
/** 
    * Como en esta clase solo agregamos elementos a una lista enlazada -
    * O devolvemos la lista enlazada , todos los metodos tienen complejidad O(1).
    * Solo modificamos la posicion de un array que tambien es O(1).
*/

