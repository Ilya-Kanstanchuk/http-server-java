# HttpServer

This is a simple HTTP server I built for learning purposes — to get familiar with socket programming in Java and how HTTP servers work in general. It allows clients to make `GET` and `POST` requests to two hosts, which are configurable via environment variables.

## Prerequisites

- **Java 21** (Maven wrapper included)

## Environment Variables

| Name             | Description                                                               |
| ---------------- | ------------------------------------------------------------------------- |
| `PORT_NUMBER`    | Port number the server will bind to                                       |
| `BACKLOG_NUMBER` | Max number of queued incoming connections                                 |
| `HOST1_NAME`     | First virtual host name                                                   |
| `HOST2_NAME`     | Second virtual host name                                                  |
| `FILE_PATH`      | Absolute path to a file where user information (from POST) will be stored |

## Routes

### Host 1 (`HOST1_NAME`)

#### `GET /`

Returns a simple main page in HTML format.

#### `POST /user`

- Accepts a JSON request body with: `name`, `surname`, and `status`.
- Appends the user information to the file specified by the `FILE_PATH` environment variable.

---

### Host 2 (`HOST2_NAME`)

#### `GET /random`

Returns an HTML page containing a randomly generated number.

---

## Features

- The server uses basic routing based on `Host`, `Method`, and `Path`.
- All responses are manually constructed HTTP/1.1 messages using Java sockets.
