
<?php

ini_set('max_execution_time', 0);

setlocale(LC_ALL,'pt_BR.UTF8');
mb_internal_encoding('UTF8');
mb_regex_encoding('UTF8');

include 'header.html';

include 'verificaserver.php';

if(isset($_GET['dominio'])){
  if(isset($_GET['tipo'])){
    if(isset($_GET['quantidade'])){

      $dominio = $_GET['dominio'];
      $tipo = $_GET['tipo'];
      $quantidade = $_GET['quantidade'];

    }
  }
}

$curl= curl_init();

$url = getUrlConsulta($dominio,$tipo,$quantidade);

curl_setopt($curl, CURLOPT_URL,$url);
curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);

$resultado = curl_exec($curl);

curl_close($curl);

$consulta_sparql = json_decode($resultado, true);

//***********************************HEAVY TABLE***********************************
?>
<style>
* {
  box-sizing: border-box;
}
body {
  font-family: "Open Sans", arial;
}
table {
  width: 100%;
  max-width: 1200px;
  height: 320px;
  border-collapse: collapse;
  border: 1px solid #000000;
  margin: 50px auto;
  background: white;
}
th {
  background: #0aa89e;
  height: 54px;
  width: 25%;
  font-weight: lighter;
  color: white;
  border: 1px solid #000000;
  transition: all 0.2s;
}
tr {
  border: 1px solid #000000;
}
tr:last-child {
  border-bottom: 0px;
}
td {
  border-right: 1px solid #000000;
  padding: 10px;
  transition: all 0.2s;
}
td:last-child {
  border-right: 0px;
}
td.selected {
  background: #d7e4ef;
  z-index: ;
}
td input {
  font-size: 14px;
  background: none;
  outline: none;
  border: 0;
  display: table-cell;
  height: 100%;
  width: 100%;
}
td input:focus {
  box-shadow: 0 1px 0 steelblue;
  color: steelblue;
}
::-moz-selection {
  background: steelblue;
  color: white;
}
::selection {
  background: steelblue;
  color: white;
}
.heavyTable {
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
  animation: float 5s infinite;
}
.main {
  max-width: 900px;
  padding: 10px;
  margin: auto;
}
.content {
  color: white;
  text-align: center;
}
</style>
<script>
// jQuery function
(function($) {
$.fn.heavyTable = function(params) {

  params = $.extend( {
    startPosition: {
      x: 1,
      y: 1
    }
  }, params);

  this.each(function() {
    var
      $hTable = $(this).find('tbody'),
      i = 0,
      x = params.startPosition.x,
      y = params.startPosition.y,
      max = {
        y: $hTable.find('tr').length,
        x: $hTable.parent().find('th').length
      };

    //console.log(xMax + '*' + yMax);

    function clearCell () {
      content = $hTable.find('.selected input').val();
      $hTable.find('.selected').html(content);
      $hTable.find('.selected').toggleClass('selected');
    }

    function selectCell () {
      if ( y > max.y ) y = max.y;
      if ( x > max.x ) x = max.x;
      if ( y < 1 ) y = 1;
      if ( x < 1 ) x = 1;
      currentCell =
       $hTable
          .find('tr:nth-child('+(y)+')')
          .find('td:nth-child('+(x)+')');
      content = currentCell.html();
      currentCell
        .toggleClass('selected')
      return currentCell;
    }

    function edit (currentElement) {
      var input = $('<input>', {type: "text"})
        .val(currentElement.html())
      currentElement.html(input)
      input.focus();
    }

    $hTable.find('td').click( function () {
      clearCell();
      x = ($hTable.find('td').index(this) % (max.x) + 1);
      y = ($hTable.find('tr').index($(this).parent()) + 1);
      edit(selectCell());
    });

    $(document).keydown(function(e){
      if (e.keyCode == 13) {
        clearCell();
        edit(selectCell());
      } else if (e.keyCode >= 37 && e.keyCode <= 40  ) {

        clearCell();
        switch (e.keyCode) {
          case 37: x--;
          break;
          case 38: y--;
          break;
          case 39: x++;
          break;
          case 40: y++;
          break;
        }
        selectCell();
        return false;
      }
    });
  });
};
})(jQuery);

