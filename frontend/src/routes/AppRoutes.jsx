import { Routes, Route, Navigate } from "react-router-dom";

import Login from "../pages/auth/Login";
import Dashboard from "../pages/dashboard/Dashboard";
import ProtectedRoute from "./ProtectedRoute";

import Complaints from "../pages/complaints/Complaints";
import AddComplaint from "../pages/complaints/AddComplaint";

import Notices from "../pages/notices/Notices";
import CreateNotice from "../pages/notices/CreateNotice";

import Maintenance from "../pages/maintenance/Maintenance";
import AddMaintenance from "../pages/maintenance/AddMaintenance";
import Register from "../pages/auth/Register";
import Users from "../pages/users/Users";
import Home from "../pages/home/Home";
function AppRoutes() {

  const role = localStorage.getItem("role");

  return (
    <Routes>

<Route 
    path="/" 
    element={<Home />} 
/>

      <Route path="/login" element={<Login />} />
      <Route
    path="/register"
    element={<Register />}
/>
      <Route
        path="/dashboard"
        element={
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        }
      />

      <Route
        path="/complaints"
        element={
          <ProtectedRoute>
            <Complaints />
          </ProtectedRoute>
        }
      />

      <Route
        path="/add-complaint"
        element={
          <ProtectedRoute>
            <AddComplaint />
          </ProtectedRoute>
        }
      />

      <Route
        path="/notices"
        element={
          <ProtectedRoute>
            <Notices />
          </ProtectedRoute>
        }
      />

<Route
  path="/add-notice"
  element={
    <ProtectedRoute>
      {role === "ADMIN" ? (
        <CreateNotice />
      ) : (
        <Navigate to="/dashboard" replace />
      )}
    </ProtectedRoute>
  }
/>


      <Route
        path="/maintenance"
        element={
          <ProtectedRoute>
            <Maintenance />
          </ProtectedRoute>
        }
      />

<Route
  path="/add-maintenance"
  element={
    <ProtectedRoute>
      {role === "ADMIN" ? (
        <AddMaintenance />
      ) : (
        <Navigate to="/dashboard" replace />
      )}
    </ProtectedRoute>
  }
/>

      <Route
        path="/users"
        element={
          <ProtectedRoute>
            {role === "ADMIN" ? (
              <Users />
            ) : (
              <Navigate to="/dashboard" replace />
            )}
          </ProtectedRoute>
        }
      />

    </Routes>
  );
}

export default AppRoutes;