"use strict";

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
module.controller('CustomerController', function (registerDAO) {
    this.customers = registerDAO.query();
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer);
        console.log(customer);
    };

});


// Shopping cart controller