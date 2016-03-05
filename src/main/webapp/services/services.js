var Owner = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/clients/:id');
}];

var OwnerEmployee = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/clients/:ownerId/employees', {ownerId : '@ownerId'});
}];

var Employee = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/employees/:id');
}];

var Test = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/tests/:testId');
}];

var Visit = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/employees/:employeeId/visits', {employeeId : '@id'});
}];

var EmployeeType = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/employees/types');
}];

