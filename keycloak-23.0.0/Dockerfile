FROM quay.io/keycloak/keycloak:23.0.0

# Copy the realm file
COPY ./data/import/realm-export.json /opt/keycloak/data/import/

# Environment variables
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=adminn

# Use the correct import flag
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--http-port=8092", "--import-realm"]