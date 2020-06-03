<?php
ini_set('max_execution_time', 0);

include 'header.html';

include 'verificaserver.php';

//*************ANÁLISE COGNITIVA*************
if(isset($_POST['conteudo'])){
    $conteudo = $_POST['conteudo'];

    //*********************CURL**************************
    $curl = curl_init();

    $query = array(
		    'texto' => $conteudo
		);

		$url = 'http://localhost:51202/ExtratorSemanticoCognitivo/ObterAnaliseCognitiva?' . http_build_query($query);

    //echo "URL:".$url;

		curl_setopt($curl, CURLOPT_URL,$url);
		curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);

		$resultadoCURL = curl_exec($curl);

		curl_close($curl);
    //**********************************************PARA TESTAR**********************************************

    $resultado = '{ "language": "en", "analyzed_text": "Tecnologico Park nThe Botucatu was designed to be a differential environment, prosecutor of inovacao through interacao between knowledge bases (universities, centres and research institutes and development) and productive base (companies). Account as support and inducao of Botucatu county and if characterizes as an instrument of agregacao partners all segments of society that can contribute decisively to the regional development underpins. Acts, primarily, so that the knowledge applied dynamically, the diffuse technology and generation of business, reduce the desigualdadesnsocioeconomicas, being a lever propelling the generation of employment, work and income. Botucatu has a great McCallister in research in medical science and biological. The city of Botucatu houses five units of Unesp : Medical College Botucatu (FMB), Hospital of Botucatu clinics, Faculty of Medicine Veterinaria and ZOOTECHNICS (FMVZ), College of Natural Agronomicas (FCA) and Institute of Biociencias (IB). The presence of a Fatec unit and a technoque School (Etec) Center Paula Souza aggregate diversity education and docencia of the county. Besides academic dan tendency, the productive sector of the region have a significant technological Illustra that relies on concentration of micro, small, medium-sized and large companies, such as Embraer-Neiva, Eucatex, Duratex, Gaius Induscar and Group Centroflora.", "usage": { "features": 6 }, "concepts": [ { "text": "Medicine", "relevance": 0.965713, "dbpedia_resource": "http://dbpedia.org/resource/Medicine" }, { "text": "Medical school", "relevance": 0.838643, "dbpedia_resource": "http://dbpedia.org/resource/Medical_school" }, { "text": "Physician", "relevance": 0.712134, "dbpedia_resource": "http://dbpedia.org/resource/Physician" }, { "text": "Pediatrics", "relevance": 0.676808, "dbpedia_resource": "http://dbpedia.org/resource/Pediatrics" }, { "text": "Anatomy", "relevance": 0.645988, "dbpedia_resource": "http://dbpedia.org/resource/Anatomy" }, { "text": "Psychiatry", "relevance": 0.563445, "dbpedia_resource": "http://dbpedia.org/resource/Psychiatry" }, { "text": "University", "relevance": 0.525751, "dbpedia_resource": "http://dbpedia.org/resource/University" }, { "text": "College", "relevance": 0.524246, "dbpedia_resource": "http://dbpedia.org/resource/College" }, { "text": "Research", "relevance": 0.516639, "dbpedia_resource": "http://dbpedia.org/resource/Research" }, { "text": "Neurology", "relevance": 0.513986, "dbpedia_resource": "http://dbpedia.org/resource/Neurology" }, { "text": "Science", "relevance": 0.5126, "dbpedia_resource": "http://dbpedia.org/resource/Science" }, { "text": "Pathology", "relevance": 0.48648, "dbpedia_resource": "http://dbpedia.org/resource/Pathology" }, { "text": "Ophthalmology", "relevance": 0.462412, "dbpedia_resource": "http://dbpedia.org/resource/Ophthalmology" } ], "entities": [ { "type": "Facility", "relevance": 0.882038, "count": 3, "text": "Tecnologico Park nThe Botucatu", "sentiment": { "score": 0.642978 } }, { "type": "Location", "relevance": 0.724181, "count": 2, "text": "Botucatu county", "sentiment": { "score": 0.700042 }, "disambiguation": { "subtype": [ "StateOrCounty" ] } }, { "type": "Organization", "relevance": 0.483479, "count": 1, "text": "Medical College Botucatu", "sentiment": { "score": 0.0 } }, { "type": "Location", "relevance": 0.434212, "count": 1, "text": "Botucatu", "sentiment": { "score": 0.0 }, "disambiguation": { "name": "Botucatu", "dbpedia_resource": "http://dbpedia.org/resource/Botucatu", "subtype": [ "City" ] } }, { "type": "Facility", "relevance": 0.41454, "count": 1, "text": "Hospital of Botucatu", "sentiment": { "score": -0.309565 } }, { "type": "Person", "relevance": 0.410273, "count": 1, "text": "Paula Souza", "sentiment": { "score": 0.0 } }, { "type": "Person", "relevance": 0.406937, "count": 1, "text": "Gaius Induscar", "sentiment": { "score": 0.0 } }, { "type": "Organization", "relevance": 0.40659, "count": 1, "text": "Faculty of Medicine Veterinaria", "sentiment": { "score": 0.0 } }, { "type": "JobTitle", "relevance": 0.394129, "count": 1, "text": "prosecutor", "sentiment": { "score": 0.0 } }, { "type": "Organization", "relevance": 0.358818, "count": 1, "text": "College of Natural Agronomicas", "sentiment": { "score": 0.0 } }, { "type": "GeographicFeature", "relevance": 0.343782, "count": 1, "text": "Group Centroflora", "sentiment": { "score": 0.0 } }, { "type": "Person", "relevance": 0.341864, "count": 1, "text": "McCallister", "sentiment": { "score": 0.642978 } }, { "type": "Organization", "relevance": 0.336102, "count": 1, "text": "technoque School", "sentiment": { "score": 0.0 } }, { "type": "Location", "relevance": 0.333507, "count": 1, "text": "Fatec", "sentiment": { "score": 0.0 }, "disambiguation": { "subtype": [ "City" ] } }, { "type": "Organization", "relevance": 0.302212, "count": 1, "text": "FCA", "sentiment": { "score": 0.0 } }, { "type": "Organization", "relevance": 0.297973, "count": 1, "text": "Institute of Biociencias", "sentiment": { "score": 0.0 } } ], "keywords": [ { "relevance": 0.962864, "text": "Park nThe Botucatu", "sentiment": { "score": 0.0 } }, { "relevance": 0.934013, "text": "regional development underpins", "sentiment": { "score": 0.700042 } }, { "relevance": 0.893074, "text": "Medical College Botucatu", "sentiment": { "score": 0.0 } }, { "relevance": 0.875332, "text": "Souza aggregate diversity", "sentiment": { "score": 0.0 } }, { "relevance": 0.869562, "text": "academic dan tendency", "sentiment": { "score": 0.0 } }, { "relevance": 0.852988, "text": "significant technological Illustra", "sentiment": { "score": 0.487984 } }, { "relevance": 0.780217, "text": "productive base", "sentiment": { "score": 0.526774 } }, { "relevance": 0.747613, "text": "Botucatu county", "sentiment": { "score": 0.700042 } }, { "relevance": 0.735542, "text": "knowledge bases", "sentiment": { "score": 0.0 } }, { "relevance": 0.734146, "text": "differential environment", "sentiment": { "score": 0.0 } }, { "relevance": 0.734071, "text": "research institutes", "sentiment": { "score": 0.0 } }, { "relevance": 0.733179, "text": "great McCallister", "sentiment": { "score": 0.642978 } }, { "relevance": 0.731204, "text": "diffuse technology", "sentiment": { "score": 0.272292 } }, { "relevance": 0.730928, "text": "productive sector", "sentiment": { "score": 0.487984 } }, { "relevance": 0.730563, "text": "Medicine Veterinaria", "sentiment": { "score": 0.0 } }, { "relevance": 0.723296, "text": "Botucatu clinics", "sentiment": { "score": -0.309565 } }, { "relevance": 0.721932, "text": "Botucatu houses", "sentiment": { "score": 0.0 } }, { "relevance": 0.71152, "text": "Gaius Induscar", "sentiment": { "score": 0.0 } }, { "relevance": 0.711233, "text": "agregacao partners", "sentiment": { "score": 0.700042 } }, { "relevance": 0.701153, "text": "medical science", "sentiment": { "score": 0.642978 } }, { "relevance": 0.691781, "text": "Natural Agronomicas", "sentiment": { "score": 0.0 } }, { "relevance": 0.691118, "text": "large companies", "sentiment": { "score": 0.0 } }, { "relevance": 0.687393, "text": "Group Centroflora", "sentiment": { "score": 0.0 } }, { "relevance": 0.687139, "text": "Fatec unit", "sentiment": { "score": 0.0 } }, { "relevance": 0.570964, "text": "FMB", "sentiment": { "score": 0.0 } }, { "relevance": 0.568829, "text": "Duratex", "sentiment": { "score": 0.0 } }, { "relevance": 0.56484, "text": "characterizes", "sentiment": { "score": 0.700042 } }, { "relevance": 0.557319, "text": "FCA", "sentiment": { "score": 0.0 } }, { "relevance": 0.55204, "text": "lever", "sentiment": { "score": 0.0 } }, { "relevance": 0.551558, "text": "generation", "sentiment": { "score": 0.272292 } }, { "relevance": 0.550549, "text": "IB", "sentiment": { "score": 0.0 } }, { "relevance": 0.547207, "text": "prosecutor", "sentiment": { "score": 0.0 } }, { "relevance": 0.542264, "text": "segments", "sentiment": { "score": 0.700042 } }, { "relevance": 0.536845, "text": "centres", "sentiment": { "score": 0.0 } }, { "relevance": 0.536379, "text": "Paula", "sentiment": { "score": 0.0 } }, { "relevance": 0.535983, "text": "Tecnologico", "sentiment": { "score": 0.0 } }, { "relevance": 0.535816, "text": "concentration", "sentiment": { "score": 0.487984 } }, { "relevance": 0.535731, "text": "inovacao", "sentiment": { "score": 0.0 } }, { "relevance": 0.535695, "text": "interacao", "sentiment": { "score": 0.0 } }, { "relevance": 0.534278, "text": "universities", "sentiment": { "score": 0.0 } }, { "relevance": 0.533072, "text": "Account", "sentiment": { "score": 0.700042 } }, { "relevance": 0.533038, "text": "support", "sentiment": { "score": 0.700042 } }, { "relevance": 0.533005, "text": "inducao", "sentiment": { "score": 0.700042 } }, { "relevance": 0.532853, "text": "instrument", "sentiment": { "score": 0.700042 } }, { "relevance": 0.532735, "text": "society", "sentiment": { "score": 0.700042 } }, { "relevance": 0.531796, "text": "business", "sentiment": { "score": 0.272292 } }, { "relevance": 0.531729, "text": "desigualdadesnsocioeconomicas", "sentiment": { "score": 0.0 } }, { "relevance": 0.53158, "text": "employment", "sentiment": { "score": 0.0 } }, { "relevance": 0.531547, "text": "work", "sentiment": { "score": 0.0 } } ], "categories": [ { "label": "/science", "score": 0.549799 }, { "label": "/education/school", "score": 0.367214 }, { "label": "/technology and computing", "score": 0.335631 } ], "emotion": { "document": { "emotion": { "anger": 0.040583, "disgust": 0.024092, "fear": 0.100509, "joy": 0.62799, "sadness": 0.136772 } } }, "sentiment": { "document": { "score": 0.708704 } } }';


    $resultado2 = '{ "language": "en", "analyzed_text": "In the rugged Colorado Desert of California, there lies buried a treasure ship sailed there hundreds of years ago by either Viking or Spanish explorers. Some say this is legend; others insist it is fact. A few have even claimed to have seen the ship, its wooden remains poking through the sand like the skeleton of a prehistoric beast. Among those who say they’ve come close to the ship is small-town librarian Myrtle Botts. In 1933, she was hiking with her husband in the Anza-Borrego Desert, not far from the border with Mexico. It was early March, so the desert would have been in bloom, its washed-out yellows and grays beaten back by the riotous invasion of wildflowers. Those wildflowers were what brought the Bottses to the desert, and they ended up near a tiny settlement called Agua Caliente. Surrounding place names reflected the strangeness and severity of the land: Moonlight Canyon, Hellhole Canyon, Indian Gorge. Try Newsweek for only $1.25 per week To enter the desert is to succumb to the unknowable. One morning, a prospector appeared in the couple’s camp with news far more astonishing than a new species of desert flora: He’d found a ship lodged in the rocky face of Canebrake Canyon. The vessel was made of wood, and there was a serpentine figure carved into its prow. There were also impressions on its flanks where shields had been attached—all the hallmarks of a Viking craft. Recounting the episode later, Botts said she and her husband saw the ship but couldn’t reach it, so they vowed to return the following day, better prepared for a rugged hike. That wasn’t to be, because, several hours later, there was a 6.4 magnitude earthquake in the waters off Huntington Beach, in Southern California. Botts claimed it dislodged rocks that buried her Viking ship, which she never saw again.There are reasons to doubt her story, yet it is only one of many about sightings of the desert ship. By the time Myrtle and her husband had set out to explore, amid the blooming poppies and evening primrose, the story of the lost desert ship was already about 60 years old.", "usage": { "features": 6 }, "concepts": [ { "text": "Colorado Desert", "relevance": 0.950835, "dbpedia_resource": "http://dbpedia.org/resource/Colorado_Desert" }, { "text": "Desert", "relevance": 0.7866, "dbpedia_resource": "http://dbpedia.org/resource/Desert" }, { "text": "Imperial County, California", "relevance": 0.765572, "dbpedia_resource": "http://dbpedia.org/resource/Imperial_County,_California" }, { "text": "Anza-Borrego Desert State Park", "relevance": 0.761281, "dbpedia_resource": "http://dbpedia.org/resource/Anza-Borrego_Desert_State_Park" }, { "text": "Mars", "relevance": 0.675419, "dbpedia_resource": "http://dbpedia.org/resource/Mars" }, { "text": "Sonoran Desert", "relevance": 0.605702, "dbpedia_resource": "http://dbpedia.org/resource/Sonoran_Desert" }, { "text": "Huntington Beach, California", "relevance": 0.594966, "dbpedia_resource": "http://dbpedia.org/resource/Huntington_Beach,_California" }, { "text": "Colorado", "relevance": 0.574538, "dbpedia_resource": "http://dbpedia.org/resource/Colorado" }, { "text": "Mojave Desert", "relevance": 0.538264, "dbpedia_resource": "http://dbpedia.org/resource/Mojave_Desert" }, { "text": "Agua Caliente", "relevance": 0.532442, "dbpedia_resource": "http://dbpedia.org/resource/Agua_Caliente" }, { "text": "Canyon", "relevance": 0.532079, "dbpedia_resource": "http://dbpedia.org/resource/Canyon" }, { "text": "Bloom", "relevance": 0.530359, "dbpedia_resource": "http://dbpedia.org/resource/Bloom" }, { "text": "In Bloom", "relevance": 0.530062, "dbpedia_resource": "http://dbpedia.org/resource/In_Bloom" }, { "text": "San Diego County, California", "relevance": 0.527255, "dbpedia_resource": "http://dbpedia.org/resource/San_Diego_County,_California" }, { "text": "Club Blooming", "relevance": 0.515959, "dbpedia_resource": "http://dbpedia.org/resource/Club_Blooming" }, { "text": "Skeleton", "relevance": 0.506855, "dbpedia_resource": "http://dbpedia.org/resource/Skeleton" }, { "text": "Rain shadow", "relevance": 0.504644, "dbpedia_resource": "http://dbpedia.org/resource/Rain_shadow" }, { "text": "Southern California", "relevance": 0.499531, "dbpedia_resource": "http://dbpedia.org/resource/Southern_California" }, { "text": "Geomorphology", "relevance": 0.495471, "dbpedia_resource": "http://dbpedia.org/resource/Geomorphology" }, { "text": "Human skeleton", "relevance": 0.483397, "dbpedia_resource": "http://dbpedia.org/resource/Human_skeleton" }, { "text": "Christopher Nolan", "relevance": 0.481544, "dbpedia_resource": "http://dbpedia.org/resource/Christopher_Nolan" } ], "entities": [ { "type": "GeographicFeature", "relevance": 0.893131, "count": 4, "text": "Anza-Borrego Desert", "sentiment": { "score": -0.493608 } }, { "type": "Person", "relevance": 0.821149, "count": 7, "text": "Myrtle Botts", "sentiment": { "score": -0.0582496 } }, { "type": "GeographicFeature", "relevance": 0.544915, "count": 1, "text": "Colorado Desert", "sentiment": { "score": -0.246817 } }, { "type": "GeographicFeature", "relevance": 0.339633, "count": 1, "text": "Moonlight Canyon", "sentiment": { "score": 0.313185 } }, { "type": "GeographicFeature", "relevance": 0.327943, "count": 1, "text": "Canebrake Canyon", "sentiment": { "score": 0.0 } }, { "type": "GeographicFeature", "relevance": 0.293025, "count": 1, "text": "Agua Caliente", "sentiment": { "score": 0.0 } }, { "type": "Location", "relevance": 0.290514, "count": 1, "text": "California", "sentiment": { "score": -0.246817 }, "disambiguation": { "subtype": [ "StateOrCounty" ] } }, { "type": "Company", "relevance": 0.275561, "count": 1, "text": "Newsweek", "sentiment": { "score": -0.684874 } }, { "type": "GeographicFeature", "relevance": 0.271105, "count": 1, "text": "Huntington Beach", "sentiment": { "score": 0.0 } }, { "type": "Location", "relevance": 0.265551, "count": 1, "text": "Southern California", "sentiment": { "score": 0.0 }, "disambiguation": { "subtype": [ "Region" ] } }, { "type": "GeographicFeature", "relevance": 0.260027, "count": 1, "text": "Indian Gorge", "sentiment": { "score": 0.0 } }, { "type": "Location", "relevance": 0.247786, "count": 1, "text": "Mexico", "sentiment": { "score": -0.519773 }, "disambiguation": { "subtype": [ "Country" ] } }, { "type": "Quantity", "relevance": 0.247786, "count": 1, "text": "60 years", "sentiment": { "score": 0.0 } }, { "type": "Quantity", "relevance": 0.247786, "count": 1, "text": "$1.25", "sentiment": { "score": 0.0 } } ], "keywords": [ { "relevance": 0.967572, "text": "librarian Myrtle Botts", "sentiment": { "score": -0.500747 } }, { "relevance": 0.930381, "text": "rugged Colorado Desert", "sentiment": { "score": -0.246817 } }, { "relevance": 0.737723, "text": "desert ship", "sentiment": { "score": -0.438901 } }, { "relevance": 0.685002, "text": "treasure ship", "sentiment": { "score": 0.0 } }, { "relevance": 0.68418, "text": "Anza-Borrego Desert", "sentiment": { "score": 0.0 } }, { "relevance": 0.668971, "text": "riotous invasion", "sentiment": { "score": 0.559545 } }, { "relevance": 0.668012, "text": "Viking ship", "sentiment": { "score": 0.0 } }, { "relevance": 0.663657, "text": "Spanish explorers", "sentiment": { "score": 0.0 } }, { "relevance": 0.663597, "text": "Agua Caliente", "sentiment": { "score": 0.0 } }, { "relevance": 0.659729, "text": "Moonlight Canyon", "sentiment": { "score": 0.313185 } }, { "relevance": 0.656936, "text": "washed-out yellows", "sentiment": { "score": 0.559545 } }, { "relevance": 0.652881, "text": "Hellhole Canyon", "sentiment": { "score": 0.0 } }, { "relevance": 0.647218, "text": "prehistoric beast", "sentiment": { "score": -0.416672 } }, { "relevance": 0.646764, "text": "Viking craft", "sentiment": { "score": 0.0 } }, { "relevance": 0.645473, "text": "Canebrake Canyon", "sentiment": { "score": 0.0 } }, { "relevance": 0.643185, "text": "desert flora", "sentiment": { "score": 0.0 } }, { "relevance": 0.639555, "text": "wooden remains", "sentiment": { "score": -0.416672 } }, { "relevance": 0.638686, "text": "rugged hike", "sentiment": { "score": 0.0 } }, { "relevance": 0.633962, "text": "Indian Gorge", "sentiment": { "score": 0.0 } }, { "relevance": 0.63114, "text": "time Myrtle", "sentiment": { "score": 0.384248 } }, { "relevance": 0.630607, "text": "place names", "sentiment": { "score": -0.482936 } }, { "relevance": 0.629506, "text": "tiny settlement", "sentiment": { "score": 0.0 } }, { "relevance": 0.629367, "text": "serpentine figure", "sentiment": { "score": 0.0 } }, { "relevance": 0.624052, "text": "new species", "sentiment": { "score": 0.0 } }, { "relevance": 0.622815, "text": "evening primrose", "sentiment": { "score": 0.0 } }, { "relevance": 0.621913, "text": "magnitude earthquake", "sentiment": { "score": 0.0 } }, { "relevance": 0.621453, "text": "couple’s camp", "sentiment": { "score": 0.0 } }, { "relevance": 0.61162, "text": "rocky face", "sentiment": { "score": 0.0 } }, { "relevance": 0.609742, "text": "Huntington Beach", "sentiment": { "score": 0.0 } }, { "relevance": 0.606665, "text": "Southern California", "sentiment": { "score": 0.0 } }, { "relevance": 0.547947, "text": "husband", "sentiment": { "score": 0.384248 } }, { "relevance": 0.505634, "text": "wildflowers", "sentiment": { "score": 0.559545 } }, { "relevance": 0.465332, "text": "story", "sentiment": { "score": -0.535203 } }, { "relevance": 0.46232, "text": "flanks", "sentiment": { "score": 0.0 } }, { "relevance": 0.460738, "text": "strangeness", "sentiment": { "score": -0.482936 } }, { "relevance": 0.459263, "text": "prow", "sentiment": { "score": 0.0 } }, { "relevance": 0.455413, "text": "Newsweek", "sentiment": { "score": -0.684874 } }, { "relevance": 0.453819, "text": "poppies", "sentiment": { "score": 0.0 } }, { "relevance": 0.453402, "text": "hallmarks", "sentiment": { "score": 0.0 } }, { "relevance": 0.45094, "text": "severity", "sentiment": { "score": -0.482936 } }, { "relevance": 0.449785, "text": "grays", "sentiment": { "score": 0.559545 } }, { "relevance": 0.449258, "text": "prospector", "sentiment": { "score": 0.0 } }, { "relevance": 0.448128, "text": "skeleton", "sentiment": { "score": -0.416672 } }, { "relevance": 0.445809, "text": "fact", "sentiment": { "score": 0.0 } }, { "relevance": 0.442428, "text": "bloom", "sentiment": { "score": -0.302342 } }, { "relevance": 0.439823, "text": "Mexico", "sentiment": { "score": -0.519773 } }, { "relevance": 0.438978, "text": "shields", "sentiment": { "score": 0.0 } }, { "relevance": 0.438932, "text": "Bottses", "sentiment": { "score": 0.0 } }, { "relevance": 0.438647, "text": "legend", "sentiment": { "score": 0.0 } }, { "relevance": 0.438395, "text": "land", "sentiment": { "score": -0.482936 } } ], "categories": [ { "label": "/home and garden", "score": 0.480819 }, { "label": "/travel/tourist destinations/mexico and central america", "score": 0.464129 }, { "label": "/science/geology/seismology/earthquakes", "score": 0.360872 } ], "emotion": { "document": { "emotion": { "anger": 0.124247, "disgust": 0.60208, "fear": 0.086856, "joy": 0.494162, "sadness": 0.489829 } } }, "sentiment": { "document": { "score": -0.713717 } } }';

    //echo $resultado;
    //***********************************************************************************************************

    $watson = json_decode($resultadoCURL, true);

