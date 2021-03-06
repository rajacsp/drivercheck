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
	}).state({
		name: "employeeDetails",
		url: "/employees/:empId",
		templateUrl: "components/employees/employee_details.html",
		controller: "EmployeeDetailsController",
		data: {requireLogin : true}
	}).state({
		name: "findPassingRates",
		url: "/testresults/:id",
		templateUrl: "components/tests/test_results.html",
		controller: "TestPassController",
		data: { requireLogin : true }
	}).state({
		name: "testsAll",
		url: "/tests/all",
		templateUrl: "components/tests/test_all.html",
		controller: "TestsAllController",
		data: { requireLogin : true }
	});

} ]);

/** Controllers **/
app.controller('MainController', MainController);
app.controller('DashboardController', DashboardController);
app.controller('ClientDetailsController', ClientDetailsController);
app.controller('TestController', TestController);
app.controller('EmployeeController', EmployeeController);
app.controller('EmployeeSaveController', EmployeeSaveController); // to save employee (add/edit/delete)
app.controller('EmployeeDetailsController', EmployeeDetailsController); // to manipulate test prefilled details
app.controller('ClientController', ClientController);
app.controller('TestDetailsController', TestDetailsController);
app.controller('AddClientController', AddClientController);
app.controller('TestPassController', TestPassController);
app.controller('TestsAllController', TestsAllController);


/** Services **/
app.factory('Client', Client);
app.factory('Employee', Employee);
app.factory('ClientEmployee', ClientEmployee);
app.factory('EmployeeTest', EmployeeTest);
app.factory('Test', Test);

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



