var EmployeeController = ['$scope', 'Employee', function($scope, Employee) {

	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#employees").offset().top
		}, 1000);
	});

	$scope.employees = Employee.query();

}];

var EmployeeDetailsController = ['$scope','EmployeeType','OwnerEmployee',function($scope,EmployeeType,OwnerEmployee,Employee) {
	$scope.employeeTypes = EmployeeType.query();
	
	$scope.save = function(){
		currentOwnerId = $scope.currentOwner.id;

		for (i=0; i<$scope.employeeTypes.length; i++){
			if ($scope.employeeTypes[i].id == $scope.currentEmployee.type.id){
				$scope.currentEmployee.type.name = $scope.employeeTypes[i].name;
				break;
			}
		}
		
		OwnerEmployee.save({ownerId:currentOwnerId},$scope.currentEmployee,function(employee) {
			var newEmployee = true;
			for (i=0;i<$scope.currentOwner.employees.length;i++) {
				if($scope.currentOwner.employees[i].id == employee.id) {
					$scope.currentOwner.employees[i] == employee;
					newEmployee = false;
					break;
				}
			}
			if(newEmployee) {
				$scope.currentOwner.employees.push(employee);
			}
		});
	};
}];