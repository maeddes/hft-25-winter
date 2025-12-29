# hft-25-winter

## 2025-Oct-16 Initial Brainstorming session

![](/images/brainstorm.png)

Introduction to distributed systems - Overview Cloud Computing

### **Content Overview**

**Distributed Systems**
   - Difference distributed and non-distributed systems
   - Reasons to use/implement distributed-systems
   - Categories of heterogenity and resilience
   - Role communication protocols, service registry, distributed configuration
   - The fallacies of distributed computing

**The NIST Cloud Definition (2011)**
   - Breakdown of the NIST’s five essential cloud characteristics, deployment models, and service models.
  
**Overview of Major Cloud Providers**
   - Key players in the cloud space (AWS, Azure, Google Cloud, etc.).
   - Comparing evolution.

**Cloud Service/Abstraction Models**
   - IaaS, PaaS, SaaS revisited, with modern examples.
   - The evolution of abstraction models, including FaaS and Containers-as-a-Service (CaaS).

**Introduction to CNCF**
   - Role of the Cloud Native Computing Foundation (CNCF) in the cloud ecosystem.
   - CNCF Landscape: technologies, tools, and projects.

**Popular Technologies**
   - **Kubernetes**: Container orchestration in cloud-native environments.
   - **eBPF**: Extending kernel capabilities for monitoring and security.
   - **OpenTelemetry**: Observability standards and practices in modern cloud systems.

---

### **Learning Objectives**
By the end of this lecture, students will be able to:
- Be able to describe what makes a distributed system a distributed system
- Elobarate on reasons - why to build a distributed architecture?
- Describe the NIST cloud definition and its significance in the modern cloud landscape.
- Identify the major cloud providers and tell about their evolution.
- Differentiate between cloud service models and discuss their evolution, including modern abstraction models like CaaS.
- Explain the role of CNCF and analyze the CNCF landscape to identify key technologies and trends.
- Provide an overview of Kubernetes  (,eBPF, and OpenTelemetry,) explaining their impact on cloud-native development.

---

### **Student/Review Questions**
#### For now:
1. What are the five essential characteristics of cloud computing according to NIST, and how do they apply to modern cloud services?
2. Identify 3 main cloud providers.
3. What are the differences between IaaS, PaaS, and SaaS? Give examples of each in today's cloud ecosystem.
4. What is the CNCF, and why is it important in the context of cloud-native technologies?
5. Desribe 3 fallacies of distributed computing
6. What do you need to build a distributed system? What makes a distributed system a distributed system?

## 2025-Oct-23 Introduction to Container Technology

### Content Overview

1. Development in distributed teams withouth containers and the potential problems:
   - Polyglot application landscapes are challenging as all work environments need to match all runtime requirements for all languages
   - Transporting application from environment A to environment B introduces challenges and problems with mismatching runtimes

2. **Containers**
   - Isolate Applications from each other
   - Package Applications along with all Runtime requirements for easy execution and transportation between working environments
   - Simplify configuration of working environments -> only container engine needed
   - handling of all application containers through same mechanisms: docker build, docker run

3. **Docker**
   - Docker ecosystem consists of the Docker Daemon, Docker CLI and Docker Hub
   - Creation of Dockerfiles
   - Building Images - Understanding the layer concept in container images
   - Running Containers
   - Running multiple container apps with Docker Compose

### **Student/Review Questions**
1. What is Docker, and how does it differ from traditional virtual machines?
2. Explain the concept of a Docker image and a Docker container. How are they related?
3. What are the main components of a Dockerfile? Describe the purpose of each component.
4. How does Docker ensure isolation and security between containers?
5. What is a container registry, and how do you use Docker Hub to share or deploy images?
6. Describe the process of building and running a containerized application using Docker, including common commands.
7. In which way does Docker Compose use existing fuctionality and in which way does it extend it?.

### Homework

Lab exercises: https://zwtj1.cloudtrainings.online/

### What and Why containers?

![](/images/Container-1.png)

### Types of containers

![](/images/Container-2.png)

### Container scenario

