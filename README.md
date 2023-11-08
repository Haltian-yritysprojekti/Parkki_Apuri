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

- Aloita aloitussivulta
- Valitse jokin parkkihalli etusivulla
- Selaa eri hallien autopaikkoja
- Luo käyttäjä
- Kirjaudu sisään käyttäjälle
- Muokkaa käyttäjätietoja
- Luo varaus halutusta paikasta halutulla ajalla
- Tarkista ongelmatilannesivu
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

- Android Studio
- Jotta puhelimeen voi asentaa sovellus, puhelimen on oltava debug-moodissa ja usb-yhteydessä tietokoneeseen. Sovellus asentuu kun se ajetaan Android Studion sisällä.
- Sovelluksen asennuksen jälkeen sovellusta voi käyttää kuten normaalia sovellusta.

## Teknologiat
- Kotlin-kieli

## Käytetyt android-kirjastot

- volley
- widget
- androidx
- kotlinx.coroutines
- java

## Toiminta 

- Sovellus käynnistyy kirjautumisnäkymään, josta voidaan siirtyä rekisteröintiin tai kirjautua sisään.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/534fd017-ca3e-4950-83dd-63fe442bade8" width="270" height="585">

- Käyttäjän valitessa rekisteröinnin, hän näkee rekisteröintisivun.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/345fd1c4-8c7a-4465-b9cf-0f4c7f06f693" width="270" height="585">

- Jos käyttäjä on jo rekisteröitynyt, hän kirjautuu sisään aikaisemmasta kirjautumisnäkymästä. Hän saapuu päänäkymään. Päänäkymässä näkyy alasvetovalikko, kaksi kuviota ja käyttäjän sähköposti. Päänäkymää voi päivittää vetämällä näyttöä alhaalta ylöspäin.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/4d69bb29-fb0e-4810-af0c-dd8e4e9b4b80" width="270" height="585">

- Käyttäjän valitessa parkkialueen alasvetovalikosta hänelle esitetään parkkipaikat valitusta parkkialueesta, paikat ovat eritelty varatuiksi, vapaiksi ja sähköpaikoiksi.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/79021fa9-d05f-4996-9892-7ce196f6fc59" width="270" height="585">

- Käyttäjän valitessa vapaan parkkipaikan, hänelle avautuu varausnäkymä.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/76a20a18-ba1f-433c-aa25-2dd96abb1c23" width="270" height="585">

- Käyttäjän tehdessä varauksen hän näkee varausvahvistuksen uudessa ikkunassa.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/4489e668-4b7f-4d4c-9f70-b792cccf48a1" width="270" height="585">

- Käyttäjä palaa päänäkymään josta hän voi valita kahden kuvion väliltä meneekö hän katsomaan hänen tehtyjä varauksia vai päivittämään käyttäjätietojaan. 
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/21b3098a-3fad-4ffb-a57d-7023537d5f4f" width="270" height="585">

- Tehtyjen varauksien näkymässä käyttäjä voi nähdä tekemänsä varaukset, ja palata päänäkymään.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/d210b290-36a8-4f76-a774-9fdfe8091990" width="270" height="585">

- Käyttäjätietojen päivitysnäkymässä käyttäjälle näytetään hänen sähköposti ja rekisterinumero. Käyttäjä voi vaihtaa omia tietojaan syöttämällä uuden sähköpostin, salasanan ja rekisterinumeron. Häneltä kysytään salasana varmistuksena siitä, että hän on salasanan haltija. Päänäkymään palatessa käyttäjän sähköpostitiedot päivittyvät päänäkymän sähköpostiosioon.
<img src="https://github.com/Haltian-yritysprojekti/Parkki_Apuri/assets/79058877/e6da08ec-8084-4719-8a01-58fbaa750b6d" width="270" height="585">



## Tekijät

Eliel Latvala & Lassi Tihinen

https://github.com/elielll & https://github.com/LassiTihinen

# Lassi Tihinen

- Muiden kuin Login ja Register aktiviteettien tyylitys
- MainActivity.kt korttinäkymässä näkyvät parkkipaikkatiedot ja niiden tyylitys
- EditUserActivity.kt - Käyttäjätietojen päivitys
- ReservationActivity.kt - Varauksien teko, varaukseen tarvittavien tietojen lähetys eri aktiviteeteista
- ReservationConfirm.kt - Varauksen tietojen varmistus
- ReservationsView.kt - Omien varauksien tarkastelu

# Eliel Latvala

- MainActivity.kt logiikka, alasvetovalikko
- LoginActivity.kt, tyylitys - Kirjautuminen
- ReservationsView.kt autettu logiikan kanssa
- RegisterActivity.kt, tyylitys - Rekisteröinti

-----------------------------
