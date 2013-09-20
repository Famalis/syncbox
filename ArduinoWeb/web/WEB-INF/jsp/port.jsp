<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/ArduinoWeb/resources/style_port.css" />
        <script src="/ArduinoWeb/resources/js/jquery-2.0.3.js"></script>
        <script src="/ArduinoWeb/resources/js/jquery.blockUI.js"></script>
        <title>SyncBox&#8482 - Wybór portu COM</title>
    </head>
    <body>
        <div class="container">

            <div class="port">

                <h1><img id="ico" src="/ArduinoWeb/resources/images/port-ico.png">PODŁĄCZ SYNCBOX’A</h1>
                <div class="port-krecha"></div>
                <div class="port-text">
                    <p>Aby uruchomić program podaj <b>numer portu COM</b>, pod który podłączony
                        jest <b>SyncBox</b> <i>(Mój komputer - Właściwości - Menedżer urządzeń - Porty (COM i LPT).</i><br><br>

                        Jeśli nie znasz numeru portu kliknij przycisk <b>WYBIERZ</b>, a program wybierze odpowiedni port samodzielnie.</p>
                </div>

                <div class="port-inputs">

                    <form name="port" action="arduino.htm">
                        <input type="text" class="port-name" name="port" value="podaj numer portu COM..." onclick="this.value = '';" onfocus="this.select()" onblur="this.value = !this.value ? 'podaj numer portu COM...' : this.value;"/>
                        <input type="submit" class="port-button" value="WYBIERZ"/>
                    </form>

                </div>
            </div> <!-- koniec | port -->    
        </div> <!-- koniec | container -->
    </body>
</html>