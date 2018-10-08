package Dao;

import Bean.Macarrao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
public class MacarraoDAO {
    
    public MacarraoDAO(){
        
    }

    public int cadastrar(Macarrao macarrao) {
       Connection conexao = Conexao.conectar();
       if(conexao != null){
           String sql = "INSERT INTO macarroes"
                   + "\n(tipo, peso, marca, aldente)"
                   + "\nVALUES (?,?,?,?)";
           try {
               PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
               ps.setString(1, macarrao.getTipo());
               ps.setDouble(2, macarrao.getPeso());
               ps.setString(3, macarrao.getMarca());
               ps.setBoolean(4, macarrao.isAldente());
               ps.execute();
               ResultSet rs = ps.getGeneratedKeys();
               if (rs.next()) {
                  return rs.getInt(1);
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }finally{
               Conexao.desconectar();
           }
       }
           return -1;
   }

   public Macarrao obterPeloId(int identificador) {
       Connection conexao = Conexao.conectar();
       if(conexao != null) {
           String sql = "SELECT * FROM macarroes WHERE id = ?";
           try{
               PreparedStatement ps = conexao.prepareStatement(sql);
               ps.setInt(1, identificador);
               ResultSet rs = ps.executeQuery();
               if(rs.next()){
                   Macarrao macarrao = new Macarrao();
                   macarrao.setId(rs.getInt("id"));
                   macarrao.setTipo(rs.getString("tipo"));
                   macarrao.setMarca(rs.getString("marca"));
                   macarrao.setPeso(rs.getDouble("peso"));
                   macarrao.setAldente(rs.getBoolean("aldente"));
                   return macarrao;
               }
           } catch (SQLException e) { 
               e.printStackTrace();
           }finally{
               Conexao.desconectar();
           }
       }
       return null;
   }

    public boolean alterar(Macarrao magali) {
        Connection conexao = Conexao.conectar();
        if(conexao != null) {
            String sql = "UPDATE macarroes SET tipo = ?, marca = ?, peso = ?, aldente = ? WHERE id = ?";
            try{
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, magali.getTipo());
                ps.setString(2, magali.getMarca());
                ps.setDouble(3, magali.getPeso());
                ps.setBoolean(4, magali.isAldente());
                ps.setInt(5, magali.getId());
                return ps.executeUpdate() == 1;
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                Conexao.desconectar();
            }
        }
        
        return false;
    }

    public boolean apagar(int identificador) {
       Connection conexao = Conexao.conectar();
       if(conexao != null) {  
         String sql = "DELETE FROM macarroes WHERE id = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, identificador);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        
       }
        return false;
    }
   
   }

   



