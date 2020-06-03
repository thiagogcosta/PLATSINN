<?php

include 'verificaserver.php';

include 'header.html';

$json = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarTipoDinamicoServlet');

$tipos_dinamicos = json_decode($json, true);

?>
<html>
	<div id="cadastro" class="row">
							<div class="col-lg-12">
								<h1 class="text-primary">Realizar Extração</h1>
							</div><!--end .col -->
							<div class="col-lg-3 col-md-4">
								<article class="margin-bottom-xxl">
									<p class="lead">
										  Seção responsável pelo cadastro das extrações
									</p>
									<p>
										Os parâmetros necessários para a extração são: endereço eletrônico da subdivisão do site, profundidade da busca e qual o tipo de seção (Notícias, Eventos ou Editais).
									</p>
								</article>
							</div><!--end .col -->
							<div class="col-lg-offset-1 col-md-8">
								<form class="form form-validate floating-label" novalidate="novalidate" action="cad_extracaoPersistir.php" method="GET">
									<div class="card">
										<div class="card-body">
											<div class="form-group">
												<input type="text" class="form-control" id="link" name="link" required="" data-rule-minlength="2" aria-required="true">
												<label for="link">Link</label>
											</div>
											<div class="form-group">
												<input type="text" class="form-control" id="profundidade" name="profundidade" required="" data-rule-minlength="2" aria-required="true">
												<label for="profundidade">Profundidade</label>
											</div>

											<div class="form-group">
												<br>
												<select id="tipo_dinamico" name="tipo_dinamico" class="form-control" required="" aria-required="true">
													<?php
													for($i = 0; $i < sizeof($tipos_dinamicos); $i++){
														?>
														<option value="<?php echo $tipos_dinamicos[$i]['nome'] ?>"><?php echo $tipos_dinamicos[$i]['nome']?></option>
														<?php
													}
													?>
												</select>
												<label for="tipo_dinamico">Tipos Dinâmicos</label>
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
