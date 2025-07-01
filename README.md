## Boardgame collection tracker application

A command line tool to manage boardgame collection with persistent storage, using an embedded database.

## Features

* Add a boardgame with related data (type, best player count, playtime).
* Register gameplays.
* List all games in collection.
* Choose a random game to play from collection.
* Delete a single game / whole library.
* Persistent storage - library is saved between sessions.

## Technologies used

* Java 21.
* SQL.
* JDBC.
* H2 embedded database.
* HikariCP (for connection pooling).
* Lombok (for reducing boilerplate code).
* Maven.

## Prerequisites

* Library is prepopulated with sample data.
* It is recommended to clear the library before adding own games.

## Roadmap

* Add filters when searching for a game.
* Migrate the project to Spring Boot for managing dependencies.
* Replace manual SQL with JPA/HIBERNATE.
* Add frontend user interface.
