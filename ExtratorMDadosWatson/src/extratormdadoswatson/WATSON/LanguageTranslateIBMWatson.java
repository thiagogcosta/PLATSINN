/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.WATSON;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.language_translator.v2.util.Language;
import scala.util.parsing.json.JSONObject;

/**
 *
 * @author thiag
 */
public class LanguageTranslateIBMWatson {
    
    private LanguageTranslator servico;
    
    public LanguageTranslateIBMWatson(){
        
        String url = "https://gateway.watsonplatform.net/language-translator/api";
        String login = "LOGIN";
        String senha = "SENHA";
        
        servico = new LanguageTranslator();
        servico.setUsernameAndPassword(login, senha);
        servico.setEndPoint(url);
    }
    
    public String traducao(String conteudo){
        String texto_traduzido = "";
        TranslateOptions traducao = new TranslateOptions.Builder()
                .addText(conteudo).source(Language.PORTUGUESE).target(Language.ENGLISH).build();
        TranslationResult translationResult = servico.translate(traducao).execute();
        
        System.out.println(translationResult);
        
        return translationResult.getTranslations().get(0).getTranslation();
        
    }
}
