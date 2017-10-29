# tikapesmoothie

Tietokantojen perusteet -kurssilla tehty web-sovellus, jonka avulla voi suunnitella smoothieita.

#### Luokkakaavio

![Luokkakaavio](/Dokumentaatio/SmoothieLuokkakaavio.png "Luokkakaavio")

#### Tietokantakaavio

![Tietokantakaavio](/Dokumentaatio/SmoothieTietokantakaavio.png "Tietokantakaavio")

#### Kyselyt taulujen luomiseksi

	CREATE TABLE IF NOT EXISTS Kategoria (
		id integer PRIMARY KEY,
		nimi varchar(40)
	)
	
	CREATE TABLE IF NOT EXISTS RaakaAine (
		id integer PRIMARY KEY,
		kategoriaId integer,
		nimi varchar(40),
		FOREIGN KEY (kategoriaId) REFERENCES Kategoria(id)
	)
	
	CREATE TABLE IF NOT EXISTS Resepti (
		id integer PRIMARY KEY,
		nimi varchar(40),
	)
	
	CREATE TABLE IF NOT EXISTS RaakaAineResepti (
		maara varchar(50),
		reseptiId integer,
		raakaAineId integer,
		jarjestys integer,
		FOREIGN KEY (reseptiId) REFERENCES Resepti(id),
		FOREIGN KEY (raakaAineId) REFERENCES RaakaAine(id)
	)
