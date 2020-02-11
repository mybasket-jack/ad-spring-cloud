# ad-spring-cloud
广告系统设计与实现（学习）

# eureka多节点配置
修改hosts,增加主机名

127.0.0.1 server1
127.0.0.1 server2
127.0.0.1 server3

配置多节点的连接地址
运行节点测试 java -jar ad-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=server2
