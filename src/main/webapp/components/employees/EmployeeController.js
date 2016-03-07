var EmployeeController = ['$scope', 'Employee', function($scope, Employee) {

	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#employees").offset().top
		}, 1000);
	});

	$scope.employees = Employee.query();

}];

/*
 * EmployeeSaveController will act as AFTER-SAVE CONTAINER for employee
 * 
 * AFTER SAVE CONTAINTER:
 * 		action after add/edit/delete
 * 
 */
var EmployeeSaveController = ['$scope','ClientEmployee',function($scope, ClientEmployee,Employee) {	
	
	// save employee [AFTER-SAVE]
	$scope.saveEmployee = function(){		
		current_id = $scope.currentClient._id;		
		ClientEmployee.save({_id:current_id},$scope.currentEmployee,function(employee) {
			var newEmployee = true;
			for (i=0;i<$scope.currentClient.employees.length;i++) {
				if($scope.currentClient.employees[i].id == employee.id) {
					$scope.currentClient.employees[i] == employee;
					newEmployee = false;
					break;
				}
			}
			if(newEmployee) {
				$scope.currentClient.employees.push(employee);
			}
		});
	};
	
	// delete employee [AFTER-SAVE]
	$scope.deleteEmployee = function(){		
		//alert('cid : '+$scope.currentClient._id+', eid : '+$scope.currentEmployee.empId);				
		ClientEmployee.remove({_id : $scope.currentClient._id, empId : $scope.currentEmployee.empId});
	};
	
}];

/*
 * EmployeeDetailsController will act as PRE-FILLED CONTAINER for test
 * 
 * PRE FILLED CONTAINER:
 * 		manipulate data
 * 
 */
var EmployeeDetailsController = ['$scope','$rootScope','$stateParams','Employee', function($scope, $rootScope, $stateParams, Employee) {	
	
	var currentId = $stateParams.id;
	$scope.currentEmployee = Employee.get($stateParams);
	
	// add test [PRE-FILLED MANIPULATOR]
	$scope.addTest = function() {
		$scope.testFormHeader = "Add a new Test";
		$scope.currentTestResult = {type:{}};
	}
	
	// edit test [PRE-FILLED MANIPULATOR]
	$scope.editTest = function(id) {		
		$scope.testFormHeader = "Edit Test";
		for(i = 0;i < $scope.currentEmployee.tests.length; i++) {			
			if($scope.currentEmployee.tests[i].testId == id) {
				$scope.currentTest = $scope.currentEmployee.tests[i];
				break;
			}
		}
	};
	
	// delete test [PRE-FILLED MANIPULATOR]
	$scope.deleteTest = function(id) {		
		$scope.testFormHeader = "Delete Test";
		for(i = 0;i < $scope.currentEmployee.tests.length; i++) {
			
			// ignore null
			if($scope.currentEmployee.tests[i] == null)
				continue;
			
			if($scope.currentEmployee.tests[i].testId == id) {
				$scope.currentTest = $scope.currentEmployee.tests[i];
				break;
			}
		}
	};
}];
