var bookApp = angular.module('bookApp', ['ngRoute']);

// 配置路由
bookApp.controller('HomeController', function ($scope, $route) {
    $scope.$route = $route;
}).config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'HomeController',
            templateUrl: "bookList.html"
        })
        .when('/edit', {
            controller: 'HomeController',
            templateUrl: "bookEdit.html"
        })
        .otherwise({
            redirectTo: '/'
        });
}]);