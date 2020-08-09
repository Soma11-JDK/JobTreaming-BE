package swm11.jdk.livexpert.back.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import swm11.jdk.livexpert.back.app.user.model.MyUserDetails;
import swm11.jdk.livexpert.back.app.user.model.User;
import swm11.jdk.livexpert.back.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        User user = ((MyUserDetails)authentication.getPrincipal()).getUser();
        String token = TokenUtils.generateJwtToken(user);
        response.addHeader("Authorization", "Bearer " + token);
    }

}