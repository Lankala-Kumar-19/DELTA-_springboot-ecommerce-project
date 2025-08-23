import { useContext, useState } from "react";
import { CartContext } from "../context/CartContext";
import api from "../services/api";
import { AuthContext } from "../context/AuthContext";
import "./Cart.css";

function Cart() {
  const { cart, removeFromCart, clearCart } = useContext(CartContext);
  const { user,token } = useContext(AuthContext); 
  const [message, setMessage] = useState("");

  // calculate total price
  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const handleCheckout = async () => {
    try {
      setMessage("");

     console.log(user?.id);
     console.log(user);
      const orderData = {
        userId: user?.id, 
        items: cart.map((item) => ({
          productId: item.id,
          productName: item.name,
          quantity: item.quantity,
          price: item.price * item.quantity,
        })),
        totalAmount: total,
        status: "PENDING",
      };

     
      const response = await api.post("/orders", orderData,{
        headers:{
            Authorization: `Bearer ${token}`,
        },
      });

      setMessage("✅ Order placed successfully!");
      clearCart(); 
      console.log("Order Response:", response.data);
    } catch (error) {
      setMessage("❌ Failed to place order.");
      console.error(error);
    }
  };

  return (
    <div className="cart-page">
    <div className="cart-container">
  <h2>Your Cart</h2>
  {cart.length === 0 ? (
    <p className="cart-empty">Your cart is empty 🛒</p>
  ) : (
    <div>
      {cart.map((item, index) => (
        <div key={index} className="cart-item">
          <h3>{item.name}</h3>
          <p>Quantity: {item.quantity}</p>
          <p>Price: ${item.price}</p>
          <p>Subtotal: ${(item.price * item.quantity).toFixed(2)}</p>
          <button
            className="cart-button remove-button"
            onClick={() => removeFromCart(item.id)}
          >
            Remove
          </button>
        </div>
      ))}
      <div className="cart-actions">
        <p className="cart-total">Total: ${total.toFixed(2)}</p>
        <div>
          <button className="cart-button" onClick={clearCart}>
            Clear Cart
          </button>
          <button className="cart-button" onClick={handleCheckout} style={{ marginLeft: "10px" }}>
            Checkout
          </button>
        </div>
      </div>
      {message && (
        <p className={`message ${message.startsWith("❌") ? "error" : ""}`}>{message}</p>
      )}
    </div>
  )}
</div>
</div>
  );
}

export default Cart;
