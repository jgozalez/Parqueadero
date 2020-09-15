
package co.unicauca.solid.parqueadero.dominio.aceso;

import java.util.Date;

/**
 *
 * @author Juan Camilo Gonzalez, Edynson Muños Jimenez
 */
public class Parqueadero {

    /**
     * aplicacion de singleton, obtener solo una instancia de la clase.
     */
    private static Parqueadero instancia;

    /**
     * CONSTRUCTORES
     */
    
    
    private Parqueadero() {
    }

    //METODOS
    public static Parqueadero getInstancia() {
        if (instancia == null) {
            instancia = new Parqueadero();
        }
        return instancia;
    }

    /**
     *
     * @param tipo
     * @return
     */

    public IRegistroRepositorio getRepositorio(String tipo) {
        IRegistroRepositorio resultado = null;
        switch (tipo) {
            case "defecto":
                resultado = new RegistroRepositorio();
                break;
        }
        return resultado;
    }

 
}
