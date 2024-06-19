package aed;

import aed.ListaEnlazada.Nodo;


public class SistemaSIU {
    private Trie<Trie<Materia>> carreras;
    private Trie<Integer> estudiantes;

    // Invariante de representacion
/**
    * 'carreras' no es null.
    * 'estudiantes' no es null.
    * Los valores de estudiantes se mueven entre 0 y la cantidad de materias.
    * Todos los elementos en carreras.obtenerAlumnos() estan en 'estudiantes' y viceversa.
    * 'estudiantes' no tiene repetidos.
    * 
*/

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }
    
    // Ejercicio 1:

    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        this.carreras = new Trie<>();
        this.estudiantes = new Trie<>();
        
        // Cada posicion del arreglo 'materiasEnCarreras' corresponde a una materia.
        for (InfoMateria info : materiasEnCarreras) {

            // Obtengo la lista de carreras que tienen esa misma materia.
            // Y obtengo la lista de nombres de esa materia. --> O(1).
            String[] infoCarreras = info.carreras;
            String[] infoMaterias = info.nombresEnCarreras;

            // Creo una instancia de Materia que van a compartir todas las carreras
            // que la tengan como materia. --> O(1).
            Materia nueva = new Materia();

            for (int i = 0; i < infoCarreras.length; i++) {

                // Accedo al subtrie de cada carrera que contiene sus materias. --> O(|c|).
                Trie<Materia> subtrieMateria = this.carreras.obtener(infoCarreras[i]);

                // Sino tiene uno lo creo. --> O(1).
                // Y lo defino --> O(|c|)
                if (subtrieMateria == null) {
                    subtrieMateria = new Trie<>();
                    this.carreras.definir(infoCarreras[i], subtrieMateria);
                }
                // Una vez que obtenemos el subtrie , agrego la materia. --> O(|n|).
                // Y guardo en el 'mapa' de la materia como encontrarla con cada carrera. --> O(1).
                subtrieMateria.definir(infoMaterias[i], nueva);
                nueva.guardarDireccion(subtrieMateria, infoMaterias[i]);
            }
        } 
        // Una vez que inscribi todas las materias , inicio a los estudiantes. --> O(E).
        for (String alumnos : libretasUniversitarias) {
            this.estudiantes.definir(alumnos, 0);
        }
    }

// Complejidad Ejercicio 1.
/** 
    * Obtener el subTrie de una carrera y definirlo tiene complejidad O(|c|) + O(|c|) = O(|c|).
    * Esto lo hacemos tantas veces como materias tenga esa carrera en su conjunto de materias.
    * Si Mc es el conjunto de materias que tiene cada carrera entonces ese paso lo hacemos 
      |Mc| veces.
    * Y esto lo hacemos con cada carrera que hay en el conjunto total de carreras C.
    * En total ese paso al finalizar el metodo tiene complejidad: O(Σ_(c ∈ C) |c|*|Mc|).

    * Definir una instancia de Materia en cada subtrie cuesta O(|n|).
    * Como las materias tienen distintos nombres , ese paso lo tengo que hacer tantas veces 
      como nombres tenga.
    * Y como los nombres aveces difieren ese paso va a costar en cada iteracion :
    * O(|n_1|) + O(|n_2|) + ... + O(|n_n|). Si Nm es el conjunto de nombres de cada materia la 
      complejidad queda: O(Σ_(n ∈ Nm) |n|. 
    * Y esto lo voy hacer con cada materia que hay en el conjunto total de materias M.
    * Por lo tanto en total la complejidad de ese paso queda : O(Σ_(m ∈ M) Σ_(n ∈ Nm) |n|.
    
    * Por ultimo como las claves de los estudiantes estan acotadas ,agregarlas a un Trie tiene 
      costo O(1).
    * Por lo tanto ese paso cuesta O(E).'E' siendo la cantidad de estudiantes en total.

    * Al finalizar el metodo la complejidad queda:
    * O(Σ_(c ∈ C) |c|*|Mc| + Σ_(m ∈ M) Σ_(n ∈ Nm) |n| + E).
 */


    // Ejercicio 2:

    public void inscribir(String estudiante, String carrera, String materia){
        // Accedo a las materias de "carrera". --> O(|c|)
        Trie<Materia> subMateria = this.carreras.obtener(carrera);

        // Accedo a la materia que voy a modificar y agrego ese alumno. --> O(|m|)
        Materia datos = subMateria.obtener(materia);
        datos.inscribirAlumno(estudiante);
        

        // Aumento la cantidad de materias que esta inscripto ese estudiante. --> O(1).
        int cantidad = this.estudiantes.obtener(estudiante);
        cantidad += 1;
        this.estudiantes.definir(estudiante, cantidad);

    }

