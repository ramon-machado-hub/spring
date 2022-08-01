package br.ifs.web1.securitty;

import br.ifs.web1.data.DetalheUsuarioData;
import br.ifs.web1.model.Usuario;
import br.ifs.web1.service.UsuarioService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {
//    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//    MyRepository myRepo = UtilBean.getBean(MyRepository.class);

    private final UsuarioService usuarioService;
//    = context.getBean(UsuarioRepository.class);

    public static final int TOKEN_EXPIRACAO = 6000_000;

    public static final String TOKEN_SENHA = "edfb5495-bffd-4c08-ad9e-e2c3aed7bd43";

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        try {
            Usuario usuario = new ObjectMapper()
                    .readValue(request.getInputStream(), Usuario.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getLoginUsuario(),
                    usuario.getSenhaUsuario(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw  new RuntimeException("Falha ao autenticar usuario", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        String token = JWT.create().
                withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRACAO))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));
        //gravar token na tabela
        this.usuarioService.saveTokenUsuario(usuarioData.getUsername(),usuarioData.getPassword(),token);
//        Usuario usu = usuarioRepository.findByLoginUsuarioAndSenhaUsuario(usuarioData.getUsername(),usuarioData.getPassword());
//        usu.setTokenUsuario(token);
//        usuarioRepository.save(usu);
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
