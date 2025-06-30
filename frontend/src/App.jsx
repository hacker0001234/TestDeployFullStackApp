import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./components/MainPage.jsx";
import CheckingAuthenticated from "./components/checkingAuthenticated.jsx";
import SSE from "./components/SSE.jsx";
import Websocket from "./components/Websocket.jsx";
import Error from "./components/Error.jsx";

function App() {

  return (
  <BrowserRouter>
      <Routes>
          <Route path={"/"} element={<CheckingAuthenticated><MainPage/></CheckingAuthenticated>}/>
          <Route path={"/sse"} element={<CheckingAuthenticated><SSE/></CheckingAuthenticated>}/>
          <Route path={"/websocket"} element={<CheckingAuthenticated><Websocket/></CheckingAuthenticated>}/>
          <Route path={"/error"} element={<Error/>}/>
      </Routes>
  </BrowserRouter>
  )
}

export default App
