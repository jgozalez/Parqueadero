
package co.unicauca.solid.parqueadero.dominio.aceso;

import co.unicauca.solid.parqueadero.dominio.RegistroVehiculo;
import co.unicauca.solid.parqueadero.dominio.servicio.Negocio;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Juan Camilo Gonzalez, Edynson Muños Jimenez
 */
public class RegistroRepositorio implements IRegistroRepositorio   {

    private Integer num=0;
    private Connection conn;

    public RegistroRepositorio() {
        initDatabase();
    }
/**
 * Me guarda en la  base de datos el Vehiculo
 * @param newVehiculo
 * @return 
 */
    @Override
    public boolean save(RegistroVehiculo newVehiculo) {//fecha entrada

        try {
            //Validacion del RegistroVehiculo
             if (newVehiculo == null  
                     || newVehiculo.getPlaca().isBlank()
                     || newVehiculo.getModelo().isBlank() 
                     || newVehiculo.getModelo().isBlank()
                     || newVehiculo.getTipo().isBlank()
                     || newVehiculo.getFechaEntrada().isBlank()) {
            return false;
         
            }
            //this.connect();
            this.num+=1;
            newVehiculo.setNumTicket(num.toString());
            String sql = "INSERT INTO Vehiculo ( Placa, Modelo , Propietario , Tipo , FechaEntrada, FechaSalida, Ticket) "
                    + "VALUES ( ?, ?, ?, ?, ?, ? ,? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newVehiculo.getPlaca());
            pstmt.setString(2, newVehiculo.getModelo());
            pstmt.setString(3, newVehiculo.getPropietario());
            pstmt.setString(4, newVehiculo.getTipo());
            pstmt.setString(5, newVehiculo.getFechaEntrada());
            pstmt.setString(7, num.toString());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
/**
 * Me lista los Vehiculos
 * @return 
 */
    @Override
    public List<RegistroVehiculo> list() {//&fecha entrada, &fechaSalida
        List<RegistroVehiculo> vehiculos = new ArrayList<>();
        try {

            String sql = "SELECT Placa, Modelo, Propietario, FechaEntrada, FechaSalida, Tipo, Ticket FROM Vehiculo";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                RegistroVehiculo newVehiculo = new RegistroVehiculo();
                newVehiculo.setPlaca(rs.getString("Placa"));
                newVehiculo.setModelo(rs.getString("Modelo"));
                newVehiculo.setPropietario(rs.getString("Propietario"));
                newVehiculo.setTipo(rs.getString("Tipo"));
                newVehiculo.setFechaEntrada(rs.getString("FechaEntrada"));
                newVehiculo.setFechaSalida(rs.getString("FechaSalida"));
                newVehiculo.setNumTicket(rs.getString("Ticket"));
                vehiculos.add(newVehiculo);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculos;
    }
    /**
     * Retira el Vehiculo de la base de datos
     * @param fechaSalida
     * @param placa
     * @return 
     */
    @Override
    public boolean retirar(String fechaSalida, String placa){
        try{
            if (fechaSalida == null){
                return false;
            }
            String sql = "  UPDATE Vehiculo SET FechaSalida = ? WHERE Placa = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fechaSalida);
            pstmt.setString(2, placa);
            pstmt.executeUpdate();
            
        }catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
     * Me Registra el vehiculo
     * @param placa
     * @return 
     */
    @Override
    public RegistroVehiculo obtenerVehiculo(String placa){
        List<RegistroVehiculo> vehiculos = new ArrayList<>();
        vehiculos = this.list();
        String str;
        for(int i=0;i<vehiculos.size();i++){
            str=vehiculos.get(i).getPlaca();
            if(str.compareTo(placa)==0){
                RegistroVehiculo v = vehiculos.get(i);
                return v;
            }
        }
        return null;
    }
    /**
     * Crea la tabla vehiculo
     */
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Vehiculo (\n"
                + "	Placa text PRIMARY KEY,\n"
                + "	Modelo text NOT NULL,\n"
                +"      Propietario text NOT NULL,\n"
                +"      Tipo text NOT NULL, \n"
                +"      FechaEntrada text NOT NULL, \n"
                +"      FechaSalida text, \n"
                +"      Ticket text \n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./mydatabase.db";
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
