
package co.unicauca.solid.parqueadero.dominio.aceso;

import co.unicauca.solid.parqueadero.dominio.RegistroVehiculo;
import java.util.List;

/**
 *
 * @author Juan Camilo Gonzalez, Edynson Muños Jimenez
 */
public interface IRegistroRepositorio {

    boolean save(RegistroVehiculo newVehiculo);
    List<RegistroVehiculo> list();
    boolean retirar(String fechaSalida, String placa);
    RegistroVehiculo obtenerVehiculo(String placa);
}
