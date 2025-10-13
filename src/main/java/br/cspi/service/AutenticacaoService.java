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
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        else{
            UserDetails user = User.withEmail(user.getEmail())
                    .password(user.get)
        }
    }

}
