var EmployeeController = ['$scope', 'Employee', function($scope, Employee) {

	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#employees").offset().top
		}, 1000);
	});

	$scope.employees = Employee.query();

}];

var EmployeeDetailsController = ['$scope','EmployeeType','ClientEmployee',function($scope,EmployeeType,ClientEmployee,Employee) {
	$scope.employeeTypes = EmployeeType.query();
	
	$scope.save = function(){
		currentClientId = $scope.currentClient.id;

		for (i=0; i<$scope.employeeTypes.length; i++){
			if ($scope.employeeTypes[i].id == $scope.currentEmployee.type.id){
				$scope.currentEmployee.type.name = $scope.employeeTypes[i].name;
				break;
			}
		}
		
		ClientEmployee.save({clientId:currentClientId},$scope.currentEmployee,function(employee) {
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
}];