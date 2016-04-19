'use strict';


angular.module('goodysApp')
    .service('UnsubscribeService', function UnsubscribeService($http) {

        var urlBase = 'api/customer/';

        var UnsubscribeService = {};

        UnsubscribeService.insertCustomer = function (name) {
            return $http.post(urlBase, name);
        };

        UnsubscribeService.loadCustomerByMsisdn = function (name) {
            return $http.get(urlBase+name);
        };

        return UnsubscribeService;

    });
