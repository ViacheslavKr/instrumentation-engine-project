[![Build Status](https://travis-ci.com/ViacheslavKr/instrumentation-engine-project.svg?branch=master)](https://travis-ci.com/ViacheslavKr/instrumentation-engine-project)

# Java Instrumentation Engineer Project


This project provides instrumentation for Spring Boot applications. It creates metric data for server requests.

# How it works?

### Filter and Servlet
Instrumentation is started in filter InstrumentationFilter then  interceptor InstrumentationInterceptor adds some metrics.


### Controller
MonitoringController provides a page with metrics


## Configuration

```xml
<dependency>
      <groupId>com.instrumentation</groupId>
      <artifactId>instrumentation-engine-starter</artifactId>
      <version>0.0.6</version>
</dependency>

```

#### Servlet and MVC Server
Configuration needs to add filters and Interceptor. All of these classes are required.


```java
@Configuration
@ImportAutoConfiguration(com.instrumentation.Configuration.class)
public class InstrumentationConfiguration implements WebMvcConfigurer {

	@Autowired
	private InstrumentationEngine instrumentationEngine;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InstrumentationInterceptor(instrumentationEngine));
	}

	@Bean
	public FilterRegistrationBean<InstrumentationFilter> instrumentationFilter() {
		InstrumentationFilter instrumentationFilter = new InstrumentationFilter(instrumentationEngine);

		FilterRegistrationBean<InstrumentationFilter> filterRegistrationBean = new FilterRegistrationBean<>(instrumentationFilter);
		filterRegistrationBean.addUrlPatterns("/api/*");
		filterRegistrationBean.setOrder(Integer.MIN_VALUE + 1);

		return filterRegistrationBean;
	}


	@Bean
	public FilterRegistrationBean<ContentLengthFilter> contentLengthFilter() {


		FilterRegistrationBean<ContentLengthFilter> filterRegistrationBean = new FilterRegistrationBean<>(new ContentLengthFilter());
		filterRegistrationBean.addUrlPatterns("/api/*");
		filterRegistrationBean.setOrder(Integer.MIN_VALUE);

		return filterRegistrationBean;
	}



}
```
