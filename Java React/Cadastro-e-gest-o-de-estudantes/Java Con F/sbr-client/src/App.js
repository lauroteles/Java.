import "../node_modules/bootstrap/dist/css/bootstrap.min.css"



import React from 'react';
import './App.css';
import Home from "./Home";
import StudentsView from './components/student/StudentsView';
import NavBar from "./components/common/NavBar";
import {BrowserRouter as Router, Routes,Route} from "react-router-dom";
import StudentsAdd from "./components/student/StudentsAdd.js";
import EditStudent from "./components/student/EditStudent.js";
import StudentPofile from "./components/student/StudentProfile.js";

function App() {
  return (
    <main className="container mt-5">
    <Router>
      <NavBar />
      
        <Routes>
         
          <Route exact path="/" 
            element={<Home />}></Route>

          <Route exact path="/view-students" 
            element={<StudentsView />}></Route>
          
          <Route exact path="/add-students" 
            element={<StudentsAdd />}></Route>

          <Route exact path="/edit-students/:id" 
            element={<EditStudent />}></Route>

          <Route exact path="/student-profile/:id" 
            element={<StudentPofile />}></Route>


        </Routes>
      </Router>


    </main>
  );
}

export default App;
