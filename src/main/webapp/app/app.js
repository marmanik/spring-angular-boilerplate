'use strict';

angular.module('goodysApp', [
    'ui.router',
    'ngAnimate',
    'ngCookies',
    'ngMessages',
    'ngSanitize',
    'ngTouch'
]).config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider.state('unsubscribe', {
        url: '/',
        templateUrl: 'app/unsubscribe/unsubscribe.html',
        controller: 'UnsubscribeContoller'
    });

}]);
