import { useEffect, useState } from "react";

import { getDashboardData } from "../../services/dashboardService";

import Layout from "../../components/layout/Layout";

import StatCard from "../../components/dashboard/StatCard";


function Dashboard() {


    const [dashboard, setDashboard] = useState(null);

    const [loading, setLoading] = useState(true);



    const role = localStorage.getItem("role");



    useEffect(() => {

        fetchDashboard();

    }, []);




    const fetchDashboard = async () => {


        try {


            const response = await getDashboardData();


            console.log(response.data);


            setDashboard(response.data);



        } catch(error) {


            console.error(
                "Dashboard Error:",
                error
            );


        }
        finally {


            setLoading(false);


        }


    };





    if(loading){

        return (

            <h3 className="text-center mt-5">

                Loading Dashboard...

            </h3>

        );

    }





    return (

        <Layout>


            {/* Header Section */}


            <div
                className="mb-4"
            >


                <h2 className="fw-bold">

                    Welcome to Smart Society 👋

                </h2>


                <p className="text-muted">

                    Role :
                    <span className="badge bg-primary ms-2">

                        {role}

                    </span>

                </p>


            </div>






            {/* Statistics Cards */}



            <div className="row g-4">



                <StatCard
                    title="Residents"
                    value={dashboard.totalResidents}
                    icon="bi bi-people-fill"
                    bgColor="#2563EB"
                />



                <StatCard
                    title="Staff"
                    value={dashboard.totalStaff}
                    icon="bi bi-person-badge-fill"
                    bgColor="#16A34A"
                />



                <StatCard
                    title="Admins"
                    value={dashboard.totalAdmins}
                    icon="bi bi-shield-lock-fill"
                    bgColor="#DC2626"
                />



                <StatCard
                    title="Complaints"
                    value={dashboard.totalComplaints}
                    icon="bi bi-exclamation-circle-fill"
                    bgColor="#F59E0B"
                />



                <StatCard
                    title="Pending Complaints"
                    value={dashboard.pendingComplaints}
                    icon="bi bi-hourglass-split"
                    bgColor="#EA580C"
                />



                <StatCard
                    title="Resolved Complaints"
                    value={dashboard.resolvedComplaints}
                    icon="bi bi-check-circle-fill"
                    bgColor="#059669"
                />



                <StatCard
                    title="Notices"
                    value={dashboard.totalNotices}
                    icon="bi bi-megaphone-fill"
                    bgColor="#7C3AED"
                />



            </div>








            {/* Quick Actions */}



            <div className="mt-5">


                <h4 className="mb-3">

                    Quick Actions

                </h4>



                <div className="row g-3">


                    <div className="col-md-3">


                        <a
                            href="/add-complaint"
                            className="btn btn-outline-primary w-100 p-3"
                        >

                            📝 Raise Complaint

                        </a>


                    </div>





                    <div className="col-md-3">


                        <a
                            href="/notices"
                            className="btn btn-outline-success w-100 p-3"
                        >

                            📢 View Notices

                        </a>


                    </div>





                    <div className="col-md-3">


                        <a
                            href="/maintenance"
                            className="btn btn-outline-warning w-100 p-3"
                        >

                            💰 Maintenance

                        </a>


                    </div>





                    <div className="col-md-3">


                        <a
                            href="/complaints"
                            className="btn btn-outline-danger w-100 p-3"
                        >

                            🔧 Complaints

                        </a>


                    </div>



                </div>


            </div>







            {/* Information Section */}



            <div className="card shadow border-0 mt-5">


                <div className="card-body">


                    <h4>

                        Smart Society Management

                    </h4>


                    <p className="text-muted">


                        Manage complaints, notices,
                        maintenance payments and residents
                        from one centralized platform.



                    </p>



                </div>


            </div>



        </Layout>


    );


}


export default Dashboard;