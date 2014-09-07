'use strict';

crudexampleApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/person', {
                    templateUrl: 'views/persons.html',
                    controller: 'PersonController',
                    resolve:{
                        resolvedPerson: ['Person', function (Person) {
                            return Person.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
