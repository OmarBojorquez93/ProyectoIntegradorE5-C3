import { createContext, useContext, useReducer } from "react";

export const initialState = { user: {} };

export const ContextGlobal = createContext();

export const ContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(initialState);

  return (
    <ContextGlobal.Provider value={{ state, dispatch }}>
      {children}
    </ContextGlobal.Provider>
  );
};

export const useRecipeState = () => useContext(ContextGlobal);
