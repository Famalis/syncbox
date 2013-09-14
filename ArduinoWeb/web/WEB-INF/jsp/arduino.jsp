<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <style type="text/css">

        </style>
        <script src="http://code.jquery.com/jquery-latest.js">
        </script>
        <script>
            $(document).ready(function() {
                $('#sizeSubmit').click(function(event) {
                    var sliderSize = $('#sizeSlide').val();
                    $.get('/ArduinoWeb/app/arduino/changeSize/' + sliderSize + '.htm', {size: sliderSize}, function(responseText) {
                        $('#console').text('/ArduinoWeb/app/arduino/changeSize/' + sliderSize + '.htm');
                    });
                });
            });
            
            $(document).ready(function() {
                $('#sizeSubmit2').click(function(event) {
                    var sliderSize = $('#sizeSlide2').val();
                    $.get('/ArduinoWeb/app/arduino/changeSize2/' + sliderSize + '.htm', {size: sliderSize}, function(responseText) {
                        $('#console').text('/ArduinoWeb/app/arduino/changeSize2/' + sliderSize + '.htm');
                    });
                });
            });
            
            $(document).ready(function() {
                $('#photoDelaySubmit').click(function(event) {
                    var photoDelay = $('#photoDelay').val();
                    $.get('/ArduinoWeb/app/arduino/changeDelay/' + photoDelay + '.htm', {delay: photoDelay}, function(responseText) {
                        $('#console').text('/ArduinoWeb/app/arduino/bleble/' + photoDelay + '.htm');
                    });
                });
            });

            $(document).ready(function() {
                $('#singleDropButton').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/singleDrop.htm', function(responseText) {
                        $('#initMessage').text(responseText);
                    });
                });
            });
            
            $(document).ready(function() {
                $('#twoDropsButton').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/twoDrops.htm', function(responseText) {
                        $('#initMessage').text(responseText);
                    });
                });
            });
            
            $(document).ready(function() {
                $('#loopSingleDrop').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/loopSingleDrop.htm', function(responseText) {
                        $('#initMessage').text(responseText);
                    });
                });
            });
            
            $(document).ready(function() {
                $('#loopTwoDrops').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/loopTwoDrops.htm', function(responseText) {
                        $('#initMessage').text(responseText);
                    });
                });
            });
            
            $(document).ready(function() {
                $('#stopLoop').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/stopLoop.htm', function(responseText) {
                        $('#initMessage').text(responseText);
                    });
                });
            });
        </script>
        <script type="text/javascript">
            function updateSlider(slideAmount, num) {
                //get the element
                var display = document.getElementById("chosen" + num);
                //show the amount
                display.innerHTML = slideAmount;
                //get the element
                var pic = document.getElementById("pic");
                //set the dimensions
                pic.style.width = slideAmount + "%";
                pic.style.height = slideAmount + "%";
            }


        </script>
    </head>
    <body>
        <h4>${initMsg}</h4>        
        <table border="1px">
            <tr>
                <td>
                    <div>
                        <input name="input" id="sizeSlide" type="range" min="5" max="2000" step="5" value="80" onchange="updateSlider(this.value, 1)" />                        
                    </div><br/>
                    <div id="chosen1">80</div>                    
                </td>
                <td>
                    <input id="sizeSubmit" type="submit" value="Zmie? rozmiar kropli"/>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <input name="input" id="sizeSlide2" type="range" min="5" max="2000" step="5" value="80" onchange="updateSlider(this.value, 3)" />                        
                    </div><br/>
                    <div id="chosen3">80</div>                    
                </td>
                <td>
                    <input id="sizeSubmit2" type="submit" value="Zmie? rozmiar drugiej kropli"/>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <input name="input" id="photoDelay" type="range" min="5" max="2000" step="5" value="80" onchange="updateSlider(this.value,2)" />                        
                    </div><br/>
                    <div id="chosen2">80</div>                    
                </td>
                <td>
                    <input id="photoDelaySubmit" type="submit" value="Zmie? opó?nienie zdj?cia"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="singleDropButton" value="Zdj?cie jednej kropli"/>
                </td>
                <td>
                    <input type="submit" id="twoDropsButton" value="Zdj?cie dwóch kropel"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="loopSingleDrop" value="Powatarzaj jedn? kropl?"/>
                </td>
                <td>
                    <input type="submit" id="loopTwoDrops" value="Powatarzaj dwie krople"/>
                </td>
                <td>
                    <input type="submit" id="stopLoop" value="Zatrzymaj powtarzanie"/>
                </td>
            </tr>
        </table>
        <p id="console"></p>
    </body>
</html>
