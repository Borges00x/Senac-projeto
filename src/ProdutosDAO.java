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
import java.sql.Date;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        
        try 
        {
            
            prep = conn.prepareStatement("Insert into produtos (nome, valor, status) values (?, ?, ?) ");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Dados Salvos com sucesso!");
            
        } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro! tente novamente mais tarde.");
            }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(String id){
        
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement("select * from produtos Where id LIKE ?");
            
            prep.setString(1, "%" +id+ "%");
            
            resultset = prep.executeQuery();
            
            while(resultset.next()) 
            {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
                return listagem;
                
        } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Erro:"+ex);
                
                return null;
            }
        
        
    }
    
    
    
        
}

