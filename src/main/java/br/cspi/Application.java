package br.cspi;


import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "DeskPet",
				version = "3.0",
				description = "Documentação API DeskPet",
				contact = @Contact(name = "Suport", email = "willianpotkova@gmail.com")
		)
)


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(OwnerRepository clienteRepo, UserRepository usuarioRepo) {
//		return args -> {
//			Owner cliente = new Owner("Amin", "031.548.659-67",
//					"(55)99935-56258", "CEU", "PREMIUM");
//			clienteRepo.save(cliente);
//
//			User usuario = new User("Admin", "admin@admin.com", "admin", true, cliente);
//			usuarioRepo.save(usuario);
//		};
//	}


}
