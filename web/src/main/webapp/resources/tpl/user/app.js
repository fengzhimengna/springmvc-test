
define(['angular'], function (angular) {
	
	return function($scope,$http,$location){
		$http.get("user/list").then(function (response) {
	        $scope.users = response.data;
	    });
		$scope.detail = function(id){
			$location.path('user/get/'+id);
		}
		
	}
});