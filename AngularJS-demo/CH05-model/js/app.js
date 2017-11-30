var App = angular.module("App", []);

App.controller("FirstCtrl", function ($scope) {
    $scope.data = {
        deleted: false,
        important: false,
        error: false
    };

    $scope.colors = [
        {name: '黑色', color: 'black'},
        {name: '白色', color: 'white'},
        {name: '红色', color: 'red'},
        {name: '蓝色', color: 'blue'},
        {name: '黄色', color: 'yellow'}
    ];

    //保存选中的状态，默认颜色设置为黑色
    $scope.colorChosen = $scope.colors[0];
});