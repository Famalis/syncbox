function ArduinoCtrl($scope) {

    $scope.test;

    $scope.read = function(presets) {
        $scope.test = presets;
        alert($scope.test);
        for (p in presets) {
            alert(p);
        }
    };
}