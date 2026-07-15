import { useEffect, useState } from "react";
import Layout from "../../components/layout/Layout";
import { getAllNotices } from "../../services/noticeService";

function Notices() {

    const [notices, setNotices] = useState([]);

    // Get logged-in user's role
    const role = localStorage.getItem("role");

    useEffect(() => {
        fetchNotices();
    }, []);

    const fetchNotices = async () => {

        try {

            const response = await getAllNotices();

            console.log(response.data);

            setNotices(response.data);

        } catch (error) {

            console.log(error);

        }

    };

    return (

        <Layout>

            <div className="d-flex justify-content-between mb-4">

                <h2>
                    Notice Management
                </h2>

                {/* Only ADMIN can add notices */}
                {role === "ADMIN" && (
                    <a
                        href="/add-notice"
                        className="btn btn-primary"
                    >
                        + Add Notice
                    </a>
                )}

            </div>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>
                        <th>Title</th>
                        <th>Message</th>
                        <th>Created Date</th>

                    </tr>

                </thead>

                <tbody>

                    {notices.map((notice) => (

                        <tr key={notice.id}>

                            <td>{notice.id}</td>

                            <td>{notice.title}</td>

                            <td>{notice.message}</td>

                            <td>{notice.createdAt}</td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </Layout>

    );

}

export default Notices;