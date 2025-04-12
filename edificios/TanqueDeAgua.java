package edificios;
import sistemas.Moneda;
/**
 * Clase que representa el almacén de agua. Inicialmente tiene una capacidad máxima de 20 unidades de agua.
 * 
 */
public class TanqueDeAgua {
    // Atributos
    private int cantidadAgua;
    private int capacidadMaxima;
    private Moneda moneda;

    /**
     * Constructor para inicializar un TanqueDeAgua con una capacidad máxima predeterminada.
     */
    public TanqueDeAgua() {
        this.cantidadAgua = 10;
        this.capacidadMaxima = 20;
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
     * Muestra el estado del tanque de agua.
     */
    public void showStatus() {
        System.out.println("Tanque de agua al " + ((cantidadAgua * 100) / capacidadMaxima) + "% de su capacidad. [" + cantidadAgua + "/" + capacidadMaxima + "]");
    }

    /**
     * Añade una cantidad de agua dada al tanque.
     *
     * @param cantidad Cantidad de agua a añadir(puede ser negativa para restar).
     */
    public void addWater(int cantidad) {
        if (cantidad != 0) {
            cantidadAgua += cantidad; // Sumar o restar la cantidad de agua
            cantidadAgua = Math.max(cantidadAgua, 0); // No permitir que el agua sea negativa
            cantidadAgua = Math.min(cantidadAgua, capacidadMaxima); // No permitir que el agua exceda la capacidad máxima
            System.out.println("Se han " + (cantidad > 0 ? "añadido" : "restado") + " " + Math.abs(cantidad) + " unidades de agua al tanque.");
        }
    }

    /**
     * Aumenta en 5 la capacidad máxima del tanque.
     */
    public void upgrade() {
        capacidadMaxima += 5;
        System.out.println("El tanque de agua ha sido mejorado. Su capacidad máxima aumentó en 5 hasta un total de " + capacidadMaxima);
    }

}
