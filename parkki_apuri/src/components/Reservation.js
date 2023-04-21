import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Reservation.css';

export default function Reservation () {

  let navigate = useNavigate();

  const [ parkingSpot, setParkingSpot ] = useState('');
  const [ licensePlate, setLicensePlate ] = useState('');

  // reserve ottaa parametrit idParkit sekä rekisteri ja postaa ne.
  const reserve = () => {
    const rekisteri = localStorage.getItem("rekisteri");
    axios({
        method: "put",
        data: {
            id: parkingSpot,
            rekisteri: rekisteri,
        },
        url: "https://localhost:4000/",
    })
    .then((res) => console.log(res));
    // localStorage.setItem("id", rekisteri.data[0].id);
    navigate('/')
};

  return (
      <div className="signupcsscontainer">
        <div className="signupcsstitleText">Varaukset</div>
        <div className="signupcsssubtitleText">Täytä tiedot ja tee varaus paikasta</div>
        <div>
            <input className="signupcsstextField1" placeholder="parkkipaikan numero*" onChange={e => setParkingSpot(e.target.value)} />
            {/* <input className="signupcsstextField3" placeholder="Rekisterinumero*" onChange={e => setLicensePlate(e.target.value)} /> */}
            <button onClick={reserve} className="signupcsssignUpButton">Tee varaus</button>
        </div>
      </div>
  )
}