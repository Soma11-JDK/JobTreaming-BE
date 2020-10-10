package swm11.jdk.jobtreaming.back.config.security;

import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import swm11.jdk.jobtreaming.back.constants.AuthConstants;
import swm11.jdk.jobtreaming.back.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static swm11.jdk.jobtreaming.back.constants.AuthConstants.CLAIMS_EMAIL;
import static swm11.jdk.jobtreaming.back.constants.AuthConstants.CLAIMS_ROLE;

@Log4j2
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        if (header != null) {
            Optional<Claims> optional = TokenUtils.isValidToken(header);
            if (optional.isPresent()) {
                Claims claims = optional.get();
                request.setAttribute(CLAIMS_EMAIL, TokenUtils.getEmailFromClaims(claims));
                request.setAttribute(CLAIMS_ROLE, TokenUtils.getRoleFromClaims(claims));
                return true;
            }
        }

        response.sendRedirect("/error/unauthorized");
        return false;
    }

}