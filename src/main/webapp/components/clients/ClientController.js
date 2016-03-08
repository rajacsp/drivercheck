var ClientController = ['$scope','$state','Client',function($scope,$state,Client) {
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#clients").offset().top
		}, 1000);
	});

	$scope.clients = Client.query();
	
	// Sorting
	$scope.predicate = 'name';
	$scope.reverse = false;
	$scope.order = function(predicate) {
	    $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
	    $scope.predicate = predicate;
	};
}];

/*
 * Client Controller will act as as PRE-FILLED CONTAINER for employee and AFTER-SAVE CONTAINER for client
 * 
 * PRE FILLED CONTAINER:
 * 		manipulate data
 * 
 * AFTER SAVE CONTAINTER:
 * 		action after add/edit/delete
 * 
 */
var ClientDetailsController = ['$scope','$rootScope','$stateParams','Client', function($scope, $rootScope, $stateParams, Client) {

	var currentId = $stateParams.id;
	var nextId = parseInt($stateParams.id) + 1;
	var prevId = parseInt($stateParams.id) - 1;

	$scope.prevClient = Client.get({id:prevId});
	$scope.nextClient = Client.get({id:nextId});
	$scope.currentClient = Client.get($stateParams);
	
	

	// save client [AFTER-SAVE]
	$scope.saveClient = function(){
		client = $scope.currentClient;
		Client.save(client);
	}
	
	// delete client [AFTER-SAVE]
	$scope.deleteClient = function(){
		client = $scope.currentClient;		
		Client.remove({_id : $scope.currentClient._id});
	}	
	
	// add employee [PRE-FILLED MANIPULATOR]
	$scope.addEmployee = function() {
		$scope.employeeFormHeader = "Add a new Employee";
		$scope.currentEmployee = {type:{}};
	}
	
	// edit employee [PRE-FILLED MANIPULATOR]
	$scope.editEmployee = function(id) {		
		$scope.employeeFormHeader = "Edit Employee";
		for(i = 0;i < $scope.currentClient.employees.length; i++) {			
			if($scope.currentClient.employees[i].empId == id) {
				$scope.currentEmployee = $scope.currentClient.employees[i];
				break;
			}
		}
	};
	
	// delete employee [PRE-FILLED MANIPULATOR]
	$scope.deleteEmployee = function(id) {		
		$scope.employeeFormHeader = "Delete Employee";
		for(i = 0;i < $scope.currentClient.employees.length; i++) {
			
			// ignore null
			if($scope.currentClient.employees[i] == null)
				continue;
			
			if($scope.currentClient.employees[i].empId == id) {
				$scope.currentEmployee = $scope.currentClient.employees[i];
				break;
			}
		}
	};	
	
	$scope.getTotalTests = function(){
	    var totalTests = 0;
	    if(typeof $scope.currentClient.employees != "undefined"){
	    	for(var i = 0; i < $scope.currentClient.employees.length; i++){
	    		var employee = $scope.currentClient.employees[i];
	        
	    		if(employee == null || employee.tests == null)
	    			continue;
	    		
	    		totalTests += employee.tests.length;	        
	    	}
	    }
	   	return totalTests;
	}

}];

var AddClientController = ['$scope','Client', function($scope,Client) {

	$scope.client={id:0,employees:[]};

	$scope.addClient = function(){
		Client.save($scope.client);
	}
}];