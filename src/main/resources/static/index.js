angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/mymarket';

    $scope.init = function () {
        $http.get(contextPath + "/api/v1/list")
            .then(function (response) {
                $scope.products = response.data;
            });
        $http.get(contextPath + "/api/v1/cart")
            .then(function (response) {
                $scope.cartProducts = response.data;
            });
    };


    $scope.deleteProductById = function (productId) {
      $http({
          url: contextPath + "/api/v1/list/" + productId,
          method: 'DELETE'
      }).then(function (response){
          $scope.init();
      });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/list', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.init();
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.addToCartById = function (productId){
        $http.get(contextPath + "/api/v1/cart/add/" + productId)
            .then(function (response) {
                $scope.init();
            });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + "/api/v1/cart/clear",
            method: 'GET'
        }).then(function (response){
            $scope.init();
        });
    };

    $scope.init();
});