?>
<script type="text/javascript">
window.onload = function () {

  var chart_sentimentos = new CanvasJS.Chart("chart_sentimentos", {
      theme: "light2",
      animationEnabled: true,
      title:{
  			text: "Taxa de Sentimento (%)"
  		},
  		data: [
  		{
  			// Change type to "doughnut", "line", "splineArea", etc.
  			type: "column",
        showInLegend: true,
        legendMarkerType: "none",
        legendText: 'O sentimento do texto varia de -100 até +100. Inclusive, sentimento que seja menor que 0 é negativo, igual a 0 é neutro e maior que 0 é positivo',
  			dataPoints: [
  				{ label: "Sentimento",  y: <?php echo $watson['sentiment']['document']['score'] * 100?>  },
  			]
  		}
  		]
  	});

  var chart_emocoes = new CanvasJS.Chart("chart_emocoes", {
    theme: "light2",
    animationEnabled: true,
    title:{
      text: "Taxa de Emoções (%)"
    },
    data: [
		{
			// Change type to "doughnut", "line", "splineArea", etc.
			type: "column",
      showInLegend: true,
      legendMarkerType: "none",
      legendText: 'As emoções presentes no texto variam de 0 até 100. Inclusive, quanto mais próximo de 0 é menor a incidência dela no texto, outrora quanto mais perto de 100 é mais ocorrente.',
			dataPoints: [
				{ label: "Alegria",  y: <?php echo $watson['emotion']['document']['emotion']['joy'] * 100;?>  },
				{ label: "Desgosto",  y: <?php echo $watson['emotion']['document']['emotion']['disgust'] * 100;?>  },
        { label: "Medo",  y: <?php echo $watson['emotion']['document']['emotion']['fear'] * 100;?>  },
        { label: "Tristeza",  y: <?php echo $watson['emotion']['document']['emotion']['sadness'] * 100;?>  },
        { label: "Raiva",  y: <?php echo $watson['emotion']['document']['emotion']['anger'] * 100;?>  }
			]
		}
		]
	});




  chart_emocoes.render();
  chart_sentimentos.render();
}
</script>

