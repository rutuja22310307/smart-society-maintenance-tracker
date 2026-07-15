import Sidebar from "../sidebar/Sidebar";
import Navbar from "../navbar/Navbar";

function Layout({ children }) {

    return (

        <div className="d-flex">

            <Sidebar />

            <div
                className="flex-grow-1"
                style={{
                    backgroundColor: "#F8FAFC",
                    minHeight: "100vh"
                }}
            >

                <Navbar />

                <div className="p-4">

                    {children}

                </div>

            </div>

        </div>

    );

}

export default Layout;