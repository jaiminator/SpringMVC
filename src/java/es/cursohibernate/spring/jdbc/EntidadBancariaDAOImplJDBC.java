package es.cursohibernate.spring.jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import es.cursohibernate.spring.dao.EntidadBancariaDAO;
import es.cursohibernate.spring.negocio.EntidadBancaria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author alumno
 */
public class EntidadBancariaDAOImplJDBC implements EntidadBancariaDAO {
    
    
    
    @Override
    public void delete (int idEntidadBancaria) {
        
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_bancario","root", "root");
            String deleteTableSQL = "DELETE FROM entidadbancaria WHERE idEntidadBancaria = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(deleteTableSQL);
            preparedStatement.setInt(1, idEntidadBancaria);

            //EJECUTAR LA CONSULTA DELETE
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("ERROR AL BORRAR EL REGISTRO", ex);
        }
        
    }    
    
    @Override
    public void insert(EntidadBancaria entidadBancaria) {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_bancario","root", "root");
            String insertTableSQL = "INSERT INTO entidadbancaria VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, entidadBancaria.getIdEntidadBancaria());
            preparedStatement.setString(2, entidadBancaria.getNombre());
            preparedStatement.setString(3, entidadBancaria.getCodigoEntidad());
            preparedStatement.setTimestamp(4, getCurrentTimeStamp());

            //EJECUTAR LA CONSULTA INSERT
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("ERROR AL INSERTAR REGISTRO", ex);
        }
        
    }
    
    @Override
    public void update(EntidadBancaria entidadBancaria) {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_bancario","root", "root");
            String updateTableSQL = "UPDATE entidadbancaria SET nombre = ?, codigoEntidad = ? WHERE idEntidadBancaria = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, entidadBancaria.getNombre());
            preparedStatement.setString(2, entidadBancaria.getCodigoEntidad());
            preparedStatement.setInt(3, entidadBancaria.getIdEntidadBancaria());

            //EJECUTAR LA CONSULTA UPDATE
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("ERROR AL MODIFICAR REGISTRO", ex);
        }
        
    }
    
    @Override
    public List<EntidadBancaria> findAll() {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_bancario","root", "root");
            String selectSQL = "SELECT * FROM entidadbancaria";

            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL );

            List<EntidadBancaria> entidadesBancariasList = new ArrayList<>();

            while (rs.next()) {
                EntidadBancaria entidadBancaria = new EntidadBancaria();
                entidadBancaria.setIdEntidadBancaria(rs.getInt("idEntidadBancaria"));
                entidadBancaria.setNombre(rs.getString("nombre"));
                entidadBancaria.setCodigoEntidad(rs.getString("codigoEntidad"));
                entidadBancaria.setFechaCreacion(rs.getDate("fechaCreacion"));

                entidadesBancariasList.add(entidadBancaria);
            }
            return entidadesBancariasList;
        } catch (Exception ex) {
            throw new RuntimeException("ERROR AL MOSTRAR LISTA DE REGISTROS", ex);
        }
        
    }
    
    @Override
    public EntidadBancaria get(int idEntidadBancaria) {
        try {
            
            InitialContext initCtx=new InitialContext();;
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource)envCtx.lookup("jdbc/sistema_bancario");
            Connection connection = dataSource.getConnection();
            
            String selectParcialSQL = "SELECT * FROM entidadbancaria WHERE idEntidadBancaria = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectParcialSQL);
            preparedStatement.setInt(1, idEntidadBancaria);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
                EntidadBancaria entidadBancaria = new EntidadBancaria();
                entidadBancaria.setIdEntidadBancaria(rs.getInt("idEntidadBancaria"));
                entidadBancaria.setNombre(rs.getString("nombre"));
                entidadBancaria.setCodigoEntidad(rs.getString("codigoEntidad"));
                entidadBancaria.setFechaCreacion(rs.getDate("fechaCreacion"));

            connection.close();
            
            return entidadBancaria;
        } catch (Exception ex) {
            throw new RuntimeException("ERROR AL MOSTRAR REGISTRO", ex);
        }
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp() {
 
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
 
    }
    
}
