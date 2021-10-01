package mx.com.proyecti;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import mx.com.proyecti.entity.Employee;

//La clase EmployeeManager implementa el patron de diseno Manager
//Patron Manager nos permite manejar multiples entidades(Employee) del mismo tipo
//Nos permite manjar varias operaciones sobre la entidad(CRUD)

public class EmployeeManager {
	protected SessionFactory sessionFactory;
	
	public void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		// Llamamos a las sentencias try-catch cuando intentamos acceder a recursos internos
		try {
			sessionFactory = new
					MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
		}			
	}
	
	public void exit() {
		//Me va a permitir cerrar la conexion a mi base de datos
		sessionFactory.close();
	}
	
	// Operaciones CRUD
	
	// Operacion CREATE
	public long create(String firstName, String lastName, Date birthdate, double salary) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setBirthdate(birthdate);
		
		//Objeto de sesion hibernate que se a crear cada que nosostros queramos interactuar con nuestra db
		Session session = sessionFactory.openSession();
		//Abre una transaccion
		session.beginTransaction();
		long id = (Integer)(session.save(employee));
		session.getTransaction().commit();
		session.close();
		return id;
	}
	
	// Operacion READ
	public Employee read (long id) {
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class, id);
		session.close();
		return employee;
	}
	
	// Operacion UPDATE
	public boolean update(long id, String firstName, String lastName, Date birthdate, double salary) {
		Employee employee = new Employee();
		employee = read(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setBirthdate(birthdate);
		employee.setSalary(salary);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(employee);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	public boolean delete(long id) {
		Employee employee = new Employee();
		employee.setId(id);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(employee);
		session.getTransaction().commit();
		session.close();
		return true;
	}
}
