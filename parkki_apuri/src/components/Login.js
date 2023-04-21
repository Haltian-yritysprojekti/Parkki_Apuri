import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Login.css';

const Login = ({ setIsLoggedIn }) => {

  let navigate = useNavigate();

  const [ email, setEmail ] = useState("");
  const [ password, setPassword ] = useState("");

  const [ loginProcessState, setLoginProcessState ] = useState("idle");

  // HandleLogin ottaa parametreinä email ja salasana. Kun post tehdään, se syöttää ne rest apin kautta tietokantaan ja kirjautuu sisään sivulle.
  // const handleLogin = async () => {
  //   try {
  //     const response = await axios.post(
  //       "https://localhost:4000/user/login",
  //       {
  //         email: email,
  //         salasana: password,
  //       }
  //     );
  
  //     if (response.status === 200) {
  //       setIsLoggedIn(true);
  //       if (response.data.rekisteri) {
  //         localStorage.setItem('rekisteri', response.data.rekisteri);
  //         navigate("/home", { replace: true });
  //       }
  //       // window.location.reload(); // Kun kirjautuminen on onnistunut, sivu päivitetään automaattisesti.
  //     }
  //   } catch (error) {
  //     console.log(error);
  //   }
  // };

  const onSubmit = async (event) => {
    event.preventDefault();
    setLoginProcessState("processing");
    try {
      const result = await axios.post('https://localhost:4000/user/login', {
          email: event.target.email.value,
          salasana: event.target.password.value
        }
      );
      console.log(result);
      console.log(result.data[0]);
      setLoginProcessState("success");
      setTimeout(() => {
        setLoginProcessState("idle")
        localStorage.setItem("rekisteri", result.data[0].rekisteri);
        localStorage.setItem("email", result.data[0].email);
        navigate("/", { replace: true });
      }, 1500);
    } catch (error) {
      console.error(error.message);
      setLoginProcessState("error");
      setTimeout(() => setLoginProcessState("idle"), 1500);
    }
  }

  let loginUIControls = null;
  switch(loginProcessState) {
    case "idle":
      loginUIControls = <button className="logincsssignInButton" type="submit">Kirjaudu sisään</button>
      break;

    case "processing":
      loginUIControls = <span style={{color: 'blue'}}>Prosessoidaan...</span>
      break;

    case "success":
      loginUIControls = <span style={{color: 'green'}}>Kirjautuminen onnistui</span>
      break;

    case "error":
      loginUIControls = <span style={{color: 'red'}}>Virhe</span>
      break;

    default:
      loginUIControls = <button className="logincsssignInButton" type="submit">Kirjaudu sisään</button>
  }

  return (
    <div>
      <div className="logincssheaderContainer">
          <div className="logincsscontainer">
              <div className="logincsstitleText">Kirjaudu sisään</div>
              <div className="logincsssubtitleText">Syötä kirjautumistietosi</div>
              <form onSubmit={ onSubmit }>
                  <div className="logincssusernameText">Sähköposti*<input className="logincssusernameField" type="text" name="email"/></div>
                  <div className="logincsspasswordText">Salasana*<input className="logincsspasswordField" type="password" name="password"/></div>
                  <div><button className="logincsssignInButton" type="submit">Kirjaudu sisään</button> </div>
              </form>
          </div>
      </div>
    </div>
  )
};
  
  export default Login;

/*
import React, { useState } from 'react';
import axios from 'axios';
import './Login.css';

const Login = ({ setIsLoggedIn }) => {
    const [ email, setEmail ] = useState("");
    const [ password, setPassword ] = useState("");
    const [ loginProcessState, setLoginProcessState ] = useState("idle");

    // HandleLogin ottaa parametreinä email ja salasana. Kun post tehdään, se syöttää ne rest apin kautta tietokantaan ja kirjautuu sisään sivulle.
    const handleLogin = async () => {
      setLoginProcessState("processing");
      try {
        const response = await axios.post(
          "https://localhost:4000/user/login",
          {
            email: email,
            salasana: password,
          }
        );
  
        if (response.status === 200) {
          setLoginProcessState("success");
          localStorage.setItem("token", response.data.token); // Store the token in localStorage
          setIsLoggedIn(true);
          window.location.reload(); // Kun kirjautuminen on onnistunut, sivu päivitetään automaattisesti.
        }
      } catch (error) {
        setLoginProcessState("error");
        console.log(error);
      }
    };

    let loginUIControls = null;
    switch(loginProcessState) {
      case "idle":
        loginUIControls = <button className="logincsssignInButton" type="submit">Kirjaudu sisään</button>
        break;
  
      case "processing":
        loginUIControls = <span style={{color: 'blue'}}>Prosessoidaan...</span>
        break;
  
      case "success":
        loginUIControls = <span style={{color: 'green'}}>Kirjautuminen onnistui</span>
        break;
  
      case "error":
        loginUIControls = <span style={{color: 'red'}}>Virhe</span>
        break;
  
      default:
        loginUIControls = <button className="logincsssignInButton" type="submit">Kirjaudu sisään</button>
    }
  
    return (
      <div className='logincssheadercontainer'>
        <div className='logincsscontainer'>
        <form onSubmit={(e) => e.preventDefault()}>
          <div>
            <label>Sähköposti</label>
            <input
              type="email"
              placeholder="Syötä sähköposti"
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div>
            <label>Salasana</label>
            <input
              type="password"
              placeholder="Syötä salasana"
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button onClick={handleLogin}>Login</button>
        </form>
        </div>
      </div>
    );
  };
  
  export default Login;
*/