<div class="row">
  <div class="col-lg-12">
    <h1 class="text-primary">Análise Cognitiva</h1>
  </div><!--end .col -->
  <div class="col-lg-6 col-md-8">
    <article class="margin-bottom-xxl">
      <p class="lead">
        Seção responsável por exibir uma análise cognitiva do conteúdo filtrado, com: Sentimento, Emoções, Categorias, Palavras-chave, Entidades e Conceitos.
      </p>
    </article>
  </div><!--end .col -->
</div>
  <!--//***********************GRÁFICO SENTIMENTOS***********************-->
  <div class="col-md-6">
    <div class="card card-bordered style-primary">
      <div class="row">
          <div class="card-head">
            <header><i class="fa fa-bar-chart"></i> Percentual de sentimento presente no texto</header>
          </div><!--end .card-head -->
          <div id="chart_sentimentos" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
      </div><!--end .row -->
    </div><!--end .card -->
  </div><!--end .col -->

  <!--//***********************GRÁFICO EMOÇÕES***********************-->
  <div class="col-md-6">
    <div class="card card-bordered style-primary">
      <div class="row">
          <div class="card-head">
            <header><i class="fa fa-bar-chart"></i> Relação percentual entre as emoções presentes no conteúdo filtrado</header>
          </div><!--end .card-head -->
          <div id="chart_emocoes" style="height: 350px; width: 90%; margin-left:30px; margin-bottom:20px"></div>
      </div><!--end .row -->
    </div><!--end .card -->
  </div><!--end .col -->

  <!--//***********************TABELA DE CATEGORIAS***********************-->
		<div class="col-lg-12">
			<h3>CATEGORIAS</h3>
		</div><!--end .col -->
		<div class="col-lg-3 col-md-4">
			<article class="margin-bottom-xxl">
				<p>
					Nesta tabela consta as categorias presentes no texto, sendo que, a pontuação da categorização varia de 0 a 100. Onde, quanto mais próximo de 0 não há uma confiança na categorização e mais perto de 100 existe uma credibilidade.
				</p>
			</article>
		</div><!--end .col -->
		<div class="col-lg-offset-1 col-md-8">
			<div class="card style-primary">
				<div class="card-body">
					<table class="table table-hover no-margin">
						<thead>
							<tr>
								<th>#</th>
								<th>Categoria</th>
								<th>Pontuação</th>
							</tr>
						</thead>
						<tbody>
            <?php
              for($i = 0; $i < sizeof($watson['categories']);$i++){
              ?>
              <tr>
                  <td><?php echo $i ?></td>
                  <td><?php echo $watson['categories'][$i]['label'] ?></td>
                  <td><?php echo $watson['categories'][$i]['score'] * 100 ?></td>
              </tr>
              <?php
            }
            ?>
						</tbody>
					</table>
				</div><!--end .card-body -->
			</div><!--end .card -->
		</div><!--end .col -->

    <!--//***********************TABELA DE CONCEITOS***********************-->
      <div class="col-lg-12">
        <h3>CONCEITOS</h3>
      </div><!--end .col -->
      <div class="col-lg-3 col-md-4">
        <article class="margin-bottom-xxl">
          <p>
            Nesta tabela consta os conceitos relacionados ao texto, sendo que, a relevância varia de 0 até 100. Onde, quanto mais próximo de 0 menos relevante e mais perto de 100 mais relevante. Além disso, consta na tabela links do dbpedia para um conhecimento mais aprofundado acerca do conceitos exibidos.
          </p>
        </article>
      </div><!--end .col -->
      <div class="col-lg-offset-1 col-md-8">
        <div class="card style-primary">
          <div class="card-body">
            <table class="table table-hover no-margin">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Conceito</th>
                  <th>Pontuação</th>
                  <th>DBPedia</th>
                </tr>
              </thead>
              <tbody>
              <?php
                for($i = 0; $i < sizeof($watson['concepts']);$i++){
                ?>
                <tr>
                    <td><?php echo $i ?></td>
                    <td><?php echo $watson['concepts'][$i]['text'] ?></td>
                    <td><?php echo $watson['concepts'][$i]['relevance'] * 100?></td>
                    <td><?php echo $watson['concepts'][$i]['dbpedia_resource'] ?></td>
                </tr>
                <?php
              }
              ?>
              </tbody>
            </table>
          </div><!--end .card-body -->
        </div><!--end .card -->
      </div><!--end .col -->

      <!--//***********************TABELA DE ENTIDADES***********************-->
        <div class="col-lg-12">
          <h3>ENTIDADES</h3>
        </div><!--end .col -->
        <div class="col-lg-3 col-md-4">
          <article class="margin-bottom-xxl">
            <p>
              Nesta tabela consta as entidades relacionadas ao texto, sendo que, a relevância varia de 0 até 100. Onde, quanto mais próximo de 0 menos relevante e mais perto de 100 mais relevante. Além disso, consta na tabela quais são os tipos das entidades.
            </p>
          </article>
        </div><!--end .col -->
        <div class="col-lg-offset-1 col-md-8">
          <div class="card style-primary">
            <div class="card-body">
              <table class="table table-hover no-margin">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Entidade</th>
                    <th>Tipo</th>
                    <th>Relevância</th>
                  </tr>
                </thead>
                <tbody>
                <?php
                  for($i = 0; $i < sizeof($watson['entities']);$i++){
                  ?>
                  <tr>
                      <td><?php echo $i ?></td>
                      <td><?php echo $watson['entities'][$i]['text'] ?></td>
                      <td><?php echo $watson['entities'][$i]['type'] ?></td>
                      <td><?php echo $watson['entities'][$i]['relevance'] * 100?></td>
                  </tr>
                  <?php
                }
                ?>
                </tbody>
              </table>
            </div><!--end .card-body -->
          </div><!--end .card -->
        </div><!--end .col -->

        <!--//***********************TABELA DE PALAVRAS-CHAVE***********************-->
          <div class="col-lg-12">
            <h3>PALAVRAS-CHAVE</h3>
          </div><!--end .col -->
          <div class="col-lg-3 col-md-4">
            <article class="margin-bottom-xxl">
              <p>
                Nesta tabela consta as palavras-chave relacionadas ao texto, sendo que, a relevância varia de 0 até 100. Onde, quanto mais próximo de 0 menos relevante e mais perto de 100 mais relevante.
              </p>
            </article>
          </div><!--end .col -->
          <div class="col-lg-offset-1 col-md-8">
            <div class="card style-primary">
              <div class="card-body">
                <table class="table table-hover no-margin">
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>Palavra-chave</th>
                      <th>Relevância</th>
                    </tr>
                  </thead>
                  <tbody>
                  <?php
                    for($i = 0; $i < sizeof($watson['keywords']);$i++){
                    ?>
                    <tr>
                        <td><?php echo $i ?></td>
                        <td><?php echo $watson['keywords'][$i]['text'] ?></td>
                        <td><?php echo $watson['keywords'][$i]['relevance'] * 100?></td>
                    </tr>
                    <?php
                  }
                  ?>
                  </tbody>
                </table>
              </div><!--end .card-body -->
            </div><!--end .card -->
          </div><!--end .col -->


<?php


}else{
  ?>
  <script>
  swal("Erro!", "Informe todos os parâmetros necessários!", "error")
  .then((value) => {
    window.location.replace("http://localhost:8088/platsinn/materialadmin/html/dashboards/filtro_fuseki.php");
  });
  </script>
  <?php
}



include 'footer.html';

?>
