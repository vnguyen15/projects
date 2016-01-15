

<?php

   $mysqli_connection = new MySQLi('vergil.u.washington.edu', 'root', 'justdata16', 'mydb', 1111);

   if($mysqli_connection->connect_error){
     echo "Not connected, error: ".$mysqli_connection->connect_error;
   }
   else{
      echo "Connected.";
   }

?>
