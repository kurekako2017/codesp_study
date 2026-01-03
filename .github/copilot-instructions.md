# Study Repository - AI Coding Agent Instructions

## 📁 Repository Architecture

Multi-domain learning workspace with **four completely isolated projects**:

| Project | Type | Status | Purpose |
|---------|------|--------|---------|
| `java-projects/JtProject/` | Spring Boot 2.7 + Hibernate | Active | E-commerce app, manual Hibernate config |
| `sap-lab/` | ABAP/CDS/RAP/CAP | Learning | 7-module SAP skill progression |
| `python-projects/ai-lab/` | Python ML/AI | Active | ML fundamentals + LLM experiments |
| `localstack-lab/` | AWS emulation | Active | S3/DynamoDB local testing |

---

## 🔧 Java Spring Boot E-Commerce (JtProject)

### Critical Architecture: Manual Hibernate + 3-Layer JSP

**Why manual?** `@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)` disables Spring Data JPA. All session management is in [HibernateConfiguration.java](java-projects/JtProject/src/main/java/com/jtspringproject/JtSpringProject/HibernateConfiguration.java).

**Data flow:**
```
Controller (static auth vars) 
  ↓ 
Service (thin delegation) 
  ↓ 
DAO (@Transactional, SessionFactory.getCurrentSession())
  ↓ 
Hibernate → MySQL (192.168.10.2:3306/ecommjava)
```

### Build & Test
```bash
cd java-projects/JtProject
./mvnw clean install              # Build + run tests
./mvnw spring-boot:run            # Run on port 8080
# or
./start.sh                         # Shell script wrapper
```

### Code Patterns

| What | Where | Pattern |
|------|-------|---------|
| Entities | `models/` | `@Entity(name="PRODUCT")` — HQL queries use uppercase names |
| DAOs | `dao/` | Call `sessionFactory.getCurrentSession()`, use `@Transactional` |
| Services | `services/` | Lowercase bean names (`productService`), delegate to DAOs |
| Controllers | `controller/` | Check static vars (`adminlogcheck`, `usernameforclass`) before processing |
| Views | `src/main/webapp/views/` | JSP files, resolved as `/views/{name}.jsp` |

### Configuration Gotchas
- **Datasource prefix:** `db.*` not `spring.datasource.*` in [application.properties](java-projects/JtProject/src/main/resources/application.properties)
- **Lazy loading:** Enable `spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true` in properties
- **Default users:** Admin (`admin/123`), User (`lisa/765`) from `basedata.sql`
- **MySQL dialect:** Uses `MySQL5Dialect` (works with MySQL 8)

---

## 📚 SAP Learning Projects (sap-lab)

**Structure:** 7 progressive modules in order (see [LEARNING_GUIDE.md](sap-lab/LEARNING_GUIDE.md)):

1. **ABAP Cloud & Clean Core** — Language fundamentals + `abaplint` code quality
2. **CDS Foundation** — Data models, views, associations
3. **RAP Managed** — Auto-generated CRUD + annotations
4. **RAP + Fiori Elements** — UI annotations (list/object pages)
5. **RAP Unmanaged** — Custom logic, determinations, validations
6. **RAP External Services** — HTTP clients, REST integration
7. **CAP Quick Check** — CAP models vs RAP comparison

### Key Files
- **CDS examples:** `02-cds-foundation/` (ProductMaster.cds, associations)
- **ABAP patterns:** `01-abap-cloud-clean-core/example.abap` + [CODE_EXPLANATION.md](sap-lab/projects/01-abap-cloud-clean-core/CODE_EXPLANATION.md)
- **Fiori annotations:** [ANNOTATIONS.md](sap-lab/projects/04-rap-fiori-elements/ANNOTATIONS.md)

**No build pipeline** — manual code review and SAP system testing. Document changes in adjacent `NOTES.md` or `CODE_EXPLANATION.md`.

---

## 🐍 Python AI Learning (python-projects/ai-lab)

**Status:** Active learning path with NumPy, Pandas, ML (scikit-learn), deep learning foundations.

### Setup
```bash
cd python-projects/ai-lab
./setup.sh                    # Creates .venv, installs dependencies
source .venv/bin/activate
python 01_python_basics.py    # Start with basics
```

**Files:**
- `01_python_basics.py` — Core Python concepts
- `02_numpy_intro.py` — Array operations
- `06_ml_intro.py` — Machine learning algorithms
- [LEARNING_GUIDE.md](python-projects/ai-lab/LEARNING_GUIDE.md) — Structured curriculum
- [START_HERE.txt](python-projects/ai-lab/START_HERE.txt) — Entry point

---

## ☁️ LocalStack Lab (localstack-lab)

**Purpose:** Isolated AWS service emulation (S3, DynamoDB) without affecting other projects.

### Run
```bash
# Python
cd localstack-lab && ./scripts/bootstrap.sh
source .venv/bin/activate
python projects/hello-localstack/main.py

# Java (embedded localstack)
cd projects/hello-localstack-java
mvn clean package && mvn exec:java
```

**Endpoint:** `http://s3.localhost.localstack.cloud:4566` (override with `LOCALSTACK_ENDPOINT_URL`)

---

## ⚠️ Critical "DO NOTs"

- **Java project:** Don't use `spring.datasource.*` — use `db.*` prefix
- **Java project:** Don't modify Hibernate config without testing manual SessionFactory setup
- **All projects:** Don't add cross-project dependencies (each is standalone)
- **Python:** Don't install packages to global `venv` — use project-local `.venv`
- **Java project:** All endpoints return **JSP views**, not JSON REST responses
