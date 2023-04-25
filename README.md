# Parkki_Apuri
\React.js frontend-osio alkaa/

Parkkiapuri -projektissa loimme neljän miehen ryhmässä web -ja mobiilisovellukset, jossa hyödynnettiin Haltianin sensoreita. Valitsimme tähän siten kaksi Beam -sensoria, joilla loimme parkkisovelluksen. Sovelluksessa voi tarkistaa eri sijainteja ja niiden autopaikkojen saatavutta. Web-pohjaisessa sovelluksessa pystyy lisäksi luoda käyttäjän (SignUp.js), kirjautua käyttäjälle (Login.js) ja varata paikkoja (Reservation.js). Lopussa kuvia websovelluksen eri osista selaimessa.


# Asennus

1. Kloonaa repositorio

    git clone https://github.com/Haltian-yritysprojekti/Parkki_Apuri.git

2. Asenna tarvittavat riippuvuudet

    npm install


# Käyttö

1. Käynnistä sovellus

    npm start

2. Avaa selain ja siirry osoitteeseen http://localhost:3000


# Toiminnot

- Valitse jokin kolmesta parkkihallista
- Selaa eri hallien autopaikkoja (10 per sijainti)
- Luo käyttäjä
- Kirjaudu sisään käyttäjälle
- Luo varaus halutusta paikasta
- Kirjaudu ulos käyttäjältä


# Teknologiat

- ReactJS
- HTML
- CSS

# Käytetyt React.js kirjastot

- Axios
- React-router-dom


# Kehitysympäristö

- Node.js versio 14.16.0
- Npm versio 6.14.11

# Tekijä

GitHub: https://github.com/Alenkaara


# Kuvia sovelluksen eri osista

- Home.js: Sovelluksen pääsivu.

![pa](https://user-images.githubusercontent.com/78751694/234013390-cec99419-1b9d-48cd-9a41-20da5e7ac669.png)

- Login.js: Käyttäjän kirjautumissivu.

![ks](https://user-images.githubusercontent.com/78751694/234013523-29fa54f6-4b4b-4ae7-b9a2-e9a96c7dc239.png)

- SignUp.js: Käyttäjän rekisteröitymissivu.

![rk](https://user-images.githubusercontent.com/78751694/234013582-47779849-151d-4d0a-9a82-b6b75ad8776a.png)

- Reservation.js: Parkkipaikan varaussivu.

![va](https://user-images.githubusercontent.com/78751694/234013654-ce179947-c300-4b55-915f-8febc7ff66c0.png)


\React.js frontend-osio loppuu/
------------------------------
\Rest API alkaa/

Rest API hallitsee liikenteen frontend applikaatioille tietokannasta ja päivittää uusia tietoa tietokantaan sensoreista saaduista viesteistä MQTT protokollaa käyttäen.
Tietokannasta tauluina löytyy parkkitalo, parkit, varaukset ja käyttäjä taulu. Parkkitalo sisältää tiedot parkkihalleista, parkit sisältävät tietoa parkki ruutujen tilanteesta ja siihen kuuluvasta sensorista, varaukset pitää sisällään varattujen paikkojen tietoja ja käyttäjä taulussa on rekisteröidyn käyttäjän tiedot.

# Asennus

1. Kloonaa repositorio

    git clone https://github.com/Haltian-yritysprojekti/Parkki_Apuri.git

2. Asenna tarvittavat riippuvuudet

    npm install
    
# Käyttö

1. Tarvittavat osat

    - Server kansioon lisättävä sertifikaatti ja avain
    - MQTT tiedostolle antaa tarvittavat sertifikaatit ja avaimet suojattuun MQTT yhdistämiseen
    - Luoda db.json tiedosto jossa tarvittavat käyttäjä ja salasana tietokannan yhdistämiseen

2. Käynnistäminen

    node server/www.js

3. Avaa selain ja siirry osoitteeseen https://localhost:3000

# Toiminnot

- Kontrolloi tietokantaa POSTMAN pyyntöjen avulla
- Hae parkkihallien tiedot ja avointen paikkojen määrä
- Hae käyttäjän varaukset rekisterillä
- Hae parkkihallin parkkien tila
- Varaa parkki ruutu
- Tarkistaa käyttämättömien varausten poistamisen
- Rekisteröi käyttäjä
- Kirjaudu sisään käyttäjällä

# Teknologiat

- JavaScript

# Käytetyt Node.js kirjastot

- Body-parser
- Cors
- Debug
- Express
- MQTT
- Mysql
- Nodemon

# Kehitysympäristö

- Node.js versio 14.15.4
- Npm versio 6.14.10

# Tekijä

GitHub: https://github.com/RamM21

\Node.js osio loppuu/
------------------------------
\Android-osio alkaa/

# Tarvittavat asiat

- Android Studio Code
- Android puhelin debug-moodissa ja usb-yhteydessä tietokoneeseen
- Puhelin ja kone saman LAN-yhteyden alla
- Asennusohjeet Rest-Api kohdasta tietokantayhteyttä varten

# Teknologiat
- Kotlin-kieli

# Käytetyt android-kirjastot

- com.android.volley
- javax.net.ssl
- android.widget
- android.view

# Toiminta (Mock data)

- Sovellus käynnistyy kolmen napin näkymään, jotka sisältävät API:lta luetut sijaintitiedot ja paikan tiedot.
![Menu](https://user-images.githubusercontent.com/79058877/234205460-85808ede-57c8-46a9-8d06-2bb3aed7afbf.jpg)
- Nappia painamalla alle tulee CardView-objecti, joka sisältää API:lta saadut parkkitiedot sijaintikohtaisesti
![A](https://user-images.githubusercontent.com/79058877/234205817-b7db1f93-df44-4d84-beba-5d2f7e4300ab.jpg)
- Muut napit katoavat painettaessa nappia, uudelleenpainallus ylimpään nappiin tuo takaisin menu-näkymän.

# Tekijät

Eliel Latvala & Lassi Tihinen

Lassi Tihinen

- Sovelluksen ulkoasun suunnittelu, nappien suunnittelu, parkkipaikkanäkymän suunnittelu, pääosin xml-tiedostot jotka muokkaavat sovellusnäkymää.
- Alkuperäinen mock-data versio josta siirryttiin nykyiseen näkymään

Eliel Latvala

- Sovelluksen logiikan ja datan käsittely, pääosin MainActivity.kt

\Android-osio loppuu/
------------------------------
