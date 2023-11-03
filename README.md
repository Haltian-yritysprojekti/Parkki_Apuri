# Parkki_Apuri

# React.js frontend-osio

Parkkiapuri -projektissa loimme neljän miehen ryhmässä web -ja mobiilisovellukset, jossa hyödynnettiin Haltianin sensoreita. Valitsimme tähän siten kaksi Beam -sensoria, joilla loimme parkkisovelluksen. Sovelluksessa voi tarkistaa eri sijainteja ja niiden autopaikkojen saatavutta. Web-pohjaisessa sovelluksessa pystyy lisäksi luoda käyttäjän (SignUp.js), kirjautua käyttäjälle (Login.js) ja varata paikkoja (Reservation.js). Lopussa kuvia websovelluksen eri osista selaimessa.

## Web-sovelluksen suora linkki
https://parkkiapuri.onrender.com/


## Asennus

1. Kloonaa repositorio

    git clone https://github.com/Haltian-yritysprojekti/Parkki_Apuri.git

2. Asenna tarvittavat riippuvuudet

    npm install


## Käyttö

1. Käynnistä sovellus

    npm start

2. Avaa selain ja siirry osoitteeseen http://localhost:3000


## Toiminnot

- Valitse jokin kolmesta parkkihallista
- Selaa eri hallien autopaikkoja (10 per sijainti)
- Luo käyttäjä
- Kirjaudu sisään käyttäjälle
- Luo varaus halutusta paikasta
- Kirjaudu ulos käyttäjältä


## Teknologiat

- ReactJS
- HTML
- CSS

## Käytetyt React.js kirjastot

- Axios
- React-router-dom


## Kehitysympäristö

- Node.js versio 14.16.0
- Npm versio 6.14.11

## Tekijä

Aleksi Lehtola. GitHub: https://github.com/Alenkaara


## Kuvia sovelluksen eri osista

- Home.js: Sovelluksen pääsivu.

![pa](https://user-images.githubusercontent.com/78751694/234013390-cec99419-1b9d-48cd-9a41-20da5e7ac669.png)

- Login.js: Käyttäjän kirjautumissivu.

![ks](https://user-images.githubusercontent.com/78751694/234013523-29fa54f6-4b4b-4ae7-b9a2-e9a96c7dc239.png)

- SignUp.js: Käyttäjän rekisteröitymissivu.

![rk](https://user-images.githubusercontent.com/78751694/234013582-47779849-151d-4d0a-9a82-b6b75ad8776a.png)

- Reservation.js: Parkkipaikan varaussivu.

![va](https://user-images.githubusercontent.com/78751694/234013654-ce179947-c300-4b55-915f-8febc7ff66c0.png)


------------------------------
# Rest API osio

Rest API kansiossa MQTT Client hakee sensorien datat, cloud funktiot jotka hakevat ja muokkaavat tietokannan tiedot ja SQL model tauluista johon tiedot tallennetaan.

## Asennus

1. Kloonaa repositorio

    git clone https://github.com/Haltian-yritysprojekti/Parkki_Apuri.git

2. Asenna tarvittavat riippuvuudet

    - siirry REST API kansioon
    - npm install

3. Lisää jokainen Cloud funktio omaan IBM funktioihin ja lisää web toiminnallisuus niille

4. Luo SQL tietokanta cloudiin tai locaalisti annetulla model tiedostolla
    
## Käyttö

1. Tarvittavat osat

    - MQTT tiedostolle antaa tarvittavat sertifikaatit ja avaimet suojattuun MQTT yhdistämiseen
    - Luoda db.json tiedosto jossa tarvittavat käyttäjä ja salasana tietokannan yhdistämiseen
    - muuttaa tarvittavat tietokannan, email ja IBM bucket tiedot cloud funktioihin

2. Käynnistäminen

    - aja tietokanta cloudissa tai locaalisti
    - aja MQTT client cloudissa tai locaalisti komennolla node mqtt.js

3. Testaa IBM Cloud funktiot selaimessa tai POSTMAN sovelluksessa

## Toiminnot

- Kontrolloi tietokantaa POSTMAN pyyntöjen avulla
- Hae parkkihallien tiedot ja avointen paikkojen määrä
- Hae käyttäjän varaukset
- Hae parkkihallin parkkien tila
- Varaa parkki ruutu tietylle aika välille
- Varauksien ajan seuranta automaattisesti
- Vapautta varatun ajan
- Admin poistaa, muuttaa, hakee ja lisää uusia paikkoja, parkkeja, varauksia
- Rekisteröi käyttäjä
- Lisää kuva käyttäjälle valmiiksi olevista kuvista
- Muuta käyttäjän tietoja
- Kirjaudu sisään käyttäjällä tai adminilla
- 2FA kirjautuminen

## Teknologiat

- JavaScript
- Node.js
- SQL
- IBM Cloud funktiot
- IBM Cloud Bucket
- AWS SQL tietokanta

## Käytetyt Node.js kirjastot

- Debug
- Express
- MQTT
- Mysql
- Nodemon

## Kehitysympäristö

- Node.js versio 14.15.4
- Npm versio 6.14.10

## Tekijä

GitHub: https://github.com/RamM21


------------------------------
# Android-osio

## Tarvittavat asiat

- Android Studio Code
- Android puhelin debug-moodissa ja usb-yhteydessä tietokoneeseen
- Puhelin ja kone saman LAN-yhteyden alla
- Asennusohjeet Rest-Api kohdasta tietokantayhteyttä varten

## Teknologiat
- Kotlin-kieli

## Käytetyt android-kirjastot

- com.android.volley
- javax.net.ssl
- android.widget
- android.view

## Toiminta (Mock data)

- Sovellus käynnistyy kolmen napin näkymään, jotka sisältävät API:lta luetut sijaintitiedot ja paikan tiedot.
![Menu](https://user-images.githubusercontent.com/79058877/234205460-85808ede-57c8-46a9-8d06-2bb3aed7afbf.jpg)
- Nappia painamalla alle tulee CardView-objecti, joka sisältää API:lta saadut parkkitiedot sijaintikohtaisesti
![A](https://user-images.githubusercontent.com/79058877/234205817-b7db1f93-df44-4d84-beba-5d2f7e4300ab.jpg)
- Muut napit katoavat painettaessa nappia, uudelleenpainallus ylimpään nappiin tuo takaisin menu-näkymän.
- Näkymän voi päivittää vetämällä ylhäältä alaspäin.

## Tekijät

Eliel Latvala & Lassi Tihinen

Lassi Tihinen

- Sovelluksen ulkoasun suunnittelu, nappien suunnittelu, parkkipaikkanäkymän suunnittelu, pääosin xml-tiedostot jotka muokkaavat sovellusnäkymää.
- Alkuperäinen mock-data versio josta siirryttiin nykyiseen näkymään

Eliel Latvala

- Sovelluksen logiikan ja datan käsittely, pääosin MainActivity.kt

-----------------------------
