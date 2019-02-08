package config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import services.MotoristaService;
import services.VeiculoService;
import services.ViagemService;
import uDao.VeiculoDao;

public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		Set<Class<?>> recursos = new HashSet<Class<?>>();
		recursos.add(MotoristaService.class);
		recursos.add(VeiculoService.class);
		recursos.add(ViagemService.class);
		return recursos;
	}

}