// call our jQuery function

$('.heavyTable').heavyTable({
  xPosition: 2,
  yPosition: 2
});
</script>
<div>
  <table class="heavyTable">
  <thead>
    <tr>
      <th style="color:#000000; font-size:18px; width:50px" ><center><b>Número da notícia</b></center></th>
      <th style="color:#000000; font-size:18px; width:50px"><center><b>Descrição</b></center></th>
    </tr>
  </thead>
  <tbody>
    <?php for($i = 0; $i < sizeof($consulta_sparql['results']['bindings']); $i++){?>
    <tr>
      <td><center><?php echo $i+1?></center></td>
      <td style="text-align: justify">
        <?php
        //**********************SUBSTITUIÇÃO DE CARACTERES ESPECIAIS**********************
        $conteudo = $consulta_sparql['results']['bindings'][$i]['descricao']['value'];
        utf8_encode($conteudo);

        $conteudo = preg_replace("/&([a-z])[a-z]+;/i", "$1", htmlentities(trim($conteudo)));
        echo $conteudo;
        ?>
      </td>
    </tr>
    <tr>
      <td style="border-top: 1px solid #ffffff;" ></td>
      <td >
        <div class="div_cog">
          <div>
            <span><b>Link: </b><?php echo $consulta_sparql['results']['bindings'][$i]['link']['value'] ?></span>
          </div>
          <div class="text-right">
            <form method="post" action="filtro_watson.php">
              <input type="hidden" name="conteudo" value="<?php echo $conteudo; ?>">
              <button type="submit" class="btn ink-reaction btn-primary" style="text-align: right">Análise Cognitiva</button>
            </form>
          </div>
        </div>
      </td>
    </tr>
    <tr>
      <td style="background: #0aa89e; border-right: 1px solid #0aa89e;"></td>
      <td style="background: #0aa89e"></td>
    </tr>
    <?php }?>
  </tbody>
</table>

<?php
//****************************************************************************

//******************OBTER URL DE CONSULTA******************
function getUrlConsulta($dominio, $tipo, $limite)
{

  if($tipo == "noticia"){
      $query ="
          PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
          PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
          PREFIX epi: <http://www.inovamarilia.com.br/ontologia#>

          SELECT ?descricao ?link WHERE {
          ?meal epi:descricaoNoticia ?descricao;
                epi:linkNoticia ?link;
                epi:dominioNoticia ?dominio.
          VALUES ?dominio { '".$dominio."'}
          }
          LIMIT ".$limite."
      ";
  }else if($tipo == "evento"){
      $query ="
          PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
          PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
          PREFIX epi: <http://www.inovamarilia.com.br/ontologia#>

          SELECT ?descricao ?link WHERE {
          ?meal epi:descricaoEvento ?descricao;
                epi:linkEvento ?link;
                epi:dominioEvento ?dominio.
          VALUES ?dominio { '".$dominio."'}
          }
          LIMIT ".$limite."
      ";
  }else if($tipo == "edital"){
      $query ="
          PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
          PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
          PREFIX epi: <http://www.inovamarilia.com.br/ontologia#>

          SELECT ?descricao ?link WHERE {
          ?meal epi:descricaoEdital ?descricao;
                epi:linkEdital ?link;
                epi:dominioEdital ?dominio.
          VALUES ?dominio { '".$dominio."'}
          }
          LIMIT ".$limite."
      ";
  }else{
    $query = "";
  }
  $format = 'json';

  str_replace(" ","",$query);

  $urlConsulta = 'http://localhost:3030/inovaOnto/sparql?'
    .'query='.urlencode($query)
    .'&format='.$format;

   return $urlConsulta;
}

//******************ESTILO DA DIV DO BOTÃO DE ANÁLISE COGNITIVA******************
?>
<style>
.div_cog{
  height:50px !important; margin:10px auto !important;
}
.div_cog {
     align-items: center;
     display: flex;
     justify-content: space-between;
}

.div_cog > * {
     flex: 0 0 auto;
}
</style>
<?php
include 'footer.html';
?>
