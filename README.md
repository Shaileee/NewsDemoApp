# NewsDemoApp
# MVI and Clean Architecture News Application

## Overview

This project is a sample application implementing **MVI Architecture** and **Clean Architecture**, along with **Jetpack Compose**, **Koin**, **Coroutines**, and **Navigation Component**. It fetches and displays the latest news for four companies (e.g., Microsoft, Apple, Google, Tesla) sequentially from the [News API](https://newsapi.org/). You need an API key from News API to make it functional.

---

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Architectures Used](#architectures-used)
   - [MVI Architecture](#mvi-architecture)
   - [Clean Architecture](#clean-architecture)
- [Technologies](#technologies)

---

## Introduction

The goal of this project is to demonstrate a clean, scalable, and testable architecture for an Android app. It follows best practices and utilizes modern Android development tools and libraries.

---

## Features

- Fetches and displays news headlines with images for four companies.
- Utilizes **MVI Architecture** for state management and unidirectional data flow.
- Implements **Clean Architecture** for separation of concerns.
- Modular structure to easily add new features.
- Supports dependency injection using **Koin**.
- Uses **Fresco** for image loading (can be swapped with Glide or Coil).
- Fully built with **Jetpack Compose** for a modern UI experience.

---

## Project Structure

- **presentation(companyinfo)**: Contains UI-related code built with Jetpack Compose.
- **Domain**: Contains business logic, use cases, and entities.
- **Data**: Responsible for interacting with external data sources (e.g., APIs, databases).

---

## Architectures Used

### MVI Architecture

Model-View-Intent (MVI) provides a unidirectional data flow to ensure a predictable state for the UI.

- **Intent**: Represents user actions or events.
- **ViewModel**: Processes intents, fetches data from the repository, and updates the state.
- **State**: Immutable recompanyinfo of the UI at any moment.
- **View**: Reacts to the state and renders the UI.

![MVI Architecture](https://github.com/Shaileee/NewsDemoApp/blob/main/News_Demo_App/media/MVI_detail.png?raw=true)

### Clean Architecture

Clean Architecture separates the code into layers to ensure modularity and maintainability.

- **Entities**: Business objects or data models.
- **Use Cases**: Contain business logic specific to the application.
- **Repositories**: Abstract data sources like APIs or local databases.
- **Frameworks & Drivers**: Interface with external tools and frameworks.

![Clean Architecture](https://github.com/Shaileee/NewsDemoApp/blob/main/News_Demo_App/media/Artboard_15587.png?raw=true)

---

## Technologies

- **Jetpack Compose**: Modern declarative UI toolkit for building Android apps.
- **Koin**: Lightweight dependency injection framework.
- **Coroutines**: For asynchronous programming and concurrency.
- **Fresco**: For efficient image loading and caching.
- **Navigation Component**: Handles in-app navigation.
- **Modularization**: Each feature is placed in a separate module for better scalability.
- **Unit tests**


