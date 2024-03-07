package com.senacead.PI.controller;

import com.senacead.PI.entity.Autenticacao;
import com.senacead.PI.entity.UserSession;
import com.senacead.PI.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    
//    @GetMapping("/*")
//    public String home() {
//        return "login";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/produtos")
    public String produtos() {
        return "produtos";
    }

    @GetMapping("/vendas")
    public String vendas() {
        return "vendas";
    }

    private final Autenticacao autenticacao;

    public LoginController(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("user") String user, @RequestParam("sen") String sen, org.springframework.ui.Model model) {
        Usuario usuarioAutenticado = autenticacao.autenticar(user, sen);
        if (usuarioAutenticado != null) {
            UserSession.getInstance().setTipoUsuario(usuarioAutenticado.getTipoUsuario());
            return "redirect:/main"; 
        } else {
            model.addAttribute("error", "Usuário ou senha inválidos");
            return "login"; 
        }
    }

}
