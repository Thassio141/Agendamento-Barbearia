package br.com.schedule.security;

import br.com.schedule.models.User;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken(User user){
        return null;
    }
}
