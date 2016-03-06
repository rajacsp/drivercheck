var EmployeeController = ['$scope', 'Employee', function($scope, Employee) {

	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#employees").offset().top
		}, 1000);
	});

	$scope.employees = Employee.query();

}];

var EmployeeDetailsController = ['$scope','ClientEmployee',function($scope, ClientEmployee,Employee) {	
	
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
	
	$scope.deleteEmployee = function(){		
		//alert('cid : '+$scope.currentClient._id+', eid : '+$scope.currentEmployee.empId);				
		ClientEmployee.remove({_id : $scope.currentClient._id, empId : $scope.currentEmployee.empId});
	};
	
}];