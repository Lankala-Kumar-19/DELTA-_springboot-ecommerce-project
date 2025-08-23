import { useEffect, useState, useContext } from "react";
import api from "../services/api";
import { AuthContext } from "../context/AuthContext";
import "./MyOrders.css";
function MyOrders() {
  const { user,token } = useContext(AuthContext);
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        setLoading(true);
        setError("");
        const response = await api.get(`/orders`,{
            headers: {
            Authorization: `Bearer ${token}`, 
            },
        });
        setOrders(response.data);
      } catch (err) {
        setError("Failed to fetch orders.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    if (user) {
      fetchOrders();
    }
  }, [user]);

  if (loading) return <p>Loading orders...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div className="orders-page">
      <div className="orders-container">
        <h2 className="orders-title">My Orders</h2>

        {loading && <p className="orders-loading">Loading orders...</p>}
        {error && <p className="orders-error">{error}</p>}

        {!loading && !error && (
          <>
            {orders.length === 0 ? (
              <p className="orders-empty">No orders found 🛒</p>
            ) : (
              orders.map((order) => (
                <div key={order.id} className="order-card">
                  <h3>Order #{order.id}</h3>
                  <p>Status: {order.status}</p>
                  <p>Total: ${order.totalAmount.toFixed(2)}</p>
                  <h4>Items:</h4>
                  <ul>
                    {order.items.map((item, index) => (
                      <li key={index}>
                        {item.productName} — Qty: {item.quantity} — $
                        {item.price.toFixed(2)}
                      </li>
                    ))}
                  </ul>
                </div>
              ))
            )}
          </>
        )}
      </div>
    </div>
  );
}

export default MyOrders;
