var TestController = ['$scope','$http','Test', function ($scope, $http, Test) {
	
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#tests").offset().top
		}, 1000);
	});
	
	$scope.tests = Test.query();
	
}];

var TestDetailsController = ['$scope','EmployeeTest',function($scope, EmployeeTest, Employee) {	
	
	// save test [AFTER-SAVE]
	$scope.saveTest = function(){		
		current_id = $scope.currentEmployee.empId;
		
		EmployeeTest.save({empId:current_id},$scope.currentTest,function(test) {
			var newTest = true;
			for (i=0;i<$scope.currentEmployee.tests.length;i++) {
				if($scope.currentEmployee.tests[i].id == test.id) {
					$scope.currentEmployee.tests[i] == test;
					newTest = false;
					break;
				}
			}
			if(newTest) {
				$scope.currentEmployee.tests.push(test);
			}
		});
	};
	
	// delete employee [AFTER-SAVE]
	$scope.deleteTest = function(){		
		alert('empId : '+$scope.currentEmployee.empId+', testId : '+$scope.currentTest.testId);				
		EmployeeTest.remove({empId : $scope.currentEmployee.empId, testId : $scope.currentTest.testId});
	};
	
}];