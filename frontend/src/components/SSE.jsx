import "../styles/SEE.css"
import {useEffect, useState} from "react";
import {EventSourcePolyfill} from 'event-source-polyfill';

export default function SSE() {
    const [emails, setEmails] = useState([]);

    useEffect(() => {
        const sse = new EventSourcePolyfill("https://microservice-lypk.onrender.com/api/sse", {withCredentials: true});

        sse.onmessage = (event) => {
            const email = event.data;
            setEmails(prevState => [...prevState, email]);
        }

    }, []);

    return (
        <div>
            <div  className={"ssebtn"} >
                <button onClick={() => window.location.href ="/"}>back</button>
                <button onClick={() => window.location.href="/websocket"}>next</button>
            </div>
            <p className={"ssetext"}>it is a SSE(Server Sent Events)</p>
            <div className={"sseemails"}>
                {emails.map(email => (
                    <p>{email}</p>
                ))}
            </div>
        </div>
    )
}
