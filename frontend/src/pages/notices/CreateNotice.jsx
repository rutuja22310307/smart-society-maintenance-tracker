import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Layout from "../../components/layout/Layout";
import { createNotice } from "../../services/noticeService";

function CreateNotice() {

    const navigate = useNavigate();

    const [notice, setNotice] = useState({
        title: "",
        message: "",
        expiryDate: ""
    });

    const handleChange = (e) => {
        setNotice({
            ...notice,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await createNotice(notice);
            alert("Notice created successfully!");
            navigate("/notices");
        } catch (error) {
            console.error(error);
            alert("Failed to create notice");
        }
    };

    return (
        <Layout>

            <h2 className="mb-4">Create Notice</h2>

            <form onSubmit={handleSubmit}>

                <div className="mb-3">
                    <label className="form-label">Title</label>
                    <input
                        type="text"
                        className="form-control"
                        name="title"
                        value={notice.title}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Message</label>
                    <textarea
                        className="form-control"
                        rows="4"
                        name="message"
                        value={notice.message}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Expiry Date</label>
                    <input
                        type="date"
                        className="form-control"
                        name="expiryDate"
                        value={notice.expiryDate}
                        onChange={handleChange}
                    />
                </div>

                <button
                    type="submit"
                    className="btn btn-primary"
                >
                    Create Notice
                </button>

            </form>

        </Layout>
    );
}

export default CreateNotice;