![](/images/Container-3.png)

### Container load-balancing scenario

![](/images/Container-4.png)

### Open questions

- How does WebAssembly fit into all this?

## 2025-Oct-30 Advanced Container Scenarios - Networks, Volumes & Compose

### **Content Overview**  

This lecture explores advanced Docker features that are essential for managing containerized applications in cloud-native environments. Students will learn about Docker networking modes, data persistence with volumes, and orchestrating multi-container applications using Docker Compose. By mastering these topics, students will be able to design, deploy, and manage robust containerized systems effectively.  

---  
      
### **Learning Objectives**  

By the end of this lecture, students will be able to:  
1. Explain Docker's network modes and create custom networks to manage container communication.  
2. Differentiate between bind mounts and named volumes, and apply them for persistent data storage.  
3. Design and implement multi-container applications using Docker Compose.  
4. Optimize container setups by combining networking and volume management best practices.  

![](/images/Container-5.png)

Of course — here’s a set of **review / student questions** focusing on **Docker**, especially **Compose**, **networking**, and **volumes**, written in your familiar lecture-summary style.
They vary in depth, from conceptual understanding to practical reasoning:

---

### **Student / Review Questions – Docker (Compose, Networks, Volumes)**

1. **Networking Basics**

   * Explain how containers in the same Docker Compose project can communicate with each other.
   * Why does Docker Compose automatically create a network?
   * What happens if you remove the default network definition in a `docker-compose.yml`?

2. **Port Mapping**

   * Describe the difference between `EXPOSE 8080` in a `Dockerfile` and `ports: - "8080:8080"` in a `docker-compose.yml`.
   * What would happen if two containers try to map the same host port?

3. **Container Isolation & Debugging**

   * How can you access the shell of a running container, and why is this useful for debugging?
   * What kind of isolation does Docker provide between containers running on the same host?

4. **Compose Structure**

   * What is the function of the `depends_on` directive in a `docker-compose.yml` file?
   * How can you make sure that a service (e.g., a web app) only starts after its dependency (e.g., a database) is ready?

5. **Volumes & Persistence**

   * Why is it important to use volumes for database data?
   * What happens to data stored in a container’s filesystem when the container is deleted?

6. **Networking Scenarios**

   * You have three containers: `frontend`, `backend`, and `db`.
     Only `frontend` should be accessible from outside. Sketch a possible Compose network setup for this.
   * What are possible security or architectural benefits of separating services into multiple networks?

7. **Practical Example (Compose Debug)**
   Consider the following `docker-compose.yml` snippet:

   ```yaml
   services:
     web:
       build: ./frontend
       ports:
         - "8080:80"
       environment:
         - API_URL=http://backend:5000
     backend:
       build: ./backend
       ports:
         - "5000:5000"
   ```

   * How do `web` and `backend` communicate in this setup?
   * What would happen if you renamed the backend service but didn’t update the `API_URL`?

### Homework
- **DO THE DOCKER EXERCISES**

## 2025-Nov-07 Cloud Native Theory - Abstractions, CAP, Conway, 12-factor

Recap - Abstractions models in the cloud on the example of Vercel.

![](/images/Cloud-Abstractions-Vercel-Example.png)

