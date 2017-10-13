# tikapesmoothie

Tietokantojen perusteet -kurssilla tehty web-sovellus, jonka avulla voi suunnitella smoothieita.

#### Luokkakaavio

![Luokkakaavio](/Dokumentaatio/SmoothieLuokkakaavio.png "Luokkakaavio")

#### Tietokantakaavio

![Tietokantakaavio](/Dokumentaatio/SmoothieTietokantakaavio.png "Tietokantakaavio")

#### Kyselyt taulujen luomiseksi

	CREATE TABLE Kategoria (
		id integer PRIMARY KEY,
		nimi varchar(40)
	)
	
	CREATE TABLE RaakaAine (
		id integer PRIMARY KEY,
		nimi varchar(40),
		FOREIGN KEY kategoriaId REFERENCES Kategoria(id)
	)
	
	CREATE TABLE Resepti (
		id integer PRIMARY KEY,
		nimi varchar(40),
	)
	
	CREATE TABLE RaakaAineResepti (
		maara integer,
		FOREIGN KEY reseptiId REFERENCES Resepti(id),
		FOREIGN KEY raakaAineId REFERENCES RaajaAine(id)
	)