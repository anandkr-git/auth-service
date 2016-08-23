app.factory('roleService', ['$resource', 'appConfigService', function ($resource, appConfigService) {
	var baseUrl = appConfigService.protocol+"://"+appConfigService.host+":"+appConfigService.port+"";
    /*return {
		findAll: function () {
			return $http.get(baseUrl+'/auth-service/api/v1/roles');
		},
		save: function (role) {
			return $http.post(baseUrl+'/auth-service/api/v1/roles', role);
		},
    };*/
	
	return $resource(baseUrl+'/auth-service/api/v1/roles/:id', {}, {
        findAll: { method: 'GET', isArray: true },
        findById: { method: 'GET', params: {id: '@id'}},
        save: { method: 'POST' },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
}]);