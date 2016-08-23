app.factory('clientDetailsService', ['$resource', 'appConfigService', function ($resource, appConfigService) {
	var baseUrl = appConfigService.protocol+"://"+appConfigService.host+":"+appConfigService.port+"";
	return $resource(baseUrl+'/auth-service/api/v1/clientdetails/:id', {}, {
        findAll: { method: 'GET', isArray: true },
        findById: { method: 'GET', params: {id: '@id'}},
        save: { method: 'POST',  headers: { 'Accept': 'text/plain' } },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
}]);