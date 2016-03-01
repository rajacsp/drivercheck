var app = angular.module('drivercheck', []);

app.controller('MainCtrl', function($scope) { 
	var json = {
			"clients" : 
				[ 			 
					{					    
					    "clientId": 102,
					    "name": "GTECH",
					    "address": "23 street",
					    "city": "Toronto",
					    
					    "employees" : [			    	
						    {
						        
						        "firstName": "Raja",
						        "lastName": "Raman",
						        "empId": 0,
						        "address": "11 street",
						        "city": "Toronto",
						        "telephone": "23434343",
						        
						        "tests" : [
						        	{
							        	
							        	"testId": 302,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        },
							        {
							        	
							        	"testId": 303,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        },
							        {
							        	
							        	"testId": 304,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        }							        
						        ]
						    },
						    {
						        "firstName": "Kumar",
						        "lastName": "Rajan",
						        "empId": 0,
						        "address": "11 street",
						        "city": "Toronto",
						        "telephone": "23434343",
						        
					        	"tests" : [
								        	{
									        	
									        	"testId": 305,
									        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
									        },
									        {
									        	
									        	"testId": 306,
									        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
									        },
									        {
									        	
									        	"testId": 307,
									        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
									        }							        
								        ]	
						    }
					    ]				    
					},
					{					    
					    "clientId": 103,
					    "name": "Gtech",
					    "address": "23 street",
					    "city": "Toronto",
					    
					    "employees" : [			    	
						    {
						        
						        "firstName": "Hitesh",
						        "lastName": "Mathpal",
						        "empId": 0,
						        "address": "12 street",
						        "city": "Toronto",
						        "telephone": "23434343",
						        
						        "tests" : [
						        	{
							        	
							        	"testId": 308,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        },
							        {
							        	
							        	"testId": 309,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        },
							        {
							        	
							        	"testId": 310,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        }							        
						        ]
						    },
					    	{						        
						        "firstName": "Feliciana",
						        "lastName": "Cannilo",
						        "empId": 0,
						        "address": "13 street",
						        "city": "Toronto",
						        "telephone": "23434343",
						        
						        "tests" : [
						        	{
							        	
							        	"testId": 302,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        },
							        {
							        	
							        	"testId": 303,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        },
							        {
							        	
							        	"testId": 304,
							        	"testTakenDate": "2016-02-24T15:06:41.890-0500"
							        }							        
						        ]
						    }
						]
					}			  	
	            ]
		};	
  
  $scope.clients = json;
  
  
});
