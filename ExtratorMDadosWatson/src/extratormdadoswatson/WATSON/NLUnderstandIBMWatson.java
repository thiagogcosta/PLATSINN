/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extratormdadoswatson.WATSON;


import com.ibm.watson.developer_cloud.http.RequestBuilder;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions.Builder;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionScores;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ListModelsResults;
import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
/**
 *
 * @author thiag
 */
public class NLUnderstandIBMWatson {
    private NaturalLanguageUnderstanding servico;
    private String conteudo;
    
    public NLUnderstandIBMWatson(String texto){
        
        String url = "https://gateway.watsonplatform.net/natural-language-understanding/api";
        String login = "LOGIN";
        String senha = "SENHA";
        
        servico = new NaturalLanguageUnderstanding(
        NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);

        servico.setUsernameAndPassword(login, senha);
        servico.setEndPoint(url);
        conteudo = texto;
    }
    
    public String getAnaliseCognitiva() throws Exception{
        
        ConceptsOptions concepts = new ConceptsOptions.Builder()
            .limit(30)
            .build();
        EntitiesOptions entities = new EntitiesOptions.Builder()
            .emotion(false)
            .limit(30)
            .sentiment(true)
            .build();
        EmotionOptions emotion = new EmotionOptions.Builder().build();
        SentimentOptions sentiment = new SentimentOptions.Builder()
            .document(true)
            .build();
        KeywordsOptions keywords = new KeywordsOptions.Builder()
            .emotion(false)
            .limit(10)
            .sentiment(true)
            .build();
        Features features = new Features.Builder()
            .categories(new CategoriesOptions())
            .concepts(concepts)
            .entities(entities)
            .keywords(keywords)
            .emotion(emotion)
            .sentiment(sentiment)
            .build();
        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
            .text(conteudo)
            .features(features)
            .returnAnalyzedText(true)
            .build();
        Builder builder = new AnalyzeOptions.Builder()
            .features(features)
            .returnAnalyzedText(true);

        AnalysisResults results = servico.analyze(parameters).execute();
        
        //*************CONCEITOS*************
        assertNotNull(results);
        assertNotNull(results.getAnalyzedText());
        assertNotNull(results.getConcepts());
        for (ConceptsResult concept : results.getConcepts()) {
          assertNotNull(concept.getText());
          assertNotNull(concept.getDbpediaResource());
          assertNotNull(concept.getRelevance());
        }
            
        //***********ENTIDADES************
        for (EntitiesResult result : results.getEntities()) {
            assertNotNull(result.getCount());
            assertNotNull(result.getRelevance());
            assertNotNull(result.getText());
            assertNotNull(result.getType());
            assertNotNull(result.getSentiment());
        }
        
        //***********PALAVRAS-CHAVE********
        assertNotNull(results.getKeywords());

        for (KeywordsResult result : results.getKeywords()) {
          assertNotNull(result.getRelevance());
          assertNotNull(result.getText());
          assertNotNull(result.getSentiment());
        }
        
        //************EMOÇÃO***************
        assertNotNull(results.getEmotion());
        assertNotNull(results.getEmotion().getDocument());
        assertNotNull(results.getEmotion().getDocument().getEmotion());

        EmotionScores scores = results.getEmotion().getDocument().getEmotion();
        assertNotNull(scores.getAnger());
        assertNotNull(scores.getDisgust());
        assertNotNull(scores.getFear());
        assertNotNull(scores.getJoy());
        assertNotNull(scores.getSadness());
        
        //*************SENTIMENTOS*********
        assertNotNull(results);
        assertEquals(results.getAnalyzedText(), conteudo);
        assertEquals(results.getLanguage(), "en");
        assertNotNull(results.getSentiment());
        assertNotNull(results.getSentiment().getDocument());
        assertNotNull(results.getSentiment().getDocument().getScore());
        assertNotNull(results.getSentiment());
        assertNull(results.getSentiment().getTargets());
        
        return results.toString();   
    }     
}
