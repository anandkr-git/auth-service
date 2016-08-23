app.factory('appConfigService', ['$location', function ($location) {
    return {
    	protocol: 	$location.protocol(),
		host:		$location.host(),
		port:		$location.port()
    };
}]);