![CAP Theorem](https://github.com/maeddes/hse-25-summer/raw/main/images/2025_04_14_CAP.png)

### **Content Overview**

1. **Distributed Systems Theory**
   - **CAP Theorem**: Understanding the trade-offs between Consistency, Availability, and Partition Tolerance in distributed systems.
   - **Conway's Law**: Exploring how software design reflects organizational structure and its implications for distributed systems.
   - **12-Factor Applications**: Best practices for building scalable, maintainable applications, focusing on principles like configuration, dependencies, and logging.
   - **Microservices**: Basic concept of microservices, its advantages, and challenges in distributed systems.

### **Learning Objectives**
By the end of this lecture, students will be able to:
   - Describe the CAP Theorem, its components, and how it affects design choices in distributed systems.
   - Explain Conway’s Law and its influence on software architecture, especially in the context of microservices.
   - List and apply the 12 factors for building scalable, portable, and maintainable applications.

### **ABOVE ALL**

Be able to relate the concepts of the NIST definition, the abstraction layers, the CAP theorem, Richardson's model and the 12-factor apps to the technologies we are covering in the lecture,
e.g. how do technologies like Spring Boot (or other frameworks/languages), Docker, Kubernetes incorporate or implement those aspects

### **Student/Review Questions**
1. What are the components of the CAP Theorem, and why can’t a distributed system fully achieve all three?
2. How does Conway’s Law impact the structure of a distributed system, especially when adopting a microservices architecture?
3. What are the key factors of a 12-factor app, and how do they contribute to application scalability and resilience?
4. Describe microservices concepts and some of its advantages over a monolithic architecture.

### Homework
- **DO THE DOCKER EXERCISES**


 Application & Communication / REST / Docker Compose

![REST and multi-application setup](/images/2024_11_15_REST_multi_container.png)

### **Content Overview**

1. **API and REST**  
   - **HTTP Basics**: Core concepts of HTTP for APIs, including request/response structure.  
   - **Introduction to REST**: Understanding the foundational ideas of REST as defined by Roy Fielding and how RESTful APIs communicate.  
   - **Nouns and Verbs**: Structuring REST APIs around resources (nouns) and actions (verbs).  
   - **Representation**: Data formats in REST (e.g., JSON, XML) and the role of content negotiation.  
   - **HTTP Return Codes**: Standard HTTP status codes, their meanings, and when to use each in API responses.  
   - **Idempotency**: Ensuring repeatable requests yield the same results to prevent unintended side effects.  
   - **Richardson's Maturity Model**: Levels of RESTful maturity, from Level 0 (HTTP as a tunnel) to Level 3 (HATEOAS), to understand API design progression.  
   - **OpenAPI and Swagger**: Using OpenAPI for defining APIs, ensuring consistency, and employing Swagger UI for visualization and testing.

2. **Docker-Compose for Multi-Component Applications**  
   - Setting up multi-component applications using Docker Compose, integrating backend APIs, databases, and frontends in a single `docker-compose.yml` file.  
   - Configuration of service communication, externalized settings, and container networking to simplify deployment and scaling.

---

### **Objectives and Exercises**

*Students should be able to:*

- Describe the foundational principles of REST and explain the HTTP concepts that underpin REST APIs.  
- Use OpenAPI to define REST APIs and visualize them with Swagger UI.  
- Create and configure a Docker Compose file to integrate multiple application components and enable effective communication between services.  

---

### **Student/Review Questions**

1. **What are the core principles of REST, and how do they align with HTTP concepts?**  
2. **Explain the importance of structuring REST APIs around resources (nouns) and actions (verbs). Provide examples.**  
3. **What is Richardson’s Maturity Model, and how does it help assess the maturity of a REST API?**  
4. **Why is idempotency important in REST APIs? Give an example of an idempotent and a non-idempotent HTTP method.**  
5. **Describe the advantages of using OpenAPI for REST API documentation.**  
6. **How does Docker Compose enable multi-component application setups, and what are the benefits of externalized configuration?**  
7. **Explain the role of container networking in Docker Compose and how it facilitates service communication.**

### 2025-Nov-28 Optimized Image Builds - Limitations of plain Docker - Scalability with Docker

### **Content Overview**

This lecture focused on different approaches to building container images for JVM-based applications. Starting from classic, low-level techniques using Dockerfiles, it introduced more advanced and cloud-native build strategies that reduce complexity, improve security, and better integrate into modern CI/CD pipelines.

We compared:

* **Traditional Dockerfiles** and **Multi-Stage Dockerfiles** for building JVM applications
* **Build tools–integrated approaches** like Google Jib
* **Cloud-Native Buildpacks**, with a closer look at **Paketo Buildpacks**

The lecture highlighted trade-offs around control vs. convenience, build-time vs. runtime concerns, image size, reproducibility, and developer experience.

---

### **Learning Objectives**

By the end of this lecture, students will be able to:

* Explain different strategies for building container images for JVM applications
* Compare Dockerfile-based and Dockerfile-less build approaches
* Understand how multi-stage builds improve image quality and security
* Describe how Jib builds container images directly from JVM build tools
* Explain the concept of Cloud-Native Buildpacks and their benefits
* Understand the role of Paketo Buildpacks in the cloud-native ecosystem
* Choose an appropriate container build strategy based on project and team requirements

---

### **Student / Review Questions**

1. Why is building container images for JVM applications more complex than for simple binaries?
2. What responsibilities does a traditional Dockerfile have when building a Spring Boot application?
3. How does a **multi-stage Dockerfile** improve image size and security compared to a single-stage Dockerfile?
4. What problems does **Jib** aim to solve compared to Dockerfile-based builds?
5. How does Jib integrate with Maven or Gradle, and what are its main advantages and limitations?
6. What are **Cloud-Native Buildpacks**, and how do they differ conceptually from Dockerfiles?
7. What responsibilities are shifted from developers to the platform when using Buildpacks?
8. What is **Paketo**, and how does it relate to CNCF and the Buildpacks ecosystem?
9. Compare the following approaches in terms of control, transparency, and automation:

   * Dockerfile
   * Multi-stage Dockerfile
   * Jib
   * Cloud-Native Buildpacks
10. In which scenarios would a team deliberately choose a Dockerfile over Buildpacks (or vice versa)?

## **2025-Dec-5th-and-12th Kubernetes**

### *Introduction*

### **Content Overview**

1. **Introduction to Kubernetes**  
   - **What is Kubernetes?**: High-level overview of Kubernetes as a container orchestration platform.  
   - **Why Kubernetes?**: Key benefits, including scalability, fault tolerance, and management of containerized applications.  

2. **Kubernetes Cluster Architecture**  
   - **Control Plane and Worker Nodes**: Explanation of the roles and responsibilities of the control plane and worker nodes in a Kubernetes cluster.  
   - **Key Components**: Overview of critical components like `kube-apiserver`, `etcd`, `kube-scheduler`, `kube-controller-manager`, and `kubelet`.  

![Kubernetes High Level Architecture](https://github.com/maeddes/hft-24-winter/raw/main/images/2024_11_11_kubernetes_architecture.png)

3. **Declarative Working Mode**  
   - **Configuration as Code**: Introduction to Kubernetes' declarative approach for managing application state using YAML manifests.  
   - **Reconciliation Loop**: How Kubernetes ensures desired state matches the actual state through its control loop.  

4. **Most Important API Objects**  
   - **Pods**: The smallest deployable unit in Kubernetes, representing one or more containers.  
   - **ReplicaSets**: Ensuring the desired number of pod replicas are running.  
   - **Deployments**: Managing updates and rollbacks for applications.  
   - **Services**: Enabling communication between pods and exposing applications to external users.

---

### **Objectives and Exercises**

*Students should be able to:*

- Explain what Kubernetes is, why it is used, and its primary benefits.  
- Describe the architecture of a Kubernetes cluster and the roles of its key components.  
- Demonstrate understanding of the declarative approach for managing applications in Kubernetes.  
- Identify and explain the purpose of key Kubernetes API objects, including pods, ReplicaSets, deployments, and services.  

---

### **Student/Review Questions**

1. **What is Kubernetes, and why is it beneficial for managing containerized applications?**  
2. **Describe the roles of the control plane and worker nodes in a Kubernetes cluster.**  
3. **What is the function of the `kube-apiserver` and `etcd` in Kubernetes?**  
4. **Explain Kubernetes’ declarative working mode. How does the reconciliation loop ensure consistency?**  
5. **What is a pod in Kubernetes, and how does it differ from a container?**  
6. **How do ReplicaSets help ensure application reliability?**  
7. **What are deployments in Kubernetes, and how do they simplify updates and rollbacks?**  
8. **How do services in Kubernetes enable communication between pods and expose applications externally?**

### *Kubernetes in Action*

### Different ways to access a Kubernetes environment (Public Cloud, Codespace, local (Kind, Minikube,...))

![Different Kubernetes Offerings](https://github.com/maeddes/hse-24-winter/blob/main/images/2024_12_02_Kubernetes_Options.png)

### Recap and relationship of important API objects

![K8s objects relationships](https://github.com/maeddes/hft-24-winter/raw/main/images/2024_12_13_Kubernetes_API_Object_Relationships.png)

### **Content Overview**  

This lecture provides an introduction to Kubernetes deployment options and revisits essential Kubernetes API objects in greater detail. Students will explore free Kubernetes trials offered by major cloud providers, local deployment tools like Minikube and Kind, and how to use Kubernetes in cloud-based IDEs like GitHub Codespaces. The session also deepens understanding of fundamental API objects such as Pods, Deployments, and Services, equipping students with practical knowledge for managing containerized applications in Kubernetes.  

---

### **Learning Objectives**  

By the end of this lecture, students will be able to:  
1. Identify and compare Kubernetes deployment options, including cloud trials and local setups.  
2. Set up a Kubernetes cluster locally using tools like Minikube or Kind, and within GitHub Codespaces.  
3. Describe the roles and relationships of basic Kubernetes API objects, including Pods, Deployments, and Services.  
4. Deploy and manage containerized applications using Kubernetes API objects.  

---  

### **Student/Review Questions**  

1. What are the advantages and limitations of using Kubernetes free trials from cloud providers compared to local tools like Minikube?  
2. How does Minikube enable Kubernetes functionality within a GitHub Codespaces environment?  
3. What is the role of a Pod in Kubernetes, and how does it differ from a Deployment?  
4. How do Services enable communication between Kubernetes Pods and external clients?  
5. How can you use a Deployment to ensure high availability for an application in Kubernetes? 

## *Kubernetes Behaviour & Networking*

![Scenario 1](https://github.com/maeddes/hft-24-winter/raw/main/images/2024_12_20_Kubernetes_Scenarios_Recovery_From_Failure.png)

![Scenario 2](https://github.com/maeddes/hft-24-winter/raw/main/images/2024_12_20_Kubernetes_Scenarios_Load_Balancing.png)

![Scenario 3](https://github.com/maeddes/hft-24-winter/raw/main/images/2024_12_20_Kubernetes_Scenarios_Zero_Downtime_Update.png)

### **Content Overview**  

In this final Kubernetes lecture, wd explored key concepts to manage and scale applications effectively. The lecture covered the three main Kubernetes Service types—ClusterIP, NodePort, and LoadBalancer—and their use cases. Students also learned how to scale instances in a Deployment, achieving automatic load-balancing across Pods using a ClusterIP Service. The session demonstrated how Kubernetes handles automatic updates of applications through rolling updates in Deployments and how it ensures high availability by automatically recovering failed instances.  

---  
### **Learning Objectives**  

By the end of this lecture, students will be able to:  
1. Differentiate between the three main Kubernetes Service types: ClusterIP, NodePort, and LoadBalancer.  
2. Scale a Kubernetes Deployment to handle increased load and distribute traffic automatically across instances.  
3. Implement rolling updates in a Deployment to update applications without downtime.  
4. Explain how Kubernetes ensures application stability by automatically recovering from Pod failures.  

---  
### **Student/Review Questions**  

1. What are the differences between ClusterIP, NodePort, and LoadBalancer Services in Kubernetes?  
2. How does Kubernetes automatically distribute incoming traffic across multiple instances in a Deployment?  
3. How can you scale a Deployment to add more instances of your application?  
4. What is a rolling update in Kubernetes, and why is it important for managing application updates?  
5. How does Kubernetes detect and recover from crashed or failed Pods automatically?  
6. Why is load-balancing critical in distributed systems, and how does Kubernetes achieve this with Services?  

### 2025-Dec-19 Kubernetes Primitives revised





For the New Year

- complete deployment: https://github.com/maeddes/hft-24-winter/raw/main/images/2024_12_13_Kubernetes_Multi_App_Definition.png
- Service Meshes & eBPF stuff
- Otel stuff