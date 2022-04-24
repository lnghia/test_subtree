import './App.css';
import LoginUser from './Login/user/LoginUser';
import Home from './Home/user/Home';
import { Routes, Route } from "react-router-dom";
import MyNavbar from './Navbar/MyNavbar';
import Container from 'react-bootstrap/esm/Container';
import React from 'react';

const IdentifyContext = React.createContext('GUESS');

function App() {
  return (
    <div>
      <MyNavbar />
      <Routes>
        <Route path="/login" element={<LoginUser />} />
        <Route path="" element={<Home />} />
      </Routes>
    </div>

  );
}

export default App;
