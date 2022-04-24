import React, { useEffect, useState } from "react";
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/esm/Button";
import Container from 'react-bootstrap/Container';
import Col from "react-bootstrap/esm/Col";
import Row from 'react-bootstrap/Row';
import axios from 'axios';

import './LoginUser.css';

import LoginError from "../../LoginError/LoginError";

const LoginUser = (props) => {
    const [response, setResponse] = useState({});
    const [loginSuccess, setLoginSuccess] = useState(true);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState([]);

    function handleOnClick(event) {
        event.preventDefault();
        axios.post(`http://localhost:8080/api/auth/login`, {
            username: username,
            password: password
        })
            .then(response => {
                console.log(response.data);
                setResponse(response.data);
                if (response.status === 200) {
                    setLoginSuccess(true);
                    return;
                }
            }).catch(error => {
                let tmp = [];
                let errors = error.response.data.errors;
                setLoginSuccess(false);
                Object.keys(errors).forEach(key => {
                    tmp.push(errors[key]);
                });
                console.log(tmp);
                setErrors(tmp);
            });
    }

    function handleUsernameOnChange(event) {
        setUsername(event.target.value);
    }

    function handlePasswordOnChange(event) {
        setPassword(event.target.value);
    }

    return (
        <Container id="main-container" className="d-grid h-100">
            <LoginError show={!loginSuccess} errorMessages={errors} />
            <Form id="sign-in-form" className="text-center p-3 w-100" onSubmit={handleOnClick}>
                <img className="mb-4 bootstrap-logo"
                    src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg"
                    alt="Bootstrap 5" />
                <h1 className="mb-3 fs-3 fw-normal">Please sign in</h1>
                <Form.Group controlId="sign-in-email-address" className="mb-3">
                    <Form.Control name="username" value={username} onChange={handleUsernameOnChange} type="email" size="lg" placeholder="Email address" autoComplete="username" className="position-relative" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="sign-in-password">
                    <Form.Control name="password" value={password} onChange={handlePasswordOnChange} type="password" size="lg" placeholder="Password" autoComplete="current-password" className="position-relative" />
                </Form.Group>
                <Form.Group className="d-flex justify-content-center mb-4" controlId="forgot-password">
                    <a href="abc">Forgot password?</a>
                </Form.Group>
                <div className="d-grid">
                    <Button type="submit" variant="primary" size="lg">Sign in</Button>
                </div>
            </Form>
        </Container>
    )
}

export default LoginUser;