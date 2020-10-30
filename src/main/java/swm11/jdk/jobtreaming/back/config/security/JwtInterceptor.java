package swm11.jdk.jobtreaming.back.config.security;

import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerInterceptor;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;
import swm11.jdk.jobtreaming.back.constants.AuthConstants;
import swm11.jdk.jobtreaming.back.utils.TokenUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static swm11.jdk.jobtreaming.back.constants.AuthConstants.CLAIMS_EMAIL;
import static swm11.jdk.jobtreaming.back.constants.AuthConstants.CLAIMS_ROLE;

@Log4j2
public class JwtInterceptor implements HandlerInterceptor {

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String header = request.getHeader(AuthConstants.AUTH_HEADER);
        if (header != null) {
            header = header.split(" ")[1];
            Optional<Claims> optional = TokenUtils.isValidToken(header);
            if (optional.isPresent()) {
                Claims claims = optional.get();
                MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(TokenUtils.getEmailFromClaims(claims));
                Authentication authentication = new UsernamePasswordAuthenticationToken(myUserDetails, "", myUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return true;
            }
        }

        response.sendRedirect("/error/unauthorized");
        return false;
    }

}