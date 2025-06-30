import {useState} from "react";
import axios from "axios";
import "../styles/MainPage.css";

export default function MainPage() {
    const [email, setEmail] = useState("");

    const getEmail = () => {
        axios.get("https://microservice-lypk.onrender.com/api/test", {withCredentials: true})
            .then(res => setEmail(res.data));
    }
    return (
        <div>
            <p className={"resttext"}>its a simple REST API request</p>
            <div className="restbtn">
                <button onClick={getEmail}>Click to get your email</button>
            </div>
            <p>your email : {email}</p>
            {email && <div className={"nextbtn"}>
                <button onClick={() => window.location.href="/sse"}>go to the next</button>
            </div>}
        </div>
    )
}