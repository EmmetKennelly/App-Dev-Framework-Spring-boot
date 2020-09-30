package Emmet.auction;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
@Configuration
public class InterConfig {
	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver= new CookieLocaleResolver();localeResolver.setDefaultLocale(Locale.UK);
		return localeResolver;
		}
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		}
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci= new LocaleChangeInterceptor();lci.setParamName("lang");return lci;}
}
