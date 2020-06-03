<?php

include 'verificaserver.php';

include 'header.html';

ini_set('max_execution_time', 0);

//****************************************GRÁFICO DE TIPO ESTÁTICO POR TIPO DINÂMICO****************************************
$json_estatico = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarTipoEstaticoServlet');

$tipos_estaticos = json_decode($json_estatico, true);

$json_dinamico = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarTipoDinamicoServlet');

$tipos_dinamicos = json_decode($json_dinamico, true);

//****************************************GRÁFICO DA INTÂNCIA ESTÁTICA POR DINÂMICA****************************************

$json_instancia = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarInstanciaEstaticaServlet');

$instancias_estaticas = json_decode($json_instancia, true);

$json_dinamica = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarInstanciaDinamicaServlet');

$instancias_dinamicas = json_decode($json_dinamica, true);

//****************************************GRÁFICO DA EXTRAÇÃO POR DOMÍNIO****************************************
$json_extracao = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarExtracaoServlet');

$extracao = json_decode($json_extracao, true);

$dominio =  array();

for($i = 0; $i < sizeof($extracao); $i++){
  array_push($dominio,$extracao[$i]["linkDominio"]);
}

$array = array_count_values($dominio);

//****************************************GRÁFICO DA EXTRAÇÃO DE LINK POR DOMÍNIO****************************************
$domLink =  array();

for($i = 0; $i < sizeof($extracao); $i++){
  for($j = 0; $j < sizeof($instancias_dinamicas); $j++){
    if($extracao[$i]["id"] == $instancias_dinamicas[$j]["fk_id_extracao"]){
      array_push($domLink,$extracao[$i]["linkDominio"]);
    }
  }
}

$dom_link = array_count_values($domLink);

//****************************************GRÁFICO DE ENTIDADES PÚBLICAS E PRIVADAS****************************************
$json_entidade = file_get_contents('http://localhost:51202/ExtratorSemanticoCognitivo/ListarEntidadeServlet');

$entidades = json_decode($json_entidade, true);

$entLink =  array();

for($i = 0; $i < sizeof($instancias_dinamicas); $i++){
  for($l = 0; $l < sizeof($extracao); $l++){
    for($j = 0; $j < sizeof($instancias_estaticas); $j++){
      for($k = 0; $k < sizeof($entidades); $k++){
          if(($entidades[$k]['id'] == $instancias_estaticas[$j]['fk_id_entidade']) && (strpos($instancias_estaticas[$j]['site'], $extracao[$l]['linkDominio']) || $instancias_estaticas[$j]['site'] == $extracao[$l]['linkDominio']) && ($extracao[$l]['id'] == $instancias_dinamicas[$i]["fk_id_extracao"])){
            array_push($entLink,$entidades[$k]['nome']);
          }
      }
    }
  }
}

$entidade_cont = array_count_values($entLink);

//****************************************GRÁFICO DE TIPOS DINÂMICOS***************************************
$tdin =  array();

for($i = 0; $i < sizeof($instancias_dinamicas); $i++){
  for($j = 0; $j < sizeof($extracao); $j++){
    for($k = 0; $k < sizeof($tipos_dinamicos); $k++){
      if(($instancias_dinamicas[$i]['fk_id_extracao'] == $extracao[$j]['id']) && ($extracao[$j]['fk_id_tipo'] == $tipos_dinamicos[$k]['id'])){
        array_push($tdin,$tipos_dinamicos[$k]['nome']);
      }
    }
  }
}

$tipo_dinamico_cont = array_count_values($tdin);

