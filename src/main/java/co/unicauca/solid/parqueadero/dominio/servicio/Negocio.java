package co.unicauca.solid.parqueadero.dominio.servicio;

import co.unicauca.solid.parqueadero.dominio.RegistroVehiculo;
import java.util.ArrayList;
import java.util.List;
import co.unicauca.solid.parqueadero.dominio.aceso.IRegistroRepositorio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

/**
 *
 * @author Juan Camilo Gonzalez, Edynson Muños Jimenez
 */
public class Negocio {

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private IRegistroRepositorio repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IRegistroRepositorio
     */
    public Negocio(IRegistroRepositorio repository) {
        this.repository = repository;
    }

    /**
     *
     * @param newVehiculo
     * @return
     */
    public boolean saveVehiculo(RegistroVehiculo newVehiculo) {

        //Validacion del Vihiculo
        if (newVehiculo == null || newVehiculo.getPlaca().isBlank() || newVehiculo.getModelo().isBlank()) {
            return false;
        }

        repository.save(newVehiculo);
        return true;

    }

    /**
     * lista los vehiculos desde la base de datos
     *
     * @return
     */
    public List<RegistroVehiculo> listVehiculo() {
        List<RegistroVehiculo> vehiculos = new ArrayList<>();
        vehiculos = repository.list();
        return vehiculos;
    }

    /**
     * Saca los vehiculos de la base de datos
     *
     * @param fechaSalida
     * @param placa
     * @return
     */

    public Boolean fijarSalida(String fechaSalida, String placa) {
        return repository.retirar(fechaSalida, placa);
    }

    /**
     * Calcula las horas que el vehiculo estuvo en el parqueadero
     *
     * @param placa
     * @return
     */
    public double calcularCobro(String placa) {
        RegistroVehiculo temporal = new RegistroVehiculo();
        temporal = repository.obtenerVehiculo(placa);
        if (temporal == null) {
            return -1;
        }
        String fechaE = temporal.getFechaEntrada();
        String fechaS = temporal.getFechaSalida();
        //System.out.println("entrada:"+fechaE);
        //System.out.println("salida:"+fechaS);
        try {
            Date fechaEnt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(fechaE);
            Date fechaSal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(fechaS);
            int cantMin = (int) (fechaSal.getTime() - fechaEnt.getTime()) / 60000;
            if (cantMin < 0) {
                cantMin = cantMin * (-1);
            }
            //System.out.println("tiempo: "+cantMin);
            String tipo = temporal.getTipo();
            double precio = this.precioReal(tipo, cantMin, temporal.getNumTicket());
            if (precio == -1) {
                return -1;
            }
            factura(temporal, precio);
            return precio;
        } catch (java.text.ParseException e) {
            e.getMessage();
            return -1;
        }
    }

    /**
     * Me genera la factura del vehiculo
     *
     * @param v
     * @param cobro
     */
    private void factura(RegistroVehiculo v, double cobro) {
        System.out.println("FACTURACION");
        System.out.println("placa: " + v.getPlaca());
        System.out.println("modelo: " + v.getModelo());
        System.out.println("propietario: " + v.getPropietario());
        System.out.println("tipo: " + v.getTipo());
        System.out.println("fecha entrada: " + v.getFechaEntrada());
        System.out.println("fecha salida: " + v.getFechaSalida());
        System.out.println("cobro: " + cobro);
    }

    /**
     * Saca el precio dependiendo de cada tipo de Vehiculo
     *
     * @param tipo
     * @param minutos
     * @param ticket
     * @return
     */
    private double precioReal(String tipo, int minutos, String ticket) {
        double valor;
        int tiempoEnt = (int) (minutos / 60);
        double tiempoDec = minutos % 60;

        if (tipo.compareTo("RegistroMoto") == 0) {
            valor = 1000;
            if (minutos > 60) {
                valor += Math.round((tiempoEnt - 1) * 500);
                if (tiempoDec > 0) {
                    valor += Math.round(500 * (tiempoDec / 60));

                }
            }
            return valor;
        }

        if (tipo.compareTo("RegistroCarro") == 0) {
            tiempoEnt = (int) (minutos / 60);
            tiempoDec = minutos % 60;
            valor = 2000;
            if (minutos > 60) {
                valor += Math.round((tiempoEnt - 1) * 1000);
                if (tiempoDec > 0) {
                    valor += Math.round(1000 * (tiempoDec / 60));
                }
            }
            return valor;
        }
        if (tipo.compareTo("RegistroCamion") == 0) {
            if (this.sorteo().compareTo(ticket) == 0) {
                System.out.println("ganador!!!");
                valor = 0.0;
                return valor;
            }
            tiempoEnt = (int) (minutos / 1440); //dia
            tiempoDec = minutos % 1440;
            valor = 15000;
            if ((minutos / 60) <= 12) {
                valor = 10000.0;
            } else if ((minutos / 60) > 24) {
                valor += Math.round((tiempoEnt - 1) * 15000);
                if (tiempoDec > 0) {
                    valor += Math.round(15000 * (tiempoDec / 1440));

                }

            }
            return valor;
        }
        return -1;
    }

    /**
     * Hace el sorteo si el tiket es igualm al numero el parqueo le sale gratis
     *
     * @return
     */
    public String sorteo() {
        String numString;
        Integer num = (int) Math.floor(Math.random() * (0 - 1000 + 1) + 1000);
        numString = num.toString();
        System.out.println("resultado sorteo: " + numString);
        return numString;
    }
}
