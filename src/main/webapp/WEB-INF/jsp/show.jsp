<html>
    <head>
        <style>
            .main{
                /* height: 400px;
                width: 900px; */
                display: flex;
                flex-direction: column;
                
            }
            /* .row{
                flex: 1;
                flex-direction: row;
                display: flex;
            } */
            .elem{
                border: 1px solid black;
                /* flex: 1; */
            }
            .l{
                position: absolute;
                float: right;
                right: 100px;
                top: 100px;
            }
        </style>
    </head>
    <body>
        <form th:action = "@{/input}", method="post">
            <input name="button" type="hidden" value="back">
            <input name="code" type="hidden" value="back">
            <input name="pass" type="hidden" value="back">
            <input type="submit" value="Back" style="height: 30px; width: 70px; font-size: 20px; border-radius: 20px;">
         </form>
        <h1>Room register</h1>
        <h3>${userName}</h3>
        <label class="l">Price: ${price}</label>
        <div class="main">
            Empty Rooms <br><br>
            ${empRoomElem}
            <br>
            Your Order <br><br>
            ${noRoomElem}
        </div>
    </body>
</html>