import { useEffect, useState } from "react";
import Layout from "../../components/layout/Layout";
import { getAllMaintenance } from "../../services/maintenanceService";

function Maintenance() {

    const [maintenanceList, setMaintenanceList] = useState([]);
    const [loading, setLoading] = useState(true);

    // Get logged-in user's role
    const role = localStorage.getItem("role");

    useEffect(() => {
        fetchMaintenance();
    }, []);

    const fetchMaintenance = async () => {

        try {

            const response = await getAllMaintenance();

            console.log(response.data);

            setMaintenanceList(response.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }
    };

    if (loading) {
        return <h3 className="text-center mt-5">Loading...</h3>;
    }

    return (

        <Layout>

            <div className="d-flex justify-content-between mb-4">

                <h2>Maintenance Management</h2>

                {/* Only ADMIN can add maintenance */}
                {role === "ADMIN" && (
                    <a
                        href="/add-maintenance"
                        className="btn btn-primary"
                    >
                        + Add Maintenance
                    </a>
                )}

            </div>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>
                        <th>Amount</th>
                        <th>Month</th>
                        <th>Year</th>
                        <th>Status</th>
                        <th>Resident</th>
                        <th>Apartment</th>
                        <th>Created At</th>

                    </tr>

                </thead>

                <tbody>

                    {maintenanceList.map((item) => (

                        <tr key={item.id}>

                            <td>{item.id}</td>

                            <td>₹ {item.amount}</td>

                            <td>{item.month}</td>

                            <td>{item.year}</td>

                            <td>

                                <span
                                    className={
                                        item.status === "PAID"
                                            ? "badge bg-success"
                                            : "badge bg-warning text-dark"
                                    }
                                >
                                    {item.status}
                                </span>

                            </td>

                            <td>{item.residentName}</td>

                            <td>{item.apartmentNumber}</td>

                            <td>{item.createdAt}</td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </Layout>

    );
}

export default Maintenance;