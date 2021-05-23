angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/mymarket';

    $scope.loadPage = function (page) {
        $http({
            url: contextPath + '/api/v1/list',
            method: 'GET',
            params: {
                p: page
            }
        }).then(function (response) {
            $scope.productsPage = response.data;

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
    };

    $scope.loadCart = function (page) {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cartDto = response.data;
        });
    };

    $scope.deleteProductById = function (productId) {
        $http({
            url: contextPath + '/api/v1/list/' + productId,
            method: 'DELETE'
        }).then(function (response) {
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

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
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

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = "Bearer " + response.data.token;
                    $localStorage.myMarketCurrentUser = {username: $scope.user.username, token: response.data.token};

                    console.log(response.data.token);
                    $scope.curretUsername = $localStorage.myMarketCurrentUser.username;
                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {

            });
    };

    $scope.checkout = function (){
        $scope.user_order = {username : $scope.curretUsername, order : $scope.cartProducts};
        $http.post(contextPath + '/api/v1/cart/checkout', $scope.user_order)
            .then(function (){
                $scope.cartProducts = null;
                $scope.loadCart();
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.myMarketCurrentUser;
        $scope.curretUsername = null;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.myMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.myMarketCurrentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.myMarketCurrentUser.token;
    }

    $scope.loadPage(1);
    $scope.loadCart();
});