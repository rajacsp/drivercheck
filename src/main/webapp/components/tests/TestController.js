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


var TestPassController = ['$scope','$rootScope','$stateParams','Client', function($scope,$rootScope,$stateParams,Client) {
	var currentId = $stateParams.id;
	$scope.currentClient = Client.get($stateParams);
	
	$scope.getTestDetails = function(){		
	    var totalTestPassed = 0;
	    var totalTests = 0;
	    if(typeof $scope.currentClient.employees != "undefined"){
	    	for(var i = 0; i < $scope.currentClient.employees.length; i++){
	    		var employee = $scope.currentClient.employees[i];
	        
	    		if(employee == null || employee.tests == null)
	    			continue;
	    		
	    		totalTests += employee.tests.length;
	    		
	    		//console.log('trap : '+totalTests);
	        
	    		for(var j=0; j<employee.tests.length;j++){
	    			var test = employee.tests[j];
	    			var testResult = test.result;        	
	        	
	    			if(testResult ==  'PASS'){
	    				totalTestPassed++;
	    			}
	    		}
	    	}
	    }	   		   	
	    
	    //console.log('totalTests : '+totalTests+', totalTestPassed  : '+totalTestPassed);
	    
	   	var tests = {"total" : totalTests, "passed" : totalTestPassed};
	   	return tests;
	}	
}];


var TestsAllController = ['$scope', 'Test',function($scope, Test) {
	
	$scope.tests = Test.query();
	
	$scope.predicate = 'testId';
	$scope.reverse = true;
	$scope.order = function(predicate) {
	    $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
	    $scope.predicate = predicate;
	};
}];
