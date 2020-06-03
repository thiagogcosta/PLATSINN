<?php
try{
    $json = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/');

    if(empty($json)){

      ?>
        <script>
            window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/serveroff.php");
        </script>
      <?php
    }
  } catch (Exception $e) {
    echo 'Exceção capturada: ',  $e->getMessage(), "\n";
  }
?>
