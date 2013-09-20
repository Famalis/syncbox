<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html ng-app>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" type="text/css" href="/ArduinoWeb/resources/style.css" />
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/ArduinoWeb/resources/js/jquery-2.0.3.js"></script>
        <script src="/ArduinoWeb/resources/js/jquery.blockUI.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
        <script>
            $(document).ready(function() {
                $('#sizeSubmit').click(function(event) {
                    var sliderSize = $('#sizeSlide').val();
                    $.get('/ArduinoWeb/app/arduino/changeSize/' + sliderSize + '.htm', {size: sliderSize}, function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });

            $(document).ready(function() {
                $('#sizeSubmit2').click(function(event) {
                    var sliderSize = $('#sizeSlide2').val();
                    $.get('/ArduinoWeb/app/arduino/changeSize2/' + sliderSize + '.htm', {size: sliderSize}, function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });

            $(document).ready(function() {
                $('#photoDelaySubmit').click(function(event) {
                    var photoDelay = $('#photoDelay').val();
                    $.get('/ArduinoWeb/app/arduino/changeDelay/' + photoDelay + '.htm', {delay: photoDelay}, function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });

            $(document).ready(function() {
                $('#singleDropButton').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/singleDrop.htm', function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });

            $(document).ready(function() {
                $('#twoDropsButton').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/twoDrops.htm', function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });

            $(document).ready(function() {
                $('#loopSingleDrop').click(function(event) {
                    var check = $('#loopType').val();
                    $.get('/ArduinoWeb/app/arduino/loopSingleDrop/' + check + '.htm', {check: check}, function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });

            $(document).ready(function() {
                $('#loopTwoDrops').click(function(event) {
                    var check = $('#loopTwoDrops').val();
                    $.get('/ArduinoWeb/app/arduino/loopTwoDrops.htm/' + check + '.htm', {check: check}, function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });

            $(document).ready(function() {
                $('#stopLoop').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/stopLoop.htm', function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });
            $(document).ready(function() {
                $('#pompka').click(function(event) {
                    $.get('/ArduinoWeb/app/arduino/openPump.htm', function(responseText) {
                        $('#console').text(responseText);
                    });
                });
            });
            $(document).ready(function() {
                $('#savePreset').click(function(event) {
                    var presetName = $('#newPresetName').val();
                    var dropSize1 = $('#sizeSlide').val();
                    var dropSize2 = $('#sizeSlide2').val();
                    var photoDelay = $('#photoDelay').val();
                    $.get('/ArduinoWeb/app/arduino/savePreset/' + presetName + "_" + dropSize1 + "_" + dropSize2 + "_" + photoDelay + ".htm", {name: presetName, size1: dropSize1, size2: dropSize2, delay: photoDelay},
                    function(responseText) {
                        $('#console').text('/ArduinoWeb/app/arduino/savePreset/' + presetName + "_" + dropSize1 + "_" + dropSize2 + "_" + photoDelay + ".htm");
                    });
                    $("#scores").load("index.php #scores");
                });
            });
            function savePreset()
              {
                var presetName = $('#newPresetName').val();
                var dropSize1 = $('#sizeSlide').val();
                var dropSize2 = $('#sizeSlide2').val();
                var photoDelay = $('#photoDelay').val();
                $.get('/ArduinoWeb/app/arduino/savePreset/' + presetName + "_" + dropSize1 + "_" + dropSize2 + "_" + photoDelay + ".htm", {name: presetName, size1: dropSize1, size2: dropSize2, delay: photoDelay},
                function(responseText) {
                    $('#console').text('/ArduinoWeb/app/arduino/savePreset/' + presetName + "_" + dropSize1 + "_" + dropSize2 + "_" + photoDelay + ".htm");
                });
                setTimeout(function() {
                    location.reload()
                }, 10);
              }

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

        <script type="text/javascript">

            function toggleDiv(divId) {
                $("#" + divId).slideToggle(200);

            }
        </script>
        <script type="text/javascript">
            setTimeout(function() {
                $('#info').fadeOut('slow');
            }, 5000); // <-- time in milliseconds


        </script>

        <script type="text/javascript">
            $(document).ready(function() {
                $('#pompka').click(function() {
                    $.blockUI({message: null});

                    setTimeout($.unblockUI, 10000);
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#singleDropButton').click(function() {
                    $.blockUI({message: null});

                    setTimeout($.unblockUI, 2000);
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#twoDropsButton').click(function() {
                    $.blockUI({message: null});

                    setTimeout($.unblockUI, 2000);
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#sizeSubmit').click(function() {
                    $.blockUI({message: null});

                    setTimeout($.unblockUI, 500);
                });
            });
        </script>
         <script type="text/javascript">
            $(document).ready(function() {
                $('#sizeSubmit2').click(function() {
                    $.blockUI({message: null});

                    setTimeout($.unblockUI, 500);
                });
            });
        </script>
          <script type="text/javascript">
            $(document).ready(function() {
                $('#photoDelaySubmit').click(function() {
                    $.blockUI({message: null});

                    setTimeout($.unblockUI, 500);
                });
            });
        </script>

        <title>SyncBox&#8482 - GUI</title>
    </head>

    <body>

        <div class="container">
            <div class="syncbox-info" id="info"><h1>${connectionMsg}${port}</h1></div>

            <div class="left-side">
                <div class="lista-presetow">
                    <a href="javascript:toggleDiv('presets-list');"><h1><img id="ico" src="/ArduinoWeb/resources/images/lista-presetow-ico.png">LISTA KONFIGURACJI</h1></a>
                    <div id="presets-list">
                        <div class="preset-krecha"></div>
                        <div class="preset-inputs">
                            <input type="text" id="newPresetName" class="preset-name" maxlength="29" value="podaj nazwę konfiguracji..." onclick="this.value = '';" onfocus="this.select()" onblur="this.value = !this.value ? 'podaj nazwę konfiguaracji...' : this.value;"/>
                            <input type="submit" class="preset-button" id="savePreset" onclick="savePreset()" value="ZAPISZ">
                        </div> <!-- koniec | preset-inputs -->

                        <div class="preset-krecha"></div>
                        <div class="preset-radio">
                            <form name="preset" action="arduino.htm" method="get">

                                <c:forEach var="preset" items="${presets}" varStatus="counter">
                                    <p>
                                        <input type="radio" id="r${counter.count}" name="preset" value="${counter.count}" />
                                        <label for="r${counter.count}"><span></span>${preset}</label>
                                    </p>
                                    <div class="preset-krecha"></div>
                                </c:forEach>

                                <input type="submit" class="wczytaj-preset-button" value="WCZYTAJ PRESET" id="loadPreset"/>
                            </form>
                        </div> <!-- koniec | preset-radio --></div>
                </div>
                <div class="ustawienia-dodatkowe">
                    <a href="javascript:toggleDiv('ustawienia-list');"><h1><img id="ico" src="/ArduinoWeb/resources/images/ustawienia-dodatkowe-ico.png">USTAWIENIA DODATKOWE</h1></a>
                    <div class="ustawienia-krecha"></div>
                    <div id="ustawienia-list">

                        <div class="ustawienia-radio" ng-init="loop='none'">
                            <p>
                                <input ng-click="loop='single'" type="radio" id="r5" name="du" />
                                <label for="r5"><span></span>Powtarzaj jedną kroplę</label>
                            </p>
                            <div class="ustawienia-krecha"></div>
                            <p>
                                <input ng-click="loop='double'" type="radio" id="r6" name="du" />
                                <label for="r6"><span></span>Powtarzaj dwie krople</label>
                            </p>
                            <!-- Nie ruszać tego inputa! :P --><input type="text" ng-show="false" id="loopType" ng-model="loop"/>
                            <div class="stop-button"><input type="submit" id="stopLoop" class="ustawienia-button-stop" value="STOP"></div>
                            <div class="start-button"><input type="submit" id="loopSingleDrop" class="ustawienia-button" value="START"></div>

                        </div> <!-- koniec | ustawienia-radio -->


                    </div> <!-- koniec | ustawienia-list -->


                </div> <!-- koniec | ustawienia-dodatkowe -->

                <div class="pompka">

                    <button id="pompka" type="submit" class="pompka-button" /><h1><img id="ico" src="/ArduinoWeb/resources/images/pompka-ico.png">URUCHOM POMPKĘ</h1></button>

                </div> <!-- koniec | pompka -->

                <div class="jedna-kropla">

                    <button id="singleDropButton" type="submit" class="jedna-kropla-button" /><h1><img id="ico" src="/ArduinoWeb/resources/images/jedna-kropla-ico.png">ZDJĘCIE <span class="czcionka-button">JEDNEJ</span> KROPLI</h1></button>

                </div> <!-- koniec | jedna-kropla-button -->

                <div class="dwie-krople">

                    <button id="twoDropsButton" type="submit" class="jedna-kropla-button" /><h1><img id="ico" src="/ArduinoWeb/resources/images/dwie-krople-ico.png">ZDJĘCIE <span class="czcionka-button">DWÓCH</span> KROPLI</h1></button>

                </div> <!-- koniec | dwie-krople-button -->
            </div> <!-- koniec | left-side -->
            <div class="right-side">
                <div class="rozmiar-kropli">
                    <h1><img id="ico" src="/ArduinoWeb/resources/images/rozmiar-kropli-ico.png">ROZMIAR KROPLI</h1>
                    <div class="suwak-wartosc" id="chosen1">${size1}</div>
                    <div class="suwak"><input name="input" id="sizeSlide" type="range" min="5" max="200" step="1" value="${size1}" onchange="updateSlider(this.value, 1)" /></div>
                    <div class="suwak-button"><input id="sizeSubmit" class="rozmiar-button" type="submit" value="USTAW"/></div>  

                </div> <!-- koniec | rozmiar-kropli -->

                <div class="rozmiar-drugiej-kropli">
                    <h1><img id="ico" src="/ArduinoWeb/resources/images/rozmiar-drugiej-kropli-ico.png">ROZMIAR DRUGIEJ KROPLI</h1>
                    <div class="suwak-wartosc" id="chosen3">${size2}</div>
                    <div class="suwak"><input name="input" id="sizeSlide2" type="range" min="5" max="200" step="1" value="${size2}" onchange="updateSlider(this.value, 3)" /></div>
                    <div class="suwak-button"><input id="sizeSubmit2" class="rozmiar-button" type="submit" value="USTAW"/></div>                     

                </div> <!-- koniec | rozmiar-drugiej-kropli -->

                <div class="opoznienie-aparatu">
                    <h1><img id="ico" src="/ArduinoWeb/resources/images/opoznienie-aparatu-ico.png">OPÓŹNIENIE APARATU</h1>
                    <div class="suwak-wartosc" id="chosen2">${delay}</div>
                    <div class="suwak"><input name="input" id="photoDelay" type="range" min="5" max="200" step="1" value="${delay}" onchange="updateSlider(this.value, 2)" /></div>
                    <div class="suwak-button"><input id="photoDelaySubmit" class="rozmiar-button" type="submit" value="USTAW"/></div>                     

                </div> <!-- koniec | opoznienie-aparatu -->
            </div> <!-- koniec | right-side -->
        </div> <!-- koniec | container -->
    </body>
</html>
