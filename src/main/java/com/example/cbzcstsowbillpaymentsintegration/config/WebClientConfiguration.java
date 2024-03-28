package com.example.cbzcstsowbillpaymentsintegration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.TEXT_XML;
import static org.springframework.http.MediaType.TEXT_XML_VALUE;

@Configuration
public class WebClientConfiguration {




    @Value("${cbz.baseUrl}")
    private String baseUrl;

    public static final int TIMEOUT = 3000;


    @Bean()
    public WebClient webClientWithTimeout() {
        TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });

        return WebClient.builder()
//                .exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
//                .build()
                .baseUrl("")
                .build();
    }

    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
        clientCodecConfigurer.customCodecs().register(new Jackson2JsonEncoder(new ObjectMapper(), MimeType.valueOf(TEXT_XML_VALUE)));
        clientCodecConfigurer.customCodecs().register(new Jackson2JsonDecoder(new ObjectMapper(), MimeType.valueOf(TEXT_XML_VALUE)));
    }

}
