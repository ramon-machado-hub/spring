package br.ifs.web1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] p) {
        try {
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuario = "postgres";
            String senha = "abcd@1234";
            //Class.forName(driver);
            Connection con = DriverManager.getConnection(url, usuario, senha);

//            //Inserção
//            String login = "zezinho3";
//            String nome = "Jose Tavares";
//            String email = "ze@tavares.com";
//            String pass = "123456";
//            String sqlInsert = "insert into seguranca.usu_usuario (usu_tx_nome, usu_tx_login, usu_tx_email, usu_tx_senha) values (?, ? ,? ,? )";
//            PreparedStatement psInsert = con.prepareStatement(sqlInsert);
//            psInsert.setObject(1, nome);
//            psInsert.setObject(2, login);
//            psInsert.setObject(3, email);
//            psInsert.setObject(4, pass);
//            //psInsert.execute();
//
//            String sqlUpdate = "update seguranca.usu_usuario set usu_tx_email=? where usu_nr_id=?";
//            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
//            psUpdate.setObject(1, "teste@email.com");
//            psUpdate.setObject(2, 1);
//            psUpdate.execute();
//
//            String sqlDelete = "delete from seguranca.usu_usuario where usu_nr_id=?";
//            PreparedStatement psDelete = con.prepareStatement(sqlDelete);
//            psDelete.setObject(1, 1);
//            psDelete.execute();
//
//
//            String sql = "select * from seguranca.usu_usuario";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getString("usu_tx_nome"));
//                System.out.println(rs.getInt("usu_nr_id"));
//            }
//            rs.close();
//            ps.close();
//            con.close();
//            System.out.println(con.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
