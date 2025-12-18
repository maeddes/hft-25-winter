# Load-Balancing with nginx reverse proxy - persistence with Spring Profiles

---

## Shopping Items App — Docker Compose Setup & Usage

This document explains how to run, test, and debug the **Shopping Items** application consisting of:

* **Spring Boot API** (Java) with Postgres or H2
* **Python Flask client**
* **NGINX load balancer**
* Optional: switching between **prod** and **dev** profiles

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Starting with Docker Compose](#starting-with-docker-compose)
3. [Validating Containers](#validating-containers)
4. [Sample Requests](#sample-requests)
5. [NGINX Configuration](#nginx-configuration)
6. [Important Docker Compose Parts](#important-docker-compose-parts)
7. [Debugging & Logs](#debugging--logs)
8. [Switching from Prod to Dev](#switching-from-prod-to-dev)
9. [Notes & Tips](#notes--tips)

---

## Project Overview

The project contains:

* **Spring Boot API** (`/items`)

  * Endpoints:

    * `GET /items` — list all items
    * `POST /items` — add items
    * `PUT /items/{name}` — update items
    * `GET /items/hostname` — show container hostname (for debugging)
  * Profiles:

    * `prod` → Postgres database
    * `dev` → H2 in-memory database

* **Python Flask client**

  * Interacts with Spring Boot via `API_BASE` environment variable
  * Can be run in browser or via REST

* **NGINX**

  * Acts as a reverse proxy / load balancer for multiple Spring Boot instances
  * Configured via `nginx.conf`

---

## Starting with Docker Compose

### 1. Default (production)

```bash
docker-compose up -d --build
```

* By default, **Spring Boot runs with `prod` profile** using Postgres.
* Flask client and NGINX are started automatically.
* Ports exposed:

  * Flask: `5123` → `http://localhost:5123`
  * NGINX: `8080` → `http://localhost:8080`
  * Postgres: `5432` → `localhost:5432`

---

### 2. Wait for services to be ready

```bash
docker compose ps
```

Expected output:

```
NAME                       IMAGE                     COMMAND                  SERVICE          CREATED          STATUS          PORTS
flask-client               frontend-flask:v0.1       "python client.py"       flask            44 seconds ago   Up 43 seconds   0.0.0.0:5123->5123/tcp, [::]:5123->5123/tcp
items-postgres             postgres:15               "docker-entrypoint.s…"   postgres         44 seconds ago   Up 43 seconds   0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp
project-nginx-1            nginx:latest              "/docker-entrypoint.…"   nginx            44 seconds ago   Up 43 seconds   0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp
project-springboot-one-1   backend-springboot:v0.1   "java -jar /opt/app.…"   springboot-one   44 seconds ago   Up 43 seconds   0.0.0.0:8091->8090/tcp, [::]:8091->8090/tcp
project-springboot-two-1   backend-springboot:v0.1   "java -jar /opt/app.…"   springboot-two   44 seconds ago   Up 43 seconds   0.0.0.0:8092->8090/tcp, [::]:8092->8090/tcp
```

---

## Sample Requests

### 1. From host (Flask client)

```bash
curl http://localhost:5123/
```

This one works better in the browser :-)

### 2. From host (Spring Boot via NGINX)

```bash
curl http://localhost:8080/items
```

### 3. Add an item

```bash
curl -X POST http://localhost:8080/items -H "Content-Type: application/json" -d '{ "name": "chocolate", "quantity": 4 }'
```

### 4. Get container hostname

```bash
curl http://localhost:8080/items/hostname
```

---

## NGINX Configuration (`nginx.conf`)

```nginx
events {}

http {
    upstream spring_cluster {
        server springboot-one:8090;
        server springboot-two:8090;
    }

    server {
        listen 8080;

        location / {
            proxy_pass http://spring_cluster;
            proxy_set_header Host localhost;                 
            }
    }
}
```

**Explanation:**

* `upstream spring_cluster` → defines backend Spring Boot instances
* `proxy_pass` → forwards requests to the cluster
* `proxy_set_header Host localhost` → prevents Spring Boot from rejecting underscores in Docker container names

---

## Important Docker Compose Parts

```yaml
flask:
  build: ./frontend-flask
  container_name: flask-client
  ports:
    - "5123:5123"
  environment:
    API_BASE: http://nginx:8080/items
  depends_on:
    - nginx

nginx:
  image: nginx:latest
  volumes:
    - ./nginx.conf:/etc/nginx/nginx.conf
  ports:
    - "8080:8080"
  depends_on:
    - springboot-one-1
    - springboot-one-2

springboot-one-1:
  build: ./springboot
  environment:
    SPRING_PROFILES_ACTIVE: prod

springboot-one-2:
  build: ./springboot
  environment:
    SPRING_PROFILES_ACTIVE: prod

postgres:
  image: postgres:15
  environment:
    POSTGRES_USER: postgres
    POSTGRES_PASSWORD: yourpassword
    POSTGRES_DB: itemsdb
  ports:
    - "5432:5432"
```

**Notes:**

* Flask depends on NGINX → ensures order
* Spring Boot has `SPRING_PROFILES_ACTIVE` → selects DB
* Postgres configured for prod DB

---

## Debugging & Logs

### 1. Flask logs

```bash
docker logs -f flask-client
```

Expected output:

```
🚀 Flask client starting — VERSION v0.1
🔧 API_BASE configured as: http://nginx:8080/items
 * Running on http://0.0.0.0:5123
```

### 2. Spring Boot logs

```bash
docker logs -f springboot-one-1
```

```
Active Spring profile: prod
...
Note: hostname endpoint works → GET /items/hostname
```

### 3. Validate NGINX

```bash
docker logs -f nginx
```

* Check for proxying errors
* Ensure requests reach Spring Boot

---

## Switching from Prod to Dev

### 1. Change profile in Docker Compose

```yaml
springboot-one-1:
  environment:
    SPRING_PROFILES_ACTIVE: dev

springboot-one-2:
  environment:
    SPRING_PROFILES_ACTIVE: dev
```

* DEV profile uses **H2 in-memory database**
* No Postgres needed

### 2. Recreate containers

```bash
docker-compose up -d --build --force-recreate
```

### 3. Verify

* Logs now show:

```
Active Spring profile: dev
Datasource: H2 in-memory
```

* `curl http://localhost:8080/items` still works, but DB is H2. Which backend and database will be picked is not predictable

### 4. Differences between PROD and DEV

| Feature                | PROD                          | DEV                          |
| ---------------------- | ----------------------------- | ---------------------------- |
| Database               | Postgres (persistent)         | H2 in-memory (volatile)      |
| Spring profile         | `prod`                        | `dev`                        |
| Container dependencies | Postgres container required   | None                         |
| Startup logs           | `Active Spring profile: prod` | `Active Spring profile: dev` |

---

## Notes & Tips

* **Flask binds to `0.0.0.0`** → container accessible from host
* **Always rebuild** when changing env:

```bash
docker-compose up -d --build --force-recreate
```

* **Check environment variables** in container:

```bash
docker exec -it flask-client env | grep API_BASE
```

* **Use `/items/debug`** to identify which Spring Boot container handled the request (useful with load balancing)

---

✅ **This setup allows**:

* Running the system fully containerized
* Load-balancing Spring Boot requests through NGINX
* Switching between dev and prod profiles



