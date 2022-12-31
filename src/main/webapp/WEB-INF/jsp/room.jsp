<html>
   <head>
      <title>Hello World</title>
      <style>
         .main{
                /* height: 400px;
                width: 900px; */
                display: flex;
                flex-direction: column;
            }  
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
         /* .row{
                flex: 1;
                flex-direction: row;
                display: flex;
            } */
            .elem{
                border: 1px solid black;
                /* flex: 1; */
            }
            .btn{
               height: 50px;
               width: 100px;
               border: 1px black solid;
               text-align: center;
               padding: 15px;
               cursor: pointer;
               
            }
      </style>
   </head>
   
   <body>
      <form  th:action = "@{/index}" th:object = "${room}" method="post" >
         <fieldset>
            <legend>Room</legend>
            <input name="button" type="hidden" value="admin-createRoom">
            <input name="code" type="hidden" value="2">
            <input name="pass" type="hidden" value="2">
            <p>
               <label>Room Code</label>
               <input name = "roomId" type="text">
            </p>
            <p>
            <input name = "cusCode" type="hidden" value="">
         </p>
         <p>
            <input name = "rDate" type="hidden" value="">
         </p>
         <p>
            <label>Price</label>
            <input name = "price" type="number">
         </p>
         <p>
            <label>People number</label>
            <input name = "people" type="number"  >
         </p>
         <input type="submit" value="Create or Update">
         </fieldset>
      </form>
      <div>
         <label class="btn" onclick="Click('allRoom')">allRoom</label>
         <label class="btn" onclick="Click('noemptyRoom')">noemptyRoom</label>
         <label class="btn" onclick="Click('EmptyRoom')">EmptyRoom</label>
         <label class="btn" onclick="Click('allCust')">allCust</label>

      </div>
      <br>
      <div class="main" id="allRoom">
         ${allRoomElem}
      </div>
      <div class="main" id="emptyRoom">
         ${emptyRoomElem}
      </div>
      <div class="main" id="noEmptyRoom">
         ${noEmptyRoomElem}
      </div>
      <div class="main" id="allCust">
         ${allCustElem}
      </div>
      <script>
         
         function Click(page){
            document.querySelectorAll(".main").forEach((i) => {
               i.style.display = "none";
            })
            document.getElementById(page).style.display = "inline";
            console.log("123");
         }
         Click("allRoom")
      </script>
   </body>

</html>