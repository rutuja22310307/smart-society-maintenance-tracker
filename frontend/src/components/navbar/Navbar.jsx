import { logout } from "../../utils/auth";

function Navbar() {

    const handleLogout = () => {

        logout();
        window.location.href = "/login";

    };

    return (

        <nav
            className="navbar navbar-expand-lg bg-white shadow-sm px-4"
            style={{
                height: "70px"
            }}
        >

            <div className="container-fluid">

                <h4
                    className="fw-bold mb-0"
                    style={{
                        color: "#2563EB"
                    }}
                >
                    🏢 Smart Society
                </h4>

                <div className="d-flex align-items-center">

                    <i
                        className="bi bi-bell fs-4 me-4"
                        style={{ cursor: "pointer" }}
                    ></i>

                    <div className="me-4 fw-semibold">

                        <i className="bi bi-person-circle me-2"></i>

                        Admin

                    </div>

                    <button
                        className="btn btn-danger"
                        onClick={handleLogout}
                    >
                        <i className="bi bi-box-arrow-right me-2"></i>

                        Logout
                    </button>

                </div>

            </div>

        </nav>

    );

}

export default Navbar;