/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class CartItemMore extends CartIteam{

    public CartItemMore() {
    }

    public CartItemMore( int cartItemID, int cartID, int productID, String title, int quantity, String thumbnail, double price) {
        super(cartItemID, cartID, productID, title, quantity, thumbnail, price);
    }
    
}
