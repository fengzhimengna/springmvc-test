define(['angular', 'require', 'angular-route'], function (angular, require) {

    var app = angular.module('wdj', [
        'ngRoute'
    ]);

    app.config(['$routeProvider', '$controllerProvider',
        function($routeProvider, $controllerProvider) {
            $routeProvider.
                when('/user', {
                    templateUrl: 'resources/tpl/user/list.html',
                    controller: 'userController',
                    resolve: {
                    	keyName: function ($q) {
                            var deferred = $q.defer();
                            require(['resources/tpl/user/app.js'], function (controller) {
                                $controllerProvider.register('userController', controller);      //由于是动态加载的controller，所以要先注册，再使用
                                deferred.resolve();
                            });
                            return deferred.promise;
                        }
                    }
                }).
                otherwise({
                    redirectTo: '/user'      //angular就喜欢斜杠开头
                });
        }]);

    return app;
});