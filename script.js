var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider

		.when('/', {
			templateUrl: 'pages/home.html',
			controller: 'ctrl'
		})

		.when('/home', {
			templateUrl: 'pages/home.html',
			controller: 'ctrl'
		})

		.when('/penpal-about', {
			templateUrl: 'pages/penpal-about.html',
			controller: 'ctrl'
		})

		.when('/faqs', {
			templateUrl: 'pages/faqs.html',
			controller: 'ctrl'
		})

		.when('/penpal-info', {
			templateUrl: 'pages/penpal-info.html',
			controller: 'ctrl'
		})

		.when('/schools', {
			templateUrl: 'pages/schools.html',
			controller: 'ctrl'
		})

		.when('/greek', {
			templateUrl: 'pages/greek.html',
			controller: 'ctrl'
		})

		.when('/bookmark-about', {
			templateUrl: 'pages/bookmark-about.html',
			controller: 'ctrl'
		})

		.when('/directors', {
					templateUrl: 'pages/directors.html',
					controller: 'ctrl'
		})

		.when('/hallheads', {
			templateUrl: 'pages/hallheads.html',
			controller: 'ctrl'
		})

		.when('/bookmark-coordinators', {
			templateUrl: 'pages/bookmark-coordinators.html',
			controller: 'ctrl'
		})

		.when('/prteam', {
			templateUrl: 'pages/prteam.html',
			controller: 'ctrl'
		})

		.when('/finance', {
			templateUrl: 'pages/finance.html',
			controller: 'ctrl'
		})

		.when('/info-apply', {
			templateUrl: 'pages/info-apply.html',
			controller: 'ctrl'
		})

		.when('/calendar', {
			templateUrl: 'pages/calendar.html',
			controller: 'ctrl'
		})

		.when('/kidsfair', {
			templateUrl: 'pages/kidsfair.html',
			controller: 'ctrl'
		})

		.when('/festigrams', {
			templateUrl: 'pages/festigrams.html',
			controller: 'ctrl'
		})

		.when('/kday', {
			templateUrl: 'pages/kday.html',
			controller: 'ctrl'
		})

		.when('/members', {
			templateUrl: 'pages/members.html',
			controller: 'ctrl'
		})

		.when('/teachers', {
			templateUrl: 'pages/teachers.html',
			controller: 'ctrl'
		})

		.when('/about', {
			templateUrl: 'pages/about.html',
			controller: 'ctrl'
		})

		.when('/news', {
			templateUrl: 'pages/news.html',
			controller: 'ctrl'
		})

		.when('/penpal-signup', {
			templateUrl: 'pages/penpal-signup.html',
			controller: 'ctrl'
		})

		.when('/coming-soon', {
			templateUrl: 'pages/coming-soon.html',
			controller: 'ctrl'
		});

});

app.controller('ctrl', function($scope) {

});