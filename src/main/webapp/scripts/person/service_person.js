'use strict';

crudexampleApp.factory('Person', function ($resource) {
        return $resource('app/rest/persons/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
