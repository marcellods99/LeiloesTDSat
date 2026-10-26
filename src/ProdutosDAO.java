/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            return true;
        } catch (Exception erro) {
            System.out.println("Erro ao cadastrar: " + erro.getMessage());
            return false;
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (Exception erro) {
            System.out.println("Erro ao listar: " + erro.getMessage());
        }
        
        return listagem;
    }
    
    public void venderProduto(int id){
        
        conn = new conectaDAO().connectDB();
        
        try {
            prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            prep.executeUpdate();
        } catch (Exception erro) {
            System.out.println("Erro ao vender: " + erro.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
        conn = new conectaDAO().connectDB();
        ArrayList<ProdutosDTO> lista_vendidos = new ArrayList<>();
        
        try {
            prep = conn.prepareStatement("SELECT * FROM produtos WHERE status = ?");
            prep.setString(1, "Vendido");
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                lista_vendidos.add(produto);
            }
        } catch (Exception erro) {
            System.out.println("Erro ao listar vendidos: " + erro.getMessage());
        }
        
        return lista_vendidos;
    }
    
    
    
        
}

