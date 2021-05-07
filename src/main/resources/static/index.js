angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/mymarket';

    $scope.init = function () {
        $http.get(contextPath + "/api/v1/list")
            .then(function (response) {
                $scope.products = response.data;
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

    $scope.init();
});