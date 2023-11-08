# Parkki_Apuri

# React.js frontend-osio

Parkkiapuri -projektissa loimme neljän miehen ryhmässä web -ja mobiilisovellukset, jossa hyödynnettiin Haltianin sensoreita. Valitsimme tähän siten kaksi Beam -sensoria, joilla loimme parkkisovelluksen. Sovelluksessa voi tarkistaa eri sijainteja ja niiden autopaikkojen saatavutta. Websovelluksessa ensimmäinen sivu on aloitussivu (StartPage.js). Siitä pääsee kirjautumiseen (Login.js) ja rekisteröitymiseen (SignUp.js). Kirjautumisen jälkeen päästään etusivulle (Home.js), josta voi selata eri parkkihalleja, niiden parkkipaikkoja ja saatavuutta. Käyttäjätietojen muokkaussivulla (EditUser.js) pystyy muokkaamaan käyttäjän tietoja. Lopuksi, varaussivulta (Reservation.js) voi varata paikkoja halutulla ajalla. Lopussa kuvia websovelluksen eri osista selaimessa.

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

- StartPage.js: Aloitussivu.

![startpage](https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/78751694/b87034b8-a267-4076-95fa-c11cd3edb5de)


- Home.js: Sovelluksen pääsivu.

![home](https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/78751694/9c3aae45-e00d-478f-8cdd-68e7a266473b)


- Login.js: Käyttäjän kirjautumissivu.

![login](https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/78751694/b984aa74-51f6-4234-a430-a936af1cef9f)


- SignUp.js: Käyttäjän rekisteröitymissivu.
  
![signup](https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/78751694/06361953-e29d-49f4-8f31-6c7318281f8b)


- EditUser.js: Käyttäjätietojen muokkaussivu.

![edituser](https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/78751694/6764f11d-13a5-4d30-b261-b507b394449d)


- Reservation.js: Parkkipaikan varaussivu.

![reservation](https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/78751694/76eceeae-0afe-416f-8aa1-a9b61bf36135)


- UKK & Ongelmatilannesivu.

![ukk](https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/78751694/6c5f9a05-54c4-4390-b975-7d71ce807516)

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
