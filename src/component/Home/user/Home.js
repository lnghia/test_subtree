import React, { useState } from "react";
import Card from "react-bootstrap/Card";


const Home = (props) => {
    return (
        <div className="hero">
            <Card className="bg-dark text-white border-0">
                <Card.Img src="/assets/bg.jpg" alt="Background" />
                <Card.ImgOverlay className="d-flex flex-column">
                    <Card.Title className="display-3 fw-bolder mb-0">NEW SEASON ARRIVALS</Card.Title>
                    <Card.Text className="lead fs-2">
                        CHECK OUT ALL THE TRENDS.
                    </Card.Text>
                </Card.ImgOverlay>
            </Card>
            {/* <div className="card bg-dark text-white border-0">
                <img src="/assets/bg.jpg" className="card-img" alt="Background" />
                <div className="card-img-overlay">
                    <h5 className="card-title"> Card title</h5>
                    <p className="card-text">
                        This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.
                    </p>
                    <p className="card-text"> Last updated 3 mins ago</p>
                </div>
            </div> */}
        </div>
    );
}

export default Home;