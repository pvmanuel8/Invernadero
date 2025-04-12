package edificios;

/**
 * Clase abstracta que representa un huerto.
 * Esta clase es la base para otros tipos de huertos, como Invernadero y Arboleda.
 */
public abstract class Huerto {
    protected String nombre;
    protected int nivel;
    protected int capacidadMaxima;
    protected int cantidadPlantas;
}
