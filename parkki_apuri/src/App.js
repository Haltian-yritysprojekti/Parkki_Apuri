import React, { useState, useEffect } from 'react';
import * as ReactDOM from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import axios from 'axios';
// import parkkilogomuokattu from './parkkilogomuokattu.png';
import './App.css';
import Login from './components/Login';
import SignUp from './components/SignUp';
import Home from './components/Home';
import Reservation from './components/Reservation';

// Footer funktio on sivun alaosassa oleva palkki, mikä voi sisältää haluttavaa sisältöä, yleensä tietoa ja linkkejä.
// function Footer() {
//   return (
//     <footer style={{
//       background: "#30494a",
//       color: "#fff",
//       padding: "1rem",
//       display: "flex",
//       justifyContent: "center",
//       alignItems: "center",
//       height: "20px"
//     }}>
//       <div style={{
//         maxWidth: "800px",
//         margin: "0 auto",
//         textAlign: "center"
//       }}>
//         <p>Parkkiapuri &copy; {new Date().getFullYear()}</p>
//       </div>
//     </footer>
//   );
// }

function ParkingApp () {
  
  // const [ isLoggedIn, setIsLoggedIn ] = useState(false);
  // const [ info, setInfo ] = useState([]);
  // const [ parkingInfo, setParkingInfo ] = useState([]);
  // const [ activeParkingHall, setActiveParkingHall ] = useState(null);
  // const [ refreshInterval, setRefreshInterval ] = useState(null);
  // const [ reservations, setReservations ] = useState([]);

  // const getReservations = async () => {
  //   try {
  //     const response = await axios.get(`https://localhost:4000/res`);
  //     setReservations(response.data);
  //   } catch (error) {
  //     console.error(error);
  //   }
  // };

  // useEffect(() => {
  //   if (isLoggedIn==true) {
  //     getReservations();
  //   }
  // });

  // // Kirjautumisen yhteydessä tallentaa kirjautumistokenin paikallisesti
  // useEffect(() => {
  //   const token = localStorage.getItem('rekisteri');
  //   if (token) {
  //     setIsLoggedIn(true);
  //   }
  // }, []);

  // const handleLogout = () => {
  //   localStorage.removeItem('rekisteri');
  //   setIsLoggedIn(false);
  // };

  // // Suoritetaan sivun latautumisen yhteydessä
  // useEffect(() => {
  //   getInfo();
  // }, []);

  // useEffect(() => {
  //   if (activeParkingHall) {
  //     getParkingInfo(activeParkingHall);
  //     setRefreshInterval(setInterval(() => {
  //       getParkingInfo(activeParkingHall);
  //     }, 60000)); // Määrittää minuutin ajastimen, jonka jälkeen haku tehdään uudestaan itsestään parkkipaikoista.
  //   }
  //   return () => {
  //     clearInterval(refreshInterval);
  //   }
  // }, [activeParkingHall]);

  // // Getinfo hakee parkkihallien nimet ja vapaat paikat tietokannasta rest apin kautta.
  // const getInfo = async () => {
  //   try {
  //     const results = await axios.get('https://localhost:4000', {
  //       headers: {
  //         'Content-Type': 'application/json',
  //         // 'Authorization': 'Bearer ' + UserAuthContextValue.jwt  // Tämä voidaan ottaa käyttöön jos sivulla on kirjautuminen käytössä.
  //         }
  //     });

  //     setInfo(results.data);
  //   } catch (error) {
  //     console.error(error);
  //   }
  // };

  // // Getparkinginfo hakee tiedot parkkipaikoista, boolean arvona on joko true (vihreä/vapaa) tai false (punainen/käytössä).
  // const getParkingInfo = async (parkingHallName) => {
  //   try {
  //     setParkingInfo([]);

  //     const results = await axios.get(`https://localhost:4000/${parkingHallName}`, {
  //       headers: {
  //         'Content-Type': 'application/json',
  //         }
  //     });

  //     setParkingInfo(results.data);
  //     setActiveParkingHall(parkingHallName); // Asettaa aktiivisen parkkihallin
  //   } catch (error) {
  //     console.error(error);
  //   }
  // };

  return (
    <BrowserRouter>
      <div>
        <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/reservation" element={<Reservation />} />
          {/* Add more routes here */}
        </Routes>
      </div>
    </BrowserRouter>
  );
};

export default ParkingApp;