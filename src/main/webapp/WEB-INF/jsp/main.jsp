<html>
    <head>
        <style>
            .btn{
                height: 50;
                width: 150px;
                font-size: 20px;
                position: absolute;
                transform: translate(-50%, -50%);
                top: 50%;
            }
            .b1{
                left: 40%;
            }
            .b2{
                left: 60%;
            }
        </style>
    </head>
    <body>
        <form th:action = "@{/index/register}"  method="post">
            <input type="hidden" name = "button" value = "create">
            <input name="code" type="hidden" value="2">
            <input name="pass" type="hidden" value="2">
            <input type="submit" value="Create account" class="btn b1">
        </form>
        <form th:action = "@{/index}"  method="post">
            <input type="hidden" name="button" value="login">
            <input name="code" type="hidden" value="2">
            <input name="pass" type="hidden" value="2">
            <input type="submit" value="Log in"  class="btn b2">
        </form>
    </body>
</html>