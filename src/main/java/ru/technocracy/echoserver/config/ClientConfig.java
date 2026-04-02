package ru.technocracy.echoserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.technocracy.echoserver.integrations.ExternalClient;

import java.time.Duration;

@Configuration
public class ClientConfig{

    @Bean
    public RestClient restClient(){

        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory();
        factory.setReadTimeout(Duration.ofSeconds(3));

        return RestClient.builder()
                .requestFactory(factory)
                .baseUrl("https://kudago.com/public-api/v1.4")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Bean
    public ExternalClient externalClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ExternalClient.class);
    }

}