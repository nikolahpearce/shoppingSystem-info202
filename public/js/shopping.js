"use strict";
// Sale Item class
class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.price;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

// Shopping cart class
class ShoppingCart {

    constructor() {
        this.items = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.items) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.items;
    }

    addItem(item) {
        this.items.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.items) {
            total += item.getItemTotal();
        }
        return total;
    }

}

var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

// factories
module.factory('productDAO', function ($resource) {
    return $resource('/api/products/:id');
});

module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:cat');
});

module.factory('registerDAO', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username');
});

module.factory('saleDAO', function ($resource) {
    return $resource('/api/sales');
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

// Product controller
module.controller('ProductController', function (productDAO, categoryDAO) {

    // load the products
    this.products = productDAO.query();

    // load the categories
    this.categories = categoryDAO.query();

    // click handler for the category filter buttons
    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"cat": selectedCat});
    };

    this.displayAll = function () {
        this.products = productDAO.query();
    };

});

// Customer controller
module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window) {

    this.signInMessage = "Please sign in to continue.";
    this.registerMessage = "";
    this.signedIn = false;
    this.welcome = "";
    let ctrl = this;

    this.registerCustomer = function (customer) {

        registerDAO.save(null, customer).$promise.then(function(result) { 
                // success
                console.log(customer);
                //ctrl.signInMessage = "Account created successfully. Please sign in to continue.";
                $window.location.href = '/signin.html';
            },
            function (error) {
                // fail
                ctrl.registerMessage = error.data;
            });

    };

    this.signIn = function (username, password) {
        signInDAO.get({'username': username},
            function (customer) {
                // success
                $sessionStorage.customer = customer;
                $window.location.href = '.';
                },
            function () {
                // fail
                ctrl.signInMessage = 'Sign in failed. Please try again.';
            });
    };

            this.checkSignIn = function () {
                if ($sessionStorage.customer) {
                    ctrl.welcome += "Welcome, ";
                    ctrl.welcome += $sessionStorage.customer.firstName;
                    ctrl.welcome += "!";
                    ctrl.signedIn = true;
                }
            };

            this.signOut = function () {
                ctrl.signedIn = false;
                //delete $sessionStorage.customer;
                $sessionStorage.$reset();
                $window.location.href = '.';
            };

        });


// Shopping cart controller
module.controller('CartController', function (cart, saleDAO, $sessionStorage, $window) {

    this.quantityMessage = "";

    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;


    this.buy = function (product) {
        //alert("in buy product");
        $sessionStorage.selectedProduct = product;
        $window.location.href = '/purchasing.html';
    };

    this.addToCart = function (quantity) {
        //alert("in Add To Cart function. ");


        let prod = $sessionStorage.selectedProduct;
        let newItem = new SaleItem(prod, quantity);

        if (quantity <= 0) {
            this.quantityMessage = "Please enter a number greater than zero.";
        } else if (quantity > this.selectedProduct.quantityInStock) {
            this.quantityMessage = "You cannot purchase a quanity greater than that available.";
        } else {

            cart.addItem(newItem);
            $sessionStorage.cart = cart;
            $window.location.href = '/products.html';
        }


    };

    this.checkOutCart = function () {
        //alert("in check out cart part");
        let cust = $sessionStorage.customer;
        cart.setCustomer(cust);
        saleDAO.save(null, cart);

        delete $sessionStorage.cart;
        $window.location.href = '/confirmation.html';

    };
});
