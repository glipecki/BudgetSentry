package net.liepcki.budgetsentry.user;

import org.springframework.stereotype.Service;

/**
 * @author gregorry
 */
@Service
public class StaticCurrentUserProvider implements CurrentUserProvider {

    public static final String USER = "greg";

    @Override
    public String getCurrentUser() {
        return USER;
    }

}
