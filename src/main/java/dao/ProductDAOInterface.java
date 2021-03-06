/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author peani371
 */
public interface ProductDAOInterface {

    void deleteProduct(Product oldProd);

    Collection<Product> filterProductCategory(String categoryToFilter);

    Collection<String> getCategories();

    Collection<Product> getProducts();

    void saveProduct(Product newProd);

    Product searchForProduct(String searchID);
    
}
