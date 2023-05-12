package com.forproject2.forproject2ipc2_2023.Conexion;


import lombok.*;

import  java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conexion {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void conectar(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "proyecto2IPC2_2023";
        String password = "eiler123";
        String url = "jdbc:mysql://localhost:3306/centro_medico";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion" + e);
        }

    }
    public void cerrarConexion(){
            try {
                connection.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

            }
    }

}
