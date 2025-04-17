package edificios;
import cultivos.Plantae;
import sistemas.Moneda;
/**
 * Clase que representa el almacén de agua. Inicialmente tiene una capacidad máxima de 20 unidades de agua.
 * 
 */
public class TanqueDeAgua {
    // Atributos
    private int cantidadAgua;
    private int capacidadMaxima;
    private Moneda moneda; //Referencia a la clase Moneda

    private int numCultivos;

    private boolean aspersores; // Indica si el tanque tiene aspersores
    private final int COSTE_ASPERSORES = 1000; // Coste fijo de los aspersores

    private Plantae[] plantas; // Array de plantas que se pueden regar automáticamente

    /**
     * Constructor para inicializar un TanqueDeAgua con una capacidad máxima predeterminada.
     */
    public TanqueDeAgua() {
        this.moneda = moneda; // Inicializa la referencia a la clase Moneda
        this.cantidadAgua = 10;
        this.capacidadMaxima = 20;
        this.plantas = new Plantae[numCultivos]; // Inicializa el array de cultivos
    }

    /**
     * Obtiene la cantidad de agua disponible en el tanque.
     *
     * @return Cantidad de agua disponible.
     */
    public int getWater() {
        return cantidadAgua;
    }

    /**
     * Obtiene la capacidad máxima del tanque.
     *
     * @return Capacidad máxima del tanque.
     */
    public int getMax() {
        return capacidadMaxima;
    }

    /**
     * Obtiene la cantidad de agua actual en el tanque.
     * @return Cantidad de agua actual.
     */
    public int getCantidadAgua() {
        return cantidadAgua;
    }
    
    /**
     * Obtiene la moneda asociada al tanque.
     *
     * @return Moneda asociada al tanque.
     */
        public Moneda getMoneda() {
        return moneda;
    }
    
    /**
     * Metodo para comprobar si el tanque tiene aspersores.
     * 
     * @return true si el tanque tiene aspersores, false en caso contrario.
     */
    public boolean isAspersores() {
        return aspersores;
    }
    
    /**
     * Metodo para obtener el coste de los aspersores.
     * 
     * @return Coste de los aspersores.
     */
    public int getCOSTE_ASPERSORES() {
        return COSTE_ASPERSORES;
    }
    
    /**
     * Metodo para obtener el array de plantas.
     * 
     * @return Array de plantas.
     */
    public Plantae[] getPlantas() {
        return plantas;
    }
    
    /**
     * Metodo para obtener el numero de cultivos.
     * 
     * @return Numero de cultivos.
     */
    public int getNumCultivos() {
        return numCultivos;
    }
    /**
     * Metodo para establecer la cantidad de agua en el tanque.
    */
    public void setCantidadAgua(int cantidadAgua) {
        this.cantidadAgua = cantidadAgua;
    }
    
    /**
     * Metodo para obtener la capacidad maxima del tanque.
    */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    /**
     * Metodo para establecer la capacidad maxima del tanque.
    */
    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
    
    /**
     * Metodo para establecer la moneda asociada al tanque.
     * 
     * @param moneda Moneda asociada al tanque.
     */

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    
    /**
     * Metodo para obtener el numero de cultivos.
     * @param numCultivos
    */
    public void setNumCultivos(int numCultivos) {
        this.numCultivos = numCultivos;
    }
    
    /**
     * Metodo para establecer si el tanque tiene aspersores.
     * @param aspersores
    */
    public void setAspersores(boolean aspersores) {
        this.aspersores = aspersores;
    }

    /**
     * Muestra el estado del tanque de agua.
     */
    public void showStatus() {
        System.out.println("Tanque de agua al " + ((cantidadAgua * 100) / capacidadMaxima) + "% de su capacidad. [" + cantidadAgua + "/" + capacidadMaxima + "]");
    }

