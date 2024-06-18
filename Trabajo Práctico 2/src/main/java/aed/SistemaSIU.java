package aed;

import aed.ListaEnlazada.Nodo;
import aed.Materia.infoIguales;

public class SistemaSIU {
    private Trie<Trie<Materia>> carreras;
    private Trie<Integer> estudiantes;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }
    

    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        this.carreras = new DiccionarioDigital<>();
        this.estudiantes = new DiccionarioDigital<>();

        for (InfoMateria info : materiasEnCarreras) {

            // Obtengo la lista de carreras que voy a ir creando o modificando en cada iteracion.
            // Y obtengo el nombre de la materia que voy agregar a esa carrera.
            String[] infoCarreras = info.carreras;
            String[] infoMaterias = info.nombresEnCarreras;

            // "nueva" va a ser la misma instancia de materia en todas las carreras que la tengan como materia.
            // Cada trie guardara una referencia distinta de como llegar a ella.
            Materia nueva = new Materia();

            for (int i = 0; i < infoCarreras.length; i++) {

                // Accedo al subtrie "Materia" correspondiente de cada carrera.
                // Si todavia no tiene uno ,lo creo.
                Trie<Materia> subtrieMateria = this.carreras.obtener(infoCarreras[i]);

                
                if (subtrieMateria == null) {
                    subtrieMateria = new DiccionarioDigital<>();
                    this.carreras.definir(infoCarreras[i], subtrieMateria);
                }

                subtrieMateria.definir(infoMaterias[i], nueva);
                nueva.guardarDireccion(subtrieMateria, infoMaterias[i]);
            }
        } 
        // Una vez que inscribi todas las materias , inicio a los estudiantes.
        for (String alumnos : libretasUniversitarias) {
            this.estudiantes.definir(alumnos, 0);
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        // Accedo a las materias de "carrera".
        Trie<Materia> subMateria = this.carreras.obtener(carrera);

        // Accedo a la materia que voy a modificar y agrego ese alumno.
        Materia datos = subMateria.obtener(materia);
        datos.inscribirAlumno(estudiante);
        

        // Aumento la cantidad de materias que esta inscripto ese estudiante.
        int cantidad = this.estudiantes.obtener(estudiante);
        cantidad += 1;
        this.estudiantes.definir(estudiante, cantidad);

    }

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

