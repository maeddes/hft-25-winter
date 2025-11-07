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