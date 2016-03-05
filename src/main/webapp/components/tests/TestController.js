var TestController = ['$scope','$http','Test', function ($scope, $http, Test) {
	
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#tests").offset().top
		}, 1000);
	});
	
	$scope.tests = Test.query();
	
}];