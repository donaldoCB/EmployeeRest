package mx.com.proyecti;

import javax.jws.WebService;
import javax.jws.WebMethod;
// Anotacion @WebService va a indicar que mi clase de tipo SOAP implementado con la libreria JAX-WS
@WebService
public class Greeter {
	private String worldGretting = "Hello Word";
	
	public Greeter() {
		
	}
	
	//Anotacion @WebMethod para indicar que los metodos que estoy definiendo van a representar a las operaciones de mi servicio web SOAP
	@WebMethod
	public String greetWorld() {
		return worldGretting;
	}
}
