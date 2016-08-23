app.controller('roleController', ['$scope', 'roleService', '$location', '$routeParams', function($scope, roleService, $location, $routeParams){
	$scope.btnlabel = "Add Role";
	$scope.actionName = "ADD";
	if($location.path().includes("add")){
		console.log("displaying add role page");
		$scope.action = "added";
	} else if ($location.path().includes("edit")){
		console.log("displaying edit role page");
		$scope.btnlabel = "Update Role";
		$scope.actionName = "Update";
		$scope.action = "updated";
		$scope.role = roleService.findById({ id: $routeParams.id });
	} else {
		$scope.roles = roleService.findAll();
	}
	// callback for ng-click 'createNewRole':
    $scope.createNewRole = function () {
        $location.path('/roles/add');
    };
    
    //callback for addRole btn
    $scope.addRole = function (role) {
    	console.log("adding role : "+role);
    	//save
    	roleService.save(role).$promise
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
	
	// callback for ng-click 'deleteRole':
    $scope.deleteRole = function (id) {
    	console.log("deleting role with id="+id);
    	//Make rest call to delete and then remove that data in the success call back
        roleService.delete({ id: id }).$promise
        .then(function(data){
			for(var i=0; i < $scope.roles.length; i++){
				if($scope.roles[i].id === id){
					$scope.roles.splice(i, 1);
					break;
				}
			}
		});
    };
    // callback for ng-click 'editRole':
    $scope.editRole = function (id) {
    	$location.path('/roles/edit/'+id);
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () {
    	$location.path('/roles');
    };
}]);