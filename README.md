## Boardgame collection tracker application

A command line tool to manage boardgame collection with persistent storage, using an embedded database.

## Features

* Manually add a boardgame with related data (type, best player count, playtime).
* **NEW -> Now supports importing users game collection from boardgamegeek.com (CSV support).** Check CSV_management branch. 
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
* Maven (dependency management).

## Prerequisites

* Library is prepopulated with sample data.
* It is recommended to clear the library before adding own games.
* To add BGG games to library, user must add relevant game type to boardgames comment section (see gameStyle enums for corrent naming).
  ![image](https://github.com/user-attachments/assets/92ec0a4d-dc3f-4a88-8626-1f9e30fcd92f)
* Sample CSV file is provided

## Roadmap

* **Currently working on :**
  
      1)   **Add player management (currently iterating on players_management branch)**
  
      2)   **Replace manual SQL with JPA/HIBERNATE.**
* Add filters when searching for a game.
* Add flexible game style possibility (multiple game styles, previously unetermined types)
* Migrate the project to Spring Boot for easier  dependencies management.
* Add frontend user interface.
