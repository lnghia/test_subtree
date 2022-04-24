import React, { useEffect, useState } from "react";
import Alert from 'react-bootstrap/Alert';

const LoginError = (props) => {
    const [show, setShow] = useState(props.show);

    if (show) {
        return (
            < Alert variant="danger" onClose={() => setShow(false)} dismissible>
                {
                    props.errorMessages.map(errorMsg => {
                        return (
                            <Alert.Heading key={errorMsg}>{errorMsg}</Alert.Heading>
                        );
                    })
                }

                {/* <p>
                    
                </p> */}
                {/* <hr />
                <p className="mb-0">
                    Whenever you need to, be sure to use margin utilities to keep things nice
                    and tidy.
                </p> */}
            </Alert >
        );
    }

    return <h1></h1>;
}

export default LoginError;