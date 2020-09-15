package co.unicauca.solid.parqueadero.dominio;

import java.util.Date;

/**
 *
 * @author Juan Camilo Gonzalez, Edynson Muños Jimenez
 */
public class RegistroMoto extends RegistroVehiculo {

    /**
     * Constructor
     *
     * @param placa
     * @param modelo
     */
    public RegistroMoto(String placa, String modelo, String propietario, String fechaEntrada) {
        super(placa,modelo,propietario, fechaEntrada);
    }

    /**
     *
     * @return el tipo moto
     */
    
    @Override
    public void tipoVehiculo() {	
        tipo= this.getClass().getSimpleName();
    }
    
}
