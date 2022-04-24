import './App.css';
import LoginUser from './Login/user/LoginUser';
import { Routes, Route } from "react-router-dom";
import MyNavbar from './Navbar/MyNavbar';
import Container from 'react-bootstrap/esm/Container';
import React from 'react';

const IdentifyContext = React.createContext('GUESS');

function App() {
  return (
    <div>
      <MyNavbar />
      <Container>
        <Routes>
          <Route path="/login" element={<LoginUser />} />
        </Routes>
      </Container>
    </div>

  );
}

export default App;
