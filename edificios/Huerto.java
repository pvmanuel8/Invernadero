package edificios;
import cultivos.Plantae;
/**
 * Clase abstracta que representa un huerto.
 * Esta clase es la base para otros tipos de huertos, como Invernadero y Arboleda.
 */
public abstract class Huerto {
    protected String nombre;
    private Plantae[] cultivos;
    protected int nivel;
    protected final int capacidadMaxima;
    protected int cantidadPlantas;
    
    public Huerto(String nombre, int nivel, int capacidadMaxima) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadPlantas = cantidadPlantas;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    public int getCantidadPlantas() {
        return cantidadPlantas;
    }
    public void setCantidadPlantas(int cantidadPlantas) {
        this.cantidadPlantas = cantidadPlantas;
    }

    /**
     * Obtiene el número de plantas plantadas en el huerto.
     * @return Array de plantas en el huerto
     */
    public Plantae[] getCultivos() {
        // Devolvemos una copia para proteger el array original
        Plantae[] copia = new Plantae[cantidadPlantas];
        System.arraycopy(cultivos, 0, copia, 0, cantidadPlantas);
        return copia;
    }
}
