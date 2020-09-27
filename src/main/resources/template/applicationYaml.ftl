cors:
  allowOrigin: '*'
logging:
    root: info
LOGFILE: ${project.logLoc}
mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true
  mapper-locations: classpath:${project.mapperLocation}
pagehelper:
  helper-dialect: mysql
  reasonable: false
server:
    port: ${project.port}
spring:
  datasource:
    druid:
      driverClassName: com.mysql.cj.jdbc.Driver
      filters: stat
      initialSize: 10
      max-open-prepared-statements: 20
      maxActive: 30
      maxWait: 60000
      minEvictableIdleTimeMillis: 300000
      minIdle: 5
      pool-prepared-statements: true
      testOnBorrow: true
      testOnReturn: false
      testWhileIdle: true
      timeBetweenEvictionRunsMillis: 60000
      url: ${projectProp.url}
      username: ${projectProp.username}
      password: ${projectProp.password}
      validationQuery: select 'x'
  profiles:
    active: dev
management:
  endpoints:
    web:
      exposure:
        include: metrics,httptrace
