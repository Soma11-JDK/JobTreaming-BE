package swm11.jdk.jobtreaming.back.utils;

import lombok.extern.log4j.Log4j2;
import swm11.jdk.jobtreaming.back.enums.user.UserRole;

import javax.servlet.http.HttpServletRequest;

import static swm11.jdk.jobtreaming.back.constants.AuthConstants.CLAIMS_EMAIL;
import static swm11.jdk.jobtreaming.back.constants.AuthConstants.CLAIMS_ROLE;

@Log4j2
public final class AuthorizationUtils {

    public static String getEmailFromRequest(HttpServletRequest request) {
        return (String) request.getAttribute(CLAIMS_EMAIL);
    }

    public static boolean isRoleUser(HttpServletRequest request) {
        return getRoleFromRequest(request).equals(UserRole.ROLE_USER);
    }

    public static boolean isRoleExpert(HttpServletRequest request) {
        return getRoleFromRequest(request).equals(UserRole.ROLE_EXPERT);
    }

    public static boolean isPlatformUser(HttpServletRequest request){
        UserRole userRole = getRoleFromRequest(request);
        return userRole.equals(UserRole.ROLE_USER) || userRole.equals(UserRole.ROLE_EXPERT);
    }

    private static UserRole getRoleFromRequest(HttpServletRequest request) {
        return (UserRole) request.getAttribute(CLAIMS_ROLE);
    }
}
