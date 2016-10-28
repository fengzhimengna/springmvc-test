'use strict'
var app = angular.module('myApp', [ 'ngRoute','ngResource']);
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/user/list', {
		templateUrl : 'resources/tpl/user/list.html',
		controller : function($scope,$http){
			$http.get("user/list").then(function (response) {
		        $scope.users = response.data;
		    });
		}
	}).when('/resource/list', {
		templateUrl : 'resources/tpl/resource/list.html',
		controller : function($scope,$http){
			$http.get("user/list").then(function (response) {
		        $scope.users = response.data;
		    });
		}
	});
} ]);