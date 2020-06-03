/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson;

import extratormdadoswatson.Extrator.Busca;
import extratormdadoswatson.InstanciaDinamica.ExtracaoCD;
import extratormdadoswatson.InstanciaDinamica.TipoDinamicoDAO;
import extratormdadoswatson.InstanciaDinamica.TipoDinamico;
import extratormdadoswatson.InstanciaEstatica.InstanciaEstaticaCD;
import extratormdadoswatson.InstanciaEstatica.TipoEstaticoDAO;
import extratormdadoswatson.WATSON.LanguageTranslateIBMWatson;
import extratormdadoswatson.WATSON.NLUnderstandIBMWatson;

/**
 *
 * @author thiag
 */
public class ExtratorMDadosWatson {  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       
        // TODO code application logic here
        //new Busca("http://www.bv.fapesp.br/pt/eventos/?page=",2,"noticia");
        //new Busca("http://www.fapesp.br/secao/Not%EDcias?ct=20&hl=1&ord=id&p=",1,"noticia");
        //new Busca("http://www.fapesp.br/secao/Not%EDcias?ct=20&hl=1&ord=id&p=",1,"noticia");
        //new Busca("http://www.inovamarilia.com.br/");
        //new Busca("http://www.desenvolvimento.sp.gov.br/NOTICIAS/",1,"noticia");
        //new Busca("http://www.inovamarilia.com.br/category/noticias/",1,"noticia");
        //new Busca("http://www.inovamarilia.com.br/category/noticias/imprensa/",1,"noticia");
        //new Busca("http://www.baita.ac/category/news/",1,"noticia");
        
        //http://umbco23.com.br/blog/
        
        //http://www.investe.sp.gov.br/eventos/nossos-eventos/
        //http://ciem.univem.edu.br/agentes-de-fomento/

        
        /*Busca b = new Busca("http://ciem.univem.edu.br/eventos/",1,"evento");
        ExtracaoCD ext = new ExtracaoCD();
        ext.cadastrarExtracao(b.getR());
        */
        
        //new Busca("http://cnpq.br/noticias-cnpq?p_p_id=101_INSTANCE_NAKQJCMco6ML&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_pos=2&p_p_col_count=3&_101_INSTANCE_NAKQJCMco6ML_delta=10&_101_INSTANCE_NAKQJCMco6ML_keywords=&_101_INSTANCE_NAKQJCMco6ML_advancedSearch=false&_101_INSTANCE_NAKQJCMco6ML_andOperator=true&p_r_p_564233524_resetCur=false&_101_INSTANCE_NAKQJCMco6ML_cur=",1);
        
        //***********************ONDE BUSCAR*********************
        //http://www.ial.sp.gov.br/ial/perfil/homepage/noticias/
            //http://www.unicamp.br/unicamp/noticias?page=4
        
        //***********************TESTE INSTÂNCIA ESTÁTICA***********************
        //InstanciaEstaticaCD inst = new InstanciaEstaticaCD();
        //inst.criarInstanciaEstatica("Inova Marilia","http://www.inovamarilia.com.br",1,1);
        //inst.criarInstanciaEstatica("Baita","http://www.baita.ac",2,2);
        //inst.deletarInstanciaEstatica("E1");
        
        //***********************TESTE EXCLUSÃO DE DOMÍNIO***********************
        /*ExtracaoCD ex = new ExtracaoCD();
        ex.deletarExtracao("www.umbco23.com.br");
        */
        //http://umbco23.com.br/blog/
        //***********************TIPO ESTATICO***********************
        //TipoEstaticoDAO tipo = new TipoEstaticoDAO();
        
        //List<TipoEstatico> te = tdao.ListaTipoEstatico();
        
        //for(int i=0; i< te.size(); i++){
        //    System.out.println("Nome:"+ te.get(i).getNome());
        //}
        
        //************************WATSON****************************
        /*String texto = "he Botucatu Technological park is designed to be a differentiated environment, promote innovation by interaction between the bases of knowledge (universities, centres and institutes of Research and development) and the productive base (enterprises). It has the support and induction of the municipality of Botucatu and is characterized as an instrument of aggregating partners of all segments of the society that can contribute decisively to sustainable regional development. It operates primarily for the dynamics of the knowledge applied, the diffusion of technology and the generation of business, reduce socioeconomic inequalities, being a propeller lever in generating employment, labor and income. Botucatu has a great tradition in research in medical and biological sciences. The city of Botucatu houses five units of UNESP: Faculty of Medicine of Botucatu (FMB), Hospital of the clinics of Botucatu, Faculty of Veterinary Medicine and Zootechnics (FMVZ), Faculty of Sciences agronomic (FCA) and Institute of Biosciences (IB). The presence of a unit of Fatec and a technical school (Etec) of Centro Paula Souza aggregates diversity of teaching and decency in the municipality. Besides the academic inclination, the region's productive sector has an expressive technological leadership that counts with the concentration of micro, small, medium and large enterprises, such as Embraer-Neiva, Eucatex, Duratex, Caio Induscar and Grupo Centroflora.";
        NLUnderstandIBMWatson nlu = new NLUnderstandIBMWatson(texto);
        System.out.println(nlu.getAnaliseCognitiva());
        */
        
        NLUnderstandIBMWatson nlu = new NLUnderstandIBMWatson("Platform support for Innovation that has the ability to extract, sort and retrieve information semantics and cognitive.");
        String texto_analisado = nlu.getAnaliseCognitiva();
        System.out.println("Texto Analisado: "+texto_analisado);
        
        
        
        /*LanguageTranslateIBMWatson lang = new LanguageTranslateIBMWatson();
        String resposta = lang.traducao("Plataforma de apoio à Inovação que tem a capacidade de extrair, classificar e recuperar informações semânticas e cognitivas");
        System.out.println("Texto Traduzido: "+resposta);
        */
        
        //***********************lINK EXCLUÍDO***********************
        /*LinkExcluido  lk = new LinkExcluido();
        lk.setCodDinamico("E1");
        lk.setLinkIndividual("www.inovamarilia.com.br");
        LinkExcluidoDAO lkDAO = new LinkExcluidoDAO();
        lkDAO.cadastrarLinkExcluido(lk);
        LinkExcluido l = lkDAO.obterLinkExcluido("E1");
        System.out.println("COD: "+l.getCodDinamico());
        lkDAO.deletarLinkExcluido("E1");*/
        
        //***********************TESTE EXTRAÇÃO DINÂMICA***********************
        
        
        //***********************TESTE TIPO DINÂMICO***********************
        /*TipoDinamicoDAO tdDao = new TipoDinamicoDAO();
        TipoDinamico td = new TipoDinamico();
        td.setNome("edital");
        td.setOnt_class_uri("http://www.inovamarilia.com.br/ontologia#Edital");
        td.setOnt_descricao_uri("http://www.inovamarilia.com.br/ontologia#descricaoEdital");
        td.setOnt_dominio_uri("http://www.inovamarilia.com.br/ontologia#dominioEdital");
        td.setOnt_link_uri("http://www.inovamarilia.com.br/ontologia#linkEdital");
        td.setOnt_titulo_uri("http://www.inovamarilia.com.br/ontologia#tituloEdital");
        tdDao.cadastrarTipoDinamico(td);
        */
    }  
}
