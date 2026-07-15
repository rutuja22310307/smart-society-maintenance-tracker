import { Link, useLocation } from "react-router-dom";
import { logout } from "../../utils/auth";

function Sidebar() {

    const location = useLocation();

    // Get user role from localStorage
    const role = localStorage.getItem("role");

    const handleLogout = () => {

        logout();

        // Remove role as well
        localStorage.removeItem("role");

        window.location.href = "/login";
    };

    return (

        <div
            className="bg-dark text-white p-3"
            style={{
                width: "250px",
                minHeight: "100vh"
            }}
        >

            <h3 className="text-center mb-4">
                Society Tracker
            </h3>

            <ul className="nav flex-column">

                <li className="nav-item mb-2">
                    <Link
                        to="/dashboard"
                        className={`nav-link ${
                            location.pathname === "/dashboard"
                                ? "text-warning"
                                : "text-white"
                        }`}
                    >
                        🏠 Dashboard
                    </Link>
                </li>

                <li className="nav-item mb-2">
                    <Link
                        to="/complaints"
                        className={`nav-link ${
                            location.pathname === "/complaints"
                                ? "text-warning"
                                : "text-white"
                        }`}
                    >
                        📝 Complaints
                    </Link>
                </li>

                <li className="nav-item mb-2">
                    <Link
                        to="/notices"
                        className={`nav-link ${
                            location.pathname === "/notices"
                                ? "text-warning"
                                : "text-white"
                        }`}
                    >
                        📢 Notices
                    </Link>
                </li>

                <li className="nav-item mb-2">
                    <Link
                        to="/maintenance"
                        className={`nav-link ${
                            location.pathname === "/maintenance"
                                ? "text-warning"
                                : "text-white"
                        }`}
                    >
                        💰 Maintenance
                    </Link>
                </li>

                {/* Only ADMIN can see Users */}
                {role === "ADMIN" && (
                    <li className="nav-item mb-2">
                        <Link
                            to="/users"
                            className={`nav-link ${
                                location.pathname === "/users"
                                    ? "text-warning"
                                    : "text-white"
                            }`}
                        >
                            👥 Users
                        </Link>
                    </li>
                )}

                <hr />

                <button
                    onClick={handleLogout}
                    className="btn btn-danger w-100"
                >
                    Logout
                </button>

            </ul>

        </div>

    );
}

export default Sidebar;