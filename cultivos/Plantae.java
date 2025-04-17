package cultivos;
//Hola que tal estas esto es una prueba
/**
 * Clase abstracta que representa un tipo de cultivo
 * Contiene atributos y metodos compartidos por ambos tipos de cultivo.
 */
public abstract class Plantae {
    protected String nombre;
    protected String nombreProducto;
    protected int precio; //Coste de plantar el cultivo
    protected int ganancia; //Ganancia al cosechar el cultivo
    protected boolean regada;
    protected boolean viva;
    protected boolean madura;
    protected int numeroDias;
    protected int diasMadura;
    protected int minProductos;
    protected int maxProductos;
    protected boolean muerealcosechar;
    /**
     * Constructor para inicializar una planta.
     * Está protegido para que solo accedan a el las subclases.
     * @param nombre
     * @param precio
     * @param ganancia
     * @param numeroDias
     * @param minProductos
     * @param maxProductos
     * @param muerealcosechar
     * @param diasMadura
     * @param regada
     * @param viva
     * @param madura
     * @param numeroDias
     * @param nombreProducto
     */
    protected Plantae(String nombre, int precio, int ganancia, int diasMadura,
    int minProductos, int maxProductos, boolean muerealcosechar, String nombreProducto) {
        this.nombreProducto = nombreProducto;
        this.diasMadura = diasMadura;
        this.nombre = nombre;
        this.precio = precio;
        this.ganancia = ganancia;
        this.regada = false;
        this.viva = true;
        this.madura = false;
        this.numeroDias = 0;
        this.minProductos = minProductos;
        this.maxProductos = maxProductos;
        this.muerealcosechar = muerealcosechar;
    }

    /**
     * Devuelve el nombre de la planta
     * @return nombre de la planta
     */
    public String getName() {
        return nombre;
    }
    

    /**
     * Devuelve el precio de la planta
     * @return precio de la planta
     */
    public int getPrice() {
        return precio;
    }

    /**
     * Devuelve la ganancia de la planta
     * @return ganancia de la planta
     */
    public int getGain() {
        return ganancia;
    }

    /**
     * Devuelve si la planta está regada
     * @return true si la planta está regada, false de lo contrario
     */
    public boolean isWatered() {
        return regada;
    }

    /**
     * Devuelve el número de días que ha pasado la planta
     * @return número de días que ha pasado la planta
     */
    public int getDays() {
        return numeroDias;
    }
    /**
     * Devuelve el número de días que ha pasado la planta en estado maduro
     * @return número de días que ha pasado la planta en estado maduro
     */
    public int getDaysMature() {
        return diasMadura;
    }
    /**
     * Devuelve si la planta está viva
     * @return true si la planta está viva, false de lo contrario
     */
    public boolean isAlive() {
        return viva;
    }

    /**
     * Devuelve si la planta está madura
     * @return true si la planta está madura, false de lo contrario
     */
    public boolean isMature() {
        if (madura) {
            diasMadura++;
        }
        return madura;
    }

    /**
     * Devuelve si la planta está muerta
     * @return true si la planta está muerta, false de lo contrario
     */
    public boolean isDead() {
        return !viva;
    }

    /**
     * Si la planta muere al cosechar devuelve true
     * @return true si la planta muere al cosechar, false de lo contrario
     */
    public boolean diesAtHarvest() {
        return muerealcosechar;
    }

    /**
     * Riega la planta.
     * @return true si la planta se riega, false si no se puede regar
     */
    public boolean water(){
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
     * Muestra el estado de la planta.
     */
    public void showStatus() {
        System.out.println("--------------- " + nombre + " ---------------");
        System.out.println("Precio: "+precio);
        System.out.println("Producto: " +nombre);
        System.out.println("Numero de productos: "+minProductos+" - "+maxProductos);
        System.out.println("Monedas por producto: "+ ganancia+"/producto");
        System.out.println("Maduracion: "+diasMadura+" dias");
        if (!diesAtHarvest()) {
            System.out.println("Ciclo: "+diasMadura+" dias");
        }

    }

    @Override
    public String toString() {
        return nombre+"- Viva "+(viva ? "Si" : "No") + 
        "| Madura: " +(madura ? "Si" : "No") +
        "| Regada: " + (regada ? "Si" : "No");
    }

    public abstract String getProduct();
}
