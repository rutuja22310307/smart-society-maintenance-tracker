import { useNavigate } from "react-router-dom";


function Home() {

    const navigate = useNavigate();


    return (

        <div>


            {/* Hero Section */}

            <div
                style={{
                    minHeight: "90vh",
                    backgroundImage:
                    "linear-gradient(rgba(0,0,0,0.55),rgba(0,0,0,0.55)), url('https://images.unsplash.com/photo-1564013799919-ab600027ffc6')",
                    backgroundSize: "cover",
                    backgroundPosition: "center"
                }}
                className="d-flex justify-content-center align-items-center"
            >


                <div className="text-center text-white">


                    <h1 className="display-2 fw-bold">

                        🏢 Green Valley Society

                    </h1>



                    <p className="fs-3 mt-3">

                        Welcome to Smart Society Management System

                    </p>



                    <p className="fs-5">

                        Manage complaints, maintenance,
                        notices and residents easily in one place.

                    </p>




                    <div className="mt-5">


                        <button
                            className="btn btn-primary btn-lg me-3 px-5"
                            onClick={() => navigate("/login")}
                        >

                            Login

                        </button>




                        <button
                            className="btn btn-success btn-lg px-5"
                            onClick={() => navigate("/register")}
                        >

                            Register

                        </button>


                    </div>


                </div>


            </div>





            {/* Why Choose Smart Society Section */}


            <div className="container mt-5 mb-5">


                <h2 className="text-center mb-5">

                    Why Choose Smart Society?

                </h2>




                <div className="row g-4">



                    <div className="col-md-3">


                        <div className="card shadow border-0 text-center p-4 h-100">


                            <h1>
                                🏠
                            </h1>


                            <h4>
                                Better Society Living
                            </h4>


                            <p>
                                A centralized platform to manage
                                daily society activities smoothly.
                            </p>


                        </div>


                    </div>





                    <div className="col-md-3">


                        <div className="card shadow border-0 text-center p-4 h-100">


                            <h1>
                                🛠️
                            </h1>


                            <h4>
                                Quick Complaint Resolution
                            </h4>


                            <p>
                                Residents can raise issues and
                                track complaint status easily.
                            </p>


                        </div>


                    </div>





                    <div className="col-md-3">


                        <div className="card shadow border-0 text-center p-4 h-100">


                            <h1>
                                💳
                            </h1>


                            <h4>
                                Transparent Maintenance
                            </h4>


                            <p>
                                Manage monthly maintenance payments
                                with complete transparency.
                            </p>


                        </div>


                    </div>





                    <div className="col-md-3">


                        <div className="card shadow border-0 text-center p-4 h-100">


                            <h1>
                                🤝
                            </h1>


                            <h4>
                                Connected Community
                            </h4>


                            <p>
                                Improve communication between
                                residents, staff and management.
                            </p>


                        </div>


                    </div>


                </div>


            </div>







            {/* Society Statistics Section */}


            <div
                className="container-fluid bg-light py-5"
            >


                <div className="container">


                    <h2 className="text-center mb-5">

                        Our Society at a Glance

                    </h2>




                    <div className="row text-center">



                        <div className="col-md-3 mb-4">


                            <div className="card shadow border-0 p-4">


                                <h1 className="text-primary">
                                    250+
                                </h1>


                                <h5>
                                    Happy Residents
                                </h5>


                                <p className="text-muted">
                                    Active members enjoying
                                    smart society services.
                                </p>


                            </div>


                        </div>





                        <div className="col-md-3 mb-4">


                            <div className="card shadow border-0 p-4">


                                <h1 className="text-success">
                                    50+
                                </h1>


                                <h5>
                                    Apartments
                                </h5>


                                <p className="text-muted">
                                    Well-managed residential
                                    units in our society.
                                </p>


                            </div>


                        </div>





                        <div className="col-md-3 mb-4">


                            <div className="card shadow border-0 p-4">


                                <h1 className="text-warning">
                                    100+
                                </h1>


                                <h5>
                                    Complaints Resolved
                                </h5>


                                <p className="text-muted">
                                    Issues solved efficiently
                                    through our system.
                                </p>


                            </div>


                        </div>





                        <div className="col-md-3 mb-4">


                            <div className="card shadow border-0 p-4">


                                <h1 className="text-danger">
                                    12
                                </h1>


                                <h5>
                                    Monthly Notices
                                </h5>


                                <p className="text-muted">
                                    Important updates shared
                                    with residents.
                                </p>


                            </div>


                        </div>



                    </div>


                </div>


            </div>







            {/* Footer */}


            <footer
                className="bg-dark text-white text-center p-3"
            >

                Smart Society Maintenance Tracker © 2026

            </footer>



        </div>

    );

}


export default Home;