import { useEffect, useState } from "react";
import Layout from "../../components/layout/Layout";
import { getAllUsers, deleteUser } from "../../services/userService";

function Users() {

    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {

        try {

            const response = await getAllUsers();

            console.log(response.data);

            setUsers(response.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    const removeUser = async (id) => {

        const confirmDelete = window.confirm(
            "Delete this user?"
        );

        if (!confirmDelete) return;

        try {

            await deleteUser(id);

            fetchUsers();

        } catch (error) {

            console.error(error);

            alert("Delete failed");

        }

    };

    if (loading) {
        return <h3 className="text-center mt-5">Loading...</h3>;
    }

    return (

        <Layout>

            <h2 className="mb-4">
                User Management
            </h2>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Apartment</th>
                        <th>Role</th>
                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {users.map((user) => (

                        <tr key={user.id}>

                            <td>{user.id}</td>

                            <td>{user.name}</td>

                            <td>{user.email}</td>

                            <td>{user.phone}</td>

                            <td>{user.apartmentNumber}</td>

                            <td>

                                <span className="badge bg-primary">

                                    {user.role}

                                </span>

                            </td>

                            <td>

                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => removeUser(user.id)}
                                >
                                    Delete
                                </button>

                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </Layout>

    );

}

export default Users;