/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.InstanciaDinamica;

import extratormdadoswatson.Extrator.MDado;
import extratormdadoswatson.Extrator.ResultadoBusca;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thiag
 */
public class ExtracaoCD {
    
    
    public ExtracaoCD(){
        
    }
    
    //******************************CADASTRAR NO BANCO DE DADOS E NO FUSEKI*****************************
    public boolean cadastrarExtracao(ResultadoBusca r) throws Exception{
                  
        int contadorIteracao_inicial;
        int contadorIteracao;
        int id_inserido;
        int id_tipo_inserido;
        boolean validate_cadastrar = true;
        boolean validate_atualizar = false;
        boolean validate_cadastrar_dinamico = true;
        ArrayList<String> sites_persistidos = new ArrayList<String>();
        
        
        String tipo = r.getTipoExtracao();
        ArrayList<MDado> mRdf = r.getmRdf();
        String URL = r.getURL();
        
        //**************************CRIAR JSON/HASH*************************
        JsonConteudo jCont = new JsonConteudo(mRdf);
        HashConteudo hash = new HashConteudo(jCont.getJson());
        String hashConteudo = hash.getHash();
        
        ExtracaoDAO exDAO = new ExtracaoDAO();
        Extracao exultima = exDAO.obterUltimaExtracao();
        
        //**************************VERIFICAR EXTRAÇÃO ANTERIOR*************************
        if(exultima != null){
           contadorIteracao = exultima.getLimiteSuperior() + 1;
           contadorIteracao_inicial = contadorIteracao;
        }else{
           contadorIteracao = 1; 
           contadorIteracao_inicial = contadorIteracao;
        }
        List<Extracao> listExtracao = exDAO.listarExtracao();
        
        //**************VERIFICAÇÃO DE MODIFICAÇÃO DE PÁGINA PARA EXTRAÇÃO**************
        for(int i = 0; i < listExtracao.size(); i++){
            if(listExtracao.get(i).getLinkExtracao().equalsIgnoreCase(URL)){
                if(!hashConteudo.equalsIgnoreCase(listExtracao.get(i).getHashExtracao())){
                   deletarExtracao(URL);
                }else{
                    System.out.println("INFORMAÇÕES JÁ CAPTADAS! AGUARDE A PRÓXIMA EXTRAÇÃO.");
                    validate_cadastrar = false;
                    break;
                }
            }
        }
        
        
        
        if(validate_cadastrar == true){
            //******************************OBJETO EXTRAÇÃO******************************
            Extracao ex = new Extracao();
            TipoDinamico td = new TipoDinamico();
            TipoDinamicoDAO tdDAO = new TipoDinamicoDAO();
            td = tdDAO.obterTipoDinamico(tipo);

            ex.setHashExtracao(hashConteudo);
            ex.setHorario(getData());
            ex.setLinkExtracao(URL);
            ex.setLinkDominio(getDominio(URL));
            ex.setFk_id_tipo(td.getId());
            ex.setLimiteInferior(0);
            ex.setLimiteSuperior(0);
            
            exDAO.cadastrarExtracao(ex); 
            
            
            //*******OBTER O ID QUE FOI INSERIDO*******
            Extracao exUlt = exDAO.obterUltimaExtracao();
            id_inserido = exUlt.getId();
            id_tipo_inserido = exUlt.getFk_id_tipo();
            //******************************INTANCIAR DINAMICAMENTE******************************

            MDado auxMRDF = mRdf.get(0);
            ArrayList<String> arrayConteudo= new ArrayList();
            ArrayList<MDado> arrayMetadados = new ArrayList();
            String guardarConteudo = "";
            String titulo = "";

            //***************************************************************************
            
            /*for(int i = 0; i < mRdf.size(); i++){
                System.out.println("LINK PARA PERSISTÊNCIA - METADADO: "+mRdf.get(i).getMetadado().getBUri());
                System.out.println("LINK PARA PERSISTÊNCIA - DADO: "+mRdf.get(i).getDado().getBUri());
            }*/
            
            for(int i = 0; i < mRdf.size(); i++){
                 MDado mrdfIterativo = mRdf.get(i);
                  //************comparo se o próximo tem o anterior para retirar os #respond*********
                    //if(!mrdfIterativo.getMetadado().getBUri().contains("#")){

                        if(!auxMRDF.getMetadado().getBUri().equalsIgnoreCase(mrdfIterativo.getMetadado().getBUri())){
                            
                            //******************************SE O LINK JÁ FOI PERSISTIDO ou TEM #**********************************
                            boolean validar_duplicata = false;  
                            
                                for(int j = 0; j < sites_persistidos.size(); j++){
                                    if(sites_persistidos.get(j).equalsIgnoreCase(mRdf.get(i).getMetadado().getBUri())){
                                        validar_duplicata = true;
                                        break;
                                    }else if(mRdf.get(i).getMetadado().getBUri().contains("#respond")){
                                        String nova = mRdf.get(i).getMetadado().getBUri().replace("#respond", "");
                                        if(sites_persistidos.get(j).equalsIgnoreCase(nova)){
                                            validar_duplicata = true;
                                            break;
                                        }
                                    }
                                }
                                
                            if(validar_duplicata == false){
                                //System.out.println("LINK PARA PERSISTÊNCIA - METADADO: "+mRdf.get(i).getMetadado().getBUri());
                                //******************************CONTEÚDO**********************************
                                guardarConteudo = "";
                                for(String x: arrayConteudo){
                                    //**********************RETIRAR CONTEÚDO QUE SEJA LINK*****************************
                                     if(!x.equalsIgnoreCase(auxMRDF.getMetadado().getBUri())){
                                        guardarConteudo+=x;

                                     }
                                }

                                //*********************************TÍTULO****************************
                                if(getTituloMetadados(arrayMetadados).equalsIgnoreCase("")){
                                    titulo = getTituloMetadadosDescricao(arrayMetadados);
                                }else{
                                    titulo = getTituloMetadados(arrayMetadados);
                                }


                                /*System.out.println("*****************EXTRACAOANTES***********************");
                                System.out.println("LINK: "+auxMRDF.getMetadado().getBUri());
                                System.out.println("DOMINIO: "+getDominio(URL));
                                System.out.println("TITULO: "+titulo);
                                System.out.println("CONTEUDO: "+guardarConteudo);
                                System.out.println("ID_INSERIDO: "+id_inserido);
                                System.out.println("FK_ID_TIPO: "+id_tipo_inserido);
                                System.out.println("FK_ID_TIPO_TDAO: "+td.getId());
                                System.out.println("*******************************************************");
                                */


                                //*********************INSTANCIAS DINÂMICAS************************
                                if(titulo !=null && !guardarConteudo.isEmpty()){

                                    //**************************CRIAR HASH INDIVIDUAL*************************
                                    HashConteudo hashcont = new HashConteudo(guardarConteudo);
                                    String hashconteudo = hashcont.getHash(); 

                                    //************************************************************************
                                    LinkExtracaoDAO linkDAO = new LinkExtracaoDAO();
                                    LinkExtracaoFUSEKI linkFU = new LinkExtracaoFUSEKI();
                                    LinkExtracao link = new LinkExtracao();
                                    link.setCodDinamico("D"+contadorIteracao);
                                    link.setFk_id_extracao(id_inserido);
                                    link.setLinkIndividual(auxMRDF.getMetadado().getBUri());
                                    link.setHashExtracao(hashconteudo);

                                    /*System.out.println("*****************EXTRACAOCD***********************");
                                    System.out.println("LINK: "+auxMRDF.getMetadado().getBUri());
                                    System.out.println("DOMINIO: "+getDominio(URL));
                                    System.out.println("TITULO: "+titulo);
                                    System.out.println("CONTEUDO: "+guardarConteudo);
                                    System.out.println("FK_ID_TIPO: "+id_tipo_inserido);
                                    System.out.println("*******************************************************");
                                   */
                                    boolean validate1 = linkFU.cadastrarInstanciaDinamicaFuseki(link, titulo, getDominio(URL), guardarConteudo,exUlt.getFk_id_tipo());

                                    if(validate1 == true){

                                        boolean validate2 = linkDAO.cadastrarLinkExtracao(link);

                                        if(validate2 == true){
                                            contadorIteracao++;
                                            
                                            //**********adiciono na lista de sites persistidos**********
                                            sites_persistidos.add(auxMRDF.getMetadado().getBUri());

                                        }else{
                                            validate_cadastrar_dinamico = false;
                                        }
                                    }else{
                                        validate_cadastrar_dinamico = false;
                                    }

                                }
                                auxMRDF = mrdfIterativo;
                                arrayMetadados.clear();
                                arrayConteudo.clear();
                            }

                        }else{
                             arrayConteudo.add(mrdfIterativo.getDado().getConteudo());
                             arrayMetadados.add(mrdfIterativo);
                        }

                    //}

            }
            //***************************INSTANCIANDO EXTRACAODAO***************************
            ExtracaoDAO exultDAO = new ExtracaoDAO();
            validate_atualizar = exultDAO.atualizarExtracao(contadorIteracao_inicial, contadorIteracao-1, id_inserido);

            if(validate_atualizar){
                System.out.println("EXTRAÇÃO REALIZADA COM SUCESSO!");
            }
        }
        System.out.println("validate_atualizar: "+validate_atualizar);
        System.out.println("validate_cadastrar: "+validate_cadastrar);
        System.out.println("validate_cadastrar_dinamico: "+validate_cadastrar_dinamico);
        
        
        if(validate_atualizar == false || validate_cadastrar == false || validate_cadastrar_dinamico == false){
            validate_cadastrar = false;
        }
        
        return validate_cadastrar;
    }
    
