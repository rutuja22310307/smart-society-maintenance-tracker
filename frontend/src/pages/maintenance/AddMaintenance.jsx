import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Layout from "../../components/layout/Layout";
import { createMaintenance } from "../../services/maintenanceService";

function AddMaintenance() {

    const navigate = useNavigate();

    const [maintenance, setMaintenance] = useState({
        amount: "",
        month: "",
        year: "",
        user: {
            id: ""
        }
    });

    const handleChange = (e) => {

        const { name, value } = e.target;

        if (name === "userId") {

            setMaintenance({
                ...maintenance,
                user: {
                    id: value
                }
            });

        } else {

            setMaintenance({
                ...maintenance,
                [name]: value
            });

        }

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await createMaintenance(maintenance);

            alert("Maintenance added successfully");

            navigate("/maintenance");

        } catch (error) {

            console.error(error);

            alert("Failed to add maintenance");

        }

    };

    return (

        <Layout>

            <h2 className="mb-4">Add Maintenance</h2>

            <form onSubmit={handleSubmit}>

                <div className="mb-3">
                    <label>Amount</label>
                    <input
                        type="number"
                        className="form-control"
                        name="amount"
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label>Month</label>
                    <input
                        type="text"
                        className="form-control"
                        name="month"
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label>Year</label>
                    <input
                        type="number"
                        className="form-control"
                        name="year"
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label>User ID</label>
                    <input
                        type="number"
                        className="form-control"
                        name="userId"
                        onChange={handleChange}
                        required
                    />
                </div>

                <button
                    className="btn btn-primary"
                    type="submit"
                >
                    Add Maintenance
                </button>

            </form>

        </Layout>

    );

}

export default AddMaintenance;