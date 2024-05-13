package com.example.conditional;

import java.io.IOException;
import java.io.InputStream;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.connections.httpclient.HttpClientProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class CustomConditionalAuthenticator implements ConditionalAuthenticator {
    private static final Logger LOG = Logger.getLogger(CustomConditionalAuthenticator.class);

    static final CustomConditionalAuthenticator SINGLETON = new CustomConditionalAuthenticator();

    @Override
    public void action(AuthenticationFlowContext context) {
        // Not used
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        // Not used
    }

    @Override
    public void close() {
        // Does nothing
    }

    // TODO
    @Override
    public boolean matchCondition(AuthenticationFlowContext context) {
        LOG.tracef("matchCondition(): %s", context);
        boolean result = true;

        // Example to get an HttpRequest object.
        LOG.tracef("Got HttpRequest: %s", context.getHttpRequest());

        // Or use context.getUriInfo() to get parameters.
        context.getUriInfo().getQueryParameters().forEach((k, mv) ->
            LOG.tracef("Got: %s, %s", k, mv));

        // You can get a preconfigured HttpClient instance which is no need to close (see JavaDoc).
        LOG.tracef("Got HttpClient: %s", context.getSession().getProvider(HttpClientProvider.class).getHttpClient());
        
        // Or use convenience methods: postText(), get().
        try {
            int responseStatus = context.getSession().getProvider(HttpClientProvider.class)
                .postText("http://localhost:8080/", "dummy data");
            LOG.tracef("POST response: %s", responseStatus);

            try (InputStream is = context.getSession().getProvider(HttpClientProvider.class)
                    .get("http://localhost:8080/")) {
                LOG.tracef("GET response: %s", new String(is.readAllBytes()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Request failed", e);
        }

        // Use User Session Note to pass around small info.
        context.getAuthenticationSession().setUserSessionNote("hoge", "hoge value");  // usually set in another plugin.
        LOG.tracef("Got note for hoge: %s", context.getAuthenticationSession().getUserSessionNotes().get("hoge"));

        return result;
    }
}
