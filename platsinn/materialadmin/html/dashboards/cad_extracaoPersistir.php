<?php

include 'verificaserver.php';

include 'header.html';

ini_set('max_execution_time', 0);

include 'loading_pacman.html';

include 'footer.html';

if(isset($_GET['link'])){
  	if(isset($_GET['profundidade'])){
  		if(isset($_GET['tipo_dinamico'])){

  			$link = $_GET['link'];
  			$profundidade = $_GET['profundidade'];
  			$tipo_dinamico = $_GET['tipo_dinamico'];

  			//*********************CURL**************************
  			$curl = curl_init();

  			$url = 'http://localhost:51202/ExtratorSemanticoCognitivo/CadastrarExtracaoServlet?link='.$link.'&tipo_dinamico='.$tipo_dinamico.'&profundidade='.$profundidade.'';

  			curl_setopt($curl, CURLOPT_URL,$url);
  			curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);

  			$resultado = curl_exec($curl);

  			curl_close($curl);

  			if($resultado == "true"){
  				?>
  				<script>
  				swal("Feito!", "Cadastro da extração realizado!", "success")
  				.then((value) => {
  					window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_extracao.php");
  				});
  				</script>
  				<?php
  			}else if($resultado == "false"){
  				?>
  				<script>
  				swal("Erro!", "Ocorreu um erro ao cadastrar a extração!", "error")
  				.then((value) => {
  					window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_extracao.php");
  				});
  				</script>
  				<?php
  			}else{
  				?>
  				<script>
  				swal("Erro!", "Resultado inesperado!", "error")
  				.then((value) => {
  					window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_extracao.php");
  				});
  				</script>
  				<?php

  			}
  		}else{
        ?>
        <script>
        swal("Erro!", "Informe todos os parâmetros necessários!", "error")
        .then((value) => {
          window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_extracao.php");
        });
        </script>
        <?php
      }
  	}else{
      ?>
      <script>
      swal("Erro!", "Informe todos os parâmetros necessários!", "error")
      .then((value) => {
        window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_extracao.php");
      });
      </script>
      <?php
    }
}else{
  ?>
  <script>
  swal("Erro!", "Informe todos os parâmetros necessários!", "error")
  .then((value) => {
    window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_extracao.php");
  });
  </script>
  <?php
}
?>
