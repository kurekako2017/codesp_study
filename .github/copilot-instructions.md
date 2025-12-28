# Study Repository - AI Coding Agent Instructions

## Architecture Overview

This is a multi-language learning repository containing Java Spring Boot e-commerce application and Python AI experiments.

### Java E-Commerce Application (`java-projects/JtProject`)

**Tech Stack:** Spring Boot 2.6.4, Hibernate 5 (manual config), JSP views, MySQL 8

**Critical Architectural Decision:** Uses **manual Hibernate configuration** instead of Spring Data JPA auto-configuration:
- Main application class excludes `HibernateJpaAutoConfiguration.class`
- Manual SessionFactory setup in [HibernateConfiguration.java](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/HibernateConfiguration.java)
- All DAOs use Hibernate `SessionFactory.getCurrentSession()` directly

**3-Layer Architecture:**
1. **Controllers** ([controller/](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/controller/)): Handle HTTP requests, use stateful session tracking via static variables (`adminlogcheck`, `usernameforclass`)
2. **Services** ([services/](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/services/)): Thin delegation layer to DAOs
3. **DAOs** ([dao/](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/dao/)): Direct Hibernate Session API usage with `@Transactional`

**Models:** JPA entities in [models/](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/models/) - Product, Category, User, Cart, CartProduct

## Critical Development Workflows

### Database Setup
```bash
# Database runs on external host (192.168.10.2:3306)
# Connection configured in application.properties
# Initialize schema using basedata.sql:
mysql -h 192.168.10.2 -u root -p123456 < java-projects/JtProject/basedata.sql
```

### Build & Run (Maven)
```bash
cd java-projects/JtProject
./mvnw clean install       # Build project
./mvnw spring-boot:run     # Run application (port 8080)
```

**View Resolution:** JSP files in [src/main/webapp/views/](java-projects/JtProject/src/main/webapp/views/) with prefix `/views/` and suffix `.jsp`

## Project-Specific Conventions

### Naming & Code Style
- **Lowercase class names for services/DAOs:** `productService`, `userDao` (non-standard Java convention)
- **Entity table names:** Use uppercase in `@Entity(name="PRODUCT")`, queries reference these names
- **DAO method naming:** Mix of `getProducts()` vs `deletProduct()` (typo pattern exists)

### Authentication Pattern
- **Role-based:** `ROLE_ADMIN` and `ROLE_NORMAL` stored in User entity
- **Session management:** Controllers use static variables for login state (not HTTP session)
- **Cookie usage:** Username stored in Cookie after successful login ([UserController.java](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/controller/UserController.java#L70))

### Controller Patterns
- **Dual route handling:** Both `@GetMapping` and `@RequestMapping(method=POST)` for form submissions
- **ModelAndView returns:** Used for view navigation with data (not `@ResponseBody`)
- **URL structure:** Admin routes prefixed with `/admin`, user routes at root
- **Guard pattern:** Check login state at start of methods, redirect to login if unauthorized

### Database Interaction
- **HQL queries:** Use entity names: `"from PRODUCT"` not SQL table names
- **Transaction boundaries:** `@Transactional` at DAO layer (not service layer)
- **Lazy loading workaround:** `spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true` in properties

## Key Integration Points

**Configuration file:** [application.properties](java-projects/JtProject/src/main/resources/application.properties)
- Custom property prefix `db.*` for datasource (not standard Spring `spring.datasource.*`)
- Hibernate properties with `hibernate.*` prefix
- MySQL connection to external host `192.168.10.2:3306/ecommjava`

**Static resources:** Product images stored in [src/main/resources/Product Images/](java-projects/JtProject/src/main/resources/Product%20Images/)

## Testing & Debugging

**Test endpoints:** 
- `/test` and `/test2` routes in [UserController.java](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/controller/UserController.java#L130) demonstrate Model and ModelAndView usage

**Default credentials (from basedata.sql):**
- Admin: username=`admin`, password=`123`
- User: username=`lisa`, password=`765`

## Important Notes

- **No REST API:** All endpoints return JSP views, not JSON
- **Python project empty:** `python-projects/ai-lab` has no code yet
- **Hibernate compatibility:** Using older `org.hibernate.dialect.MySQL5Dialect` for MySQL 8
- **Port conflicts:** Application runs on default port 8080
