package com.yunxin.cb.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class Elasticsearch {

/*    @Bean
    public TransportClient client() throws UnknownHostException {
        InetSocketTransportAddress node1 = new InetSocketTransportAddress(InetAddress.getByName("192.168.0.145"),
                9300
        );
        //连接localhost的9300端口，即Elasticsearch的master
        Settings settings = Settings.builder().
                put("cluster.name","elasticsearch")//定义集群的名字，应该与Elasticsearch的master的配置保持一致
                .build();
        TransportClient transportClient = TransportClient.builder()
                .settings(settings)
                .build()
                .addTransportAddress(node1);
        return transportClient;
    }*/

}
