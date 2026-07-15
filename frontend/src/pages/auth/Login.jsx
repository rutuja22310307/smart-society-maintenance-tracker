import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../../services/authService";
import { saveToken } from "../../utils/auth";

function Login() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);


  const handleLogin = async (e) => {

    e.preventDefault();

    setError("");
    setLoading(true);

    try {

      const response = await loginUser({
        email,
        password,
      });


      console.log(
        "Login Response:",
        response.data
      );


      // Save JWT Token
      saveToken(response.data.token);


      // Save User Role
      localStorage.setItem(
        "role",
        response.data.role
      );


      alert("Login Successful!");


      navigate("/dashboard");


    } catch (err) {

      console.error(err);

      setError(
        "Invalid email or password."
      );

    } finally {

      setLoading(false);

    }

  };


  return (

    <div
      className="container-fluid d-flex justify-content-center align-items-center"
      style={{
        minHeight: "100vh",
        backgroundColor: "#f1f5f9"
      }}
    >


      <div className="col-md-4">


        <div className="card shadow-lg border-0">


          <div className="card-body p-5">


            <div className="text-center mb-4">


              <h1>
                🏢
              </h1>


              <h2>
                Smart Society Tracker
              </h2>


              <p className="text-muted">
                Login to manage your society
              </p>


            </div>



            {error && (

              <div className="alert alert-danger">

                {error}

              </div>

            )}



            <form onSubmit={handleLogin}>


              <div className="mb-3">

                <label className="form-label">
                  Email
                </label>


                <input
                  type="email"
                  className="form-control"
                  placeholder="Enter your email"
                  value={email}
                  onChange={(e)=>setEmail(e.target.value)}
                  required
                />


              </div>




              <div className="mb-3">

                <label className="form-label">
                  Password
                </label>


                <input
                  type="password"
                  className="form-control"
                  placeholder="Enter your password"
                  value={password}
                  onChange={(e)=>setPassword(e.target.value)}
                  required
                />


              </div>




              <button
                type="submit"
                className="btn btn-primary w-100"
                disabled={loading}
              >

                {
                  loading
                    ? "Logging in..."
                    : "Login"
                }


              </button>



            </form>



            <div className="text-center mt-4">


              <span>
                New resident?
              </span>


              <button
                className="btn btn-link"
                onClick={() => navigate("/register")}
              >

                Create Account

              </button>


            </div>



          </div>


        </div>


      </div>


    </div>

  );

}

export default Login;