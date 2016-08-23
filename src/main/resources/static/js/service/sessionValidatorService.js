app.factory("sessionValidatorService", ['$log', function($log){
    return {
        request: function(config) {return config;},
        response: function(response) {
            if (typeof response.data === "string" && response.data.indexOf("login") > -1) {
                console.log("Session expired.");
                location.reload();
            }
            return response;
        }
    };
}]);