FROM registry.redhat.io/rhbk/keycloak-rhel9:22-10

COPY target/custom-conditional-authenticator.jar /opt/keycloak/providers/
COPY keycloak.conf /opt/keycloak/conf/

RUN /opt/keycloak/bin/kc.sh build

