var ClientController = ['$scope','$state','Owner',function($scope,$state,Owner) {
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#clients").offset().top
		}, 1000);
	});

	$scope.clients = Owner.query();
}];

var ClientDetailsController = ['$scope','$rootScope','$stateParams','Owner', function($scope,$rootScope,$stateParams,Owner) {

	var currentId = $stateParams.id;
	var nextId = parseInt($stateParams.id) + 1;
	var prevId = parseInt($stateParams.id) - 1;

	$scope.prevOwner = Owner.get({id:prevId});
	$scope.nextOwner = Owner.get({id:nextId});
	$scope.currentOwner = Owner.get($stateParams);

	$scope.saveOwner = function(){
		owner = $scope.currentOwner;
		Owner.save(owner);
	}
	
	$scope.addEmployee = function() {
		$scope.employeeFormHeader = "Add a new Employee";
		$scope.currentEmployee = {type:{}};
	}
	
	$scope.editEmployee = function(id) {
		$scope.employeeFormHeader = "Edit Employee";
		for(i = 0;i < $scope.currentOwner.employees.length; i++) {
			if($scope.currentOwner.employees[i].id == id) {
				$scope.currentEmployee = $scope.currentOwner.employees[i];
				break;
			}
		}
	};

}];

var AddOwnerController = ['$scope','Owner', function($scope,Owner) {

	$scope.owner={id:0,employees:[]};

	$scope.addOwner = function(){
		Owner.save($scope.owner);
	}
}];