?>
<script type="text/javascript">
  window.onload = function () {

  var chart_instEstDin = new CanvasJS.Chart("chart_instEstDin", {
    theme: "light2",
  	animationEnabled: true,
  	title:{
  		text: "Instância Estática X Instância Dinâmica"
  	},
  	data: [
  	{
  		// Change type to "bar", "area", "spline", "pie",etc.
  		type: "column",
  		dataPoints: [
  			{ label: "Instância Estática",  y: <?php echo sizeof($instancias_estaticas)?> },
  			{ label: "Instância Dinâmica", y: <?php echo sizeof($instancias_dinamicas)?>  }
  		]
  	}
  	]
  });

  var chart_tipoEstDin = new CanvasJS.Chart("chart_tipoEstDin", {
  	theme: "light2",
  	animationEnabled: true,
  	title:{
  		text: "Tipo Estático X Tipo Dinâmico"
  	},
  	data: [
  	{
  		// Change type to "bar", "area", "spline", "pie",etc.
  		type: "doughnut",
  		dataPoints: [
  			{ label: "Tipo Estático",  y: <?php echo sizeof($tipos_estaticos)?> },
  			{ label: "Tipo Dinâmico", y: <?php echo sizeof($tipos_dinamicos)?>  }
  		]
  	}
  	]
  });

  var chart_extDomin = new CanvasJS.Chart("chart_extDomin",
   {
     theme: "light2",
   	 animationEnabled: true,
     title:{
       text: "Extrações por domínio"
     },
     data: [
     {
      type: "pie",
      dataPoints: [
      <?php
      while ($dom = current($array)) {
        ?>
        {  y: <?php echo $dom?>, indexLabel: "<?php echo key($array)?>" },
        <?php
          next($array);
      }
      ?>
      ]
    }
    ]
  });

  var chart_linkDomin = new CanvasJS.Chart("chart_linkDomin",
   {
     theme: "light2",
   	 animationEnabled: true,
     title:{
       text: "Links Informacionais por domínio"
     },
     data: [
     {
      type: "column",
      dataPoints: [
      <?php
      while ($dom1 = current($dom_link)) {
        ?>
        {  y: <?php echo $dom1?>, indexLabel: "<?php echo key($dom_link)?>" },
        <?php
          next($dom_link);
      }
      ?>
      ]
    }
    ]
  });

  var chart_entidade = new CanvasJS.Chart("chart_entidade",
   {
     theme: "light2",
   	 animationEnabled: true,
     title:{
       text: "Extrações Públicas X Extrações Privadas"
     },
     data: [
     {
      type: "doughnut",
      dataPoints: [
      <?php
      while ($ent = current($entidade_cont)) {
        ?>
        {  y: <?php echo $ent?>, indexLabel: "<?php echo key($entidade_cont)?>" },
        <?php
          next($entidade_cont);
      }
      ?>
      ]
    }
    ]
  });

  var chart_tipoDinamico = new CanvasJS.Chart("chart_tipoDinamico",
   {
     theme: "light2",
   	 animationEnabled: true,
     title:{
       text: "Links Informacionais por Tipos Dinâmicos"
     },
     data: [
     {
      type: "column",
      dataPoints: [
      <?php
      while ($tdinamico = current($tipo_dinamico_cont)) {
        ?>
        {  y: <?php echo $tdinamico?>, indexLabel: "<?php echo key($tipo_dinamico_cont)?>" },
        <?php
          next($tipo_dinamico_cont);
      }
      ?>
      ]
    }
    ]
  });


  chart_instEstDin.render();
  chart_tipoEstDin.render();
  chart_extDomin.render();
  chart_linkDomin.render();
  chart_entidade.render();
  chart_tipoDinamico.render();
}
</script>
<br>
<div class="row">
              <!--******************INSTÂNCIA ESTÁTICA E DINÂMICA******************-->
              <div class="col-md-6">
								<div class="card card-bordered style-primary">
									<div class="row">
											<div class="card-head">
												<header><i class="fa fa-bar-chart"></i> Relação quantitava entre Instâncias Estáticas e Dinâmicas</header>
											</div><!--end .card-head -->
                      <div id="chart_instEstDin" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
									</div><!--end .row -->
								</div><!--end .card -->
							</div><!--end .col -->

              <!--******************TIPO ESTÁTICO E DINÂMICO******************-->
							<div class="col-md-6">
								<div class="card card-bordered style-primary">
									<div class="row">
											<div class="card-head">
												<header><i class="fa fa-pie-chart"></i> Relação quantitava entre Tipos Estáticos e Dinâmicos</header>
											</div><!--end .card-head -->
                      <div id="chart_tipoEstDin" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
									</div><!--end .row -->
								</div><!--end .card -->
							</div><!--end .col -->

              <!--******************EXTRAÇÕES POR DOMÍNIO******************-->
              <div class="col-md-6">
								<div class="card card-bordered style-primary">
									<div class="row">
											<div class="card-head">
												<header><i class="fa fa-pie-chart"></i> Relação quantitava de extrações por domínio</header>
											</div><!--end .card-head -->
                      <div id="chart_extDomin" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
									</div><!--end .row -->
								</div><!--end .card -->
							</div><!--end .col -->

              <!--******************LINKS PERSISTIDOS POR DOMÍNIO******************-->
              <div class="col-md-6">
								<div class="card card-bordered style-primary">
									<div class="row">
											<div class="card-head">
												<header><i class="fa fa-bar-chart"></i> Relação quantitava de Links Informacionais persistidos por domínio</header>
											</div><!--end .card-head -->
                      <div id="chart_linkDomin" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
									</div><!--end .row -->
								</div><!--end .card -->
							</div><!--end .col -->

              <!--******************INFORMAÇÕES ADVINDAS DE ENTIDADES PÚBLICAS E PRIVADAS******************-->
            <div class="col-md-6">
              <div class="card card-bordered style-primary">
                <div class="row">
                    <div class="card-head">
                      <header><i class="fa fa-pie-chart"></i> Relação quantitava de informações advindas de entidades públicas e privadas</header>
                    </div><!--end .card-head -->
                    <div id="chart_entidade" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
                </div><!--end .row -->
              </div><!--end .card -->
            </div><!--end .col -->

              <!--******************INFORMAÇÕES ADVINDAS DE EVENTOS, NOTÍCIAS E EDITAIS******************-->
              <div class="col-md-6">
                <div class="card card-bordered style-primary">
                  <div class="row">
                      <div class="card-head">
                        <header><i class="fa fa-bar-chart"></i> Relação quantitava de informações advindas de eventos, notícias e editais</header>
                      </div><!--end .card-head -->
                      <div id="chart_tipoDinamico" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
                  </div><!--end .row -->
                </div><!--end .card -->
              </div><!--end .col -->

	</div>



<?php
include 'footer.html';

?>
