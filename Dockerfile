FROM ubuntu:22.04

# Update and install required packages
RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y \
    openjdk-21-jdk \
    nginx \
    openssh-server \
    php-cli \
    git \
    && rm -rf /var/lib/apt/lists/*

# Configure SSH
RUN mkdir -p /run/sshd

# Configure NGINX reverse proxy for Spring Boot
RUN echo "server { listen 8080 default_server; location / { proxy_pass http://127.0.0.1:8081; } }" > /etc/nginx/sites-available/default

# Expose required ports
EXPOSE 8080 8443 2222

# Start NGINX and SSH silently
CMD service nginx start && /usr/sbin/sshd -D
