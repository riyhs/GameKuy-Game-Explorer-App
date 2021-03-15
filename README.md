# GameKuy-Game-Explorer-App

<h1 align="center">
  Submission 2 Dicoding MADE : GameKuy
</h1>
<p align="center">
  Submission 2 MADE (Menjadi Android Developer Expert) Dicoding
</p>
<p align="center">
  <a href="http://developer.android.com/index.html"><img alt="Platform" src="https://img.shields.io/badge/platform-Android-green.svg"></a>
  <a href="http://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/kotlin-1.4.31-blue.svg"></a>
  <a href="https://developer.android.com/studio/releases/gradle-plugin"><img alt="Gradle" src="https://img.shields.io/badge/gradle-4.1.2-green.svg"></a>
  <a href="https://github.com/riyhs/GameKuy-Game-Explorer-App/"><img alt="Star" src="https://img.shields.io/github/stars/riyhs/GameKuy-Game-Explorer-App"></a>
  <a href="https://github.com/riyhs/GameKuy-Game-Explorer-App/"><img alt="Circle CI" src="https://circleci.com/gh/riyhs/GameKuy-Game-Explorer-App.svg?style=shield"></a>
</p>

## Table of Contents
- [Introduction](#introduction)
- [Installation](#installation)
- [Demo](#demo)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Dependencies](#dependencies)

## Introduction

GameKuy Android App, build with Kotlin due to finish Dicoding Menjadi Android Developer Expert (MADE) Final Submission. I'm using [api.rawg.io](https://api.rawg.io/docs/) for the data source, and my design inspirated from [rawg.io](https://rawg.io/). 

I Hope this Project will help someone, if you feel helped with this project, you can give stars to support me, thank you very much :), 
And also thanks to [Adithya](https://github.com/Adithya-13/MadeSubmsission) for allowing me using his readme template, it was very helpful :))

## Installation

Clone or Download and Open it into Android Studio for Submission 2
```
    https://github.com/riyhs/GameKuy-Game-Explorer-App.git
```  

You can use the [DownGit](https://downgit.github.io/) for download Submission 1 separately, Copy this

```
    https://github.com/riyhs/GameKuy-Game-Explorer-App/tree/submission-1
```

and Paste it into DownGit, after that click Download Button, and voila, you can clone the code.


## Demo

|Splashscreen|All Games|
|--|--|
|![](assets/splash.webp?raw=true)|![](assets/all-games.webp?raw=true)|

|Detail Games|Explore Games|Favorite Games|
|--|--|--|
|![](assets/detail-game.webp?raw=true)|![](assets/explore-games.webp?raw=true)|![](assets/favorite-games.webp?raw=true)|

## Features
- Splash Screen
- Get All Games
- Explore Games
- Add game to favorite

## Tech Stack
- MVVM (Model-View-ViewModel) Architecture Pattern
- Modularization (core module)
- Dynamic Feature (favorite module)
- Clean Architecture (data, domain, presentation)
- Dependency Injection with Dagger Hilt
- Coroutines Flow
- ViewBinding
- Room Persistence
- Obfuscation with Proguard
- Encryption with SQLCipher
- Certificate Pinning with OkHttp
- Continuous Integration with Circle-CI

## Dependencies
- [Glide](https://github.com/bumptech/glide)
- [Lottie](https://github.com/airbnb/lottie-android)
- [AndroidX](https://mvnrepository.com/artifact/androidx)
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Lifecycle & LiveData](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Retrofit](https://square.github.io/retrofit/)
- [Coroutines Flow](https://developer.android.com/kotlin/flow)
- [Room](https://developer.android.com/training/data-storage/room?gclid=Cj0KCQiA0MD_BRCTARIsADXoopYlw1cozWjwyR-ucLYaaoqYlZeJmxG34JnhByjApMNwuchOcAzcy0aAgGHEALw_wcB&gclsrc=aw.ds)
- [SQLCipher](https://github.com/sqlcipher/sqlcipher)
- [SQLite](https://developer.android.com/jetpack/androidx/releases/sqlite)
- [Leak Canary](https://github.com/square/leakcanary)
