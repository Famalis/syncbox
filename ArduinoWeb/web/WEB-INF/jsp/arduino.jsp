<!doctype html>
<html>
    <head>
        <style type="text/css">

        </style>
        <script src="http://code.jquery.com/jquery-latest.js">
        </script>
        <script>
            $(document).ready(function() {
                $('#submit').click(function(event) {
                    var sliderSize = $('#slide').val();
                    $.get('/ArduinoWeb/app/arduino/user/'+sliderSize+".htm", {size: sliderSize}, function(responseText) {
                        $('#welcometext').text(responseText);
                    });
                });
            });
        </script>
        <script type="text/javascript">
            function updateSlider(slideAmount) {
                //get the element
                var display = document.getElementById("chosen");
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
        <table>
            <tr>
                <td>
                    <div id="slider">
                        5% <input name="input" id="slide" type="range" min="5" max="200" step="5" value="80" onchange="updateSlider(this.value)" />
                        200%
                    </div><br/>
                    <div id="chosen">80</div>
                    <div id="picHolder">
                        <img id="pic" src="/ArduinoWeb/resources/kropla.svg" alt="cat eye"/>
                    </div>
                </td>
                <td>
                    <input id="submit" type="submit"/>
                    <input type="text"/><br/>
                    Suma wys?anych inputów: ${msg}
                    <p id="welcometext">
                    </p>
                </td>
            </tr>
        </table>
        <a href="arduino.htm">arduino</a>
        <a href="arduino/user.htm">arduino/user</a>


    </body>
</html>
