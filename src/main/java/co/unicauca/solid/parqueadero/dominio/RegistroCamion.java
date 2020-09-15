package co.unicauca.solid.parqueadero.dominio;

import java.util.Date;

/**
 *
 * @author Juna Camilo Gonzalez, Edynson Muños Jimenez
 */
public class RegistroCamion extends RegistroVehiculo {

    /**
     * Constructor
     *
     * @param placa
     * @param modelo
     */

    public RegistroCamion(String placa, String modelo, String propietario, String fechaEntrada) {
        super(placa, modelo,propietario, fechaEntrada);
    }

    /**
     *
     * @return El tipo RegistroCamion
     */
    @Override
    public void tipoVehiculo() {	
        tipo= this.getClass().getSimpleName();
    }
    
}
