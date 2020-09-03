package com.springboot.cloud.auth.client.config;

import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@AutoConfigureBefore(FeignAutoConfiguration.class)
@Configuration
@ConditionalOnClass(Feign.class)
/****
 *     需要修改成OKHTTP的客户端，需要在配置文件增加
 *     feign.httpclient.enabled=false
	   feign.okhttp.enabled=true
 */
public class FeignOkHttpConfig {

	private int feignOkHttpReadTimeout = 60;
	private int feignConnectTimeout = 60;
	private int feignWriteTimeout = 120;

	@Bean
	public okhttp3.OkHttpClient okHttpClient() {
		return new okhttp3.OkHttpClient.Builder()
				.readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS)
				.connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
				.writeTimeout(feignWriteTimeout, TimeUnit.SECONDS)
//				.connectionPool(new ConnectionPool(int maxIdleConnections, long keepAliveDuration, TimeUnit timeUnit))   //自定义链接池
//				.addInterceptor(XXXXXXXInterceptor) 	//自定义拦截器
				.build();
	}


//	@Bean
//	public Decoder feignDecoder() {
//		return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
//	}
//
//	public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
//		final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GateWayMappingJackson2HttpMessageConverter());
//		return new ObjectFactory<HttpMessageConverters>() {
//			@Override
//			public HttpMessageConverters getObject() throws BeansException {
//				return httpMessageConverters;
//			}
//		};
//	}
//
//	public class GateWayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
//		GateWayMappingJackson2HttpMessageConverter() {
//			List<MediaType> mediaTypes = new ArrayList<>();
//			mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
//			setSupportedMediaTypes(mediaTypes);
//		}
//	}

}
