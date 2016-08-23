//Create the routers
//This configures the routes and associates each route with a view and a controller
app.config(function ($routeProvider) {
    $routeProvider
    	.when('/home',
            {
                //controller: 'homeController',
                templateUrl: 'partials/home.html',
                title: 'Home'
            })
        .when('/users',
            {
                controller: 'userController',
                templateUrl: 'partials/users.html',
                title: 'View users'
            })
        .when('/users/add',
            {
                controller: 'userController',
                templateUrl: 'partials/addUser.html',
                title: 'Add user'
            })
        .when('/users/edit/:id',
            {
                controller: 'userController',
                templateUrl: 'partials/addUser.html',
                title: 'Edit user'
            })
        .when('/roles',
            {
                controller: 'roleController',
                templateUrl: 'partials/roles.html',
                title: 'View roles'
            })
        .when('/roles/add',
            {
                controller: 'roleController',
                templateUrl: 'partials/addRole.html',
                title: 'Add role'
            })
        .when('/roles/edit/:id',
            {
                controller: 'roleController',
                templateUrl: 'partials/addRole.html',
                title: 'Edit role'
            })
        .when('/clientdetails',
            {
                controller: 'clientDetailsController',
                templateUrl: 'partials/clientDetails.html',
                title: 'View clients'
            })
        .when('/clientdetails/add',
            {
                controller: 'clientDetailsController',
                templateUrl: 'partials/addClient.html',
                title: 'Register a client'
            })
        .when('/clientdetails/edit/:id',
            {
                controller: 'clientDetailsController',
                templateUrl: 'partials/addClient.html',
                title: 'Edit a client'
            })
        .otherwise({ redirectTo: '/home' });
});


app.config(["$httpProvider", function($httpProvider){
    $httpProvider.interceptors.push("sessionValidatorService");
}]);

app.run(['$location', '$rootScope', function($location, $rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        if (current.hasOwnProperty('$$route')) {
            $rootScope.title = current.$$route.title;
        }
    });
}]);