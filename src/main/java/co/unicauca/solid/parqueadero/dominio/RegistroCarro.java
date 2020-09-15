package co.unicauca.solid.parqueadero.dominio;

import java.sql.Date;
import java.util.Vector;

/**
 *
 *@author Juan Camilo Gonzalez, Edynson Muños Jimenez
 */
public class RegistroCarro extends RegistroVehiculo {

    /**
     * Constructor
     *
     * @param placa
     * @param modelo
     */
    public RegistroCarro(String placa, String modelo, String propietario,String fechaEntrada) {
        super(placa, modelo, propietario, fechaEntrada);
    }

    /**
     *
     * @return el tipo RegistroCarro
     */
    
   @Override
    public void tipoVehiculo() {	
        tipo= this.getClass().getSimpleName();
    }
    
}