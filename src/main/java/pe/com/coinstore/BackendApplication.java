package pe.com.coinstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.com.coinstore.entities.*;
import pe.com.coinstore.repositories.*;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EntityScan("pe.com.coinstore.entities")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			EmployeeRepository employeeRepository,
			ProjectRepository projectRepository,
			ProjectEmployeeRepository projectEmployeeRepository,
			UserRespository userRespository,
			AuthorityRepository authorityRepository

	) {
		return args -> {

			authorityRepository.save(new Authority(AuthorityName.ROLE_ADMIN));
			authorityRepository.save(new Authority(AuthorityName.ROLE_STUDENT));
			authorityRepository.save(new Authority(AuthorityName.ROLE_TEACHER));
			authorityRepository.save(new Authority(AuthorityName.ROLE_PRINCIPAL));

			authorityRepository.saveAll(List.of(
					new Authority(AuthorityName.READ),
					new Authority(AuthorityName.WRITE)
					)
			);

			userRespository.save(
					new User("gmorip",new BCryptPasswordEncoder().encode("gmorip"),true, new Date(),
							List.of(
								authorityRepository.findByName(AuthorityName.ROLE_ADMIN),
								authorityRepository.findByName(AuthorityName.WRITE)
								)
							)
					);

			userRespository.save(
					new User("crevilla",new BCryptPasswordEncoder().encode("crevilla"),true, new Date(),
							List.of(
									authorityRepository.findByName(AuthorityName.ROLE_STUDENT),
									authorityRepository.findByName(AuthorityName.READ)
							)
					)
			);







			employeeRepository.save(new Employee("Cinthy","Lima",37,"42232322"));
			employeeRepository.save(new Employee("Emma","Lima",23,"67435433"));
			employeeRepository.save(new Employee("Ana Paula","Arequipa",22,"78329988"));
			employeeRepository.save(new Employee("Gladys","Arequipa",55,"07122322"));
			Employee employee1;
			employee1=new Employee("Gonzalo","Lima",38,"421686845");
			employeeRepository.save(employee1);
			employeeRepository.save(new Employee("Pilar","Lima",36,"11111111"));
			employeeRepository.save(new Employee("Hugo","Lima",22,"22222222"));
			employeeRepository.save(new Employee("Julia","Arequipa",21,"33333333"));
			employeeRepository.save(new Employee("Kelly","Arequipa",54,"44444444"));

			Project project1;
			project1=new Project("Frontend");

			projectRepository.save(project1);
			projectRepository.save(new Project("Backend"));
			projectRepository.save(new Project("Test"));

			ProjectEmployee projectEmployee1;
			projectEmployee1 = new ProjectEmployee(project1,employee1,35);
			projectEmployeeRepository.save(projectEmployee1);
			Project project2=projectRepository.findById(Long.valueOf(2)).get();

			projectEmployeeRepository.save(new ProjectEmployee(project2,employee1,30));


			Employee foundEmployee = employeeRepository.findById(Long.valueOf(4)).get();
			System.out.println(foundEmployee.getName());
			foundEmployee.setName("Walter");
			employeeRepository.save(foundEmployee);

			Employee wrongUpdate = new Employee();
			wrongUpdate.setId(Long.valueOf(2));
			wrongUpdate.setDni("99999999");
			wrongUpdate.setEmail("Arequipa");
			employeeRepository.save(wrongUpdate);

			List<Employee> employeeList = employeeRepository.findAll();
			for(Employee e: employeeList) {
				System.out.println(e.getName());
			}

			Employee forDeleteEmployee1 = employeeRepository.findById(Long.valueOf(3)).get();
			employeeRepository.delete(forDeleteEmployee1);

			Employee forDeleteEmployee2 = new Employee();
			forDeleteEmployee2.setId(Long.valueOf(4));
			employeeRepository.delete(forDeleteEmployee2);

			System.out.println("--------------");
			employeeList = employeeRepository.findAll();
			for(Employee e: employeeList) {
				System.out.println(e.getName());
			}



			System.out.println("--------------");

			foundEmployee = employeeRepository.findByDni("42232322");
			System.out.println(foundEmployee.getName());
			foundEmployee = employeeRepository.findByAgeAndEmail(38,"Lima");
			System.out.println(foundEmployee.getName());
			System.out.println("--------------");
			employeeList = employeeRepository.findByEmail("Lima");
			for(Employee e: employeeList) {
				System.out.println(e.getName());
			}
			System.out.println("--------------");

			employeeList = employeeRepository.findByCityAndLowerAgeJPQL("Arequipa",33);
			for(Employee e: employeeList) {
				System.out.println(e.getName());
			}

			System.out.println("--------------");

			employeeList = employeeRepository.findByNameContaining("u");
			for(Employee e: employeeList) {
				System.out.println(e.getName());
			}


			System.out.println("--------------");
			List<ProjectEmployee> projectEmployeeList = projectEmployeeRepository.findByEmployee_Id(Long.valueOf(5));
			for(ProjectEmployee pe: projectEmployeeList) {
				System.out.println(pe.getProject().getName());
			}

			System.out.println("--------------");
			projectEmployeeList = projectEmployeeRepository.findByProject_Id(Long.valueOf(1));
			for(ProjectEmployee pe: projectEmployeeList) {
				System.out.println(pe.getEmployee().getName());
			}

		};
	}

}
