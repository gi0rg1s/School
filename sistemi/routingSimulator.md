# Graphical Router Simulator

A web application that simulates the behavior of an IP router and its Routing Table, built with PHP (backend), MySQL and JavaScript/HTML/CSS (graphical interface).
It is available at the following URL: [https://giorgia.dedeho.cloud/routingSimulator/](https://giorgia.dedeho.cloud/routingSimulator/)

This is a space that my PCTO tutor gave me to experiment with my personal projects, and I decided to use it for this homework in order to demonstrate my knowledge of routing and web development, skills that I achieved during the PCTO experience.

## Features

- Routers list and creation of new routers
- Routing table for each router
- Insert and delete routes
- Manage network interfaces
- Routing simulation using the Longest Prefix Match algorithm
- Intelligent choice of the next hop, based on the netMask, metric and the status of th einterfaces
- Routes whose next hop is unreachable (interface down) are automatically excluded from the simulation
- Multi-router simulation: hop by hop routing through multiple routers in order to reach the specified destination IP
- Export and import route configuration with JSON files

## Architecture

The project follows Object-Oriented Programming principles, keeping three responsibilities clearly separated:

**Graphical interface** — HTML/CSS/JavaScript (`html/`, `css/`, `js/`). It never contains routing logic: it only collects user input, calls the PHP endpoints via `fetch()`, and displays the JSON results it receives.
It also works with the different types of requests to the backend and handles the responses.

**Routing logic** — PHP classes in `classes/`:
- `Route` — represents a single entry in the Routing Table. It can check itself against a destination IP and compare itself with another route to decide which one is preferable according to Long Prefix Match and metric.
- `TDI` — holds the routes of a router. It only stores and filters, it never decides anything.
- `Router` — uses the TDI to decide which route to apply, applying the Longest Prefix Match.
- `NetworkInterface` — represents a router's network interface.
- `Path` — accumulates the sequence of hops crossed during a multi-router simulation.

**Data persistence** — `*Repository` classes, the only part of the project that talks to the MySQL database through PDO/mysqli. No other class runs queries directly: if the storage system changed in the future , only this layer would need to change. Also the connection.php file is the only one that contains the database connection credentials, so it can be easily changed without touching any other file.

## Database schema

- **routers**(id, name)
- **interfaces**(id, router_id, name, ip, mask, status)
- **routes**(id, router_id, network, mask, next_hop, metric)

Each interface and route is linked to a router through `router_id` (foreign key with `ON DELETE CASCADE`) and once a router is deleted, so eveery interface and route linked will be deleted as well.

## Data structures

The Router is represented as a simple array of `Route` objects, linearly filtered at every simulation. For the typical scale of a network simulated in an educational context

## Possible future evolutions

- Packet simulation with TTL and explicit routing loop detection
- Support for dynamic routing protocols instead of static route configuration only