// Complejidad Ejercicio 2:
/**
    * Accedo a la carrera de ese alumno en tiempo O(|c|).
    * Despues accedo a la materia que lo quiero inscribir en tiempo O(|m|).
    * Lo agrego a esa materia en tiempo O(1)
    * Como los nombres de estudiantes estan acotados , accedo a su trie materias en O(1).
    * Aumento la cantidad de materias en la que esta en O(1).
    * En total la complejidad queda O(|c| + |m| + 1 + 1) = O(|c| + |m|). 
*/


    // Ejercicio 3:
    
    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        Trie<Materia> subMateria = this.carreras.obtener(carrera);
        Materia datos = subMateria.obtener(materia);

        if (cargo.ordinal() == 0) {
            datos.inscribirDocente("AY2");

        } else if (cargo.ordinal() == 1) {
            datos.inscribirDocente("AY1"); 

        } else if (cargo.ordinal() == 2) {
            datos.inscribirDocente("JTP");

        } else {
            datos.inscribirDocente("PROF");
        }
    } 

    public int[] plantelDocente(String materia, String carrera){
        Trie<Materia> subMateria = this.carreras.obtener(carrera);
        Materia datos = subMateria.obtener(materia);
        return datos.obtenerPlantel();
    }

    @SuppressWarnings("rawtypes")
    public void cerrarMateria(String materia, String carrera){
        // Accedo a las materias de esa carrera
        Trie<Materia> subTrieMateria = this.carreras.obtener(carrera);

        // Accedo a los datos de esa materia
        Materia datosMateria = subTrieMateria.obtener(materia);

        // Accedo a todas las direcciones en que se encuentra esa materia.
        ListaEnlazada<infoIguales> direcciones = datosMateria.obtenerIguales();

        // Comienzo en la primer direccion y con un ciclo voy cerrando las materias en todas las carreras.
        Nodo actual = direcciones.obtenerPrimero();

        while (actual != null) {
            infoIguales info = (infoIguales) actual.valor;
            info.otraCarrera.eliminar(info.otraClave);
            actual = actual.siguiente;
        }

        // Idem al paso anterior pero ahora itero con los alumnos de la materia.
        ListaEnlazada<String> alumnes = datosMateria.obtenerAlumnos();
        Nodo alumno = alumnes.obtenerPrimero();

        while (alumno != null) {
            // Disminuyo la cantidad de materias de cada alumno en esa materia.
            String LU = (String) alumno.valor;
            Integer inscripciones = this.estudiantes.obtener(LU);
            inscripciones -= 1;
            this.estudiantes.definir(LU, inscripciones);

            alumno = alumno.siguiente;
        }
    }

    public int inscriptos(String materia, String carrera){
        Trie<Materia> subMateria = this.carreras.obtener(carrera);
        Materia datos = subMateria.obtener(materia);
        return datos.obtenerAlumnos().obtenerSize();
    }

    public boolean excedeCupo(String materia, String carrera){
        Trie<Materia> subMateria = this.carreras.obtener(carrera);
        Materia datosMateria = subMateria.obtener(materia);

        int[] plantel = datosMateria.obtenerPlantel();

        int cupo = plantel[0]*250;

        if (cupo > plantel[1]*100) {
            cupo = plantel[1]*100;
        } 
        if (cupo > plantel[2]*20) {
            cupo = plantel[2]*20;
        }
        if (cupo > plantel[3]*30) {
            cupo = plantel[3]*30;
        }
        return cupo < datosMateria.obtenerAlumnos().obtenerSize();
    }


    public String[] carreras(){
        return this.carreras.inOrder();
    }

    public String[] materias(String carrera){
        Trie<Materia> subTrieMaterias = this.carreras.obtener(carrera);
        return subTrieMaterias.inOrder();
    }

    public int materiasInscriptas(String estudiante){
        return this.estudiantes.obtener(estudiante);
    }
}

