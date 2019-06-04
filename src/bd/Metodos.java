/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author fojomars
 * //
 */
public class Metodos {
//        public static void createNewDatabase(String fileName) {
// 
//        String url = "jdbc:sqlite:" + fileName;
// 
//        try (Connection conn = DriverManager.getConnection(url)) {
//            if (conn != null) {
//                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");
//            }
// 
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    
    /**
     * Este método sirve para conectar con la base de datos
     *
     * @return
     */
    public Connection conectar() {
        String url = "jdbc:sqlite:snake.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión establecida");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
        }
        return conn;
    }

    /**
     * Este método crea la tabla en la base de datos. En caso de que ya exista
     * la borra
     */
    public void crearTablaJugador() {
        String sql1 = "DROP TABLE IF EXISTS datos;\n";
        String sql2 = "CREATE TABLE IF NOT EXISTS datos (\n"
                + "id integer PRIMARY KEY,\n"
                + "nombre text NOT NULL,\n"
                + "puntuacion integer NOT NULL"
                + ");";
        try (Connection conn = this.conectar();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql1);
            stmt.execute(sql2);
            System.out.println("Tabla creada con exito");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla");
        }
    }

    /**
     * Este méto sirve para insertar datos en la tabla clientes
     *
     * @param id
     * @param nombre
     * @param puntuacion
     */
    public void insertarJugador(int id, String nombre, int puntuacion) {
        String sql = "INSERT INTO datos(id,nombre,puntuacion) VALUES(?,?,?)";
        try (Connection conn = this.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setInt(3, puntuacion);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Jugador registrado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir los datos o puede que el id ya este selecionado");
        }
        
    }
    
    /**
     * Método para borrar datos de la tabla clientes
     * @param id
     */
    public void borrarJugador(int id) {
        String sql = "DELETE FROM datos WHERE id=?";
        try (Connection conn = this.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente");
        }
    }
    
    public void modificarCliente(String nombre, int referencia) {
        String sql = "UPDATE datos SET nombre = ? "
                + "WHERE id = ?";
        try (Connection conn = this.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, referencia);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Jugador modificado correctamente");
        } catch (SQLException e) {
            System.out.println("Erro al modificar el jugador");
        }
    }
}
