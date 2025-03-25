import { createContext, useContext, useReducer } from "react";
import { authLogin } from "../core/auth/auth-actions";

export const ContextGlobal = createContext();

const initialState = JSON.parse(localStorage.getItem("authState")) || {
  status: "unauthenticated",
  session: null,
  user: null,
};

const authReducer = (state, action) => {
  switch (action.type) {
    case "LOGIN":
      const newState = {
        status: "authenticated",
        session: action.payload.session,
        user: action.payload.user,
      };
      localStorage.setItem("authState", JSON.stringify(newState));
      return newState;

    case "LOGOUT":
      localStorage.removeItem("authState");
      return {
        ...state,
        status: "unauthenticated",
        session: null,
        user: null,
      };
    default:
      return state;
  }
};

export const ContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, initialState);

  const login = async (email, password) => {
    const resp = await authLogin(email, password);

    if (!resp?.user) {
      return resp;
    }

    dispatch({
      type: "LOGIN",
      payload: { user: resp.user, session: resp.session },
    });

    return resp;
  };

  const logout = async () => {
    dispatch({ type: "LOGOUT" });
  };

  return (
    <ContextGlobal.Provider value={{ state, login, logout }}>
      {children}
    </ContextGlobal.Provider>
  );
};

export const useRecipeState = () => useContext(ContextGlobal);
