package br.cspi.service;

import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User usuario = userRepository.findByEmail(login);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario ou senha Incoreetos");
        }else{
            System.out.println(usuario.getEmail());
            System.out.println(usuario.getPermissao());
            return org.springframework.security.core.userdetails.User.withUsername(usuario.getEmail())
                    .password(usuario.getSenha()).authorities(usuario.getPermissao()).build();
        }

    }
}
