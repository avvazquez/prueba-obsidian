prueba-obsidian

Este proyecto incluye los ejercicios 1 y 2 de la prueba. Para lanzar el proyecto ubicarse en el directorio raíz y lanzar:     `docker-compose up`
    
Hay tres puntos de entrada:  
 - GET localhost:8080/node devuelve un listado de nodos  
 - POST localhost:8080/node da de alta un nodo
 - GET localhost:8080/trees devuelve un listado de árboles

**EJERCICIO BACKEND 1**

  **Objetivo**: Validación de buenas prácticas, patrones de diseño y conocimiento de Spring Boot.
	
**Enunciado**:  Desarrollar un microservicio en Spring Boot con webflux, endpoints funcionales y sus test unitarios, siguiendo las convenciones de un servicio REST. Tendrá dos endpoints, uno de guardado de objetos y otro para listar todos los objetos. El servicio debería utilizar DTOs para la comunicación con el exterior. El microservicio guardará en mongoDB los datos, utilizando docker.  
  
  Tendremos dos objetos, el primero tendrá id y nombre (nodeRoot) y el segundo estos mismos atributos y, además, una descripción (nodeDesc). Queremos que ambos objetos se guarden en una misma colección llamada “node”.
	

**EJERCICIO BACKEND 2**

**Objetivo**: Desarrollo de un algoritmo asíncrono recursivo con webflux
	
**Enunciado**:    Basándonos en lo desarrollado en el ejercicio backend 1, queremos poder generar una estructura de árbol con los nodos de la base de datos. Para ello, vamos a añadir un nuevo atributo al objeto NodeDesc (parentId). Este atributo contendrá el id de cualquier otro nodo, ya sea un NodeRoot u otro NodeDesc. 

A partir de esta premisa, es necesario desarrollar un método que, sin bloquear, devuelva un Flux con todos los árboles almacenados en la base de datos. Cada elemento de este árbol deberá contener, en un atributo, la lista de todos sus nodos hijo obtenidos de la base de datos utilizando el parentId. 

Para este método se deberá desarrollar un test unitario que verifique la correcta generación de un Flux con dos árboles con la siguiente estructura:  
    ![alt text](https://github.com/avvazquez/prueba-obsidian/blob/master/nodos.jpg?raw=true)
