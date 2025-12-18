

## **Generic Lab Task Description – Distributed Systems in Docker**

**Objective:**
Each team will implement a small distributed system using **plain Docker containers**. The goal is to explore modern distributed systems patterns and OSS/CNCF tools by building a fully working, observable, and resilient system.

**Requirements (Mandatory for All Teams):**

1. **Multi-service Architecture**

   * The system must consist of **at least 2 separate services** running in independent containers.
   * Services must communicate using a networked protocol (HTTP/REST, gRPC, messaging, etc.).

2. **Dockerized Deployment**

   * Provide a `Dockerfile` for each service.
   * Provide a simple script or `docker-compose.yml` to start the entire system in one command.

3. **Integration of a Modern OSS/CNCF Tool**

   * Each team must integrate **at least one open-source technology** meaningfully (examples: Prometheus, Grafana, Keycloak, Kafka, NATS, Redis, Envoy, Jaeger).
   * The integration should **enhance the system** (observability, messaging, security, load balancing, resilience, etc.), not just exist as a side experiment.

4. **Content**

   * The options and descriptions are given in the shared spreadsheet

6. **Documentation**

   * Include a `README.md` explaining:

     * System architecture and services
     * Chosen OSS/CNCF tool and purpose
     * Instructions to build, run, and test the system
     * Demonstration of metrics, resilience, or other key patterns

7. **Code Quality**

   * Services should follow reasonable coding standards.
   * Code should be structured, modular, and maintainable.

**Deliverables:**

* GitHub repository including:

  * All service code and `Dockerfile`s
  * `docker-compose.yml` or start scripts
  * Documentation (`README.md`)
* Optional: Screenshots or recordings of observability dashboards or metrics in action

**Evaluation Criteria:**

* Correctness and completeness of the system
* Meaningful integration of OSS/CNCF technology
* Documentation clarity

### PVL Lab – Team & Topic Guidelines

- **Team Size:** Up to 3–4 students.
- **Team Formation:**  
  - Only add yourself to a team after *all members agree*.  
  - Adding your name in the spreadsheet alone does **not** make you part of the team.
  - Highlight your team in the spreadsheet (color or border).

- **Topics:**  
  - Multiple teams/individuals may choose the same topic.  
  - Maximum **3 teams per topic**.

- **If conflicts or uncertainties arise:** Contact the instructor directly.

Thanks to everyone who has already submitted results!

