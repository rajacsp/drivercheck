var Client = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/clients/:id');
}];

var ClientEmployee = ['$resource','context', function($resource, context) {
	//alert('test');
	return $resource(context + '/api/clients/:_id/employees', {_id : '@_id'});
}];

var Employee = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/employees/:id');
}];

var EmployeeTest = ['$resource','context', function($resource, context) {
	//alert('test');
	return $resource(context + '/api/clients/:_id/employees/:empId/tests', {_id : '@_id', empId : '@empId'});
}];

var Test = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/tests/:testId');
}];


var EmployeeType = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/employees/types');
}];


