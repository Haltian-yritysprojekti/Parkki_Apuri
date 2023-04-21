import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './SignUp.css';

export default function Signup () {

  let navigate = useNavigate();

  const [ email, setEmail ] = useState('');
  const [ password, setPassword ] = useState('');
  const [ licensePlate, setLicensePlate ] = useState('');

  // Signup ottaa parametrit email, salasana ja rekisteri ja postaa ne.
  const signup = () => {
    axios({
        method: "post",
        data: {
            email: email,
            salasana: password,
            rekisteri: licensePlate,
        },
        url: "https://localhost:4000/user//",
    })
    .then((res) => console.log(res));
    navigate('/login')
};

  return (
      <div className="signupcsscontainer">
        <div className="signupcsstitleText">Rekisteröityminen</div>
        <div className="signupcsssubtitleText">Täyttämällä tiedot voit luoda käyttäjän</div>
        <div>
            <input className="signupcsstextField1" placeholder="sähköposti*" onChange={e => setEmail(e.target.value)} />
            <input className="signupcsstextField2" type="password" placeholder="salasana*" onChange={e => setPassword(e.target.value)} />
            <input className="signupcsstextField3" placeholder="Rekisterinumero*" onChange={e => setLicensePlate(e.target.value)} />
            <button onClick={signup} className="signupcsssignUpButton">Luo käyttäjä</button>
        </div>
      </div>
  )
}

/*
import React, { useState } from 'react';
import axios from 'axios';

const Signup = () => {
  const [ email, setEmail ] = useState('');
  const [ password, setPassword ] = useState('');
  const [ licensePlate, setLicensePlate ] = useState('');

  // Handlesubmit ottaa parametrit email, salasana ja rekisteri.
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('https://localhost:4000/user/', {
        email: email,
        salasana: password,
        rekisteri: licensePlate
      });
      console.log(response.data);
      window.location.reload(); // Kun käyttäjän tekeminen on onnistunut, sivu päivitetään automaattisesti.
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Email:
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
      </label>
      <br />
      <label>
        Password:
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </label>
      <br />
      <label>
        License Plate:
        <input type="text" value={licensePlate} onChange={(e) => setLicensePlate(e.target.value)} />
      </label>
      <br />
      <button type="submit">Signup</button>
    </form>
  );
};

export default Signup;
*/