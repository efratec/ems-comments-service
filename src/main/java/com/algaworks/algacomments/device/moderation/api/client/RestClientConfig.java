package com.algaworks.algacomments.device.moderation.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    public MonitorationClient monitorationClient(RestClientFactory factory) {
        var restClient = factory.monitorationRestClient();
        var adapter =  RestClientAdapter.create(restClient);
        var proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(MonitorationClient.class);
    }

}
