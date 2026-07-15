import { useState } from "react";
import Layout from "../../components/layout/Layout";
import { createComplaint } from "../../services/complaintService";
import { useNavigate } from "react-router-dom";


function AddComplaint(){

    const navigate = useNavigate();


    const [complaint,setComplaint] = useState({

        title:"",
        description:"",
        category:"",
        priority:""

    });


    const handleChange=(e)=>{

        setComplaint({

            ...complaint,

            [e.target.name]:e.target.value

        });

    };


    const handleSubmit=async(e)=>{

        e.preventDefault();


        try{

            await createComplaint(complaint);


            alert("Complaint created successfully");


            navigate("/complaints");


        }
        catch(error){

            console.error(error);

            alert("Failed to create complaint");

        }

    };


    return(

        <Layout>


            <div className="container">


                <h2 className="mb-4">
                    Add Complaint
                </h2>



                <form onSubmit={handleSubmit}>


                    <div className="mb-3">

                        <label>
                            Title
                        </label>

                        <input

                            type="text"

                            name="title"

                            className="form-control"

                            value={complaint.title}

                            onChange={handleChange}

                        />

                    </div>



                    <div className="mb-3">

                        <label>
                            Description
                        </label>


                        <textarea

                            name="description"

                            className="form-control"

                            value={complaint.description}

                            onChange={handleChange}

                        />

                    </div>




                    <div className="mb-3">

                        <label>
                            Category
                        </label>


                        <select

                            name="category"

                            className="form-control"

                            value={complaint.category}

                            onChange={handleChange}

                        >

                            <option value="">
                                Select Category
                            </option>


                            <option>
                                Plumbing
                            </option>


                            <option>
                                Electrical
                            </option>


                            <option>
                                Security
                            </option>


                            <option>
                                Housekeeping
                            </option>


                        </select>


                    </div>





                    <div className="mb-3">

                        <label>
                            Priority
                        </label>


                        <select

                            name="priority"

                            className="form-control"

                            value={complaint.priority}

                            onChange={handleChange}

                        >

                            <option value="">
                                Select Priority
                            </option>


                            <option>
                                LOW
                            </option>


                            <option>
                                MEDIUM
                            </option>


                            <option>
                                HIGH
                            </option>


                        </select>


                    </div>




                    <button
                        className="btn btn-primary"
                    >

                        Submit Complaint

                    </button>



                </form>


            </div>


        </Layout>

    );

}


export default AddComplaint;