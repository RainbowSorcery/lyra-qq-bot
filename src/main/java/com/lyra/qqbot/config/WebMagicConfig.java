package com.lyra.qqbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Configuration
public class WebMagicConfig {
    @Bean
    public HttpClientDownloader httpClientDownloader() {
        HttpClientDownloader httpClientDownloader =  new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",7890)));
        return httpClientDownloader;
    }
}
