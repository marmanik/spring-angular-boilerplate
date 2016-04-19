'use strict';

angular.module('goodysApp')
    .controller('UnsubscribeContoller', function ($scope, $rootScope, UnsubscribeService) {

        $scope.name = '';

        $scope.submit = function () {
            console.log('submit ' + $scope.name);
            UnsubscribeService.insertCustomer($scope.name);
        };

    });
