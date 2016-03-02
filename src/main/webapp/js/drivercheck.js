
function clients($scope, $http) {	
	$http.get('http://localhost:3030/drivercheck/api/clients').
    success(function(data) {
        $scope.clients = data; //remove wrapper parent
    });	
}


/*
var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) {
  var json =     
        [
                {
                   "title":"name of module1",
                   "description":"description of module1",
                   "weeks":[{"id":1, "title":"Week 01"}]
                },

                {
                   "title":"name of module2",
                   "description":"description of module2",
                   "weeks":[{"id":2, "title":"Week 02"},{"id":3,"title":"Week 03"}]
                }
        ]
  ;
  
  $scope.ocw = json;  
  
});
*/
