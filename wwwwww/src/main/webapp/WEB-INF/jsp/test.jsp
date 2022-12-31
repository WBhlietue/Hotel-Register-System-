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
         form{
            display: table;
         }
         p{
            display: table-row;
         }
      </style>
   </head>
   
   <body>
      <form  th:action = "@{/location}" th:object = "${location}" method="post">
         <p>
            <label>ID</label>
            <input name = "id" type="number">
         </p>
         <p>
            <label>Code</label>
            <input name = "lcode" type="text">
         </p>
         <p>
            <label>Name</label>
            <input name = "lname" type="text">
         </p>
         <p>
            <label>Type</label>
            <p><input name = "itype" type="radio"  value="Urban" checked> <label>Urban</label></p>
            <p><input name = "itype" type="radio"  value="Rural"> <label>Rural</label></p>
         </p>
         <input type="submit" value="Save">
      </form>
      <h3 >
         ${value}
      </h3>
   </body>

</html>