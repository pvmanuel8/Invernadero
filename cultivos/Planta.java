package cultivos;
/**
 * Clase que representa una planta genérica.
 */
public class Planta {
    // Atributos
    private final String nombreComun;
    private final String nombreCientifico;
    private int numeroDias;
    private boolean regada;
    private boolean viva;
    private boolean madura;

    /**
     * Constructor para inicializar una planta.
     *
     * @param nombreComun    Nombre común de la planta.
     * @param nombreCientifico Nombre científico de la planta.
     * @param numeroDias      Número de días de la planta.
     * @param regada          Indica si la planta está regada.
     * @param viva            Indica si la planta está viva.
     * @param madura          Indica si la planta está madura para ser cosechada.
     */
    public Planta(String nombreComun, String nombreCientifico, int numeroDias, boolean regada, boolean viva, boolean madura) {
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.numeroDias = numeroDias;
        this.regada = regada;
        this.viva = viva;
        this.madura = madura;
    }

  
    /**
     * Obtiene el nombre común de la planta.
     *
     * @return Nombre común de la planta.
     */
    public String getName() {
        return nombreComun;
    }

    /**
     * Obtiene el nombre científico de la planta.
     *
     * @return Nombre científico de la planta.
     */
    public String getScientificName() {
        return nombreCientifico;
    }

    /**
     * Devuelve si la planta está madura.
     *
     * @return true si la planta está madura, false de lo contrario.
     */
    public boolean isMature() {
        return madura;
    }

    /**
     * Devuelve si la planta está muerta.
     *
     * @return true si la planta está muerta, false de lo contrario.
     */
    public boolean isDead() {
        return !viva;
    }
    /**
     * Devuelve si la planta está viva.
     *
     * @return true si la planta está viva, false de lo contrario.
     * Este método es auxiliar para la lógica del invernadero
     */
    public boolean isAlive() {
        return viva;
    }

    /**
     * Devuelve si la planta está regada.
     *
     * @return true si la planta está regada, false de lo contrario.
     */
    public boolean isWatered() {
        return regada;
    }

    /**
     * Muestra el estado de la planta.
     */
    public void showStatus() {
        System.out.println("--------------- " + nombreComun + " ---------------");
        System.out.println("Edad: " + numeroDias + " días");
        System.out.println("Viva: " + (viva ? "Si" : "No"));
        System.out.println("Regada: " + (regada ? "Si" : "No"));
        System.out.println("Madura: " + (madura ? "Si" : "No"));
    }

    /**
     * Riega la planta si está viva y devuelve si se ha regado o no.
     *
     * @return true si la planta ha sido regada, false si está muerta.
     */
    public boolean water() {
        if (viva) {
            regada = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Hace crecer un día la planta teniendo en cuenta ciertas condiciones.
     * La planta deja de estar regada después de pasar el día.
     */
    public void grow() {
        if (viva) {
            numeroDias++;

            // Condiciones para el crecimiento de la planta
            if (!regada) {
                // 50% de probabilidad de morir si no está regada
                if (Math.random() < 0.5) {
                    viva = false;
                }
            } else {
                // Madura después de 3 días
                if (numeroDias >= 3) {
                    madura = true;
                }
                // Muere después de 10 días
                if (numeroDias >= 10) {
                    viva = false;
                }
            }

            // La planta deja de estar regada después de pasar el día
            regada = false;
        }
    }

    /**
     * Recolecta la planta si es madura, devolviendo el número de productos producidos.
     * En este caso, devuelve una cantidad entre 1 y 3, ambos incluidos.
     * Una vez recolectada, la planta morirá.
     *
     * @return Número de productos recolectados o 0 si la planta no es madura.
     */
    public int harvest() {
        if (madura && viva) {
            int productos = (int) (Math.random() * 3) + 1;
            viva = false;
            madura = false;
            return productos;
        } else {
            return 0;
        }
    }

    /**
     * Reinicia la planta, estableciendo su edad a cero y el resto de atributos a sus estados iniciales.
     */
    public void replant() {
        numeroDias = 0;
        regada = false;
        viva = true;
        madura = false;
    }

}
