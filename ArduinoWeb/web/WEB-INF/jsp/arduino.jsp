<!doctype html>
<html>
    <head>
        <style type="text/css">

        </style>
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
        <form method="POST" action="arduino.htm">
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
                        <input type="submit"/>
                        <input type="text"/><br/>
                        Suma wys?anych input�w: ${msg}
                    </td>
                </tr>
            </table>

        </form>
        
    </body>
</html>
