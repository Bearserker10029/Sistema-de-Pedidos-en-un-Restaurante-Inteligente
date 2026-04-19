# 🍽️ Sistema de Pedidos de Restaurante

> Aplicación web desarrollada con Spring Boot y Thymeleaf para gestionar un sistema completo de pedidos de restaurante como parte de un laboratorio académico.

## 📋 Tabla de Contenidos

- [Descripción](#-descripción-del-proyecto)
- [Tecnologías](#-tecnologías-usadas)
- [Estructura](#-estructura-principal)
- [Flujo Funcional](#-flujo-funcional-implementado)
- [Gestión de Pedidos](#-gestión-de-pedidos)
- [Ejecución](#-cómo-ejecutar)

---

## 📝 Descripción del Proyecto

Una aplicación web que implementa un sistema completo de gestión de pedidos para restaurante, permitiendo a los usuarios:

✅ Visualizar el menú de platos disponibles  
✅ Crear nuevos pedidos seleccionando platos  
✅ Ver el historial de pedidos realizados  
✅ Consultar el resumen detallado de cada pedido  
✅ Cambiar el estado de los pedidos durante su preparación

## 💻 Tecnologías Usadas

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| Java | 26 | Lenguaje base |
| Spring Boot | 4.0.5 | Framework MVC |
| Spring MVC | Incluido | Controladores HTTP |
| Thymeleaf | Starter | Motor de plantillas |
| Maven | Wrapper | Gestor de dependencias |
| Lombok | Latest | Anotaciones de código |

## 📂 Estructura Principal

```
restaurante-inteligente/
├── src/
│   ├── main/
│   │   ├── java/com/example/RestauranteInteligente/
│   │   │   ├── RestauranteInteligenteApplication.java
│   │   │   ├── controller/
│   │   │   │   └── RestauranteController.java
│   │   │   ├── entity/
│   │   │   │   ├── Dish.java
│   │   │   │   ├── Order.java
│   │   │   │   └── OrderStatus.java
│   │   │   └── repository/
│   │   │       └── RestauranteRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── bootstrap.min.css
│   │       └── templates/
│   │           ├── plato.html
│   │           ├── crear.html
│   │           ├── historialpedido.html
│   │           └── resumenpedido.html
│   └── test/
│       └── java/com/example/RestauranteInteligente/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── HELP.md
└── README.md
```

## 🔄 Flujo Funcional Implementado

```
┌──────────────────────────────────────────────────────────────┐
│                    FLUJO DE PEDIDOS                          │
├──────────────────────────────────────────────────────────────┤
│  1. GET /platos                                              │
│     ↓ Visualiza el menú disponible                           │
│                                                              │
│  2. GET /crear                                               │
│     ↓ Formulario para crear nuevo pedido                     │
│                                                              │
│  3. POST /crear                                              │
│     ↓ Procesa el pedido y lo almacena                        │
│                                                              │
│  4. GET /historial                                           │
│     ↓ Muestra historial de todos los pedidos                 │
│                                                              │
│  5. GET /resumen                                             │
│     ↓ Detalle completo del pedido seleccionado               │
│                                                              │
│  6. POST /resumen                                            │
│     ↓ Actualiza el estado del pedido                         │
└──────────────────────────────────────────────────────────────┘
```

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| `/platos` | GET | Visualiza el menú de platos disponibles |
| `/crear` | GET | Muestra formulario para crear pedido |
| `/crear` | POST | Procesa y guarda el nuevo pedido |
| `/historial` | GET | Lista todos los pedidos realizados |
| `/resumen` | GET | Muestra detalle de un pedido específico |
| `/resumen` | POST | Actualiza el estado del pedido |

## 📦 Gestión de Pedidos

### Entidades Principales

**Dish (Plato)**
- `id`: Identificador único del plato
- `nombre`: Nombre del plato
- `precio`: Precio unitario (BigDecimal)
- `tiempoPreparacion`: Tiempo estimado en minutos
- `tipo`: Categoría del plato (entrada, principal, postre, bebida)

**Order (Pedido)**
- `id`: Identificador único del pedido
- Contiene lista de platos
- Calcula el total automáticamente
- Estado actual del pedido

**OrderStatus (Estados del Pedido)**
- `PENDIENTE`: Pedido registrado, en espera de preparación
- `PREPARANDO`: Se está preparando en cocina
- `LISTO`: Completado y listo para servir
- `ENTREGADO`: Entregado al cliente

### Flujo de Estados

```
PENDIENTE → PREPARANDO → LISTO → ENTREGADO
```

## 🚀 Cómo Ejecutar

### Requisitos Previos

- Java 26+ (o compatible con Spring Boot 4.0.5)
- Maven 3.6+ (incluido como wrapper)

### Ejecutar la Aplicación

**En Windows (PowerShell):**

```powershell
.\mvnw.cmd spring-boot:run
```

**En Linux/macOS:**

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

### Acceder a la Aplicación

Una vez iniciada, abre tu navegador en:

```
http://localhost:8080
```


---

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Thymeleaf Guide](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)

---

## 📄 Licencia

Este proyecto es de uso académico y educativo como parte de un laboratorio de curso universitario.