    /**
     * Añade una cantidad de agua dada al tanque.
     * Cada cantidad cuesta 1 moneda. Si se llena el tanque tendra un ahorro por 10 de agua
     *
     * @param cantidad Cantidad de agua a añadir(puede ser negativa para restar).
     */
    public void addWater(int cantidad, Moneda moneda) {
        if (cantidad <= 0) return;
    
        int espacioDisponible = capacidadMaxima - cantidadAgua;
        int aguaAAñadir = Math.min(cantidad, espacioDisponible);
    
        if (aguaAAñadir <= 0) {
            System.out.println("El tanque ya está lleno.");
            return;
        }
    
    // Calcular costo con posible descuento
    int costo = aguaAAñadir;
    boolean llenadoCompleto = (cantidadAgua + aguaAAñadir) == capacidadMaxima;
    
    if (llenadoCompleto) {
        int descuento = aguaAAñadir / 10; // 1 moneda de descuento por cada 10 unidades
        costo -= descuento;
        System.out.println("¡Descuento por llenado completo! Ahorras " + descuento + " monedas.");
    }
    
    if (moneda.getCantidad() >= costo) {
        moneda.gastar(costo);
        cantidadAgua += aguaAAñadir;
        System.out.println("Añadidas " + aguaAAñadir + " unidades. Costo: " + costo + " monedas.");
    } else {
        System.out.println("Fondos insuficientes. Necesitas " + costo + " monedas.");
    }
}

    /**
     * Aumenta en 5 la capacidad máxima del tanque.
     */
    public void upgrade() {
        capacidadMaxima += 5;
        System.out.println("El tanque de agua ha sido mejorado. Su capacidad máxima aumentó en 5 hasta un total de " + capacidadMaxima);
    }

    @Override
    /**
     * Devuelve una representación en cadena del estado del tanque de agua.
     *
     * @return Representación en cadena del tanque de agua.
     */
    public String toString() {
        return "Tanque de agua al " +((cantidadAgua * capacidadMaxima)/100)  + "% de su capacidad.["+cantidadAgua+"/" + capacidadMaxima+"]";
    }

    /**
     * Metodo para comprar aspersores para el tanque de agua.
     * @return true si se instalan correctamente.
     */
    public boolean comprarAspersores(){
        if(aspersores){
            System.out.println("Ya tienes aspersores en el tanque de agua.");
            return false;
        }else if(moneda.getCantidad() >= COSTE_ASPERSORES){
            moneda.gastar(COSTE_ASPERSORES);
            aspersores = true;
            System.out.println("Has comprado aspersores para el tanque de agua.");
            return true;
        }else{
            System.out.println("Monedas insuficientes, necesitas " + COSTE_ASPERSORES + " monedas.");
            return false;
        }
    }

    public void regarAutomaticamente(Huerto[] huertos) {
        if (huertos == null || huertos.length == 0) {
            System.out.println("No hay cultivos disponibles para regar automáticamente.");
            return;
        }
         // Validaciones iniciales
         if (!aspersores) {
            System.out.println("¡No se puede regar! No hay aspersores instalados.");
            return;
        }
        
        if (cantidadAgua <= 0) {
            System.out.println("¡No se puede regar! El tanque está vacío.");
            return;
        }
        
        if (huertos == null || huertos.length == 0) {
            System.out.println("No hay huertos para regar.");
            return;
        }

        // Calcular agua necesaria
        int aguaNecesaria = 0;
        for (Huerto huerto : huertos) {
            if (huerto != null) {
                for (Plantae planta : huerto.getCultivos()) {
                    if (planta != null && planta.isAlive()) {
                        aguaNecesaria++;
                    }
                }
            }
        }

        // Realizar el riego si hay suficiente agua
        if (cantidadAgua >= aguaNecesaria) {
            for (Huerto huerto : huertos) {
                if (huerto != null) {
                    for (Plantae planta : huerto.getCultivos()) {
                        if (planta != null && planta.isAlive()) {
                            planta.water();
                        }
                    }
                }
            }
            cantidadAgua -= aguaNecesaria;
            System.out.println("✓ Riego completado. Agua usada: " + aguaNecesaria);
        } else {
            System.out.println("✗ Agua insuficiente. Necesitas " + aguaNecesaria + " unidades.");
        }
    }
}

