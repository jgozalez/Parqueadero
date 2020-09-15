package co.unicauca.solid.parqueadero.dominio.main;

import co.unicauca.solid.parqueadero.dominio.RegistroCamion;
import co.unicauca.solid.parqueadero.dominio.RegistroCarro;
import co.unicauca.solid.parqueadero.dominio.RegistroMoto;
import co.unicauca.solid.parqueadero.dominio.RegistroVehiculo;
import co.unicauca.solid.parqueadero.dominio.aceso.Parqueadero;
import co.unicauca.solid.parqueadero.dominio.servicio.Negocio;
import java.util.List;
import co.unicauca.solid.parqueadero.dominio.aceso.IRegistroRepositorio;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Juan Camilo Gonzalez, Edynson Muños Jimenez
 */
public class ClienteMain {

    public static void main(String[] args) throws ParseException {
        /**
         * Crea los vehiculos de cada tipo
         */
        IRegistroRepositorio repositorio = Parqueadero.getInstancia().getRepositorio("defecto");

        Negocio myNegocio = new Negocio(repositorio);
        RegistroVehiculo v1 = new RegistroCarro("234h", "2010", "pepe", "2019-07-23 13:20:45 UTC");
        v1.tipoVehiculo();
        RegistroVehiculo v2 = new RegistroMoto("234t", "2011", "roberto", "2019-08-22 12:00:00 UTC");
        v2.tipoVehiculo();
        RegistroVehiculo v3 = new RegistroCamion("234a", "2030", "juan", "2019-09-23 13:20:45 UTC");
        v3.tipoVehiculo();
        /**
         * Guarda los vehiculos en la base de datos
         */
        myNegocio.saveVehiculo(v3);//fecha entrada
        myNegocio.saveVehiculo(v2);
        myNegocio.saveVehiculo(v1);
        /*"yyyy-MM-dd HH:mm:ss
        1) Formato de fecha requerido: 2012.10.23 20:20:45 PST
        El formato de fecha apropiado especificado será aaaa.MM.dd HH: mm: ss zzz
         */
        //"2010-01-04 01:32:27 UTC"
        /**
         * Saca a los vehiculos de la base de datos
         */
        myNegocio.fijarSalida("2019-10-23 13:20:45 UTC", "234h");
        myNegocio.fijarSalida("2019-11-22 15:30:00 UTC", "234t");
        myNegocio.fijarSalida("2019-12-23 13:20:45 UTC", "234a");
        /**
         * Calcula las horas en parqueadero del vehciulo dependiendo del tipo de
         * vehiculo
         */
        myNegocio.calcularCobro("234a");
        myNegocio.calcularCobro("234t");
        myNegocio.calcularCobro("234h");

        /**
         * LLama al metodo imprimir Vehiculo
         */
        imprimirVehiculos(myNegocio.listVehiculo());
    }

    /**
     * Metodo que imprime el vehiculo
     *
     * @param vehiculos
     */
    public static void imprimirVehiculos(List<RegistroVehiculo> vehiculos) {
        System.out.println("LISTA EN BASE DE DATOS: ");
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.println("tipo: " + vehiculos.get(i).getTipo());
            System.out.println("placa: " + vehiculos.get(i).getPlaca());
            System.out.println("modelo: " + vehiculos.get(i).getModelo());
            System.out.println("propietario: " + vehiculos.get(i).getPropietario());
            System.out.println("FechaEntrada: " + vehiculos.get(i).getFechaEntrada());
            System.out.println("FechaSalida: " + vehiculos.get(i).getFechaSalida());
            System.out.println("Ticket: " + vehiculos.get(i).getNumTicket());
            System.out.println("-------------------");
        }
    }
}
