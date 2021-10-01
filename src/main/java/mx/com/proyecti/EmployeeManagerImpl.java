package mx.com.proyecti;

import mx.com.proyecti.entity.Employee;

public class EmployeeManagerImpl {

	public static void main(String args[]) {
		// TODO Auto-generated method stub
		EmployeeManager manager = new EmployeeManager();
		manager.setup();
		Employee employee = manager.read(1);
		System.out.println("Empleado ID #" + employee.getId() + ", Nombre: " + employee.getFirstName() + " " + employee.getLastName());
		manager.exit();
	}
}