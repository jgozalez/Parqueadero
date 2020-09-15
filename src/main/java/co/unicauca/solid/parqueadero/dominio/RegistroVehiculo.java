/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.solid.parqueadero.dominio;

import java.util.Date;

/**
 *
 * @author EdynsonMJ
 */
public class RegistroVehiculo {

    //ATRIBUTOS
    private String placa;
    private String modelo;
    private String propietario;
    private String fechaEntrada;
    private String fechaSalida;
    String tipo;
    private String numTicket;

    //CONSTRUCTORES
    public RegistroVehiculo() {
    }

    public RegistroVehiculo(String placa, String modelo, String propietario, String fechaEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
        this.fechaEntrada = fechaEntrada;
    }

    public void tipoVehiculo() {
    }
    //SET AND GET

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNumTicket() {
        return numTicket;
    }

    public void setNumTicket(String numTicket) {
        this.numTicket = numTicket;
    }

}
