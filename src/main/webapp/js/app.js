var app = angular.module('spa', ['ui.router','ui.router.stateHelper','ngAnimate','ngCookies','ngResource', 'ngStorage']);


/** Start of Configurable constants **/
app.constant('context', '/drivercheck');
/** End of Configurable constants **/

app.config(['stateHelperProvider','$urlRouterProvider','$urlMatcherFactoryProvider',function(stateHelperProvider,$urlRouterProvider,$urlMatcherFactoryProvider) {

	$urlRouterProvider.otherwise("/");

	$urlMatcherFactoryProvider.strictMode(false)

	stateHelperProvider.state({
		name: "landing",
		url: "/",
		templateUrl: "components/landing/landing.html",
		controller: "MainController",
		data: { requireLogin : false }
	}).state({
		name: "dashboard",
		url: "/dashboard",
		templateUrl: "components/dashboard/dashboard.html",
		controller: "DashboardController",
		data: { requireLogin : true }
	}).state({
		name: "tests",
		url: "/tests",
		templateUrl: "components/tests/tests.html",
		controller: "TestController",
		data: { requireLogin : true }
	}).state({
		name: "employees",
		url: "/employees",
		templateUrl: "components/employees/employees.html",
		controller: "EmployeeController",
		data: { requireLogin : true }
	}).state({
		name: "clients",
		url: "/clients",
		templateUrl: "components/clients/clients.html",
		controller: "ClientController",
		data: { requireLogin : true }
	}).state({
		name: "clientDetails",
		url: "/clients/:id",
		templateUrl: "components/clients/client_details.html",
		controller: "ClientDetailsController",
		data: {requireLogin : true}
	});

} ]);

/** Controllers **/
app.controller('MainController', MainController);
app.controller('DashboardController', DashboardController);

app.controller('TestController', TestController);
app.controller('EmployeeController', EmployeeController);
app.controller('EmployeeDetailsController', EmployeeDetailsController);
app.controller('ClientController', ClientController);
app.controller('ClientDetailsController', ClientDetailsController);

app.controller('AddClientController', AddClientController);


/** Services **/
app.factory('Client', Client);
app.factory('Employee', Employee);
app.factory('ClientEmployee', ClientEmployee);
app.factory('Test', Test);
//app.factory('EmployeeType', EmployeeType);

/** Directives **/

app.directive('scrollToTarget', function() {
  return function(scope, element) {
    element.bind('click', function() {
    	angular.element('html, body').stop().animate({
			scrollTop: angular.element(angular.element(element).attr('href')).offset().top - 20
		}, 1500);
		return false;
    });
  };
});



