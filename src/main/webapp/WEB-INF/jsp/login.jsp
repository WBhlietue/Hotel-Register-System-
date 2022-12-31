<html>
    <head>
        <style>
            .btn{
                height: 50px;
                width: 150px;
                font-size: 20px;
                position: relative;
                left: 50%;
                transform: translateX(-50%);
            }
            .input{
                height: 30px;
                width: 300px;
                position: relative;
                left: 50%;
                transform: translateX(-50%);
            }
            .f{
                border: 1px solid black;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                height: 400px;
                width: 400px;
            }
            .p{
                margin-left: 100px;

                margin-bottom: 5px;
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
        <fieldset class="f">
            <legend>Login</legend>
            <form th:action = "@{/login}" method="post">
                <input type="hidden" name="button" value ="signin">
                <br>
                <br>
                <p class="p">User name</p>
                <input class="input " type="text" name="code">
                <p class="p">Password</p>
                <input class="input " type="password" name = "pass">
                <br>
                <br>
                <br>
                <input type="submit" value="Login" class="btn">
            </form>
            <p class="p" style="color:red ;">${value}</p>
        </fieldset>
    </body>
</html>