import {useEffect, useState} from "react";
import axios from "axios";

export default function CheckingAuthenticated({children}){
    const [authenticated,setAuthenticated] = useState(null);

    useEffect(() => {
        axios.get("https://microservice-lypk.onrender.com/api/authentication/check", {withCredentials: true})
            .then(response => setAuthenticated(response.data));
    }, []);

    if (authenticated === null) {
        return <p>waiting...</p>
    }else if (authenticated){
        return children;
    }else {
        return window.location.href ="https://microservice-lypk.onrender.com/login/oauth2/code/google";
    }
}
