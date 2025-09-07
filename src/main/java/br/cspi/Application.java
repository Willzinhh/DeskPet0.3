package br.cspi;

import br.cspi.model.usuario.Cliente_Usuario;
import br.cspi.model.usuario.Cliente_UsuarioRepository;
import br.cspi.model.usuario.Usuario;
import br.cspi.model.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(Cliente_UsuarioRepository clienteRepo, UsuarioRepository usuarioRepo) {
//		return args -> {
//			Cliente_Usuario cliente = new Cliente_Usuario("Amin", "031.548.659-67",
//					"(55) 99935-56258", "CEU", "PREMIUM");
//			clienteRepo.save(cliente);
//
//			Usuario usuario = new Usuario("Admin", "admin@admin.com", "admin", true, cliente);
//			usuarioRepo.save(usuario);
//		};
//	}


}
