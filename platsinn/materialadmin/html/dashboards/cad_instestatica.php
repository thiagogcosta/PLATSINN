<?php

include 'verificaserver.php';

include 'header.html';

if(isset($_GET['nome'])){
	if(isset($_GET['site'])){
		if(isset($_GET['id_tipo_estatico'])){
      if(isset($_GET['id_entidade'])){

				$nome = $_GET['nome'];
				$site = $_GET['site'];
				$tipo_estatico = $_GET['id_tipo_estatico'];
				$tipo_entidade = $_GET['id_entidade'];

				//*********************CURL**************************
				$curl = curl_init();

				$query = array(
				    'nome' => $nome,
						'site' => $site,
						'id_tipo_estatico' => $tipo_estatico,
						'id_entidade' => $tipo_entidade
				);

				$url = 'http://localhost:51202/ExtratorSemanticoCognitivo/CadastrarInstanciaEstaticaServlet?' . http_build_query($query);

				curl_setopt($curl, CURLOPT_URL,$url);
				curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);

				$resultado = curl_exec($curl);

				curl_close($curl);

				if($resultado == "true"){
					?>
					<script>
					swal("Feito!", "Cadastro da instância estática realizado!", "success")
					.then((value) => {
		  			window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_instestatica.php");
					});
					</script>
					<?php
				}else if($resultado == "false"){
					?>
					<script>
					swal("Erro!", "Ocorreu um erro ao cadastrar a instância estática!", "error")
					.then((value) => {
		  			window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_instestatica.php");
					});
					</script>
					<?php
				}else{
					?>
					<script>
					swal("Erro!", "Resultado inesperado!", "error")
					.then((value) => {
		  			window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/cad_instestatica.php");
					});
					</script>
					<?php
				}
      }
		}
	}
}else{
	$json_estatico = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarTipoEstaticoServlet');

	$tipos_estaticos = json_decode($json_estatico, true);

  $json_entidade = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarEntidadeServlet');

	$tipos_entidade = json_decode($json_entidade, true);

}
?>
<html>
	<div class="row">
							<div class="col-lg-12">
								<h1 class="text-primary">Cadastrar Instância Estática</h1>
							</div><!--end .col -->
							<div class="col-lg-3 col-md-4">
								<article class="margin-bottom-xxl">
									<p class="lead">
                     Seção responsável pelo cadastro de instâncias estáticas
									</p>
									<p>
										Os parâmetros necessários para a criação são: nome, site, qual entidade a instâcia pertence e de qual tipo ela é.
									</p>
								</article>
							</div><!--end .col -->
							<div class="col-lg-offset-1 col-md-8">
								<form class="form form-validate floating-label" novalidate="novalidate" method="GET">
									<div class="card">
										<div class="card-body">
											<div class="form-group">
												<input type="text" class="form-control" id="nome" name="nome" required="" data-rule-minlength="2" aria-required="true">
												<label for="nome">Nome</label>
											</div>
											<div class="form-group">
												<input type="text" class="form-control" id="site" name="site" required="" data-rule-minlength="2" aria-required="true">
												<label for="site">Site</label>
											</div>

											<div class="form-group">
                        <br>
                        <select id="id_tipo_estatico" name="id_tipo_estatico" class="form-control" required="" aria-required="true">
													<?php
													for($i = 0; $i < sizeof($tipos_estaticos); $i++){
														?>
														<option value="<?php echo $tipos_estaticos[$i]['id'] ?>"><?php echo $tipos_estaticos[$i]['nome']?></option>
														<?php
													}
													?>
												</select>
												<label for="id_tipo_estatico">Tipos Estáticos</label>
											</div>

                      <div class="form-group">
                        <br>
                        <select id="id_entidade" name="id_entidade" class="form-control" required="" aria-required="true">
													<?php
													for($i = 0; $i < sizeof($tipos_entidade); $i++){
														?>
														<option value="<?php echo $tipos_entidade[$i]['id'] ?>"><?php echo $tipos_entidade[$i]['nome']?></option>
														<?php
													}
													?>
												</select>
												<label for="id_entidade">Tipos de Entidade</label>
											</div>

										</div><!--end .card-body -->
										<div class="card-actionbar">
											<div class="card-actionbar-row">
												<button id="button" type="submit" class="btn btn-flat btn-primary ink-reaction">Cadastrar</button>
											</div>
										</div><!--end .card-actionbar -->
									</div><!--end .card -->
								</form>
							</div><!--end .col -->
						</div>
</html>

<?php

include 'footer.html';

?>
