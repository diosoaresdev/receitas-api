# Receitas API

API REST para gerenciamento de receitas culinárias, desenvolvida com Java 21 e Spring Boot.

## Tecnologias

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL 16
- Docker / Docker Compose
- Lombok
- Maven

## Como rodar

### Pré-requisitos
- Java 21
- Docker

### 1. Subir o banco de dados
```bash
docker compose up -d
```

### 2. Rodar a aplicação
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Endpoints

### Categorias
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/categorias` | Lista todas as categorias |
| POST | `/categorias` | Cria uma categoria |
| DELETE | `/categorias/{id}` | Remove uma categoria |

### Ingredientes
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/ingredientes` | Lista todos os ingredientes |
| POST | `/ingredientes` | Cria um ingrediente |
| DELETE | `/ingredientes/{id}` | Remove um ingrediente |

### Receitas
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/receitas` | Lista receitas com filtros e paginação |
| GET | `/receitas/{id}` | Busca receita por ID |
| POST | `/receitas` | Cria uma receita |
| DELETE | `/receitas/{id}` | Remove uma receita |
| GET | `/receitas/{id}/porcoes?qtd=8` | Recalcula ingredientes por porção |

### Filtros disponíveis em GET /receitas
| Parâmetro | Tipo | Exemplo |
|-----------|------|---------|
| `categoria` | String | `?categoria=Italiana` |
| `nivel` | Enum | `?nivel=FACIL` |
| `maxTempo` | Integer | `?maxTempo=30` |
| `titulo` | String | `?titulo=risoto` |
| `page` | Integer | `?page=0` |
| `size` | Integer | `?size=10` |

### Exemplo de criação de receita
```json
{
    "titulo": "Massa fresca caseira",
    "descricao": "Massa fresca tradicional italiana",
    "porcoes": 4,
    "tempoPreparo": 45,
    "nivel": "MEDIO",
    "categoriaId": "uuid-da-categoria",
    "ingredientes": [
        {
            "ingredienteId": "uuid-do-ingrediente",
            "quantidade": 300.0
        }
    ]
}
```

## Configuração do banco

As credenciais padrão estão no `docker-compose.yml`:

| Campo | Valor |
|-------|-------|
| Host | localhost |
| Porta | 5433 |
| Banco | receitas-db |
| Usuário | receitas |
| Senha | receitas123 |