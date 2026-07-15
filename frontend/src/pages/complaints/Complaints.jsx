import { useEffect, useState } from "react";
import Layout from "../../components/layout/Layout";
import {
    getAllComplaints,
    updateComplaintStatus
} from "../../services/complaintService";


function Complaints() {

    const [complaints, setComplaints] = useState([]);
    const [loading, setLoading] = useState(true);

    // Get logged-in user's role
    const role = localStorage.getItem("role");


    useEffect(() => {
        fetchComplaints();
    }, []);


    const fetchComplaints = async () => {

        try {

            const response = await getAllComplaints();

            console.log(response.data);

            setComplaints(response.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };


    const changeStatus = async (id) => {

        const status = prompt(
            "Enter status: PENDING / IN_PROGRESS / RESOLVED / REJECTED"
        );


        if (!status) {
            return;
        }


        try {

            await updateComplaintStatus(
                id,
                status
            );


            fetchComplaints();


        } catch (error) {

            console.error(
                "Status update failed:",
                error
            );

        }

    };


    if (loading) {

        return (
            <h3 className="text-center mt-5">
                Loading...
            </h3>
        );

    }


    return (

        <Layout>

            <div className="d-flex justify-content-between mb-4">

                <h2>
                    Complaint Management
                </h2>


                <a
                    href="/add-complaint"
                    className="btn btn-primary"
                >
                    + Add Complaint
                </a>


            </div>


            <table className="table table-hover shadow">


                <thead className="table-dark">

                    <tr>

                        <th>ID</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Priority</th>
                        <th>Status</th>
                        <th>Resident</th>
                        <th>Apartment</th>
                        <th>Actions</th>

                    </tr>

                </thead>


                <tbody>


                    {complaints.map((complaint) => (

                        <tr key={complaint.id}>


                            <td>
                                {complaint.id}
                            </td>


                            <td>
                                {complaint.title}
                            </td>


                            <td>
                                {complaint.category}
                            </td>


                            <td>

                                <span
                                    className={
                                        complaint.priority === "HIGH"
                                            ? "badge bg-danger"
                                            : complaint.priority === "MEDIUM"
                                                ? "badge bg-warning text-dark"
                                                : "badge bg-success"
                                    }
                                >

                                    {complaint.priority}

                                </span>

                            </td>


                            <td>

                                <span
                                    className={
                                        complaint.status === "PENDING"
                                            ? "badge bg-warning text-dark"

                                            : complaint.status === "IN_PROGRESS"
                                                ? "badge bg-primary"

                                                : complaint.status === "RESOLVED"
                                                    ? "badge bg-success"

                                                    : "badge bg-danger"
                                    }
                                >

                                    {complaint.status}

                                </span>

                            </td>


                            <td>
                                {complaint.residentName}
                            </td>


                            <td>
                                {complaint.apartmentNumber}
                            </td>


                            <td>


                                <button
                                    className="btn btn-sm btn-primary me-2"
                                >
                                    View
                                </button>


                                {/* Only ADMIN can update status */}
                                {role === "ADMIN" && (
                                    <button
                                        className="btn btn-sm btn-warning"
                                        onClick={() => changeStatus(complaint.id)}
                                    >
                                        Update Status
                                    </button>
                                )}


                            </td>


                        </tr>

                    ))}


                </tbody>


            </table>


        </Layout>

    );

}


export default Complaints;