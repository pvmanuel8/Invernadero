package Invernadero;
import java.util.Scanner;

import cultivos.Planta;
import edificios.Invernadero;
import edificios.TanqueDeAgua;

/**
 * La clase Simulador representa el sistema principal que gestiona los invernaderos,
 * el tanque de agua y las operaciones relacionadas con las plantas.
 */
public class Simulador {
    private int diasTranscurridos; // Número de días que han pasado en el simulador
    private Invernadero[] invernaderos; // Arreglo de invernaderos disponibles
    private TanqueDeAgua tanqueDeAgua; // Tanque de agua para regar las plantas
    private String nombreEntidad; // Nombre de la entidad/empresa/partida
    private int plantasRecolectadas; // Número total de plantas recolectadas
    private int productosCosechados; // Número total de productos cosechados
    private int maxInvernaderos = 10; // Capacidad máxima de invernaderos

    /**
     * Constructor de la clase Simulador.
     * Inicializa los atributos con valores predeterminados.
     */
    public Simulador() {
        this.diasTranscurridos = 0;
        this.invernaderos = new Invernadero[maxInvernaderos];
        this.tanqueDeAgua = new TanqueDeAgua(); // Capacidad inicial de 50, con 20 de agua
        this.plantasRecolectadas = 0;
        this.productosCosechados = 0;
    }

    /**
     * Método que inicializa el sistema, solicitando el nombre de la entidad
     * y creando el primer invernadero.
     */
    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre de la entidad/empresa/partida: ");
        this.nombreEntidad = scanner.nextLine();

        System.out.print("Introduce el nombre del primer invernadero: ");
        String nombreInvernadero = scanner.nextLine();
        invernaderos[0] = new Invernadero(nombreInvernadero, 10); // Capacidad inicial de 10 plantas

