# https://catalog.redhat.com/en/software/containers/rhbk/keycloak-rhel9/64f0add883a29ec473d40906
FROM registry.redhat.io/rhbk/keycloak-rhel9:26.4-7

COPY custom-conditional-authenticator.jar /opt/keycloak/providers/
COPY keycloak.conf /opt/keycloak/conf/

# Suppress resource usage to build in a small cluster
# RUN JAVA_OPTS_KC_HEAP="-XX:ActiveProcessorCount=1 -Xms256m -Xmx256m" /opt/keycloak/bin/kc.sh build
RUN /opt/keycloak/bin/kc.sh build

