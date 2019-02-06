package config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import services.MotoristaService;

public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		Set<Class<?>> recursos = new HashSet<Class<?>>();
		recursos.add(MotoristaService.class);
		return recursos;
	}

}
