import React, { useEffect, useState } from 'react';
import axios from "axios";
import {FaEdit, FaEye, FaTrashAlt} from "react-icons/fa";
import { Link } from 'react-router-dom';
const StudentsView = () => {
    const[students,setStudents] = useState([]);

    useEffect(()=>{
        loadStudents();
    }, [])

    const loadStudents = async() =>{
        const result = await axios.get("http://localhost:9192/students", {
            validateStatus:() =>{
                return true;
            }
        });
        if (result.status === 302){
            setStudents(result.data);   
        }
    };

    const handleDelete = async (id) =>{
        await axios.delete(`http://localhost:9192/students/delete/${id}`);
        loadStudents();
    };

    return (
    <section>
      <table className='table table-bordered table-hover shadow'>
        <thead>
            <tr className='text-center'>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Department</th>
                <th colSpan={"3"}>Actions</th>
            </tr>
        </thead>
        <tbody className='text-center'>
            {students.map((students, index)=>(

                <tr key={students.id}>
                    <th scope='row' key={index}>
                        {index +1}
                    </th>
                      <td>{students.firstName}</td>
                      <td>{students.lastName}</td>
                      <td>{students.email}</td>
                      <td>{students.department}</td>
                     
                      <td className='mx-2'>
                            <Link to = {`/student-profile/${students.id}`} className='btn btn-info'>
                                <FaEye />
                            </Link>
                        </td>
                        
                      <td className='mx-2'>
                        <Link to = {`/edit-students/${students.id}`} className='btn btn-warning'>
                                <FaEdit />
                            </Link>
                        </td>
                        
                      <td className='mx-2'>
                        <button className='btn btn-danger' onClick={()=> handleDelete(students.id)}>
                                <FaTrashAlt />
                            </button>
                        </td>
                 
                </tr>



            ))}
        </tbody>

      </table>

    </section>
  )
}

export default StudentsView
