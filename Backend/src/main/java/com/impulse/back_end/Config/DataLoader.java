package com.impulse.back_end.Config;

import com.impulse.back_end.Dto.UsuarioPeticionDTO;
import com.impulse.back_end.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public void run(String... args) throws Exception {
        UsuarioPeticionDTO usuarioPeticionDTO = new UsuarioPeticionDTO();

        usuarioPeticionDTO.setNombre("Admin");
        usuarioPeticionDTO.setApellido("Admin");
        usuarioPeticionDTO.setEmail("admin@email.com");
        usuarioPeticionDTO.setPassword("12345678");
        usuarioPeticionDTO.setAdmin(true);

        usuarioService.registrarUsuario(usuarioPeticionDTO);
    }
}
