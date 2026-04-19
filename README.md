# Journal App

A simple Spring Boot REST API for creating and managing journal entries with MongoDB.

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data MongoDB
- Maven

## Prerequisites

- Java 21 installed
- Maven (or use the included Maven wrapper)
- MongoDB running locally on `localhost:27017`

## Configuration

The app uses these MongoDB settings in `src/main/resources/application.properties`:

- Host: `localhost`
- Port: `27017`
- Database: `journaldb`

## Run the Application

Using Maven wrapper:

```bash
./mvnw spring-boot:run
```

On Windows (IntelliJ terminal):

```powershell
.\mvnw.cmd spring-boot:run
```

## API Endpoints

### Health Check

- `GET /health-check` → returns `OK`

### Journal Entries

- `GET /journal` → get all entries
- `POST /journal` → create entry
- `GET /journal/id/{myId}` → get entry by id
- `PUT /journal/id/{id}` → update entry
- `DELETE /journal/id/{myId}` → delete entry

## Example Create Request

`POST /journal`

```json
{
  "title": "My first note",
  "content": "Started building my journal app"
}
```

## Build and Test

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```
