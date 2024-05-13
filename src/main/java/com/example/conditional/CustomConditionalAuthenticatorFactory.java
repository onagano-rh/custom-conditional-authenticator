package com.example.conditional;

import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

public class CustomConditionalAuthenticatorFactory implements ConditionalAuthenticatorFactory {

    @Override
    public String getId() {
        return "custom-conditional-authenticator"; // TODO
    }

    @Override
    public String getDisplayType() {
        return "Condition - custom implementation"; // TODO
    }

    @Override
    public String getHelpText() {
        return "Executes the current flow only if a condition met"; // TODO
    }

    @Override
    public ConditionalAuthenticator getSingleton() {
        return CustomConditionalAuthenticator.SINGLETON;
    }

    @Override
    public void init(Scope config) {
        // no-op
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // no-op
    }

    @Override
    public void close() {
        // no-op
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    private static final Requirement[] REQUIREMENT_CHOICES = {
        AuthenticationExecutionModel.Requirement.REQUIRED, AuthenticationExecutionModel.Requirement.DISABLED
    };

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return null;
    }
}
