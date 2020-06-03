<?php

include 'verificaserver.php';

include 'header.html';

if(isset($_GET['codigo'])){
		$codigo = $_GET['codigo'];

		//*********************CURL**************************
		$curl = curl_init();

		$query = array(
		    'codigo' => $codigo
		);

		$url = 'http://localhost:51202/ExtratorSemanticoCognitivo/DeletarInstanciaEstaticaServlet?' . http_build_query($query);

		curl_setopt($curl, CURLOPT_URL,$url);
		curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);

		$resultado = curl_exec($curl);

		curl_close($curl);

		if($resultado == "true"){
			?>
			<script>
			swal("Feito!", "Instância Estática excluída!", "success")
			.then((value) => {
  			window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/del_instestatica.php");
			});
			</script>
			<?php
		}else if($resultado == "false"){
			?>
			<script>
			swal("Erro!", "Ocorreu um erro ao deletar a Instância Estática!", "error")
			.then((value) => {
  			window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/del_instestatica.php");
			});
			</script>
			<?php
		}else{
			?>
			<script>
			swal("Erro!", "Ocorreu um erro ao deletar a Instância Estática!", "error")
			.then((value) => {
				window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/del_extracao.php");
			});
			</script>
			<?php
		}

}else{
	//*********************TIPO ESTÁTICO**************************
  $json_estatico = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarTipoEstaticoServlet');

	$tipos_estaticos = json_decode($json_estatico, true);

	//*********************TIPO ENTIDADE**************************
  $json_entidade = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarEntidadeServlet');

	$tipos_entidade = json_decode($json_entidade, true);

	//*********************INSTÂNCIAS ESTÁTICAS**************************
  $json_instancia = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarInstanciaEstaticaServlet');

	$instancias_estaticas = json_decode($json_instancia, true);

}
?>
<html>
  <div class="row">
            <div class="col-lg-12">
              <h1 class="text-primary">Deletar Instância Estática</h1>
            </div><!--end .col -->
            <div class="col-lg-3 col-md-4">
              <article class="margin-bottom-xxl">
                <p class="lead">
                  Seção responsável por excluir instâncias estáticas
                </p>
                <p>
                  Nesta tabela consta uma lista com todas as instâncias estáticas cadastradas, portanto somente clique no botão de excluir daquela que deseja realmente deletar!
                </p>
              </article>
            </div><!--end .col -->
      <div class="col-lg-offset-1 col-md-8">
						<table class="table table-bordered table-striped">
							<thead>
								<tr style="background:#0aa89e">
									<th style="color:#000000"><b>Nome</b></th>
									<th style="color:#000000"><b>Site</b></th>
									<th style="color:#000000"><b>Entidade</b></th>
									<th style="color:#000000"><b>Tipo Estático</b></th>
									<th class="text-center" style="color:#000000"><b>Excluir</b></th>
								</tr>
							</thead>
							<tbody>
								<?php

								if(isset($tipos_estaticos)){
									if(isset($tipos_entidade)){
										if(isset($instancias_estaticas)){

											for($i = 0; $i < sizeof($instancias_estaticas); $i++){

													for($j = 0; $j < sizeof($tipos_entidade); $j++){
														if($tipos_entidade[$j]["id"] == $instancias_estaticas[$i]["fk_id_entidade"]){
															$entidade_nome = $tipos_entidade[$j]["nome"];
														}
													}

													for($j = 0; $j < sizeof($tipos_estaticos); $j++){
														if($tipos_estaticos[$j]["id"] == $instancias_estaticas[$i]["fk_tipo_estatico"]){
															$tipo_nome = $tipos_estaticos[$j]["nome"];
														}
													}

								?>
                <tr>
									<td><?php echo $instancias_estaticas[$i]["nome"]?></td>
									<td><?php echo $instancias_estaticas[$i]["site"]?></td>
									<td><?php echo $entidade_nome?></td>
									<td><?php echo $tipo_nome?></td>
									<td class="text-center">
										<form method="get"><button type="submit" name="codigo" value="<?php echo $instancias_estaticas[$i]["cod_estatico"]?>" class="btn btn-icon-toggle" data-toggle="tooltip" data-placement="top" data-original-title="Excluir"><i class="fa fa-trash-o"></i></button></form>
									</td>
								</tr>
								<?php
                				}
											}
										}
									}
								?>
							</tbody>
						</table>
					</div>

    </div>
</html>
<?php

include 'footer.html';

?>
