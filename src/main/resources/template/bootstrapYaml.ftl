spring:
  application:
    name: ${project.projectFullName}
  cloud:
    nacos:
      server-addr: ${project.nacosServerAddr}
      username: ${project.nacosUsername}
      password: ${project.nacosPassword}
      config:
        refresh-enabled: true
        file-extension: yml