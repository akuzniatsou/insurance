# Insurance
-

> prepare database
```
:\> gradlew clean build init
```

> start database

```
:\> "C:\workspace\insurance\build\postgres\pgsql\bin\pg_ctl" -D "C:\workspace\insurance\build\postgres\pgsql\data" -l logfile start
```

> create database

`
:\> gradlew cD cDT iDT
`
OR
`
:\> gradlew initDatabaseTables
`

> run application

```
:\> gradlew run
```

> stop database

```
:\> "C:\workspace\insurance\build\postgres\pgsql\bin\pg_ctl" -D "C:\workspace\insurance\build\postgres\pgsql\data" -l logfile stop
```


> Start from BAT

```
:\> gradlew clean build
:\> unzip build/distributions/insurance.zip -d build/distributions
:\> cd build/distributions/insurance/bin/
:\> insurance.bat
```

