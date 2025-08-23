import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { CartProvider } from "./context/CartContext";
import Register from "./pages/Register";

import Login from "./pages/Login";
import Products from "./pages/Products";
import Cart from "./pages/Cart";
import MyOrders from "./pages/MyOrders";

function App() {
  return (
    <AuthProvider>
      <CartProvider>
        <Router>
          <nav style={{ padding: "10px", borderBottom: "1px solid #ddd" }}>
            <Link to="/products" style={{ marginRight: "10px" }}>
              Products
            </Link>
            <Link to="/register" style={{ marginRight: "10px" }}>
            Register
            </Link>
            <Link to="/cart" style={{ marginRight: "10px" }}>
              Cart
            </Link>
            <Link to="/orders" style={{ marginRight: "10px" }}>
              My Orders
            </Link>

            <Link to="/login">Login</Link>
          </nav>

          <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
            <Route path="/products" element={<Products />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/orders" element={<MyOrders />} />
            <Route path="*" element={<Login />} />
          </Routes>
        </Router>
      </CartProvider>
    </AuthProvider>
  );
}

export default App;
