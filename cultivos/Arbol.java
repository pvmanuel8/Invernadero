package cultivos;

/**
 * Clase que representa un arbol frutal, con caracteristicas especiales
 * 
 */
public class Arbol extends Plantae {
    private boolean crecidoCompletamente;

    /**
     * Constructor para inicializar un arbol frutal.
     * 
     * @param nombre
     * @param precio
     * @param ganancia
     * @param diasMadura
     * @param minProductos
     * @param maxProductos
     * @param muerealcosechar
     */
    public Arbol(String nombre, int precio, int ganancia, int diasMadura,
            int minProductos, int maxProductos, boolean muerealcosechar, String nombreProducto) {
        super(nombre, precio, ganancia, diasMadura, minProductos, maxProductos, muerealcosechar, nombreProducto);
        this.crecidoCompletamente = false;
    }

    /**
     * Metodo para obtener el nombre del arbol
     * @return nombre del arbol
     */
    public String getName(){
        return nombre;
    }

    /**
     * Metodo para obtener el precio del arbol
     *  @return precio del arbol
     */
    public int getPrice(){
        return precio;
    }
    /**
     * Devuelve si el arbol ha crecido completamente
     * @return true si el arbol ha crecido completamente, false de lo contrario
     */ 
    public boolean getGrowed() {
        return crecidoCompletamente;
    }

    /**
     * Devuelve el nombre del producto que da el arbol
     * @return nombre del producto que da el arbol
     */
    public String getProduct(){
        return nombreProducto;
    }


    /**
     * Logica de crecimiento diario del arbol
     * 5% de probabilidad de morir cada día (regado o no)
     * Solo crece si está regado
     */
    @Override
    public void grow(){
        if (viva) {
            //5% de probabilidad de morir
            if (Math.random() < 0.05) {
                viva = false;
                System.out.println("El arbol ha muerto");
            } else {
                if (regada) {
                    numeroDias++;
                    if (numeroDias >= diasMadura) {
                        crecidoCompletamente = true;
                        System.out.println("El arbol ha crecido completamente");
                    }
                } else {
                    System.out.println("El arbol no ha crecido porque no está regado");
                }
            }
        }
    }

    /**
     * Metodo que se usa para cortar el arbol.
     * @return mitad del precio si crecio completamente y no está vivo
     */
    public int plow(){
        if (!viva && crecidoCompletamente) {
            return precio/2;
        }
        return 0;
    }

    /**
     * Metodo que se usa para talar el arbol.
     * @return mitad del precio
     */
    public int unRoot(){
        return precio / 2;
    }

    /**
     * Metodo para regar el arbol
     */
    @Override
    public boolean water() {
        if (viva) {
            regada = true;
            return true;
        } else {
            return false;
        }
    }
    
}
