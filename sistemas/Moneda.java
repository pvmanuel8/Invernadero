package sistemas;
/**
 * Clase que gestiona las monedas
 * 
 */
public class Moneda {
    private int cantidad;

    /**
     * Constructor que crea una instancia de Monedas
     * Empieza con 100 monedas
     */
    public Moneda(){
        this.cantidad = 100;
    }

    /**
     * Obtiene la cantidad de monedas
     * @return cantidad de monedas
     */
    public int getCantidad(){
        return cantidad;
    }

    /**
     * Añade la cantidad de monedas
     * @param cantidad Cantidad de monedas añadidas
     */
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    /**
     * Metodo para comprar algo
     * Si hay dinero suficiente, se resta la cantidad de monedas
     * @param precio
     */
    public void gastar(int precio){
        if (cantidad >= precio) {
            cantidad -= precio;
        } else {
            System.out.println("No hay dinero");
        }
    }

    /**
     * Metodo para ganar monedas
     * @param cantidad Cantidad de monedas ganadas
     */
    public void ganar(int cantidad){
        this.cantidad += cantidad;
    }
}
