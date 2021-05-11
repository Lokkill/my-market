angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/mymarket';

    $scope.loadPage = function (page) {
        $http({
            url: contextPath + '/api/v1/list',
            method: 'GET',
            params: {
                p: page
            }
        }).then(function (response) {
            $scope.products = response.data;

            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.productsPage.totalPages) {
                maxPageIndex = $scope.productsPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);

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
          $scope.loadPage();
      });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/list', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.loadPage(1);
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.addToCartById = function (productId){
        $http.get(contextPath + "/api/v1/cart/add/" + productId)
            .then(function (response) {
                $scope.loadPage(1);
            });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + "/api/v1/cart/clear",
            method: 'GET'
        }).then(function (response){
            $scope.loadPage(1);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadPage(1);
});