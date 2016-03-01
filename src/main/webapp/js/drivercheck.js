
function clients($scope, $http) {    
	var json = {
		"clients" : 
			[ 			 
				{
				    "id": 
				
				    {
				        "timestamp": 1456348835,
				        "machineIdentifier": 7745367,
				        "processIdentifier": 6532,
				        "counter": 16172145,
				        "time": 1456348835000,
				        "date": "2016-02-24T16:20:35.000-0500",
				        "timeSecond": 1456348835
				    },
				    "clientId": 102,
				    "name": "IGT",
				    "address": "23 street",
				    "city": "Toronto",
				    
				    "employees" : [			    	
					    {
					        "id": 
					        {
					            "timestamp": 1456343402,
					            "machineIdentifier": 4597077,
					            "processIdentifier": 15880,
					            "counter": 12266398,
					            "time": 1456343402000,
					            "date": "2016-02-24T14:50:02.000-0500",
					            "timeSecond": 1456343402
					        },
					        "firstName": "Raja",
					        "lastName": "Raman",
					        "empId": 0,
					        "address": "11 street",
					        "city": "Toronto",
					        "telephone": "23434343"
					    },
					    {
					    	"id": 
					        {
					            "timestamp": 1456343402,
					            "machineIdentifier": 4597077,
					            "processIdentifier": 15880,
					            "counter": 12266398,
					            "time": 1456343402000,
					            "date": "2016-02-24T14:50:02.000-0500",
					            "timeSecond": 1456343402
					        },
					        "firstName": "Raja",
					        "lastName": "Raman",
					        "empId": 0,
					        "address": "11 street",
					        "city": "Toronto",
					        "telephone": "23434343"
					    },
					    {
					    	"id": 
					        {
					            "timestamp": 1456343402,
					            "machineIdentifier": 4597077,
					            "processIdentifier": 15880,
					            "counter": 12266398,
					            "time": 1456343402000,
					            "date": "2016-02-24T14:50:02.000-0500",
					            "timeSecond": 1456343402
					        },
					        "firstName": "Raja",
					        "lastName": "Raman",
					        "empId": 0,
					        "address": "11 street",
					        "city": "Toronto",
					        "telephone": "23434343"
					    }
				    ]				    
				},
				{
				    "id": 				
				    {
				        "timestamp": 1456348835,
				        "machineIdentifier": 7745367,
				        "processIdentifier": 6532,
				        "counter": 16172145,
				        "time": 1456348835000,
				        "date": "2016-02-24T16:20:35.000-0500",
				        "timeSecond": 1456348835
				    },
				    "clientId": 102,
				    "name": "IGT",
				    "address": "23 street",
				    "city": "Toronto"
				}			  	
            ]
	};
  
	$scope.clients = json;
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