    //******************************DELETAR NO BANCO DE DADOS E NO FUSEKI*****************************
    public boolean deletarExtracao(String extracao) throws Exception{
        
        ExtracaoDAO e = new ExtracaoDAO();
        Extracao ex = new Extracao();
        LinkExtracaoDAO lk = new LinkExtracaoDAO();
        LinkExtracaoFUSEKI lkFUSEKI = new LinkExtracaoFUSEKI();
        
        ex = e.obterExtracao(extracao);
        int id_extracao = ex.getId();
        List<LinkExtracao> listaLink =  lk.ListaLinkExtracao();
        
        for(int i = 0; i < listaLink.size(); i++){
            if(listaLink.get(i).getFk_id_extracao() == id_extracao){
                lk.deletarLinkExtracao(listaLink.get(i).getId_linkExtracao());
                lkFUSEKI.deletarInstanciaDinamica(listaLink.get(i).getCodDinamico());
            }
        }
        
        boolean val = e.deletarExtracao(extracao);
        
        if(val == true){
            System.out.println("Extracoes excluidas com sucesso!");
        }
        return val;
    }
    
    
    
    //******************************OBTENÇÃO DA DATA ATUAL*****************************
    public String getData(){
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format( new Date() );   
    }
    
    //******************************OBETENÇÃO DO DOMÍNIO*****************************
    public String getDominio(String link){
        String domain = "";
        if(link.contains("http://")){
            String nova = link.replace("http://", "");
            char[] chars = nova.toCharArray();
            for (int i = 0, n = chars.length; i < n; i++) {
                if(chars[i] == '/'){
                    if(chars[i] == '/'){
                        break;
                    }
                }
                domain = domain + chars[i];
            }
        }else if(link.contains("https://")){
            String nova = link.replace("https://", "");
            char[] chars = nova.toCharArray();
            for (int i = 0, n = chars.length; i < n; i++) {
                if(chars[i] == '/'){
                    break;
                }
                domain = domain + chars[i];
            }
        }else if(link.contains("www.")){
            String nova = link.replace("www.", "");
            char[] chars = nova.toCharArray();
            for (int i = 0, n = chars.length; i < n; i++) {
                if(chars[i] == '/'){
                    break;
                }
                domain = domain + chars[i];
            }
        }else{
            System.out.println("Invalidez do link: "+link);
        }
        //****************************************************************************** 
        
        return domain;
    }
    
