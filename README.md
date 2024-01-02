# inzynierka_back
Komputerowe wspomaganie analizy tekstu backend.

# Baza danych
### Stawianie samego postgresa
* wejść do scripts/postgres
* odpalić `docker-compose up -d`. Baza będzie się uruchamiała automatycznie także po ponownym uruchomieniu komupera.
* żeby zatrzymać wydać komendę `docker-compose down`
### Stawianie Firebirda DOM
1. Skopiować pliki baz danych: `ARISCO.GDB` i `GFM.GDB` do `scripts/firebird_dom`
2. Zbudować obraz dockerowy: w katalogu `scripts/firebird_dom` wydać polecenie `docker-compose build`
3. Uruchomić: `docker-compose up -d`
### Reset bazy danych
Żeby zresetować bazę danych:
```
docker-compose down
docker volume rm postgres_db-data
docker-compose up -d
```
Powershell: `docker-compose down; docker volume rm postgres_db-data; docker-compose up -d`
### Zmiany w bazie danych
W celu wprowadzenia zmian w bazie danych należy dodać plik migracji. Znajdują się one w
folderze `resources/db/migration`
.
Kolejne migracje należy nazywać według
klucza: https://flywaydb.org/documentation/concepts/migrations#sql-based-migrations
Sodaris przyjmujemy że na początku migracji powinien być pojedynczy numer migracji (bez kropek)
## Kopie zapasowe
### Dump postgresa
Polecenie przystosowane do basha:
```docker exec -t <cointainer_name> pg_dump -U postgres -c -b pwo_db > dump_`date +%d-%m-%Y"_"%H_%M_%S`.sql```
Polecenie przystosowane do cmd:
- to samo co wyżej tylko plik trzeba inaczej nazwać
  Odtwarzanie dumpa:
1. Przekopiować dumpa do dockera
2. Wgrać dumpa poleceniem: `psql -q -U postgres -d pwo_db -f <nazwa_backupu.sql>`
   Oneliner dla basha: `cat ./<nazwa_dumpa.sql> | docker exec -i <nazwa_kontenera> psql -q -U postgres -d pwo_db`
### Dump z chmury
Dumpa robi się standardowo. Parametr -h określa hosta
`pg_dump -h <sql_ip> -U postgres -c -b -f 07042022_sodaris_demo_backup.sql pwo_db_demo`
`docker cp <contianer_id>:07042022_sodaris_demo_backup.sql ./`
### Tworzenie afterMigrate.sql
zrobić dumpa z parametrem --column-inserts
1. Wejść do kontenera z dockerem:`docker ps` i `docker exec -it <container_id> bash`
2. Tworzenie dumpa danych: `pg_dump --column-inserts --data-only -U postgres -b pwo_db > my_dump.sql`
3. Kopiowanie z dockera: `docker cp <container_id>:/my_dump.sql .\my_dump.sql`
   Przekopiować dane z dumpa do skryptu `afterMigrate.sql` pomiędzy polecenia `SET session_replication_role = 'replica';`,
   a `SET session_replication_role = 'origin';`
   Należyd dodać nazwy nowych tablic pod TRUNCATE.
   Należy usunąć wiersze dotyczące tabeli flyway_schema_history.
   Poza wykorzystaniem Flywaya dane można także wgrać na serwer
   psqlem: `psql -h 127.0.0.1 -U postgres -d pwo_db -f afterMigrate.sql -q`