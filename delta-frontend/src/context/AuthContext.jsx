import {jwtDecode} from "jwt-decode";
import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);

  useEffect(() => {
    const savedToken = localStorage.getItem("token");
    if (savedToken) {
      setToken(savedToken);
      const decoded = jwtDecode(savedToken);
      console.log(decoded.id);
      setUser({
        id: decoded.id,
        email: decoded.sub,
        roles: decoded.roles
        });

    }
  }, []);

  const login = (jwt) => {
    localStorage.setItem("token", jwt);
    setToken(jwt);
    const decoded=jwtDecode(jwt);
    setUser({
        id: decoded.id,
        email: decoded.sub,
        roles: decoded.roles
    });
  };

  const logout = () => {
    localStorage.removeItem("token");
    setToken(null);
    setUser(null);
  };

  const isAdmin = () => {
    return user?.roles?.includes("ADMIN");
  };

  return (
    <AuthContext.Provider value={{ user, token, login, logout, isAdmin }}>
      {children}
    </AuthContext.Provider>
  );
};
