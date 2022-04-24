import React, { useState } from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from 'react-bootstrap/Nav';
import Container from "react-bootstrap/esm/Container";
import NavDropdown from "react-bootstrap/NavDropdown";
import Form from "react-bootstrap/Form";
import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";

import './MyNavbar.css';

const MyNavbar = (props) => {
    const IdentifyContext = React.useContext('IdentifyContext');

    return (
        <Navbar bg="white" expand="lg" className="py-3 shadow-sm">
            <Container>
                <Navbar.Brand href="#" className="fw-bold fs-4 navbar-brand">Woohoo!</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="mx-auto my-2 my-lg-0"
                        style={{ maxHeight: '100px' }}
                        navbarScroll
                    >
                        <Nav.Link href="#action1" className="navbar-nav-a">Home</Nav.Link>
                        <NavDropdown title="Products" id="navbarScrollingDropdown" className="navbar-nav-a">
                            <NavDropdown.Item href="#action3">Action</NavDropdown.Item>
                            <NavDropdown.Item href="#action4">Another action</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="#action5">
                                Products
                            </NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link href="#action2" className="navbar-nav-a">About</Nav.Link>
                        <Nav.Link href="#action2" className="navbar-nav-a">Contact</Nav.Link>
                    </Nav>
                    {/* <Form className="d-flex">
                        <FormControl
                            type="search"
                            placeholder="Search"
                            className="me-2"
                            aria-label="Search"
                        />
                        <Button variant="outline-success">Search</Button>
                    </Form> */}
                    <div>
                        <a href="" className="btn btn-outline-dark login-btn">
                            <i className="fa fa-sign-in me-1"></i> Login
                        </a>
                        <a href="" className="btn btn-outline-dark login-btn ms-2">
                            <i className="fa fa-user-plus me-1"></i> Register
                        </a>
                        <a href="" className="btn btn-outline-dark login-btn ms-2">
                            <i className="fa fa-shopping-cart me-1"></i> Cart
                        </a>
                    </div>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default MyNavbar;