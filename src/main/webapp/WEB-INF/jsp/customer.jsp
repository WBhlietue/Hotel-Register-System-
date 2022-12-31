<html>
   <head>
      <title>Hello World</title>
      <style>
         input{
            display: table-cell;
            margin: 10px;
         }
         label{
            display: table-cell;
         }
         .form{
            position: absolute;
            display: table;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
         }
         p{
            display: table-row;
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
      <form  th:action = "@{/input}" th:object = "${customer}" method="post" class="form">
         <fieldset>
            <legend>Register</legend>
            <input name="button" type="hidden" value="createCus">
            <input name="code" type="hidden" value="2">
            <input name="pass" type="hidden" value="2">
            <p>
               <label>customer Code</label>
               <input name = "cusCode" type="text">
               <input name = "isadmin" type="hidden" value="false">
            </p>
            <p>
            <label>first Name</label>
            <input name = "cusFName" type="text">
         </p>
         <p>
            <label>lastName</label>
            <input name = "cusLName" type="text">
         </p>
         <p>
            <label>Birth Day</label>
            <input name = "cusBirth" type="date">
         </p>
         <p>
            <label>Register</label>
            <input name = "cusReg" type="text" maxlength="10" >
         </p>
         <p>
            <label>Phone</label>
            <input name = "cusPhone" type="number">
         </p>
         <p>
            <label>Password</label>
            <input name = "pass" type="password">
         </p>
         <input type="submit" value="Save">
         </fieldset>
      </form>
   </body>

</html>