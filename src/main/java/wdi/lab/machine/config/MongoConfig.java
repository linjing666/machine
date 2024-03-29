package wdi.lab.machine.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-10 15:39
 **/
@Configuration
@Slf4j
public class MongoConfig {
 //覆盖默认的MongoDbFacotry
 @Bean
 @Autowired
 public MongoDbFactory mongoDbFactory(MongoSettingsProperties properties) {
  //客户端配置（连接数，副本集群验证）
  MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
  builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
  builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
// if (properties.getReplicaSet() != null) {
// builder.requiredReplicaSetName(properties.getReplicaSet());
// }

  builder.threadsAllowedToBlockForConnectionMultiplier(
          properties.getThreadsAllowedToBlockForConnectionMultiplier());
  builder.serverSelectionTimeout(properties.getServerSelectionTimeout());
  builder.maxWaitTime(properties.getMaxWaitTime());
  builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
  builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
  builder.connectTimeout(properties.getConnectTimeout());
  builder.socketTimeout(properties.getSocketTimeout());
//        builder.socketKeepAlive(properties.getSocketKeepAlive());
  builder.sslEnabled(properties.getSslEnabled());
  builder.sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed());
  builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
  builder.heartbeatFrequency(properties.getHeartbeatFrequency());
  builder.minHeartbeatFrequency(properties.getMinHeartbeatFrequency());
  builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
  builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
  builder.localThreshold(properties.getLocalThreshold());
  MongoClientOptions mongoClientOptions = builder.build();
  // MongoDB地址列表
  List<ServerAddress> serverAddresses = new ArrayList<>();
  for (String address : properties.getAddress()) {
   String[] hostAndPort = address.split(":");
   String host = hostAndPort[0];
   Integer port = Integer.parseInt(hostAndPort[1]);
   ServerAddress serverAddress = new ServerAddress(host, port);
   serverAddresses.add(serverAddress);
  }
  //System.out.println("serverAddresses:" + serverAddresses.toString());​
  // 连接认证
 MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(properties.getUsername(),
                properties.getDatabase() != null ? properties.getDatabase() : properties.getDatabase(),
                properties.getPassword().toCharArray());
//​​
  //创建客户端和Factory
  MongoClient mongoClient = new MongoClient(serverAddresses,mongoCredential, mongoClientOptions);
  MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
  log.info("mongodb注入成功");
  return mongoDbFactory;
 }
}