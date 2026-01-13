# https://catalog.redhat.com/en/software/containers/rhbk/keycloak-rhel9/64f0add883a29ec473d40906
FROM registry.redhat.io/rhbk/keycloak-rhel9:26.4-7

COPY custom-conditional-authenticator.jar /opt/keycloak/providers/
COPY keycloak.conf /opt/keycloak/conf/

RUN /opt/keycloak/bin/kc.sh build

