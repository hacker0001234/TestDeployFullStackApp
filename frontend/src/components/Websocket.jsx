import "../styles/Websocket.css";
import {useEffect, useRef, useState} from "react";
import axios from "axios";

export default function Websocket() {
    const [email, setEmail] = useState("");
    const websocket = useRef(null);
    const [messages, setMessages] = useState([]);
    const [message, setMessage] = useState("");
    const [correctMessage, setCorrectMessage] = useState(false);

    useEffect(() => {
        axios.get("https://testdeployfullstackapp.onrender.com/api/test", {withCredentials: true})
            .then(res => setEmail(res.data));
    }, []);

    useEffect(() => {
        if (!email) return;

        websocket.current = new WebSocket(`wss://testdeployfullstackapp.onrender.com/ws/message?phone=${email}`);

        websocket.current.onmessage = (event) => {
            setMessages(prevState => [...prevState, event.data]);
        }

        return () => websocket.current.close();

    }, [email]);

    const handleChange = (event) => {
        setMessage(event.target.value);
    }

    useEffect(() => {
        if (message.trim() !== "") {
            setCorrectMessage(true);
        } else {
            setCorrectMessage(false);
        }
    }, [message]);

    const addMessage = () => {
        if (correctMessage && websocket.current?.readyState === WebSocket.OPEN) {

            websocket.current.send(message);

            setMessage("");
        }
        setMessage("");
    }

    return (
        <div>
            <div className={"websocketbtn"}>
                <button onClick={() => window.location.href = "/sse"}>back</button>
            </div>

            <p className={"websockettext"}>It is a websocket</p>

            <div className={"sending"}>
                <input type={"text"}
                       placeholder={"input smth"}
                       onChange={handleChange}
                       value={message}/>

                <button disabled={!correctMessage} onClick={addMessage}>send</button>
            </div>

            <div className={"websocketMsg"}>
                {messages.map(msg => (
                    <p>{msg}</p>
                ))}
            </div>
        </div>
    )
}