        System.out.println("Sistema inicializado correctamente.");
    }

    /**
     * Muestra el menú principal con las opciones disponibles.
     */
    public void menu() {
        System.out.println("1. Estado general");
        System.out.println("2. Estado plantas");
        System.out.println("3. Pasar día");
        System.out.println("4. Recolectar agua");
        System.out.println("5. Regar");
        System.out.println("6. Plantar");
        System.out.println("7. Cosechar");
        System.out.println("8. Desbrozar");
        System.out.println("9. Arrancar");
        System.out.println("10. Mejorar");
        System.out.println("11. Salir");
    }

    /**
     * Muestra un menú con los invernaderos disponibles para que el usuario seleccione uno.
     */
    public void menuInv() {
        System.out.println("Seleccione una opción:");
        System.out.println("--------------------------- Invernaderos ---------------------------");
        System.out.println("[Plantas vivas / Plantas plantadas / Terreno total]");
        for (int i = 0; i < invernaderos.length; i++) {
            if (invernaderos[i] != null) {
                System.out.println((i + 1) + ".- " + invernaderos[i].getName() + " [" + invernaderos[i].getAlive() +
                "/" + invernaderos[i].getNum() + "/" + invernaderos[i].getTiles() + "]");
            }
        }
        System.out.println("0.- Cancelar");
    }

    /**
     * Permite al usuario seleccionar un invernadero de la lista.
     *
     * @return El índice del invernadero seleccionado, o -1 si se cancela.
     */
    public int selectInv() {
        menuInv();
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        return opcion - 1; // Ajustar índice
    }

    /**
     * Muestra el estado general del simulador, incluyendo el día actual,
     * el nombre de la entidad, las plantas recolectadas, los productos cosechados,
     * el estado del tanque de agua y el estado de cada invernadero.
     */
    public void showGeneralStatus() {
        System.out.println("Día: " + diasTranscurridos);
        System.out.println("Nombre de la entidad: " + nombreEntidad);
        System.out.println("Plantas recolectadas: " + plantasRecolectadas);
        System.out.println("Productos cosechados: " + productosCosechados);
        tanqueDeAgua.showStatus();
        for (Invernadero invernadero : invernaderos) {
            if (invernadero != null) {
                invernadero.showStatus();
            }
        }
    }

    /**
     * Muestra el estado de las plantas en un invernadero específico.
     */
    public void showSpecificStatus() {
        int invIndex = selectInv();
        if (invIndex >= 0 && invernaderos[invIndex] != null) {
            invernaderos[invIndex].showTileStatus();
        }
    }

    /**
     * Avanza un día en el simulador, haciendo crecer todas las plantas
     * en los invernaderos.
     */
    public void nextDay() {
        diasTranscurridos++;
        for (Invernadero invernadero : invernaderos) {
            if (invernadero != null) {
                invernadero.growCrops();
            }
        }
        System.out.println("Ha pasado un día.");
    }

    /**
     * Permite al usuario añadir agua al tanque de agua.
     * Ofrece opciones para añadir 1, 5, 10 unidades o llenar el tanque.
     */
    public void addWater() {
        if (tanqueDeAgua.getWater() == tanqueDeAgua.getMax()) {
            System.out.println("Tanque lleno");
        }else{
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Añadir 1 unidad de agua");
        System.out.println("2. Añadir 5 unidades de agua");
        System.out.println("3. Añadir 10 unidades de agua");
        System.out.println("4. Llenar el tanque");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                tanqueDeAgua.addWater(1);
                break;
            case 2:
                tanqueDeAgua.addWater(5);
                break;
            case 3:
                tanqueDeAgua.addWater(10);
                break;
            case 4:
                tanqueDeAgua.addWater(tanqueDeAgua.getMax() - tanqueDeAgua.getWater());
                break;
            default:
                System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Riega todas las plantas vivas en todos los invernaderos,
     * reduciendo la cantidad de agua disponible en el tanque.
     */
    public void waterCrops() {
        int plantasRegadas = 0;
        int plantasVivas = 0;
        int aguaNecesaria = 0;
    
        // Calcular el número total de plantas vivas y el agua necesaria
        for (Invernadero invernadero : invernaderos) {
            if (invernadero != null) {
                plantasVivas += invernadero.getAlive();
                aguaNecesaria += invernadero.getAlive(); // Cada planta viva necesita 1 unidad de agua
            }
        }
    
        // Verificar si hay suficiente agua en el tanque
        
        if (tanqueDeAgua.getWater() >= aguaNecesaria) {
            // Regar las plantas en todos los invernaderos
            for (Invernadero invernadero : invernaderos) {
                if (invernadero != null) {
                    plantasRegadas += invernadero.waterCrops();
                }
            }
            // Restar el agua utilizada del tanque
            tanqueDeAgua.addWater(-aguaNecesaria); // Restar el agua utilizada
            System.out.println("Se han regado " + plantasRegadas + "/" + plantasVivas + " plantas de los invernaderos.");
            System.out.println("Agua restante en el tanque: " + tanqueDeAgua.getWater());
        } else {
            // No hay suficiente agua para regar todas las plantas
            System.out.println("No hay suficiente agua en el tanque para regar todas las plantas.");
            System.out.println("Agua disponible: " + tanqueDeAgua.getWater() + ", Agua necesaria: " + aguaNecesaria);
        }

    }

    /**
     * Añade una planta a un invernadero seleccionado si hay espacio disponible.
     */
    public void plant() {
        int invIndex = selectInv();
        if (invIndex >= 0 && invernaderos[invIndex] != null) {
            if (invernaderos[invIndex].getNum() < invernaderos[invIndex].getTiles()) {
                invernaderos[invIndex].addPlant(new Planta("Planta Genérica", "Plantus Genericus", 0, false, true, false));
                System.out.println("Planta añadida al invernadero " + invernaderos[invIndex].getName());
            } else {
                System.out.println("No hay espacio disponible en el invernadero.");
            }
        }
    }


    /**
     * Elimina todas las plantas de un invernadero seleccionado,
     * independientemente de su estado.
     */
    public void unroot() {
        int invIndex = selectInv();
        if (invIndex >= 0 && invernaderos[invIndex] != null) {
            invernaderos[invIndex] = null;
            System.out.println("Se han eliminado todas las plantas del invernadero seleccionado.");
        }
    }

    /**
     * Permite al usuario mejorar los invernaderos o el tanque de agua.
     * Ofrece opciones para mejorar un invernadero, comprar un nuevo invernadero,
     * mejorar el tanque de agua o cancelar.
     */
    public void upgrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Mejorar invernadero");
        System.out.println("2. Comprar invernadero");
        System.out.println("3. Mejorar tanque de agua");
        System.out.println("4. Cancelar");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                int invIndex = selectInv();
                if (invIndex >= 0 && invernaderos[invIndex] != null) {
                    invernaderos[invIndex].upgrade();
                }
                break;
            case 2:
                System.out.print("Introduce el nombre del nuevo invernadero: ");
                String nombreInvernadero = scanner.next();
                for (int i = 0; i < invernaderos.length; i++) {
                    if (invernaderos[i] == null) {
                        invernaderos[i] = new Invernadero(nombreInvernadero, 10);
                        System.out.println("Invernadero " + nombreInvernadero + " creado.");
                        break;
                    }
                }
                break;
            case 3:
                tanqueDeAgua.upgrade();
                break;
            case 4:
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    
/**
 * Método que recolecta todas las plantas haciendo la lógica necesaria, incrementa la cantidad
 * recolectada y el número de plantas recolectadas. Luego, muestra el resultado
 * “X plantas recolectadas han producido Y productos” “Han muerto Z plantas en el proceso”.
 */
public void harvest() {
    int plantasCosechadas = 0;
    int productosObtenidos = 0;

    for (Invernadero invernadero : invernaderos) {
        if (invernadero != null) {
            // Recolectar plantas maduras en el invernadero
            int productosInvernadero = invernadero.harvestPlants();
            productosObtenidos += productosInvernadero;

            // Contar las plantas cosechadas
            plantasCosechadas += invernadero.getDeadPlants();
        }
    }

    // Actualizar las estadísticas
    plantasRecolectadas += plantasCosechadas;
    productosCosechados += productosObtenidos;

    // Mostrar el resultado
    System.out.println(plantasCosechadas + " plantas recolectadas han producido " + productosObtenidos + " productos.");
    System.out.println("Han muerto " + plantasCosechadas + " plantas en el proceso.");
}
/**
 * Método que elimina todas las plantas muertas del invernadero.
 */
public void plow() {
    for (Invernadero invernadero : invernaderos) {
        if (invernadero != null) {
            invernadero.plow();
        }
    }
}


    /**
     * Método principal que se ejecuta al iniciar el programa.
     *
     * @param args Argumentos de la línea de comandos (no utilizados actualmente).
     */
    public static void main(String[] args) {
        Simulador simulador = new Simulador();
        simulador.init();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
    
        try {
            while (!salir) {
                simulador.showGeneralStatus();
                simulador.menu();
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        simulador.showGeneralStatus();
                        break;
                    case 2:
                        simulador.showSpecificStatus();
                        break;
                    case 3:
                        simulador.nextDay();
                        break;
                    case 4:
                        simulador.addWater();
                        break;
                    case 5:
                        simulador.waterCrops();
                        break;
                    case 6:
                        simulador.plant();
                        break;
                    case 7:
                        simulador.harvest();
                        break;
                    case 8:
                        simulador.plow();
                        break;
                    case 9:
                        simulador.unroot();
                        break;
                    case 10:
                        simulador.upgrade();
                        break;
                    case 11:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            System.out.println("Gracias por jugar.");
            scanner.close();
       }

    }
}
