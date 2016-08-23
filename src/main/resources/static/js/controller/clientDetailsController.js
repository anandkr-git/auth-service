app.controller('clientDetailsController', ['$scope', 'clientDetailsService', '$location', '$routeParams', function($scope, clientDetailsService, $location, $routeParams){
	$scope.btnlabel = "Add Client";
	$scope.actionName = "ADD";
	$scope.grantTypes = [
	                     {name: 'authorization_code', value: 'Authorization Code'},
	                     {name: 'refresh_token', value: 'Refresh Token'},
	                     {name: 'client_credentials', value: 'Client Credentials'},
	                     {name: 'password', value: 'Password'},
	                     {name: 'implicit', value: 'Implicit'}
	                    ];
	if($location.path().includes("add")){
		console.log("displaying add client page");
		$scope.action = "added";  
	} else if ($location.path().includes("edit")){
		console.log("displaying edit client page");
		$scope.btnlabel = "Update Client";
		$scope.actionName = "Update";
		$scope.action = "updated";
		clientDetailsService.findById({ id: $routeParams.id }).$promise
		.then(function(data){
			$scope.clientDetails = data;
			var tempArray = data.authorizedGrantTypes.split(",");
			$scope.clientDetails.authorizedGrantTypesArray = [];
			for(var i=0; i< tempArray.length; i++){
				var selectedGrantType = {
						name: tempArray[i]
				}
				$scope.clientDetails.authorizedGrantTypesArray.push(selectedGrantType);
			}
		});
	} else {
		$scope.clientDetailsList = clientDetailsService.findAll();
	}
	// callback for ng-click 'createNewClient':
    $scope.createNewClient = function () {
        $location.path('/clientdetails/add');
    };
    
    //callback for addClient btn
    $scope.addClient = function (clientDetails) {
    	var authorizedGrantTypesArray = clientDetails.authorizedGrantTypesArray.map(function(authorizedGrantType) {
    	    return authorizedGrantType['name'];
    	});
    	clientDetails.authorizedGrantTypes = authorizedGrantTypesArray.toString();
    	console.log("adding client : "+clientDetails);
    	//save
    	clientDetailsService.save(clientDetails).$promise
		.then(function(data){
			$scope.added = true;
		},
		function(error){
			$scope.added = false; 
			if(error.status === 409){
				$scope.alreadyExists = true;
			} else {
				$scope.errorAdding == true;
			}
		});
	};
	
	// callback for ng-click 'deleteClient':
    $scope.deleteClient = function (id) {
    	console.log("deleting clientDetails with id="+id);
    	//Make rest call to delete and then remove that data in the success call back
        clientDetailsService.delete({ id: id }).$promise
        .then(function(data){
			for(var i=0; i < $scope.clientDetailsList.length; i++){
				if($scope.clientDetailsList[i].clientId === id){
					$scope.clientDetailsList.splice(i, 1);
					break;
				}
			}
		});;
    };
    // callback for ng-click 'editClient':
    $scope.editClient = function (id) {
    	$location.path('/clientdetails/edit/'+id);
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () {
    	$location.path('/clientdetails');
    };
}]);