import React from "react";
import Login from "../views/Login";
import App from "../main/App";
import { Link, useNavigate } from "react-router-dom";

function Navibar() {
  const navigate = useNavigate();

  return (
    <div className="navbar navbar-expand-lg fixed-top navbar-dark bg-primary" >
      <div className="container">
        <a href="https://bootswatch.com/" className="navbar-brand">Minhas Finanças</a>

        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarResponsive"
          aria-controls="navbarResponsive"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarResponsive">
          <ul className="navbar-nav">

          <li className="nav-item">
              <button className="nav-link" onClick={() => navigate("/home")}>
                Home
              </button>
            </li>

            <li className="nav-item">
              <button className="nav-link" onClick={() => navigate("/login")}>
                Login
              </button>
            </li>

            <li className="nav-item">
              <button className="nav-link" onClick={() => navigate("/cadastro")}>
                Cadastro
              </button>
            </li>
            <li className="nav-item">
              <button className="nav-link" onClick={() => navigate("/consulta")}>
                Consultar Lançamentos
              </button>
            </li>


          </ul>
        </div>
      </div>
    </div>
  );
}

export default Navibar;