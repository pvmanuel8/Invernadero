package edificios;

import cultivos.Planta;

/**
 * La clase Invernadero representa el terreno en el que se plantan las plantas.
 * Inicialmente hay espacio para 10 plantas.
 *
 */
public class Invernadero {
    // Atributos
    private int nivel;
    private final String nombre;
    private Planta[] plantas;
    private int capacidadMaxima;

    /**
     * Constructor para inicializar un Invernadero con un nombre y capacidad inicial.
     *
     * @param nombre         Nombre del invernadero.
     * @param capacidadMaxima Capacidad máxima inicial de plantas en el invernadero.
     */
    public Invernadero(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.nivel = 1;
        this.capacidadMaxima = capacidadMaxima;
        this.plantas = new Planta[capacidadMaxima];
    }

    /**
     * Obtiene el nombre del invernadero.
     *
     * @return Nombre del invernadero.
     */
    public String getName() {
        return nombre;
    }

    /**
     * Obtiene el número de plantas plantadas en el invernadero.
     *
     * @return Número de plantas plantadas.
     */
    public int getNum() {
        int count = 0;
        for (Planta planta : plantas) {
            if (planta != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Obtiene el número de espacios totales en el invernadero.
     *
     * @return Número total de espacios.
     */
    public int getTiles() {
        return capacidadMaxima;
    }

    /**
     * Obtiene el número de plantas vivas en el invernadero.
     *
     * @return Número de plantas vivas.
     */
    public int getAlive() {
        int count = 0;
        for (Planta planta : plantas) {
            if (planta != null && planta.isAlive()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Obtiene el número de plantas maduras en el invernadero.
     *
     * @return Número de plantas maduras.
     */
    public int getMature() {
        int count = 0;
        for (Planta planta : plantas) {
            if (planta != null && planta.isMature()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Obtiene el número de plantas regadas en el invernadero.
     *
     * @return Número de plantas regadas.
     */
    public int getWatered() {
        int count = 0;
        for (Planta planta : plantas) {
            if (planta != null && planta.isWatered()) {
                count++;
            }
        }
        return count;
    }

    public void showStatus() {
        System.out.println("==========" + nombre + "==========");
        int numPlantas = getNum();
        int tiles = getTiles();
        int plantasVivas = getAlive();
        int plantasRegadas = getWatered();
        int plantasMaduras = getMature();
    
        try {
            int porcentajeOcupacion = (numPlantas * 100) / tiles;
            System.out.println("Ocupación: " + numPlantas + "/" + tiles + " (" + porcentajeOcupacion + "%)");
        } catch (ArithmeticException e) {
            System.out.println("Ocupación: " + numPlantas + "/" + tiles + " (0%) - Error: Capacidad máxima es 0.");
        }
    
        try {
            int porcentajeVivas = (plantasVivas * 100) / numPlantas;
            System.out.println("Plantas vivas: " + plantasVivas + " / " + numPlantas + " (" + porcentajeVivas + "%)");
        } catch (ArithmeticException e) {
            System.out.println("Plantas vivas: " + plantasVivas + " / " + numPlantas + " (0%) - Error: No hay plantas plantadas.");
        }
    
        try {
            int porcentajeRegadas = (plantasRegadas * 100) / plantasVivas;
            System.out.println("Plantas regadas: " + plantasRegadas + " / " + plantasVivas + " (" + porcentajeRegadas + "%)");
        } catch (ArithmeticException e) {
            System.out.println("Plantas regadas: " + plantasRegadas + " / " + plantasVivas + " (0%) - Error: No hay plantas vivas.");
        }
    
        try {
            int porcentajeMaduras = (plantasMaduras * 100) / plantasVivas;
            System.out.println("Plantas maduras: " + plantasMaduras + " / " + plantasVivas + " (" + porcentajeMaduras + "%)");
        } catch (ArithmeticException e) {
            System.out.println("Plantas maduras: " + plantasMaduras + " / " + plantasVivas + " (0%) - Error: No hay plantas vivas.");
        }
    }

    /**
     * Muestra el estado de cada planta.
     */
    public void showTileStatus() {
        System.out.println("Estado de cada planta:");
        for (int i = 0; i < plantas.length; i++) {
            System.out.print("Espacio " + (i + 1) + ": ");
            if (plantas[i] != null) {
                System.out.println("Viva: " + (plantas[i].isAlive() ? "Si" : "No") +
                        ", Madura: " + (plantas[i].isMature() ? "Si" : "No") +
                        ", Regada: " + (plantas[i].isWatered() ? "Si" : "No"));
            } else {
                System.out.println("Vacío");
            }
        }
    }

    /**
     * Muestra la ocupación del invernadero.
     */
    public void showCapacity() {
        System.out.println("Invernadero " + nombre + " al " + ((getNum() * 100) / getTiles()) + "% de capacidad. [" + getNum() + "/" + getTiles() + " plantas/terreno]");
    }

    /**
     * Riega las plantas devolviendo el número de ellas que ha regado.
     *
     * @return Número de plantas regadas.
     */
    public int waterCrops() {
        int count = 0;
        for (Planta planta : plantas) {
            if (planta != null && planta.isAlive() && !planta.isWatered()) {
                planta.water();
                count++;
            }
        }
        return count;
    }

    /**
     * Hace crecer todas las plantas del invernadero.
     */
    public void growCrops() {
        for (Planta planta : plantas) {
            if (planta != null) {
                planta.grow();
            }
        }
    }

    /**
     * Mejora el invernadero, aumentando en 10 la capacidad máxima de plantas.
     * Luego, mostrará un mensaje “Invernadero X mejorado. Su capacidad ha aumentado en 10 hasta un total de Y”
     * Siendo X el nombre del invernadero e Y la capacidad máxima actual.
     */
    public void upgrade() {
        capacidadMaxima += 10;
        Planta[] nuevoArray = new Planta[capacidadMaxima];
        System.arraycopy(plantas, 0, nuevoArray, 0, plantas.length);
        plantas = nuevoArray;
        System.out.println("Invernadero " + nombre + " mejorado. Su capacidad ha aumentado en 10 hasta un total de " + capacidadMaxima);
    }
/**
 * Añade una planta al invernadero si hay espacio disponible.
 *
 * @param planta Planta a añadir al invernadero.
 */
public void addPlant(Planta planta) {
    for (int i = 0; i < plantas.length; i++) {
        if (plantas[i] == null) {
            plantas[i] = planta;
            break;
        }
    }
}
/**
 * Obtiene las plantas muertas del invernadero
 * @return Número de plantas muertas
 */
public int getDeadPlants() {
    int count = 0;
    for (Planta planta : plantas) {
        if (planta != null && !planta.isAlive()) {
            count++;
        }
    }
    return count;
}

/**
 * Obtiene el número de productos cosechados en el invernadero.
 *
 * @return Número de productos cosechados.
 */
public int getHarvestedProducts() {
    int count = 0;
    for (Planta planta : plantas) {
        if (planta != null && !planta.isAlive() && planta.isMature()) {
            count += planta.harvest();
        }
    }
    return count;
}

/**
 * Recolecta las plantas maduras en el invernadero, devolviendo el número total de productos recolectados.
 *
 * @return Número de productos recolectados.
 */
public int harvestPlants() {
    int totalProductos = 0;
    for (Planta planta : plantas) {
        if (planta != null && planta.isMature() && planta.isAlive()) {
            totalProductos += planta.harvest();
        }
    }
    return totalProductos;
}

/**
 *
 * Elimina todas las plantas muertas del invernadero.
 * Muestra un mensaje indicando que se han eliminado las plantas muertas.

 */
public void plow() {
    for (int i = 0; i < plantas.length; i++) {
        if (plantas[i] != null && !plantas[i].isAlive()) {
            plantas[i] = null; // Eliminar planta muerta
        }
    }
    System.out.println("Se han eliminado todas las plantas muertas del invernadero " + nombre + ".");
}

}

