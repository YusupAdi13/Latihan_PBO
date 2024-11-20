 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MyBook Hype
 */
public class Product {
    int product_id, product_price, product_cat_id;
    String product_name, product_desc;
    
    private Connection konek;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public Product()throws SQLException{
        koneksi koneksi = new koneksi();
        konek = koneksi.konekDB();
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_cat_id() {
        return product_cat_id;
    }

    public void setProduct_cat_id(int product_cat_id) {
        this.product_cat_id = product_cat_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }
    public void tambahProduct() {
        String quey = "INSERT INTO products VALUES(?,?,?,?,?)";
        try {
            ps = konek.prepareStatement(quey);
            ps.setInt(1, product_id);
            ps.setString(2, product_name);
            ps.setString(3, product_desc);
            ps.setInt(4, product_price);
            ps.setInt(5, product_cat_id);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "product Berhasil Ditambahkan");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null,"product Gagal Ditambahkan");
        
        }
    }
    public ResultSet tampilProduk(){
        query = "SELECT "
                + "p.produk_id,"
                + "p.produk_name,"
                + "p.produk_desc,"
                + "p.produk_price,"
                + "c.category_name "
                + "FROM products p "
                + "JOIN category c ON p.produk_card_id = c.category_id";
        try {
            st=konek.createStatement();
            rs=st.executeQuery(query);
            
        } catch(SQLException sQLException){
            JOptionPane.showMessageDialog(null, "Data Gagal Tampil");
        }
        return rs;
    }
    public void hapusProduk(){
        query = "DELETE FROM products WHERE produk_id = ?";
        try {
            ps = konek.prepareStatement(query);
            ps.setInt(1, product_id);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Product Berhasil Dihapus");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Product Gagal Dihapus");
        }
    }
    public void ubahProduct() throws SQLException{
        query = "UPDATE products "
                + "SET produk_name = ?,"
                + "produk_desc = ?,"
                + "produk_price = ?,"
                + "produk_card_id = ? "
                + "WHERE produk_id = ?";
        try {
            ps = konek.prepareStatement(query);
            ps.setString(1, product_name);
            ps.setString(2, product_desc);
            ps.setInt(3, product_price);
            ps.setInt(4, product_cat_id);
            ps.setInt(5, product_id);
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Product Berhasil Di ubah");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Product Gagal Di Ubah");
        }

    }
    
    public ResultSet autoID(){
        query = "SELECT produk_id FROM products ORDER BY produk_id DESC LIMIT 1";
        try{
           st = konek.createStatement();
           rs = st.executeQuery(query);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to fetch next category ID : " + ex.getMessage());
        }
        return rs;
    }
}