    //****************************OBTER TÍTULO METADADO - CONTEÚDO*****************************
    //************Obtenho o metadado que mais se repete que descreve uma notícia**********
    // tipo = metadado, descrição = ... && tipo = dado, descrição = ...
    public String getTituloMetadadosDescricao(ArrayList<MDado> mRdf){
        
        Map<String, Integer> mapaFreq = new HashMap<>();
        
        // Cria o mapa de Frequências
        for (MDado x : mRdf) {
            if (!mapaFreq.containsKey(x.getMetadado().getConteudo())) {
                mapaFreq.put(x.getMetadado().getConteudo(), 1);
            } else {
                mapaFreq.put(x.getMetadado().getConteudo(), 1 + mapaFreq.get(x.getMetadado().getConteudo()));
            }
        }
        
        String[] palavrasMaisFrequentes = new String[3];
        int[] freqPalavras = new int[3];
        //Percorre todos os valores do mapa
        for (Map.Entry<String, Integer> entrada : mapaFreq.entrySet()) {
            //Se achar algo mais frequente que a primeira posição
            if (entrada.getValue() >= freqPalavras[0]) {
                freqPalavras[0] = entrada.getValue();
                palavrasMaisFrequentes[0] = entrada.getKey();

            } else {
                if (entrada.getValue() >= freqPalavras[1]) {
                    freqPalavras[1] = entrada.getValue();
                    palavrasMaisFrequentes[1] = entrada.getKey();
                } else if (entrada.getValue() >= freqPalavras[2]) {
                    freqPalavras[2] = entrada.getValue();
                    palavrasMaisFrequentes[2] = entrada.getKey();
                }
            } 
        }
        /*for (int i = 0; i < freqPalavras.length; i++) {
            System.out.println(i + 1 + " palavra: " + palavrasMaisFrequentes[i]
                    + " \nFrequência: " + freqPalavras[i]
                    + "\n------------------------\n");
        }*/
        
        return palavrasMaisFrequentes[0];
    }
    
    
     //******************************OBTER TÍTULO METADADO******************************
    //Vasculho a lista de metadados se tem alguma estrutura de título, sendo que não posso esquecer da ordem de prioridade
    public String getTituloMetadados(ArrayList<MDado> mRdf){
        String tit = "";
        String tipoMetadado = "";
        for(MDado x: mRdf){    
            if(x.getMetadado().getTipo().equalsIgnoreCase("metadado") && x.getDado().getTipo().equalsIgnoreCase("metadado")){
                switch(x.getMetadado().getConteudo()){
                    case "h1":
                            tit = x.getDado().getConteudo();
                            tipoMetadado = "h1";
                        break;
                    case "h2":
                            if(tipoMetadado.equalsIgnoreCase("") || !tipoMetadado.equalsIgnoreCase("h1")){
                                tit = x.getDado().getConteudo();
                                tipoMetadado = "h2";
                            }
                        break;
                    case "h3":
                            if(tipoMetadado.equalsIgnoreCase("") || (!tipoMetadado.equalsIgnoreCase("h1") && !tipoMetadado.equalsIgnoreCase("h2"))){
                                tit = x.getDado().getConteudo();
                                tipoMetadado = "h3";  
                            }
                        break;
                    case "h4":
                            if(tipoMetadado.equalsIgnoreCase("") || (!tipoMetadado.equalsIgnoreCase("h1") && !tipoMetadado.equalsIgnoreCase("h2") && !tipoMetadado.equalsIgnoreCase("h3"))){
                                tit = x.getDado().getConteudo();
                                tipoMetadado = "h4";  
                            }
                        break;
                    case "h5":
                            if(tipoMetadado.equalsIgnoreCase("") || (!tipoMetadado.equalsIgnoreCase("h1") && !tipoMetadado.equalsIgnoreCase("h2") && !tipoMetadado.equalsIgnoreCase("h3") && !tipoMetadado.equalsIgnoreCase("h4"))){
                                tit = x.getDado().getConteudo();
                                tipoMetadado = "h5";  
                            }
                        break;
                    case "h6":
                            if(tipoMetadado.equalsIgnoreCase("") || (!tipoMetadado.equalsIgnoreCase("h1") && !tipoMetadado.equalsIgnoreCase("h2") && !tipoMetadado.equalsIgnoreCase("h3") && !tipoMetadado.equalsIgnoreCase("h4") && !tipoMetadado.equalsIgnoreCase("h5"))){
                                tit = x.getDado().getConteudo();
                                tipoMetadado = "h6";  
                            }
                        break;
                    default:
                            tit = x.getDado().getConteudo();
                            tipoMetadado = "";
                        break;
                }
            }
        }
        
        //System.out.println("TITULO METADADO ----->"+titulo);
        return tit; 
    }
}
