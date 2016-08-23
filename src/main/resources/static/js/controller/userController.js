app.controller('userController', ['$scope', 'userService', 'roleService', '$location', '$routeParams', function($scope, userService, roleService, $location, $routeParams){
	$scope.btnlabel = "Add User";
	$scope.actionName = "ADD";
	
	/**
	 * fetch all the available roles from the database
	 */
    var populateRoles = function(callback){
    	console.log("populating roles");
    	roleService.findAll().$promise
		.then(function(data){
			$scope.roles = data;
			if (typeof callback === "function") {
		    // Call it, since we have confirmed it is callable​
				callback();
		    }
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
	
	if($location.path().includes("add")){
		console.log("displaying add user page");
		$scope.action = "added";
		populateRoles();
	} else if ($location.path().includes("edit")){
		console.log("displaying edit user page");
		$scope.btnlabel = "Update User";
		$scope.actionName = "Update";
		$scope.action = "updated";
		populateRoles(function(){
			userService.findById({ id: $routeParams.id }).$promise
			.then(function(data){
				$scope.user = data;
			});
		});
	} else {
		$scope.users = userService.findAll();
	}
	// callback for ng-click 'createNewUser':
    $scope.createNewUser = function () {
        $location.path('/users/add');
    };
    
    //callback for addUser btn
    $scope.addUser = function (user) {
    	console.log("adding user : "+user);
    	//save
    	userService.save(user).$promise
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
	
	// callback for ng-click 'deleteUser':
    $scope.deleteUser = function (id) {
    	console.log("deleting user with id="+id);
    	//Make rest call to delete and then remove that data in the success call back
    	var test = userService.delete({ id: id }).$promise
        .then(function(data){
			for(var i=0; i < $scope.users.length; i++){
				if($scope.users[i].id === id){
					$scope.users.splice(i, 1);
					break;
				}
			}
		});
    };
    // callback for ng-click 'editUser':
    $scope.editUser = function (id) {
    	$location.path('/users/edit/'+id);
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () {
    	$location.path('/users');
    };
}]);