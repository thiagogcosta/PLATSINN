<?php

include 'verificaserver.php';

include 'header.html';

if(isset($_GET['extracao'])){
	$extracao = $_GET['extracao'];

	//*********************CURL**************************
	$curl = curl_init();

	$query = array(
			'extracao' => $extracao
	);

	$url = 'http://localhost:51202/ExtratorSemanticoCognitivo/DeletarExtracaoServlet?' . http_build_query($query);

	curl_setopt($curl, CURLOPT_URL,$url);
	curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);

	$resultado = curl_exec($curl);

	curl_close($curl);

	if($resultado){
		?>
		<script>
		swal("Feito!", "Extração excluída!", "success")
		.then((value) => {
			window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/del_extracao.php");
		});
		</script>
		<?php
	}else{
		?>
		<script>
		swal("Erro!", "Ocorreu um erro ao deletar a Extração!", "error")
		.then((value) => {
			window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/del_extracao.php");
		});
		</script>
		<?php
	}

}else{
	//*********************TIPO DINÂMICO**************************
  $json_tdinamico = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarTipoDinamicoServlet');

	$tipos_dinamicos = json_decode($json_tdinamico, true);

	//*********************INSTÂNCIAS DINÂMICAS**************************
  $json_dinamico = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarExtracaoServlet');

	$instancias_dinamicas = json_decode($json_dinamico, true);

}
?>
<html>
  <div class="row">
            <div class="col-lg-12">
              <h1 class="text-primary">Deletar Extração</h1>
            </div><!--end .col -->
            <div class="col-lg-3 col-md-4">
              <article class="margin-bottom-xxl">
                <p class="lead">
                  Seção responsável por excluir extrações
                </p>
                <p>
                  Nesta tabela consta uma lista com todas as extrações cadastradas, portanto somente clique no botão de excluir daquela que deseja realmente deletar!
                </p>
              </article>
            </div><!--end .col -->
      <div class="col-lg-offset-1 col-md-8">
						<table class="table table-bordered table-striped">
							<thead>
								<tr style="background:#0aa89e">
									<th style="color:#000000"><b>Domínio</b></th>
									<th style="color:#000000"><b>Link Extração (Seção de Extração)</b></th>
									<th style="color:#000000"><b>Links Persistidos</b></th>
									<th style="color:#000000"><b>Horário</b></th>
									<th style="color:#000000"><b>Tipo Dinâmico</b></th>
									<th class="text-center" style="color:#000000"><b>Excluir</b></th>
								</tr>
							</thead>
							<tbody>
								<?php

								if(isset($instancias_dinamicas)){
									if(isset($tipos_dinamicos)){

											for($i = 0; $i < sizeof($instancias_dinamicas); $i++){

													for($j = 0; $j < sizeof($tipos_dinamicos); $j++){
														if($tipos_dinamicos[$j]["id"] == $instancias_dinamicas[$i]["fk_id_tipo"]){
															$tipo_nome = $tipos_dinamicos[$j]["nome"];
														}
													}

								?>
                <tr>
									<td><?php echo $instancias_dinamicas[$i]["linkDominio"]?></td>
									<td><?php echo $instancias_dinamicas[$i]["linkExtracao"]?></td>
									<td><?php $qtd = $instancias_dinamicas[$i]["limiteSuperior"] - $instancias_dinamicas[$i]["limiteInferior"]; echo $qtd ?></td>
									<td><?php echo $instancias_dinamicas[$i]["horario"]?></td>
									<td><?php echo $tipo_nome?></td>
									<td class="text-center">
										<form method="get"><button type="submit" name="extracao" value="<?php echo $instancias_dinamicas[$i]["linkExtracao"]?>" class="btn btn-icon-toggle" data-toggle="tooltip" data-placement="top" data-original-title="Excluir"><i class="fa fa-trash-o"></i></button></form>
									</td>
								</tr>
								<?php
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
