import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../../services/authService";

function Register() {

    const navigate = useNavigate();


    const [formData, setFormData] = useState({

        name: "",
        email: "",
        password: "",
        phone: "",
        apartmentNumber: "",
        role: "RESIDENT"

    });


    const [error, setError] = useState("");

    const [loading, setLoading] = useState(false);



    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value

        });

    };



    const handleRegister = async (e) => {

        e.preventDefault();

        setError("");

        setLoading(true);


        try {


            console.log(
                "Sending Register Data:",
                formData
            );


            const response = await registerUser(formData);



            console.log(
                "Register Response:",
                response.data
            );



            alert(
                "Registration Successful! Please login."
            );


            navigate("/login");



        } catch (err) {


            console.error(
                "Register Error:",
                err
            );


            console.log(
                "Status:",
                err.response?.status
            );


            console.log(
                "Backend Error:",
                err.response?.data
            );



            setError(

                err.response?.data?.message
                ||
                JSON.stringify(err.response?.data)
                ||
                "Registration failed"

            );



        } finally {


            setLoading(false);


        }

    };



    return (


        <div
            className="container-fluid d-flex justify-content-center align-items-center"
            style={{
                minHeight: "100vh",
                backgroundColor:"#f1f5f9"
            }}
        >


            <div className="col-md-5">


                <div className="card shadow-lg border-0">


                    <div className="card-body p-5">



                        <div className="text-center mb-4">


                            <h1>
                                🏢
                            </h1>


                            <h2>
                                Create Account
                            </h2>


                            <p className="text-muted">
                                Join Smart Society Tracker
                            </p>


                        </div>




                        {
                            error && (

                                <div className="alert alert-danger">

                                    {error}

                                </div>

                            )
                        }




                        <form onSubmit={handleRegister}>


                            <div className="mb-3">

                                <label className="form-label">
                                    Name
                                </label>


                                <input
                                    type="text"
                                    className="form-control"
                                    name="name"
                                    value={formData.name}
                                    onChange={handleChange}
                                    required
                                />

                            </div>





                            <div className="mb-3">

                                <label className="form-label">
                                    Email
                                </label>


                                <input
                                    type="email"
                                    className="form-control"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    required
                                />

                            </div>





                            <div className="mb-3">

                                <label className="form-label">
                                    Phone
                                </label>


                                <input
                                    type="text"
                                    className="form-control"
                                    name="phone"
                                    placeholder="10 digit mobile number"
                                    value={formData.phone}
                                    onChange={handleChange}
                                    required
                                />

                            </div>





                            <div className="mb-3">

                                <label className="form-label">
                                    Apartment Number
                                </label>


                                <input
                                    type="text"
                                    className="form-control"
                                    name="apartmentNumber"
                                    value={formData.apartmentNumber}
                                    onChange={handleChange}
                                    required
                                />

                            </div>





                            <div className="mb-3">

                                <label className="form-label">
                                    Password
                                </label>


                                <input
                                    type="password"
                                    className="form-control"
                                    name="password"
                                    value={formData.password}
                                    onChange={handleChange}
                                    required
                                />

                            </div>





                            <button
                                type="submit"
                                className="btn btn-success w-100"
                                disabled={loading}
                            >


                                {
                                    loading
                                    ?
                                    "Creating Account..."
                                    :
                                    "Register"
                                }


                            </button>



                        </form>





                        <div className="text-center mt-4">


                            <span>
                                Already have an account?
                            </span>


                            <button
                                className="btn btn-link"
                                onClick={() => navigate("/login")}
                            >

                                Login

                            </button>


                        </div>




                    </div>


                </div>


            </div>


        </div>


    );

}


export default Register;