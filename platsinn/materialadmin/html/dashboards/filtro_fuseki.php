<?php
include 'header.html';

include 'verificaserver.php';

//*********************INSTÂNCIAS DINÂMICAS**************************
$json_dinamico = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarExtracaoServlet');

$instancias_dinamicas = json_decode($json_dinamico, true);

$dominios = array();

for($i = 0; $i < sizeof($instancias_dinamicas); $i++){
  if(!in_array($instancias_dinamicas[$i]['linkDominio'], $dominios)){
    array_push($dominios,$instancias_dinamicas[$i]['linkDominio']);
  }
}


?>
      <div id="cadastro" class="row">
      	<div class="col-lg-12">
      		<h1 class="text-primary">Filtro Informacional</h1>
      	</div><!--end .col -->
      	<div class="col-lg-3 col-md-4">
      		<article class="margin-bottom-xxl">
      			<p class="lead">
      				  Seção responsável por filtrar as informações obtidas com as extrações
      			</p>
      			<p>
      				Os parâmetros necessários para a filtragem são: domínio, tipo dinâmico e quantidade exibida.
      			</p>
      		</article>
      	</div><!--end .col -->
      	<div class="col-lg-offset-1 col-md-8">
      		<form class="form form-validate floating-label" novalidate="novalidate" action="filtro_fuseki_sparql.php" method="GET">
      			<div class="card">
      				<div class="card-body">

                <div class="form-group">
      						<br>
      						<select id="dominio" name="dominio" class="form-control static dirty" required="" aria-required="true">
                    <?php
                    for($i = 0; $i < sizeof($dominios); $i++){
                        ?>
                        <option value="<?php echo $dominios[$i] ?>"><?php echo $dominios[$i]?></option>
                        <?php
                    }
                    ?>
									</select>
      						<label for="dominio">Domínio</label>
      					</div>

      					<div class="form-group">
      						<br>
      						<select id="tipo" name="tipo" class="form-control static dirty" required="" aria-required="true">
						         <option value="noticia">noticia</option>
					           <option value="evento">evento</option>
						         <option value="edital">edital</option>
  								</select>
      						<label for="tipo">Tipos Dinâmicos</label>
      					</div>

                <div class="form-group">
      						<br>
      						<select id="quantidade" name="quantidade" class="form-control static dirty" required="" aria-required="true">
                      <option value="10">10</option>
                      <option value="50">50</option>
                      <option value="100">100</option>
                      <option value="500">500</option>
  										<option value="1000">1000</option>
                      <option value="10000">10000</option>
  								</select>
      						<label for="quantidade">Quantidade Exibida</label>
      					</div>

      				</div><!--end .card-body -->
      				<div class="card-actionbar">
      					<div class="card-actionbar-row">
      						<button id="button" type="submit" class="btn btn-flat btn-primary ink-reaction">Filtrar</button>
      					</div>
      				</div><!--end .card-actionbar -->
      			</div><!--end .card -->
      		</form>
      	</div><!--end .col -->
      </div>
<?php
include 'footer.html';
?>
