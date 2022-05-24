package com.UdecPasantiasBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UdecPasantiasBackApplication {
//comando solucion problemas de puerto:
//		CMD como administrador
//		netstat -ao |find /i "listening"
//		buscar el puerto que diga 8080 y copiar el id
//			TCP    0.0.0.0:8080           DESKTOP-8VHQT0L:0      LISTENING       5856
//			TCP    0.0.0.0:8080           Jimmy-Rueda:0          LISTENING       23264
//		ingresar el siguiente comando cambiando el id
//		Taskkill /F /IM 5856
	
	public static void main(String[] args) {
		SpringApplication.run(UdecPasantiasBackApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
}
