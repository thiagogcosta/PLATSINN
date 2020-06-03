/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.Extrator;

import extratormdadoswatson.InstanciaDinamica.ExtracaoCD;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DC;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author thiago
 */
public class Busca {
    String URL;
    private Model model;
    private Resource modelResource;
    private long tempoInicial = System.currentTimeMillis();
    private ArrayList<String> linksGuardados = new ArrayList();
    private ArrayList<String> linksAcessados = new ArrayList();
    private ArrayList<Link> linksGuardadosEspecifico = new ArrayList();
    private ArrayList<Link> linksAcessadosEspecifico = new ArrayList();
    int profundidadeLimite = 0;
    private ArrayList<Dado> mheads = new ArrayList();
    private ArrayList<Dado> mconteudo = new ArrayList();
    private ArrayList<MDado> mRdf = new ArrayList();
    int contador = 0;
    private Pattern pattern;
    private Matcher matcher;
    public String domain = "";
    private static final String SITE_PATTERN = ".*\\.(png|jpg|gif|bmp|pdf|ppt|pptx|jpeg|xml|csv)";
    private Query query;
    private ResultSet results;
    private String tipoExtracao;
    ResultadoBusca r = null;
  
    
    //***********************************CONSTRUTOR*************************************
    public Busca(String u, int p, String t) throws Exception{
        tipoExtracao = t;
        URL = u;
        model = ModelFactory.createDefaultModel();
        String modelUri = URL;
        modelResource = model.createResource(modelUri);
        
        //******************************Obtenção do domínio*****************************
        if(u.contains("http://")){
            String nova = u.replace("http://", "");
            char[] chars = nova.toCharArray();
            for (int i = 0, n = chars.length; i < n; i++) {
                if(chars[i] == '/'){
                    if(chars[i] == '/'){
                        break;
                    }
                }
                domain = domain + chars[i];
            }
        }else if(u.contains("https://")){
            String nova = u.replace("https://", "");
            char[] chars = nova.toCharArray();
            for (int i = 0, n = chars.length; i < n; i++) {
                if(chars[i] == '/'){
                    break;
                }
                domain = domain + chars[i];
            }
        }else if(u.contains("www.")){
            String nova = u.replace("www.", "");
            char[] chars = nova.toCharArray();
            for (int i = 0, n = chars.length; i < n; i++) {
                if(chars[i] == '/'){
                    break;
                }
                domain = domain + chars[i];
            }
        }else{
            System.out.println("Invalidez do link: "+u);
        }
        //****************************************************************************** 
        profundidadeLimite = p;
        
        Link l = new Link(u,0);
        //System.out.println("domínio: "+domain);
        getPages(l);
        System.out.println("terminou a busca");
    }
    
    
    //***********************************GET PAGES ESPECÍFICO*****************************
    //Web Crawler navega e pega todos os metadados e dados das páginas com o mesmo domínio, no entanto
    //respeita-se uma profundidade personalizada.
    public int getPages(Link u) throws Exception{
       
        Document doc = null;
        Elements links = null;
        Elements header = null;
        
        try {
            int pcont = 0;
            //*******************************verificação de link já acessado************************
            for(Link l: linksAcessadosEspecifico){
                if(l.getUrl().contains(u.getUrl())){
                    pcont = 1;
                    break;
                }
            }
            //**************************************************************************************
            
            if(pcont != 1){
                doc = Jsoup.connect(u.getUrl()).get();
               
                //**********************************************EXTRAÇÃO METAS*******************************
                /*try{
                    header = doc.select("meta");
                    
                    for(Element meta : header) {
                        if(meta.attr("property").equals("og:title")||meta.attr("property").equals("og:description")||meta.attr("property").equals("og:url")||meta.attr("property").equals("og:site_name")){
                            Dado d = new Dado("dado",meta.attr("property"),meta.attr("content"),meta.baseUri());
                            mheads.add(d);
                        }
                    }
                }catch(Exception e){
                    System.out.println("Erro ao acessar os metas!");
                }*/
                
                
                //********************************************EXTRAÇÃO DE METADADOS*********************************
                try{
                   
                   Elements x = doc.getAllElements();
                   
                   for(Element a: x){
                        if(!a.text().isEmpty()){
                           if(a.tagName().equals("h1")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("metadado", "h1",a.text(),a.baseUri(), a.cssSelector(), u.getProfundidade());
                               mconteudo.add(d);
                           }
                           if(a.tagName().equals("h2")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("metadado", "h2",a.text(),a.baseUri(), a.cssSelector(), u.getProfundidade());
                               mconteudo.add(d);
                           } 
                           if(a.tagName().equals("h3")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("metadado", "h3",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           } 
                           if(a.tagName().equals("h4")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("metadado", "h4",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           }
                           if(a.tagName().equals("h5")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("metadado", "h5",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           } 
                           if(a.tagName().equals("h6")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("metadado", "h6",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           } 
                           if(a.tagName().equals("p")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("dado", "p",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           }
                           if(a.tagName().equals("pre")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("dado", "pre",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           }
                           if(a.tagName().equals("span")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("dado", "span",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           }
                           if(a.tagName().equals("i")){
                               //System.out.println("texto:"+a.text()+" css:"+a.cssSelector());
                               Dado d = new Dado("dado", "i",a.text(),a.baseUri(), a.cssSelector(),u.getProfundidade());
                               mconteudo.add(d);
                           }
                          
                        }
                    }
               
                    
                 //System.out.println("tag: "+a.tagName()+" conteudo: "+a.text());
                
                }catch(Exception e){
                    System.out.println("Erro ao acessar os metadados!");
                }
                
                
                
                //***********************************************EXTRAÇÃO LINKS*******************************
                //extração de links e controle da profundidade
                links = doc.select("a[href]");
                for (Element a: links) {
                    
                    //verifico se não tem os formatos da excessão
                    pattern = Pattern.compile(SITE_PATTERN);
                    matcher = pattern.matcher(a.attr("abs:href"));
                    
                    //*********************verificação se o link já está guardado****************************
                    int pguard = 0;
                    for(Link l: linksGuardadosEspecifico){
                        if(l.getUrl().equals(a.attr("abs:href"))){
                            pguard = 1;
                            break;
                        }
                    }
                    //**************************************************************************************
                    
                    if(pguard != 1){
                        if(a.attr("abs:href").contains(URL) && !matcher.find()){
                            
                            Link l = new Link(a.attr("abs:href"),u.getProfundidade());
                            linksGuardadosEspecifico.add(l);
                            System.out.println("link URL: "+l.getUrl()+" profundidade: "+l.getProfundidade());
                        }else if(a.attr("abs:href").contains(domain) && !matcher.find() && u.getProfundidade() < profundidadeLimite){
                                Link l = new Link(a.attr("abs:href"),u.getProfundidade()+1);
                                linksGuardadosEspecifico.add(l);
                                System.out.println("link DOMAIN: "+l.getUrl()+" profundidade: "+l.getProfundidade());
                            }
                        }
                    }
                
                linksAcessadosEspecifico.add(u);
            }
            
            //*****************************************link acessado****************************************
            
        contador++;
        } catch (IOException e) {
            System.err.println("Erro em: '" + URL + "': " + e.getMessage());
            contador++;
        }
        
        //************************************************RDF************************************************
        if(contador == linksGuardadosEspecifico.size()){
            
            //Verificação de grau de profundidade
            
            System.out.println("**********************************************************************************");
            /*for(Dado a: mheads){
               Dado d = new Dado("metadado", a.getTag(),a.getTag(),a.getBUri());
               MDado m = new MDado(d,a);
               mRdf.add(m);
            } */
            
            //System.out.printf("tamanho mconteudo: "+mconteudo.size());
            
     
            System.out.println("TAMANHO MCONTEUDO: "+mconteudo.size());
            for(int i = 0; i < mconteudo.size(); i++){
                try{
                    
                    //******************VERIFICAÇÃO DE GRAU DE PROFUNDIDADE, PROFUNDIDADE = LIMITE****************
                    if(mconteudo.get(i).getProfundidade() == profundidadeLimite){
                         
                         //*******************************VERIFICAÇÃO DE SUCESSÃO*********************************
                        if(mconteudo.get(i).getTipo().equals("metadado")&&mconteudo.get(i+1).getTipo().equals("metadado")){
                             Dado d = new Dado("metadado", mconteudo.get(i).getTag(),mconteudo.get(i).getTag(),mconteudo.get(i).getBUri());
                             MDado m = new MDado(d,mconteudo.get(i));

                             if(getUnicidade(m) == 1){
                                mRdf.add(m);
                             }
                        }else{
                            if(mconteudo.get(i).getTipo().equals("metadado")&&mconteudo.get(i+1).getTipo().equals("dado")){
                                 int j = i;
                                 j++;
                                while(mconteudo.get(j).getTipo().equals("dado") && j<mconteudo.size()){

                                    MDado m = new MDado(mconteudo.get(i),mconteudo.get(j));

                                    if(getUnicidade(m) == 1){
                                        mRdf.add(m);
                                    }
                                    j++;
                                }

                                 i = j-1;
                            }else{
                                Dado d = new Dado("dado", mconteudo.get(i).getTag(),mconteudo.get(i).getTag(),mconteudo.get(i).getBUri());
                                MDado m = new MDado(d,mconteudo.get(i));
                                if(getUnicidade(m) == 1){
                                    mRdf.add(m);
                                }
                            }
                        }
                    }
                }catch(Exception e){
                    System.out.println("Erro ao indexar MDado!");
                }
            }
            
            //long tempofinal = (System.currentTimeMillis() - tempoInicial)/1000;
            
            //System.out.println("tempo final: "+tempofinal);
            
            //ExtracaoCD ext = new ExtracaoCD();
            r = new ResultadoBusca();
            r.setTipoExtracao(tipoExtracao);
            r.setURL(URL);
            r.setmRdf(mRdf);
            
            
            //ext.cadastrarExtracao(r);
            
            //new ConnectOntology(tipoExtracao, mRdf, URL);
      
            return 1;
        }else{
            //System.out.println("Tamanho dos linksAcessados: "+linksAcessadosEspecifico.size());
            //System.out.println("Tamanho dos linksGuardados: "+linksGuardadosEspecifico.size());
            return getPages(linksGuardadosEspecifico.get(contador));
          
        }
            
    }
    
    //***************************RETORNA UM RESULTADO BUSCA************************
    public ResultadoBusca getR()
    {return r;
    }
    
    //***************************VERIFICAÇÃO DE UNICIDADE************************
    public int getUnicidade(MDado m){
        for(MDado t: mRdf){
            if((t.getDado().getTipo().equals(m.getDado().getTipo()))&&(t.getDado().getTag().equals(m.getDado().getTag()))&&(t.getDado().getConteudo().equals(m.getDado().getConteudo()))&&(t.getMetadado().getTipo().equals(m.getMetadado().getTipo()))&&(t.getMetadado().getTag().equals(m.getMetadado().getTag()))&&(t.getMetadado().getConteudo().equals(m.getMetadado().getConteudo()))){
                return 0;
            }   
        }
        return 1;
    }
 }