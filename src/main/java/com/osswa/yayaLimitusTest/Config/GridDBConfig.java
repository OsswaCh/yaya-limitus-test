package com.osswa.yayaLimitusTest.Config;

import java.util.Properties;

import com.osswa.yayaLimitusTest.Model.Answer;
import com.osswa.yayaLimitusTest.Model.Survey;
import com.osswa.yayaLimitusTest.Model.SurveyResponse;
import com.toshiba.mwcloud.gs.Collection;
import com.toshiba.mwcloud.gs.GridStore;
import com.toshiba.mwcloud.gs.GridStoreFactory;
import com.toshiba.mwcloud.gs.GSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GridDBConfig {

    // env variables
    @Value ("${GRIDDB_NOTIFICATION_MEMBER}")
    private String notificationMember;

    @Value ("${GRIDDB_CLUSTER_NAME}")
    private String clusterName;

    @Value ("${GRIDDB_USER}")
    private String user;

    @Value ("${GRIDDB_PASSWORD}")
    private String password;

    @Bean
    public GridStore gridStore () throws GSException{
        //acquiring a gridstore instance
        Properties properties = new Properties();
        properties.setProperty("notificationMember", notificationMember);
        properties.setProperty("clusterName", clusterName);
        properties.setProperty("user", user);
        properties.setProperty("password",password);
        return GridStoreFactory.getInstance().getGridStore(properties);
    }


    @Bean
    public Collection<String, Survey> surveyCollection (GridStore gridStore) throws GSException {
        return gridStore.putCollection("surveys", Survey.class);
    }

    @Bean
    public Collection<String, SurveyResponse> surveyResponseCollection(GridStore gridStore) throws GSException{
        Collection<String, SurveyResponse> collection = gridStore.putCollection("surveyResponses", SurveyResponse.class);
        collection.createIndex("surveyId");
        collection.createIndex("responseId");
        return collection;
    }

    @Bean
    public Collection<String, Answer> answerCollection(GridStore gridStore) throws GSException{
        Collection<String, Answer> urlCollection = gridStore.putCollection("answers", Answer.class);
        urlCollection.createIndex("surveyResponseId");
        return urlCollection;
    }


}
