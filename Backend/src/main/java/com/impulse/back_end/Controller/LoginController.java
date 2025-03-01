package com.impulse.back_end.Controller;

import com.impulse.back_end.Constant.Constants;
import com.impulse.back_end.Dto.LoginPeticionDTO;
import com.impulse.back_end.Dto.LoginRespuestaDTO;
import com.impulse.back_end.Service.LoginService;
import com.impulse.back_end.exception.LoginException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constants.PublicRoutes.LOGIN)
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginRespuestaDTO login(
            @RequestBody @Valid LoginPeticionDTO loginPeticionDTO
    ) throws LoginException {
        return loginService.login(loginPeticionDTO);
    }
}
