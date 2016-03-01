var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) { 
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
						        "telephone": "23434343",
						        
						        "tests" : {
						        	"id": 
						        	{

						        	    "timestamp": 1456344401,
						        	    "machineIdentifier": 4597077,
						        	    "processIdentifier": 12036,
						        	    "counter": 1072454,
						        	    "time": 1456344401000,
						        	    "date": "2016-02-24T15:06:41.000-0500",
						        	    "timeSecond": 1456344401

						        	},
						        	"testId": 0,
						        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
						        }
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
						        "firstName": "Kumar",
						        "lastName": "Rajan",
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
					    "name": "Gtech",
					    "address": "23 street",
					    "city": "Toronto"
					}			  	
	            ]
		};	
  
  $scope.clients = json;
  
  
});
